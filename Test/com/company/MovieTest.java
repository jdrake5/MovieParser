package com.company;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MovieTest {
    Movie testMovie;

    @Before
    public void getMovie() throws Exception {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject)jsonParser.parse(new BufferedReader(new FileReader(new File("C:\\Users\\John Drake\\Desktop\\Code\\CS 126 Projects\\MovieParser\\Test\\com\\company\\TestJson"))));;
        testMovie = new Movie(jsonObject);
    }

    @Test

    public void istAdult() throws Exception {
        assertFalse(testMovie.isAdult());
    }
    @Test
    public void getBackdrop_path() throws Exception {
        assertEquals(testMovie.getBackdrop_path(), "/wSJPjqp2AZWQ6REaqkMuXsCIs64.jpg");
    }
    @Test
    public void getId() throws Exception {
        assertEquals(testMovie.getId(), 550);
    }
    @Test
    public void getOriginal_language() throws Exception {
        assertEquals(testMovie.getOriginal_language(), "en");
    }

    @Test
    public void getOriginal_title() throws Exception {
        assertEquals(testMovie.getOriginal_title(), "Fight Club");
    }

    @Test
    public void getOverview() throws Exception {
        assertEquals(testMovie.getOverview(), "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.");
    }

    @Test
    public void getPopularity() throws Exception {
        assertTrue(testMovie.getPopularity(0));
    }

    @Test
    public void getPoster_path() throws Exception {
        assertEquals(testMovie.getPoster_path(), "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg");
    }

    @Test
    public void getTitle() throws Exception {
        assertEquals(testMovie.getTitle(), "Fight Club");
    }

    @Test
    public void isVideo() throws Exception {
        assertFalse(testMovie.isVideo());
    }

    @Test
    public void getVote_average() throws Exception {
        assertTrue(testMovie.getVote_average(0));
    }

    @Test
    public void getVote_count() throws Exception {
        assertEquals(testMovie.getVote_count(), 6172);
    }

}