package com.tlf.AliPay;

public class AliPayTradeStatus {
    /**
     * 交易状态，枚举值
     * WAIT_BUYER_PAY:交易创建，等待买家付款。
     * TRADE_CLOSED:未付款交易超时关闭，或支付完成后全额退款。
     * TRADE_SUCCESS:交易支付成功。
     * TRADE_FINISHED:交易结束，不可退款。
     */
    public static String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    public static String TRADE_CLOSED = "TRADE_CLOSED";
    public static String TRADE_SUCCESS = "TRADE_SUCCESS";
    public static String TRADE_FINISHED = "TRADE_FINISHED";
}

