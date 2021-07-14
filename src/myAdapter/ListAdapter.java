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

        int prevSize = size();
        HIterator it = c.iterator();
        while (it.hasNext())
            add(index++, it.next());

        return prevSize != size();
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

    /**
     * Returns the hash code value for this list. The hash code of a list is defined to be the result of the following calculation:
     *   hashCode = 1;
     *   Iterator i = list.iterator();
     *   while (i.hasNext()) {
     *       Object obj = i.next();
     *       hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     *   }
     * @return the hash code value for this list.
     */

    @Override
    public int hasCode() {
        int hashCode = 1;
        HIterator i = iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
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

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return an iterator over the elements in this list in proper sequence.
     */

    @Override
    public HIterator iterator() {
        return new IteratorAdapter(this);
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

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     * @return a list iterator of the elements in this list (in proper sequence).
     */

    @Override
    public HListIterator listIterator() {
        return new ListIteratorAdapter(this, -1);
    }

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. The specified index indicates the first element that would be returned by an initial call to the next method.
     * @param index - index of first element to be returned from the list iterator (by a call to the next method).
     * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
     */

    @Override
    public HListIterator listIterator(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();
        return new ListIteratorAdapter(this, index);
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

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, the returned list is empty.) The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa. The returned list supports all of the optional list operations supported by this list.
     * @param fromIndex - low endpoint (inclusive) of the subList.
     * @param toIndex - high endpoint (exclusive) of the subList.
     * @return a view of the specified range within this list.
     * @throws IndexOutOfBoundsException - for an illegal endpoint index value (fromIndex < 0 || toIndex > size || fromIndex > toIndex).
     */

    @Override
    public HList subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException{
        if ((fromIndex < 0 || toIndex > size() || fromIndex > toIndex))
            throw new IndexOutOfBoundsException();
        return new SubList(fromIndex, toIndex);
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

        protected int index;
        protected boolean possible;
        protected ListAdapter list;

        public IteratorAdapter(ListAdapter l){
            this(l, -1);
        }

        public IteratorAdapter(ListAdapter l, int ind){
            this.index = ind;
            possible = false;
            list = l;
        }

        /**
         * Returns true if the iteration has more elements.
         * @return true if the iterator has more elements.
         */

        @Override
        public boolean hasNext() {
            return (index + 1) < list.size();
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
            return list.get(++index);
        }

        /**
         * Removes from the underlying collection the last element returned by the iterator. This method can be called only once per call to next.
         * @throws IllegalStateException - if the next method has not yet been called, or the remove method has already been called after the last call to the next method.
         */

        @Override
        public void remove() throws IllegalStateException{
            if (!possible)
                throw new IllegalStateException();
            possible = false;
            list.remove(index--);
        }
    }



    private class ListIteratorAdapter extends IteratorAdapter implements HListIterator{

        protected int last;



        public ListIteratorAdapter(ListAdapter l, int ind){
            super(l, ind);
            last = -1;
        }

        /**
         * Inserts the specified element into the list. The element is inserted immediately before the next element that would be returned by next, if any, and after the next element that would be returned by previous, if any.
         * @param o - the element to insert.
         * @throws IllegalArgumentException - if some aspect of this element prevents it from being added to this list.
         */

        @Override
        public void add(Object o) throws IllegalArgumentException{
            if (o == null)
                throw new IllegalArgumentException();

            possible = false;
            list.add(++index, o);
        }

        /**
         * Returns true if this list iterator has more elements when traversing the list in the forward direction.
         * @return true if the list iterator has more elements when traversing the list in the forward direction.
         */

        @Override
        public boolean hasNext() {
            return super.hasNext();
        }

        /**
         * Returns true if this list iterator has more elements when traversing the list in the reverse direction.
         * @return true if the list iterator has more elements when traversing the list in the reverse direction.
         */

        @Override
        public boolean hasPrevious() {
            return index >= 0;
        }

        /**
         * Returns the next element in the list.
         * @return the next element in the list.
         * @throws NoSuchElementException - if the iteration has no next element.
         */

        @Override
        public Object next() throws NoSuchElementException{
            if (!hasNext())
                throw new NoSuchElementException();
            last = index + 1;
            return super.next();
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to next.
         * @return the index of the element that would be returned by a subsequent call to next, or list size if list iterator is at end of list.
         */

        @Override
        public int nextIndex() {
            if (hasNext())
                return index + 1;
            else
                return list.size();
        }

        /**
         * Returns the previous element in the list.
         * @return the previous element in the list.
         * @throws NoSuchElementException - if the iteration has no previous element.
         */

        @Override
        public Object previous() throws NoSuchElementException{
            if (!hasPrevious())
                throw new NoSuchElementException();
            last = index;
            possible = true;
            return list.get(index--);
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to previous.
         * @return the index of the element that would be returned by a subsequent call to previous, or -1 if list iterator is at beginning of list.
         */

        @Override
        public int previousIndex() {
            if (hasPrevious())
                return index - 1;
            else
                return -1;
        }

        /**
         * Removes from the list the last element that was returned by next or previous. This call can only be made once per call to next or previous.
         * @throws IllegalStateException - neither next nor previous have been called, or remove or add have been called after the last call to * next or previous.
         */

        @Override
        public void remove() throws IllegalStateException{
            if (!possible)
                throw new IllegalStateException();

            possible = false;
            list.remove(last);
            last = -1;
        }

        /**
         * Replaces the last element returned by next or previous with the specified element.
         * @param o - the element with which to replace the last element returned by next or previous.
         * @throws IllegalArgumentException - if some aspect of the specified element prevents it from being added to this list.
         * @throws IllegalStateException - if neither next nor previous have been called, or remove or add have been called after the last call to next or previous.
         */

        @Override
        public void set(Object o) throws IllegalStateException, IllegalArgumentException{
            if (!possible)
                throw new IllegalStateException();

            if (o == null)
                throw new IllegalArgumentException();

            list.set(last, o);
        }
    }

    private class SubList extends ListAdapter {
        private int from;
        private int to;

        public SubList(int f, int t){
            from = f;
            to = t;
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

            ListAdapter.this.add(index + from, element);
            to++;
        }

        /**
         * Appends the specified element to the end of this list.
         * @param o - element to be appended to this list.
         * @return true.
         * @throws NullPointerException - if the specified element is null.
         */

        @Override
        public boolean add(Object o) throws NullPointerException{
            ListAdapter.this.add(to++, o);
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

            int prevSize = size();
            ListAdapter.this.addAll(index + from, c);
            to += c.size();
            return prevSize != size();
        }

        /**
         * Removes all of the elements from this list. This list will be empty after this call returns.
         */

        @Override
        public void clear() {
            for (int i = from; i < to; i++)
                ListAdapter.this.remove(i + from);
            to = from + 1;
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

            for (int i = from; i < to; i++)
                if (ListAdapter.this.get(i + from).equals(o))
                    return true;
            return false;
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
            int counter = 0;
            for (int i = 0; i < temp.length; i++)
                if (contains(temp[i]))
                    counter++;

            return counter == temp.length;
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
            return ListAdapter.this.get(from + index);
        }

        /**
         * Returns the hash code value for this list. The hash code of a list is defined to be the result of the following calculation:
         *   hashCode = 1;
         *   Iterator i = list.iterator();
         *   while (i.hasNext()) {
         *       Object obj = i.next();
         *       hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
         *   }
         * @return the hash code value for this list.
         */

        @Override
        public int hasCode() {
            int hashCode = 1;
            HListIterator i = ListAdapter.SubList.this.listIterator();
            while (i.hasNext()) {
                Object obj = i.next();
                hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
            }
            return hashCode;
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
            for (int i = from; i < to; i++)
                if (ListAdapter.this.get(i).equals(o))
                    return i - from;
            return -1;
        }

        /**
         * Returns true if this list contains no elements.
         * @return true if this list contains no elements.
         */

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        /**
         * Returns an iterator over the elements in this list in proper sequence.
         * @return an iterator over the elements in this list in proper sequence.
         */

        @Override
        public HIterator iterator() {
            return new SubListIterator(ListAdapter.this, from, from, to);
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
            HIterator it = iterator();
            int index = 0;
            int lastIndex = -1;
            while (it.hasNext()) {
                if (it.next().equals(o))
                    lastIndex = index;
                index++;
            }
            return lastIndex;
        }

        /**
         * Returns a list iterator of the elements in this list (in proper sequence).
         * @return a list iterator of the elements in this list (in proper sequence).
         */

        @Override
        public HListIterator listIterator() {
            return new SubListIterator(ListAdapter.this, from, from, to);
        }

        /**
         * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. The specified index indicates the first element that would be returned by an initial call to the next method.
         * @param index - index of first element to be returned from the list iterator (by a call to the next method).
         * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
         * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size()).
         */

        @Override
        public HListIterator listIterator(int index) throws IndexOutOfBoundsException{
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();
            return new ListIteratorAdapter(this, index);
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

            to--;
            return ListAdapter.this.remove(index + from);
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

            int index = indexOf(o);
            if (index >= 0){
                remove(index);
                return true;
            }
            return false;
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
            HIterator it = c.iterator();
            int size = size();
            Object temp;
            while (it.hasNext()){
                temp = it.next();
                if (contains(temp))
                    remove(temp);
            }
            return size != size();
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

            HIterator it = c.iterator();
            int size = size();
            while (it.hasNext())
                if(!c.contains(it.next()))
                    it.remove();
            return size != size();
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

            Object temp = ListAdapter.this.get(from + index);
            ListAdapter.this.set(from + index, element);
            return temp;
        }

        /**
         * Returns the number of elements in this list. If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
         * @return the number of elements in this list.
         */

        @Override
        public int size() {
            return to - from;
        }

        /**
         * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, the returned list is empty.) The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa. The returned list supports all of the optional list operations supported by this list.
         * @param fromIndex - low endpoint (inclusive) of the subList.
         * @param toIndex - high endpoint (exclusive) of the subList.
         * @return a view of the specified range within this list.
         * @throws IndexOutOfBoundsException - for an illegal endpoint index value (fromIndex < 0 || toIndex > size || fromIndex > toIndex).
         */

        @Override
        public HList subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException{
            if ((fromIndex < 0 || toIndex > size() || fromIndex > toIndex))
                throw new IndexOutOfBoundsException();
            return new SubList(fromIndex, toIndex);
        }

        /**
         * Returns an array containing all of the elements in this list in proper sequence.
         * @return an array containing all of the elements in this list in proper sequence.
         */

        @Override
        public Object[] toArray() {
            Object[] temp = new Object[size()];
            for (int i = 0; i < size(); i++)
                temp[i] = ListAdapter.this.get(from + i);
            return temp;
        }

        /**
         * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
         * @param a - the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
         * @return an array containing the elements of this list.
         * @throws NullPointerException - if the specified array is null.
         */

        @Override
        public Object[] toArray(Object[] a) throws NullPointerException {
            if (a == null)
                throw new NullPointerException();

            if (a.length >= size()) {
                for (int i = 0; i < size(); i++)
                    a[i] = get(i);
                return a;
            } else {
                return toArray();
            }
        }

        private class SubListIterator extends ListIteratorAdapter implements HListIterator{

            private int from;

            private int to;

            public SubListIterator(ListAdapter l, int index, int from, int to) {
                super(l, index);
                this.from = from;
                this.to = to;
            }

            /**
             * Inserts the specified element into the list. The element is inserted immediately before the next element that would be returned by next, if any, and after the next element that would be returned by previous, if any.
             * @param o - the element to insert.
             * @throws IllegalArgumentException - if some aspect of this element prevents it from being added to this list.
             */

            @Override
            public void add(Object o) throws IllegalArgumentException{
                if (o == null)
                    throw new IllegalArgumentException();

                to++;
                ListAdapter.SubList.this.to = to;
                super.add(o);
            }

            /**
             * Returns true if this list iterator has more elements when traversing the list in the forward direction.
             * @return true if the list iterator has more elements when traversing the list in the forward direction.
             */

            @Override
            public boolean hasNext() {
                return index < to;
            }

            /**
             * Returns true if this list iterator has more elements when traversing the list in the reverse direction.
             * @return true if the list iterator has more elements when traversing the list in the reverse direction.
             */

            @Override
            public boolean hasPrevious() {
                return index >= from;
            }

            /**
             * Returns the next element in the list.
             * @return the next element in the list.
             * @throws NoSuchElementException - if the iteration has no next element.
             */

            @Override
            public Object next() throws NoSuchElementException{
                if (!hasNext())
                    throw new NoSuchElementException();
                return super.next();
            }

            /**
             * Returns the index of the element that would be returned by a subsequent call to next.
             * @return the index of the element that would be returned by a subsequent call to next, or list size if list iterator is at end of list.
             */

            @Override
            public int nextIndex() {
                return index - from;
            }

            /**
             * Returns the previous element in the list.
             * @return the previous element in the list.
             * @throws NoSuchElementException - if the iteration has no previous element.
             */

            @Override
            public Object previous() throws NoSuchElementException{
                if (!hasPrevious())
                    throw new NoSuchElementException();
                return super.previous();
            }

            /**
             * Returns the index of the element that would be returned by a subsequent call to previous.
             * @return the index of the element that would be returned by a subsequent call to previous, or -1 if list iterator is at beginning of list.
             */

            @Override
            public int previousIndex() {
                return index - from - 1;
            }

            /**
             * Removes from the list the last element that was returned by next or previous. This call can only be made once per call to next or previous.
             * @throws IllegalStateException - neither next nor previous have been called, or remove or add have been called after the last call to * next or previous.
             */

            @Override
            public void remove() throws IllegalStateException{
                if (!possible)
                    throw new IllegalStateException();

                possible = false;
                list.remove(last);
                last = -1;
                to--;
                ListAdapter.SubList.this.to = to;
            }

            /**
             * Replaces the last element returned by next or previous with the specified element.
             * @param o - the element with which to replace the last element returned by next or previous.
             * @throws IllegalArgumentException - if some aspect of the specified element prevents it from being added to this list.
             * @throws IllegalStateException - if neither next nor previous have been called, or remove or add have been called after the last call to next or previous.
             */

            @Override
            public void set(Object o) throws IllegalStateException, IllegalArgumentException{
                if (!possible)
                    throw new IllegalStateException();

                if (o == null)
                    throw new IllegalArgumentException();

                list.set(last, o);
            }
        }
    }
}
