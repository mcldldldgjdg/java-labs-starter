package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class DiscountPolicyTest {

    @Test
    void noDiscountKeepsPrice() {
        DiscountPolicy policy = new NoDiscount();

        assertEquals(100.0, policy.apply(100.0));
    }

    @Test
    void noDiscountRejectsNegativePrice() {
        DiscountPolicy policy = new NoDiscount();

        assertThrows(
            IllegalArgumentException.class,
            () -> policy.apply(-100.0)
        );
    }

    @Test
    void percentDiscountCalculatesCorrectly() {
        DiscountPolicy policy = new PercentDiscount(20);

        assertEquals(80.0, policy.apply(100.0), 0.000001);
    }

    @Test
    void percentDiscountAcceptsLimits() {
        assertEquals(
            100.0,
            new PercentDiscount(0).apply(100.0),
            0.000001
        );

        assertEquals(
            0.0,
            new PercentDiscount(100).apply(100.0),
            0.000001
        );
    }

    @Test
    void percentDiscountRejectsInvalidPercent() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new PercentDiscount(-1)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new PercentDiscount(101)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> new PercentDiscount(Double.NaN)
        );
    }

    @Test
    void percentDiscountRejectsNegativePrice() {
        DiscountPolicy policy = new PercentDiscount(20);

        assertThrows(
            IllegalArgumentException.class,
            () -> policy.apply(-50)
        );
    }

    @Test
    void calculatorSupportsDifferentPolicies() {
        PriceCalculator first = new PriceCalculator(
            new NoDiscount()
        );

        PriceCalculator second = new PriceCalculator(
            new PercentDiscount(20)
        );

        assertEquals(100.0, first.calculate(100.0));
        assertEquals(80.0, second.calculate(100.0));
    }

    @Test
    void calculatorRejectsNullPolicy() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new PriceCalculator(null)
        );
    }
}