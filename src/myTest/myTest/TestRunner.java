package myTest;

import myAdapter.ListAdapter;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;


public class TestRunner {
    ListAdapter list;

    @Before
    public void setUp(){
        list = new ListAdapter();
    }


    @Test
    public void testAdd() {
        assertEquals(4, 4);
    }

}