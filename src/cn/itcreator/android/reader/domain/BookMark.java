package cn.itcreator.android.reader.domain;

import java.io.Serializable;

public class BookMark implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7887363738929016732L;
	/**the offset in file*/
	private int currentOffset=0;
	/**book mark name*/
	private String markName="";
	private int bookId = 0;
	
	private int bookMarkId = 0;
	
	private String saveTime = "0000-00-00 00:00:00";
	
	public BookMark() {
	}
	public BookMark(int offset,String markName,int bookId){
		this.currentOffset=offset;
		this.markName = markName;
		this.bookId = bookId;
	}
	
	public int getCurrentOffset() {
		return currentOffset;
	}
	public void setCurrentOffset(int currentOffset) {
		this.currentOffset = currentOffset;
	}
	public String getMarkName() {
		return markName;
	}
	public void setMarkName(String markName) {
		this.markName = markName;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBookMarkId() {
		return bookMarkId;
	}
	public void setBookMarkId(int bookMarkId) {
		this.bookMarkId = bookMarkId;
	}
	
	public String getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}
	@Override
	public String toString() {
		return markName + "   "+saveTime;
	}
	
}
