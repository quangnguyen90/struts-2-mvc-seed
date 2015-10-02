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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文字列情報・日付情報等の文字編集Util
 *
 * @author s-araki
 * @version $Id: MiscUtils.java 1212 2010-08-30 05:16:30Z kenji.kodaka $
 **/
public class MiscUtils  {

	private static final Log log = LogFactory.getLog(MiscUtils.class);

	/** 日付フォーマット
	 * フォーマット文字列の長い順に評価させる
	*/
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
			"MMdd"
		};


		/**
		 * パターンマッチのテストを行い、結果を booleanで返す。
		 * @param String 正規表現
		 * @param String 評価文字列
		 */
		public static boolean regMatch(String p, String s) {
			if(null == s){
				return false;
			}
			Pattern pattern = Pattern.compile(p);
			Matcher matcher = pattern.matcher(s);
			return matcher.matches();
		}

		/**
		* パターンマッチのテストを行い、結果を booleanで返す。
		* 大文字小文字の区別は行わない。
		* @param String 正規表現
		* @param String 評価文字列
		**/
	 public static boolean regMatchIC(String p, String s) {
		 if(null == s){
				return false;
		}
		 Pattern pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);
		 Matcher matcher = pattern.matcher(s);
		 return matcher.matches();
	 }

	 /**
	  * 文字列を分割して、結果を String[] で返す。
	  * @param String 正規表現
	  * @param String 評価文字列
	  **/
	 public static String[] regSplit(String p, String s) {
		 if(null == s){
				return null;
			}
		 Pattern pattern = Pattern.compile(p);
		 return pattern.split(s);
	 }

	 /**
	  * 文字列を分割して、結果を String[] で返す。
	  * 分割数の上限指定も行う。
	  * @param String 正規表現
	  * @param String 評価文字列
	  * @param String 分割リミット
	  */
	 public static String[] regSplit(String p, String s, int limit) {
		 Pattern pattern = Pattern.compile(p);
		 return pattern.split(s, limit);
	 }

	 /**
	  * 文字列の置換を行い、置換された文字列を返す。
	  * 最初にマッチしたものしか置換しない
	  * @param String 正規表現
	  * @param String 置換文字
	  * @param String 評価文字列
	  */
	 public static String regReplaceFirst(String p, String r, String s) {
		 Pattern pattern = Pattern.compile(p);
		 Matcher matcher = pattern.matcher(s);
		 return matcher.replaceFirst(r);
	 }

	 /**
	  * 文字列の置換を行い、置換された文字列を返す。
	  * マッチするものはすべて置換する
	  * @param String 正規表現
	  * @param String 置換文字
	  * @param String 評価文字列
	  */
	 public static String regReplaceAll(String p, String r, String s) {
		 Pattern pattern = Pattern.compile(p);
		 Matcher matcher = pattern.matcher(s);
		 return matcher.replaceAll(r);
	 }

	 /**
	  * 文字列の置換を行い、置換された文字列を返す。
	  * マッチするものはすべて置換する
	  * matcher.find() を用いて実装する。
	  * @param String 正規表現
	  * @param String 置換文字
	  * @param String 評価文字列
	  */
	 public static String regReplaceAll2(String p, String r, String s) {
		 Pattern pattern = Pattern.compile(p);
		 Matcher matcher = pattern.matcher(s);
		 StringBuffer sb = new StringBuffer();
		 while (matcher.find()) {
			 matcher.appendReplacement(sb, r);
		 }
		 matcher.appendTail(sb);
		 return sb.toString();
	 }

	/**
	 * 日付を文字型に変換します
	 *
	 * @param date フォーマットする日付
	 * @param format フォーマット書式
	 * @return フォーマット後の文字列
	 */
	public static String formatDate(Date date, String format) {

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

	/**
	 * 文字列を日付型にパースします。
	 *
	 * @param str パースする文字列
	 * @param format フォーマット書式
	 * @return パース後の日付
	 * @exception ParseException
	 */
	public static Date parseDate(String str, String format)
		throws ParseException {
		return parseDate(str, format, true);
	}

	/**
	 * 文字列日付のフォーマット変換
	 *
	 * @param str パースする文字列
	 * @param format フォーマット書式
	 * @return パース後の日付
	 * @exception ParseException
	 */
	public static String parseStrDate(String str, String format)
		throws ParseException {
		return formatDate(parseDate(str),format);
	}

	/**
	 * 文字列を日付型にパースします。
	 *
	 * @param str パースする文字列
	 * @param format フォーマット書式
	 * @param lenient true の場合は厳密ではない解析
	 * @return パース後の日付
	 * @exception ParseException
	 */
	public static Date parseDate(String str, String format, boolean lenient)
		throws ParseException {
		//DbC
		if(str == null || format == null){
			throw new IllegalArgumentException("無効な引数です");
		}

		Date rt;
		SimpleDateFormat df;

		df = new SimpleDateFormat(format);
		df.setLenient(lenient);
		rt = df.parse(str);

		return rt;
	}

	/**
	 * 文字列を日付型にパースします。
	 *
	 * @param str パースする文字列
	 * @return パース後の日付
	 * @exception ParseException
	 */
	public static Date parseDate(String str)  {
		return parseDate(str, true);
	}

	/**
	 * 文字列を日付型にパースします。<BR>
	 * フォーマットの評価は先頭一致で評価される為、<BR>
	 * フォーマット文字列の並び順を長い順に変更した<BR>
	 *
	 * @param str パースする文字列
	 * @param lenient true の場合は厳密ではない解析
	 * @return パース後の日付
	 * @exception ParseException
	 */
	public static Date parseDate(String str, boolean lenient)
		 {
		Date rt = null;
		try{
			//DbC
			if(str == null){
				return null;
				//throw new IllegalArgumentException("無効な引数です");
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


	/**
	 * 文字列日付をフォーマット変換
	 *
	 * @param str 変換する文字列
	 * @param format 変換前フォーマット
	 * @param format 変換後フォーマット
	 * @return パース後の日付
	 * @exception ParseException
	 */
	public static String formatDString(String str, String before_format,String after_format)
		throws ParseException {

		if(str == null || before_format == null || after_format==null){
			return "";
		}

		Date d = parseDate(str, before_format);
		String s= formatDate(d,after_format);
		return s;
	}


	/**
	 * 文字列を Number に変換
	 *
	 * @param str 変換する文字列
	 * @return 変換後の Number
	 * @exception ParseException
	 */
	public static Number parseNumber(String str) throws ParseException {
		Number rt;
		NumberFormat nf;

		// nullもしくは""の場合には0を返す
		if (str == null || str.length() == 0) {
			return new Integer(0);
		}

		// プラス記号で始まる場合には、プラス記号を削除
		if (str.startsWith("+")) {
			str = str.substring(1);
		}

		// 数値の変換
		nf = NumberFormat.getInstance();
		rt = nf.parse(str);

		return rt;
	}

	/**
	 * intを文字列に変換
	 *
	 * @param int 変換する数値
	 * @return 変換後の String
	 * @exception ParseException
	 */
	public static String parseStr(int i) {
		Integer _int = new Integer(i);
		return _int.toString();
	}


	/**
	 * 文字列を int に変換
	 *
	 * @param str 変換する文字列
	 * @return 変換後の int
	 * @exception ParseException
	 */
	public static int parseInt(String str) throws ParseException {
		return parseNumber(str).intValue();
	}

	/**
	 * 文字列を long に変換
	 *
	 * @param str 変換する文字列
	 * @return 変換後の long
	 * @exception ParseException
	 */
	public static long parseLong(String str) throws ParseException {
		return parseNumber(str).longValue();
	}

	/**
	 * 文字列を double に変換
	 *
	 * @param str 変換する文字列
	 * @return 変換後の double
	 * @exception ParseException
	 */
	public static double parseDouble(String str) throws ParseException {
		return parseNumber(str).doubleValue();
	}


	/**
	 * 文字列filenameから拡張子を取得して返します。拡張子が無い場合は
	 * 空文字列を返します。
	 */
	public static String getExt(String filename) {
	  String ext = "";
	  int pos = filename.lastIndexOf(".");
	  if (pos > 0) {
		ext = filename.substring(pos + 1, filename.length());
	  }
	  return ext;
	}


	/**
	 * 日付区切り文字の除去
	 *
	 * @return String 評価文字列
	 */
	public static String delDateSp(String s) {
		return regReplaceAll("/","",s);
	}

	/**
	 * 数値区切り文字の除去
	 *
	 * @return String 評価文字列
	 */
	public static String delAmtSp(String s) {
		if (s==null){
			return s;
		}else{
			return regReplaceAll(",","",s);
		}
	}

	/**
	 * 画面表示用　日付フォーマット変換
	 *
	 * @return String 評価文字列
	 */
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

	/**
		 * String型で指定された数値データに、小数点以下の桁数をそろえるために0
		 * を追加したり、整数部にカンマを追加したりします。
		 * @param src 処理対象の文字列(double型として認識できること)
		 * @param scale 小数部の桁数
		 * @return
		 */
		public static String formatNumber(String src, int scale){
			if(src == null){
				return null;
			}
			src = src.trim();
			StringBuffer srcbuf = new StringBuffer(src);

			/* まず整数部、小数部の桁数を求める */
			int seiketa, shouketa;
			int dotloc = src.indexOf('.');
			if(dotloc == -1){
				/* 小数点がない */
				seiketa = src.length();
				shouketa = 0;
			} else {
				/* その他(先頭が小数点の場合も含む) */
				seiketa = dotloc;
				shouketa = src.length() - dotloc - 1;
			}

			/* shouketaがscaleに満たない場合、右に0を足す */
			if(scale>0 && shouketa == 0){
				srcbuf.append('.');
			}
			for(int i=shouketa; i<scale; i++){
				srcbuf.append('0');
			}
			/* カンマ区切り */
			if(seiketa > 3){
				int startIndex = seiketa % 3;
				if(startIndex == 0){
					startIndex = 3;
				}
				for(int i=startIndex; i<seiketa; i+=3){
					srcbuf.insert(i++, ',');
				}
			}
			/* 先頭が小数点の場合、先頭に0をつける */
			if(dotloc == 0){
				srcbuf.insert(0, '0');
			}
			return srcbuf.toString();
		}






		/**
		 * サイトIDからマーチャントIDの取得
		 *
		 */
		public static String getSiteToMrch(String siteId){
			if (ValidateUtils.isEmpty(siteId)){
				return siteId;
			}
			//サイトIDの先頭6桁をマーチャントIDとする
			if (siteId.length()>=6){
				return siteId.substring(0,6);
			}else{
				return siteId;
			}
		}

		/**
		 * 月が１桁の場合、前方に"0"を付加する
		 *
		 * @param year
		 * @return
		 */
		public static String padMonth(String month){
			if (!ValidateUtils.isEmpty(month) && month.length()==1){
				month= "0"+month;
			}
			return month;
		}

		/**
		 * 月の前方の0を削除する
		 *
		 * @param year
		 * @return
		 */
		public static String trimMonth(String month){
			if (!ValidateUtils.isEmpty(month)){
				try{
					month =Integer.valueOf(month).toString();
				}catch (Exception e){

				}
			}
			return month;
		}

		/**
		 * 年が２桁の場合、前方に"20"を付加する
		 *
		 * @param year
		 * @return
		 */
		public static String padYear(String year){
			if (!ValidateUtils.isEmpty(year)){
				if (year.length()==2){
					year= "20"+year;
				} else if (year.length()==1){
					year= "200"+year;
				}

			}
			return year;
		}

		/**
		 * 年が2桁以上の場合、後方の2桁を返す
		 *
		 * @param year
		 * @return
		 */
		public static String trimYear(String year){
			if (!ValidateUtils.isEmpty(year)){
				if (year.length()>3){
					year = year.substring(year.length()-2,year.length());
				}else if (year.length()==1){
					year = "0"+year;
				}
			}
			return year;
		}

		/**
		 * フルメールアドレスからドメイン部分を切り取る
		 *
		 * @param mailaddr
		 * @return
		 */
		public static String splitMailDoamin(String mailaddr){
			String domain="";
			try{
				domain=mailaddr.split(Consts.MAIL_DMIN_SEPARATERE)[1];
			}catch (Exception e){
			}
			return domain;
		}
		/**
		 *
		 * @param arg1
		 * @param arg2
		 * @return
		 */
		public static String rightPad(String arg1,int arg2){
			return StringUtils.rightPad(arg1,arg2);
		}

		/**
		 *
		 * @param arg1
		 * @param arg2
		 * @param arg3
		 * @return
		 */
		public static String rightPad(String arg1,int arg2,String arg3){
			return StringUtils.rightPad(arg1,arg2,arg3);
		}

		/**
		 *
		 * @param arg1
		 * @param arg2
		 * @return
		 */
		public static String leftPad(String arg1,int arg2){
			return StringUtils.leftPad(arg1,arg2);
		}

		/**
		 *
		 * @param arg1
		 * @param arg2
		 * @param arg3
		 * @return
		 */
		public static String leftPad(String arg1,int arg2,String arg3){
			return StringUtils.leftPad(arg1,arg2,arg3);
		}

		/**
		 *
		 * @param arg1
		 * @param arg2
		 * @param arg3
		 * @return
		 */
		public static String replace(String arg1,String arg2,String arg3){
			return StringUtils.replace(arg1,arg2,arg3);
		}

		/**
		 *
		 * @param arg1
		 * @param arg2
		 * @param arg3
		 * @param agr4
		 * @return
		 */
		public static String replace(String arg1,String arg2,String arg3,int agr4){
			return StringUtils.replace(arg1,arg2,arg3,agr4);
		}

		/**
		 * 先頭を小文字に変換
		 *
		 * @param arg1
		 * @return
		 */
		public static String uncapitalise(String arg1){
			if(arg1==null || arg1.length() == 0) {
				return "";
			}
			return arg1.substring(0,1).toLowerCase() + arg1.substring(1);

		}

		/**
		 *
		 * @param arg1
		 * @param arg2
		 * @return
		 */
		public static String remove(String arg1,String arg2){
			return StringUtils.remove(arg1,arg2);
		}



		public static String getSetlStatString(Integer stat){
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



	/**
		 * 分割処理
		 * Stringのsplitでは、最終行にdelimiterがあった場合
		 * 分割されない為
		 *
		 * @param value
		 * @param delimiter
		 * @return
		 */
		public static String[] split(String value,String delimiter){

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



		/**
		 * メールアドレス:@以降はマスキング
		 * @param telNo
		 * @param dispLen
		 * @return 例：　info@alij.ne.jp → info@*********
		 * 例外の場合、""を返す
		 */
		public static String parseUserMail(String mail){

			try{

				//空か不正なメールアドレスかチェック
				if (ValidateUtils.isEmpty(mail) || !ValidateUtils.isEmail(mail)){
					return "";
				}else{

					int arobasPosition = mail.indexOf("@")+1;	//【@】の位置
					int nbLetters = mail.substring(arobasPosition).toString().length();	//文字列の長さ

					//【@】から【*】を挿入
					char[] stars = new char[nbLetters];
					Arrays.fill(stars, '*');

					return mail.substring(0,arobasPosition)+ new String(stars);
				}
			}catch(Exception e){
				return "";
			}
		}

		/**
		 * 電話番号：真ん中3桁を非表示
		 * 対応範囲：　数字+ハイフン+空欄
		 * 例： xxx-xxxx-xxxx, xxx-xxxxx xxxx, xxxxxxxxxxx,　xxx xxxx xxxx　など
		 * 例外の場合、""を返す
		 * @param mail
		 * @return
		 */
		public static String parseUserTel(String tel){

			try{
				String telephone = tel.trim().replace(" ", "");

				if (ValidateUtils.isEmpty(telephone)){
					return "";
				}else{

					//ハイフン2つ以上を持っているか確認
					boolean hasTwoHaifun = (telephone.indexOf("-")!=-1 && telephone.indexOf("-")!= telephone.lastIndexOf("-"))?true:false;

					//2つのハイフンを含む配列なら・・・
					if(hasTwoHaifun){
						int firstHaifun 	= telephone.indexOf("-",0);		//一つ目のハイフン位置
						int secondHaifun 	= telephone.lastIndexOf("-");	//二つ目のハイフン位置

						//1つ目と二つ目のハイフンの中に【*】を挿入
						char[] stars = new char[(secondHaifun-firstHaifun-1)];
						Arrays.fill(stars, '*');

						return telephone.substring(0,firstHaifun+1) + new String(stars) + telephone.substring(secondHaifun);

					}else{

						//5桁以上のもなら、マスキング
						if(telephone.length()>=5){
							int middle = (telephone.length())/2;	//文字列の真ん中のIndex（四捨五入）
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
		 * @author thuna
		 * @Description: parse a such date to format date yyyy/mm/dd
		 * @param date
		 * @return String
		 */
		public static final String parseToFormatString(Date date){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
			
			return simpleDateFormat.format(calendar.getTime());
		}
		
		/**
		 * @author thuna
		 * @Description: parse string to date with format yyyy/MM/dd
		 * @param date
		 * @return
		 */
		public static final Date parseFromFormatString(String date){
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
		 * @author thuna
		 * @Description: encrypt a input string by md5 algorithm
		 * @param plainText
		 * @return String
		 */
		public static final String encryptedMd5(String plainText){
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
			return null;
		}
}
