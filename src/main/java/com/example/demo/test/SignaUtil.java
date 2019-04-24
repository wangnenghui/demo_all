package com.example.demo.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class SignaUtil {
	private static final String DA_PING_CLIENT_ID = "daping_app";
	public static Map<String, String> CLIENT_MAP = new HashMap<String, String>();
	static {
		CLIENT_MAP.put("app", "0357a7592c4734a8b1e12bc48e29e9e9");
		CLIENT_MAP.put("ios", "0cde14f1-c0c4-4590-b4e2-58d530012736");
		CLIENT_MAP.put("ebelter", "7e02fcf1-25ee-428e-8fb5-94df21fa1faf");
		CLIENT_MAP.put("doctor_android", "0315887c-355a-48eb-b4a2-451676ebef94");
		CLIENT_MAP.put("doctor_ios", "d7375964-a581-45a8-b64d-86045fd97b30");
		CLIENT_MAP.put("doctor_ios_in_house", "1671968a-dacb-4089-b4e9-ee5735951661");
		CLIENT_MAP.put("blood_sugar_android", "fc57c684-fac6-46fa-9d07-a49e0412dd04");
		CLIENT_MAP.put("blood_sugar_ios", "db98373a-4014-4026-96fd-95c8335b65e2");
		CLIENT_MAP.put("blood_sugar_en_android", "4cca8c82-6853-4ad4-b25a-18f534538fd0");
		CLIENT_MAP.put("blood_sugar_en_ios", "0f327538-5348-4b99-83e7-d45e92404678");
		CLIENT_MAP.put("tv", "7c4fa07f-a90e-43f0-bfce-1d36ae524dcc");
		CLIENT_MAP.put("daping_app", "257f811e-f13b-48c3-8894-c68f6ea44874");
		CLIENT_MAP.put("ios_in_house", "761f8aa1-8502-4f9f-afb3-93fd77747fd6");
		CLIENT_MAP.put("network_hospital_tv", "278273d98449d7033418b7101ab68f6b");
		CLIENT_MAP.put("ums_android", "e8x8rovl7105ikxl794j7jz8hu3i6ybx");
		CLIENT_MAP.put("ums_ios", "bxpv6nfw2rym4nr88flvc8jhqi10mcdo");
		CLIENT_MAP.put("aihome_android", "f78726d3b819be78b2df952659c3dedd");
		CLIENT_MAP.put("aihome_ios", "dd2ff1319d6fd46d96a3a2196d8b8d3d");
		
		CLIENT_MAP.put("upk_android", "e8x8rovl7105ikxl794j7jz8hu3i6ybx");
		CLIENT_MAP.put("upk_ios", "bxpv6nfw2rym4nr88flvc8jhqi10mcdo");
	}

	/**
	 * mhs签名获取
	 * 
	 * @param subgba
	 *            mhs平台签名映射
	 * @param names
	 *            相关签名的参数名称
	 * @return
	 */
	public static String getSignaStr(MhsSingna subgba, String[] names, String clientId) {
		if (null == names) {
			return null;
		}
		// 排序
		String sortLastNames[] = sortParameter(names);
		// 签名之前的字符
		String beforSignaStr = builderAllParameterValues(subgba, sortLastNames, clientId);
		// 签名后的字符
		String lastSignaStr = SHAUtil.encrypt(beforSignaStr);
		return lastSignaStr;
	}

	private static String builderAllParameterValues(MhsSingna subgba, String[] sortLastNames, String clientId) {
		StringBuffer parametersBf = new StringBuffer();
		for (String name : sortLastNames) {
			String value = subgba.getMappingValue(name);
			System.out.println("subgba:"+value);
			if (value==null) {
				value = "";
			}
			parametersBf.append(value.trim());
		}
		if (DA_PING_CLIENT_ID.equals(clientId)) {
			parametersBf.append(CLIENT_MAP.get(DA_PING_CLIENT_ID));
		} else {
			parametersBf.append(CLIENT_MAP.get("app"));
		}
		System.out.println("parametersBf:"+parametersBf.toString());
		return parametersBf.toString();
	}

	/**
	 * 参数排序
	 * 
	 * @param parameters
	 * @return
	 */
	private static String[] sortParameter(String[] parameters) {
		Arrays.sort(parameters, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				int i = Math.abs(a.hashCode());
				int j = Math.abs(b.hashCode());
				return i - j;
			}
		});
		return parameters;
	}

	public interface MhsSingna {
		/**
		 * 获取 映射参数值
		 * 
		 * @param key
		 * @return
		 */
		public String getMappingValue(String key);
	}
}
