package cn.itcreator.android.reader.domain;

public class BookInfo {
	
	public byte type;
	public short pgkSeed;
	public String title;
	public String author;
	public String year;
	public String month;
	public String day;
	public String gender;
	public String publisher;
	public String vendor;
	public byte[] cover;
	public int cid;
	public int contentLength;
	
	public String getDate(){
		StringBuffer sb = new StringBuffer();
		sb.append(year);
		sb.append("-");
		sb.append(month);
		sb.append("-");
		sb.append(day);
		return sb.toString();
	}

}
