package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void isPrime_returnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void isPrime_returnsFalseForFour() {
        assertFalse(CourseToolkit.isPrime(4));
    }

    @Test
    void isPrime_returnsFalseForFortyNine() {
        assertFalse(CourseToolkit.isPrime(49));
    }

    @Test
    void isPrime_returnsFalseForOne() {
        assertFalse(CourseToolkit.isPrime(1));
    }

    @Test
    void isPalindrome_returnsTrueForLevel() {
        assertTrue(CourseToolkit.isPalindrome("level"));
    }

    @Test
    void isPalindrome_returnsFalseForLove() {
        assertFalse(CourseToolkit.isPalindrome("love"));
    }

    @Test
    void isPalindrome_throwsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void average_returnsCorrectForPositiveNumbers() {
        assertEquals(2.5, CourseToolkit.average(new int[]{1, 2, 3, 4}));
    }

    @Test
    void average_returnsCorrectForNegativeNumbers() {
        assertEquals(-2.5, CourseToolkit.average(new int[]{-1, -2, -3, -4}));
    }

    @Test
    void average_throwsForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
    }

    @Test
    void average_throwsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[]{}));
    }
}
