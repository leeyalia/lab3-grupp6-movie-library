package se.iths.grupp6.app.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.grupp6.app.model.ComedyMovie;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ComedyMovieStorageTest {
    @TempDir
    Path tempDir;

    @Test
    void saveAndRead() throws IOException {
        ComedyMovieStorage storage  = new ComedyMovieStorage(tempDir.resolve("comedy.json"));
        storage.save(new ComedyMovie(1, "The Grand Budapest Hotel"));

        ComedyMovie found  = storage.findById(1);
        assertEquals(1, found.getId());
        assertEquals("The Grand Budapest Hotel", found.getTitle());
    }

    @Test
    void duplicateIdIsIgnored() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(tempDir.resolve("comedy.json"));
        storage.save(new ComedyMovie(1, "The Grand Budapest Hotel"));
        storage.save(new ComedyMovie(1, "Mean Girls"));

        assertEquals(1, storage.findAll().size());
        assertEquals("The Grand Budapest Hotel", storage.findAll().get(0).getTitle());
    }

    @Test
    void emptyWhenNothingSaved() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(tempDir.resolve("comedy.json"));
        assertTrue(storage.findAll().isEmpty());
    }

    @Test
    void missingIdReturnsNull() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(tempDir.resolve("comedy.json"));
        storage.save(new ComedyMovie(1, "The Grand Budapest Hotel"));
        assertNull(storage.findById(2));
    }

}
