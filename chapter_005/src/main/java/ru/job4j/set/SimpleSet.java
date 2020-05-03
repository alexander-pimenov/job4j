package ru.job4j.set;

import ru.job4j.list.DynamicSimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private DynamicSimpleArrayList<E> simpleArrayList;

    SimpleSet() {
        this.simpleArrayList = new DynamicSimpleArrayList<>();
    }

    /**
     * Метод добавляющий элементы в Set и делающий проверку на наличие дубликатов,
     * и отсутствие дубликата null.
     * Если Set содержит такой же элемент, то он не будет добавлен.
     * @param e объект
     */
    public void add(E e) {
        boolean isNew = true;
        for (E element : simpleArrayList) {
            //проверка на содержание в коллекции null и что будет
            //если хотим добавить null
            if (element == null) {
                if (e == null) {
                    isNew = false;
                    break;
                }
            } else if (element.equals(e)) { //проверка, если объекты совпадают
                isNew = false;
                break;
            }
        }
        if (isNew) { //запись в сет, если разные объекты
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
