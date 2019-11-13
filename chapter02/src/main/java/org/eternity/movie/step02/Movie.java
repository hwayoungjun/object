package org.eternity.movie.step02;

import org.eternity.money.Money;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime; //상영시간
    private Money fee;
    private DiscountPolicy discountPolicy;
    private MovieAddInfo movieAddInfo;
    private Duration runningTimeAD; //광고시간

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
        this.movieAddInfo = movieAddInfo;
    }

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy, MovieAddInfo movieAddInfo, Duration runningTimeAD) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
        this.movieAddInfo = movieAddInfo;
        this.runningTimeAD = runningTimeAD;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }

    public MovieAddInfo getMovieAddInfo() {
        return movieAddInfo;
    }

    public void setMovieAddInfo(MovieAddInfo movieAddInfo) {
        this.movieAddInfo = movieAddInfo;
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
}

