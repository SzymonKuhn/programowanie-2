package myList;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyList<E> implements List<E> {

    //TODO zmienić firstElement na null;
    private MyListObject<E> firstElement = new MyListObject<E>(null, null);
//    private MyListObject<E> firstElement = null;

    //TODO wyrzucić instrukcje do mniejszych metod
    //TODO dodać indexof, lastindexof


    @Override
    public boolean add(E o) {
        if (firstElement.getValue() == null) {
            firstElement.setValue(o);
            return true;
        }

        MyListObject newListObject = new MyListObject(o, null);

        if (firstElement.getNextObject() == null) {
            firstElement.setNextObject(newListObject);
            return true;
        }

        MyListObject temp = firstElement.getNextObject();
        while (true) {
            if (temp.getNextObject() == null) {
                temp.setNextObject(newListObject);
                return true;
            } else {
                temp = temp.getNextObject();
            }
        }
    }

    @Override
    public void add(int index, E element) {
        MyListObject<E> objectToAdd = new MyListObject<>(element, null);
        if (index == 0) {
            objectToAdd.setNextObject(firstElement);
            firstElement = objectToAdd;
            return;
        }
        int count = 1;
        MyListObject<E> beforeTemp = firstElement;
        MyListObject<E> temp = firstElement.getNextObject();
        while (true) {
            if (index == count) {
                beforeTemp.setNextObject(objectToAdd);
                objectToAdd.setNextObject(temp);
                return;
            }
            beforeTemp = beforeTemp.getNextObject();
            temp = temp.getNextObject();
            count++;
        }
    }

    @Override
    public E remove(int index) {
        if (index > this.size() - 1) {
            throw new IndexOutOfBoundsException("Błędny indeks");
        }
        if (firstElement.getValue() == null) {
            return null;
        }

        MyListObject<E> temp = firstElement;
        if (index == 0) {
            firstElement = firstElement.getNextObject();
            return temp.getValue();
        }

        MyListObject<E> beforeTemp = firstElement;
        temp = firstElement.getNextObject();
        int count = 1;
        while (true) {
            if (count == index) {
                beforeTemp.setNextObject(temp.getNextObject());
                return temp.getValue();
            }
            beforeTemp = beforeTemp.getNextObject();
            temp = temp.getNextObject();
            count++;
        }
    }

    @Override
    public int size() {
        if (firstElement == null || firstElement.getValue() == null) {
            return 0;
        }
        int size = 1;
        MyListObject temp = firstElement;
        while (true) {
            if (temp.getNextObject() == null) {
                return size;
            } else {
                temp = temp.getNextObject();
                size++;
            }
        }
    }

    @Override
    public boolean remove(Object objectToRemove) { //GENERYK!
        if (firstElement.getValue() == null) {
            return false;
        }

        boolean result = false;

        MyListObject<E> temp = firstElement;
        MyListObject<E> beforeTemp = null;

        do {
            if (temp.getValue().equals(objectToRemove)) {
                result = true;
                if (temp == firstElement) {
                    firstElement = firstElement.getNextObject();
                    temp = firstElement;
                } else {
                    beforeTemp.setNextObject(temp.getNextObject());
                    temp = temp.getNextObject();
                }
            } else {
                beforeTemp = temp;
                temp = temp.getNextObject();
            }

        } while (temp != null);
        return result;
    }


    @Override
    public boolean isEmpty() {
        return false;
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

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator operator) {

    }

    @Override
    public void sort(Comparator c) {

    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        if (index > this.size() - 1) {
            new NullPointerException("No such index");
        }

        if (index == 0) {
            return firstElement.getValue();
        }

        MyListObject<E> temp = firstElement.getNextObject();
        int count = 1;
        while (true) {
            if (index == count) {
                return temp.getValue();
            }
            temp = temp.getNextObject();
            count++;
        }
    }

    @Override
    public Object set(int index, Object element) {
        return null;
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
    public Spliterator spliterator() {
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
}
