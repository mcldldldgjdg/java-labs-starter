
package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class IdentifierTest {

    @Test
    void testEqualIdentifiers() {
        Identifier first = new Identifier("TASK-001");
        Identifier second = new Identifier("TASK-001");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void testInvalidIdentifier() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Identifier("")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new Identifier(null)
        );
    }

    @Test
    void testDifferentIdentifiers() {
        Identifier first = new Identifier("TASK-001");
        Identifier second = new Identifier("TASK-002");

        assertNotEquals(first, second);
    }
}
