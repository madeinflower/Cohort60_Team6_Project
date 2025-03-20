package utils;

import java.util.Iterator;
import java.util.Arrays;

public abstract class ArrayMyList<T> implements MyList<T> {
    private Object[] elements;
    private int size = 0;

    public ArrayMyList() {
        elements = new Object[10]; // Начальный размер массива
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size++] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) return true;
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return (T) elements[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return (T) elements[currentIndex++];
            }
        };
    }

    // Добавь остальные методы, если они нужны
}
