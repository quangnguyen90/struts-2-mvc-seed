package common.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 文字列情報・日付情報等の文字編集Util
 *
 * @author s-araki
 * @version $Id: ValidateUtils.java 1304 2011-06-29 10:32:50Z takashi.suzuki $
 **/
public class ValidateUtils {

	private static final Log log = LogFactory.getLog(ValidateUtils.class);

	/**
	 * 入力チェック用（正規表現）
	 *
	 */
	public static final String REG_ZEN_KIGOU	="[①②③④⑤⑥⑦⑧⑨⑩⑪⑫⑬⑭⑮⑯⑰⑱⑲⑳㍉㌔㌢㍍㌘㌧㌃㌶㍑㍗㌍㌦㌣㌫㍊㌻㎜㎝㎞㎎㎏㏄㎡㍻〝〟㏍㊤㊥㊦㊧㊨㈲㈹㍾㍽㍼∮∑∟⊿]*";
	public static final String REG_HAN_KANA	="[ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｬｭｮｯ､｡ｰ｢｣ﾞﾟ]*";
	public static final String REG_ZEN_KANA	="[アカサタナハマヤラワイキシチニヒミリウクスツヌフムユ	ルエケセテネヘメレオコソトノホモヨロン｡ｰ｢｣ﾞﾟ]*";
	public static final String REG_KANA = "^[ァ-ンｧ-ﾝヴヵヶヷヸヹヺ・ーヽヾヿ]+$";
	public static final String REG_HAN_KIGOU	="[!\"=`|~{}<>?^@\\[;:]$%]*";
											//アドレスに許可する記号　#$%&()*+-./=?^_`{|}~
											//拒否する記号　!"',:;<>[\]@
	public static final String REG_EMAIL	="^[\\x01-\\x20\\x23-\\x26\\x28-\\x2B\\x2D-\\x39\\x3D\\x3F\\x41-\\x5A\\x5E-\\x7F]+@([-\\w]+\\.)+[-\\w]+$";
	public static final String REG_ALPHA			="[a-zA-Z_]*";
	public static final String REG_ALPHA_SPACE	="[a-zA-Z_ ]*";
	public static final String REG_NUMERIC		="[0-9]*";
	public static final String REG_AMOUNT		="[0-9.,]*";
	public static final String REG_ALPHANUMERIC	="[a-zA-Z0-9_]*";
	public static final String REG_ALPHANUMERIC_FOR_CARDNAME = "[a-zA-Z0-9_. ]*";	//カード名義用
	public static final String REG_CAHR	="[a-zA-Z0-9_\\-@\\. ]*";
	public static final String REG_ALPHANUMERIC_SPACE	="[a-zA-Z0-9_\\- ]*";
	public static final String REG_DATE_FORMAT = "^\\d{4}/\\d{2}/\\d{2}$";
	public static final String REG_DATE_TIME_FORMAT = "^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}$";
	//public static final String REG_URL_FORMAT = "/^(((ht|f)tp(s?))\\:\\/\\/)([0-9a-zA-Z\\-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?$/";
	public static final String REG_URL_FORMAT = "^(((ht|f)tp(s?))://)([0-9a-zA-Z-]+.)+[a-zA-Z]{2,6}(:[0-9]+)?(\\S*)?$";
	//public static final String REG_URL_FORMAT = "^(https?://)?([\\da-z.-]+).([a-z.]{2,6})([\\w .-]*)*/?$";
	//public static final String REG_NOT_SYMBOL = "^[^-/:\\[\\`{\\~]+$";
	public static final String REG_NOT_SYMBOL = "^[^〜！＠＃＄％＾＆＊（）＿＋＜＞／？!”#$%&’()*+-.,/:;<=>?@^_`{|}~'\"]+$";
	public static final String REG_FLOAT_POINT_NUMERIC = "^((\\d{1,3}(,\\d{3})*)|\\d{1,})(.\\d{1,2})?$";
	public static final String REG_FORMAT_NUMERIC = "^((\\d{1,3}(,\\d{3})*)|\\d{1,})$";
	public static final String REG_SPACE_STRING = "^( )+$";
//	private static final long serialVersionUID=0;

