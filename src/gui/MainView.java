package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.Headbar;
import gui.Menu;
import gui.MusicList;
import gui.PlayedList;
import gui.PlayingPanel;
import gui.SongList;
import net.FileDonwload;



/**
 * Demo class
 * @author sparrow
 * @date 2019/11/15
 */
public  class MainView {
	JFrame firstJFrame=new JFrame("Sparrow MusicPlayer1.0");
	//歌曲列表
	JPanel musicListPanel=new JPanel();
	static JPanel playingPanel=new JPanel();
	JPanel toolbarPanel=new JPanel();
	JPanel headPanel=new JPanel();
	JPanel messagePanel=new JPanel();
	//JScrollPane playedJScrollPane;
	//unse
	//JPanel localListPanel=new JPanel();
	
	SongList  songList=new SongList();
	MusicList musicList=new MusicList();		
	Menu playingControlMenu=new Menu();
	PlayingPanel showmessagepanel=new PlayingPanel();
	PlayedList playedList=new PlayedList();
	
	
	public MainView() throws Exception {
		
		
		//100*666
		// TODO Auto-generated constructor stub
		firstJFrame.setSize(1000,700);
		firstJFrame.setBackground(Color.red);
		firstJFrame.setLayout(null);
		firstJFrame.setResizable(false);
		//上
		headPanel.setBackground(new Color(154,0,15));
		headPanel.setLocation(0, 0);
		headPanel.setSize(firstJFrame.getWidth(), 100);
		headPanel.add(new Headbar().label);
		//左
		musicListPanel.setLayout(new GridLayout(1,1));
		musicListPanel.setSize(150,firstJFrame.getHeight()-headPanel.getHeight()-100);
		musicListPanel.setLocation(0, 100);
		musicListPanel.setFont(new Font("宋体",Font.BOLD,10));
		musicListPanel.setBackground(Color.black);
		musicListPanel.add(musicList.sPane);
		
		//中
		playingPanel.setSize(630,500);
		playingPanel.setBackground(Color.black);
		playingPanel.setLocation(musicListPanel.getWidth(), headPanel.getHeight());
		playingPanel.add(songList.songJScrollPane);
		playingPanel.add(PlayingPanel.playingJPanel);
		playingPanel.setLayout(null);
		//右
		messagePanel.setSize(firstJFrame.getWidth()-musicListPanel.getWidth()-playingPanel.getWidth(),firstJFrame.getHeight()-headPanel.getHeight()-100);
		messagePanel.setBackground(Color.black);
		messagePanel.setLocation(musicListPanel.getWidth()+playingPanel.getWidth(), headPanel.getHeight());
		messagePanel.add(playedList.playedJScrollPane);
		messagePanel.add(playedList.calluploadButton);
		//messagePanel.add(playedJScrollPane);
		//下
		toolbarPanel.setLayout(null);
		toolbarPanel.setSize(firstJFrame.getWidth(),120);
		toolbarPanel.setBackground(Color.black);
		toolbarPanel.setLocation(0,headPanel.getHeight()+musicListPanel.getHeight());
		
		toolbarPanel.add(Menu.playJProgressBar);
		toolbarPanel.add(playingControlMenu.beforeSongButton);
		toolbarPanel.add(playingControlMenu.playingButton);
		toolbarPanel.add(playingControlMenu.pauseButton);
		toolbarPanel.add(playingControlMenu.nextSongButton);
		toolbarPanel.add(Menu.fileBlower);
		
		firstJFrame.add(headPanel,BorderLayout.NORTH);
		firstJFrame.add(musicListPanel,BorderLayout.WEST);
		firstJFrame.add(messagePanel, BorderLayout.EAST);
		firstJFrame.add(toolbarPanel,BorderLayout.SOUTH);
		firstJFrame.add(playingPanel);
		firstJFrame.setDefaultCloseOperation(3);//EXIT_ON_CLOSE
		firstJFrame.setVisible(true);
		
		chooseAMusic();
		downLoadMusic();
		
	}
	
	/**
	 * 选中歌单列表歌单展示事件监听
	 */
	public void chooseAMusic() {
		
		musicList.ss.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				//refreshSonglist();
				try {
					songList.roadSongs(MusicList.a[musicList.ss.getSelectedIndex()].getMusicItems());
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 双击歌曲下载监听
	 */
	public void downLoadMusic() {
		songList.songsJList.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				String mD5ValueString=songList.mD5ValueString[songList.songsJList.getSelectedIndex()];
				String name=songList.songNameString[songList.songsJList.getSelectedIndex()];
				String path="D:\\spar music download\\";
				// TODO Auto-generated method stub
				if(songList.songsJList.getSelectedIndex() != -1) {	
					if(e.getClickCount() == 2) {
						System.out.println("歌曲下载");
						try {
							new FileDonwload().DownLoading(mD5ValueString, name, path);
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}	
		});
	}
}





