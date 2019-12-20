package ru.job4j.generic;

public class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> simpleArray;

    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        simpleArray.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (id == null || model == null) {
            return false;
        }
        boolean result = false;
        int index = 0;
        for (T element : this.simpleArray) {
            //Проверяем на null и на равенство id.
            if (element != null && element.getId().equals(id)) {
                this.simpleArray.set(index, model);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        if (id == null) {
            return false;
        }
        boolean result = false;
        int index = 0;
        for (T element : this.simpleArray) {
            //Проверяем на null и на равенство id.
            if (element != null && element.getId().equals(id)) {
                this.simpleArray.remove(index);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T element : this.simpleArray) {
            //Проверяем на null и на равенство id.
            if (element != null && element.getId().equals(id)) {
                result = element;
                break;
            }
        }
        return result;
    }
}
