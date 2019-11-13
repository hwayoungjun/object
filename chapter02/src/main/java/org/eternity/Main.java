package org.eternity;

import org.eternity.money.Money;
import org.eternity.movie.step02.DefaultDiscountPolicy;
import org.eternity.movie.step02.DiscountCondition;
import org.eternity.movie.step02.Movie;
import org.eternity.movie.step02.Screening;
import org.eternity.movie.step02.pricing.AmountDiscountPolicy;
import org.eternity.movie.step02.pricing.SequenceCondition;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * @author : hy.jun
 * @date : 2019-11-13
 * @description :
 */
public class Main {

    public static void main(String[] args) {
        LocalDateTime oldDate = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 20, 55);
        LocalDateTime newDate = LocalDateTime.of(2016, Month.NOVEMBER, 9, 10, 21, 56);

        Money originalMoney = Money.wons(10000); //정가
        Money discountedMoney = Money.wons(1000); //할인금액

        DiscountCondition discountCondition = new SequenceCondition(1);

        Duration duration = Duration.between(oldDate, newDate);
        DefaultDiscountPolicy discountPolicy = new AmountDiscountPolicy(discountedMoney, discountCondition);

        Movie movie = new Movie("배트맨 2D", duration, originalMoney, discountPolicy);

        Screening screening = new Screening(movie, 1, oldDate);
        System.out.println(screening.getMovieFee().toString());
        System.out.println(movie.calculateMovieFee(screening).toString());
    }
}
