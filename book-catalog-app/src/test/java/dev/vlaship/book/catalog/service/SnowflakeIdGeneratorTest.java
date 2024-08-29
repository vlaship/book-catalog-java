package dev.vlaship.book.catalog.service;

import dev.vlaship.book.catalog.config.SnowflakeIdProperties;
import dev.vlaship.book.catalog.generator.SnowflakeIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SnowflakeIdGeneratorTest {

    private SnowflakeIdGenerator generator;
    private SnowflakeIdProperties properties;

    @BeforeEach
    void setUp() {
        properties = Mockito.mock(SnowflakeIdProperties.class);
        when(properties.getDatacenterId()).thenReturn(1L);
        when(properties.getWorkerId()).thenReturn(1L);
        generator = new SnowflakeIdGenerator(properties);
    }

    @Test
    void nextId_WhenTimestampLessThanLastTimestamp_ThrowsException() {
        generator.nextId(); // to set lastTimestamp

        // Use ReflectionTestUtils to set lastTimestamp to a value greater than the current timestamp
        long lastTimestamp = getEpoch("lastTimestamp");
        ReflectionTestUtils.setField(generator, "lastTimestamp", lastTimestamp + 1);

        assertThrows(RuntimeException.class, () -> generator.nextId());
    }

    @Test
    void nextId_WhenLastTimestampEqualsCurrentTimestamp_IncrementsSequence() {
        long id1 = generator.nextId();
        long id2 = generator.nextId();
        assertNotEquals(id1, id2);
    }

    @Test
    void nextId_WhenLastTimestampNotEqualsCurrentTimestamp_ResetsSequence() throws InterruptedException {
        generator.nextId(); // to set lastTimestamp
        Thread.sleep(1); // to ensure current timestamp is not equal to lastTimestamp
        long id = generator.nextId();
        assertEquals(0, ReflectionTestUtils.getField(generator, Long.class, "sequence"));
    }

    @Test
    void nextId_ReturnsCorrectId() {
        long id = generator.nextId();
        long timestampPart = (id >> 22) + getEpoch("EPOCH");
        long datacenterPart = (id >> 17) & 0x1F;
        long workerPart = (id >> 12) & 0x1F;
        long sequencePart = id & 0xFFF;

        assertEquals(System.currentTimeMillis(), timestampPart, 10);
        assertEquals(properties.getDatacenterId(), datacenterPart);
        assertEquals(properties.getWorkerId(), workerPart);
        assertTrue(sequencePart >= 0 && sequencePart < 4096);
    }

    private long getEpoch(String fieldName) {
        return (long) ReflectionTestUtils.getField(generator, Long.class, fieldName);
    }
}
