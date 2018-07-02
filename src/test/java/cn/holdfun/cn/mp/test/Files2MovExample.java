/*
 * Files2MovExample.java
 *
 * Created on October 16, 2006, 10:43 PM
 * 
 * <p>Title: Jim2mov</p>
 *
 * <p>Description: Create movies from image files</p>
 *
 * <p>Copyright: (C) Copyright 2005-2006, by Andre' Neto</p>
 *
 * Project Info:  	http://jim2mov.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 * </p>
 *
 * @author Andre' Neto
 * @version 1.0.0
 *
 */

package cn.holdfun.cn.mp.test;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.jim2mov.core.DefaultMovieInfoProvider;
import org.jim2mov.core.FrameSavedListener;
import org.jim2mov.core.ImageProvider;
import org.jim2mov.core.Jim2Mov;
import org.jim2mov.core.MovieInfoProvider;
import org.jim2mov.core.MovieSaveException;

/**
 *
 * @author andre
 */
public class Files2MovExample implements ImageProvider, FrameSavedListener
{
    private String[] files = null;
    private int type = MovieInfoProvider.TYPE_QUICKTIME_JPEG;
    
    /**
     * Creates a new instance of Files2MovExample 
     */
    public Files2MovExample(String[] files, int type, String saveFileLocation)
    {
        this.files = files;
        this.type = type;
        DefaultMovieInfoProvider dmip = new DefaultMovieInfoProvider(saveFileLocation);
        dmip.setFPS(1);
        dmip.setNumberOfFrames(files.length);
        //dmip.setMWidth(320);
        //dmip.setMHeight(240);
        try
        {
            new Jim2Mov(this, dmip, this).saveMovie(type);
        }
        catch(MovieSaveException mse)
        {
            mse.printStackTrace();
        }        
    }

    public void frameSaved(int frameNumber)
    {
        System.out.println("Saved frame: " + frameNumber);
    }

    public byte[] getImage(int frame)
    {
        try
        {            
        	byte[] re =  MovieUtils.convertImageToJPEG(new File(files[frame].trim()), 1.0f);

        	System.out.println(files[frame].trim() + " = " + re.length);
//        	byte2image(re, frame + ".jpg");
        	return re;
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        
        return null;
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
//    	String path = "D:\\资料\\摇电视音频设备\\照片截图";
    	String path = "D:\\选手图";
    	File pf = new File(path);
    	File [] fa = pf.listFiles();
    	args = new String[fa.length];
    	for(int i = 0 ;i<fa.length;i++){
    		File f = fa[i];
//    		System.out.println(f.getAbsolutePath());
    		args[i]=f.getAbsolutePath();
    	}
    	//args = new String[]{"D:\\选手图\\spy.png","D:\\选手图\\spy_2.png","D:\\选手图\\哐才组合.png","D:\\选手图\\宋宇.png","D:\\选手图\\张艺瀚.png","D:\\选手图\\周深李维.jpg"};
        new Files2MovExample(args, MovieInfoProvider.TYPE_QUICKTIME_JPEG, "Test6.avi");
    }
    //byte数组到图片
    public static void byte2image(byte[] data,String path){
        if(data.length<3||path.equals("")) return;
        try{
        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
        imageOutput.write(data, 0, data.length);
        imageOutput.close();
        System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
          System.out.println("Exception: " + ex);
          ex.printStackTrace();
        }
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
