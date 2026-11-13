
package edu.course.lab04;

public final class ChainedHashTable {

    private static final int DEFAULT_CAPACITY = 16;

    private final Entry[] buckets;
    private int size;

    private static final class Entry {

        private final IssueId key;
        private ProjectTask value;
        private Entry next;

        private Entry(
                IssueId key,
                ProjectTask value,
                Entry next) {

            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public ChainedHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public ChainedHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                "Размер должен быть положительным"
            );
        }

        buckets = new Entry[capacity];
    }

    private int index(IssueId key) {
        return Math.floorMod(
            key.hashCode(), buckets.length
        );
    }

    public ProjectTask put(IssueId key, ProjectTask value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException(
                "Ключ и значение обязательны"
            );
        }

        int index = index(key);

        for (Entry entry = buckets[index];
                entry != null;
                entry = entry.next) {

            if (entry.key.equals(key)) {
                ProjectTask old = entry.value;
                entry.value = value;
                return old;
            }
        }

        buckets[index] =
                new Entry(key, value, buckets[index]);

        size++;
        return null;
    }

    public ProjectTask get(IssueId key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "Ключ не может быть null"
            );
        }

        for (Entry entry = buckets[index(key)];
                entry != null;
                entry = entry.next) {

            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean containsKey(IssueId key) {
        return get(key) != null;
    }

    public ProjectTask remove(IssueId key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "Ключ не может быть null"
            );
        }

        int index = index(key);

        Entry previous = null;
        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {

                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return current.value;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    public int size() {
        return size;
    }
}
