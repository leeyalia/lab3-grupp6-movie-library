package se.iths.grupp6.app;

import se.iths.grupp6.app.model.ComedyMovie;
import se.iths.grupp6.app.storage.ComedyMovieStorage;
import se.iths.grupp6.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main() throws IOException {
        MovieStorage<ComedyMovie> comedyMovieStorage =
                new ComedyMovieStorage(Path.of("data", "comedy-movies.json"));

        comedyMovieStorage.save(new ComedyMovie(1, "The Grand Budapest Hotel"));
        comedyMovieStorage.save(new ComedyMovie(2, "Mean Girls"));
        comedyMovieStorage.save(new ComedyMovie(3, "Zootopia"));
        comedyMovieStorage.save(new ComedyMovie(4, "Puss in Boots: The Last Wish"));
        comedyMovieStorage.save(new ComedyMovie(5, "Barbie"));

    }
}
