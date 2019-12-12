package net;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;




/**
 * 文件下载类
 * 下载大哥提供的API  的文件，虽然都是 file not exsit! -_- 
 * @author sparrow
 *
 */
public class FileDonwload {
	static String dowmLoadUrl="http://service.uspacex.com/music.server/downloadMusic?md5=";
	
	static String MD5=new String();
	
	public FileDonwload() {
		// TODO Auto-generated constructor stu+b
	}
	
	public static File DownLoading(String md5vlaue,String name,String path) throws MalformedURLException {
		MD5=md5vlaue;
		int bytesum = 0;
		        int byteread = 0;
		        URL url = new URL(dowmLoadUrl+MD5);
		        try {
			 
		            URLConnection conn = (HttpURLConnection) url.openConnection();
		            InputStream inStream = (InputStream) conn.getInputStream();
		            //File aFile =(File) conn.getContent();
		            FileOutputStream fs = new FileOutputStream(path+name);
		            byte[] buffer = new byte[1204];
		            while ((byteread = inStream.read(buffer)) != -1) {
		                bytesum += byteread;
		                System.out.println(bytesum);
		                fs.write(buffer, 0, byteread);
		            }
		            fs.close();
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        
		return null;
		
	}
	
}