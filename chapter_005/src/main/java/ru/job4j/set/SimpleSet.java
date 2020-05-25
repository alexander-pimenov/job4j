package ru.job4j.set;

import ru.job4j.list.DynamicSimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private DynamicSimpleArrayList<E> simpleArrayList;

    SimpleSet() {
        this.simpleArrayList = new DynamicSimpleArrayList<>();
    }

    /**
     * Метод проверяющий наличие элементов в Set
     *
     * @param value Добавляемый элемент
     * @return булево значение
     */
    public boolean contains(E value) {
        for(E setItem : simpleArrayList) {
            if(setItem == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод добавляющий элементы в Set и делающий проверку на наличие дубликатов,
     * и отсутствие дубликата null.
     * Если Set содержит такой же элемент, то он не будет добавлен.
     * @param e объект
     */
    public void add(E e) {
        if (!contains(e)) { //запись в сет, если разные объекты
            this.simpleArrayList.add(e);
        }
    }

    /**
     * Метод возвращающий размер нашего Set
     * @return int
     */
    public int size(){
        return this.simpleArrayList.size();
    }

    @Override
    public Iterator<E> iterator() {
        return simpleArrayList.iterator();
    }
}
