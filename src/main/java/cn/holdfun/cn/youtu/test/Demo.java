package cn.holdfun.cn.youtu.test;

import java.net.URL;

import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Demo {

	// appid, secretid secretkey请到http://open.youtu.qq.com/注册
	// 请把下面的APP_ID、SECRET_ID和SECRET_KEY换成你自己的数据，下面的数据已经不可用
	
	public static final String APP_ID = "10008631";
	public static final String SECRET_ID = "AKIDKj4GG4rmJqFoET8wSvDLTXdbZO2d5fQf";
	public static final String SECRET_KEY = "xmS7cw64MVPBrIVjlBnJkWioqe8GVYfq";
	public static final String USER_ID = "154052684";
	
	public static void main(String[] args) {

		try {
			//String imgUrl = "https://portalstoragewuprod2.azureedge.net/face/demo/detection%206.jpg";
			//String imgUrl = "https://portalstoragewuprod2.azureedge.net/emotion/recognition1.jpg";
			//String imgUrl = "https://portalstoragewuprod2.azureedge.net/face/demo/detection%205.jpg";
//			String imgUrl = "https://portalstoragewuprod2.azureedge.net/emotion/recognition2.jpg";
//			String imgUrl = "https://portalstoragewuprod2.azureedge.net/emotion/recognition3.jpg";
			//String imgUrl = "https://portalstoragewuprod2.azureedge.net/emotion/recognition4.jpg";
//			String imgUrl = "http://yaotv-test.oss-cn-shenzhen.aliyuncs.com/cloudalbum/6e3226bd203b452eb6f48b67d34c7ae5/cover.jpg";
			//String imgUrl = "http://yaotv-test.oss-cn-shenzhen.aliyuncs.com/cloudalbum/f3e03bca577d4f75b6da804c5a875f8a/ys7N8z30MOqtEpqdbtQZVFqGUEs1_VWrPG-0VddiwD6Ye_iEZq0HehQc8fDALrwS.jpg";
			String imgUrl = "http://cdn.holdfun.cn/cloudalbum/59531956fefa4fe7bd26b149ddd2cc4f/cover.jpg";
			//String imgUrl = "http://cdn.holdfun.cn/cloudalbum/dc4f9c048e28423892db82498cb3b48f/LwPmpOnm6sqcv-dkgay2zwtr8w3JfjjLk9l1681_QnWzhr9JHkmma3mFjFJic2As.jpg";
			Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, USER_ID, Youtu.API_YOUTU_END_POINT);
			JSONObject respose;
//			respose= faceYoutu.FaceCompareUrl("http://open.youtu.qq.com/content/img/slide-1.jpg","http://open.youtu.qq.com/content/img/slide-1.jpg");
			//respose = faceYoutu.DetectFace("D://img//404390385334650410.png",1);
			respose = faceYoutu.DetectFaceUrl(imgUrl, 0);
			//get respose 
			System.out.println("youtu detect facu, url=" + imgUrl + ", respose=" + respose.toString());
			//分析分败
			/*
			 * {"face":[],"errorcode":-1308,"errormsg":"ERROR_DOWNLOAD_IMAGE_FAILED"}
			 */
			int errorcode = respose.getInt("errorcode");
			if (0 != errorcode)
				return;
			
			//分析成功
			/*
			 * {
			    "session_id": "",
			    "image_height": 600,
			    "image_width": 421,
			    "face": [
			        {
			            "face_id": "1745908595457851391",
			            "x": 113,
			            "y": 198,
			            "height": 241,
			            "width": 241,
			            "pitch": -8,
			            "roll": -4,
			            "yaw": 9,
			            "age": 6,
			            "gender": 3,
			            "glass": false,
			            "expression": 20,
			            "beauty": 88
			        }
			    ],
			    "errorcode": 0,
			    "errormsg": "OK"
			}
			 */
			
			//如果图片是正方形的就不做任何的裁剪处理，直接pass
			int image_height = respose.getInt("image_height");
			System.out.println("image_height="+image_height);
			int image_width = respose.getInt("image_width");
			System.out.println("image_width="+image_width);
			//如果图片是1：1的正方形就不用做任何处理了。
			if(image_height == image_width)
				return;
			
			if(!respose.containsKey("face"))
				return;
			
			//找出人脸的中心点
			JSONArray faces = respose.getJSONArray("face");
			if(null == faces || faces.isEmpty())
				return;
			
			//得到最包括所有人脸在内的矩阵
			int y=10000;
			int x=10000;
			int bottom=0;
			int right=0;
			int width=0;
			int height=0;
			for(int i=0;i<faces.size();i++){
				JSONObject eachFace = faces.getJSONObject(i);
				int faceX = eachFace.getInt("x");
				int faceY = eachFace.getInt("y");
				int faceHeight = eachFace.getInt("height");
				int faceWidth = eachFace.getInt("width");
				int faceTop = faceY;
				int faceBottom = faceY + faceHeight;
				int faceLeft = faceX;
				int faceRight = faceX + faceWidth;
				
				//top与left哪个更小用哪个，bottom与right哪个更大用哪个
				if(faceTop < y)
					y = faceTop;
				if(faceLeft < x)
					x = faceLeft;
				
				if(faceBottom > bottom)
					bottom = faceBottom;
				if(faceRight > right)
					right = faceRight;
			}
			width=right-x;
			height=bottom - y;
			
			//计算出包括所有人脸在内的矩阵的中心点
			int faceCenterX = x + (int)(width/2);
			int faceCenterY = y + (int)(height/2);

			System.out.println("x="+x);
			System.out.println("y="+y);
			System.out.println("bottom="+bottom);
			System.out.println("right="+right);
			System.out.println("width="+width);
			System.out.println("height="+height);
			System.out.println("faceCenterX="+faceCenterX);
			System.out.println("faceCenterY="+faceCenterY);
			
			//计算width与height哪个更小，哪个更小就使用哪个做为等比值进行裁剪
			int scaleValue = image_width;
			if(image_width > image_height)
				scaleValue = image_height;
			System.out.println("scaleValue="+scaleValue);
			int halfScaleValue = (int)(scaleValue/2);
			System.out.println("halfScaleValue="+halfScaleValue);
			
			int cutX = 0;
			int cutY = 0;
			int cutWidth = scaleValue;
			int cutHeight = scaleValue;
			//计算在scaleValue为正方形的裁剪区域内与face的区域关系
			if(faceCenterX <= halfScaleValue)
				cutX = 0;
			else
				cutX = faceCenterX - halfScaleValue;
			if((cutX + cutWidth) > image_width)
				cutX = cutX - ((cutX + cutWidth) -image_width);
			
			if(faceCenterY <= halfScaleValue)
				cutY = 0;
			else
				cutY = faceCenterY - halfScaleValue;
			if((cutY+cutHeight) > image_height)
				cutY = cutY - ((cutY + cutHeight) -image_height);

			System.out.println("cutX="+cutX);
			System.out.println("cutY="+cutY);
			System.out.println("cutWidth="+cutWidth);
			System.out.println("cutHeight="+cutHeight);
			
			URL imageUrl = new URL(imgUrl);
			//int x, int y, int width, int height
			Thumbnails.of(imageUrl).sourceRegion(x, y, width, height).scale(1).toFile("D://img//face9_size1.jpg");
			Thumbnails.of(imageUrl).sourceRegion(cutX, cutY, cutWidth, cutHeight).scale(1).toFile("D://img//face9_size2.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
