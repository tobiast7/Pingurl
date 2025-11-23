package se.nackademin.devops24.pingurl.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.nackademin.devops24.pingurl.model.PingedURL;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class MemoryURLRepositoryTest {

    private MemoryURLRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MemoryURLRepository();
    }

    @Test
    void testSaveStoresNewUrl() {
        String name = "TestName";
        String url = "https://example.com";

        repository.save(name, url);

        Collection<PingedURL> urls = repository.getUrls();
        assertEquals(1, urls.size());
        PingedURL saved = urls.iterator().next();
        assertEquals(name, saved.getName());
        assertEquals(url, saved.getUrl());
        assertNotNull(saved.getCreatedAt());
    }

    @Test
    void testGetUrlsReturnsAllStoredUrls() {
        repository.save("Name1", "https://url1.com");
        repository.save("Name2", "https://url2.com");

        Collection<PingedURL> urls = repository.getUrls();

        assertEquals(2, urls.size());
    }

    @Test
    void testUpdateReplacesExistingEntry() {
        String name = "TestName";
        String url = "https://original.com";
        repository.save(name, url);

        PingedURL updated = new PingedURL()
                .setName(name)
                .setUrl("https://updated.com")
                .setCreatedAt(LocalDateTime.now());

        repository.update(updated);

        Collection<PingedURL> urls = repository.getUrls();
        assertEquals(1, urls.size());

        PingedURL stored = urls.iterator().next();
        assertEquals("https://updated.com", stored.getUrl());
    }

    @Test
    void testDeleteRemovesEntry() {
        repository.save("TestName", "https://example.com");
        repository.delete("TestName");

        assertTrue(repository.getUrls().isEmpty());
    }

    @Test
    void testDeleteNonExistingDoesNothing() {
        repository.save("TestName", "https://example.com");
        repository.delete("NonExistingName");

        assertEquals(1, repository.getUrls().size());
    }
}
