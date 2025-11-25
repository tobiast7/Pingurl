class MemoryURLRepositoryTest {

    @Test
    void testSave() {
        MemoryURLRepository repo = new MemoryURLRepository();
        repo.save("Test", "https://example.com");

        assertEquals(1, repo.getUrls().size());
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
