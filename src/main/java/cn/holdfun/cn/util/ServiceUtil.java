package cn.holdfun.cn.util;

import org.apache.commons.lang.StringUtils;

/**
 * 业务服务工具类
 * 
 * @author allen
 * 
 */
public class ServiceUtil {

	/**
	 * 获取表名后缀
	 * 
	 * @param serviceNo
	 * @return
	 */
	public static String getTableNameSuffixByServiceNo(String serviceNo) {
		if (StringUtils.isEmpty(serviceNo))
			return "";
		return "_" + serviceNo;

	}
}
