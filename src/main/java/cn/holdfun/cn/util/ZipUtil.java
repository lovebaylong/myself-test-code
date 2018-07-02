package cn.holdfun.cn.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class ZipUtil {
	/**
	 * 执行压缩操作
	 * 
	 * @param srcPathName 被压缩的文件/文件夹
	 */
	public static void compressExe(String srcPathName, String destFileName) {
		File file = new File(srcPathName);
		if (!file.exists()) {
			throw new RuntimeException(srcPathName + "不存在！");
		}
		File destFile = new File(destFileName);
		if (destFile.exists())
			destFile.deleteOnExit();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(destFileName));
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			String basedir = "";
			compressByType(file, out, basedir);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 判断是目录还是文件，根据类型（文件/文件夹）执行不同的压缩方法
	 * 
	 * @param file
	 * @param out
	 * @param basedir
	 */
	private static void compressByType(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			compressDirectory(file, out, basedir);
		} else {
			compressFile(file, out, basedir);
		}
	}

	/**
	 * 压缩一个目录
	 * 
	 * @param dir
	 * @param out
	 * @param basedir
	 */
	private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists()) {
			return;
		}

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compressByType(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/**
	 * 压缩一个文件
	 * 
	 * @param file
	 * @param out
	 * @param basedir
	 */
	private static void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[1024];
			while ((count = bis.read(data)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解压缩zip包
	 * 
	 * @param zipFilePath zip文件路径
	 * @param targetPath 解压缩到的位置，如果为null或空字符串则默认解压缩到跟zip包同目录跟zip包同名的文件夹下
	 * @throws IOException
	 */
	public static void unzip(String zipFilePath, String targetPath) throws IOException {
		OutputStream os = null;
		InputStream is = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipFilePath);
			String directoryPath = "";
			if (StringUtils.isEmpty(targetPath)) {
				directoryPath = zipFilePath.substring(0, zipFilePath.lastIndexOf("."));
			} else {
				directoryPath = targetPath;
			}
			Enumeration<ZipEntry> entryEnum = zipFile.getEntries();
			if (null != entryEnum) {
				ZipEntry zipEntry = null;
				while (entryEnum.hasMoreElements()) {
					zipEntry = entryEnum.nextElement();
					if (!zipEntry.isDirectory() && zipEntry.getSize() > 0) {
						// 文件
						File targetFile = buildFile(directoryPath + File.separator + zipEntry.getName(), false);
						os = new BufferedOutputStream(new FileOutputStream(targetFile));
						is = zipFile.getInputStream(zipEntry);
						byte[] buffer = new byte[4096];
						int readLen = 0;
						while ((readLen = is.read(buffer, 0, 4096)) >= 0) {
							os.write(buffer, 0, readLen);
						}
						os.flush();
						os.close();
					}
				}
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (null != zipFile) {
				zipFile = null;
			}
			if (null != is) {
				is.close();
			}
			if (null != os) {
				os.close();
			}
		}
	}

	/**
	 * 生产文件 如果文件所在路径不存在则生成路径
	 * 
	 * @param fileName 文件名 带路径
	 * @param isDirectory 是否为路径
	 * @return
	 */
	private static File buildFile(String fileName, boolean isDirectory) {
		File target = new File(fileName);
		if (isDirectory) {
			target.mkdirs();
		} else {
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
				target = new File(target.getAbsolutePath());
			}
		}
		return target;
	}

	public static void main(String[] args) {
		compressExe("C:/3264b6983a7745508afae5552ba82557", "c:/tt.zip");
	}

	/**
	 * 压缩文件
	 * 
	 * @param baseDir 压缩后文件的存放路径
	 * @param resultFile 压缩后的文件
	 * @param targetFile 需要压缩的文件
	 */
	public static void zip(File baseDir, File resultFile, File targetFile) {
		Zip zip = new Zip();
		Project project = new Project();
		zip.setProject(project);
		zip.setDestFile(resultFile);
		FileSet fs = new FileSet();
		fs.setProject(project);
		if (null != baseDir)
			fs.setDir(baseDir);
		fs.setFile(targetFile);
		zip.addFileset(fs);
		zip.execute();
	}
}
