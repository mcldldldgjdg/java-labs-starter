
package edu.course.lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTaskTest {

    @Test
    void testCreateTask() {
        ProjectTask task = new ProjectTask(
            "1", "Создать сайт", TaskStatus.TODO, 10
        );

        assertEquals("1", task.getId());
        assertEquals("Создать сайт", task.getTitle());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertEquals(10, task.getEstimatedHours());
    }

    @Test
    void testChangeStatus() {
        ProjectTask task = new ProjectTask(
            "1", "Создать сайт", TaskStatus.TODO, 10
        );

        task.changeStatus(TaskStatus.DONE);

        assertTrue(task.isCompleted());
    }

    @Test
    void testIncreaseHours() {
        ProjectTask task = new ProjectTask(
            "1", "Создать сайт", TaskStatus.TODO, 10
        );

        task.increaseEstimatedHours(5);

        assertEquals(15, task.getEstimatedHours());
    }

    @Test
    void testEmptyId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ProjectTask(
                "", "Задача", TaskStatus.TODO, 5
            )
        );
    }

    @Test
    void testEmptyTitle() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ProjectTask(
                "1", " ", TaskStatus.TODO, 5
            )
        );
    }

    @Test
    void testNegativeHours() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ProjectTask(
                "1", "Задача", TaskStatus.TODO, -5
            )
        );
    }

    @Test
    void testInvalidStatus() {
        ProjectTask task = new ProjectTask(
            "1", "Задача", TaskStatus.TODO, 5
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> task.changeStatus(null)
        );

        assertEquals(TaskStatus.TODO, task.getStatus());
    }

    @Test
    void testInvalidHoursIncrease() {
        ProjectTask task = new ProjectTask(
            "1", "Задача", TaskStatus.TODO, 5
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> task.increaseEstimatedHours(-5)
        );

        assertEquals(5, task.getEstimatedHours());
    }
}
