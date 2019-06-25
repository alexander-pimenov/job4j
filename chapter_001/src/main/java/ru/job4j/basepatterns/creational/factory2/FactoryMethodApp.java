package ru.job4j.basepatterns.creational.factory2;

import java.util.Date;

/**
 * Фабрика нужна если имеет смысл создавать дополнительный класс maker, если имеются какие либо
 * сложные составные объекты и существует сложность создания таких объектов.
 * В данном примере класс-создатель и класс-продукт просто параллельные классы. И класс-создатель
 * только и делает, что создает экземпляр класса-продукта. Пример был бы более полноценный,
 * если бы класс-создатель помимо создания экземпляра класса-продукта делал бы еще какую либо работу,
 * что, в принципе, и предполагается на практике.
 */
public class FactoryMethodApp {
    public static void main(String[] args) {
//        Watch watch = new DigitalWatch();
//        watch.showTime();

        /*По итогу получится, что наш клиентский код не меняется, а меняется только
         * ("digital") или ("rome"). При необходимости добавить часы, нам нужно только
         * добавлять подклассы, а клиентский код не трогаем*/

        /*пусть у нас будет производитель часов WatchMaker, не знаем какой конкретный
         * А потом указали конкретный DigitalWatchMaker или new DigitalWatchMaker*/
        // WatchMaker maker = new DigitalWatchMaker();//new RomeWatchMaker

        WatchMaker maker = getMakerByName("rome");
        /*теперь хотим получить часы*/
        Watch watch = maker.createWatch(); // - этот код теперь будет неизменен!!!
        watch.showTime();
    }

    /*напишем статический метод, который возвращает конкретный объект DigitalWatchMaker
     * или RomeWatchMaker, а вкачестве аргумента принимает просто строку*/
    public static WatchMaker getMakerByName(String maker) {
        if (maker.equalsIgnoreCase("digital"))
            return new DigitalWatchMaker();
        else if (maker.equalsIgnoreCase("rome"))
            return new RomeWatchMaker();
        /*если передают не поддерживаемый тип часов, то метод будет
         * выбрасывать исключение и сообщение*/
        throw new RuntimeException("Не поддерживаемая производственная линия часов: " + maker);
    }
}

/*есть часы, которые показывают время, и мы задали интерфейс этих часов*/
interface Watch {
    void showTime();
}

/*создаем конкретные часы*/
/*электронные часы*/
class DigitalWatch implements Watch {
    @Override
    public void showTime() {
        System.out.println(new Date());
    }
}

/*римские часы*/
class RomeWatch implements Watch {
    @Override
    public void showTime() {
        System.out.println("VII - XX");
    }
}

/*нужен тот класс, который будет производить экземпляры часов
 * этот класс имеет фабричный метод*/
interface WatchMaker {
    /*этот класс имеет всего лишь один метод,
     * который возвращает объект часы Watch, будем возвращать разные часы*/
    Watch createWatch();
}

/*Ниже напишем две наших реализации часов, хотя можно и намного больше,
 * т.е. сколько нам потребуется. Подкаждую реализацию часов нужно создать
 * своего производителя*/
/*напишем 1 реализацию производителя WatchMaker, который будет возвращать
 * объект DigitalWatch*/
class DigitalWatchMaker implements WatchMaker {

    @Override
    public Watch createWatch() {
        return new DigitalWatch();
    }
}

/*напишем 2 реализацию производителя WatchMaker, который будет возвращать
 * объект RomeWatch*/
class RomeWatchMaker implements WatchMaker {
    @Override
    public Watch createWatch() {
        return new RomeWatch();
    }
}
