
package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;

public final class IssueRegistry {

    private final Map<IssueId, ProjectTask> tasks =
            new HashMap<>();

    public ProjectTask addOrReplace(
            IssueId id, ProjectTask task) {

        if (id == null || task == null) {
            throw new IllegalArgumentException(
                "Ключ и задача не могут быть null"
            );
        }

        return tasks.put(id, task);
    }

    public ProjectTask get(IssueId id) {
        if (id == null) {
            throw new IllegalArgumentException(
                "Ключ не может быть null"
            );
        }

        return tasks.get(id);
    }

    public ProjectTask remove(IssueId id) {
        if (id == null) {
            throw new IllegalArgumentException(
                "Ключ не может быть null"
            );
        }

        return tasks.remove(id);
    }

    public boolean contains(IssueId id) {
        if (id == null) {
            throw new IllegalArgumentException(
                "Ключ не может быть null"
            );
        }

        return tasks.containsKey(id);
    }

    public int size() {
        return tasks.size();
    }
}
