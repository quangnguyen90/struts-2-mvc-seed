package common.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.dao.UserDAO;
import dao.domain.User;
import dao.mapper.UserMapper;

public class ValidateUtil extends ActionSupport {

	public static final String REG_EMAIL = "^[\\x01-\\x20\\x23-\\x26\\x28-\\x2B\\x2D-\\x39\\x3D\\x3F\\x41-\\x5A\\x5E-\\x7F]+@([-\\w]+\\.)+[-\\w]+$";
	public static final String REG_EMAIL_POP3 = "^[\\x01-\\x20\\x23-\\x26\\x28-\\x2B\\x2D-\\x39\\x3D\\x3F\\x41-\\x5A\\x5E-\\x7F]+@([-\\w]+\\.)+[-\\w]+$";
	public static final String REG_ALPHA = "[a-zA-Z_]*";
	public static final String REG_ALPHA_SPACE = "[a-zA-Z_ ]*";
	public static final String REG_NUMERIC = "[0-9]*";
	public static final String REG_AMOUNT = "[0-9.,]*";
	public static final String REG_ALPHANUMERIC = "[a-zA-Z0-9_]*";
	public static final String REG_ALPHANUMERIC_FOR_CARDNAME = "[a-zA-Z0-9_. ]*"; // カード�??義用
	public static final String REG_CAHR = "[a-zA-Z0-9_\\-@\\. ]*";
	public static final String REG_ALPHANUMERIC_SPACE = "[a-zA-Z0-9_\\- ]*";
	public static final String REG_DATE_FORMAT = "^\\d{4}/\\d{2}/\\d{2}$";
	public static final String REG_DATE_TIME_FORMAT = "^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}$";
	// public static final String REG_URL_FORMAT =
	// "/^(((ht|f)tp(s?))\\:\\/\\/)([0-9a-zA-Z\\-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?$/";
	public static final String REG_URL_FORMAT = "^(((ht|f)tp(s?))://)([0-9a-zA-Z-]+.)+[a-zA-Z]{2,6}(:[0-9]+)?(\\S*)?$";
	// public static final String REG_URL_FORMAT =
	// "^(https?://)?([\\da-z.-]+).([a-z.]{2,6})([\\w .-]*)*/?$";
	// public static final String REG_NOT_SYMBOL = "^[^-/:\\[\\`{\\~]+$";
	public static final String REG_NOT_SYMBOL = "^[^〜�?＠＃＄％＾＆＊（）＿＋＜＞�?？!�?#$%&’()*+-.,/:;<=>?@^_`{|}~'\"]+$";
	public static final String REG_FLOAT_POINT_NUMERIC = "^((\\d{1,3}(,\\d{3})*)|\\d{1,})(.\\d{1,2})?$";
	public static final String REG_FORMAT_NUMERIC = "^((\\d{1,3}(,\\d{3})*)|\\d{1,})$";
	public static final String REG_SPACE_STRING = "^( )+$";
	private static final String NAME_STANDARD = "^[a-zA-Z0-9]{6,25}$";
	private static final String REG_DATE_FORMAT_DDMMYYYY = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

	User user = new User();
	Logger log = Logger.getLogger(ValidateUtil.class);

	private static final long serialVersionUID = 1L;

