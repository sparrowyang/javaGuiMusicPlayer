package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;


/**
 * 播放列表
 * 显示已播放或者正在播放的歌曲
 * @author sparrow
 *
 */

public class PlayedList {
	
	public JButton calluploadButton;
	
	//储存已播放的文件
	static Vector<File> playedSongFile;
	public JScrollPane playedJScrollPane;
	//歌名列表
	static JList<String> songJList;
	//
	static Vector<String> songNameString;
	
	//本地音乐五MD5值
	String md5String;
	public PlayedList() {
		// TODO Auto-generated constructor stub
		calluploadButton=new JButton("上传");
		calluploadButton.setSize(40, 30);
		calluploadButton.setLocation(0, 500);

		songJList=new JList<String>();
		songJList.setBackground(Color.black);
		songJList.setForeground(Color.white);
		
		md5String=new String();
		songNameString=new Vector<String>();
		playedSongFile=new Vector<File>();

		songJList.setListData(songNameString);
		
		playedJScrollPane=new JScrollPane(songJList);
		calluplistener();
	}
	
	/**
	 * 更新 列表
	 */
	public static void refresh() {
		songJList.setListData(songNameString);
	}
	
	public void calluplistener() {
		calluploadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UpSheetWindows();
				
			}
		});
		
	}
	
}
	

