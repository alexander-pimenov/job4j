package ru.job4j.basepatterns.behavioral.state;

/**
 * Пример использования шаблона State
 * в данном примере переключение между состояниями происходит внутри Контекста (это один вариант)
 * но также бывает , что переключение между состояниями происходит в самих состояниях (см.второй вариант)
 */
public class StateApp {
    public static void main(String[] args) {

        /*
         * здесь пишем проверочный код, чтобы посмотреть как это работает
         */
        Station dfm = new RadioDFM(); //создали какую нибудь станцию
        Radio radio = new Radio(); // создаем Контекст
        radio.setStation(dfm); // зададим для радио первую станцию dfm

        for (int i = 0; i < 10; i++) {
            radio.play();
            radio.nextStation();
        }
    }
}

//State. Интерфейс Станция, у которой будет 1 метод Play
interface Station {
    void play();
}

//определяем одну радиостанцию. Это одно из конкретных состояний
//Radio7 реализует наш Station
class Radio7 implements Station {

    @Override
    public void play() {
        System.out.println("Радио 7...");
    }
}

//определякм вторую радиостанцию. Это второе конкретное состояние
class RadioDFM implements Station {
    @Override
    public void play() {
        System.out.println("Радио DFM...");
    }
}

//определяем третью радиостанцию. Это наше третье конкретное состояние.
class VestiFM implements Station {
    @Override
    public void play() {
        System.out.println("Вести FM...");
    }
}

/**
 * Context. Создаем контекст.
 * Контекстом будет само радио
 * В данном шаблоне, переключение между состояниями производит сам Контекст
 */
class Radio {
    Station station; //у нашего Radio есть радиостанция. Это его состояние

    /**
     * этот метод будет присваивать нашей станции, то значение которое мы передадим
     * при вызове setStation(Station st). Как по шаблону setCurrentState
     */
    void setStation(Station st) {
        station = st;
    }

    /**
     * метод будет задавать переключение между нашими состояниями.
     * переключаемся между тремя радиостанциями
     */
    void nextStation() {
        if (station instanceof Radio7) { //мы говорим, что если наша текущая радиостанция Radio7, то мы зададим новую радиостанцию RadioDFM
            setStation(new RadioDFM());
        } else if (station instanceof RadioDFM) { //для проверки типов используем instanceof
            setStation(new VestiFM());
        } else if (station instanceof VestiFM) { //если мы находимся на VestiFM, то переключаемся на Radio7
            setStation(new Radio7());
        }
    }

    /**
     * метод - играть Радио
     */
    void play() {
        station.play();
    }
}


