
package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class BankAccountTest {

    @Test
    void withdrawDecreasesBalance() {
        BankAccount account = new BankAccount(500);

        account.withdraw(200);

        assertEquals(300, account.getBalance());
    }

    @Test
    void cannotWithdrawMoreThanBalance() {
        BankAccount account = new BankAccount(100);

        assertThrows(
            IllegalArgumentException.class,
            () -> account.withdraw(101)
        );

        assertEquals(100, account.getBalance());
    }

    @Test
    void depositIncreasesBalance() {
        BankAccount account = new BankAccount(100);

        account.deposit(30);

        assertEquals(130, account.getBalance());
    }

    @Test
    void initialBalanceCannotBeNegative() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new BankAccount(-1)
        );
    }

    @Test
    void depositMustBePositive() {
        BankAccount account = new BankAccount(50);

        assertThrows(
            IllegalArgumentException.class,
            () -> account.deposit(0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> account.deposit(-10)
        );

        assertEquals(50, account.getBalance());
    }

    @Test
    void withdrawMustBePositive() {
        BankAccount account = new BankAccount(50);

        assertThrows(
            IllegalArgumentException.class,
            () -> account.withdraw(0)
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> account.withdraw(-5)
        );

        assertEquals(50, account.getBalance());
    }

    @Test
    void cannotOverflowIntegerBalance() {
        BankAccount account =
            new BankAccount(Integer.MAX_VALUE);

        assertThrows(
            IllegalArgumentException.class,
            () -> account.deposit(1)
        );

        assertEquals(
            Integer.MAX_VALUE,
            account.getBalance()
        );
    }
}
