import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.nackademin.devops24.pingurl.model.PingedURL;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


class MemoryURLRepositoryTest {

    @Test
    void testSave() {
        MemoryURLRepository repo = new MemoryURLRepository();
        repo.save("Test", "https://example.com");

        assertEquals(1, repo.getUrls().size());
    }

    @Test
    void testGetUrls() {
        MemoryURLRepository repo = new MemoryURLRepository();
        repo.save("One", "https://one.com");
        repo.save("Two", "https://two.com");

        assertEquals(2, repo.getUrls().size());
    }

    @Test
    void testUpdate() {
        MemoryURLRepository repo = new MemoryURLRepository();
        repo.save("Test", "https://example.com");

        PingedURL updated = new PingedURL()
                .setName("Test")
                .setUrl("https://updated.com");

        repo.update(updated);

        assertEquals("https://updated.com",
            repo.getUrls().iterator().next().getUrl());
    }

    @Test
    void testDelete() {
        MemoryURLRepository repo = new MemoryURLRepository();
        repo.save("Test", "https://example.com");

        repo.delete("Test");

        assertEquals(0, repo.getUrls().size());
    }
}
