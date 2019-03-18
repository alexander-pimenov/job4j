package ru.job4j.samples;

public class Car {
    //Поле - это внутренний контейнер для хранения типов данных.
    //Поле пишем в теле класса. При обращении к полям пишем ключевое слово "this."
    //По умолчанию "0".
    public double volume;

    public void drive (int kilometer) {
        this.volume = this.volume - kilometer;
    }
    public void fill (int gas) {
        this.volume = this.volume + 10 * gas;
    }
    public boolean canDrive () {
        boolean result = this.volume > 0;
        return result;
    }
    public void gasInfo () {
        System.out.println("I can drive" + " " + this.volume +" " + "kilometers.");
    }
}