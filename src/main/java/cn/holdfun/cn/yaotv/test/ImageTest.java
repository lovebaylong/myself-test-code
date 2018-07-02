package cn.holdfun.cn.yaotv.test;

import java.net.URL;

import net.coobird.thumbnailator.Thumbnails;

public class ImageTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// 放大两倍cc
			// Thumbnails.of("d://fj.jpg").scale(2).toFile("d://fj2.jpg");
			// 缩小两倍
			// Thumbnails.of("d://fj.jpg").scale(0.5).toFile("d://fj05.jpg");
			// 旋转90度
			/*Thumbnails.of("d://fj.jpg").rotate(15).scale(0.5f).outputFormat("png").toFile("d://fj10.png");
			Thumbnails.of("d://fj.jpg").scale(1).sourceRegion(-100, -100, 200, 200).outputFormat("png").toFile("d://fj20.png");
			Thumbnails.of("d://fj.jpg").sourceRegion(0, 0, 200, 200).size(300, 300).outputFormat("png").toFile("d://fj30.png");
			// 剪裁
			// Thumbnails.of("d://fj.jpg").sourceRegion(Positions.CENTER,400,400).size(200,
			// 200).keepAspectRatio(false).toFile("d://fj_size2.jpg");
			File file = new File("d://fj.jpg");
			BufferedImage image = ImageIO.read(file);
			BufferedImage kk = ImageUtil.abscut(image, -100, -100, 1, 200, 200);
			ImageIO.write(kk, "PNG", new File("d://fjwwwwwww30.png"));*/
			//ByteArrayOutputStream os = new ByteArrayOutputStream();
			//Thumbnails.of("D://img//cover_baby.jpg").scale(1.0f).toOutputStream(os);
			
			//InputStream is = new  ByteArrayInputStream(os.toByteArray());
			//Thumbnails.of(is).sourceRegion(362, 110, 451, 451).scale(1).toFile("D://img//cover_baby_size2.jpg");
			//Thumbnails.of(is).sourceRegion(362, 110, 451, 451).scale(1).toFile("D://img//cover_baby_size2.jpg");
			//Thumbnails.of(is).size(640, 640).keepAspectRatio(true).outputQuality(0.25f).toFile("D://img//cover7.jpg");
			
			//Thumbnails.of("D://img//cover.jpg").size(640, 640).keepAspectRatio(true).outputQuality(0.25f).toFile("D://img//cover4.jpg");
			URL url = new URL("http://xshareres.holdfun.cn/fandom/resources/images/2017/05/04/bd42481614fe43d29a29004af0fa9cd6.jpg");
			Thumbnails.of(url).scale(1.0d).outputQuality(1.0D).toFile("D://选手图//图片处理//bd42481614fe43d29a29004af0fa9cd6.jpg");
			Thumbnails.of(url).size(74, 74).outputQuality(1.0D).toFile("D://选手图//图片处理//bd42481614fe43d29a29004af0fa9cd6_74_74_1.0.jpg");
			Thumbnails.of(url).size(74, 74).toFile("D://选手图//图片处理//bd42481614fe43d29a29004af0fa9cd6_74_74.jpg");
			
			Thumbnails.of(url).size(174, 174).outputQuality(1.0D).toFile("D://选手图//图片处理//bd42481614fe43d29a29004af0fa9cd6_174_174_1.0.jpg");
			Thumbnails.of(url).size(174, 174).toFile("D://选手图//图片处理//bd42481614fe43d29a29004af0fa9cd6_174_174.jpg");
			
			Thumbnails.of(url).size(375, 375).outputQuality(1.0D).toFile("D://选手图//图片处理//bd42481614fe43d29a29004af0fa9cd6_375_375_1.0.jpg");
			Thumbnails.of(url).size(375, 375).toFile("D://选手图//图片处理//bd42481614fe43d29a29004af0fa9cd6_375_375.jpg");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
