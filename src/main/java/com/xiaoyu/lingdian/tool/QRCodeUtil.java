package com.xiaoyu.lingdian.tool;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.xiaoyu.lingdian.tool.file.ImageHandler;

public final class QRCodeUtil extends ImageHandler{

	private QRCodeUtil() {}

	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;

	private static final int ICON_WIDTH = 40;
	private static final int ICON_HEIGHT = 40;
	private static final int IMAGE_HALF_WIDTH = 20;

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	public static void generate(
			String content,
			int width,
			int height,
			String iconUrl,
			ImageFormat format,
			OutputStream out) throws WriterException, IOException {
		BufferedImage image = toBufferedImage(content, width, height, iconUrl);
		writeToStream(image, format, out);
	}

	public static void generate(
			String content,
			int width,
			int height,
			ImageFormat format,
			OutputStream out) throws WriterException, IOException {
		generate(content, width, height, null, format, out);
	}

	public static void generateFile(String content, ImageFormat format, File file) throws IOException, WriterException {
		FileOutputStream out = new FileOutputStream(file);
		try {
			generate(content, WIDTH, HEIGHT, format, out);
		} finally {
			out.close();
		}
	}

	public static void generateStream(String content, OutputStream out)
			throws WriterException, IOException {
		generate(content, WIDTH, HEIGHT, ImageFormat.jpeg, out);
	}

	public static void generateStream(String content, String iconUrl, OutputStream out)
			throws WriterException, IOException {
		generate(content, WIDTH, HEIGHT, iconUrl, ImageFormat.jpeg, out);
	}

	public static String generateBase64(String content, ImageFormat format)
			throws WriterException, IOException {
		BufferedImage bi = toBufferedImage(content, WIDTH, HEIGHT, null);
		return codeBase64(bi, format);
	}

	public static String generateBase64WithIcon(String content, String iconUrl, ImageFormat format)
			throws WriterException, IOException {
		BufferedImage bi = toBufferedImage(content, WIDTH, HEIGHT, iconUrl);
		return codeBase64(bi, format);
	}

	public static BufferedImage toBufferedImage(String content, int width, int height, String iconUrl)
			throws WriterException {
		Map<EncodeHintType, Object> hints = new HashMap<>();

		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		BitMatrix matrix = new MultiFormatWriter().encode(
				content, BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < matrix.getWidth(); x++) {
			for (int y = 0; y < matrix.getHeight(); y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}

		if(StringUtils.isNotEmpty(iconUrl)) {
			BufferedImage iconImage = getIconImage(iconUrl, ICON_WIDTH, ICON_HEIGHT);

			int[][] srcPixels = new int[ICON_WIDTH][ICON_HEIGHT];
			for (int i = 0; i < iconImage.getWidth(); i++) {
				for (int j = 0; j < iconImage.getHeight(); j++) {
					srcPixels[i][j] = iconImage.getRGB(i, j);
				}
			}

			int halfW = matrix.getWidth() / 2;
			int halfH = matrix.getHeight() / 2;
			int[] pixels = new int[width * height];

			for (int y = 0; y < matrix.getHeight(); y++) {
				for (int x = 0; x < matrix.getWidth(); x++) {
					// 读取图片
					if (x > halfW - IMAGE_HALF_WIDTH
							&& x < halfW + IMAGE_HALF_WIDTH
							&& y > halfH - IMAGE_HALF_WIDTH
							&& y < halfH + IMAGE_HALF_WIDTH ) {

						int tmp = srcPixels[x - halfW + IMAGE_HALF_WIDTH]
														[y - halfH + IMAGE_HALF_WIDTH];
						if(tmp != -1) {
							pixels[y * width + x] = tmp;
						} else {
							pixels[y * width + x] = matrix.get(x, y) ? 0xff000000 : 0xfffffff;
						}
					}
					// 在图片四周形成边框
					else {
						// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
						pixels[y * width + x] = matrix.get(x, y) ? 0xff000000 : 0xfffffff;
					}
				}
			}
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			image.getRaster().setDataElements(0, 0, width, height, pixels);
		}

		return image;
	}

	private static BufferedImage getIconImage(String iconUrl, int iconWidth, int iconHeight) {

		try {
			URL url = new URL(iconUrl);
			BufferedImage srcImage = ImageIO.read(url);
			Image destImage = srcImage.getScaledInstance(iconWidth, iconHeight, BufferedImage.SCALE_SMOOTH);


			BufferedImage image = new BufferedImage(iconWidth, iconHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, iconWidth, iconHeight);

			if (iconWidth == destImage.getWidth(null))
				graphic.drawImage(destImage, 0,
						(iconHeight - destImage.getHeight(null)) / 2,
						destImage.getWidth(null), destImage.getHeight(null),
						Color.white, null);
			else
				graphic.drawImage(destImage,
						(iconWidth - destImage.getWidth(null)) / 2, 0,
						destImage.getWidth(null), destImage.getHeight(null),
						Color.white, null);
			graphic.dispose();

			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*public static void main(String args[]) throws IOException, WriterException {
		System.out.println(QRCodeUtil.generateBase64WithIcon("http://www.baidu.com", "http://localhost:8100/media/image/201512/0ihsg9tu38gej_256x256.png", ImageFormat.png));
	}*/
}