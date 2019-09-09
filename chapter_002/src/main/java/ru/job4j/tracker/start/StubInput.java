package ru.job4j.tracker.start;

import java.util.List;

public class StubInput implements Input {
    /**
     * Это поле содержит последовательность ответов пользователя.
     * Например. Если пользователь
     * хочет выбрать добавление новой заявки ему нужно ввести:
     * 0 - выбор пункта меня "добавить новую заявку".
     * name - имя заявки
     * desc - описание заявки
     * 6, y - выйти из трекера.
     */
    private final List<String> value;

    /**
     * Поле считает количество вызовом метода ask.
     * При каждом вызове надо передвинуть указатель на новое число.
     */
    private int position = 0;

    public StubInput(final List<String> value) {
        this.value = value;
    }

    /**
     * Давайте рассмотрим, как работает этот метод.
     * у нас есть объект в котором содержатся заранее продуманные ответы.
     * При последовательном вызове метода ask нам надо возвращать соответствующие данные.
     * Как если бы мы симулировали поведение пользователя.
     * Для этого при каждом вызове метода ask мы увеличиваем счетчик и
     * при следующем вызове он вернет нам новое значение.
     */
    @Override
    public String ask(String question) {
        return this.value.get(this.position++);
    }

    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.value.get(this.position++));
        boolean exist = false; //по умолчанию считаем, что данные введены с ошибкой
        // значение проходит через диапазон меню
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of menu range.");
        }

        return key;
        //Integer.parseInt(this.ask(question));

        //throw new UnsupportedOperationException("Unsupported operation");
    }
}


//    @Override
//    public int ask(String question, List<Integer> range) {
//
//        return Integer.parseInt(this.ask(question));
//
//        //throw new UnsupportedOperationException("Unsupported operation");
//    }


