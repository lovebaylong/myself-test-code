package cn.holdfun.cn.robot.test;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.csvreader.CsvReader;

/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-4-7，alex创建。 
 */

public class CsvReaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			URL url = new URL("http://yaotv-ebusiness-test.oss-cn-shenzhen.aliyuncs.com/selfservice/parameter/ddd61e71e24845e3a842e79e36da3471.csv");
			
			CsvReader csvReader = new CsvReader(url.openStream(), StandardCharsets.UTF_8);
			//System.out.println(csvReader.getDelimiter());
			//System.out.println(csvReader.getHeaders());
			while(csvReader.readRecord()){
				System.out.println(csvReader.getRawRecord());
				//String[] values = csvReader.getValues();
				//System.out.println(ReflectionToStringBuilder.toString(values));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
