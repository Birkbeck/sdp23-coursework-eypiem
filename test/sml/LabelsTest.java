package sml;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabelsTest {
    private Labels labels;

    @BeforeEach
    void setUp() {
        labels = new Labels();
    }

    @AfterEach
    void tearDown() {
        labels = null;
    }

    @Test
    void executeValidGetAddress() {
        labels.addLabel("L1", 0);
        Assertions.assertEquals(0, labels.getAddress("L1"));
    }

    @Test
    void executeInvalidGetAddress() {
        Assertions.assertThrows(RuntimeException.class, () -> labels.getAddress("L1"));
    }

    @Test
    void executeInvalidGetAddressTwo() {
        Assertions.assertThrows(RuntimeException.class, () -> labels.getAddress(null));
    }

    @Test
    void executeValidToString() {
        Assertions.assertEquals("[]", labels.toString());
    }

    @Test
    void executeValidToStringTwo() {
        labels.addLabel("L1", 0);
        Assertions.assertEquals("[L1 -> 0]", labels.toString());
    }

    @Test
    void executeValidToStringThree() {
        labels.addLabel("L1", 0);
        labels.addLabel("L2", 5);
        Assertions.assertEquals("[L1 -> 0, L2 -> 5]", labels.toString());
    }

    @Test
    void executeDuplicates() {
        labels.addLabel("L1", 0);
        Assertions.assertThrows(RuntimeException.class, () -> labels.addLabel("L1", 0));
    }

    @Test
    void executeEquals() {
        Labels expected = new Labels();

        Assertions.assertEquals(expected, labels);
    }

    @Test
    void executeEqualsTwo() {
        labels.addLabel("L1", 0);

        Labels expected = new Labels();
        expected.addLabel("L1", 0);

        Assertions.assertEquals(expected, labels);
    }

    @Test
    void executeNotEquals() {
        labels.addLabel("L1", 0);

        Labels unexpected = new Labels();
        unexpected.addLabel("L1", 1);

        Assertions.assertNotEquals(unexpected, labels);
    }

    @Test
    void executeNotEqualsTwo() {
        labels.addLabel("L1", 0);

        Labels unexpected = new Labels();
        unexpected.addLabel("L2", 1);

        Assertions.assertNotEquals(unexpected, labels);
    }

    @Test
    void executeNotEqualsThree() {
        Assertions.assertNotEquals(new Object(), labels);
    }

    @Test
    void executeNotEqualsFour() {
        Assertions.assertNotEquals(null, labels);
    }
}
