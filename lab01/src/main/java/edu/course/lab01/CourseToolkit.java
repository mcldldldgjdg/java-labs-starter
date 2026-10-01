package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
    public static boolean isPrime(int number) {
    if (number < 2) {
        return false;
    }
    for (int divisor = 2; divisor * divisor <= number; divisor++) {
        if (number % divisor == 0) {
            return false;
        }
    }
    return true;
}

public static boolean isPalindrome(String text) {
    if (text == null) {
        throw new IllegalArgumentException("Text cannot be null");
    }
    int left = 0;
    int right = text.length() - 1;
    while (left < right) {
        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}

public static double average(int[] values) {
    if (values == null || values.length == 0) {
        throw new IllegalArgumentException("Array cannot be null or empty");
    }
    double sum = 0;
    for (int value : values) {
        sum += value;
    }
    return sum / values.length;
}
}
