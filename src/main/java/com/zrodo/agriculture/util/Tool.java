package com.zrodo.agriculture.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.zrodo.agriculture.util.json.JsonMapUtils;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.domain.Page;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Clob;
import java.text.*;
import java.util.*;

public final class Tool {
	static int fourNum = 0;

	public synchronized static int getFournum() {
		fourNum++;
		if (fourNum == 1000) {
			fourNum = 1;
		}
		return fourNum;
	}

	/**
	 * 生成主键ID
	 * 
	 * @return
	 * @throws Exception
	 * @author kevin.xia
	 */
	public static String createPramaryKey() throws Exception {
		StringBuffer result = new StringBuffer("");
		String fourStr = numberFormatI(getFournum());
		String dateStr = dateToDateS(new Date());
		result.append(dateStr);
		result.append(fourStr);
		return result.toString();
	}

	static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	// 将iSource转为长度为iArrayLen的byte数组，字节数组的低位是整型的低字节位
	public static byte[] toByteArray(int iSource, int iArrayLen) {
		byte[] bLocalArr = new byte[iArrayLen];
		for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
			bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);

		}
		return bLocalArr;
	}

	public static byte[] int2byte(int n) {
		byte b[] = new byte[4];
		b[0] = (byte) (n >> 24);
		b[1] = (byte) (n >> 16);
		b[2] = (byte) (n >> 8);
		b[3] = (byte) n;
		return b;
	}

	/**
	 * 得到一个唯一标识的UUID
	 * 
	 * @return
	 * @author kevin.xia
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	/**
	 * 验证字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotNullOrEmpty(String s) {
		if (null == s) {
			return false;
		}
		if (s.trim().equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符是否英文半角字符或标点 32 空格 33-47 标点 48-57 0~9 58-64 标点 65-90 A~Z 91-96 标点
	 * 97-122 a~z 123-126 标点
	 * 
	 * @param c
	 * @return
	 * @author kevin.xia
	 */
	public static boolean isBjChar(char c) {
		int i = (int) c;
		return i >= 32 && i <= 126;
	}

	/**
	 * 判断字符是否全角字符或标点 全角字符 - 65248 = 半角字符 全角空格例外
	 * 
	 * @param c
	 * @return
	 * @author kevin.xia
	 */
	public static boolean isQjChar(char c) {
		if (c == '\u3000')
			return true;

		int i = (int) c - 65248;
		if (i < 32)
			return false;
		return isBjChar((char) i);
	}

	/**
	 * 字符型转换整型
	 * 
	 * @param s
	 * @return
	 */
	public static Integer String2Integer(String s) {
		if (s == null || "".equals(s))
			return Integer.valueOf(-1);
		else
			return Integer.valueOf(s);
	}

	/**
	 * 判断输入的字符串是否为null,如果为null，则将输入字符串用""替换。
	 * 
	 * @param nvlString
	 *            输入字符串
	 * @return
	 */
	public static String nvl(String nvlString) {
		return nvlString == null ? "" : nvlString;
	}

	/**
	 * 判断输入的对象是否为null,如果为null，则将输入字符串用""替换。
	 * 
	 * @param nvlString
	 *            输入对象
	 * @return
	 * @author kevin.xia
	 */
	public static String nvl(Object nvlString) {
		return nvlString == null ? "" : nvlString.toString();
	}

	/**
	 * 判断输入的字符串是否为null,如果为null，则将输入字符串用缺省值字符串替换。
	 * 
	 * @param nvlString
	 *            输入字符串
	 * @param defaultValue
	 *            缺省值字符串
	 * @return
	 */
	public static String nvl(String nvlString, String defaultValue) {
		return nvlString == null ? defaultValue : nvlString;
	}

	/**
	 * 判断输入的对象是否为null,如果为null，则将输入字符串用缺省值字符串替换。
	 * 
	 * @param nvlString
	 *            输入对象
	 * @param defaultValue
	 *            缺省值字符串
	 * @return
	 * @author kevin.xia
	 */
	public static String nvl(Object nvlString, String defaultValue) {
		return nvlString == null ? defaultValue : nvlString.toString();
	}

	/**
	 * 将字符串中的全角字符转换为半角
	 * 
	 * @param s
	 * @return
	 * @author kevin.xia
	 */
	public static String toBj(String s) {
		if (s == null || s.equals(""))
			return s;

		StringBuffer sb = new StringBuffer(s.length());
		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == '\u3000')
				sb.append('\u0020');
			else if (isQjChar(c))
				sb.append((char) ((int) c - 65248));
			else
				sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 将字符串中的半角字符转换为全角
	 * 
	 * @param s
	 * @return
	 * @author kevin.xia
	 */
	public static String toQj(String s) {
		if (s == null || s.equals(""))
			return s;

		StringBuffer sb = new StringBuffer(s.length());
		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == '\u0020')
				sb.append('\u3000');
			else if (isBjChar(c))
				sb.append((char) ((int) c + 65248));
			else
				sb.append(c);
		}
		return sb.toString();
	}

	public static boolean isHanzi(String str) {
		if (str.getBytes().length == str.length()) {
			System.out.println("无汉字");
			return false;
		} else {
			System.out.println("有汉字");
			return true;
		}

	}

	/**
	 * MD5加密
	 * 
	 * @param s
	 * @return
	 * @author kevin.xia
	 */
	public final static String MD5(String s) {
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(s.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();

			char str[] = new char[md.length * 2];
			for (int i = 0, k = 0; i < md.length; i++) {
				str[k++] = hexDigits[md[i] >>> 4 & 0xf];
				str[k++] = hexDigits[md[i] & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 四舍五入保留两位小数
	 * 
	 * @param num
	 * @return
	 * @author kevin.xia
	 */
	public static double numberRound(double num) {
		if (num > 0) {
			num += 0.0000001;
		} else {
			num -= 0.0000001;
		}
		BigDecimal b = new BigDecimal(num);
		double f = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f;
		/*
		 * long l=(long)((num+0.005)*100); return l/100.0;
		 */
	}

	/**
	 * 五舍六入保留一位小数
	 * 
	 * @param num
	 * @return
	 * @author kevin.xia
	 */
	public static double numberFiveToSix(double num) {
		if (num > 0) {
			num = num + 0.0000001 - 0.01;
		} else {
			num = num - 0.0000001 + 0.01;
		}
		BigDecimal b = new BigDecimal(num);
		double f = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f;

	}

	/**
	 * 字符串转换为decimal
	 * 
	 * @param num
	 * @return
	 * @author kevin.xia
	 */
	public static BigDecimal stringToDecimal(String num) {
		DecimalFormat df = new DecimalFormat("#.00");
		BigDecimal b = new BigDecimal(df.format(Double.parseDouble(num)));
		return b;
	}

	/**
	 * 数字左补0，总长度length
	 *
	 * @param num
	 * @return
	 * @author kevin.xia
	 */
	public static String numberFormat(int num, int length) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		StringBuffer format = new StringBuffer("");
		for (int i = 0; i < length; i++) {
			format.append("0");
		}
		df.applyPattern(format.toString());
		return df.format(num);
	}

	/**
	 * 数字格式A 格式：整数部分 + 小数点 + 小数部分
	 * 整数部分7位，不足7位的，左面补0，补足7位。小数部分2位，不足2位的，右面补0，补足2位。小数点占1位。 例：
	 * 0456734.35；0456734.20； 0456734.00；0000000.00
	 *
	 * @param num
	 * @return
	 * @throws Exception
	 * @author kevin.xia
	 */
	public static String numberFormatA(double num) throws Exception {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("0000000.00");
		return df.format(num);
	}

	/**
	 * 数字格式B 格式：整数部分 + 小数点 + 小数部分 整数部分m位，小数部分2位，不足2位的，右面补0，补足2位。小数点占1位。 例：
	 * 56734.35；4.20； 6734.00;
	 *
	 * @param num
	 * @return
	 * @throws Exception
	 * @author kevin.xia
	 */
	public static String numberFormatB(double num) throws Exception {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("0.00");
		return df.format(num);
	}

	/**
	 * 数字格式D 格式：整数部分 整数部分4位，不足4位的，左面补0，补足4位。 例： 034； 333；
	 *
	 * @param num
	 * @return
	 * @throws Exception
	 * @author kevin.xia
	 */
	public static String numberFormatD(int num) throws Exception {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("0000");
		return df.format(num);
	}

	/**
	 * 数字格式I 格式：整数部分 整数部分3位，不足3位的，左面补0，补足3位。 例： 033； 333；
	 *
	 * @param num
	 * @return
	 * @throws Exception
	 * @author kevin.xia
	 */
	public static String numberFormatI(int num) throws Exception {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("000");
		return df.format(num);
	}

	/**
	 * 数字格式E 格式：整数部分 整数部分8位，不足8位的，左面补0，补足3位。 例： 00000033； 04457333；
	 *
	 * @param num
	 * @return
	 * @throws Exception
	 * @author kevin.xia
	 */
	public static String numberFormatE(int num) throws Exception {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("00000000");
		return df.format(num);
	}

	/**
	 * 格式化数值输出 如果是整数,小数部分补两个零. 如是是一位小数,小数部分补一个零. 其它保留五位小数,整数部分原样输出.
	 *
	 * @param num
	 * @return
	 * @throws Exception
	 * @author kevin.xia
	 */
	public static String numberFormatTwo(double num) throws Exception {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("#0.00###");
		return df.format(num);
	}

	/**
	 * 产生指定数量的空格字符串
	 *
	 * @param number
	 * @return
	 * @author kevin.xia
	 */
	public static String spaceString(int number) {
		if (number <= 0) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < number; i++) {
				sb.append(" ");
			}
			return sb.toString();
		}
	}

	/**
	 * 产生指定总数量右补空格的字符串
	 *
	 * @return
	 * @author kevin.xia
	 */
	public static String spaceToRight(String str, int total) {
		int lenth = str.length();
		int number = total - lenth;
		if (number < 0) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer(str);
			for (int i = 0; i < number; i++) {
				sb.append(" ");
			}
			return sb.toString();
		}
	}

	/**
	 * 产生指定总数量右补0的字符串
	 *
	 * @return
	 * @author kevin.xia
	 */
	public static String zeroToRight(String str, int total) {
		int lenth = str.length();
		int number = total - lenth;
		if (number < 0) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer(str);
			for (int i = 0; i < number; i++) {
				sb.append("0");
			}
			return sb.toString();
		}
	}

	/**
	 * 产生指定总数量左补0的字符串
	 *
	 * @return
	 * @author kevin.xia
	 */
	public static String zeroToLeft(String str, int total) {
		int lenth = str.length();
		int number = total - lenth;
		if (number < 0) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < number; i++) {
				sb.append("0");
			}
			sb.append(str);
			return sb.toString();
		}
	}

	/**
	 * 把指定数值按指定位数(8),保存两位小数,不足位数前补零,超出指定位数则截取指定位数
	 *
	 * @param num
	 * @return
	 * @author kevin.xia
	 */
	public static String formatDouble(double num) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("0.00");
		String value = df.format(num);
		if (value.length() >= 8) {
			value = value.substring(0, 8);
		} else {
			value = spaceString(8 - value.length()) + value;
		}
		return value;
	}

	/**
	 * 字符串日期时间yyyy-MM-dd HH:mm:ss转换成字符串日期YYYYMMDD
	 *
	 * @param string
	 * @return
	 * @throws Exception
	 * @author kevin.xia
	 */
	public static String getYYYYMMDD(String string) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(string.substring(0, 4)).append(string.substring(5, 7))
				.append(string.substring(8, 10));
		return sb.toString();
	}

	/**
	 * 字符串转换成日期带格式化字符串
	 *
	 * @param string
	 *            输入的字符串
	 * @param dateStyle
	 *            指定的格式
	 * @return
	 * @throws ParseException
	 * @author kevin.xia
	 */
	public static Date stringToDate(String string, String dateStyle)
			throws ParseException {
		SimpleDateFormat format = getSDF4Date(dateStyle);
		return format.parse(string);
	}

	/**
	 * 字符串转换成日期带格式化字符串
	 *
	 * @param string
	 *            输入的字符串
	 * @param dateStyle
	 *            指定的格式
	 * @return
	 * @throws ParseException
	 * @author kevin.xia
	 */
	public static Date stringToDateTime(String string, String dateStyle)
			throws ParseException {
		SimpleDateFormat format = getSDF4DateTime(dateStyle);
		return format.parse(string);
	}

	/**
	 * 字符串转换成日期
	 *
	 * @param string
	 *            格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 * @author kevin.xia
	 */
	public static Date stringToDate(String string) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(string);
	}

	/**
	 * 字符串转换成日期
	 *
	 * @param string
	 *            格式：yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 * @author kevin.xia
	 */
	public static Date stringToDate10(String string) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(string);
	}

	/**
	 * 字符串转换成日期(带毫秒)
	 *
	 * @param string
	 * @return
	 * @throws ParseException
	 * @author kevin.xia
	 */
	public static Date stringToDateS(String string) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return format.parse(string);
	}

	/**
	 * 日期转换成字符串(带毫秒) yyyyMMddHHmmsss
	 *
	 * @return
	 * @throws ParseException
	 * @author kevin.xia
	 */
	public static String dateToDateS(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(date);
	}

	/**
	 * 日期转换成字符串MMdd
	 *
	 * @return
	 * @throws ParseException
	 * @author kevin.xia
	 */
	public static String dateToStringMMDD(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat("MMdd");
		return format.format(date);
	}

	/**
	 * 带S日期转换成不带S
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 * @author kevin.xia
	 */
	public static Date dateSToDate(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = format.format(date).substring(0, 18);
		return format.parse(strDate);
	}

	/**
	 * 日期转换成字符串yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 * @author kevin.xia
	 */
	public static String dateToString(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 日期按指定格式转换成字符串
	 *
	 * @param date
	 * @param format
	 * @return
	 * @author kevin.xia
	 */
	public static String dateToString(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 日期时间转换成日期
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 * @author kevin.xia
	 */
	public static Date dateTimeToDate(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = format.format(date).substring(0, 10);
		format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(strDate);
	}

	/**
	 * 根据日期计算年龄
	 *
	 * @param birthday
	 * @return
	 * @author kevin.xia
	 */
	public static Integer dateToAge(Date birthday) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthday);
		int year = calendar.get(calendar.YEAR);
		calendar.setTime(new Date());
		return calendar.get(calendar.YEAR) - year;
	}

	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 *
	 * @author patriotlml
	 *            origin, 原始字符串
	 * @return String, 返回的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String subString(String origin, int len)
			throws UnsupportedEncodingException {
		if (origin == null || origin.equals("")) {
			return "";
		}
		byte[] strByte = new byte[len];
		if (len > origin.getBytes("UTF-8").length) {
			return origin;
		}
		System.arraycopy(origin.getBytes("UTF-8"), 0, strByte, 0, len);
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int) strByte[i];
			if (value < 0) {
				count++;
			}
		}
		if (count % 2 != 0) {
			// len = (len == 1) ? ++len : --len;
			len--;
		}
		return new String(strByte, 0, len);
	}

	/**
	 * 格式化字符串为html格式 如 空格转换为&nbsp；\n 转换为<br/>
	 *
	 * @param str
	 * @return
	 * @author kevin.xia
	 */
	public static String formatString(String str) {
		if (str == null)
			return str;
		return str.replaceAll(" ", "&nbsp;").replaceAll("\r\n|\r|\n", "<br />");
	}

	/**
	 * 判断是否是数字
	 *
	 * @param s
	 * @return
	 * @author kevin.xia
	 */
	public static boolean isNumData(String s) {
		boolean tag = false;
		for (int j = 0; j < s.length(); j++) {
			if (!(s.charAt(j) >= 48 && s.charAt(j) <= 57)) {
				tag = false;
			} else {
				tag = true;
			}
		}
		return tag;
	}

	/**
	 * 判断传入参数是否为NUll,不是返回Long.
	 *
	 * @param str
	 *            传入参数
	 * @return Long
	 */
	public static Long stringToLong(String str) {
		if (str == null) {
			return 0L;
		} else {
			return Long.valueOf(str);
		}
	}

	/**
	 * 判断传入参数是否为NUll,不是返回Date.
	 *
	 *            传入参数
	 * @return Long
	 */
	public static String dateSubString(String strDate) {
		if (strDate.trim().length() == 0) {
			return "";
		} else {
			if (strDate.trim().length() < 19) {
				return strDate.trim();
			} else {
				return strDate.trim().substring(0, 19);
			}

		}
	}

	/**
	 * 日期格式样式。可输入如下值：
	 * <p>
	 * "-"：　　yyyy-MM-dd；
	 * <p>
	 * "/"：　　yyyy/MM/dd。
	 * <p>
	 * "SC"：　　中国式日期格式。
	 * <p>
	 * "US"：　　美国式日期格式。
	 * <p>
	 * ""为默认格式。
	 *
	 * @param dateStyle
	 *            日期格式样式
	 * @return SimpleDateFormat
	 */
	private static SimpleDateFormat getSDF4Date(String dateStyle) {
		SimpleDateFormat sdf = null;
		if (dateStyle == null)
			dateStyle = "";
		if (dateStyle.equals("-")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else if (dateStyle.equals("/")) {
			sdf = new SimpleDateFormat("yyyy/MM/dd");
		} else if (dateStyle.equals("SC")) {
			sdf = new SimpleDateFormat("yyyy年MM月dd日");
		} else if (dateStyle.equals("US")) {
			sdf = new SimpleDateFormat("MMM dd,yyyy", Locale.US);
		} else if (dateStyle.equals("")) {
			sdf = new SimpleDateFormat();
		} else {
			// 自定义日期格式
			sdf = new SimpleDateFormat(dateStyle);
		}
		return sdf;
	}

	/**
	 * 日期格式样式。可输入如下值：
	 * <p>
	 * "-"：　　yyyy-MM-dd；
	 * <p>
	 * "/"：　　yyyy/MM/dd。
	 * <p>
	 * "SC"：　　中国式日期格式。
	 * <p>
	 * "US"：　　美国式日期格式。
	 * <p>
	 * ""为默认格式。
	 *
	 * @param dateStyle
	 *            日期格式样式
	 * @return SimpleDateFormat
	 */
	private static SimpleDateFormat getSDF4DateTime(String dateStyle) {
		SimpleDateFormat sdf = null;
		if (dateStyle == null)
			dateStyle = "";
		if (dateStyle.equals("-")) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if (dateStyle.equals("/")) {
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		} else if (dateStyle.equals("SC")) {
			sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		} else if (dateStyle.equals("US")) {
			sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss ", Locale.US);
		} else if (dateStyle.equals("")) {
			sdf = new SimpleDateFormat();
		} else {
			// 自定义日期格式
			sdf = new SimpleDateFormat(dateStyle);
		}
		return sdf;
	}

	/**
	 * 将数据库中的date对象转换为yyyy-mm-dd的字符串
	 *
	 * @param obj
	 * @return
	 * @author kevin.xia
	 */
	public static String date2str(Object obj) {
		String dateStr = "";
		if (obj != null) {
			dateStr = obj.toString();
			if (dateStr != null) {
				if (dateStr.length() > 10)
					dateStr = dateStr.substring(0, 10);
			}
		}
		return dateStr;
	}

	/**
	 * Clob转换为String
	 *
	 * @param clob
	 * @return
	 * @author kevin.xia
	 */
	public static String clob2string(Clob clob) {
		if (clob == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer(65535); // 64K
		Reader clobStream = null;
		try {
			clobStream = clob.getCharacterStream();
			char[] b = new char[60000];// 每次获取60K
			int i = 0;
			while ((i = clobStream.read(b)) != -1) {
				sb.append(b, 0, i);
			}
		} catch (Exception ex) {
			sb = null;
		} finally {
			try {
				if (clobStream != null)
					clobStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (sb == null)
			return null;
		else
			return sb.toString();
	}

	/**
	 * Capitalize 首字母转换为大写
	 *
	 * @param s
	 * @return
	 */
	public static String capitalize(String s) {
		char ch[];
		ch = s.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		String newString = new String(ch);
		return newString;
	}

	public static String getRandomSixNum() {
		Random ran = new Random();
		int n = Math.abs(ran.nextInt(900000) + 100000);
		return n + "";
	}

	/**
	 * 得到本月第一天的yyyy-MM-dd格式字符串
	 *
	 * @return
	 */
	public static String getFirstDayStrOfMonth() {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return dateToString(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 得到本月第一天的date类型数据，并且时间是0点0分0秒000毫秒
	 *
	 * @return
	 */
	public static Date getFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		// dateToString(cal.getTime(),"yyyy-MM-dd HH:mm:ss.SSS");
		return cal.getTime();
	}

	/**
	 * 得到与本月相隔N个月的月份第一天的yyyy-MM-dd格式字符串
	 *
	 * @param relativeNum
	 * @return
	 */
	public static String getFirstDayStrOfMonthRelativeThisMonth(int relativeNum) {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cal.add(Calendar.MONTH, relativeNum);
		return dateToString(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 得到与本月相隔N个月的月份第一天的date类型数据，并且时间是0点0分0秒000毫秒
	 *
	 * @param relativeNum
	 * @return
	 */
	public static Date getFirstDayOfMonthRelativeThisMonth(int relativeNum) {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, relativeNum);
		return cal.getTime();
	}

	/**
	 * 得到本月最后一天的yyyy-MM-dd格式字符串
	 *
	 * @return
	 */
	public static String getLastDayStrOfMonth() {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return dateToString(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 得到本月最后一天的date类型数据，并且时间是0点0分0秒000毫秒
	 *
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 得到与本月相隔N个月的月份最后一天的yyyy-MM-dd格式字符串
	 *
	 * @param relativeNum
	 * @return
	 */
	public static String getLastDayStrOfMonthRelativeThisMonth(int relativeNum) {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cal.add(Calendar.MONTH, relativeNum + 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return dateToString(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 得到与本月相隔N个月的月份最后一天的date类型数据，并且时间是0点0分0秒000毫秒
	 *
	 * @param relativeNum
	 * @return
	 */
	public static Date getLastDayOfMonthRelativeThisMonth(int relativeNum) {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, relativeNum + 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 得到今天的起点时间即今天的0点
	 *
	 * @return
	 */
	public static Date getTodayStartTime() {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 得到今天的起点时间即今天的0点yyyy-MM-dd格式字符串
	 *
	 * @return
	 */
	public static String getTodayStartTimeStr() {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		return dateToString(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 得到和今天间隔N天的那一天的起点时间
	 *
	 * @param relativeNum
	 * @return
	 */
	public static Date getRelativeTodayStartTime(int relativeNum) {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, relativeNum);
		return cal.getTime();
	}

	/**
	 * 得到与传入时间间隔N天的日期date，时分秒保持一致
	 *
	 * @param date
	 * @param relativeNum
	 * @return
	 */
	public static Date getRelativeDayOfDate(Date date, int relativeNum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, relativeNum);
		return cal.getTime();
	}

	/**
	 * 得到与传入时间间隔N个月的日期date，时分秒保持一致
	 *
	 * @param date
	 * @param relativeNum
	 * @return
	 */
	public static Date getRelativeMonthOfDate(Date date, int relativeNum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, relativeNum);
		return cal.getTime();
	}

	/**
	 * 得到本月的第一天
	 *
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		return dateToString(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 得到本月的最后一天
	 *
	 * @return
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateToString(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 将传入的字符串转成固定格式的字符串 格式：4位传入字符串的长度+字符串
	 *
	 * @param str
	 * @param isAll
	 *            是否计算本身4位的长度
	 * @return
	 * @author kevin.xia
	 */
	public static String getSocketFormatStr(String str, boolean isAll)
			throws Exception {
		String result = "";
		int length = 0;
		if (isAll) {
			length = str.getBytes().length + 4;
		} else {
			length = str.getBytes().length;
		}
		String head = Tool.numberFormatD(length);
		result = head + str;
		return result;
	}

	/**
	 * 得到与传入String时间间隔N天的日期date数据，格式都是"yyyy—MM-dd"
	 *
	 * @param date
	 * @param relativeNum
	 * @return
	 * @throws ParseException
	 */
	public static Date getRelativeDayOfStrDate(String date, int relativeNum)
			throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(stringToDate10(date));
		cal.add(Calendar.DAY_OF_MONTH, relativeNum);
		return cal.getTime();
	}

	public static String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getMAC() {
		String mac = null;
		try {
			Process pro = Runtime.getRuntime().exec("cmd.exe /c ipconfig/all");

			InputStream is = pro.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String message = br.readLine();

			int index = -1;
			while (message != null) {
				if ((index = message.indexOf("Physical Address")) > 0) {
					mac = message.substring(index + 36).trim();
					break;
				}
				message = br.readLine();
			}
			System.out.println(mac);
			br.close();
			pro.destroy();
		} catch (IOException e) {
			System.out.println("Can't get mac address!");
			return null;
		}
		return mac;
	}

	public static Cookie getLoginCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		System.out.println("遍历cookie");
		Cookie cookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				System.out.println("cookieName:" + cookies[i].getName()
						+ ",cookiesValue:" + cookies[i].getValue());
				if (cookies[i].getName().equals("CERLOGIN")
						&& !Tool.nvl(cookies[i].getValue()).equals("")) {
					System.out.println("找到登录cookie：cookieName:"
							+ cookies[i].getName() + ",cookiesValue:"
							+ cookies[i].getValue());
					cookie = cookies[i];
				}
			}
		}
		return cookie;
	}

	/**
	 * 设置返回页面参数
	 *
	 * @param statusCode
	 *            状态码：200、成功 300、失败 301、会话超时
	 * @param message
	 *            返回消息
	 * @param navTabId
	 *            设置为1：标示刷新页面
	 * @param callbackType
	 *            返回类型 ：closeCurrent、表示关闭页面 forward、表示跳转到新页面，要求forwardUrl有值
	 * @param forwardUrl
	 *            callbackType为forward时，新页面的地址
	 * @param request
	 */
	public static void setResponseMap(String statusCode, String message,
			String navTabId, String callbackType, String forwardUrl,
			HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("statusCode", statusCode);
		map.put("message", message);
		map.put("navTabId", navTabId);
		map.put("callbackType", callbackType);
		map.put("forwardUrl", forwardUrl);
		Object json = JSONObject.fromObject(map);
		request.setAttribute("json", json);
	}
	/**
	 * 设置返回页面参数
	 *
	 * @param statusCode
	 *            状态码：200、成功 300、失败 301、会话超时
	 * @param message
	 *            返回消息
	 * @param callbackType
	 *            返回类型 ：closeCurrent、表示关闭页面 forward、表示跳转到新页面，要求forwardUrl有值
	 * @param request
	 */
	public static void setResponseMap(String statusCode, String message,
			String callbackType,
			HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("statusCode", statusCode);
		map.put("message", message);
		map.put("callbackType", callbackType);
		Object json = JSONObject.fromObject(map);
		request.setAttribute("json", json);
	}

	/**
	 * 设置返回页面参数
	 *
	 * @param statusCode
	 *            状态码：200、成功 300、失败 301、会话超时
	 * @param message
	 *            返回消息
	 * @param request
	 */
	public static void setResponseMap(String statusCode, String message,
			HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("statusCode", statusCode);
		map.put("message", message);
		Object json = JSONObject.fromObject(map);
		request.setAttribute("json", json);
	}

	public static String invokeHttpUrl(String urlStr, HashMap paramMap) {
		URL url = null;
		HttpURLConnection httpConn = null;
		String sTotalString = "";
		InputStream is = null;
		OutputStream out = null;
		BufferedReader reader = null;
		try {
			url = new URL(urlStr);
			System.out.println("请求地址：" + urlStr);
			// 以post方式请求
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setRequestMethod("POST");
			out = httpConn.getOutputStream();
			Iterator its = paramMap.keySet().iterator();
			String tmpStr = "";
			while (its.hasNext()) {
				String keyName = (String) its.next();
				tmpStr += keyName + "=" + paramMap.get(keyName) + "&";
			}
			tmpStr = tmpStr.substring(0, tmpStr.length() - 1);
			System.out.println(tmpStr);
			out.write(tmpStr.getBytes());
			out.flush();
			out.close();

			// 获取响应代码
			int code = httpConn.getResponseCode();

			String msg = httpConn.getResponseMessage();
			System.out.println("msg:" + msg);
			// 读取响应内容
			String sCurrentLine = "";
			if (code == 200) {
				is = httpConn.getInputStream();

				reader = new BufferedReader(new InputStreamReader(is));
				while ((sCurrentLine = reader.readLine()) != null) {
					if (sCurrentLine.length() > 0) {
						sTotalString = sTotalString + sCurrentLine.trim();
					}
				}

				is.close();
				reader.close();
			} else {
				sTotalString = "远程服务器连接失败,错误代码:" + code;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpConn != null) {
				httpConn.disconnect();
			}
		}

		return sTotalString;
	}

	public static String convetJC(String jc1, String jc2) {
		int start = Integer.parseInt(jc1);
		int end = Integer.parseInt(jc2);
		StringBuffer sb = new StringBuffer("第");
		if (start < end) {
			for (int i = start; i <= end; i++) {
				sb.append(i).append(",");
			}
			sb = sb.delete(sb.length() - 1, sb.length());
			sb.append("节");
		} else {
			throw new DataException("开始节次不能大于结束节次！");
		}

		return sb.toString();
	}

	public static String conertXQ(String xq) {
		String xqStr = null;
		if (xq.equals("1")) {
			xqStr = "周一";
		} else if (xq.equals("2")) {
			xqStr = "周二";
		} else if (xq.equals("3")) {
			xqStr = "周三";
		} else if (xq.equals("4")) {
			xqStr = "周四";
		} else if (xq.equals("5")) {
			xqStr = "周五";
		} else if (xq.equals("6")) {
			xqStr = "周六";
		} else if (xq.equals("7")) {
			xqStr = "周日";
		} else {
			new DataException("星期索引超出范围");
		}
		return xqStr;
	}

	/**
	 * 判断周次是否是一周
	 *
	 * @param yzc
	 * @return
	 */
	public static boolean isOneWeek(String yzc) {
		// TODO Auto-generated method stub
		String ksz = yzc.substring(0, 2);
		String jsz = yzc.substring(3, 5);
		return ksz.equals(jsz);
	}

	public static void createFile(InputStream is, String fileName) {
		FileOutputStream fos = null;
		try {
			// 打开一个已存在文件的输出流
			fos = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// 将输入流is写入文件输出流fos中
		int ch = 0;
		try {
			while ((ch = is.read()) != -1) {
				fos.write(ch);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			// 关闭输入流等（略）
			try {
				fos.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/*
	 * 获得 大图 小图 中途
	 */
	public static String getImageAndSize(String img, String size) {
		String image = img.substring(0, img.length() - 4) + size
				+ img.substring(img.length() - 4, img.length());
		return image;
	}

	/*
	 * 获得图片地址的完整路径 url
	 */
	public static String getImageAdress(Properties props, String imageAdress) {

		return props.getProperty("imageurl") + imageAdress;

	}

	/*
	 * 获得图片地址的完整路径 path
	 */
	public static String getImageAdressPath(Properties props, String imageAdress) {

		return props.getProperty("imageabsolutepath") + imageAdress;

	}

	/**
	 * 下载方法
	 *
	 * @param response
	 * @param fileName
	 * @param name
	 * @param contentType
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void downloadFile(HttpServletResponse response,
			String fileName, String name, String contentType)
			throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		InputStream ins = new FileInputStream(fileName);
		BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
		OutputStream outs = response.getOutputStream();// 获取文件输出IO流
		BufferedOutputStream bouts = new BufferedOutputStream(outs);
		response.setContentType(contentType); // 通知客户端浏览器文件的类型。

		response.setHeader("Content-disposition", "attachment;filename="
				+ URLEncoder.encode(name, "UTF-8"));// 设置头部信息
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		// 开始向网络传输文件流
		while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
			bouts.write(buffer, 0, bytesRead);
		}
		bouts.flush();
		ins.close();
		bins.close();
		outs.close();
		bouts.close();
	}

	/**
	 * 文件file进行加密
	 *
	 * @param fileUrl
	 *            文件路径
	 * @param key
	 *            密码
	 * @throws Exception
	 */
	public static void encrypt(String fileUrl, String key) throws Exception {
		String fgf = System.getProperty("file.separator");
		File file = new File(fileUrl);
		String path = file.getPath();
		if (!file.exists()) {
			return;
		}
		int index = path.lastIndexOf(fgf);
		String destFile = path.substring(0, index) + fgf + "abc";
		File dest = new File(destFile);
		InputStream in = new FileInputStream(fileUrl);
		OutputStream out = new FileOutputStream(destFile);
		byte[] buffer = new byte[1024];
		int r;
		byte[] buffer2 = new byte[1024];
		while ((r = in.read(buffer)) > 0) {
			for (int i = 0; i < r; i++) {
				byte b = buffer[i];
				buffer2[i] = b == 255 ? 0 : ++b;
			}
			out.write(buffer2, 0, r);
			out.flush();
		}
		in.close();
		out.close();
		file.delete();
		dest.renameTo(new File(fileUrl));
		appendMethodA(fileUrl, key);
	}

	/**
	 *
	 * @param fileName
	 * @param content
	 *            密钥
	 */
	public static void appendMethodA(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解密
	 *
	 * @param fileUrl
	 *            源文件
	 * @param tempUrl
	 *            临时文件
	 *            密码长度
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String fileUrl, String tempUrl, int keyLength)
			throws Exception {
		File file = new File(fileUrl);
		if (!file.exists()) {
			return null;
		}
		File dest = new File(tempUrl);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		InputStream is = new FileInputStream(fileUrl);
		OutputStream out = new FileOutputStream(tempUrl);

		byte[] buffer = new byte[1024];
		byte[] buffer2 = new byte[1024];
		byte bMax = (byte) 255;
		long size = file.length() - keyLength;
		int mod = (int) (size % 1024);
		int div = (int) (size >> 10);
		int count = mod == 0 ? div : (div + 1);
		int k = 1, r;
		while ((k <= count && (r = is.read(buffer)) > 0)) {
			if (mod != 0 && k == count) {
				r = mod;
			}

			for (int i = 0; i < r; i++) {
				byte b = buffer[i];
				buffer2[i] = b == 0 ? bMax : --b;
			}
			out.write(buffer2, 0, r);
			k++;
		}
		out.close();
		is.close();
		return tempUrl;
	}

	/**
	 * 判断文件是否加密
	 *
	 * @param fileName
	 * @return
	 */
	public static String readFileLastByte(String fileName, int keyLength) {
		File file = new File(fileName);
		if (!file.exists())
			return null;
		StringBuffer str = new StringBuffer();
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "r");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			for (int i = keyLength; i >= 1; i--) {
				randomFile.seek(fileLength - i);
				str.append((char) randomFile.read());
			}
			randomFile.close();
			return str.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String GetImageStr(String url)
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = url;//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "";
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

	public static String savePictoServer(String base64String,String path,String imgName)throws Exception{
		  BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes1 = decoder.decodeBuffer(base64String);                  
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);    
        BufferedImage bi1 =ImageIO.read(bais); 
		  
		  String realPath = path;
		        
		        File dir=new File(realPath);
		        if(!dir.exists()){
		         dir.mkdirs();
		        }
		        String fileName=path+imgName+".jpg";
		        File w2 = new File(fileName);//可以是jpg,png,gif格式
		        if(w2.exists()) { 
		        	w2.delete(); 
		        } 
		        w2.createNewFile(); 
		        ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动   
		        
		        return fileName;
		 }
	

	/**
	 * base64加密
	 * @param str
	 * @return
	 * @author wangmin
	 * 2016年7月13日
	 */
    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    /**
     * base64解密
     * @param s
     * @return
     * @author wangmin
     * 2016年7月13日
     */
    public static String getStrFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
    
    /**
	  * 获取当年第一天日期
	  * @return
	  * @author wangmin
	  * 2016年7月26日
	  */
   public static String getCurrYearFirstDay(){ 
       Calendar calendar = Calendar.getInstance();
       int year = calendar.get(Calendar.YEAR);
       calendar.clear();
       calendar.set(Calendar.YEAR, year);
       Date currYearFirst = calendar.getTime();
       return dateToString(currYearFirst, "yyyy-MM-dd");
   }
    
   public static int getPageTotalCount(List<Map<String, String>> list){
	   
	   Integer count = null;
	   try
	   {
		   if(list != null && list.size() > 0)
		   {
			   Map<String, String> map = list.get(0);
			   Object str = map.get("sum_num");
			   count =(Integer) str;
		   }
		   else
		   {
			   count = 0;
		   }
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   
	   return count == null ? 0: count;
   }
   
   /**
    * 转json字符串
    * @param obj
    * @return
    * @throws Exception
    * @author wangmin
    * 2016年8月1日
    */
   public static String getJsonFromObect(Object obj) throws Exception{
	   ObjectMapper mapper = new ObjectMapper();
	   return mapper.writeValueAsString(obj);
   }
   
   /**
    * 分页对象转json字符串
    * @param page
    * @return
    * @throws Exception
    * @author wangmin
    * 2016年8月1日
    */
   public static String getPageSuccessStr(Page page) throws Exception{
		Map<String, Object> map = JsonMapUtils.buildSuccessMap();
		map.put("page", page);
		return getJsonFromObect(map);
   }
   
	public static String getMapProperty(String pName){
		 String result = null;
		 ResourceBundle rb = ResourceBundle.getBundle("mapkeyvalue");
		 result = rb.getString(pName);
	     return result;
	}
	
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
		"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
		"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
		"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
		"W", "X", "Y", "Z" };


	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
    }
	
	public static void main(String[] args){
		System.out.println(generateShortUuid());
	}
	
	public static String getTracecodeProperty(String pName){
		 String result = null;
		 ResourceBundle rb = ResourceBundle.getBundle("tracecode_to_basecode");
		 result = rb.getString(pName);
	     return result;
	}
	
	public static void listMapSort(List<Map<String, Object>> result123,final String sortKey){
		Comparator<Map<String, Object>> comparator = new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return (Integer)(o2.get(sortKey)) - (Integer)(o1.get(sortKey));
            }
        };
        Collections.sort(result123,comparator);
	}
	
	private static void pdfExtraction(String source,String target,int pageNum) throws Exception{
		//1：创建PDF读取对象
		PdfReader pr = new PdfReader(source);
		System.out.println("this document "+pr.getNumberOfPages()+" page");
		
		//2：将第page页转为提取，创建document对象
		Document doc = new Document(pr.getPageSize(pageNum));
		
		//3：通过PdfCopy转其单独存储
		PdfCopy copy = new PdfCopy(doc, new FileOutputStream(new File(target)));
		doc.open();
		doc.newPage();
		
		//4：获取第1页，装载到document中。
		PdfImportedPage page = copy.getImportedPage(pr,pageNum);
		copy.addPage(page);	
		
		//5：释放资源
		copy.close();
		doc.close();
		pr.close();
	}
	
	/**
	* 得到n位长度的随机数
	* @param n 随机数的长度
	* @return 返回 n位的随机整数
	*/
	public static int getRandomNumber(int n){
		int temp = 0;
		int min = (int) Math.pow(10, n-1);
		int max = (int) Math.pow(10, n);
		Random rand = new Random();
		while(true){
			temp = rand.nextInt(max);
			if(temp >= min)
			break;
		}
		return temp;
	}
}
