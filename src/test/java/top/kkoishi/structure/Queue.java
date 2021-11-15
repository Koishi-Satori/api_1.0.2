package top.kkoishi.structure;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public class Queue<E> implements java.util.Queue<E> {
    private E[] list;
    private int size;
    private Class<E> type;

    public Queue (Class<E> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    private void expandSize () {
        size++;
        if (list != null) {
            E[] temp = list;
            list = (E[]) Array.newInstance(type,size);
            System.arraycopy(temp,0,list,0,size - 1);
        } else {
            list = (E[]) Array.newInstance(type,size);
        }
    }

    @Override
    public boolean add (E e) {
        if (size == 0) {
            size++;
        }
        expandSize();

        list[size - 2] = e;
        return true;
    }

    @Deprecated
    @Override
    public boolean offer (E e) {
        return false;
    }

    @ApiStatus.Experimental
    @Override
    public E remove () {
        size = 0;
        list = null;
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E poll () {
        size--;
        E[] temp = list;
        list = (E[]) Array.newInstance(type,size);
        System.arraycopy(temp,1,list,0,size);
        return temp[0];
    }

    @Override
    public E element () {
        return null;
    }

    @Override
    public E peek () {
        return list[0];
    }

    @Override
    public int size () {
        return size - 1;
    }

    @Override
    public boolean isEmpty () {
        return size == 1;
    }

    @Override
    public boolean contains (Object o) {
        return false;
    }

    @NotNull
    @Override
    public Iterator<E> iterator () {
        return null;
    }

    @NotNull
    @Override
    public Object @NotNull [] toArray () {
        return new Object[0];
    }

    @NotNull
    @Override
    public <T> T @NotNull [] toArray (@NotNull T @NotNull [] a) {
        return null;
    }

    @Override
    public boolean remove (Object o) {
        return false;
    }

    @Override
    public boolean containsAll (@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll (@NotNull Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll (@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll (@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear () {

    }
}
