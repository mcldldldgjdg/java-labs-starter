package edu.course.lab03;

public final class ProjectTask {

    private final String id;
    private final String title;
    private final TaskStatus status;
    private final int estimatedHours;

    public ProjectTask(
            String id,
            String title,
            TaskStatus status,
            int estimatedHours) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "ID задачи не может быть пустым"
            );
        }

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Название задачи не может быть пустым"
            );
        }

        if (status == null) {
            throw new IllegalArgumentException(
                "Статус не может быть null"
            );
        }

        if (estimatedHours < 0) {
            throw new IllegalArgumentException(
                "Количество часов не может быть отрицательным"
            );
        }

        this.id = id.trim();
        this.title = title.trim();
        this.status = status;
        this.estimatedHours = estimatedHours;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }
}