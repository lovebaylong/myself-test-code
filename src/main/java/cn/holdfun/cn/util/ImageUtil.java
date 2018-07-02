package cn.holdfun.cn.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

	/**
	 * 旋转图片
	 * 
	 * @param image：图片
	 * @param degree：角度
	 * @param bgcolor：背景颜色，可以为null
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage rotateImg(BufferedImage image, double degree, Color bgcolor) throws IOException {
		int iw = image.getWidth();// 原始图象的宽度
		int ih = image.getHeight();// 原始图象的高度
		int w = 0, h = 0;
		int x = 0, y = 0;
		degree = degree % 360;
		if (degree < 0)
			degree = 360 + degree;// 将角度转换到0-360度之间
		double ang = Math.toRadians(degree);// 将角度转为弧度
		/**
		 * 确定旋转后的图象的高度和宽度
		 */
		if (degree == 180 || degree == 0 || degree == 360) {
			w = iw;
			h = ih;
		} else if (degree == 90 || degree == 270) {
			w = ih;
			h = iw;
		} else {
			int maxH = (int) Math.sqrt(Math.pow(ih, 2) + Math.pow(iw, 2));
			h = (int) (ih / Math.abs(Math.cos(ang))) + 50;
			w = (int) (ih * Math.abs(Math.sin(ang)) + iw * Math.abs(Math.cos(ang)));
			if (h > maxH)
				h = maxH;
			if (w > maxH)
				w = maxH;
		}
		x = (w / 2) - (iw / 2);// 确定原点坐标
		y = (h / 2) - (ih / 2);
		BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		Graphics2D gs = (Graphics2D) rotatedImage.getGraphics();
		if (bgcolor == null) {
			rotatedImage = gs.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
		} else {
			gs.setColor(bgcolor);
			gs.fillRect(0, 0, w, h);// 以给定颜色绘制旋转后图片的背景
		}
		AffineTransform at = new AffineTransform();
		at.rotate(ang, w / 2, h / 2);// 旋转图象
		at.translate(x, y);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		op.filter(image, rotatedImage);
		image = rotatedImage;
		return image;
	}

	/**
	 * 按照指定的XY坐标以及图片宽高，进行切图，先缩放比例，在切图
	 * 
	 * @param bi源图片
	 * @param x 切图点X坐标
	 * @param y 切图点Y坐标
	 * @param scale 缩放和放大倍数
	 * @param destWidth 目标图宽
	 * @param destHeight 目标图高
	 * @return
	 */
	public static BufferedImage abscut(BufferedImage bi, int x, int y, float scale, int destWidth, int destHeight) {
		if (scale < 0)
			return null;
		if (scale == 0)
			scale = 1;
		try {
			// 读取源图像
			int srcWidth = bi.getWidth(); // 源图宽度
			int srcHeight = bi.getHeight(); // 源图高度
			int destWidthTemp = (int) (scale * srcWidth);// 目标宽度
			int destHeightTemp = (int) (scale * srcHeight);// 目标高度
			// 获取缩放后的图片大小
			Image image = bi.getScaledInstance(destWidthTemp, destHeightTemp, Image.SCALE_DEFAULT);

			// 四个参数分别为图像起点坐标和宽高
			// 即: CropImageFilter(int x,int y,int width,int height)
			ImageFilter cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
			Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
			BufferedImage tag = new BufferedImage(destWidth, destHeight, Transparency.TRANSLUCENT);
			Graphics g1 = tag.getGraphics();
			Graphics2D g = (Graphics2D) g1;
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(img, 0, 0, null); // 绘制剪切后的图
			g.dispose();
			return tag;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) throws Exception {
		File file = new File("C:/test/q.png");
		BufferedImage image = ImageIO.read(file);
		BufferedImage ll = rotateImg(image, 80, null);
		// BufferedImage ll = ImageIO.read(inputStream);
		ImageIO.write(ll, "PNG", new File("C:/test/q2.png"));
		BufferedImage kk = abscut(ll, 200, 200, 0.5f, 200, 200);
		ImageIO.write(kk, "PNG", new File("C:/test/q3.png"));
	}

}