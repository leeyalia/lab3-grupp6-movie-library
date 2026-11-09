package se.iths.grupp6.app.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import se.iths.grupp6.app.model.ComedyMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ComedyMovieStorage implements MovieStorage<ComedyMovie>{
    private final Path file;
    private final ObjectMapper mapper= new ObjectMapper();

    public ComedyMovieStorage(Path file){
        this.file = file;
    }

    @Override
    public List<ComedyMovie> findAll() throws IOException {
        if (!Files.exists(file)){
            return new ArrayList<>();
        }

        return mapper.readValue(file.toFile(), new TypeReference<List<ComedyMovie>>() {});
    }

    @Override
    public ComedyMovie findById(int id) throws IOException {
        for (ComedyMovie movie : findAll()) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public void save(ComedyMovie movie) throws IOException {
        if (findById(movie.getId()) != null) {
            return; // id already exists, do nothing
        }
        List<ComedyMovie> movies = findAll();
        movies.add(movie);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file.toFile(), movies);
    }


}
