package cn.itcreator.android.reader.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static String dateToString(Date d){
		Calendar calendar = Calendar.getInstance();
	//	calendar.setTime(d);
		StringBuilder sb= new StringBuilder();
		sb.append(calendar.get(Calendar.YEAR));
		sb.append("-");
		sb.append(calendar.get(Calendar.MONTH)+1);
		sb.append("-");
		sb.append(calendar.get(Calendar.DATE));
		sb.append(" ");
		sb.append(calendar.get(Calendar.HOUR_OF_DAY));
		sb.append(":");
		sb.append(calendar.get(Calendar.MINUTE));
		sb.append(":");
		sb.append(calendar.get(Calendar.SECOND));
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(dateToString(new Date(System.currentTimeMillis())));
	}
	
}
