package com.zrodo.agriculture.util.json;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonMapUtils {
	public static final String respCode = "respCode";
	public static final String respMsg = "respMsg";

	public static Map<String, Object> buildStatusMap(String statusCode,
			String statusMsg) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put(respCode, statusCode);
		map.put(respMsg, statusMsg);
		return map;
	}

	public static Map<String, Object> buildSuccessMap() {
		return buildStatusMap("1000000", "操作成功");
	}

	public static Map<String, Object> buildFailureMap() {
		return buildStatusMap("1000001", "操作失败");
	}

	public static Map<String, Object> buildNetworkErrorMap() {
		return buildStatusMap("1000002", "网络错误");
	}
	
	public static Map<String, Object> buildValidationErrorMap(String msg) {
		return buildStatusMap("1000003", "验证失败"+"("+msg+")");
	}
	
	public static Map<String, Object> buildNullErrorMap(String msg) {
		return buildStatusMap("1000004", "("+msg+")");
	}
	
	public static Map<String, Object> buildNullErrorResult(String msg) {
		return buildStatusMap("1000005", "结果集为空"+"("+msg+")");
	}

	public static Map<String, Object> buildDataError() {
		return buildStatusMap("1000006", "数据库操作异常");
	}
	
	public static Map<String, Object> buildTokenExpire() {
		return buildStatusMap("1000007", "token失效");
	}
	
	public static Map<String, Object> buildUserNull() {
		return buildStatusMap("1000008", "用户不存在");
	}
	
	public static Map<String, Object> buildPasswdError() {
		return buildStatusMap("1000009", "密码错误");
	}
	
	public static Map<String, Object> buildAreaError() {
		return buildStatusMap("1000010", "地区输入错误");
	}
	
	public static Map<String, Object> buildProductError() {
		return buildStatusMap("1000011", "产品类型错误");
	}
	
	public static Map<String, Object> buildDeleteError() {
		return buildStatusMap("1000012", "无法删除");
	}
	
	public static Map<String, Object> buildAddError(String msg) {
		return buildStatusMap("1000013", "添加失败"+"("+msg+")");
	}
	
	public static Map<String, Object> buildPermitError() {
		return buildStatusMap("1000014", "没有权限操作");
	}
	
	public static Map<String, Object> buildUserExistError() {
		return buildStatusMap("1000015", "用户已经存在");
	}
	
	public static Map<String, Object> buildOperationError() {
		return buildStatusMap("1000016", "操作标识错误");
	}
	
	public static Map<String, Object> buildItemExistError() {
		return buildStatusMap("1000017", "检测项目已经存在");
	}
	
	public static Map<String, Object> buildNumLowError() {
		return buildStatusMap("1000018", "剩余数量不足");
	}
	
	public static void main(String[] args) {
		Map<String, Object> m = JsonMapUtils.buildStatusMap("1000000", "成功");
		System.out.println(JsonConverter.convert(m));
	}
}
