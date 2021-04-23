package com.huyunit.dn.datastructure.lesson1;

import android.support.annotation.NonNull;

/**
 * author: bobo
 * create time: 2018/12/11 10:28 AM
 * email: jqbo84@163.com
 */
public class Cards implements Comparable {

    private int pokerColors;//花色
    private int cardPoints;//点数

    public Cards(int pokerColors, int cardPoints) {
        this.pokerColors = pokerColors;
        this.cardPoints = cardPoints;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Cards c = (Cards) o;
        if(this.cardPoints > c.cardPoints){
            return 1;
        } else if (this.cardPoints < c.cardPoints){
            return -1;
        }
        if(this.pokerColors > c.pokerColors){
            return 1;
        } else if (this.pokerColors < c.pokerColors){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "pokerColors=" + pokerColors +
                ", cardPoints=" + cardPoints +
                '}';
    }
}
