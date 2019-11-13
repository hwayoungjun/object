package org.eternity;

import org.eternity.money.Money;
import org.eternity.movie.step02.*;
import org.eternity.movie.step02.pricing.AmountDiscountPolicy;
import org.eternity.movie.step02.pricing.PercentDiscountPolicy;
import org.eternity.movie.step02.pricing.SequenceCondition;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author : hy.jun
 * @date : 2019-11-13
 * @description :
 */
public class ValidateBehaviorTest {

    Money originalMoney = Money.wons(10000); //정가
    Money discountedMoney = Money.wons(1000); //할인금액

    LocalDateTime startDt = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 20, 55); //기간할인 시작일시
    LocalDateTime endDt = LocalDateTime.of(2016, Month.NOVEMBER, 9, 10, 21, 56); //기간할인 종료일시
    Duration duration = Duration.between(startDt, endDt);

    @Test
    public void validAmountDiscount() {
        DiscountCondition discountCondition = new SequenceCondition(1);
        DiscountPolicy discountPolicy = new AmountDiscountPolicy(discountedMoney, discountCondition);

        //영화정보 생성
        Movie movie = new Movie("배트맨 2D", duration, originalMoney, discountPolicy);
        //상영관정보 생성
        Screening screening = new Screening(movie, 1, startDt);

        //정가 검증
        assertThat(screening.getMovieFee().getAmount(), is(BigDecimal.valueOf(10000)));
        //할인된 금액 검증
        assertThat(movie.calculateMovieFee(screening).getAmount(), is(BigDecimal.valueOf(9000)));
    }

    @Test
    public void validPercentDiscount() {
        DiscountCondition discountCondition = new SequenceCondition(1);
        DiscountPolicy discountPolicy = new PercentDiscountPolicy(10, discountCondition); //퍼센트 할인정책 10프로 적용

        //영화정보 생성
        Movie movie = new Movie("배트맨 2D", duration, originalMoney, discountPolicy);
        //상영관정보 생성
        Screening screening = new Screening(movie, 1, startDt);

        //정가 검증
        assertThat(screening.getMovieFee().getAmount(), is(BigDecimal.valueOf(10000)));
        //할인된 금액 검증
        assertThat(movie.calculateMovieFee(screening).getAmount(), is(BigDecimal.valueOf(9000)));
    }
}
