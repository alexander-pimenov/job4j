package ru.job4j.stream.sections;

/**
 * Для решения этого задания нам понадобится создать дополнительный класс Holder.
 * Он будет содержать пару - имя секции и имя студента.
 */

public class Holder {
    String key; //имя секции
    String value; //имя студента

    public Holder(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Holder{" + "key='" + key + '\''
                + ", value='" + value + '\''
                + '}';
    }
}
