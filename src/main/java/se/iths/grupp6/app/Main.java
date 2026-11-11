package se.iths.grupp6.app;

import se.iths.grupp6.app.model.ActionMovie;
import se.iths.grupp6.app.model.ComedyMovie;
import se.iths.grupp6.app.model.Movie;
import se.iths.grupp6.app.storage.ActionMovieStorage;
import se.iths.grupp6.app.storage.ComedyMovieStorage;
import se.iths.grupp6.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final MovieStorage<ComedyMovie> comedyMovieStorage =
            new ComedyMovieStorage(Path.of("data", "comedy-movies.json"));
    private static final MovieStorage<ActionMovie> actionMovieMovieStorage =
            new ActionMovieStorage(Path.of("data", "action-movies.json"));

    public static void main() throws IOException {
        saveStarterMovies();
        runTheMenu();
    }

    private static void saveStarterMovies() throws IOException {
        comedyMovieStorage.save(new ComedyMovie(1, "The Grand Budapest Hotel"));
        comedyMovieStorage.save(new ComedyMovie(2, "Mean Girls"));
        comedyMovieStorage.save(new ComedyMovie(3, "Zootopia"));
        comedyMovieStorage.save(new ComedyMovie(4, "Puss in Boots: The Last Wish"));
        comedyMovieStorage.save(new ComedyMovie(5, "Barbie"));

        actionMovieMovieStorage.save(new ActionMovie(1,"Avengers"));
        actionMovieMovieStorage.save(new ActionMovie(2,"Star Wars"));
        actionMovieMovieStorage.save(new ActionMovie(3,"Indian Jones"));
        actionMovieMovieStorage.save(new ActionMovie(4,"Die Hard"));
        actionMovieMovieStorage.save(new ActionMovie(5,"Terminator"));
    }

    private static void runTheMenu() throws IOException{
        boolean hasChosen = false;

        while (!hasChosen) {
            IO.println("What do you want to do? \n 1. Show all movies of a genre " +
                    "\n 2. show all movies \n 3. end process ");
            switch (IO.readln("Enter your choice: ")) {

                case "1": getMovieStorage();
                break;

                case "2":
                    List<Movie> movies = new ArrayList<>();

                    try {
                        movies.addAll(comedyMovieStorage.findAll());
                        movies.addAll(actionMovieMovieStorage.findAll());
                    }catch (NullPointerException e ){
                        IO.println("Movies not found");
                    }
                    for (Movie m : movies) {
                        IO.print(m.category() + " ");
                        IO.println(m.getTitle());

                    }
                    break;

                case "3":
                    hasChosen = true;
                    break;

                default:
                    IO.println("Please enter a valid choice!");
                    break;

            }
        }
    }

    private static void getMovieStorage() throws IOException{
        System.out.println("1. Comedy Movies; 2.Action Movies");
        String choice = IO.readln("Enter your category choice: ");
        boolean categoryChosen = false;
        List<Movie> category = new ArrayList<>();
        while (!categoryChosen) {
            switch (choice) {

                case "1":
                    category.addAll(comedyMovieStorage.findAll());
                    for (Movie g : category) {
                        IO.println(g.getTitle());
                    }
                    categoryChosen = true;
                    break;

                case "2":
                    category.addAll(actionMovieMovieStorage.findAll());
                    for (Movie g : category) {
                        IO.println(g.getTitle());
                    }
                    categoryChosen =true;
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

}
