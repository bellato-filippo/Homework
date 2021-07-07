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
        assertEquals(list.get(2), "elemento 3");
        assertEquals(list.indexOf("elemento 2"), 2);

        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(-1, "oggetto");});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(5, "oggetto");});
        assertThrows(NullPointerException.class, () -> {list.add(1, null);});
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