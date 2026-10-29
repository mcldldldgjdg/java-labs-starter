package edu.course.lab03;

public final class LargeEffortPriorityPolicy
        implements PriorityPolicy {

    @Override
    public int calculatePriority(ProjectTask task) {

        if (task == null) {
            throw new IllegalArgumentException(
                "Задача не может быть null"
            );
        }

        return task.getEstimatedHours();
    }
}