package com.huyunit.dn.datastructure.lesson2;

/**
 * author: bobo
 * create time: 2018/12/11 2:08 PM
 * email: jqbo84@163.com
 */
public class Mahjong {
    public int suit;// 花色 筒 万 索
    public int rank;// 点数，一 二 三

    public Mahjong(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "("+this.suit+" "+this.rank+")";
    }
}
