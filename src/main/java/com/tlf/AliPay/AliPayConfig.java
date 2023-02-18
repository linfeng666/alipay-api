package com.tlf.AliPay;

public class AliPayConfig {
    // 公共参数
    private final String FORMAT = "json";
    private final String CHARSET = "UTF-8";
    private final String SIGN_TYPE = "RSA2";

    // sandbox 沙箱测试
    private String TEST_SERVERURL;
    private String TEST_APP_ID;
    private String TEST_APP_PRIVATE_KEY;
    private String TEST_APP_PUBLIC_KEY;
    private String TEST_ALIPAY_PUBLIC_KEY;
    private String TEST_AES;
    private String TEST_RETURN_URL;
    private String TEST_NOTIFY_URL;

    // 正式环境
    /**
     * 支付宝网关地址
     */
    private String SERVERURL;

    /**
     * appId
     */
    private String APP_ID;

    /**
     * 应用私钥
     */
    private String APP_PRIVATE_KEY;

    /**
     * 应用公钥
     */
    private String APP_PUBLIC_KEY;

    /**
     * 支付宝公钥
     */
    private String ALIPAY_PUBLIC_KEY;

    /**
     * 加密方式
     */
    private String AES;

    /**
     * 跳转地址
     */
    private String RETURN_URL;

    /**
     * 回调通知地址
     */
    private String NOTIFY_URL;


    public AliPayConfig(
        boolean isSandBox,
        String SERVERURL,
        String APP_ID,
        String APP_PRIVATE_KEY,
        String APP_PUBLIC_KEY,
        String ALIPAY_PUBLIC_KEY,
        String AES,
        String RETURN_URL,
        String NOTIFY_URL
    ) {
        if (isSandBox) {
            this.SERVERURL = SERVERURL;
            this.APP_ID = APP_ID;
            this.APP_PRIVATE_KEY = APP_PRIVATE_KEY;
            this.APP_PUBLIC_KEY = APP_PUBLIC_KEY;
            this.ALIPAY_PUBLIC_KEY = ALIPAY_PUBLIC_KEY;
            this.AES = AES;
            this.RETURN_URL = RETURN_URL;
            this.NOTIFY_URL = NOTIFY_URL;
        } else {
            this.TEST_SERVERURL = SERVERURL;
            this.TEST_APP_ID = APP_ID;
            this.TEST_APP_PRIVATE_KEY = APP_PRIVATE_KEY;
            this.TEST_APP_PUBLIC_KEY = APP_PUBLIC_KEY;
            this.TEST_ALIPAY_PUBLIC_KEY = ALIPAY_PUBLIC_KEY;
            this.TEST_AES = AES;
            this.TEST_RETURN_URL = RETURN_URL;
            this.TEST_NOTIFY_URL = NOTIFY_URL;
        }
    }


    public String getFORMAT() {
        return FORMAT;
    }

    public String getCHARSET() {
        return CHARSET;
    }

    public String getSIGN_TYPE() {
        return SIGN_TYPE;
    }

    public String getTEST_SERVERURL() {
        return TEST_SERVERURL;
    }

    public void setTEST_SERVERURL(String TEST_SERVERURL) {
        this.TEST_SERVERURL = TEST_SERVERURL;
    }

    public String getTEST_APP_ID() {
        return TEST_APP_ID;
    }

    public void setTEST_APP_ID(String TEST_APP_ID) {
        this.TEST_APP_ID = TEST_APP_ID;
    }

    public String getTEST_APP_PRIVATE_KEY() {
        return TEST_APP_PRIVATE_KEY;
    }

    public void setTEST_APP_PRIVATE_KEY(String TEST_APP_PRIVATE_KEY) {
        this.TEST_APP_PRIVATE_KEY = TEST_APP_PRIVATE_KEY;
    }

    public String getTEST_APP_PUBLIC_KEY() {
        return TEST_APP_PUBLIC_KEY;
    }

    public void setTEST_APP_PUBLIC_KEY(String TEST_APP_PUBLIC_KEY) {
        this.TEST_APP_PUBLIC_KEY = TEST_APP_PUBLIC_KEY;
    }

    public String getTEST_ALIPAY_PUBLIC_KEY() {
        return TEST_ALIPAY_PUBLIC_KEY;
    }

    public void setTEST_ALIPAY_PUBLIC_KEY(String TEST_ALIPAY_PUBLIC_KEY) {
        this.TEST_ALIPAY_PUBLIC_KEY = TEST_ALIPAY_PUBLIC_KEY;
    }

    public String getTEST_AES() {
        return TEST_AES;
    }

    public void setTEST_AES(String TEST_AES) {
        this.TEST_AES = TEST_AES;
    }

    public String getTEST_RETURN_URL() {
        return TEST_RETURN_URL;
    }

    public void setTEST_RETURN_URL(String TEST_RETURN_URL) {
        this.TEST_RETURN_URL = TEST_RETURN_URL;
    }

    public String getTEST_NOTIFY_URL() {
        return TEST_NOTIFY_URL;
    }

    public void setTEST_NOTIFY_URL(String TEST_NOTIFY_URL) {
        this.TEST_NOTIFY_URL = TEST_NOTIFY_URL;
    }

    public String getSERVERURL() {
        return SERVERURL;
    }

    public void setSERVERURL(String SERVERURL) {
        this.SERVERURL = SERVERURL;
    }

    public String getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(String APP_ID) {
        this.APP_ID = APP_ID;
    }

    public String getAPP_PRIVATE_KEY() {
        return APP_PRIVATE_KEY;
    }

    public void setAPP_PRIVATE_KEY(String APP_PRIVATE_KEY) {
        this.APP_PRIVATE_KEY = APP_PRIVATE_KEY;
    }

    public String getAPP_PUBLIC_KEY() {
        return APP_PUBLIC_KEY;
    }

    public void setAPP_PUBLIC_KEY(String APP_PUBLIC_KEY) {
        this.APP_PUBLIC_KEY = APP_PUBLIC_KEY;
    }

    public String getALIPAY_PUBLIC_KEY() {
        return ALIPAY_PUBLIC_KEY;
    }

    public void setALIPAY_PUBLIC_KEY(String ALIPAY_PUBLIC_KEY) {
        this.ALIPAY_PUBLIC_KEY = ALIPAY_PUBLIC_KEY;
    }

    public String getAES() {
        return AES;
    }

    public void setAES(String AES) {
        this.AES = AES;
    }

    public String getRETURN_URL() {
        return RETURN_URL;
    }

    public void setRETURN_URL(String RETURN_URL) {
        this.RETURN_URL = RETURN_URL;
    }

    public String getNOTIFY_URL() {
        return NOTIFY_URL;
    }

    public void setNOTIFY_URL(String NOTIFY_URL) {
        this.NOTIFY_URL = NOTIFY_URL;
    }
}
