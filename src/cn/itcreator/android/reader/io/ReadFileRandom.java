package cn.itcreator.android.reader.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.util.Log;

public class ReadFileRandom {
	//private RandomAccessFile randomAccessFile = null;
	private InputStream dataInputStream = null;
	private String filePath = null;
	public ReadFileRandom(String path) {
		String tag = "ReadFileRandom";
		this.filePath = path;
		try {
		//	randomAccessFile = new RandomAccessFile(filePath,"rw");
			dataInputStream = new DataInputStream(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			Log.d(tag, "Exception :"+e.getMessage());
		}
	}
	
	/**
	 * ���µ������
	 */
	public void openNewStream(){
		close();
		try {
			//randomAccessFile = new RandomAccessFile(filePath,"rw");
			dataInputStream = new DataInputStream(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
		}
	}
	/**
	 * ��ȡ���
	 * @param length ��ȡ�೤
	 * @return
	 */
	public byte[] readBytes(int length){
		byte[] b = new byte[length];
		try {
			//randomAccessFile.read(b);
			if(dataInputStream == null){
				dataInputStream = new DataInputStream(new FileInputStream(filePath));
			}
			dataInputStream.read(b);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}
	
	
	/**
	 * ��ȡ��ݣ�������ݼ��ص��ֽ�������
	 * @param buffer
	 * @return ����ʵ�ʶ�ȡ���ֽ���
	 */
	public int readBytes(byte[] buffer){
		int i = 0;
		try {
			i= dataInputStream.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}	
		return i;
	}
	
	
	/**
	 * ����ֽ�
	 * @param length ������
	 */
	public void skip(int length){
		try {
			//dataInputStream.skipBytes(length);
			dataInputStream.skip(length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �������
	 * @param length ������
	 */
	public void fastSkip(int length ){
		readBytes(length);
	}
	
	
	
	/**
	 * ���ٶ�Ϊ���ļ��е�λ��
	 * @param location ��Ϊ�ĵص�
	 */
	public void locate(int location){
		readBytes(location);
	}

	/**
	 * �õ��ļ��ĵ�ǰλ��
	 * @return
	 */
	/*public long getCurrentLocation(){
		long i = 0;
		 try {
			i = randomAccessFile.getFilePointer();
		} catch (IOException e) {
			 return i;
		}
		 return i;
	}*/
	/**
	 * ȡ���ļ��ĳ���
	 * @return
	 */
	public long getFileLength(){
		long i =0;
		try {
			i= new File(filePath).length();
		} catch (Exception e) {
		}
		return i;
	}
	
	
	/**
	 * �ر���
	 */
	public void close(){
		if(null!=dataInputStream)
			try {
				dataInputStream.close();
			} catch (IOException e) {
			}	
	}

	
	public static void main(String[] args) {
		
		ReadFileRandom r = new ReadFileRandom("src/mayun.txt");
		byte[] b = new byte[10]; 
		r.readBytes(b);
		System.err.println(new String(b));
		r.skip(10);
		b = r.readBytes(21);
		System.out.println("=================");
		System.err.println(new String(b));
		r.close();
	}
	
}
