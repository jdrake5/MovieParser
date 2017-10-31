package com.company;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class Movie
{
    public Movie(JsonObject movieFromArray){
        parseJson(movieFromArray);
    }
    private boolean adult; // fields represent variables in json
    private String backdrop_path;
    private List<Long> genre_ids = new ArrayList<>();
    private long id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String title;
    private boolean video;
    private Double vote_average;
    private long vote_count;

    /**
     * This method parses a JsonObject into a variety of java variables and arrays
     * @param movie movies is a json object that represents an index from the listOfMovies arrayList in MovieCollection
     */
    private void parseJson(JsonObject movie) { // this is the code used by the end user when movies are parsed
        adult = movie.get("adult").getAsBoolean();
        backdrop_path = movie.get("backdrop_path").getAsString();
        id = movie.get("id").getAsLong();
        original_language = movie.get("original_language").getAsString();
        original_title = movie.get("original_title").getAsString();
        overview = movie.get("overview").getAsString();
        popularity = movie.get("popularity").getAsDouble();
        poster_path = movie.get("poster_path").getAsString();
        title = movie.get("title").getAsString();
        video = movie.get("video").getAsBoolean();
        vote_average = movie.get("vote_average").getAsDouble();
        vote_count = movie.get("vote_count").getAsLong();
        try { // same method of extracting results from the original json is implemented here to parse genre_ids from movie json
            JsonArray genres = movie.getAsJsonArray("genre_ids");
            for (int i = 0; i < genres.size(); i++) {
                long genreIndex = genres.get(i).getAsLong();
                genre_ids.add(genreIndex);
            }

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }
    public boolean isAdult() {
        return adult;
    } // getter methods

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public long getId() {
        return id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public long getVote_count() {
        return vote_count;
    }

    /**
     * This method returns whether or not a given long is in the array genre_ids
     * @param id user passed long which represents the desired genre id
     * @return boolean value which represents whether or not a given genre id is in the genre_ids array for this particular movie
     */
    public boolean getGenre_ids (long id) { // same approach as getPopularity
        for (long idNum: genre_ids){
            if (idNum == id)
                return true;
        }
        return false;
    }

    /**
     * This method compares this movies popularity to a value given by the user
     * @param userPopularity user submitted value for minimum popularity threshold
     * @return returns boolean representative of whether or not the popularity of this movie is sufficient
     */

    public boolean getPopularity(double userPopularity) { // since popularity isn't directly accessed this getter method actually returns a boolean indicative of whether or not a passed genre id is present
        if (userPopularity <= popularity)
            return true;
        return false;
    }

    /**
     * This method compares this movies vote average to a value given by the user
     * @param userAverage user submitted value for minimum vote average threshold
     * @return returns boolean representative of whether or not the vote average of this movie is sufficient
     */

    public boolean getVote_average(double userAverage) { // same approach as getPopularity
        if (userAverage <= vote_average)
            return true;
        return false;
    }
}