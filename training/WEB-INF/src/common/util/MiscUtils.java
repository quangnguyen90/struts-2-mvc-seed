package common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

//import dao.domain.UserCookie;

public class MiscUtils  {

	private static final Log log = LogFactory.getLog(MiscUtils.class);

	private static String[] DATE_FORMAT =
	{
		"yyyy/MM/dd HH:mm:ss",
		"yyyy-MM-dd HH:mm:ss",
		"yyyy/MM/dd HH:mm",
		"yyyy-MM-dd HH:mm",
		"yyyy/MM/dd",
		"yyyy-MM-dd",
		"yy/MM/dd",
		"yy-MM-dd",
		"yyyyMMdd",
		"yyMMdd",
		"MMdd",
		"dd/MM/yyyy"
	};


	public static boolean regMatch(String p, String s) 
	{
		if(null == s || s.trim()==""){
			return false;
		}
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	public static boolean regMatchIC(String p, String s) 
	{
		 if(null == s){
				return false;
		}
		 Pattern pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);
		 Matcher matcher = pattern.matcher(s);
		 return matcher.matches();
	}

	public static String[] regSplit(String p, String s) 
	{
		 if(null == s){
				return null;
			}
		 Pattern pattern = Pattern.compile(p);
		 return pattern.split(s);
	 }

	public static String[] regSplit(String p, String s, int limit) 
	{
		 Pattern pattern = Pattern.compile(p);
		 return pattern.split(s, limit);
	 }

	 public static String regReplaceFirst(String p, String r, String s) 
	 {
		 Pattern pattern = Pattern.compile(p);
		 Matcher matcher = pattern.matcher(s);
		 return matcher.replaceFirst(r);
	 }

	public static String regReplaceAll(String p, String r, String s) 
	{
		 Pattern pattern = Pattern.compile(p);
		 Matcher matcher = pattern.matcher(s);
		 return matcher.replaceAll(r);
	 }

	public static String regReplaceAll2(String p, String r, String s) 
	{
		 Pattern pattern = Pattern.compile(p);
		 Matcher matcher = pattern.matcher(s);
		 StringBuffer sb = new StringBuffer();
		 while (matcher.find()) {
			 matcher.appendReplacement(sb, r);
		 }
		 matcher.appendTail(sb);
		 return sb.toString();
	 }

	public static String formatDate(Date date, String format) 
	{
		String rt;
		SimpleDateFormat df;
		try{

			if (date != null) {
				df = new SimpleDateFormat(format);
				rt = df.format(date);
			} else {
				rt = "";
			}
		}catch(Exception e){
			rt = "";
		}
		return rt;
	}

	public static Date parseDate(String str, String format) throws ParseException 
	{
		return parseDate(str, format, true);
	}

	public static String parseStrDate(String str, String format)throws ParseException 
	{
		return formatDate(parseDate(str),format);
	}

	public static Date parseDate(String str, String format, boolean lenient) throws ParseException 
	{
		//DbC
		if(str == null || format == null){
			throw new IllegalArgumentException("ç„¡åŠ¹ã�ªå¼•æ•°ã�§ã�™");
		}

		Date rt;
		SimpleDateFormat df;

		df = new SimpleDateFormat(format);
		df.setLenient(lenient);
		rt = df.parse(str);

		return rt;
	}

	public static Date parseDate(String str)  
	{
		return parseDate(str, true);
	}

	public static Date parseDate(String str, boolean lenient)
	{
		Date rt = null;
		try{
			//DbC
			if(str == null){
				return null;
				//throw new IllegalArgumentException("ç„¡åŠ¹ã�ªå¼•æ•°ã�§ã�™");
			}

			SimpleDateFormat df;
			int i;
			//ParseException exception = null;

			for (i = 0; i < DATE_FORMAT.length; i++) {
				try {
					df = new SimpleDateFormat(DATE_FORMAT[i]);
					df.setLenient(lenient);
					rt = df.parse(str);
					break;
				} catch (ParseException ex2) {
					//exception = ex2;
				}
			}

			if (rt == null) {
				df = new SimpleDateFormat();
				df.setLenient(lenient);
				rt = df.parse(str);
			}
		}catch (Exception e){
			return null;
		}
		return rt;
	}

	public static String formatDString(String str, String before_format,String after_format) throws ParseException 
	{

		if(str == null || before_format == null || after_format==null){
			return "";
		}

		Date d = parseDate(str, before_format);
		String s= formatDate(d,after_format);
		return s;
	}


