package common.util;



/**
 * <br>
 *
 * @author
 * @version $Id: C.java 1304 2011-06-29 10:32:50Z takashi.suzuki $
 */
public class Consts {

	/**
	 * セッション名
	 *
	 */
	public static final String SESSION_LOING_MASTER_USER = "master_user";
	public static final String SESSION_LOING_DEVELOP_USER = "develop_user";
	public static final String SESSION_LOING_CUSTOMER = "customer";


	/**
	 * サービス種別区分
	 *
	 */
	public static final String SETL_TYPE_CONT 	="CONT";
	public static final String SETL_TYPE_DELY	="DELY";
	public static final String SETL_TYPE_REAL	="REAL";
	public static final String SETL_TYPE_CHECK	="CHEK";


	/**
	 * 通貨種別区分
	 *
	 */
	public static final String CURRENCY_JPY	="JPY";//日本円
	public static final String CURRENCY_USD	="USD";//米ドル
	public static final String CURRENCY_EUR	="EUR";//ユーロ

	public static final String CURRENCY_THB	="THB";
	public static final String CURRENCY_GBP	="GBP";
	public static final String CURRENCY_CAD	="CAD";
	public static final String CURRENCY_CHF	="CHF";
	public static final String CURRENCY_SEK	="SEK";
	public static final String CURRENCY_DKK	="DKK";
	public static final String CURRENCY_IDR	="IDR";
	public static final String CURRENCY_NOK	="NOK";
	public static final String CURRENCY_PKR	="PKR";
	public static final String CURRENCY_PHP	="PHP";
	public static final String CURRENCY_QAR	="QAR";
	public static final String CURRENCY_AED	="AED";
	public static final String CURRENCY_AUD	="AUD";
	public static final String CURRENCY_HKD	="HKD";
	public static final String CURRENCY_INR	="INR";
	public static final String CURRENCY_SAR	="SAR";
	public static final String CURRENCY_KWD	="KWD";
	public static final String CURRENCY_KRW	="KRW";
	public static final String CURRENCY_SGD	="SGD";
	public static final String CURRENCY_NZD	="NZD";
	public static final String CURRENCY_ZAR	="ZAR";
	public static final String CURRENCY_CZK	="CZK";
	public static final String CURRENCY_MXN	="MXN";
	public static final String CURRENCY_RUB	="RUB";
	public static final String CURRENCY_HUF	="HUF";
	public static final String CURRENCY_PLN	="PLN";
	public static final String CURRENCY_SKK	="SKK";
	public static final String CURRENCY_MYR	="MYR";

	/**
	 * カード種別区分
	 *
	 */
	public static final String CARD_VISA		="VISA";
	public static final String CARD_MASTER		="MASTER";
	public static final String CARD_JCB			="JCB";
	public static final String CARD_DINERS		="DINERS";
	public static final String CARD_AMMEX		="AMMEX";


	/**
	 * カード種別判断（正規表現）
	 *
	 */
	public static final String REG_VISA			="^4.*";
	public static final String REG_MASTER		="^5.*";
	public static final String REG_JCB			="^35.*";
	public static final String REG_AMMEX		="^34.*|^37.*";
	public static final String REG_DINERS		="^30.*|^36.*|^38.*";




	/**
	 * メールのドメイン・アドレスの結合子
	 */
	public static final String MAIL_DMIN_SEPARATERE	="@";

	/**
	 * HTTPOKステータス
	 *
	 */
	public static final String HTTP_OK="200";

		/**
	 * 決済ステータス区分
	 * ステータス追加時は「CustInquiryLogic」の「changeIndexDown」にも追加してください。
	 */
	public static final int SETL_OK				= 1;	//決済成功
	public static final int SETL_AUTH			= 0;	//決済確定待ち
	public static final int SETL_VOID			=-1;	//決済取消
	public static final int SETL_CB				=-2;	//決済失敗
	public static final int SETL_CONT_CANCEL	=-3;	//継続決済退会
	public static final int SETL_NG				=-9;	//決済失敗
	public static final int SETL_ERR			=-99;   //システムエラー

	public static final int SETL_VOID_REQ		= 2;	//決済取消依頼
	public static final int SETL_CAN_REQ		= 3;	//AUTH決済取消依頼
	public static final int SETL_RETRIEVAL		= 4;	//リトリーバル
	public static final int SETL_CANCEL			= -4;	//AUTH決済取消

	public static final int SETL_EXCEPTION		=-99;	//決済例外エラー(EXCEPTION）

