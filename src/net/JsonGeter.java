package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.json.*;

public class JsonGeter {
	
	/**
	 * 从网络获取json数据，返回String 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String loadJson(String url) throws Exception {
		//读取url,返回json串
		StringBuilder json = new StringBuilder();
		URL oracle = new URL(url);
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine = null;
		while((inputLine = in.readLine()) != null){
			json.append(inputLine);
		}
		in.close();
		return json.toString();
	}
	
	/**
	 * 将得到的json 转为jsinObject
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getjson() throws Exception {
		String url = "http://service.uspacex.com/music.server/queryMusicSheets?type=all";
		String json = loadJson(url);

		JSONObject jsonObject=new JSONObject(json);;
		return jsonObject;
		
	}
	
	/**
	 * 将抓取的jsonObject  处理成Musicsheet[]
	 * @author sparrow
	 */
	public static MusicSheet[] jsonToMap() throws Exception {
		JSONObject a=getjson();
		
		//获得json  musicSheetList数组  这就是一个歌单数组
		JSONArray b=a.getJSONArray("musicSheetList");
		//使用musicsheet接收数据
		MusicSheet[] musicSheet=new MusicSheet[b.length()];
		//载入歌单
		
		//开始接收数据
		for(int i=0;i<b.length();i++) {
			//一个歌单
			JSONObject c=new JSONObject(b.getString(i));
			//初始化（不初始化就会报空指针异常）
			musicSheet[i] = new MusicSheet();
			
			//获取每个歌单中的歌曲
			JSONObject songlistArray=c.getJSONObject("musicItems");
			//读取歌单创建者<-----c中的"creator"所对应的值。   new String(string.getBytes(),"utf-8")是解决编码问题
			musicSheet[i].setCreator(new String(c.getString("creator").getBytes(),"utf-8"));
			//读取创建时间
			musicSheet[i].setDateCreated(new String(c.getString("dateCreated").getBytes(),"utf-8"));
			//......
			musicSheet[i].setCreatorId(new String(c.getString("creatorId").getBytes(),"utf-8"));
			
			musicSheet[i].setlistid(new String(c.getString("id").getBytes(),"utf-8"));
			
			musicSheet[i].setName(new String(c.getString("name").getBytes(),"utf-8"));
			
			musicSheet[i].setPicture(new String(c.getString("picture").getBytes(),"utf-8"));
			
			musicSheet[i].setUuid(new String(c.getString("uuid").getBytes(),"utf-8"));
			
			//获取 key  也就是MD5
			if(songlistArray.keys()!=null) {
				//**器。。。。不知道叫什么。      用于获取歌单的key值
				Iterator<String> it =songlistArray.keys(); 
				
				//遍历   载入歌曲
				while(it.hasNext()){
					String keyString=new String(it.next());
					//Map.put(key,value);将MD5  和歌曲名put 到Map 中
					musicSheet[i].musicItems.put(keyString, songlistArray.getString(keyString));
				
				}
			}
			
		}
		return musicSheet;
	}
}

