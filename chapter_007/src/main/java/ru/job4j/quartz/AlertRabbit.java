package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;

/**
 * В этом примере нам нужно в консоль выводить сообщение раз в 10 секунд.
 * В этом поможет библиотека http://www.quartz-scheduler.org/
 * Подключили её в pom.xml.
 * <p>
 * Создаем класс AlertRabbit управляющий всеми работами.
 */

public class AlertRabbit {

    private static final Logger LOG = LogManager.getLogger(AlertRabbit.class.getName());
    private static String property;

    public AlertRabbit() {
        this.init();
    }

    private void init() {
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            Properties config = new Properties();
            if (in != null) {
                config.load(in);

                property = config.getProperty("rabbit.interval");
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        try {
            new AlertRabbit();
            //В начале создаем класс, управляющий всеми работами.
            //В объект Scheduler добавляем задачи, которые хотим выполнять периодически.
            Scheduler scheduler =
                    StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            //Создание задачи
            JobDetail job = newJob(Rabbit.class).build();

            //Создание расписания.
            //Конструируем периодичность запуска задачи.
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(property))
//                    .withIntervalInSeconds(10)
                    .repeatForever();

            //Задача выполняется через триггер.
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();

            //Загрузка задачи и триггера в планировщик.
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            LOG.error(se.getMessage(), se);
            se.printStackTrace();
        }
    }

    /**
     * Класс для создания задачи.
     * Реализует интерфейс Job.
     */
    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }
}
