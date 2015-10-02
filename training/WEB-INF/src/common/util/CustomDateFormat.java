package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateFormat{
	private SimpleDateFormat simpleDateFormat;
	
	public CustomDateFormat(String format){
		simpleDateFormat = new SimpleDateFormat(format);
	}
	public String format(Date date)
    {
        String result = "";
		if(date != null){
        	result = simpleDateFormat.format(date);
        }
        return result;
    }
	public Date parse(String text){
		try {
			return simpleDateFormat.parse(text);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