	public static final String SETL_OK_STR			= "SALE";	//決済成功
	public static final String SETL_AUTH_STR		= "AUTH";	//決済確定待ち
	public static final String SETL_VOID_STR		= "VOID";	//決済取消
	public static final String SETL_NG_STR			= "NG";		//決済失敗
	public static final String SETL_CB_STR			= "CB";		//決済失敗
	public static final String SETL_EXCEPTION_STR	= "ERR";	//決済例外エラー(EXCEPTION）
	public static final String SETL_VOID_REQ_STR	= "VOID_REQ";//決済取消依頼
	public static final String SETL_CAN_REQ_STR		= "CAN_REQ";//AUTH決済取消依頼
	public static final String SETL_RETRIEVAL_STR	= "RETRIEVAL";//リトリーバル
	public static final String SETL_CANCEL_STR		= "CANCEL";//AUTH決済取消

	public static final String CHK_VALD_SUB			= "SUB";	//マスター問い合わせ状況（SUB）

	public static final String[] VALDS = {"STOP", "ACTIVE"}; // 0: STOP, 1: ACTIVE
	public static final String[] REGIST_TYPES = {"仮登録", "本登録"}; // -1: 仮登録, 1: 本登録
	public static final String[] STATUS_IDS = {"有効", "アンインストール"}; // ok: 有効, ng: アンインストール
	public static final String[] COMP_TYPES = {"個人", "法人"}; // 0: 個人, 1: 法人
	public static final String[] PREFECTURES = {"北海道", "青森県", "岩手県", "宮城県", "秋田県", 
												"山形県", "福島県", "東京都", "神奈川県", "埼玉県",
												"千葉県", "茨城県", "栃木県", "群馬県", "山梨県",
												"新潟県", "長野県", "富山県", "石川県", "福井県",
												"愛知県", "岐阜県", "静岡県", "三重県", "大阪府",
												"兵庫県", "京都府", "滋賀県", "奈良県", "和歌山県",
												"鳥取県", "島根県", "岡山県", "広島県", "山口県",
												"徳島県", "香川県", "愛媛県", "高知県", "福岡県",
												"佐賀県", "長崎県", "熊本県", "大分県", "宮崎県",
												"鹿児島県", "沖縄県"};
	public static final String[] BANK_ACNTS = {"当座", "普通"}; // 0: 当座, 1: 普通
	public static final String[] SETL_PAY_CRCY = {"JPY", "USD", "EUR", "GBP", "HKD", "KRW", "MYR", "SGD"};
		
	//some default value
	public static final double DEFAULT_AMOUNT = -1;
	public static final int DEFAULT_SETLSTAT = 123456;
	
	// Define some constants for maximum length of some textbox
	public static final int CUST_NAME_MAXIMUM_LENGTH = 30;
	public static final int CUST_PASS_MAXIMUM_LENGTH = 30;
	public static final int CARD_NO_MAXIMUM_LENGTH = 17;
	public static final int CARD_NAME_MAXIMUM_LENGTH = 30;
	public static final int DEVELOP_ID_MAXIMUM_LENGTH = 30;
	public static final int COMP_NAME_MAXIMUM_LENGTH = 255;
	public static final int COMP_NAME_KANA_MAXIMUM_LENGTH = 255;
	public static final int COMP_PRESIDENT_MAXIMUM_LENGTH = 30;
	public static final int COMP_CHARGER_MAXIMUM_LENGTH = 30;
	public static final int COMP_POST_MAXIMUM_LENGTH = 30;
	public static final int TEL_MAXIMUM_LENGTH = 30;
	public static final int FAX_MAXIMUM_LENGTH = 30;
	public static final int ZIP_MAXIMUM_LENGTH = 7;
	public static final int ADDR_MAXIMUM_LENGTH = 255;
	public static final int ADDR2_MAXIMUM_LENGTH = 255;
	public static final int ADDR_KANA_MAXIMUM_LENGTH = 255;
	public static final int ADDR2_KANA_MAXIMUM_LENGTH = 255;
	public static final int BANK_NAME_MAXIMUM_LENGTH = 30;
	public static final int BANK_NAME_KANA_MAXIMUM_LENGTH = 30;
	public static final int BANK_BRANCH_CODE_MAXIMUM_LENGTH = 10;
	public static final int BANK_BRANCH_NAME_MAXIMUM_LENGTH = 30;
	public static final int BANK_ACNT_NUM_MAXIMUM_LENGTH = 30;
	public static final int BANK_ACNT_NAME_MAXIMUM_LENGTH = 30;
	public static final int NOTE_MAXIMUM_LENGTH = 500;
	public static final int ACNT_ID_MAXIMUM_LENGTH = 30;
	public static final int ACNT_NAME_MAXIMUM_LENGTH = 30;
	public static final int CONNECTION_ID_MAXIMUM_LENGTH = 30;
	public static final int CONNECTION_PASS_MAXIMUM_LENGTH = 30;
	//
	public static final String RESOURCES_PATH = "/resources";
}