	public static boolean isEmpty(Double s) {
		try {
			if (s == null || s.doubleValue() <= 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean isEmpty(Integer s) {
		try {
			if (s == null || s.intValue() <= 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean isEmpty(String s) {
		try {
			if (s == null || s.toString().equals("")) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean isEmpty(BigDecimal b) {
		try {
			if (b == null || b.doubleValue() == 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean isEmpty(String[] str) {
		boolean b;
		if (str == null || str.length == 0) {
			b = true;
		} else {
			b = false;
		}
		return b;
	}

	public static boolean isEmpty(Date s) {
		try {
			if (s == null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean isNumeric(String str) {
		return MiscUtils.regMatch(REG_NUMERIC, str);
	}

	public static boolean isAmount(String str) {
		// return MiscUtils.regMatch(REG_AMOUNT,str);
		return MiscUtils.regMatch(REG_FLOAT_POINT_NUMERIC, str);
	}

	public static boolean isAlpha(String str) {
		return MiscUtils.regMatch(REG_ALPHA, str);
	}

	public static boolean isAlphaSpace(String str) {
		return MiscUtils.regMatch(REG_ALPHA_SPACE, str);
	}

	public static boolean isAlphanumeric(String str) {
		return MiscUtils.regMatch(REG_ALPHANUMERIC, str);
	}

	public static boolean isChar(String str) {
		return MiscUtils.regMatch(REG_CAHR, str);
	}

	public static boolean isAlphanumericSpace(String str) {
		return MiscUtils.regMatch(REG_ALPHANUMERIC_SPACE, str);
	}

	public static boolean isEmail(String str) {
		if (isEmpty(str)) {
			return true;
		}
		return MiscUtils.regMatch(REG_EMAIL, str);
	}

	public static boolean isCardLength(String a) {
		if (a == null) {
			return false;
		}
		if (a.length() == 15 || a.length() == 16 || a.length() == 14) {
			return true;
		}
		return false;
	}

	public static boolean isCardMonth(String a) {
		if (a == null) {
			return false;
		}
		try {
			if (Integer.parseInt(a) >= 1 && Integer.parseInt(a) <= 12) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isCardYear(String a) {
		if (a == null) {
			return false;
		}
		try {
			if (Integer.parseInt(a) >= 2000 && Integer.parseInt(a) <= 3000) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean isAmountDecimal(double amount, String curncy) {
		if (amount <= 0) {
			return true;
		}
		try {
			// 少数点以下を算出
			double dec = amount - (int) amount;
			// 日本円�?��?数�?��?�?�
			if (curncy.equals(Consts.CURRENCY_JPY) && dec > 0) {
				return false;
			}

		} catch (Exception e) {
		}
		return true;
	}

	public static boolean isTrue(Boolean bool) {
		if (bool == null || bool.booleanValue() == false) {
			return false;
		}
		return true;
	}

	public static boolean isValidLength(String a, int length) {
		if (a == null) {
			return true;
		}
		if (a.getBytes().length <= length) {
			return true;
		}
		return false;
	}

	public static boolean isAlphanumericForCardname(String str) {
		return MiscUtils.regMatch(REG_ALPHANUMERIC_FOR_CARDNAME, str)
				&& !MiscUtils.regMatch(REG_SPACE_STRING, str);
	}

	public static boolean isValidIDCheck(String name) {
		return isAlphaOrDigit(name);
	}

	public static boolean isValidPassword(String password) {
		return isAlphaOrDigit(password);
	}

	public static boolean isAlphaOrDigit(String str) {
		if (null == str) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (isAlphaOrDigit(ch) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAlphaOrDigit(char ch) {
		if ('A' <= ch && ch <= 'Z') {
			return true;
		}
		if ('a' <= ch && ch <= 'z') {
			return true;
		}
		if ('0' <= ch && ch <= '9') {
			return true;
		}
		return false;
	}

	/**
	 * Check the date format as YYYY/MM/DD
	 * 
	 * @param dateFormatString
	 * @return
	 */
	public static boolean isDateFormat(String dateFormatString) {
		// First check for the pattern
		if (!MiscUtils.regMatch(REG_DATE_FORMAT, dateFormatString))
			return false;

		// Parse the date parts to integers
		String[] parts = dateFormatString.split("/");
		int day = Integer.parseInt(parts[2], 10);
		int month = Integer.parseInt(parts[1], 10);
		int year = Integer.parseInt(parts[0], 10);

		// Check the ranges of month and year
		if (year < 1000 || year > 3000 || month == 0 || month > 12)
			return false;

		int[] monthLength = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		// Adjust for leap years
		if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
			monthLength[1] = 29;

		// Check the range of the day
		return (day > 0 && day <= monthLength[month - 1]);
	}

	/**
	 * Check the date format as YYYY/MM/DD
	 * 
	 * @param dateTimeFormatString
	 * @return
	 */
	public static boolean isDateTimeFormat(String dateTimeFormatString) {
		// First check for the pattern
		if (!MiscUtils.regMatch(REG_DATE_TIME_FORMAT, dateTimeFormatString))
			return false;

		// Parse the date parts to integers
		String[] parts = dateTimeFormatString.split(" ");
		String date = parts[0];
		String time = parts[1];

		// Check the validate of date
		String[] dates = date.split("/");

		int day = Integer.parseInt(dates[2], 10);
		int month = Integer.parseInt(dates[1], 10);
		int year = Integer.parseInt(dates[0], 10);

		// Check the ranges of month and year
		if (year < 1000 || year > 3000 || month == 0 || month > 12)
			return false;

		int[] monthLength = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		// Adjust for leap years
		if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
			monthLength[1] = 29;

		// Check the range of the day
		if (!(day > 0 && day <= monthLength[month - 1])) {
			return false;
		}

		// Check validate time
		String[] times = time.split(":");
		int hour = Integer.parseInt(times[0]);
		int minute = Integer.parseInt(times[1]);
		return (0 <= hour && hour <= 23 && 0 <= minute && minute <= 59);
	}

	/**
	 * check format url
	 * 
	 * @param url
	 *            (String)
	 * @return true or false
	 */
	public static boolean isURL(String url) {
		if (url == null || "".equals(url)) {
			return false;
		}
		Pattern pattern = Pattern.compile(REG_URL_FORMAT);
		Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}

	/**
	 * Check 2 strings as the range of date format yyyy/MM/dd
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static boolean isCheckBetweenDates(String firstDate,
			String secondDate) {
		boolean isOK = true;

		for (;;) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date1 = null;
			Date date2 = null;

			try {
				date1 = (Date) dateFormat.parse(firstDate);
				date2 = (Date) dateFormat.parse(secondDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isOK = false;
				break;
			}
			if (date1 != null && date2 != null) {
				if (date1.after(date2)) // The first date is less than second
										// one
				{
					isOK = false;
					break;
				}
			}

			break;
		}
		return isOK;
	}

	public static boolean isSymbol(String str) {
		return !MiscUtils.regMatch(REG_NOT_SYMBOL, str)
				|| MiscUtils.regMatch(REG_SPACE_STRING, str)
				|| str.contains("[") || str.contains("]");
	}

	public static boolean isFloatPointNumeric(String str) {
		return MiscUtils.regMatch(REG_FLOAT_POINT_NUMERIC, str);
	}

	public static boolean isFormatNumeric(String str) {
		return MiscUtils.regMatch(REG_FORMAT_NUMERIC, str);
	}

	// ======================================================================================================
	// Length of Password [6-50]
	public static boolean validateLengthOfPassword(String Password) {
		try {
			if (Password.trim().toLowerCase().length() >= 6
					&& Password.trim().toLowerCase().length() <= 50) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	// Length of full name is from 6 to 50 characters.
	public static boolean validateLengthOfFullname(String Fullname) {
		try {
			if (Fullname.trim().toLowerCase().length() >= 6
					&& Fullname.trim().toLowerCase().length() <= 50) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	//  Length of address is from 10 to 100 characters.
	public static boolean validateLengthOfAddress(String Address) {
		try {
			if (Address.trim().toLowerCase().length() >= 10
					&& Address.toLowerCase().trim().length() <= 100) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================

	// BPassword and Confirm Password is the same
	public static boolean validateConfirmPassword(String password,
			String confirmPassword) {
		try {
			if (password.trim().equalsIgnoreCase(confirmPassword.trim()) == true) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================

	/**
	 * VALIDATE NAME STANDARD just allow input [a-z] / [A-Z] / [0-9]
	 * */
	// ======================================================================================================

	public static boolean validateNameStandard(String str) {
		try {
			if (MiscUtils.regMatch(NAME_STANDARD, str) == true)
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;

	}

	// ======================================================================================================================
	/**
	 * Check the date format as dd/MM/yyyy
	 * 
	 * @param dateFormatString
	 * @return
	 */

	public static boolean validateDateFormat(String dateFormatString) {
		// First check for the pattern
		if (!MiscUtils.regMatch(REG_DATE_FORMAT_DDMMYYYY, dateFormatString))
			return false;

		// Parse the date parts to integers
		String[] parts = dateFormatString.split("/");
		int day = Integer.parseInt(parts[0], 10);
		int month = Integer.parseInt(parts[1], 10);
		int year = Integer.parseInt(parts[2], 10);

		// Check the ranges of month and year
		if (year < 1000 || year > 3000 || month == 0 || month > 12)
			return false;

		int[] monthLength = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		// Adjust for leap years
		if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
			monthLength[1] = 29;

		// Check the range of the day
		return (day > 0 && day <= monthLength[month - 1]);
	}

	// ======================================================================================================================
	// Email must follow standard AccountName@DomainName (AccountName can be
	// different with User ID)
	public static boolean validateEmailStandard(String email) {
		if (validateEmptyString(email) == false) {
			return MiscUtils.regMatch(REG_EMAIL_POP3, email);
		}
		return false;
	}

	// ======================================================================================================
	// Validate empty string
	public static boolean validateEmptyString(String str) {
		try {
			if (str.trim() == null || str.trim().toString().equals("")) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	// Length of company name isn't from 6 to 200 characters.
	public static boolean validateLengthOfCompanyName(String companyname) {
		try {
			if (companyname.trim().length() >= 6
					&& companyname.trim().length() <= 200) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	// Length of tax ID is from 6 to 50 characters.
	public static boolean validateLengthOfTaxID(String taxid) {
		try {
			if (taxid.trim().length() >= 6 && taxid.trim().length() <= 15) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	// userName is only 6-25 characters
	public static boolean validateLengthOfUserName(String userName) {
		try {
			if (userName.trim().length() >= 6 && userName.trim().length() <= 25) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	public static boolean validateLengthOfPhoneNumber(String phoneNumber) {
		try {
			if (phoneNumber.trim().length() >= 10
					&& phoneNumber.trim().length() <= 20) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	// MD5 Encryption
	private static String convertedToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfOfByte = (data[i] >>> 4) & 0x0F;
			int twoHalfBytes = 0;
			do {
				if ((0 <= halfOfByte) && (halfOfByte <= 9)) {
					buf.append((char) ('0' + halfOfByte));
				}

				else {
					buf.append((char) ('a' + (halfOfByte - 10)));
				}

				halfOfByte = data[i] & 0x0F;

			} while (twoHalfBytes++ < 1);
		}
		return buf.toString();
	}

	public static String MD5Encryption(String text)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		byte[] md5 = new byte[64];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5 = md.digest();
		return convertedToHex(md5);
	}

	// ======================================================================================================
	// User account must be active.
	public static boolean validateActiveUser(String userName) {
		try {
			UserMapper userMapper = new UserDAO();
			User userDB = userMapper.validateActiveUser(userName.trim());
			if (userDB.getStatus() == 1) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	// Username is only and exist in system if User wants retrieve password.
	public static boolean validateUserExist(String userName) {
		try {
			UserMapper userMapper = new UserDAO();
			User userDB = userMapper.validateUserExist(userName);
			if (userDB == null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	// User account doesn't login into system.
	public static boolean validateUserOrNot(String userName) {
		try {
			UserMapper userMapper = new UserDAO();
			User userDB = userMapper.validateUserOrNot(userName);
			if (userDB.getStatus() == 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	// One account just login at the same on the place (one browser, laptop)
	public static boolean validateUserLogedin(String userName) {
		try {
			UserMapper userMapper = new UserDAO();
			User userDB = userMapper.validateUserStatus(userName.trim());
			if (userDB.getStatus() == 2) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================

	public static boolean validateUserEmailExist(String email) {
		try {
			UserMapper userMapper = new UserDAO();
			User userDB = userMapper.validateUserEmailExist(email.trim());
			if (userDB == null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// ======================================================================================================
	public static boolean validateOldPassword(User mastuser) {
		try {
			UserMapper userMapper = new UserDAO();
			User userDB = userMapper.validateOldPassword(mastuser);
			if (userDB != null) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean validateEmptyQuanity(int num) {
		try {
			if (String.valueOf(num).equals("")
					|| String.valueOf(num).equals(null) || num == 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// validate temporary password
	public static boolean validateTemporaryPassword(String temporaryPassword) {
		try {
			UserMapper userMapper = new UserDAO();
			User userDB = userMapper
					.validateTemporaryPassword(temporaryPassword);
			if (userDB != null)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	// validate email matching userName
	public static boolean validateEmailMatchUserName(User user) {
		try {
			UserMapper userMapper = new UserDAO();
			User userDB = userMapper.validateEmailMatchUsername(user);
			if (userDB != null)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return false;
	}
	
	// Format file
	public static String fileExtention(String myContentType) {
		String imgType = ".jpg";
		try
		{
			if (myContentType.equalsIgnoreCase("image/png")) {
				imgType = ".png";
			}
			if (myContentType.equalsIgnoreCase("image/jpeg")) {
				imgType = ".jpeg";
			}
			if (myContentType.equalsIgnoreCase("image/jpg")) {
				imgType = ".jpg";
			}
			return imgType;
		}
		catch (Exception e){
			return "";
		}
	}
	
	// ======================================================================================================
	// Title is only 2-250 characters
	public static boolean validateLengthOfTitle(String title) {
		try {
			if (title.trim().length() >= 2 && title.trim().length() <= 250) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
