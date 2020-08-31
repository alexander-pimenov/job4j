package ru.job4j.stream.sections;

/**
 * Для решения этого задания нам понадобится создать дополнительный класс Holder.
 * Он будет содержать пару - имя секции и имя студента.
 */

class Holder {
    private String key; //имя секции unit
    private String value; //имя студента

    Holder(String key, String value) {
        this.key = key;
        this.value = value;
    }

    String getKey() {
        return key;
    }

    String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Holder{" + "key='" + key + '\''
                + ", value='" + value + '\''
                + '}';
    }
}