	/**
	 * ブランクまたはNULLかどうかチェックする
	 * <br>
	 *
	 * @return boolean NULLまたはブランク時:true
	 *
	 */
	public static boolean isEmpty(Double s){
		try{
			if (s==null || s.doubleValue()<=0){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}

	/**
	 * ブランクまたはNULLかどうかチェックする
	 * <br>
	 *
	 * @return boolean NULLまたはブランク時:true
	 *
	 */
	public static boolean isEmpty(Integer s){
		try{
			if (s==null || s.intValue()<=0){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}


	/**
	 * ブランクまたはNULLかどうかチェックする
	 * <br>
	 *
	 * @return boolean NULLまたはブランク時:true
	 *
	 */
	public static boolean isEmpty(String s){
		try{
			if (s==null || s.toString().equals("")){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}

	/**
	 * ブランクまたはNULLかどうかチェックする
	 * <br>
	 *
	 * @return boolean NULLまたはブランク時:true
	 *
	 */
	public static boolean isEmpty(BigDecimal b){
		try{
			if (b==null || b.doubleValue()==0){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}


	/**
	 * ブランクまたはNullかどうかを判定します。
	 *
	 * @param str 文字列
	 * @return ブランク(Null)かどうか
	 */
	public static boolean isEmpty(String[] str) {
		boolean b;
		if (str==null || str.length==0){
			b = true;
		}else{
			b = false;
		}
		return b;
	}

	/**
	 * ブランクまたはNULLかどうかチェックする
	 * <br>
	 *
	 * @return boolean NULLまたはブランク時:true
	 *
	 */
	public static boolean isEmpty(Date s){
		try{
			if (s==null ){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}

	    /**
	     * 数値
	     *
	     * @param str
	     * @return
	     */
	    public static boolean isNumeric(String str){
	    	return MiscUtils.regMatch(REG_NUMERIC,str);
	    }

	    /**
	     * 金額（数値＋ドット）
	     *
	     * @param str
	     * @return
	     */
	    public static boolean isAmount(String str){
//	    	return MiscUtils.regMatch(REG_AMOUNT,str);
	    	return MiscUtils.regMatch(REG_FLOAT_POINT_NUMERIC,str);
	    }

	    /**
	     * 半角英字チェック
	     *
	     * @param str
	     * @return
	     */
	    public static boolean isAlpha(String str){
	    	return MiscUtils.regMatch(REG_ALPHA,str);
	    }

	    /**
	     * 半角英字+スペースチェック
	     *
	     * @param str
	     * @return
	     */
	    public static boolean isAlphaSpace(String str){
	    	return MiscUtils.regMatch(REG_ALPHA_SPACE,str);
	    }

	    /**
	     * 半角英数字
	     *
	     * @param str
	     * @return
	     */
	    public static boolean isAlphanumeric(String str){
	    	return MiscUtils.regMatch(REG_ALPHANUMERIC,str);
	    }


	    /**
	     * 半角英数字
	     *
	     * @param str
	     * @return
	     */
	    public static boolean isChar(String str){
	    	return MiscUtils.regMatch(REG_CAHR,str);
	    }

	    /**
	     * 半角英数字+スペース
	     *
	     * @param str
	     * @return
	     */
	    public static boolean isAlphanumericSpace(String str){
	    	return MiscUtils.regMatch(REG_ALPHANUMERIC_SPACE,str);
	    }

		/**
	     * 入力禁止文字文字を含んでいないかチェック
	     * (全角記号、半角記号、半角カナ）
	     */
	    public static boolean isSafeChar(String str) {
	    	if (isEmpty(str)){
	    		return true;
	    	}
	    	if (isKana(str)){
	    		return false;
	    	}else if (isZenKigou(str)){
	    		return false;
	    	}else if (isHanKigou(str)){
	    		return false;
	    	}
	    	return true;
	    }



		/**
	     * 文字列が半角カナを含んでいたら真を返します。
	     */
	    public static boolean isKana (String a) {
	    	return MiscUtils.regMatch(REG_KANA,a);
	    }

	    public static boolean isHanKana (String a) {
	    	return MiscUtils.regMatch(REG_HAN_KANA,a);
	    }
	    /**
	     * 全角記号文字を含んでいたら真を返します。
	     */
	    public static boolean isZenKigou (String a) {
	    	return MiscUtils.regMatch(REG_ZEN_KIGOU,a);
	    }

	    /**
	     * 半角記号+を含んでいたら真を返します。
	     */
	    public static boolean isHanKigou (String a) {
	    	return MiscUtils.regMatch(REG_HAN_KIGOU,a);
	    }

	    /**
	     * Check the string as katakana characters
	     * @param a the input string
	     * @return
	     */
	    public static boolean isZenkana (String a) {
	    	return MiscUtils.regMatch(REG_ZEN_KANA,a);
	    }
	    
	    public static boolean isEmail(String str){
	    	if (isEmpty(str)){
	    		return true;
	    	}
	    	return MiscUtils.regMatch(REG_EMAIL,str);
	    }


	    /**
	     * カード番号の文字長さチェック（14～16桁）
	     * (AMEX=15桁,DINERS=14桁,他=16桁)
	     */
	    public static boolean isCardLength(String a) {
	    	if (a==null){
	    		return false;
	    	}
	    	if (a.length()==15 || a.length()==16 || a.length()==14){
	    		return true;
	    	}
	    	return false;
	    }

	    /**
	     * カード有効期限(月）チェック
	     */
	    public static boolean isCardMonth(String a) {
	    	if (a==null){
	    		return false;
	    	}
	    	try{
		    	if (Integer.parseInt(a)>=1 && Integer.parseInt(a)<=12){
		    		return true;
		    	}
	    	}catch (Exception e){
	    	}
	    	return false;
	    }

	    /**
	     * カード有効期限(年）チェック
	     */
	    public static boolean isCardYear(String a) {
	    	if (a==null){
	    		return false;
	    	}
	    	try{
		    	if (Integer.parseInt(a)>=2000 && Integer.parseInt(a)<=3000){
		    		return true;
		    	}
	    	}catch (Exception e){
	    	}
	    	return false;
	    }

	    /**
	     * ご利用金額の小数点以下入力可能チェック
	     */
	    public static boolean isAmountDecimal(double amount,String curncy) {
	    	if (amount<=0){
	    		return true;
	    	}
	    	try{
	    		//少数点以下を算出
	    		double dec = amount - (int)amount;
	    		//日本円は小数は不可
	    		if (curncy.equals(Consts.CURRENCY_JPY) && dec>0){
	    			return false;
	    		}

	    	}catch (Exception e){
	    	}
	    	return true;
	    }



		public static boolean isTrue(Boolean bool){
			if (bool==null || bool.booleanValue()==false){
				return false;
			}
			return true;
		}

	    /**
	     * 文字長さチェック(入力チェック用)
	     *
	     * @param String チェック対象文字列
	     * @param int 	  最大入力可能桁数
	     */
	    public static boolean isValidLength(String a, int length) {
	    	if (a==null){
	    		return true;
	    	}
	    	if ( a.getBytes().length <= length ){
	    		return true;
	    	}
	    	return false;
	    }


	    /**
	     * 半角英数字+スペース+ドットチェック
	     * (カード名義用)
	     * @param str
	     * @return
	     */
	    public static boolean isAlphanumericForCardname(String str){
	    	return MiscUtils.regMatch(REG_ALPHANUMERIC_FOR_CARDNAME, str) && !MiscUtils.regMatch(REG_SPACE_STRING, str);
	    }


		/**
		 * 有効なユーザIDかどうかを判定します。
		 * @param name
		 * @return
		 */
	    public static boolean isValidIDCheck(String name) {
			return isAlphaOrDigit(name);
		}

		/**
		 * 有効なパスワードかどうかを判定します。
		 * @param password
		 * @return
		 */
	    public static boolean isValidPassword(String password) {
			return isAlphaOrDigit(password);
		}

		/**
		 * 文字列が半角英数字から構成されているかどうかを判定します。
		 * @param str
		 * @return
		 */
	    public static boolean isAlphaOrDigit(String str) {
	    	if(null==str){
	    		return false;
	    	}
			for(int i = 0; i < str.length(); i ++) {
				char ch = str.charAt(i);
				if(isAlphaOrDigit(ch) == false) {
					return false;
				}
			}
			return true;
		}

		/**
		 * 文字が半角英数字かどうかを判定します。
		 * @param ch
		 * @return
		 */
	    public static boolean isAlphaOrDigit(char ch) {
			if('A' <= ch && ch <= 'Z') {
				return true;
			}
			if('a' <= ch && ch <= 'z') {
				return true;
			}
			if('0' <= ch && ch <= '9') {
				return true;
			}
			return false;
		}

	    /**
	     * Check the date format as YYYY/MM/DD
	     * @param dateFormatString
	     * @return
	     */
	    public static boolean isDateFormat(String dateFormatString)
	    {
	    	// First check for the pattern
	        if(!MiscUtils.regMatch(REG_DATE_FORMAT, dateFormatString))
	            return false;

	        // Parse the date parts to integers
	        String[] parts = dateFormatString.split("/");
	        int day = Integer.parseInt(parts[2], 10);
	        int month = Integer.parseInt(parts[1], 10);
	        int year = Integer.parseInt(parts[0], 10);

	        // Check the ranges of month and year
	        if(year < 1000 || year > 3000 || month == 0 || month > 12)
	            return false;

	        int[] monthLength = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	        // Adjust for leap years
	        if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
	            monthLength[1] = 29;

	        // Check the range of the day
	        return (day > 0 && day <= monthLength[month - 1]);
	    }
	    /**
	     * Check the date format as YYYY/MM/DD
	     * @param dateTimeFormatString
	     * @return
	     */
	    public static boolean isDateTimeFormat(String dateTimeFormatString)
	    {
	    	// First check for the pattern
	        if(!MiscUtils.regMatch(REG_DATE_TIME_FORMAT, dateTimeFormatString))
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
	        if(year < 1000 || year > 3000 || month == 0 || month > 12)
	            return false;

	        int[] monthLength = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	        // Adjust for leap years
	        if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
	            monthLength[1] = 29;

	        // Check the range of the day
	        if(!(day > 0 && day <= monthLength[month - 1])){
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
	     * @param url (String)
	     * @return true or false
	    */
	    public static boolean isURL(String url) {
	    	if(url == null || "".equals(url)){
	    		return false;
	    	}
	    	Pattern pattern = Pattern.compile(REG_URL_FORMAT);
			Matcher matcher = pattern.matcher(url);
			return matcher.matches();
		}

	    /**
		 * Check 2 strings as the range of date format yyyy/MM/dd
		 * @param firstDate
		 * @param secondDate
		 * @return
		 */
		public static boolean isCheckBetweenDates(String firstDate, String secondDate)
		{
			boolean isOK = true;
			
			for(;;)
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date1 = null;
				Date date2 = null;
				
				try {
					date1 = (Date)dateFormat.parse(firstDate);
					date2 = (Date)dateFormat.parse(secondDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					isOK = false;
					break;
				}
				if(date1 != null && date2 != null)
				{
					if(date1.after(date2)) // The first date is less than second one
					{
						isOK = false;
						break;
					}
				}
				
				break;
			}
			return isOK;
		}
		
		public static boolean isSymbol(String str){
	    	return !MiscUtils.regMatch(REG_NOT_SYMBOL,str) || MiscUtils.regMatch(REG_SPACE_STRING, str) || str.contains("[") || str.contains("]");
	    }
		
		public static boolean isFloatPointNumeric(String str){
	    	return MiscUtils.regMatch(REG_FLOAT_POINT_NUMERIC,str);
	    }
		public static boolean isFormatNumeric(String str){
	    	return MiscUtils.regMatch(REG_FORMAT_NUMERIC,str);
	    }
}