	public static Number parseNumber(String str) throws ParseException 
	{
		Number rt;
		NumberFormat nf;

		if (str == null || str.length() == 0) {
			return new Integer(0);
		}

		if (str.startsWith("+")) {
			str = str.substring(1);
		}

		nf = NumberFormat.getInstance();
		rt = nf.parse(str);

		return rt;
	}
	// Parse int to string
	public static String parseStr(int i) 
	{
		Integer _int = new Integer(i);
		return _int.toString();
	}

	// Parse string to int
	public static int parseInt(String str) throws ParseException {
		return parseNumber(str).intValue();
	}
	
	// Parse string to long
	public static long parseLong(String str) throws ParseException {
		return parseNumber(str).longValue();
	}
	
	// Parse string to double
	public static double parseDouble(String str) throws ParseException {
		return parseNumber(str).doubleValue();
	}

	// Get file extension
	public static String getExt(String filename) 
	{
	  String ext = "";
	  int pos = filename.lastIndexOf(".");
	  if (pos > 0) {
		ext = filename.substring(pos + 1, filename.length());
	  }
	  return ext;
	}

	public static String delDateSp(String s) {
		return regReplaceAll("/","",s);
	}

	public static String delAmtSp(String s) {
		if (s==null){
			return s;
		}else{
			return regReplaceAll(",","",s);
		}
	}
	
	// Convert string-date to yyyy/mm/dd
	public static String cnvDspDate(String s) {
		try{
			if (s==null){
				return s;
			}else{
				return formatDate(parseDate(s),"yyyy/MM/dd");
			}
		}catch (Exception e){
			return null;
		}
	}
	
	// Convert string-date to dd/mm/yyyy
	public static String cnvDspDate2(String s) {
		try{
			if (s==null){
				return s;
			}else{
				return formatDate(parseDate(s),"dd/MM/yyyy");
			}
		}catch (Exception e){
			return null;
		}
	}
	
	public static String formatNumber(String src, int scale){
			if(src == null){
				return null;
			}
			src = src.trim();
			StringBuffer srcbuf = new StringBuffer(src);

			int seiketa, shouketa;
			int dotloc = src.indexOf('.');
			if(dotloc == -1){
				seiketa = src.length();
				shouketa = 0;
			} else {
				seiketa = dotloc;
				shouketa = src.length() - dotloc - 1;
			}

			if(scale>0 && shouketa == 0){
				srcbuf.append('.');
			}
			for(int i=shouketa; i<scale; i++){
				srcbuf.append('0');
			}
			if(seiketa > 3){
				int startIndex = seiketa % 3;
				if(startIndex == 0){
					startIndex = 3;
				}
				for(int i=startIndex; i<seiketa; i+=3){
					srcbuf.insert(i++, ',');
				}
			}
			if(dotloc == 0){
				srcbuf.insert(0, '0');
			}
			return srcbuf.toString();
		}

	public static String getSiteToMrch(String siteId)
	{
		if (ValidateUtil.isEmpty(siteId)){
			return siteId;
		}
		if (siteId.length()>=6){
			return siteId.substring(0,6);
		}else{
			return siteId;
		}
	}

	public static String padMonth(String month)
	{
		if (!ValidateUtil.isEmpty(month) && month.length()==1){
			month= "0"+month;
		}
		return month;
	}

	public static String trimMonth(String month)
	{
		if (!ValidateUtil.isEmpty(month)){
			try{
				month =Integer.valueOf(month).toString();
			}catch (Exception e){

			}
		}
		return month;
	}

	public static String padYear(String year)
	{
		if (!ValidateUtil.isEmpty(year)){
			if (year.length()==2){
				year= "20"+year;
			} else if (year.length()==1){
				year= "200"+year;
			}

		}
		return year;
	}

	public static String trimYear(String year)
	{
		if (!ValidateUtil.isEmpty(year)){
			if (year.length()>3){
				year = year.substring(year.length()-2,year.length());
			}else if (year.length()==1){
				year = "0"+year;
			}
		}
		return year;
	}

	public static String splitMailDoamin(String mailaddr)
	{
		String domain="";
		try{
			domain=mailaddr.split(Consts.MAIL_DMIN_SEPARATERE)[1];
		}catch (Exception e){
		}
		return domain;
	}
	public static String rightPad(String arg1,int arg2){
		return StringUtils.rightPad(arg1,arg2);
	}

	public static String rightPad(String arg1,int arg2,String arg3)
	{
		return StringUtils.rightPad(arg1,arg2,arg3);
	}

	public static String leftPad(String arg1,int arg2)
	{
		return StringUtils.leftPad(arg1,arg2);
	}

