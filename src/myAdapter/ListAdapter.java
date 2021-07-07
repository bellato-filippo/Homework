package myAdapter;

import java.util.Vector;

public class ListAdapter implements HList{

    private Vector vector;

    /**
     * Costruttore
     */
    public ListAdapter(){
        vector = new Vector();
    }

    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * @param index - index at which the specified element is to be inserted.
     * @param element - element to be inserted.
     * @throws NullPointerException - if the specified element is null.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     */
    @Override
    public void add(int index, Object element) throws NullPointerException, IndexOutOfBoundsException{
        if (element == null)
            throw new NullPointerException();

        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        vector.insertElementAt(element, index);
    }

    /**
     * Appends the specified element to the end of this list.
     * @param o - element to be appended to this list.
     * @return true.
     * @throws NullPointerException - if the specified element is null.
     */

    @Override
    public boolean add(Object o) throws NullPointerException{
        if (o == null)
            throw new NullPointerException();

        vector.addElement(o);
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator.
     * @param c - collection whose elements are to be added to this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException - if the specified collection contains one or more null elements or if the specified collection is null.
     */

    @Override
    public boolean addAll(HCollection c) throws NullPointerException{
        return addAll(size(), c);
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position. Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices). The new elements will appear in this list in the order that they are returned by the specified collection's iterator.
     * @param index - index at which to insert first element from the specified collection.
     * @param c - elements to be inserted into this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException - if the specified collection contains one or more null elements and this list does not support null elements, or if the specified collection is null.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     */

    @Override
    public boolean addAll(int index, HCollection c) throws IndexOutOfBoundsException, NullPointerException{
        if (c == null)
            throw new NullPointerException();

        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        Object[] temp = c.toArray();
        for (int i = 0; i < temp.length; i++)
            if (temp[i] == null)
                throw new NullPointerException();

        for (int i = 0; i < temp.length; i++)
            vector.insertElementAt(temp[i], index + i);

        if (c.size() == 0)
            return false;
        else
            return true;
    }

    /**
     * Removes all of the elements from this list. This list will be empty after this call returns (unless it throws an exception).
     */

    @Override
    public void clear() {
        vector.removeAllElements();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(HCollection c) {
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index - index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     */

    @Override
    public Object get(int index) throws IndexOutOfBoundsException{
        return vector.elementAt(index);
    }

    @Override
    public int hasCode() {
        return 0;
    }

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o - element to search for.
     * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     */

    @Override
    public int indexOf(Object o) throws NullPointerException{
        return vector.indexOf(o);
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements.
     */

    @Override
    public boolean isEmpty() {
        return vector.isEmpty();
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

    /**
     * Returns the number of elements in this list. If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * @return the number of elements in this list.
     */

    @Override
    public int size() {
        return Math.min(vector.size(), Integer.MAX_VALUE);
    }

    @Override
    public HList subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     * @return an array containing all of the elements in this list in proper sequence.
     */

    @Override
    public Object[] toArray() {
        Object[] temp = new Object[size()];
        vector.copyInto(temp);
        return temp;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
