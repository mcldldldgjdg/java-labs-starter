package edu.course.lab03;

public final class PriceCalculator {

    private final DiscountPolicy policy;

    public PriceCalculator(DiscountPolicy policy) {
        if (policy == null) {
            throw new IllegalArgumentException(
                "Политика скидок не может быть null"
            );
        }

        this.policy = policy;
    }

    public double calculate(double price) {
        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException(
                "Некорректная цена"
            );
        }

        return policy.apply(price);
    }
}