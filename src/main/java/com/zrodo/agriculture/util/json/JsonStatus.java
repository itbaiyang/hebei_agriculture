package com.zrodo.agriculture.util.json;

public class JsonStatus {
	/**
	 * 定制的返回状态
	 * 
	 * @param statusCode
	 *            返回码
	 * @param statusMsg
	 *            返回消息
	 * @return
	 */
	public static String status(String statusCode, String statusMsg) {
		return JsonConverter.convert(JsonMapUtils.buildStatusMap(statusCode,
				statusMsg));
	}

	/**
	 * 操作成功
	 * 
	 * @return
	 */
	public static String success() {
		return JsonConverter.convert(JsonMapUtils.buildSuccessMap());
	}

	/**
	 * 操作失败
	 * 
	 * @return
	 */
	public static String failure() {
		return JsonConverter.convert(JsonMapUtils.buildFailureMap());
	}

	/**
	 * 网络错误
	 * 
	 * @return
	 */
	public static String networkError() {
		return JsonConverter.convert(JsonMapUtils.buildNetworkErrorMap());
	}
	
	/**
	 * 验证失败
	 * 
	 * @return
	 */
	public static String validationError(String msg) {
		return JsonConverter.convert(JsonMapUtils.buildValidationErrorMap(msg));
	}
	
	/**
	 * 参数为空
	 * 
	 * @return
	 */
	public static String paramNullError(String msg) {
		return JsonConverter.convert(JsonMapUtils.buildNullErrorMap(msg));
	}
	
	/**
	 * 结果集为空
	 * 
	 * @return
	 */
	public static String resultNullError(String msg) {
		return JsonConverter.convert(JsonMapUtils.buildNullErrorResult(msg));
	}
	
	/**
	 * 数据库操作异常
	 * 
	 * @return
	 */
	public static String DataError() {
		return JsonConverter.convert(JsonMapUtils.buildDataError());
	}
	
	/**
	 * token失效
	 * 
	 * @return
	 */
	public static String tokenExpire() {
		return JsonConverter.convert(JsonMapUtils.buildTokenExpire());
	}
	
	/**
	 * 用户不存在
	 * 
	 * @return
	 */
	public static String userNull() {
		return JsonConverter.convert(JsonMapUtils.buildUserNull());
	}
	
	/**
	 * 密码错误
	 * 
	 * @return
	 */
	public static String passwdError() {
		return JsonConverter.convert(JsonMapUtils.buildPasswdError());
	}
	
	/**
	 * url地区错误
	 * 
	 * @return
	 */
	public static String areaError() {
		return JsonConverter.convert(JsonMapUtils.buildAreaError());
	}
	
	/**
	 * 产品类型错误
	 * 
	 * @return
	 */
	public static String productError(){
		return JsonConverter.convert(JsonMapUtils.buildProductError());
	}
	
	/**
	 * 无法删除
	 * 
	 * @return
	 */
	public static String deleteError(){
		return JsonConverter.convert(JsonMapUtils.buildDeleteError());
	}
	
	/**
	 * 添加失败
	 * 
	 * @return
	 */
	public static String addError(String msg){
		return JsonConverter.convert(JsonMapUtils.buildAddError(msg));
	}
	
	/**
	 * 没有权限操作
	 * 
	 * @return
	 */
	public static String noPermit(){
		return JsonConverter.convert(JsonMapUtils.buildPermitError());
	}
	
	/**
	 * 用户已经存在
	 * 
	 * @return
	 */
	public static String userExistError(){
		return JsonConverter.convert(JsonMapUtils.buildUserExistError());
	}
	
	/**
	 * 操作标识错误
	 * 
	 * @return
	 */
	public static String operationError(){
		return JsonConverter.convert(JsonMapUtils.buildOperationError());
	}
	
	/**
	 * 检测项目已经存在
	 * 
	 * @return
	 */
	public static String itemExistError(){
		return JsonConverter.convert(JsonMapUtils.buildItemExistError());
	}
	
	/**
	 * 剩余数量不足
	 * 
	 * @return
	 */
	public static String numLowError(){
		return JsonConverter.convert(JsonMapUtils.buildNumLowError());
	}
	
}
