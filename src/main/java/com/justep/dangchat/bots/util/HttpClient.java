package com.justep.dangchat.bots.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;

/**
 * Http客户端类
 * 
 * @author Lining
 * @date 2016/9/5
 *
 */
public class HttpClient {

	/**
	 * 发送get请求，得到Json数组
	 * 
	 * @param url
	 *            请求Url
	 * @return Json数组
	 * @throws Exception
	 */
	public static JSONArray getJsonArray(String url) throws Exception {
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.setRequestProperty("Accept", "application/json");
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();

			// 调用服务
			if (code == 200) {
				InputStream is = conn.getInputStream();
				String jsonString = getResponseString(is);
				JSONArray array = new JSONArray(jsonString);
				return array;
			} else {
				throw new Exception("打开url出错，response code=" + code);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 得到Http响应内容
	 * 
	 * @param is
	 *            InputStream类的实例
	 * @return Http响应内容
	 * @throws IOException
	 */
	private static String getResponseString(InputStream is) throws IOException {
		byte[] data = readStream(is);
		String objectstring = new String(data);
		return objectstring;
	}

	/**
	 * 读取流内容
	 * 
	 * @param inStream
	 *            InputStream类的实例
	 * @return 包含流内容的byte数组
	 * @throws IOException
	 */
	private static byte[] readStream(InputStream inStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while (len != -1) {
			len = inStream.read(buffer);
			outputStream.write(buffer);
		}
		inStream.close();
		return outputStream.toByteArray();
	}

}
