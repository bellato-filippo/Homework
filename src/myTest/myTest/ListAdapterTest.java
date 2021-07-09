package myTest;

import myAdapter.*;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class ListAdapterTest {

    private ListAdapter list;

    @Before
    public void setUp(){
        list = new ListAdapter();
    }

    @Test
    public void precondizioni(){
        assertEquals(list.size(), 0);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testAdd(){
        assertEquals(list.size(), 0);
        assertTrue(list.isEmpty());
        assertTrue(list.add("elemento 1"));
        assertTrue(list.add("elemento 2"));
        assertTrue(list.add("elemento 3"));
        assertFalse(list.isEmpty());
        assertEquals(list.size(), 3);
        assertEquals(list.get(0), "elemento 1");
        assertEquals(list.get(1), "elemento 2");
        assertEquals(list.get(2), "elemento 3");
        assertEquals(list.indexOf("elemento 1"), 0);
        assertEquals(list.indexOf("elemento 2"), 1);
        assertEquals(list.indexOf("elemento 3"), 2);

        assertThrows(NullPointerException.class, () -> {list.add(null);});
    }

    @Test
    public void testAddIndex(){
        assertTrue(list.add("elemento 1"));
        list.add(1, "elemento 2");
        assertEquals(list.get(1), "elemento 2");
        assertEquals(list.indexOf("elemento 2"), 1);

        list.add(0, "elemento 3");
        assertEquals(list.get(0), "elemento 3");
        assertEquals(list.indexOf("elemento 3"), 0);
        assertEquals(list.get(1), "elemento 1");
        assertEquals(list.indexOf("elemento 1"), 1);
        assertEquals(list.get(2), "elemento 2");
        assertEquals(list.indexOf("elemento 2"), 2);
        list.add(3, "elemento 4");
        assertEquals(list.get(3), "elemento 4");

        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(-1, "oggetto");});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(5, "oggetto");});
        assertThrows(NullPointerException.class, () -> {list.add(1, null);});
    }

    @Test
    public void testAddAll(){
        ListAdapter col = getCollection();
        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
        assertTrue(list.addAll(col));
        assertEquals(list.size(), 10);
        for (int i = 0; i < 10; i++)
            assertEquals(list.get(i), "elemento " + i);
        list.clear();
        assertTrue(list.add("elemento a"));
        assertTrue(list.add("elemento b"));
        assertTrue(list.add("elemento c"));
        assertTrue(list.addAll(col));
        assertEquals(list.get(0), "elemento a");
        assertEquals(list.get(12), "elemento 9");
        assertEquals(list.indexOf("elemento 0"), 3);
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
        assertEquals(list.get(0), "elemento a");
        assertEquals(list.get(1), "elemento 0");
        assertEquals(list.get(10), "elemento 9");
        assertEquals(list.get(11), "elemento b");
        assertEquals(list.get(12), "elemento c");

        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.addAll(25, col);});
    }

    @Test
    public void testClear(){
        assertTrue(list.isEmpty());
        assertEquals(list.size(), 0);
        list.clear();
        assertEquals(list.size(), 0);
        list.add("elemento");
        assertEquals(list.size(), 1);
        list.clear();
        assertEquals(list.size(), 0);
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
            assertEquals(list.get(i), "elemento " + i);

        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(15);});
    }

    @Test
    public void testIndexOf(){
        list.addAll(getCollection());
        for (int i = 0; i < 10; i++){
            String s = "elemento " + i;
            assertEquals(list.indexOf(s), i);
        }

        assertEquals(list.indexOf("prova"), -1);
        assertThrows(NullPointerException.class, () -> {list.indexOf(null);});
    }

    @Test
    public void testIsEmpty(){
        assertTrue(list.isEmpty());
        list.add("elemento");
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testLastIdexOf(){
        list.addAll(getCollection());
        assertEquals(list.lastIndexOf("elemento 0"), 0);
        list.add("elemento 0");
        assertEquals(list.lastIndexOf("elemento 0"), 10);
        assertEquals(list.lastIndexOf("elemento 12"), -1);

        assertThrows(NullPointerException.class, () -> {list.lastIndexOf(null);});
    }

    @Test
    public void testRemoveIndex(){
        list.addAll(getCollection());
        assertEquals(list.size(), 10);
        assertEquals(list.remove(4), "elemento 4");
        assertEquals(list.size(), 9);
        assertFalse(list.contains("elemento 4"));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(12);});
    }

    @Test
    public void testRemove(){
        list.addAll(getCollection());
        assertTrue(list.remove("elemento 0"));
        assertFalse(list.contains("elemento 0"));
        assertEquals(list.indexOf("elemento 1"), 0);
        assertFalse(list.remove("elemento"));

        assertThrows(NullPointerException.class, () -> {list.remove(null);});
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