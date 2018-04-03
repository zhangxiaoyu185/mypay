package com.xiaoyu.lingdian.entity;

import com.google.common.collect.ImmutableMap;

@SuppressWarnings("serial")
public class PayConstant extends BaseEntity {
	
	/**
     * charset (UTF-8)
     */
	public static final String CHARSET_UTF8 = "UTF-8";
    /**
     * charset (GBK)
     */
    public static final String CHARSET_GBK = "GBK";
    /**
     * http post请求
     */
    public static final String HTTP_METHOD_POST = "POST";
    /**
     * http get请求
     */
    public static final String HTTP_METHOD_GET = "GET";
	/**
	 * http 请求设置连接超时（3秒）
	 */
	public static final int CONNECTIONTIMEOUT = 3000;//3秒
	/**
	 * http 请求设置读超时（15秒）
	 */
    public static final int READTIMEOUT = 15000;//15秒
	/**
	 * 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	 */
	public static final String WX_PAY_DEVICE_INFO = "WEB";
	/**
	 * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	public static final String WX_PAY_FEE_TYPE = "CNY";
	/**
	 * 支付方式：JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
	 */
	public static final String WX_PAY_TRADE_TYPE_JSAPI = "JSAPI";
	/**
	 * 支付方式：JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
	 */
	public static final String WX_PAY_TRADE_TYPE_NATIVE = "NATIVE";
	/**
	 * 微信支付随机码长度：32
	 */
	public static final int WX_PAY_NONCE_STR_LENGTH = 32;
	/**
	 * 微信交互成功返回return_code :SUCCESS
	 */
	public static final String WX_PAY_RETURN_CODE_SUCCESS = "SUCCESS";
	/**
	 * 微信交互成功返回return_msg :OK
	 */
	public static final String WX_PAY_RETURN_MSG_OK = "OK";
	/**
	 * 存放微信统一支付返回数据的redis key
	 */
	public static final String WX_PAY_PREFIX = "wx_order_pay:";
	/**
	 * 存放微信统一支付返回的code_url的redis hkey
	 */
	public static final String WX_PAY_PREPAY_ID = "prepay_id";
	/**
	 * 存放微信统一支付返回的交易会话标识的redis hkey
	 */
	public static final String WX_PAY_CODE_URL = "code_url";
	/**
	 * 存放微信统一支付返回数据redis key 过期时间90分钟
	 */
	public static final long WX_PAY_EXPIRE = 90 * 60;
	/**
	 * 微信支付签名方式:MD5
	 */
	public static final String WX_PAY_SIGN = "MD5";
	/**
	 * 用于JSAPI微信支付package参数前缀
	 */
	public static final String WX_PAY_JSAPI_PACKAGE_PREFIX = "prepay_id=";
	/**
	 * 支付宝验证notify_id 返回true
	 */
	public static final String ALIPAY_NOTIFY_VERIFY_TRUE = "true";
	/**
	 * 支付宝PC即时支付:PC
	 */
	public static final String ALIPAY_PC_DIRECT = "PC";
	/**
	 * 支付宝手机网站支付:WAP
	 */
	public static final String ALIPAY_WAP_DIRECT = "WAP";
	/**
	 * 支付宝移动支付:APP
	 */
	public static final String ALIPAY_APP_DIRECT = "APP";
	/**
	 * 支付宝PC端即时支付接口接口名称
	 */
	public static final String ALIPAY_DIRECT_SERVICE = "create_direct_pay_by_user";
	/**
	 * 支付宝手机网站支付接口接口名称
	 */
	public static final String ALIPAY_WAP_DIRECT_SERVICE = "alipay.wap.create.direct.pay.by.user";
	/**
	 * 支付宝移动支付接口接口名称
	 */
	public static final String ALIPAY_APP_DIRECT_SERVICE = "mobile.securitypay.pay";
	/**
	 * 支付宝退款接口接口名称
	 */
	public static final String ALIPAY_REFUND_SERVICE = "refund_fastpay_by_platform_pwd";
	/**
	 * 支付宝清关接口接口名称
	 */
	public static final String ALIPAY_CUSTOMS_SERVICE = "alipay.acquire.customs";
	/**
	 * 支付宝支付类型:1商品购买
	 */
	public static final String ALIPAY_PAYMENT_TYPE_1 = "1";
	/**
	 * 请求方式：get
	 */
	public static final String METHOD_GET = "get";
	/**
	 * 请求方式：post
	 */
	public static final String METHOD_POST = "post";
	/**
	 * 支付宝form提交按钮名称
	 */
	public static final String SUBMIT_BT_NAME = "确认";
	/**
	 * 验证是否是支付宝发来的通知接口
	 */
	public static final String ALIPAY_NOTIFY_VERIFY = "notify_verify";
	/**
	 * 支付宝支付签名方式:MD5
	 */
	public static final String ALI_PAY_SIGN_TYPE = "MD5";
	/**
	 * 支付宝支付签名方式:RSA
	 */
	public static final String ALI_PAY_SIGN_TYPE_RSA = "RSA";
	/**
	 * 最大支付超时时间
	 */
	public static final int PAY_DEFAULT_TIME_OUT = 120;
	/**
	 * 最小支付超时时间
	 */
	public static final int PAY_MIN_TIME_OUT = 5;
	/**
	 * 最大通知次数
	 */
	public static final int MAX_NOTIFY_TIMES = 9;
	/**
	 * 最大查询次数
	 */
	public static final int MAX_QUERY_TIMES = 9;
	
