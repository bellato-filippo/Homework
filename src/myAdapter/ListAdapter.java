package myAdapter;

import java.util.NoSuchElementException;
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
     * Removes all of the elements from this list. This list will be empty after this call returns.
     */

    @Override
    public void clear() {
        vector.removeAllElements();
    }

    /**
     * Returns true if this list contains the specified element.
     * @param o - element whose presence in this list is to be tested.
     * @return true if this list contains the specified element.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     */

    @Override
    public boolean contains(Object o) throws NullPointerException{
        if (o == null)
            throw new NullPointerException();

        return vector.contains(o);
    }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     * @param c - collection to be checked for containment in this list.
     * @return true if this list contains all of the elements of the specified collection.
     * @throws NullPointerException - if the specified collection is null.
     */

    @Override
    public boolean containsAll(HCollection c) throws NullPointerException{
        if (c == null)
            throw new NullPointerException();

        Object[] temp = c.toArray();
        for (int i = 0; i < temp.length; i++)
            if (!vector.contains(temp[i]))
                return false;

        return true;
    }

    /**
     * Compares the specified object with this list for equality. Returns true if and only if the specified object is also a list, both lists have the same size, and all corresponding pairs of elements in the two lists are equal.
     * @param o - the object to be compared for equality with this list.
     * @return true if the specified object is equal to this list.
     */

    public boolean equals(Object o){
        if (!(o instanceof HList))
            return false;

        ListAdapter list = (ListAdapter) o;

        if (size() != list.size())
            return false;

        for (int i = 0; i < size(); i++)
            if (!list.get(i).equals(this.get(i)))
                return false;

        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index - index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     */

    @Override
    public Object get(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
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
        if (o == null)
            throw new NullPointerException();

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

    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o - element to search for.
     * @return the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     */

    @Override
    public int lastIndexOf(Object o) throws NullPointerException{
        if (o == null)
            throw new NullPointerException();

        return vector.lastIndexOf(o);
    }

    @Override
    public HListIterator listIterator() {
        return null;
    }

    @Override
    public HListIterator listIterator(int index) {
        return null;
    }

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left. Returns the element that was removed from the list.
     * @param index - the index of the element to removed.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     */

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        Object temp = get(index);
        vector.removeElementAt(index);
        return temp;
    }

    /**
     * Removes the first occurrence in this list of the specified element. If this list does not contain the element, it is unchanged.
     * @param o - element to be removed from this list, if present.
     * @return true if this list contained the specified element.
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     */

    @Override
    public boolean remove(Object o) {
        if (o == null)
            throw new NullPointerException();

        return vector.remove(o);
    }

    /**
     * Removes from this list all the elements that are contained in the specified collection.
     * @param c - collection that defines which elements will be removed from this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException - if the specified collection is null.
     */

    @Override
    public boolean removeAll(HCollection c) throws NullPointerException{
        if (c == null)
            throw new NullPointerException();

        int counter = 0;
        Object[] temp = c.toArray();
        for (int i = 0; i < temp.length; i++){
            while (contains(temp[i])) {
                remove(temp[i]);
                counter++;
            }
        }

        if (counter > 0)
            return true;
        else
            return false;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection. In other words, removes from this list all the elements that are not contained in the specified collection.
     * @param c - collection that defines which elements this set will retain.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException - if the specified collection is null.
     */

    @Override
    public boolean retainAll(HCollection c) throws NullPointerException{
        if (c == null)
            throw new NullPointerException();

        boolean change = false;
        Object[] temp = toArray();
        for (int i = 0; i < temp.length; i++)
            if (!c.contains(temp[i])) {
                remove(temp[i]);
                change = true;
            }
        return change;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index - index of element to replace.
     * @param element - element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()).
     * @throws NullPointerException - if the specified element is null and this list does not support null elements.
     */

    @Override
    public Object set(int index, Object element) throws NullPointerException, IndexOutOfBoundsException{
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        if (element == null)
            throw new NullPointerException();

        Object temp = vector.elementAt(index);
        vector.setElementAt(element, index);
        return temp;
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

    /**
     * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
     * @param a - the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list.
     * @throws NullPointerException - if the specified array is null.
     */

    @Override
    public Object[] toArray(Object[] a) throws NullPointerException{
        if (a == null)
            throw new NullPointerException();

        if (a.length >= size()){
            vector.copyInto(a);
            return a;
        } else {
            Object[] temp = new Object[size()];
            vector.copyInto(temp);
            return temp;
        }
    }

    private class IteratorAdapter implements HIterator{

        private int index;
        private boolean possible;

        public IteratorAdapter(){
            index = -1;
            possible = false;
        }

        /**
         * Returns true if the iteration has more elements.
         * @return true if the iterator has more elements.
         */

        @Override
        public boolean hasNext() {
            return (index + 1) < size();
        }

        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration.
         * @throws NoSuchElementException - iteration has no more elements.
         */

        @Override
        public Object next() throws NoSuchElementException{
            if(!hasNext())
                throw new NoSuchElementException();
            possible = true;
            return vector.elementAt(index++);
        }

        /**
         * Removes from the underlying collection the last element returned by the iterator. This method can be called only once per call to next.
         * @throws IllegalStateException - if the next method has not yet been called, or the remove method has already been called after the last call to the next method.
         */

        @Override
        public void remove() throws IllegalStateException{
            if (!possible)
                throw new IllegalStateException();
            vector.removeElementAt(index - 1);
        }
    }
}
