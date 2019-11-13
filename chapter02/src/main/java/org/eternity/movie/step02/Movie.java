package org.eternity.movie.step02;

import org.eternity.money.Money;
import org.eternity.movie.step02.constant.ShowType;
import org.eternity.movie.step02.pricing.NoneDiscountPolicy;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime; //상영시간
    private Money fee;
    private DiscountPolicy discountPolicy;
    private ShowType showType; //IMAX, 2D..
    private Duration runningTimeAD; //광고시간
    private double taxRatio; //부가세 비율

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy, MovieAddInfo movieAddInfo) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy, MovieAddInfo movieAddInfo, Duration runningTimeAD) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
        this.runningTimeAD = runningTimeAD;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening) {
        fee = fee.minus(discountPolicy.calculateDiscountAmount(screening));

        //IMAX 영화 인 경우 최종금액에서 부과세 적용
        if(showType.equals(ShowType.IMAX)) {
            fee = fee.plus(fee.times(taxRatio));
        }

        return fee;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public Duration getRunningTimeAD() {
        return runningTimeAD;
    }

    public Duration getTotalRunningTime() {
        return runningTime.plus(runningTimeAD);
    }

    public void setTaxRatio(double taxRatio) {
        this.taxRatio = taxRatio;
    }

    public static class Builder {
        private String title;
        private Money fee;
        private Duration runningTime;
        private Duration runningTimeAD;

        private DiscountPolicy discountPolicy = new NoneDiscountPolicy();
        private ShowType showType = ShowType.TWO_DIMENSION;
        private double taxRatio = 0.1; //기본 부가세 10%

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withFee(Money fee) {
            this.fee = fee;
            return this;
        }

        public Builder withRunningTime(Duration runningTime) {
            this.runningTime = runningTime;
            return this;
        }

        public Builder withRunningTimeAD(Duration runningTimeAD) {
            this.runningTimeAD = runningTimeAD;
            return this;
        }

        public Builder withDiscountPolicy(DiscountPolicy discountPolicy) {
            this.discountPolicy = discountPolicy;
            return this;
        }

        public Builder withShowType(ShowType showType) {
            this.showType = showType;
            return this;
        }

        public Builder withTaxRatio(double taxRatio) {
            this.taxRatio = taxRatio;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    private Movie(Builder builder) {
        title = builder.title;
        fee = builder.fee;
        runningTime = builder.runningTime;
        runningTimeAD = builder.runningTimeAD;
        discountPolicy = builder.discountPolicy;
        showType = builder.showType;
        taxRatio = builder.taxRatio;
    }
}

