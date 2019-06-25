package ru.job4j.basepatterns.creational.abstractfactory2;

/**
 * В этом примере реализованы две фабрики, Ru и En: RuDeviceFactory и EnDeviceFactory
 * Если нужна третья, например французская FR, то нужно создать фабрику FrDeviceFactory
 * для французских устройств и каждое фр.устройство мы должны описать FrMouse,
 * FrKeyboard, FrTouchpad
 * Клиентский код при этом меняться не будет, в этом и есть удобство фабрики
 */

public class AbstractFactoryApp {
    public static void main(String[] args) {
        /*клиентский код:*/
        /*создаем фабрику DeviceFactory factory*/
        DeviceFactory factory = getFactoryByCountryCode("RU");
        /*теперь создаем наши устройства этой фабрикой*/
        Mouse m = factory.getMouse();
        Keyboard k = factory.getKeyboard();
        Touchpad t = factory.getTouchpad();

        /*вызываем методы у наших устройств*/
        m.click();
        m.dbclick();
        m.scroll(5);
        k.print();
        k.println();
        t.track(10, 35);

    }

    /*метод который возвращает нам конкретную реализацию фабрики
     * в зависимости от переданного аргумента (код страны)
     * пока поддержка двух стран*/
    private static DeviceFactory getFactoryByCountryCode(String lang) {
        switch (lang) {
            case "RU":
                return new RuDeviceFactory();
            case "EN":
                return new EnDeviceFactory();
            default:
                throw new RuntimeException("Unsupported Country Code: " + lang);
        }
    }
}

/*делаем мышь, клавиатуру и тачпад*/
interface Mouse {
    void click();

    void dbclick();

    void scroll(int direction);
}

interface Keyboard {
    void print();

    void println();
}

interface Touchpad {
    void track(int deltaX, int deltaY);
}

/*делаем абстрактную фабрику, интерфейс*/
interface DeviceFactory {
    //фабрика возвращает три устройства: мышь, клавиатуру, тачпад
    Mouse getMouse();

    Keyboard getKeyboard();

    Touchpad getTouchpad();
}

/*создаем фабрики под разные локализации, т.е. языки(страны)*/
/*русская - реализуем устройства для русской локализации*/
class RuMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("Щелчок мышью");
    }

    @Override
    public void dbclick() {
        System.out.println("Двойной щелчок мышью");
    }

    @Override
    public void scroll(int direction) {
        if (direction > 0)
            System.out.println("Скроллим вверх");
        else if (direction < 0)
            System.out.println("Скроллим вниз");
        else
            System.out.println("Не скроллим");
    }
}

class RuKeyboard implements Keyboard {
    @Override
    public void print() {
        System.out.print("Печатаем строку ");
    }

    @Override
    public void println() {
        System.out.println("Печатаем строку с переводом строки");

    }
}

class RuTouchpad implements Touchpad {
    @Override
    public void track(int deltaX, int deltaY) {
        int s = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        System.out.println("Передвинулись на  " + s + " пикселей");
    }
}

/*английская - реализуем устройства для английской локализации*/
class EnMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("Mouse click");
    }

    @Override
    public void dbclick() {
        System.out.println("Mouse double click");
    }

    @Override
    public void scroll(int direction) {
        if (direction > 0)
            System.out.println("Scroll Up");
        else if (direction < 0)
            System.out.println("Scroll Down");
        else
            System.out.println("No scrolling");

    }
}

class EnKeyboard implements Keyboard {
    @Override
    public void print() {
        System.out.print("Print");
    }

    @Override
    public void println() {
        System.out.println("Print Line");

    }
}

class EnTouchpad implements Touchpad {
    @Override
    public void track(int deltaX, int deltaY) {
        int s = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        System.out.println("Moved " + s + " pixels");
    }
}

/*создаем фабрики, которые эти устройства будут производить.
 * т.е имеем две фабрики, которые будут производить два семейства устройств :
 * русские и английские.
 * они будут реализовывать интерфейс фабрики DeviceFactory*/
/*если сравнивать с Фабричным Методом, то наша Фабрика должна реализовать
 * три фабричных метода: вернет нам три разных объекта Mouse, Keyboard, Touchpad
 * в то время как Фабричный метод возвращал только один объект!!!*/
class EnDeviceFactory implements DeviceFactory {
    @Override
    public Mouse getMouse() {
        return new EnMouse();
    }

    @Override
    public Keyboard getKeyboard() {
        return new EnKeyboard();
    }

    @Override
    public Touchpad getTouchpad() {
        return new EnTouchpad();
    }
}

class RuDeviceFactory implements DeviceFactory {
    @Override
    public Mouse getMouse() {
        return new RuMouse();
    }

    @Override
    public Keyboard getKeyboard() {
        return new RuKeyboard();
    }

    @Override
    public Touchpad getTouchpad() {
        return new RuTouchpad();
    }
}