package myAdapter;

public class ListAdapter implements HList{

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean addAll(HCollection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, HCollection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(HCollection c) {
        return false;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public int hasCode() {
        return 0;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public HIterator iterator() {
        return null;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public HListIterator listIterator() {
        return null;
    }

    @Override
    public HListIterator listIterator(int index) {
        return null;
    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean removeAll(HCollection c) {
        return false;
    }

    @Override
    public boolean retainAll(HCollection c) {
        return false;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public HList subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
