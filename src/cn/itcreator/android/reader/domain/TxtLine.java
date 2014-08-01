package cn.itcreator.android.reader.domain;

public class TxtLine {
	
    public int offset = 0;
    
    public int lineLength = 0;
    
    /**the data length before this line and contain current line*/
    public int beforeLineLength=0;
    public TxtLine() {
        this(0, 0,0);
    }

    public TxtLine(int offset, int lenght,int beforeLineLength) {
        this.offset = offset;
        this.lineLength = lenght;
        this.beforeLineLength=beforeLineLength;
    }

}
