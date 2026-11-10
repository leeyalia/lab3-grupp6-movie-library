package se.iths.grupp6.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.grupp6.app.model.ActionMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ActionMovieStorage implements MovieStorage<ActionMovie>{
    private final Path file;
    private final ObjectMapper mapper= new ObjectMapper();

    public ActionMovieStorage(Path file){
        this.file = file;
    }

    @Override
    public void save(ActionMovie movie) throws IOException {
        if (findById(movie.getId()) != null) {
            return;
        }
        List<ActionMovie> movies = findAll();
        movies.add(movie);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file.toFile(), movies);
    }

    @Override
    public ActionMovie findById(int id) throws IOException {
        for (ActionMovie movie : findAll()) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public List<ActionMovie> findAll() throws IOException {
        if (!Files.exists(file)){
            return new ArrayList<>();
        }

        return mapper.readValue(file.toFile(), new TypeReference<List<ActionMovie>>() {});
    }
}
