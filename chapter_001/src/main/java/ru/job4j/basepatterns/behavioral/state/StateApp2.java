package ru.job4j.basepatterns.behavioral.state;

/**
 * Показан пример работы шаблона State на работе охранника "сутки-трое"
 * здесь переключение между состояниями происходит в самих состояниях
 * а не как в первом варианте с радиостанциями внутри Контекста
 */

public class StateApp2 {
    public static void main(String[] args) {
        Humann human = new Humann();
        human.setState(new Work());
        for (int i = 0; i < 10; i++) {
            human.doSomething();
        }
    }
}

//Context
class Humann {
    Activity2 state;

    public void setState(Activity2 s) {
        this.state = s;
    }

    public void doSomething() {
        state.doSomething(this);
    }

}

//Interface. State. Состояние Активности. в этом состоянии человек может что то делать doSomething
//ниже видно либо Work либо WeekEnd
interface Activity2 {
    void doSomething(Humann context); //добавляем в качестве аргумента ссылку на человека Humann
}

//определим активность человека
//Work реализует Activity2 и метод doSomething
class Work implements Activity2 {
    @Override
    public void doSomething(Humann context) {
        System.out.println("Работаем!!!");
        context.setState(new WeekEnd());
    }
}

class WeekEnd implements Activity2 {
    private int count = 0; // счетчик, чтобы отдохнул 3 дня

    @Override
    public void doSomething(Humann context) {
        if (count < 3) {
            System.out.println("Отдыхаем (Zzz)");
            count++;
        } else {
            context.setState(new Work());
        }
    }
}