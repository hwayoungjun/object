package org.eternity.money;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private static final double TAX_PERCENT = 0.1;

    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    private BigDecimal tax; //부가세

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    Money(BigDecimal amount) {
        this.amount = amount;
        this.tax = this.amount.multiply(BigDecimal.valueOf(TAX_PERCENT)); //부가세 10프로
    }

    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public boolean isLessThan(Money other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Money)) {
            return false;
        }

        Money other = (Money)object;
        return Objects.equals(amount.doubleValue(), other.amount.doubleValue());
    }

    public int hashCode() {
        return Objects.hashCode(amount);
    }

    public BigDecimal getAmount() {
        return amount.setScale(0, BigDecimal.ROUND_HALF_EVEN);
    }

    public BigDecimal getTax() {
        return tax;
    }

    //부가세 포함 금액
    public BigDecimal getTotalAmount() {
        return (amount.add(tax)).setScale(0, BigDecimal.ROUND_HALF_EVEN);
    }

    public String toString() {
        return amount.toString() + "원";
    }
}
