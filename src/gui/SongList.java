package gui;

import java.awt.Color;
import java.awt.Font;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * 主界面中，用于显示每个歌单中歌曲的类
 * 
 * @author sparrow
 */
public class SongList {
	public JScrollPane songJScrollPane;
	public String[] songNameString;
	public String[] mD5ValueString;
	public JList<String> songsJList;
	public SongList() {
		
		// TODO Auto-generated constructor stub
		songsJList=new JList<String>();
		songsJList.setSize(100, 200);
		songsJList.setBackground(Color.black);
		songsJList.setForeground(Color.white);
		songJScrollPane=new JScrollPane(songsJList);
		songsJList.setFont(new Font("黑体",Font.BOLD,20));
		//songJScrollPane.setLayout(null);
		songJScrollPane.setSize(700,200);
		songJScrollPane.setBackground(Color.black);
		
		
	}
	
	
	public void roadSongs(Map<String, String> map) throws UnsupportedEncodingException {
		//songsJList.setListData(null);
		//songsJList.removeAll();
		// TODO Auto-generated method stub
		mD5ValueString=new String[map.size()];
		songNameString=new String[map.size()];
		int i=0;
		for(String key : map.keySet()) {
			//String a=new String(k.getBytes(),"utf-8");
			mD5ValueString[i]=new String(key.getBytes(), "utf-8");
			songNameString[i]=new String(map.get(key).getBytes(), "utf-8");
			i++;
			
		}
		songsJList.setListData(songNameString);
		
	}
	
}
