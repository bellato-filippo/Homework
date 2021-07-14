package myTest;

import myAdapter.*;
import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ListAdapterTest {

    private ListAdapter list;

    @Before
    public void setUp(){
        list = new ListAdapter();
    }

    @Test
    public void precondizioni(){
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testAdd(){
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertTrue(list.add("elemento 1"));
        assertTrue(list.add("elemento 2"));
        assertTrue(list.add("elemento 3"));
        assertFalse(list.isEmpty());
        assertEquals(3, list.size());
        assertEquals("elemento 1", list.get(0));
        assertEquals("elemento 2", list.get(1));
        assertEquals("elemento 3", list.get(2));
        assertEquals(0, list.indexOf("elemento 1"));
        assertEquals(1, list.indexOf("elemento 2"));
        assertEquals(2, list.indexOf("elemento 3"));

        assertThrows(NullPointerException.class, () -> {list.add(null);});
    }

    @Test
    public void testAddIndex(){
        assertTrue(list.add("elemento 1"));
        list.add(1, "elemento 2");
        assertEquals("elemento 2", list.get(1));
        assertEquals(1, list.indexOf("elemento 2"));

        list.add(0, "elemento 3");
        assertEquals("elemento 3", list.get(0));
        assertEquals(0, list.indexOf("elemento 3"));
        assertEquals("elemento 1", list.get(1));
        assertEquals(1, list.indexOf("elemento 1"));
        assertEquals("elemento 2", list.get(2));
        assertEquals(2, list.indexOf("elemento 2"));
        list.add(3, "elemento 4");
        assertEquals("elemento 4", list.get(3));
        assertEquals(4, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(-1, "oggetto");});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(5, "oggetto");});
        assertThrows(NullPointerException.class, () -> {list.add(1, null);});
    }

    @Test
    public void testAddAll(){
        ListAdapter col = getCollection();
        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
        assertTrue(list.addAll(col));
        assertEquals(10, list.size());
        for (int i = 0; i < 10; i++)
            assertEquals("elemento " + i, list.get(i));
        list.clear();
        assertTrue(list.add("elemento a"));
        assertTrue(list.add("elemento b"));
        assertTrue(list.add("elemento c"));
        assertTrue(list.addAll(col));
        assertEquals("elemento a", list.get(0));
        assertEquals("elemento 9", list.get(12));
        assertEquals(3, list.indexOf("elemento 0"));
        ListAdapter col1 = new ListAdapter();
        assertFalse(list.addAll(col1));
    }

    @Test
    public void testAddAllindex(){
        ListAdapter col = getCollection();
        assertTrue(list.add("elemento a"));
        assertTrue(list.add("elemento b"));
        assertTrue(list.add("elemento c"));
        ListAdapter col1 = new ListAdapter();
        assertFalse(list.addAll(col1));

        assertTrue(list.addAll(1, col));
        assertEquals(13, list.size());
        assertEquals("elemento a", list.get(0));
        assertEquals("elemento 0", list.get(1));
        assertEquals("elemento 9", list.get(10));
        assertEquals("elemento b", list.get(11));
        assertEquals("elemento c", list.get(12));

        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.addAll(25, col);});
    }

    @Test
    public void testClear(){
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        list.clear();
        assertEquals(0, list.size());
        list.add("elemento");
        assertEquals(1, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testContains(){
        assertFalse(list.contains("elemento"));
        list.add("elemento 1");
        list.add("elemento 2");
        list.add("elemento 3");
        assertTrue(list.contains("elemento 2"));
        assertFalse(list.contains("elemento 4"));
        assertThrows(NullPointerException.class, () -> {list.contains(null);});
    }

    @Test
    public void testContainsAll(){
        list.addAll(getCollection());
        assertTrue(list.containsAll(getCollection()));
        ListAdapter l = new ListAdapter();
        l.add("elemento 1");
        l.add("elemento 2");
        l.add("elemento 7");
        assertTrue(list.containsAll(l));
        l.add("elemento 15");
        assertFalse(list.containsAll(l));
        assertThrows(NullPointerException.class, () -> {list.containsAll(null);});
    }

    @Test
    public void testEquals(){
        Object[] temp = list.toArray();
        assertFalse(list.equals(temp));
        list.addAll(getCollection());
        ListAdapter l = new ListAdapter();
        assertFalse(list.equals(l));
        l.addAll(getCollection());
        assertTrue(list.equals(l));
    }

    @Test
    public void testGet(){
        list.addAll(getCollection());
        for (int i = 0; i < 10; i++)
            assertEquals("elemento " + i, list.get(i));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(15);});
    }

    @Test
    public void testHashCode(){
        list.addAll(getCollection());
        ListAdapter l = new ListAdapter();
        l.addAll(getCollection());
        assertEquals(list.hasCode(), l.hasCode());
    }

    @Test
    public void testIndexOf(){
        list.addAll(getCollection());
        list.addAll(getCollection());
        for (int i = 0; i < 10; i++){
            String s = "elemento " + i;
            assertEquals(i, list.indexOf(s));
        }

        assertEquals(-1, list.indexOf("prova"));
        assertThrows(NullPointerException.class, () -> {list.indexOf(null);});
    }

    @Test
    public void testIsEmpty(){
        assertTrue(list.isEmpty());
        list.add("elemento");
        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIterator(){
        HIterator iter = list.iterator();
        assertFalse(iter.hasNext());
        list.addAll(getCollection());
        assertTrue(iter.hasNext());
        for (int i = 0; i < 10; i++)
            assertEquals("elemento " + i, iter.next());
        assertFalse(iter.hasNext());
        iter.remove();
        assertFalse(list.contains("elemento 9"));
        assertFalse(iter.hasNext());

        assertThrows(IllegalStateException.class, () -> {iter.remove();});
        assertThrows(NoSuchElementException.class, () -> {iter.next();});

        HIterator iter1 = list.iterator();
        assertThrows(IllegalStateException.class, () -> {iter1.remove();});
        Object temp = iter1.next();
        iter1.remove();
        assertFalse(list.contains(temp));
    }


    @Test
    public void testLastIdexOf(){
        list.addAll(getCollection());
        assertEquals(0, list.lastIndexOf("elemento 0"));
        list.add("elemento 0");
        assertEquals(10, list.lastIndexOf("elemento 0"));
        assertEquals(-1, list.lastIndexOf("elemento 12"));

        assertThrows(NullPointerException.class, () -> {list.lastIndexOf(null);});
    }

    @Test
    public void testListIterator(){
        list.addAll(getCollection());
        HListIterator it = list.listIterator();
        assertEquals(0, it.nextIndex());
        assertTrue(it.hasNext());
        assertFalse(it.hasPrevious());
        assertThrows(NoSuchElementException.class, () -> {it.previous();});
        assertThrows(IllegalArgumentException.class, () -> {it.add(null);});
        assertThrows(IllegalStateException.class, () -> {it.remove();});
        for (int i = 0; i < 10; i++){
            assertEquals("elemento " + i, it.next());
        }
        assertFalse(it.hasNext());
        assertTrue(it.hasPrevious());

        for (int i = 9; i > -1; i--)
            assertEquals("elemento " + i, it.previous());

        it.add("nuovo elemento");
        assertEquals(11, list.size());
        assertThrows(IllegalStateException.class, () -> {it.remove();});
        it.next();
        it.set("elemento 10");
        assertEquals(11, list.size());
    }

    @Test
    public void testRemoveIndex(){
        list.addAll(getCollection());
        assertEquals(10, list.size());
        assertEquals("elemento 4", list.remove(4));
        assertEquals(9, list.size());
        assertFalse(list.contains("elemento 4"));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(12);});
    }

    @Test
    public void testRemove(){
        list.addAll(getCollection());
        assertTrue(list.remove("elemento 0"));
        assertFalse(list.contains("elemento 0"));
        assertEquals(0, list.indexOf("elemento 1"));
        assertFalse(list.remove("elemento"));
        assertEquals(9, list.size());

        assertThrows(NullPointerException.class, () -> {list.remove(null);});
    }

    @Test
    public void testRemoveAll(){
        list.addAll(getCollection());
        ListAdapter l = new ListAdapter();
        l.add("elemento 4");
        l.add("elemento 5");
        l.add("elemento 6");
        assertTrue(list.removeAll(l));
        assertFalse(list.contains("elemento 5"));
        assertEquals(4, list.indexOf("elemento 7"));
        assertFalse(list.removeAll(l));
        assertEquals(7, list.size());

        assertThrows(NullPointerException.class, () -> {list.removeAll(null);});
    }

    @Test
    public void testRetainAll(){
        list.addAll(getCollection());
        ListAdapter l = new ListAdapter();
        l.add("elemento 4");
        l.add("elemento 5");
        l.add("elemento 6");
        assertTrue(list.retainAll(l));
        assertFalse(list.contains("elemento 0"));
        assertEquals(3, list.size());
        assertFalse(list.retainAll(l));

        assertThrows(NullPointerException.class, () -> {list.retainAll(null);});
    }

    @Test
    public void testSet(){
        list.addAll(getCollection());
        assertEquals("elemento 0", list.set(0, "nuovo elemento"));
        assertEquals(10, list.size());
        assertFalse(list.contains("elemento 0"));
        assertTrue(list.contains("nuovo elemento"));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.set(15, "elemento");});
        assertThrows(NullPointerException.class, () -> {list.set(5, null);});
    }

    @Test
    public void testSize(){
        assertEquals(0, list.size());
        list.addAll(getCollection());
        assertEquals(10, list.size());
        list.remove(1);
        list.remove(2);
        assertEquals(8, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testToArray(){
        Object[] arr = new Object[10];
        for (int i = 0; i < arr.length; i++)
            arr[i] = "elemento " + i;
        list.addAll(getCollection());
        Object[] temp = list.toArray();
        for (int i = 0; i < temp.length; i++)
            assertEquals(temp[i], arr[i]);

        assertTrue(Arrays.equals(arr, temp));
    }

    @Test
    public void testToArrayParameter(){
        Object[] arr = new Object[10];
        for (int i = 0; i < arr.length; i++)
            arr[i] = "elemento " + i;
        list.addAll(getCollection());
        Object[] temp = list.toArray();
        assertTrue(Arrays.equals(arr, temp));
        Object[] arr1 = new Object[5];
        Object[] temp1 = list.toArray(arr1);
        assertTrue(Arrays.equals(temp1, arr));

        assertThrows(NullPointerException.class, () -> {list.toArray(null);});
    }






    private ListAdapter getCollection(){
        ListAdapter l = new ListAdapter();
        for (int i = 0; i < 10; i++) {
            String s = "elemento " + i;
            l.add(s);
        }
        return l;
    }
}