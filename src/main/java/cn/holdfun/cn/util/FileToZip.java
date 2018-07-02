package cn.holdfun.cn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 将文件打包成ZIP压缩文件
 * 
 * @author LanP
 * @since 2012-3-1 15:47
 */
public class FileToZip {

	public static void zip(String zipFileAbsPath, String zipFileName, String... inputFilePaths) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileAbsPath + File.separator + zipFileName));
		for (String inputFilePath : inputFilePaths) {
			zip(out, new File(inputFilePath), "");
		}
		out.close();
	}

	private static void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			out.closeEntry();
			in.close();
		}
	}

	public static void main(String[] temp) {
		try {
			String[] ll = { "E:/svn/tv3.0/trunk/zq-tv-core/src/main/webapp/WEB-INF/template/survey",
					"E://svn//tv3.0//trunk//zq-tv-core//src//main//webapp//WEB-INF//html//survey//survey-111" };
			zip("d://", "12.zip", ll);// 你要压缩的文件夹
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}