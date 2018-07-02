package cn.holdfun.cn.robot.test;
import java.util.HashMap;
import java.util.Map;

/*
 * 版权所有 (C) 2014-2017 深圳掌趣互动科技有限公司。保留所有权利。
 * 版本：
 * 修改记录：
 *		1、2017-4-8，alex创建。 
 */

public class WildcardResolveTest {

	private static final String PARAM_WILDCARD_PREFIX = "%%_";
	private static final String PARAM_WILDCARD_SUFFIX = "%%";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String requestUrl = "http://testlive.holdfun.cn/lportal/api/comments/save?uu=%%_oi_real.csv:oi%%&uc=%%_un_real.csv:un%%&roomId=roomId&con=Areyouok";
		int fromIndex = 0;
		// 通配符前缀索引
		int wildcardPrefixIndex = 0;
		int wildcardSuffixIndex = 0;
		Map<String, String> wildcardParamMap = new HashMap<String, String>();
		while (fromIndex >= 0) {
			wildcardPrefixIndex = requestUrl.indexOf(PARAM_WILDCARD_PREFIX, fromIndex);
			if (wildcardPrefixIndex < 0)
				break;
			wildcardSuffixIndex = requestUrl.indexOf(PARAM_WILDCARD_SUFFIX, (wildcardPrefixIndex + 1));
			wildcardSuffixIndex += 2;
			fromIndex = wildcardSuffixIndex;
			String eachWildcard = requestUrl.substring(wildcardPrefixIndex, wildcardSuffixIndex);

			String[] wildcardPart = eachWildcard.split(":", 2);
			String fileName = wildcardPart[0];
			fileName = fileName.replaceAll(PARAM_WILDCARD_PREFIX, "");

			String paraName = wildcardPart[1];
			paraName = paraName.replaceAll(PARAM_WILDCARD_SUFFIX, "");
			wildcardParamMap.put(fileName, paraName);
		}

		for (Map.Entry<String, String> entrys : wildcardParamMap.entrySet()) {
			System.out.println(entrys.getKey() + "==" + entrys.getValue());
		}
	}
}
