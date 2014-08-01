package cn.itcreator.android.reader.paramter;

public class Constant {

	public final static String M_LINE_NUMBER = "mLineNumber";
	public final static String M_OFFSET = "mOffset";
	public final static String M_IS_MARK = "mIsMark";
	public final static String BOOK = "book";
	public final static String MARK = "mark";
	public final static int DATA_LENGTH = 65535;
	public static String FILE_PATH = "";
	public static String IMAGE_PATH = "";
	public static int BOOK_ID_IN_DATABASE=0;
	
	/**For the key of file path ,intent use it*/
	public final static String FILE_PATH_KEY = "filePath";

	/** the font size var */
	public final static int FONT16 = 16;
	public final static int FONT18 = 18;
	public final static int FONT20 = 20;
	public final static int FONT22 = 22;
	public final static int FONT24 = 24;
	
	

	/** text encode */
	public final static String GB2312 = "GB2312";
	public final static String GBK = "GBK";
	public final static String UTF8 = "UTF-8";

	/** the text color */
	public final static String YELLOW = "��ɫ";
	public final static String BLACK = "��ɫ";
	public final static String RED = "��ɫ";
	public final static String GRAY = "��ɫ";
	public final static String GREEN = "��ɫ";
	public final static String BLUE = "��ɫ";
	public final static String WHITE = "��ɫ";

	/** read file error */
	public final static String FILENOTFOUND = "�ļ��޷���ȡ���뷵��...";
	public final static String NODATAINFILE = "�ļ���û����ݣ�";

	public final static String BOOKMARK = "��ǩ";

	/** mesure text */
	public final static String UPPERASCII = "A";
	public final static String LOWERASCII = "a";
	public final static String CHINESE = "��";

	/** Percent */
	public final static String PERCENTCHAR = "%";

	
	/** background */
	public static String QXLY = "������Ҷ";
	public static String HZHX = "�������";
	public static String HYDQ = "��ҹ����";
	public static String HSSP = "��ɫ��Ƥ";
	public static String HSSB = "��ɫ�鱾";
	public static String LYXG = "�����ǹ�";
	public static String YJST = "�侰˼��";
	public static String PGHS = "ƻ���ɫ";

	/** SQL */
	/** database name */
	public final static String DB_NAME = "coolreader.db";
	public final static int DB_VERSION = 2;

	/** book table name and field */
	public final static String BOOK_TABLE_NAME = "book";
	public final static String BOOK_ID = "bid";
	public final static String BOOK_PATH = "bpath";

	/** book mark table name and field */
	public final static String BOOK_MARK_TABLE_NAME = "bookmark";
	public final static String BOOK_MARK_ID = "bmid";
	public final static String BOOK_MARK_NAME = "bmname";
	public final static String BOOK_MARK_OFFSET = "bmoffset";
	public final static String Book_MARK_SAVETIME = "bmsavetime";
	/** create table */
	public final static String CREATE_TABLE_BOOK = "create table IF NOT EXISTS book(bid integer primary key autoincrement,bpath varchar(255) not null);";
	public final static String CREATE_TABLE_BOOK_MARK = "create table IF NOT EXISTS bookmark(bmid integer primary key autoincrement,"
			+ "bid integer not null,bmname varchar(50) not null,bmoffset integer not null,bmsavetime varchar(20) not null);";
}
