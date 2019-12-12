package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;
import net.JsonGeter;
import net.MusicSheet;

/**
 * 整合每个歌单label  成为一个scrollpanel
 * @author sparrow
 */
public class MusicList {
	public JScrollPane sPane;
	JList<String> ss;
	String[] sheetArray;
	public static MusicSheet[] a;
	//Map<String, String> songMap;
	public MusicList() throws Exception {
		a=JsonGeter.jsonToMap();
		//songMap=a.;
		// TODO Auto-generated constructor stub
		ss=new JList<String>();
		load();
		ss.setListData(sheetArray);
		//歌单字体
		ss.setFont(new Font("宋体",Font.BOLD,20));
		ss.setBackground(Color.black);
		ss.setForeground(Color.white);
		//JScrollPane需要用JList初始化！！
		sPane=new JScrollPane(ss);
		sPane.setBackground(Color.black);
		sPane.setLocation(0, 0);
		sPane.setSize(200,700);
		sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);;
	}
	
	
	public void loadSheetFromNet() throws Exception {
		a=JsonGeter.jsonToMap();
		//songMap=a.;
		// TODO Auto-generated constructor stub
		ss=new JList<String>();
		load();
		ss.setListData(sheetArray);
		//歌单字体
		ss.setFont(new Font("宋体",Font.BOLD,20));
		//JScrollPane需要用JList初始化！！
		sPane=new JScrollPane(ss);
		sPane.setLocation(0, 0);
		sPane.setSize(200,700);
	}
	/**
	 * 从网络获取歌单信息
	 * @throws Exception
	 */
	public void load() throws Exception {
		
		sheetArray=new String[a.length];
		//sheetArray=new AMusicSheetLable[a.length];
		for(int i=0;i<a.length;i++) {
			sheetArray[i]=(new AMusicSheetLable(a[i]).amusicSheetString);
			
		}
	}

	

	
}
