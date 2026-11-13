
package edu.course.lab04;

public final class Book {

    private final String isbn;
    private final String title;

    public Book(String isbn, String title) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "ISBN не может быть пустым"
            );
        }

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Название книги не может быть пустым"
            );
        }

        this.isbn = isbn.trim();
        this.title = title.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Book)) {
            return false;
        }

        Book other = (Book) obj;
        return isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return isbn + " - " + title;
    }
}
