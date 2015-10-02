package common.util;

import java.text.DecimalFormat;

public class CustomDecimalFormat {
	private DecimalFormat decimalFormat;
	
	public CustomDecimalFormat(String format){
		decimalFormat = new DecimalFormat(format);
	}

	public String format(Object dValue){
		String result = "";
		if(dValue != null && (dValue instanceof Integer || dValue instanceof Double)){
			result = decimalFormat.format(dValue);
		}
		return result;
	}
}
