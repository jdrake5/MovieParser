package com.company;
import java.net.*;
/**
 * Created by John Drake on 1/31/2017.
 * This program will take json from themoviedb and parse it into an array of singular movies
 * the singular movies can then be printed by title, popularity, vote average, or genre
 * The program works by creating a class MovieCollection which parses original json and runs methods called in main
 * After MovieCollection parse the json it will create an array of movies to be used in the methods of MovieCollection
 * information on individual movies is parsed and stored in the class Movie
 */
public class Main {
    //private static MovieCollection collection1 = new MovieCollection("C:\\Users\\John Drake\\Desktop\\Code\\CS 126 Projects\\MovieParser\\Test\\com\\company\\TestCollection"); // creates new moviecollection class with file path to json
    public static void main (String[] args) {
        try {
            System.out.println("Welcome to Movie Parser this program will print movie titles from themoviedb");// intro
            int howManyMovies = TextIO.getlnInt();
            int storeHowMany = howManyMovies;
            URL url = new URL("https://api.themoviedb.org/3/movie/popular?api_key=" + Key.apiKey + "&page=" + 1);
            MovieCollection collection1 = new MovieCollection(url, storeHowMany);
            if (howManyMovies > 20) {
                int i = 2;
                while (howManyMovies > 0 && i<= collection1.getTotal_pages()) {
                    URL url2 = new URL("https://api.themoviedb.org/3/movie/popular?api_key=" + Key.apiKey + "&page=" + i);
                    collection1.morePages.add(url2);
                    i++;
                    howManyMovies -= 20;
                }
                collection1.sentToParseJson();
            }
            System.out.println("Found " + collection1.numOfMovies() + " movies");
            if (storeHowMany > collection1.getTotal_pages()*20) {
                System.out.println("You asked for more movies than there are so you have been provided with the maximum  of reachable movies");
            }
            while (true) {
                System.out.println("############################");
                System.out.println("Would you like to print all Movies(1)"); // options
                System.out.println("Movies of a certain Genre ID(2)");
                System.out.println("Moves with a certain vote average(3)");
                System.out.println("Movies with a certain popularity(4)");
                System.out.println("Exit(5)");
                int user = TextIO.getlnInt();// all user choices correspond to methods in movie collection some require parameters
                switch (user) {
                    case 1:
                        collection1.printAllMovies();
                        break;
                    case 2:
                        System.out.println("What genre Id would you like to see movies from");
                        Long userGenreId = TextIO.getlnLong();
                        collection1.printGenre(userGenreId);
                        break;
                    case 3:
                        System.out.println("Please input a minimum vote average");
                        double userVoteAverage = TextIO.getlnDouble();
                        collection1.printMoviesByAverage(userVoteAverage);
                        break;
                    case 4:
                        System.out.println("Please input minimum popularity");
                        double userMinPopularity = TextIO.getlnDouble();
                        collection1.printMoviesByPopularity(userMinPopularity);
                        break;
                    case 5:
                        System.out.println("You have chosen to exit");
                        return;
                    default: System.out.println("Your Input Could Not Be Recognized");
                        break;
                }
            }
        } catch(MalformedURLException e) {
            System.out.println("Bad URL");
        }
    }
}
