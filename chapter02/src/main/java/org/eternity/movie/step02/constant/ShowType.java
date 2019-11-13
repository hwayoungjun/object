package org.eternity.movie.step02.constant;

/**
 * @author : hy.jun
 * @date : 2019-11-13
 * @description :
 */
public enum ShowType {

    IMAX("아이맥스"),
    TWO_DIMENSION("2D");

    private String value;
    public String getValue() {
        return value;
    }
    ShowType(String value) {
        this.value = value;
    }
}
