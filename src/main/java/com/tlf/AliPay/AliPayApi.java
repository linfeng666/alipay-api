package com.tlf.AliPay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AliPayApi {
    private static final Logger log = LoggerFactory.getLogger(AliPayApi.class);

    private static AlipayClient alipayClient = null;
    private static AlipayClient sandboxAalipayClient = null;

    private static void init(
        boolean isSandBox,
        AliPayConfig aliPayConfig
    ) {
        if (isSandBox) {
            sandboxAalipayClient = new DefaultAlipayClient(
                aliPayConfig.getTEST_SERVERURL(),
                aliPayConfig.getTEST_APP_ID(),
                aliPayConfig.getTEST_APP_PRIVATE_KEY(),
                aliPayConfig.getFORMAT(),
                aliPayConfig.getCHARSET(),
                aliPayConfig.getTEST_ALIPAY_PUBLIC_KEY(),
                aliPayConfig.getSIGN_TYPE()
            );
        } else {
            alipayClient = new DefaultAlipayClient(
                aliPayConfig.getSERVERURL(),
                aliPayConfig.getAPP_ID(),
                aliPayConfig.getAPP_PRIVATE_KEY(),
                aliPayConfig.getFORMAT(),
                aliPayConfig.getCHARSET(),
                aliPayConfig.getALIPAY_PUBLIC_KEY(),
                aliPayConfig.getSIGN_TYPE()
            );
        }
    }


    /**
     * ??????????????????????????????????????????
     * https://opendocs.alipay.com/open/270/105899
     * alipay.trade.page.pay(???????????????????????????????????????)
     *
     * @param out_trade_no
     * @param money
     * @param goodTitle
     * @param time_expire
     * @return
     */
    public static String pcQrCodeForm(
        String out_trade_no,
        BigDecimal money,
        String goodTitle,
        String time_expire,
        boolean isSandBox,
        AliPayConfig aliPayConfig
    ) {
        init(isSandBox, aliPayConfig);
        // ??????API?????????request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        if (isSandBox) {
            // ?????????????????????????????????????????????????????????
            alipayRequest.setReturnUrl(aliPayConfig.getTEST_RETURN_URL());
            // ??????????????????
            alipayRequest.setNotifyUrl(aliPayConfig.getTEST_NOTIFY_URL());
        } else {
            // ?????????????????????????????????????????????????????????
            alipayRequest.setReturnUrl(aliPayConfig.getRETURN_URL());
            // ??????????????????
            alipayRequest.setNotifyUrl(aliPayConfig.getNOTIFY_URL());
        }
        JSONObject params = new JSONObject();
        // ????????????
        params.put("out_trade_no", out_trade_no);
        // ??????????????????????????????
        params.put("total_amount", money);
        params.put("subject", goodTitle);
        params.put("product_code", "FAST_INSTANT_TRADE_PAY");

        // ?????????
        params.put("qr_pay_mode", 2);
        params.put("qrcode_width", 200);
        // ????????????????????????????????????yyyy-MM-dd HH:mm:ss???
        params.put("time_expire", time_expire);
        alipayRequest.setBizContent(params.toString());

        String form = null;
        try {
            AlipayTradePagePayResponse alipayResponse = null;
            if (isSandBox) {
                alipayResponse = sandboxAalipayClient.pageExecute(alipayRequest);
            } else {
                alipayResponse = alipayClient.pageExecute(alipayRequest);
            }
            if (alipayResponse.isSuccess()) {
                log.info("????????? >>> ?????????????????? >>> ????????????! form = {}", alipayResponse.getBody());
                form = alipayResponse.getBody();
            } else {
                log.info("????????? >>> ?????????????????? >>> ????????????! body = {}", alipayResponse.getBody());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.info("????????? >>> ?????????????????? >>> ????????????");
        }
        return form;
    }


    /**
     * ???????????????????????????
     * https://opendocs.alipay.com/open/02ekfg?scene=19
     * alipay.trade.precreate(?????????????????????????????????)
     *
     * @param out_trade_no
     * @param money
     * @param goodTitle
     * @param isSandBox
     * @return
     */
    public static String qrCode(
        String out_trade_no,
        BigDecimal money,
        String goodTitle,
        String time_expire,
        boolean isSandBox,
        AliPayConfig aliPayConfig
    ) {
        init(isSandBox, aliPayConfig);
        money = money.setScale(2, BigDecimal.ROUND_UP);
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
        if (isSandBox) {
            // ?????????????????????????????????????????????????????????
            alipayRequest.setReturnUrl(aliPayConfig.getTEST_RETURN_URL());
            // ??????????????????
            alipayRequest.setNotifyUrl(aliPayConfig.getTEST_NOTIFY_URL());
        } else {
            // ?????????????????????????????????????????????????????????
            alipayRequest.setReturnUrl(aliPayConfig.getRETURN_URL());
            // ??????????????????
            alipayRequest.setNotifyUrl(aliPayConfig.getNOTIFY_URL());
        }
        JSONObject params = new JSONObject();
        params.put("out_trade_no", out_trade_no);
        // ??????????????????????????????
        params.put("total_amount", money);
        params.put("subject", goodTitle);
        params.put("time_expire", time_expire);

        alipayRequest.setBizContent(params.toString());
        String qr_code = null;
        try {
            AlipayTradePrecreateResponse alipayResponse = null;
            if (isSandBox) {
                alipayResponse = sandboxAalipayClient.execute(alipayRequest);
            } else {
                alipayResponse = alipayClient.execute(alipayRequest);
            }
            if (alipayResponse.isSuccess()) {
                log.info("????????? >>> ??????????????????????????? >>> ????????????! qr_code = {}", alipayResponse.getQrCode());
                qr_code = alipayResponse.getQrCode();
            } else {
                log.error("????????? >>> ??????????????????????????? >>> ????????????! sub_code = {}, sub_msg = {}", alipayResponse.getSubCode(), alipayResponse.getSubMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error("????????? >>> ??????????????????????????? >>> ????????????", e);
        }
        return qr_code;
    }


    /**
     * ????????????????????????
     *
     * @param out_trade_no
     * @param isSandBox
     * @return
     */
    public static boolean close(
        String out_trade_no,
        boolean isSandBox,
        AliPayConfig aliPayConfig
    ) {
        init(isSandBox, aliPayConfig);
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        if (isSandBox) {
            // ?????????????????????????????????????????????????????????
            alipayRequest.setReturnUrl(aliPayConfig.getTEST_RETURN_URL());
            // ??????????????????
            alipayRequest.setNotifyUrl(aliPayConfig.getTEST_NOTIFY_URL());
        } else {
            // ?????????????????????????????????????????????????????????
            alipayRequest.setReturnUrl(aliPayConfig.getRETURN_URL());
            // ??????????????????
            alipayRequest.setNotifyUrl(aliPayConfig.getNOTIFY_URL());
        }
        JSONObject params = new JSONObject();
        params.put("out_trade_no", out_trade_no);

        alipayRequest.setBizContent(params.toString());
        try {
            AlipayTradeCloseResponse alipayResponse = null;
            if (isSandBox) {
                alipayResponse = sandboxAalipayClient.execute(alipayRequest);
            } else {
                alipayResponse = alipayClient.execute(alipayRequest);
            }
            if (alipayResponse.isSuccess()) {
                log.info("????????? >>> ??????????????????????????? >>> ????????????! OutTradeNo = {}", alipayResponse.getOutTradeNo());
            } else {
                log.error("????????? >>> ??????????????????????????? >>> ????????????! sub_code = {}, sub_msg = {}", alipayResponse.getSubCode(), alipayResponse.getSubMsg());
                return false;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error("????????? >>> ??????????????????????????? >>> ???????????????", e);
            return false;
        }
        return true;
    }


    /**
     * ????????????
     *
     * @param out_trade_no
     * @param isSandBox
     * @return
     */
    public static boolean query(
        String out_trade_no,
        boolean isSandBox,
        AliPayConfig aliPayConfig
    ) {
        init(isSandBox, aliPayConfig);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject params = new JSONObject();
        params.put("out_trade_no", out_trade_no);

        request.setBizContent(params.toString());
        try {
            AlipayTradeQueryResponse alipayResponse = null;
            if (isSandBox) {
                alipayResponse = sandboxAalipayClient.execute(request);
            } else {
                alipayResponse = alipayClient.execute(request);
            }
            if (alipayResponse.isSuccess()) {
                log.info("????????? >>> ??????????????????????????? >>> ????????????! OutTradeNo = {}", alipayResponse.getOutTradeNo());
            } else {
                log.error("????????? >>> ??????????????????????????? >>> ????????????! sub_code = {}, sub_msg = {}", alipayResponse.getSubCode(), alipayResponse.getSubMsg());
                return false;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error("????????? >>> ??????????????????????????? >>> ???????????????", e);
            return false;
        }
        return true;
    }


    /**
     * ?????????????????????????????????Map
     *
     * @param request
     * @return
     */
    public static Map<String, String> transferCallBackResultToMap(
        HttpServletRequest request
    ) {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                    : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

}
