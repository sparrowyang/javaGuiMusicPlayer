package gui;

import net.MusicSheet;


/**
 * 
 * 歌单用于展示的歌单类
 * 将MusicSheet   处理成 用于一个显示的    string
 * @author sparrow
 *
 */
public class AMusicSheetLable  {
	String sheetName;
	String sheetCreator;
	String sheetId;
	String amusicSheetString;
	/*
	 * 构造参数是一个musicsheet
	 */
	public AMusicSheetLable(MusicSheet b) {
		// TODO Auto-generated constructor stub
		super();
		sheetId=new String();
		sheetName=new String();
		sheetCreator=new String();
		loadMusicSheet(b);
		amusicSheetString=sheetName;
		//this.setText(sheetId+sheetName+sheetCreator);
		//this.setSize(100, 30);
		
	}
	
	/*
	 * 获取歌单
	 * 
	 */
	public void loadMusicSheet(MusicSheet a) {
		sheetId=a.getlistid();
		sheetCreator=a.getCreator();
		sheetName=a.getName();
		
		
	}
	

}
