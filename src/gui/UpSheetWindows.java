package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.MusicSheet;
import net.MusicSheetAndFilesUploader;


/**
 * 上传窗口
 * @author sparrow
 *
 */
public class UpSheetWindows extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JPanel uPanel;
	
	JLabel idJLabel;
	JLabel nameJLabel;
	JLabel creatorJLabel;
	
	JTextField inputcreateridField;
	JTextField inputnameField;
	JTextField inputcreatorField;
	
	JButton uploadButton;
	

	public UpSheetWindows() {
		super("上传歌单");
		this.setSize(400,260);
		this.setResizable(false);
		this.setLocation(500, 500);
		
		
		
		
		uPanel=new JPanel();
		uPanel.setBackground(Color.black);
		uPanel.setSize(400,260);
		uPanel.setLayout(null);
		
		inputcreateridField=new JTextField();
		inputcreateridField.setLocation(10, 30);
		inputcreateridField.setSize(380, 30);
		
		inputnameField=new JTextField();
		inputnameField.setLocation(10, inputcreateridField.getY()+60);
		inputnameField.setSize(380, 30);
		
		inputcreatorField=new JTextField();
		inputcreatorField.setLocation(10, inputnameField.getY()+60);
		inputcreatorField.setSize(380, 30);
		
		idJLabel=new JLabel();
		idJLabel.setSize(200, 20);
		idJLabel.setBackground(Color.black);
		idJLabel.setForeground(Color.white);
		idJLabel.setLocation(10, 0);
		idJLabel.setText("输入创建者id");
		nameJLabel=new JLabel();
		nameJLabel.setSize(200, 20);
		nameJLabel.setBackground(Color.black);
		nameJLabel.setForeground(Color.white);
		nameJLabel.setText("输入歌单名");
		nameJLabel.setLocation(10, inputcreateridField.getY()+30);
		creatorJLabel=new JLabel();
		creatorJLabel.setSize(200, 20);
		creatorJLabel.setBackground(Color.black);
		creatorJLabel.setForeground(Color.white);
		creatorJLabel.setLocation(10,inputnameField.getY()+30);
		creatorJLabel.setText("输入创建者");
		
		
		uploadButton=new JButton("🔃");
		uploadButton.setSize(50, 30);
		uploadButton.setLocation(this.getWidth()/2-25, inputcreatorField.getY()+40);
		
		
		uPanel.add(idJLabel);
		uPanel.add(nameJLabel);
		uPanel.add(inputcreateridField);
		uPanel.add(inputnameField);
		uPanel.add(inputcreatorField);
		uPanel.add(creatorJLabel);
		uPanel.add(uploadButton);
		
		this.add(uPanel);
		this.setVisible(true);
		
		/**
		 * 上传按钮事件
		 */
		uploadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String url = "http://service.uspacex.com/music.server/upload";

				List<String> filePaths = new ArrayList<String>();

				//filePaths.add("image/main.png");
				
				//添加上传文件
				for (File p:PlayedList.playedSongFile) {
					filePaths.add(p.getPath());
				}
				
				//处理上传信息
				MusicSheet ms;
				try {
					ms = new MusicSheet();
					ms.setCreatorId(inputcreateridField.getText());
					ms.setPicture("image/main.png");
					ms.setCreator(inputcreatorField.getText());
					ms.setName(inputnameField.getText());
					System.out.println(inputcreateridField.getText()
							+inputcreatorField.getText()
							+inputnameField.getText());
					MusicSheetAndFilesUploader.createMusicSheetAndUploadFiles(url, ms, filePaths);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
	}
	
	
}
