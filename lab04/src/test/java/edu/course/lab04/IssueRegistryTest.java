
package edu.course.lab04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IssueRegistryTest {

    private ProjectTask task(String id) {
        return new ProjectTask(
            id, "Тестовая задача", TaskStatus.TODO, 5
        );
    }

    @Test
    void equivalentKeysFindSameTask() {
        IssueRegistry registry = new IssueRegistry();

        IssueId first = new IssueId("APP", 1);
        IssueId second = new IssueId("APP", 1);

        ProjectTask value = task("T-1");

        registry.addOrReplace(first, value);

        assertSame(value, registry.get(second));
        assertTrue(registry.contains(second));
        assertEquals(1, registry.size());
    }

    @Test
    void equalKeyReplacesExistingValue() {
        IssueRegistry registry = new IssueRegistry();

        ProjectTask first = task("T-1");
        ProjectTask second = task("T-2");

        registry.addOrReplace(
            new IssueId("APP", 1), first
        );

        ProjectTask previous = registry.addOrReplace(
            new IssueId("APP", 1), second
        );

        assertSame(first, previous);
        assertSame(
            second, registry.get(new IssueId("APP", 1))
        );
        assertEquals(1, registry.size());
    }

    @Test
    void collisionKeepsDifferentValues() {
        IssueRegistry registry = new IssueRegistry();

        IssueId first = new IssueId("Aa", 1);
        IssueId second = new IssueId("BB", 1);

        assertNotEquals(first, second);
        assertEquals(
            first.hashCode(), second.hashCode()
        );

        ProjectTask firstTask = task("T-1");
        ProjectTask secondTask = task("T-2");

        registry.addOrReplace(first, firstTask);
        registry.addOrReplace(second, secondTask);

        assertSame(firstTask, registry.get(first));
        assertSame(secondTask, registry.get(second));
        assertEquals(2, registry.size());
    }

    @Test
    void removesExistingTask() {
        IssueRegistry registry = new IssueRegistry();

        IssueId id = new IssueId("APP", 1);
        ProjectTask value = task("T-1");

        registry.addOrReplace(id, value);

        assertSame(value, registry.remove(id));
        assertFalse(registry.contains(id));
        assertEquals(0, registry.size());
    }

    @Test
    void rejectsNullArguments() {
        IssueRegistry registry = new IssueRegistry();

        assertThrows(
            IllegalArgumentException.class,
            () -> registry.addOrReplace(null, task("T-1"))
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> registry.addOrReplace(
                new IssueId("APP", 1), null
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> registry.get(null)
        );
    }

    @Test
    void missingKeyReturnsNull() {
        IssueRegistry registry = new IssueRegistry();

        IssueId id = new IssueId("APP", 10);

        assertNull(registry.get(id));
        assertNull(registry.remove(id));
        assertFalse(registry.contains(id));
        assertEquals(0, registry.size());
    }

    @Test
    void issueIdEqualityContract() {
        IssueId first = new IssueId("APP", 1);
        IssueId equal = new IssueId("APP", 1);
        IssueId different = new IssueId("APP", 2);

        assertEquals(first, equal);
        assertEquals(first.hashCode(), equal.hashCode());
        assertNotEquals(first, different);
        assertNotEquals(first, null);
        assertNotEquals(first, "APP-1");
    }

    @Test
    void rejectsInvalidModelData() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new IssueId("", 1)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new IssueId("APP", 0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new ProjectTask(
                "", "Задача", TaskStatus.TODO, 5
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new ProjectTask(
                "T-1", "Задача", TaskStatus.TODO, -1
            )
        );
    }
}
