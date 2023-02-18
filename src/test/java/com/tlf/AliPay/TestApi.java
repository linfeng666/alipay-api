package com.tlf.AliPay;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestApi {

    @Test
    public void test1() {
        String orderNumber = "23257070782382080";
        BigDecimal money = BigDecimal.valueOf(Double.parseDouble("66.66"));
        String goodTitle = "支付宝-电脑网站支付-扫码支付测试";
        String timeExpire = "2023-02-09 14:51:00";
        goodTitle = "支付宝-当面付-扫码支付测试";
        AliPayConfig aliPayConfig = new AliPayConfig(
            false,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        );
        AliPayApi.qrCode(orderNumber, money, goodTitle, timeExpire, false, aliPayConfig);
        System.out.println(orderNumber);

        AliPayApi.query("23257070782382080", false, aliPayConfig);
        AliPayApi.close("23257070782382080", false, aliPayConfig);
    }

}
