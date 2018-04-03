package com.xiaoyu.lingdian.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO add your class javadoc here
 *
 * @author Kai
 * @version 0.1
 */
public class BaseVoUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseVoUtils.class);

	public static <P, T extends BaseVO> T convert(Class<T> voType, P po) {

		if (voType == null) {
			throw new IllegalArgumentException("argument required");
		}

		try {
			T v = voType.newInstance();
			v.convertPOToVO(po);

			return v;

		} catch (InstantiationException | IllegalAccessException e) {
			LOGGER.error("could not create instance for clazz " + voType.getName(), e);
			throw new IllegalArgumentException("failed to create instance for class " + voType.getName());
		}
	}
}
