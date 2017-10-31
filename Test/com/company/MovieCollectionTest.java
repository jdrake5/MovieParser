package com.company;

import org.junit.Test;

import static org.junit.Assert.*;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by John Drake on 1/30/2017.
 */
public class MovieCollectionTest {
    MovieCollection Collection1;

    @Before
    public void parseJSON() {
        Collection1 = new MovieCollection("");
    }
    @Test
    public void getPage() throws Exception {
        assertEquals(Collection1.getPage(), 1);
    }
    public void getTotal_results() throws Exception {
        assertEquals(Collection1.getPage(), 1);
    }
    public void getTotal_pages() throws Exception {
        assertEquals(Collection1.getPage(), 1);
    }


}