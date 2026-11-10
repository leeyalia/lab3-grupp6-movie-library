package se.iths.grupp6.app.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.grupp6.app.model.ActionMovie;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ActionMovieStorageTest {

    @TempDir
    Path tempDir;

    @Test
    void saveAndRead() throws IOException {
        ActionMovieStorage storage  = new ActionMovieStorage(tempDir.resolve("action.json"));
        storage.save(new ActionMovie(1, "Avengers"));

        ActionMovie found  = storage.findById(1);
        assertEquals(1, found.getId());
        assertEquals("Avengers", found.getTitle());
    }

    @Test
    void duplicateIdIsIgnored() throws IOException {
        ActionMovieStorage storage = new ActionMovieStorage(tempDir.resolve("action.json"));
        storage.save(new ActionMovie(1, "Avengers"));
        storage.save(new ActionMovie(1, "Star Wars"));

        assertEquals(1, storage.findAll().size());
        assertEquals("Avengers", storage.findAll().get(0).getTitle());
    }

    @Test
    void emptyWhenNothingSaved() throws IOException {
        ActionMovieStorage storage = new ActionMovieStorage(tempDir.resolve("action.json"));
        assertTrue(storage.findAll().isEmpty());
    }

    @Test
    void missingIdReturnsNull() throws IOException {
        ActionMovieStorage storage = new ActionMovieStorage(tempDir.resolve("action.json"));
        storage.save(new ActionMovie(1, "Avengers"));
        assertNull(storage.findById(2));
    }

}
