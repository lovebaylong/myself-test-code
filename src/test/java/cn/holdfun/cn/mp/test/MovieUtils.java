package cn.holdfun.cn.mp.test;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.media.MediaLocator;

public abstract class MovieUtils
{
  public static MediaLocator createMediaLocator(String url)
  {
    MediaLocator ml  = null;
    if ((url.indexOf(":") > 0) && ((ml = new MediaLocator(url)) != null)) {
      return ml;
    }
    if (url.startsWith(File.separator))
    {
      if ((ml = new MediaLocator("file:" + url)) != null)
        return ml;
    }
    else
    {
      String file = "file:" + System.getProperty("user.dir") + File.separator + url;
      if ((ml = new MediaLocator(file)) != null) {
        return ml;
      }
    }
    return null;
  }

  public static byte[] loadImageFile(File imageLoc)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(imageLoc);
    byte[] read = new byte[fis.available()];
    fis.read(read);
    fis.close();
    return read;
  }

  public static byte[] convertImageToJPEG(File originalImage, float quality)
    throws IOException
  {
    return bufferedImageToJPEG(ImageIO.read(originalImage), quality);
  }

  public static byte[] loadImageAs32bitRGB(File imageLoc)
    throws IOException
  {
    BufferedImage img = ImageIO.read(imageLoc);
    return int32RGBBufferedImageToByteArray(img);
  }

  public static byte[] bufferedImageToJPEG(BufferedImage img, float quality)
    throws IOException
  {
    Iterator writers = ImageIO.getImageWritersBySuffix("jpeg");
    if (!writers.hasNext()) {
      throw new IllegalStateException("No writers for jpeg...");
    }
    ImageWriter writer = (ImageWriter)writers.next();
    ImageWriteParam imageWriteParam = writer.getDefaultWriteParam();
    imageWriteParam.setCompressionMode(2);
    imageWriteParam.setCompressionQuality(quality);

    IIOImage iioImage = new IIOImage(img, null, null);

    ByteArrayOutputStream baos = new ByteArrayOutputStream(img.getWidth() * img.getHeight() * 2);
    MemoryCacheImageOutputStream mcios = new MemoryCacheImageOutputStream(baos);
    writer.setOutput(mcios);
    writer.write(null, iioImage, imageWriteParam);
    baos.flush();
    mcios.close();
    return baos.toByteArray();
  }

  public static byte[] int32RGBBufferedImageToByteArray(BufferedImage img)
  {
    int[] pixels = null;
    DataBuffer db = img.getRaster().getDataBuffer();
    if ((db instanceof DataBufferInt))
      pixels = ((DataBufferInt)db).getData();
    else {
      throw new RuntimeException("Format not supported...");
    }
    byte[] ret = new byte[pixels.length * 4];
    for (int i = 0; i < pixels.length; i++)
    {
      ret[(4 * i)] = (byte)(pixels[i] & 0xFF000000);
      ret[(4 * i + 1)] = (byte)(pixels[i] & 0xFF0000);
      ret[(4 * i + 2)] = (byte)(pixels[i] & 0xFF00);
      ret[(4 * i + 3)] = (byte)(pixels[i] & 0xFF);
    }

    return ret;
  }
}