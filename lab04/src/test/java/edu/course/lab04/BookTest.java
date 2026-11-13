
package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    void booksWithSameIsbnAreEqual() {
        Book first = new Book("12345", "Java");
        Book second = new Book("12345", "Java 2");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book first = new Book("12345", "Java");
        Book second = new Book("67890", "Java");

        assertNotEquals(first, second);
    }

    @Test
    void hashmapFindsBookByEquivalentKey() {
        Map<Book, String> books = new HashMap<>();

        Book first = new Book("12345", "Java");
        Book second = new Book("12345", "Другое название");

        books.put(first, "Книга найдена");

        assertEquals("Книга найдена", books.get(second));
    }

    @Test
    void equalsRejectsNullAndOtherType() {
        Book book = new Book("12345", "Java");

        assertNotEquals(null, book);
        assertNotEquals("12345", book);
    }

    @Test
    void rejectsInvalidBookData() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Book("", "Java")
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new Book("12345", " ")
        );
    }
}
