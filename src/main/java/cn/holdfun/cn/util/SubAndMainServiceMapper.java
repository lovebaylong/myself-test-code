/**
 * 
 */
package cn.holdfun.cn.util;


/**
 * 有些副业务的部分数据需要合并到主业务，所以暂时先通过硬编码来实现彼此的对应关系，后续将实现到业务的配置里去
 * 
 * @author alex
 * 
 */
public class SubAndMainServiceMapper {

	// 勇士的荣耀业务本次需要打通三个业务的卡牌对战数据，所以此处做一下主从业务的对应关系，把从业务的全部导到主业务上来，先直接通过硬编码解决本次的需求
	/*private static final Map<String, String> SUB_MAIN_SERVICE_MAP = new HashMap<String, String>();
	static {
		SUB_MAIN_SERVICE_MAP.put("tv_henantv_warriorgzh", "tv_henantv_warriorhonor");// 公众号
		SUB_MAIN_SERVICE_MAP.put("tv_zhengzhoutv_warrior", "tv_henantv_warriorhonor");// 网络端
	}

	public static String getFinalServiceNo(String serviceNo) {
		if (StringUtils.isEmpty(serviceNo))
			return serviceNo;

		if (SUB_MAIN_SERVICE_MAP.containsKey(serviceNo))
			return SUB_MAIN_SERVICE_MAP.get(serviceNo);
		return serviceNo;
	}*/
}
