package cn.holdfun.cn.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.IgnoreSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HTTP工具类
 * 
 * @author Alex
 * 
 */
public class HttpUtil {
	private static final Log log = LogFactory.getLog(HttpUtil.class);
	private static final int MAX_REQ_TIME = 5000;

	/**
	 * 发送HTTP GET方式请求，返回请求响应中的字符串，不适应需要post返回响应数据的请求场景
	 * 
	 * @param reqUrl
	 * @return
	 */
	public static String sendHttpGetReqUrlToServer(String reqUrl) {
		if (StringUtils.isEmpty(reqUrl))
			return null;
		HttpURLConnection connection = null;
		try {
			URL getUrl = new URL(reqUrl);
			// 根据拼凑的URL，打开连接，URL.openConnection()函数会根据
			// URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection
			connection = (HttpURLConnection) getUrl.openConnection();
			// 建立与服务器的连接，并未发送数据
			connection.connect();
			// 发送数据到服务器并使用Reader读取返回的数据
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String lines;
			while ((lines = reader.readLine()) != null) {
				sb.append(lines);
			}
			reader.close();
			// 断开连接
//			connection.disconnect();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != connection){
				connection.disconnect();
				connection = null;
			}
		}
		return null;
	}

	/**
	 * 发送HTTP GET方式请求，返回请求响应中的字符串，不适应需要post返回响应数据的请求场景
	 * 
	 * @param reqUrl
	 * @return
	 */
	public static String sendHttpGetReqToServer(String reqUrl) {
		if (StringUtils.isEmpty(reqUrl))
			return null;
		
		RequestConfig config = RequestConfig.custom().setSocketTimeout(MAX_REQ_TIME).setConnectTimeout(MAX_REQ_TIME).setConnectionRequestTimeout(MAX_REQ_TIME).build();
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(reqUrl);
			httpGet.setConfig(config);
			
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			try {
				if (null != response1 && null != response1.getEntity()) {
					return EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8.toString());
				}
			} catch (Exception e) {
				log.error("向Http服务端发送请求时发生异常，原因：", e);
			} finally {
				if (response1 != null)
					response1.close();
			}
		} catch (Exception e) {
			log.error("向Http服务端发送请求时发生异常，原因：", e);
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (Exception e) {
					log.error("关闭httpclient时发生异常，原因：", e);
				}
			}
		}
		return null;
	}

	/**
	 * 发送HTTP GET方式请求，返回请求响应中的字符串，不适应需要post返回响应数据的请求场景
	 * 
	 * @param reqUrl
	 * @return
	 */
	public static String sendHttpGetReqToServer4CookiePolicy(String reqUrl, String cookieSpec) {
		if (StringUtils.isEmpty(reqUrl))
			return null;
		// long startTime = System.currentTimeMillis();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// String result = null;
		// String status = null;
		try {
			RequestConfig config = RequestConfig.custom().setCookieSpec(cookieSpec).build();
			HttpGet httpGet = new HttpGet(reqUrl);
			httpGet.setConfig(config);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			try {
				if (null != response1 && null != response1.getEntity()) {
					return EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
				}
			} catch (Exception e) {
				log.error("向Http服务端发送请求时发生异常，原因：", e);
			} finally {
				if (response1 != null)
					response1.close();
			}
		} catch (Exception e) {
			log.error("向Http服务端发送请求时发生异常，原因：", e);
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (Exception e) {
					log.error("关闭httpclient时发生异常，原因：", e);
				}
			}
		}
		return null;
	}

	/**
	 * 高性能方式发送请求到服务器
	 * 
	 * @param reqUrl
	 * @return
	 */
	public static String sendHttpGetReqToServerByHighPerformance(String reqUrl) {
		try {
			Content result = Request.Get(reqUrl).connectTimeout(MAX_REQ_TIME).socketTimeout(MAX_REQ_TIME).execute().returnContent();
			if (null == result)
				return null;
			return result.asString();
		} catch (Exception e) {
			log.error("向Http服务端发送请求时发生异常，原因：", e);
			return null;
		}
	}

	/**
	 * 高性能方式发送请求到服务器，同时限制在2秒之内必须返回与响应
	 * 
	 * @param reqUrl
	 * @return
	 */
	public static String sendHttpGetReqToServerByHighPerformanceAndTimeout(String reqUrl) {
		try {
			Content result = Request.Get(reqUrl).socketTimeout(MAX_REQ_TIME).connectTimeout(MAX_REQ_TIME).execute()
					.returnContent();
			if (null == result)
				return null;
			return result.asString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 高性能方式发送请求到服务器
	 * 
	 * @param reqUrl
	 * @return
	 */
	public static String sendHttpGetReqToServerByCustomCookie(String reqUrl) {
		CloseableHttpClient httpclient = null;
		try {
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(MAX_REQ_TIME).setSocketTimeout(MAX_REQ_TIME)
					.setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();

			httpclient = HttpClients
					.custom()
					.setDefaultCookieSpecRegistry(
							RegistryBuilder.<CookieSpecProvider> create().register(CookieSpecs.IGNORE_COOKIES, new IgnoreSpecFactory())
									.build()).setDefaultRequestConfig(requestConfig).build();

			HttpGet httpGet = new HttpGet(reqUrl);
			CloseableHttpResponse response1 = null;
			try {
				response1 = httpclient.execute(httpGet);
				if (null != response1 && null != response1.getEntity()) {
					return EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
				}
			} catch (Exception e) {
				log.error("向Http服务端发送请求时发生异常，原因：", e);
			} finally {
				try {
					if (response1 != null)
						response1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			log.error("向Http服务端发送请求时发生异常，原因：", e);
		} finally {
			try {
				if (httpclient != null)
					httpclient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 发送HTTP post方式请求
	 * 
	 * @param httpServerUrl
	 *            请求地址
	 * @param map
	 *            携带的参数值
	 * @return
	 */
	public static String sendHttpPostReqToServerByParams(String httpServerUrl, Map<String, String> map) {
		if (StringUtils.isEmpty(httpServerUrl))
			return null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = null;
		String status = null;
		try {
			HttpPost httpPost = new HttpPost(httpServerUrl);
			if (null != map && !map.isEmpty()) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			}
			CloseableHttpResponse response1 = httpclient.execute(httpPost);
			try {
				if (null != response1) {
					if (null != response1.getStatusLine())
						status = response1.getStatusLine().toString();
					if (null != response1.getEntity())
						result = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
				}
			} catch (Exception e) {
				log.error("向Http服务端发送请求时发生异常，原因：", e);
			} finally {
				if (response1 != null)
					response1.close();
			}
		} catch (Exception e) {
			log.error("向Http服务端发送请求时发生异常，原因：", e);
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (Exception e) {
					log.error("关闭httpclient时发生异常，原因：", e);
				}
			}
		}
		log.info(String.format("向Http服务端发送请求，请求地址：%s，请求返回状态：%s，请求返回报文：%s", httpServerUrl, status, result));
		return result;
	}

	/**
	 * 发送HTTP post方式请求
	 * 
	 * @param httpServerUrl
	 *            请求地址
	 * @param reqBody
	 *            请求的body字符串
	 * @return
	 */
	public static String sendHttpPostReqToServerByReqbody(String httpServerUrl, String reqBody, String contentType) {
		if (StringUtils.isEmpty(httpServerUrl))
			return null;
		if (StringUtils.isEmpty(contentType))
			contentType = "application/x-www-form-urlencoded";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = null;
		String status = null;
		try {
			HttpPost httpPost = new HttpPost(httpServerUrl);
			if (StringUtils.isNotEmpty(reqBody)) {
				StringEntity reqBodyEntity = new StringEntity(reqBody, StandardCharsets.UTF_8);
				reqBodyEntity.setContentType(contentType);
				httpPost.setEntity(reqBodyEntity);
			}
			CloseableHttpResponse response1 = httpclient.execute(httpPost);
			try {
				if (null != response1) {
					if (null != response1.getStatusLine())
						status = response1.getStatusLine().toString();
					if (null != response1.getEntity())
						result = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
				}
			} catch (Exception e) {
				log.error("向Http服务端发送请求时发生异常，原因：", e);
			} finally {
				if (response1 != null)
					response1.close();
			}
		} catch (Exception e) {
			log.error("向Http服务端发送请求时发生异常，原因：", e);
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (Exception e) {
					log.error("关闭httpclient时发生异常，原因：", e);
				}
			}
		}
		log.info(String.format("向Http服务端发送请求，请求地址：%s，请求报文：%s，请求返回状态：%s，请求返回报文：%s", httpServerUrl, reqBody, status,
				result));
		return result;
	}

	public static String sendHttpPostReqToServerByStream(String httpServerUrl, Map<String, String> textParams,
			Map<String, String> fileParams, String contentType) {
		if (StringUtils.isEmpty(httpServerUrl))
			return null;
		if (StringUtils.isEmpty(contentType))
			contentType = "multipart/form-data";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = null;
		String status = null;
		try {
			HttpPost httpPost = new HttpPost(httpServerUrl);

			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			multipartEntity.setCharset(StandardCharsets.UTF_8);
			if (null != textParams && !textParams.isEmpty()) {
				for (Map.Entry<String, String> e : textParams.entrySet()) {
					// 解决中文乱码
					multipartEntity.addTextBody(e.getKey(), e.getValue(),
							ContentType.create("text/plain", StandardCharsets.UTF_8));
				}
			}
			if (null != fileParams && !fileParams.isEmpty()) {
				for (Map.Entry<String, String> e : fileParams.entrySet()) {
					multipartEntity.addBinaryBody(e.getKey(), new File(e.getValue()),
							ContentType.APPLICATION_OCTET_STREAM, e.getValue());
				}
			}
			httpPost.setEntity(multipartEntity.build());

			CloseableHttpResponse response1 = null;
			try {
				response1 = httpclient.execute(httpPost);
				if (null != response1) {
					if (null != response1.getStatusLine())
						status = response1.getStatusLine().toString();
					if (null != response1.getEntity())
						result = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
				}
			} catch (Exception e) {
				log.error("向Http服务端发送请求时发生异常，原因：", e);
			} finally {
				if (response1 != null)
					response1.close();
			}
		} catch (Exception e) {
			log.error("向Http服务端发送请求时发生异常，原因：", e);
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (Exception e) {
					log.error("关闭httpclient时发生异常，原因：", e);
				}
			}
		}
		log.info(String.format("向Http服务端发送请求，请求地址：%s，请求返回状态：%s，请求返回报文：%s", httpServerUrl, status, result));
		return result;
	}

	public static String sendHttpPostReqToServerByURIStream(String httpServerUrl, Map<String, String> textParams,
			Map<String, String> fileParams, String contentType) {
		if (StringUtils.isEmpty(httpServerUrl))
			return null;
		if (StringUtils.isEmpty(contentType))
			contentType = "multipart/form-data";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = null;
		String status = null;
		try {
			HttpPost httpPost = new HttpPost(httpServerUrl);

			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			multipartEntity.setCharset(StandardCharsets.UTF_8);
			if (null != textParams && !textParams.isEmpty()) {
				for (Map.Entry<String, String> e : textParams.entrySet()) {
					// 解决中文乱码
					multipartEntity.addTextBody(e.getKey(), e.getValue(),
							ContentType.create("text/plain", StandardCharsets.UTF_8));
				}
			}
			if (null != fileParams && !fileParams.isEmpty()) {
				for (Map.Entry<String, String> e : fileParams.entrySet()) {
					String n = UuidUtil.randomUUID() + ".zip";
					InputStream is = new URL(e.getValue()).openStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					for (int b = is.read(); b >= 0; b = is.read())
						baos.write(b);

					File lf = new File(n);
					System.out.println(lf.getAbsolutePath());
					FileOutputStream faos = new FileOutputStream(lf);
					faos.write(baos.toByteArray());
					multipartEntity.addBinaryBody("detail", lf);
					try {
						is.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						faos.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			httpPost.setEntity(multipartEntity.build());

			CloseableHttpResponse response1 = null;
			try {
				response1 = httpclient.execute(httpPost);
				if (null != response1) {
					if (null != response1.getStatusLine())
						status = response1.getStatusLine().toString();
					if (null != response1.getEntity())
						result = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
				}
			} catch (Exception e) {
				log.error("向Http服务端发送请求时发生异常，原因：", e);
			} finally {
				if (response1 != null)
					response1.close();
			}
		} catch (Exception e) {
			log.error("向Http服务端发送请求时发生异常，原因：", e);
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (Exception e) {
					log.error("关闭httpclient时发生异常，原因：", e);
				}
			}
		}
		log.info(String.format("向Http服务端发送请求，请求地址：%s，请求返回状态：%s，请求返回报文：%s", httpServerUrl, status, result));
		return result;
	}

	public static String addTvZipResourceToWeixin(String httpServerUrl,  String resourceId, String resourceName, String filePath) {
		if (StringUtils.isEmpty(httpServerUrl) || StringUtils.isEmpty(resourceName) || StringUtils.isEmpty(filePath))
			return null;

		CloseableHttpClient httpclient = null;
		String result = null;
		String status = null;
		File lf = null;
		FileOutputStream faos = null;
		try {
			InputStream is = new URL(filePath).openStream();
			String n = UuidUtil.randomUUID() + ".zip";
			lf = new File(n);
			System.out.println(lf.getAbsolutePath());
			faos = new FileOutputStream(lf);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int b = is.read(); b >= 0; b = is.read())
				baos.write(b);
			
			faos.write(baos.toByteArray());
			log.info("开始上传素材到微信, 请求地址：" + httpServerUrl + ", 素材名称：" + resourceName + ", 素材路径：" + filePath + ", 素材大小：" + baos.size()
					+ "字节！");
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(httpServerUrl);
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			if(StringUtils.isNotEmpty(resourceId))
				multipartEntity.addTextBody("id", resourceId);
			multipartEntity.addBinaryBody("name", resourceName.getBytes(StandardCharsets.UTF_8));
			multipartEntity.addBinaryBody("detail", lf);
			httpPost.setEntity(multipartEntity.build());

			/*log.info("上传素材到微信, RequestLine = " + httpPost.getRequestLine());
			ByteArrayOutputStream bodyos = new ByteArrayOutputStream();
			httpPost.getEntity().writeTo(bodyos);
			log.info("上传素材到微信, Body Size = " + bodyos.size());
			byte[] bb = bodyos.toByteArray();
			log.info("上传素材到微信, Byte Array Size = " + bb.length);
			String s = new String(bb);
			log.info("上传素材到微信, Entity Content = " + s);*/
			CloseableHttpResponse response1 = null;
			try {
				response1 = httpclient.execute(httpPost);
				if (null != response1) {
					if (null != response1.getStatusLine())
						status = response1.getStatusLine().toString();
					if (null != response1.getEntity())
						result = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
				}
			} catch (Exception e) {
				log.error("向Http服务端发送请求时发生异常，原因：", e);
			} finally {
				if (response1 != null)
					response1.close();
			}
		} catch (Exception e) {
			log.error("向Http服务端发送请求时发生异常，原因：", e);
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error("关闭httpclient时发生异常，原因：", e);
				}
			}

			try {
				if (null != faos)
					faos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				try {
					if (null != lf)
						lf.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		log.info(String.format("向Http服务端发送请求，请求地址：%s，请求返回状态：%s，请求返回报文：%s", httpServerUrl, status, result));
		return result;
	}
	
	public static String sendHttpPostReqToServerByStream(String httpServerUrl, String keyName, String fileName,
			InputStream input) {
		if (StringUtils.isEmpty(httpServerUrl))
			return null;

		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = null;
		String status = null;
		try {
			HttpPost httpPost = new HttpPost(httpServerUrl);

			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			multipartEntity.setCharset(StandardCharsets.UTF_8);
			multipartEntity.addBinaryBody(keyName, input, ContentType.MULTIPART_FORM_DATA, fileName);
			httpPost.setEntity(multipartEntity.build());

			CloseableHttpResponse response1 = null;
			try {
				response1 = httpclient.execute(httpPost);
				if (null != response1) {
					if (null != response1.getStatusLine())
						status = response1.getStatusLine().toString();
					if (null != response1.getEntity())
						result = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
				}
			} catch (Exception e) {
				log.error("向Http服务端发送请求时发生异常，原因：", e);
			} finally {
				if (response1 != null)
					response1.close();
			}
		} catch (Exception e) {
			log.error("向Http服务端发送请求时发生异常，原因：", e);
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (Exception e) {
					log.error("关闭httpclient时发生异常，原因：", e);
				}
			}
		}
		log.info(String.format("向Http服务端发送请求，请求地址：%s，请求返回状态：%s，请求返回报文：%s", httpServerUrl, status, result));
		return result;
	}

	/**
	 * 高性能方式发送请求到服务器
	 * 
	 * @param reqUrl
	 * @return
	 */
	public static String sendHttpPostStreamReqToServerByHighPerformance(String reqUrl, String keyName, String fileName, InputStream input) {
		File lf = null;
		FileOutputStream faos = null;
		try {
			String n = UuidUtil.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
			lf = new File(n);
			System.out.println(lf.getAbsolutePath());
			faos = new FileOutputStream(lf);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int b = input.read(); b >= 0; b = input.read())
				baos.write(b);

			faos.write(baos.toByteArray());
			log.info("开始上传图片到微信, 请求地址：" + reqUrl + ", 图片名称：" + fileName + ", 图片大小：" + baos.size() + "字节！");

			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			multipartEntity.setCharset(StandardCharsets.UTF_8);
			multipartEntity.addBinaryBody(keyName, lf);
			// multipartEntity.addBinaryBody(keyName, input,
			// ContentType.MULTIPART_FORM_DATA, fileName);
			Content result = Request.Post(reqUrl).body(multipartEntity.build()).execute().returnContent();
			if (null == result)
				return null;

			log.info(String.format("向Http服务端发送请求，请求地址：%s，请求返回报文：%s", reqUrl, result.asString()));

			return result.asString();
		} catch (Exception e) {
			log.info(String.format("向Http服务端发送请求，请求地址：%s，异常原因：%s", reqUrl, e.getMessage()));
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (null != faos)
					faos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				try {
					if (null != lf)
						lf.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 微信支付专用
	 * 
	 * @param url
	 *            ：请求地址
	 * @param reqBody
	 * @param mch_id
	 *            :微信公众号商户编号
	 * @param carAddress
	 *            ：微信证书
	 * @return
	 */
	public static String mpSendHttpPostReqToServerByReqbody(String url, String reqBody, String mch_id, String carAddress) {
		String returnStr = null;
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(carAddress));
			try {
				keyStore.load(instream, mch_id.toCharArray());
			} finally {
				instream.close();
			}
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			try {
				HttpPost httppost = new HttpPost(url);
				if (StringUtils.isNotEmpty(reqBody)) {
					StringEntity reqBodyEntity = new StringEntity(reqBody, StandardCharsets.UTF_8);
					httppost.setEntity(reqBodyEntity);
				}
				CloseableHttpResponse response = httpclient.execute(httppost);
				try {
					HttpEntity entity = response.getEntity();
					returnStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} finally {
				httpclient.close();
			}
		} catch (Exception e) {
		}
		return returnStr.toString();
	}

	/**
	 * 微信 上传LOGO图像接口
	 * 
	 * @param uploadImgUrl
	 * @param fileType
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String uploadImg(String uploadImgUrl, String filePath) throws Exception {
		String result = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		/**
		 * 第一部分
		 */
		URL urlObj = new URL(uploadImgUrl);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流,输出表头
		OutputStream out = new DataOutputStream(con.getOutputStream());
		out.write(head);
		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return result;
	}

	/**
	 * 上传图片（从网络上某地址取出后，上传到另外一个地址）
	 * 
	 * @param targetImgUrl 目标地址
	 * @param srcImgUrl  源地址
	 * @return
	 * @throws Exception
	 */
	public static String uploadImgTransferAddr(String targetImgUrl, String srcImgUrl) throws Exception {
		String result = null;
		String fileName = srcImgUrl.substring(srcImgUrl.lastIndexOf("/") + 1);
		/**
		 * 第一部分
		 */
		URL urlObj = new URL(targetImgUrl);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + fileName + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流,输出表头
		OutputStream out = new DataOutputStream(con.getOutputStream());
		out.write(head);
		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new URL(srcImgUrl).openStream());
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		String fileUrl = "http://yaotv-test.oss-cn-shenzhen.aliyuncs.com/wxopen/merchant/images/20151021/3e04df676d8143348db9709514e42388.png";
		String UPLOAD_MEDIA_IMAGE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=image";
		String filePath = "E:\\百度云同步盘\\10.公司\\allen\\logo\\holdfun108.png";
		// System.out.println(file.getName());
		uploadImg(UPLOAD_MEDIA_IMAGE_URL, filePath);
		uploadImgTransferAddr(UPLOAD_MEDIA_IMAGE_URL, fileUrl);
	}
}
