package gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

//import com.sun.java.util.jar.pack.Package.Class.Member;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Menu {
	public static JProgressBar playJProgressBar;
	static JButton loadNetButton;
	public JButton nextSongButton;
	public JButton beforeSongButton;
	public JButton playingButton;
	public JButton pauseButton;
	public static JButton fileBlower;
	public JFrame choosefileFrame;
	//本地选择文件
	static File selectedFile;
	//音乐播放列表
	//static Vector<File> playedListFile;
	//static JList<File> playedList;
	//播放线程
	Thread thread;
	Thread barThread;
	static boolean isPlaying=false;
	InputStream in;
	static Player player;
	static Thread playingThread;
	public Menu() throws IOException {
		
		// TODO Auto-generated constructor stub
		
		/*loadNetButton=new JButton("🔄");
		loadNetButton.setSize(60,30);
		loadNetButton.setLocation(40,10);*/
		//playedListFile=new Vector<File>();
		//playedList=new JList<File>();
		
		
		nextSongButton=new JButton("⏭");
		nextSongButton.setSize(60,30);
		
		playingButton=new JButton("▶");
		playingButton.setSize(60,30);
		
		pauseButton=new JButton("⏸");
		pauseButton.setSize(60,30);
		pauseButton.setLocation(470,10);
		pauseButton.setVisible(false);		 
		
		beforeSongButton=new JButton("⏮");
		beforeSongButton.setSize(60,30);
		beforeSongButton.setLocation(350,10);
		
		
		playingButton.setLocation(470,10);
		nextSongButton.setLocation(580, 10);
		
		playJProgressBar=new JProgressBar(0,260000);
		playJProgressBar.setSize(500, 10);
		playJProgressBar.setLocation(240, 50);
		fileBlower=new JButton("📁");
		fileBlower.setSize(60, 30);
		fileBlower.setLocation(900, 10);
		getOpenfile();
		playing();
		pause();
	}
	//Button 📁
	public static void getOpenfile() {
		fileBlower.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser=new JFileChooser();
				//设置默认打开文件夹
				fileChooser.setCurrentDirectory(new File("F:\\CloudMusic"));
				//
				fileChooser.setFileFilter(new FileNameExtensionFilter("支持的音频文件（*.mid、*.mp3、*.wav、*.mp3、*.au", "wav","au","mid","mp3"));
				fileChooser.showOpenDialog(fileChooser);
				selectedFile = fileChooser.getSelectedFile();
				PlayedList.playedSongFile.add(selectedFile);
				PlayedList.songNameString.add(selectedFile.getName());
				PlayedList.refresh();
				//playedList.setListData(playedListFile);
				
				//
			}
		});
		//return selectedFile;
	}
	//button ▶
	public void playing(){
		playingButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				try {
					
					if(selectedFile!=null) {
						
						in=new FileInputStream(selectedFile);
						//缓冲流
						BufferedInputStream bufferedInputStream=new BufferedInputStream(in);
						
						player=new Player(bufferedInputStream);
						
						PlayingRuner runer=new PlayingRuner(player);
						thread=new Thread(runer);
						thread.start();
						
						playingButton.setVisible(false);
						pauseButton.setVisible(true);	
						showPlayingMessage();
					}	
				
				} catch (IOException | JavaLayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				prossbar();
			}
		});
		
		
	}
	
	//button ⏸
	public void pause() {
		pauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("暂停"+player.getPosition());
				player.close();
				Menu.isPlaying=false;
				playingButton.setVisible(true);
				pauseButton.setVisible(false);
			}
		});
	}
	
	
	/**
	 * 显示正在播放的文件
	 */

	public void showPlayingMessage() {
		
		JLabel songMessageJLabel=new JLabel();
		songMessageJLabel.setText(selectedFile.getName());
		Font f = new Font("宋体",Font.BOLD,40);
		songMessageJLabel.setFont(f);
		songMessageJLabel.setForeground(Color.white);
		songMessageJLabel.setSize(700,150);
		songMessageJLabel.setLocation(0, 300);
		PlayingPanel.playingJPanel.removeAll();
		PlayingPanel.playingJPanel.add(songMessageJLabel);
		
	}
	
	
	public void prossbar() {
		int songlength=(int) selectedFile.length();
		System.out.println("选中文件长度："+songlength);
		Progress process=new Progress(songlength);
		barThread=new Thread(process);
		barThread.start();
	}
	
}


class Progress implements Runnable{
	
	int max;
	public Progress(int a) {
		// TODO Auto-generated constructor stub
		max=a;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(Menu.isPlaying) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("进度条刷新");
			System.out.println(Menu.player.getPosition()+"      "+max);
			
			//这个player.getPosition 是时间，单位位毫秒
			Menu.playJProgressBar.setValue((Menu.player.getPosition()));
		}
		
		
	}
	
	
}



class PlayingRuner implements Runnable{
	Player musicFile;
	public PlayingRuner(Player  a) {
		// TODO Auto-generated constructor stub
		musicFile=a;
		
	}
	public void run() {
		try {
			Menu.isPlaying=true;
			start();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void start() throws JavaLayerException {
		musicFile.play();
		
		
	}
	
	
}
