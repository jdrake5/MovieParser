package com.company;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MovieCollection {
    /**
     *  These constructors create a new movie object based on file name or url
     * @param FileName location of json file
     */
    public MovieCollection(String FileName) {

        if (FileName.length() == 0 || FileName == null)
            parseJson("C:\\Users\\John Drake\\Desktop\\Code\\CS 126 Projects\\MovieParser\\Test\\com\\company\\TestCollection");
        else parseJson(FileName);
    }
    public MovieCollection(URL url, int movies) {
        numOfMovies = movies;
        parseJson(url);
    }
    public List<URL> morePages = new ArrayList<>();
    private int numOfMovies;
    private long page; // field variables represent directly accessible variables in json
    private long total_results;
    private long total_pages;
    private List<Movie> listOfMovies = new ArrayList<Movie>(); // the array results in the json is an array of json objects which represent movies, this list holds those movies
    public long getPage() {
        return page;
    } // getters for fields
    public long getTotal_results() {
        return total_results;
    }
    public long getTotal_pages() {
        return total_pages;
    }
    public int numOfPages(){
        return morePages.size();
    }
    public int numOfMovies(){return listOfMovies.size();}

    /**
     * After Json is downloaded it is put into a file this method will read the file of Json and use it to fill an arraylist with movie objects
     * variables:
     *           jsonParser - parses json
     *           jsonObject - stores the contents of the downloadedJson file as a JsonObject
     *           jsonArr - stores the json array results from downloadedJson
     *           page - directly accessible variable in downloadedJson representing page number
     *           total_results - directly accessible variable in downloadedJson representing total results
     *           total_pages - directly accessible variable in downloadedJson representing total pages
     *           movie - JsonObject representing index of jsonArr
     *           listOfMovies - stores movie objects in arrayList
     * @param FileName location of downloaded Json
     */
    private void parseJson(String FileName) { // this method parses json into variables and a json array of json objects which are transferred into an array list of movies
        try {
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject)jsonParser.parse(new BufferedReader(new FileReader(new File(FileName))));
            JsonArray jsonArr = jsonObject.getAsJsonArray("results");
            page = jsonObject.get("page").getAsLong();
            total_results = jsonObject.get("total_results").getAsLong();
            total_pages = jsonObject.get("total_pages").getAsLong();

            // creates array of json objects turns them into movie objects and adds them to array list
            for (int i = 0; i < jsonArr.size() ; i++) {
                JsonObject movie = (JsonObject)jsonArr.get(i);
                if (movie != null)
                    listOfMovies.add(new Movie(movie));
            }
            if (listOfMovies.size() > numOfMovies){
                while (listOfMovies.size() > numOfMovies){
                    listOfMovies.remove(listOfMovies.size() - 1);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *  This method reads from a url and gets Json which is written to a file the method then call the other parseJson method to read from this file
     *  variables:
     *            in - BufferedReader which reads the Json from the url provided in the main class
     *            inputLine - temp variable which holds any given line of json from the url
     *            InternetJSON.json a public string in the class InternetJSON which accumulates all the json
     * @param movieUrl api url from which Json can be pulled
     */
    private void parseJson(URL movieUrl) {
        try {
            //HttpURLConnection connection = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(movieUrl.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                InternetJSON.json = "" + inputLine;
            in.close();
            InternetJSON.toFile();
            parseJson("C:\\Users\\John Drake\\Desktop\\Code\\CS 126 Projects\\MovieParser\\Test\\com\\company\\DownloadedJSON");

        }catch (MalformedURLException e) {
            System.out.println("bad url");
        }catch (IOException e) {
            System.out.println("IOException");
        }
    }

    /**
     * if there are more movies requested than on a page add more page urls to the arrayList more pages
     */
    public void sentToParseJson(){
        if (morePages.size() > 0) {
            for (URL url: morePages) {
                parseJson(url);
            }
        }
    }

    /**
     * prints every movie in the arrayList listOfMovies
     */
    public void printAllMovies(){ // goes through array list prints movie at given index.getTitle
        for (Movie movie: listOfMovies) {
            System.out.println(movie.getTitle());
        }
    }
    /**
     * prints every movie in the arrayList listOfMovies that has a genre id that mathches with a user provided id
     */
    public void printGenre(long genreId){ // goes through array list prints movie at given index.getTitle if genre id is present in movie
        for (Movie movie: listOfMovies) {
            if (movie.getGenre_ids(genreId))
                System.out.println(movie.getTitle());
        }
    }
    /**
     * prints every movie in the arrayList listOfMovies that has a vote average higher than a user provided threshold
     */
    public void printMoviesByAverage(double avgVotes){ // goes through array list prints movie at given index.getTitle if vote_count is sufficiently high
        for (Movie movie: listOfMovies) {
            if (movie.getVote_average(avgVotes))
                System.out.println(movie.getTitle());
        }
    }
    /**
     *  prints every movie in the arrayList listOfMovies that has a popularity greater than a user provided threshold
     */
    public void printMoviesByPopularity(double minPopularity){ // goes through array list prints movie at given index.getTitle if popularity is sufficiently high
        for (Movie movie: listOfMovies) {
            if (movie.getPopularity(minPopularity))
                System.out.println(movie.getTitle());
        }
    }
}