	public static String leftPad(String arg1,int arg2,String arg3)
	{
		return StringUtils.leftPad(arg1,arg2,arg3);
	}

	public static String replace(String arg1,String arg2,String arg3)
	{
		return StringUtils.replace(arg1,arg2,arg3);
	}

	public static String replace(String arg1,String arg2,String arg3,int agr4)
	{
		return StringUtils.replace(arg1,arg2,arg3,agr4);
	}

	public static String uncapitalise(String arg1)
	{
		if(arg1==null || arg1.length() == 0) {
			return "";
		}
		return arg1.substring(0,1).toLowerCase() + arg1.substring(1);
	}

	public static String remove(String arg1,String arg2)
	{
		return StringUtils.remove(arg1,arg2);
	}

	public static String getSetlStatString(Integer stat)
	{
		if (stat==null){
			return "";
		}
		if (stat.intValue()==Consts.SETL_OK){
			return Consts.SETL_OK_STR;
		}else if (stat.intValue()==Consts.SETL_AUTH){
			return Consts.SETL_AUTH_STR;
		}else if (stat.intValue()==Consts.SETL_VOID){
			return Consts.SETL_VOID_STR;
		}else if (stat.intValue()==Consts.SETL_NG){
			return Consts.SETL_NG_STR;
		}else if (stat.intValue()==Consts.SETL_EXCEPTION){
			return Consts.SETL_EXCEPTION_STR;
		}else if (stat.intValue()==Consts.SETL_CB){
			return Consts.SETL_CB_STR;
		}else if (stat.intValue()==Consts.SETL_VOID_REQ){
			return Consts.SETL_VOID_REQ_STR;
		}else if (stat.intValue()==Consts.SETL_CAN_REQ){
			return Consts.SETL_CAN_REQ_STR;
		}else if (stat.intValue()==Consts.SETL_CANCEL){
			return Consts.SETL_CANCEL_STR;
		}else if (stat.intValue()==Consts.SETL_RETRIEVAL){
			return Consts.SETL_RETRIEVAL_STR;
		}else{
			return "";
		}
	}

	public static String[] split(String value,String delimiter)
	{
	    int count = 0;
	    int temi = 0;
	    int temj = value.indexOf(delimiter);

	    if (value.equals("")){
	      String[] values={""};
	      return values;
	    }
	    while (temj >= 0) {
	      count++;
	      temi = temj + delimiter.length();
	      temj = value.indexOf(delimiter, temi);
	    }

	    String[] values = new String[count+1];
	    int i = 0;
	    int j = value.indexOf(delimiter);
	    for (int h = 0; j >= 0; h++) {
	     values[h] = value.substring(i, j);
	      i = j + delimiter.length();
	      j = value.indexOf(delimiter, i);
	    }
	    values[count] = value.substring(i);
	    return values;
	}

	public static String parseUserMail(String mail)
	{
		try{

			if (ValidateUtil.isEmpty(mail) || !ValidateUtil.isEmail(mail)){
				return "";
			}else{

				int arobasPosition = mail.indexOf("@")+1;
				int nbLetters = mail.substring(arobasPosition).toString().length();

				char[] stars = new char[nbLetters];
				Arrays.fill(stars, '*');

				return mail.substring(0,arobasPosition)+ new String(stars);
			}
		}catch(Exception e){
			return "";
		}
	}
	
	public static String parseUserTel(String tel)
	{
		try{
			String telephone = tel.trim().replace(" ", "");
			if (ValidateUtil.isEmpty(telephone)){
				return "";
			}else{
				boolean hasTwoHaifun = (telephone.indexOf("-")!=-1 && telephone.indexOf("-")!= telephone.lastIndexOf("-"))?true:false;
				if(hasTwoHaifun){
					int firstHaifun 	= telephone.indexOf("-",0);	
					int secondHaifun 	= telephone.lastIndexOf("-");
					char[] stars = new char[(secondHaifun-firstHaifun-1)];
					Arrays.fill(stars, '*');
					return telephone.substring(0,firstHaifun+1) + new String(stars) + telephone.substring(secondHaifun);
				}else{		
					if(telephone.length()>=5){
						int middle = (telephone.length())/2;	//æ–‡å­—åˆ—ã�®çœŸã‚“ä¸­ã�®Indexï¼ˆå››æ�¨äº”å…¥ï¼‰
						return telephone.substring(0,(middle-1)) + "***" + telephone.substring(middle+2);
					}else{
						return telephone;
					}
				}
			}
		}catch(Exception e){
			return "";
		}
	}
		
