package ru.job4j.basepatterns.behavioral.strategy.example1;
/**
 * Рассматривается шаблон Strategy
 * У нас есть разарботчик у которого есть несколько
 * активностей в течение дня:
 * сон, тренировка, разработка, чтение
 * У нашего интерфейса будет 4 реализации:
 * сон, тренировка, написание кода, чтение
 * Такой интерфейс еще называют Strategy
 *
 */
public interface Activity {
    public void justDoIt();
}
