
package edu.course.lab04;

public final class IssueId {

    private final String projectCode;
    private final int number;

    public IssueId(String projectCode, int number) {
        if (projectCode == null
                || projectCode.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Код проекта не может быть пустым"
            );
        }

        if (number <= 0) {
            throw new IllegalArgumentException(
                "Номер задачи должен быть положительным"
            );
        }

        this.projectCode = projectCode.trim();
        this.number = number;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IssueId)) {
            return false;
        }

        IssueId other = (IssueId) obj;

        return number == other.number
                && projectCode.equals(other.projectCode);
    }

    @Override
    public int hashCode() {
        return 31 * projectCode.hashCode() + number;
    }

    @Override
    public String toString() {
        return projectCode + "-" + number;
    }
}
