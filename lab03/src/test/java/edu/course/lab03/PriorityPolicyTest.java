package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PriorityPolicyTest {

    private ProjectTask task(
            String id, TaskStatus status, int hours) {
        return new ProjectTask(
            id, "Тестовая задача", status, hours
        );
    }

    @Test
    void projectTaskRejectsInvalidData() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new ProjectTask(
                "", "Задача", TaskStatus.TODO, 5
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new ProjectTask(
                "1", "", TaskStatus.TODO, 5
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new ProjectTask(
                "1", "Задача", null, 5
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new ProjectTask(
                "1", "Задача", TaskStatus.TODO, -5
            )
        );
    }

    @Test
    void effortPolicyPrefersShortTasks() {
        PriorityPolicy policy = new EffortPriorityPolicy();

        ProjectTask shortTask = task("1", TaskStatus.TODO, 2);
        ProjectTask longTask = task("2", TaskStatus.TODO, 10);

        assertTrue(
            policy.calculatePriority(shortTask)
            > policy.calculatePriority(longTask)
        );
    }

    @Test
    void effortPolicyRejectsNull() {
        PriorityPolicy policy = new EffortPriorityPolicy();

        assertThrows(
            IllegalArgumentException.class,
            () -> policy.calculatePriority(null)
        );
    }

    @Test
    void statusPolicyPrefersInProgress() {
        PriorityPolicy policy = new StatusPriorityPolicy();

        ProjectTask doing = task(
            "1", TaskStatus.IN_PROGRESS, 10
        );

        ProjectTask todo = task(
            "2", TaskStatus.TODO, 2
        );

        ProjectTask done = task(
            "3", TaskStatus.DONE, 1
        );

        assertTrue(
            policy.calculatePriority(doing)
            > policy.calculatePriority(todo)
        );

        assertTrue(
            policy.calculatePriority(todo)
            > policy.calculatePriority(done)
        );
    }

    @Test
    void statusPolicyRejectsNull() {
        PriorityPolicy policy = new StatusPriorityPolicy();

        assertThrows(
            IllegalArgumentException.class,
            () -> policy.calculatePriority(null)
        );
    }

    @Test
    void prioritizerSupportsPolicyReplacement() {
        ProjectTask shortTask = task(
            "1", TaskStatus.TODO, 2
        );

        ProjectTask activeTask = task(
            "2", TaskStatus.IN_PROGRESS, 10
        );

        TaskPrioritizer byEffort = new TaskPrioritizer(
            new EffortPriorityPolicy()
        );

        TaskPrioritizer byStatus = new TaskPrioritizer(
            new StatusPriorityPolicy()
        );

        assertSame(
            shortTask,
            byEffort.chooseHigherPriority(
                shortTask, activeTask
            )
        );

        assertSame(
            activeTask,
            byStatus.chooseHigherPriority(
                shortTask, activeTask
            )
        );
    }

    @Test
    void prioritizerKeepsFirstTaskOnTie() {
        ProjectTask first = task(
            "1", TaskStatus.TODO, 5
        );

        ProjectTask second = task(
            "2", TaskStatus.TODO, 5
        );

        TaskPrioritizer prioritizer = new TaskPrioritizer(
            new EffortPriorityPolicy()
        );

        assertSame(
            first,
            prioritizer.chooseHigherPriority(first, second)
        );
    }

    @Test
    void prioritizerRejectsNullInputs() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new TaskPrioritizer(null)
        );

        TaskPrioritizer prioritizer = new TaskPrioritizer(
            new EffortPriorityPolicy()
        );

        ProjectTask task = task(
            "1", TaskStatus.TODO, 5
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> prioritizer.chooseHigherPriority(null, task)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> prioritizer.chooseHigherPriority(task, null)
        );
    }

    @Test
    void largeEffortPolicyPrefersLongTasks() {
        PriorityPolicy policy = new LargeEffortPriorityPolicy();

        ProjectTask shortTask = task(
            "1", TaskStatus.TODO, 2
        );

        ProjectTask longTask = task(
            "2", TaskStatus.TODO, 10
        );

        assertTrue(
            policy.calculatePriority(longTask)
            > policy.calculatePriority(shortTask)
        );
    }

    @Test
    void largeEffortPolicyWorksWithPrioritizer() {
        ProjectTask first = task(
            "1", TaskStatus.TODO, 3
        );

        ProjectTask second = task(
            "2", TaskStatus.TODO, 15
        );

        TaskPrioritizer prioritizer = new TaskPrioritizer(
            new LargeEffortPriorityPolicy()
        );

        assertSame(
            second,
            prioritizer.chooseHigherPriority(first, second)
        );
    }
}