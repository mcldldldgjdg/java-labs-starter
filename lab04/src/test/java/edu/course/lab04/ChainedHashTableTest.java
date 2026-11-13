
package edu.course.lab04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ChainedHashTableTest {

    private ProjectTask task(String id) {
        return new ProjectTask(
            id, "Задача", TaskStatus.TODO, 5
        );
    }

    @Test
    void storesCollidingKeysSeparately() {
        ChainedHashTable table = new ChainedHashTable(1);

        IssueId first = new IssueId("APP", 1);
        IssueId second = new IssueId("APP", 2);

        ProjectTask a = task("T-1");
        ProjectTask b = task("T-2");

        table.put(first, a);
        table.put(second, b);

        assertSame(a, table.get(first));
        assertSame(b, table.get(second));
        assertEquals(2, table.size());
    }

    @Test
    void replacesAndRemovesValues() {
        ChainedHashTable table = new ChainedHashTable();

        IssueId id = new IssueId("APP", 1);

        ProjectTask first = task("T-1");
        ProjectTask second = task("T-2");

        table.put(id, first);

        assertSame(second,
            table.put(id, second) == first
                ? table.get(id)
                : null
        );

        assertEquals(1, table.size());
        assertTrue(table.containsKey(id));

        assertSame(second, table.remove(id));
        assertFalse(table.containsKey(id));
        assertEquals(0, table.size());
    }

    @Test
    void rejectsInvalidArguments() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ChainedHashTable(0)
        );

        ChainedHashTable table = new ChainedHashTable();

        assertThrows(
            IllegalArgumentException.class,
            () -> table.put(null, task("T-1"))
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> table.get(null)
        );
    }
}