	/**
	 * @Description: parse a such date to format date yyyy/mm/dd
	 * @param date
	 * @return String
	 */
	public static final String parseToFormatString(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		return simpleDateFormat.format(calendar.getTime());
	}
		
	/**
	 * @Description: parse string to date with format yyyy/MM/dd
	 * @param date
	 * @return
	 */
	public static final Date parseFromFormatString(String date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Description: parse a such date to format date dd/MM/yyyy
	 * @param date
	 * @return String
	 */
	public static final String parseToFormatString2(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return simpleDateFormat.format(calendar.getTime());
	}
	
	/**
	 * @Description: parse string to date with format dd/MM/YYY
	 * @param date
	 * @return
	 */
	public static final Date parseFromFormatString2(String date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Description: encrypt a input string by md5 algorithm
	 * @param plainText
	 * @return String
	 */
	public static final String encryptedMd5(String plainText)
	{
		if(plainText != null){
			MessageDigest m;
			try {
				m = MessageDigest.getInstance("MD5");
				m.reset();
				m.update(plainText.getBytes());
				byte[] digest = m.digest();
				BigInteger bigInt = new BigInteger(1, digest);
				String hashText = bigInt.toString(16);
				if(hashText.length() > 30){
					hashText = hashText.substring(0, 30);
				}
				return hashText;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
		
	/**
	 * @Description: add user name and password to cookie
	 * @param userId
	 * @param passWord
	 */
	/*public static final void addUserCookie(String userId, String passWord){
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
    	Cookie userCookie = null;
    	Cookie passCookie = null;
        for(int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("UserId")) {
            	userCookie = cookies[i];
            }else if(cookies[i].getName().equals("PassWord")){
            	passCookie = cookies[i];
            }
            if(userCookie != null && passCookie != null){
            	break;
            }
        }  

        // Add user name to cookie
        if (userCookie == null) {
            userCookie = new Cookie("UserId", userId);
        }
        userCookie.setValue(userId);
        ServletActionContext.getResponse().addCookie(userCookie);
        // Add password to cookie
        if (passCookie == null) {
            passCookie = new Cookie("PassWord", passWord);
        }
        passCookie.setValue(passWord);
        ServletActionContext.getResponse().addCookie(passCookie);
	}*/
		
	/**
	 * @Description: delete user from cookie
	 */
	/*public static final void deleteUserCookie(){
		int count = 0;
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        for(int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("UserId")) {
            	cookies[i].setValue("");
            	count++;
            	ServletActionContext.getResponse().addCookie(cookies[i]);
            }else if(cookies[i].getName().equals("PassWord")){
            	cookies[i].setValue("");
            	count++;
            	ServletActionContext.getResponse().addCookie(cookies[i]);
            }
            if(count >= 2){
            	break;
            }
        }  
	}*/
		
	/*public static final UserCookie isSavedUserCookie(){
		UserCookie userCookie = new UserCookie();
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        for(int i = 0; i < cookies.length; i++) {
        	if(cookies[i].getValue() != null && cookies[i].getValue().length() > 0){
        		if(cookies[i].getName().equals("UserId")){
        			userCookie.setSaved(true);
        			userCookie.setUserName(cookies[i].getValue());
        		}else if(cookies[i].getName().equals("PassWord")){
        			userCookie.setSaved(true);
        			userCookie.setPassWord(cookies[i].getValue());
        			if(userCookie.getUserName().length() > 0){
        				break;
        			}
        		}
        	}
        }
        return userCookie;
	}
	*/

	/**
	 * @param int length
	 * @return String random password
	 * @description get random n char password
	 */
	private static final String dCase = "abcdefghijklmnopqrstuvwxyz";
	private static final String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String intChar = "0123456789";
    public static String returnRandomPassword(int lenght) 
    {
    	String pass = "";
		Random r = new Random();
    	Random r1 = new Random();
        while (pass.length () != lenght-2){
            int rPick = r.nextInt(2);
            int spot;
            if (rPick == 0){
                spot = r1.nextInt(25);
                pass += dCase.charAt(spot);
            } else{
                spot = r1.nextInt (25);
                pass += uCase.charAt(spot);
            }
        }
        for(int i=0; i < 2; i++){
        	int spot;
        	spot = r1.nextInt (9);
        	pass += intChar.charAt (spot);
        }
        return pass;
    }
    
    public static String convertDateToString(Date date, String format) {
	    SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
	    return mySimpleDateFormat.format(date);
	}
	 
	public static Date convertStringToDate(String dateStr, String format) {
	    SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
	    try {
	        return mySimpleDateFormat.parse(dateStr);
	    } catch (ParseException e) {
	        return null;
	    }
	}
    
}