	/**
	 * 退款成功后接受短信通知
	 */
	public static final String REFUND_MSG = "尊敬的会员：您的订单（#orderNo#）已退款成功，退款金额：#refundFee#元，实际到账日期以支付平台规定为准，请注意查收！";
	
    public static final String SUCCESS = "SUCCESS"; // 成功
    public static final String FAIL = "FAIL"; // 失败
    public static final String ERROR_0001 = "E0001"; // 缺失参数
    public static final String ERROR_0002 = "E0002"; // 系统异常
    public static final String ERROR_0003 = "E0003"; // 不支持的支付渠道
    public static final String ERROR_0004 = "E0004";//不支持的交易类型
    public static final String ERROR_0005 = "E0005";//未查询到第三方交易单号
    public static final String ERROR_0006 = "E0006";//未查询到平台单号
    public static final String ERROR_0007 = "E0007";//商户订单号已存在
    public static final String ERROR_0008 = "E0008";//平台单号已支付
    public static final String ERROR_0009 = "E0009";//校验签名错误
    public static final String ERROR_0010 = "E0010";//校验是否是支付宝发来的通知失败
    public static final String ERROR_0011 = "E0011";//重复支付
    public static final String ERROR_0012 = "E0012";//接收到的通知返回金额不是传入的金额
    public static final String ERROR_0013 = "E0013";//退款金额大于支付金额
    
    public static final ImmutableMap<String, String> ERROR_MAPS = ImmutableMap.<String,String>builder()
    		.put("E0001","缺失参数")
    		.put("E0002", "系统异常")
			.put("E0003", "不支持的支付渠道")
			.put("E0004", "不支持的交易类型")
			.put("E0005", "未查询到第三方交易单号")
			.put("E0006", "未查询到平台单号")
			.put("E0007", "商户订单号已存在")
			.put("E0008", "平台单号已支付")
			.put("E0009", "校验签名错误")
			.put("E0010", "校验是否是支付宝发来的通知失败")
			.put("E0011", "重复支付")
			.put("E0012", "通知返回的金额不是传入的金额")
			.put("E0013", "退款金额大于支付金额").build();
    	
    /**
     * (通知策略，key为重试次数，value距离上一次通知的间隔毫秒数)
     */
    public static final ImmutableMap<Integer, Integer> NOTIFY_MAPS = ImmutableMap.<Integer,Integer>builder()
    		.put(1, 15000)
			.put(2, 15000)
			.put(3, 30000)
			.put(4, 180000)
			.put(5, 1800000)
			.put(6, 1800000)
			.put(7, 1800000)
			.put(8, 1800000)
			.put(9, 3600000).build();

    /**
     * 查询微信退款订单策略，key为重试次数，value距离上一次通知的间隔毫秒数,为增量值
     */
    public static final ImmutableMap<Integer, Integer> REFUND_QUERY_MAPS = ImmutableMap.<Integer,Integer>builder()
    		.put(1, 10*60*1000)//10分钟后查询第一次
			.put(2, 20*60*1000)//30分钟后查询第二次
			.put(3, 30*60*1000)//一个小时后查询第三次
			.put(4, 1*24*60*60*1000)//一天后查询第四次
			.put(5, 1*24*60*60*1000)//两天后查询第五次
			.put(6, 1*24*60*60*1000)//三天后查询第六次
			.put(7, 2*24*60*60*1000)//五天后查询第七次
			.put(8, 2*24*60*60*1000)//七天后查询第八次
			.put(9, 3*24*60*60*1000).build();//十天后查询第九次
    
}