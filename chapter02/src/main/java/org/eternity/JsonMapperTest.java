package org.eternity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eternity.money.Money;
import org.eternity.movie.step02.DiscountCondition;
import org.eternity.movie.step02.DiscountPolicy;
import org.eternity.movie.step02.Movie;
import org.eternity.movie.step02.pricing.PercentDiscountPolicy;
import org.eternity.movie.step02.pricing.SequenceCondition;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.*;

/**
 * @author : hy.jun
 * @date : 2019-11-13
 * @description :
 */
public class JsonMapperTest {

    @Test
    public void test() throws JsonProcessingException {

        Money originalMoney = Money.wons(10000); //정가

        LocalDateTime startDt = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 20, 55); //기간할인 시작일시
        LocalDateTime endDt = LocalDateTime.of(2016, Month.NOVEMBER, 9, 10, 21, 56); //기간할인 종료일시
        Duration duration = Duration.between(startDt, endDt);

        DiscountCondition discountCondition = new SequenceCondition(1);
        DiscountPolicy discountPolicy = new PercentDiscountPolicy(10, discountCondition); //퍼센트 할인정책 10프로 적용

        //영화정보 생성
        Movie movie = new Movie("배트맨 2D", duration, originalMoney, discountPolicy, null, duration);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(movie);
        assertThat(jsonString, (containsString("tax")));
        assertThat(jsonString, (containsString("totalRunningTime")));
        assertThat(jsonString, (containsString("movieAddInfo")));
    }
}
