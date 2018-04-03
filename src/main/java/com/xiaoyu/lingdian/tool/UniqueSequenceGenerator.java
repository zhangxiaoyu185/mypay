package com.xiaoyu.lingdian.tool;

import java.util.Date;

import org.springframework.data.redis.core.RedisTemplate;

import com.xiaoyu.lingdian.core.spring.adapter.ContextBeanFactory;

public class UniqueSequenceGenerator {

	private static UniqueSequenceGenerator instance = new UniqueSequenceGenerator();

	private RedisTemplate<Object, Object> redisTemplate;

	private static final String UNIQUE_SENQUECE_KEY_SUFFIX = ":unique:key";

	private static final String DEFAULT_BUSINESS_CODE = "zhp";
	
	/**
	 * 生成订单支付流水号前缀：P
	 */
	public static final String DEFAULT_TRADE_NO_CODE = "P";
	
	/**
	 * 微信授权用state生成
	 */
	public static final String DEFAULT_WX_AUTH_STATE = "WAS";
	
	private static String[] prefixString = { "00000000", "0000000", "000000", "00000", "0000", "000", "00", "0" };

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private UniqueSequenceGenerator() {
		redisTemplate = (RedisTemplate) ContextBeanFactory.getInstance().getBean("redisTemplate");
	}

	public static UniqueSequenceGenerator getInstance() {
		return instance;
	}

	public String getUniqueSequence(String businessPrefix) {
		if (StringUtil.isEmpty(businessPrefix)) {
			businessPrefix = DEFAULT_BUSINESS_CODE;
		}

		String key = businessPrefix.toLowerCase() + UNIQUE_SENQUECE_KEY_SUFFIX;
		String currentTime = DateUtil.formatDate("yyyyMMddHHmmss", new Date());
		Long index = redisTemplate.opsForValue().increment(key, 1);
		if (index == 1) {
			redisTemplate.expireAt(key, DateUtil.addDate(DateUtil.getToday(), 1));
		}

		String strIndex = index.toString();
		if (strIndex.length() > 9) {
			return businessPrefix + currentTime + strIndex;
		} else {
			return businessPrefix + currentTime + prefixString[strIndex.length() - 1] + strIndex;
		}
	}
}
