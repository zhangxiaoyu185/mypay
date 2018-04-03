package com.xiaoyu.lingdian.tool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

import com.xiaoyu.lingdian.tool.file.ImageHandler;

/**
 * 验证码工具类
 */
public class VerificationUtils extends ImageHandler {

	public enum ComplexLevel {
		SIMPLE,
		MEDIUM,
		HARD
	}

	/**
	 * captcha info include text and image built from text
	 */
	public static class CaptchaInfo {

		private BufferedImage image;

		private String text;

		public BufferedImage getImage() {
			return image;
		}

		public void setImage(BufferedImage image) {
			this.image = image;
		}

		public String getText() {

			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}

	private static final char[] SIMPLE={'0','1','2','3','4','5','6','7','8','9'};
	private static final char[] MEDIUM={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','Z','Y','Z'};
	private static final char[] HARD={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','Z','Y','Z'};
	private static SecureRandom POSITION_RANDOM = new SecureRandom();

	private static final int WIDTH = 100;
	private static final int HEIGHT = 40;

	/**
	 * 根据复杂程度生产指定长度的文本
	 * @param textLength captcha文本长度
	 * @param level captcha 复杂度
	 * @return 指定长度的captcha文本
	 */
	public static String getRandomText(int textLength, ComplexLevel level) {

		if (textLength <= 0) {
			throw new IllegalArgumentException("text length must be great than 0");
		}

		char[] targetArr = chooseComplexArr(level);
		StringBuilder sb = new StringBuilder(textLength);
		for(int i=0; i< textLength; i++){
			int position = ThreadLocalRandom.current().nextInt(targetArr.length);
			sb.append(targetArr[position]);
		}
		return sb.toString();
	}

	private static char[] chooseComplexArr(ComplexLevel level) {
		char[] targetArr;
		switch (level) {
			case SIMPLE:
				targetArr = SIMPLE;
				break;
			case MEDIUM:
				targetArr = MEDIUM;
				break;
			case HARD:
				targetArr = HARD;
				break;
			default:
				targetArr = SIMPLE;
		}
		return targetArr;
	}

	/**
	 * 产生特定位数的验证码及图片，宽度默认100，高度默认40
	 * @param textLength
	 * @param level
	 * @return CaptchaInfo
	 */
	public static CaptchaInfo getCaptcha(int textLength, ComplexLevel level) {
		return getCaptcha(textLength, WIDTH, HEIGHT, level);
	}

	/**
	 * 验证码生产类<br>
	 * @param width 验证码图片宽度
	 * @param height 验证码图片高度
	 * @return CaptchaInfo
	 */

	public static CaptchaInfo getCaptcha(int textLength, int width, int height, ComplexLevel level) {

		if (textLength <= 0) {
			throw new IllegalArgumentException("captcha text length must be larger than 0");
		}

		//用来存放结果
		CaptchaInfo captcha = new CaptchaInfo();

		//获取随机字符串
		String text = getRandomText(textLength, level);

		//绘制图片
		BufferedImage image = getCaptchaImage(text, width, height);

		captcha.setImage(image);
		captcha.setText(text);

		return captcha;
	}

	public static BufferedImage getCaptchaImage(String captchaText, int width, int height) {

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graph = bi.getGraphics();
		graph.fillRect(0, 0, width, height);
		graph.setFont(new Font("Courier New", Font.BOLD, 30));
		graph.setColor(Color.BLACK);

		for(int i = 0; i < captchaText.length(); i++) {
			char word = captchaText.charAt(i);
			graph.drawString(word + "", (i * ((width - 10) / 4)) + 5 + POSITION_RANDOM.nextInt(((width - 10) / 4) / 2), height / 2 + POSITION_RANDOM.nextInt(height / 2));
		}
		graph.dispose();
		return bi;
	}



	/*
	public static void main(String[] args) throws IOException, InterruptedException, IOException {

		Object[] obj = VerificationUtils.getCaptcha(4, ComplexLevel.MEDIUM);

		String base64 = VerificationUtils.codeBase64((BufferedImage)obj[0], ImageFormat.png);
		String kaptcha = (String)obj[1];
		System.out.println(base64);
		System.out.println(kaptcha);
	}
	*/
}
