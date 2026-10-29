package edu.course.lab03;

public final class NoDiscount implements DiscountPolicy {

    @Override
    public double apply(double price) {
        if (!Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException(
                "Цена должна быть неотрицательным конечным числом"
            );
        }

        return price;
    }
}