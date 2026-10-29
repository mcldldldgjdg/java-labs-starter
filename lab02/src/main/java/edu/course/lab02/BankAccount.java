package edu.course.lab02;

public final class BankAccount {

    private int balance;

    public BankAccount(int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                "Начальный баланс не может быть отрицательным"
            );
        }

        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Сумма пополнения должна быть положительной"
            );
        }

        if (amount > Integer.MAX_VALUE - balance) {
            throw new IllegalArgumentException(
                "Переполнение баланса"
            );
        }

        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Сумма снятия должна быть положительной"
            );
        }

        if (amount > balance) {
            throw new IllegalArgumentException(
                "Недостаточно средств"
            );
        }

        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}

