package edu.course.lab03;

public final class TaskPrioritizer {

    private final PriorityPolicy policy;

    public TaskPrioritizer(PriorityPolicy policy) {
        if (policy == null) {
            throw new IllegalArgumentException(
                "Политика приоритета не может быть null"
            );
        }

        this.policy = policy;
    }

    public ProjectTask chooseHigherPriority(
            ProjectTask first,
            ProjectTask second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                "Задачи не могут быть null"
            );
        }

        int firstPriority =
            policy.calculatePriority(first);

        int secondPriority =
            policy.calculatePriority(second);

        if (firstPriority >= secondPriority) {
            return first;
        }

        return second;
    }
}