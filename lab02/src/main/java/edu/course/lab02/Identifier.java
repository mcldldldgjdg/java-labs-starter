
package edu.course.lab02;

public final class Identifier {

    private final String value;

    public Identifier(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Идентификатор не может быть пустым"
            );
        }

        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Identifier)) {
            return false;
        }

        Identifier other = (Identifier) obj;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
