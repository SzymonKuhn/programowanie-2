package myList;

public class MyListObject <E> {
    private E value;
    private MyListObject<E> nextObject;

    public MyListObject(E value, MyListObject<E> nextObject) {
        this.value = value;
        this.nextObject = nextObject;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public MyListObject<E> getNextObject() {
        return nextObject;
    }

    public void setNextObject(MyListObject<E> nextObject) {
        this.nextObject = nextObject;
    }
}
