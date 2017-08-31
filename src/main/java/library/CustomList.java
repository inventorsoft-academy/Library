package library;

import java.util.*;

public class CustomList<T> implements List {
    private static final int DEFAULT_INITIAL_CAPACITY = 5;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private transient Object[] customArrayListElementData;
    private int size;

    //initial capacity
    public CustomList(int initialCapacity) {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        this.customArrayListElementData = new Object[initialCapacity];

    }

    //empty
    public CustomList() {
        super();
        this.customArrayListElementData = EMPTY_ELEMENT_DATA;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;

    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            customArrayListElementData[i] = null;
        size = 0;
    }

    @Override
    public Object remove(int index) {
        Object oldValue = (Object) customArrayListElementData[index];
        int removeNumber = size - index - 1;
        if (removeNumber > 0) {
            System.arraycopy(customArrayListElementData, index + 1, customArrayListElementData, index, removeNumber);
        }
        customArrayListElementData[--size] = null;
        return oldValue;
    }

    @Override
    public Object get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("array index out of bound exception with index at" + index);
        }
        return (Object) customArrayListElementData[index];
    }

    @Override
    public boolean add(Object e) {
        ensureCapacity(size + 1);

        customArrayListElementData[size++] = e;

        return true;

    }

    private void ensureCapacity(int minCapacity) {
        if (customArrayListElementData == EMPTY_ELEMENT_DATA) {

            minCapacity = Math.max(DEFAULT_INITIAL_CAPACITY, minCapacity);

        }
        if (minCapacity - customArrayListElementData.length > 0)

            growCustomArrayList(minCapacity);

    }

    private void growCustomArrayList(int minCapacity) {

        int oldCapacity = customArrayListElementData.length;
        int newCapacity = oldCapacity + (oldCapacity / 2);

        if (newCapacity - minCapacity < 0)

            newCapacity = minCapacity;

        customArrayListElementData = Arrays.copyOf(customArrayListElementData, newCapacity);

    }


    @Override
    public boolean remove(Object o) {
        return false;

    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }


    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }


    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

}