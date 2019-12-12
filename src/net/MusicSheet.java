package net;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


//import org.json.JSONException;

/**
 * 歌单类
 * 照搬老师的代码
 * @author sparrow
 *
 */
public class MusicSheet {
	public  String listId;
	public  String listUuid;
	public  String listName;
	public  String creatorId;
	public  String creator;
	public  String createDate;
	public  String songPictrue;
	
	//这里  第一个字符串是MD5   第二个是歌曲名
	public Map<String, String> musicItems;

	public MusicSheet() throws Exception {
		listId=" ";
		listUuid=" ";
		listName =" ";
		creatorId=" ";
		creator=" ";
		createDate=" ";
		songPictrue=" ";
		musicItems=new HashMap<String, String>() ;
		listUuid = UUID.randomUUID().toString().replace("-", "");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		createDate = formatter.format(new Date());
		
		
	}
	

	public MusicSheet(String _listid,String _uuid,String _name,String _creatorid,String _creator,String _ceatedate,String _songpic) {
		listId=_listid;
		listUuid=_uuid;
		listName = _name;
		creatorId=_creatorid;
		creator=_creator;
		createDate=_ceatedate;
		songPictrue=_songpic;
		
	}
	public void setlistid(String a) {
		this.listId=a;
	}
	public String getlistid() {
		return listId;
		
	}

	public void setUuid(String uuid) {
		listUuid = uuid;
	}

	public String getUuid() {
		return listUuid;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public void setDateCreated(String dateCreated) {
		createDate = dateCreated;
	}

	public String getDateCreated() {
		return createDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String _creator) {
		this.creator = _creator;
	}

	public String getName() {
		return listName;
	}

	public void setName(String name) {
		this.listName = name;
	}

	public String getPicture() {
		return songPictrue;
	}

	public void setPicture(String picture) {
		this.songPictrue = picture;
	}

	public Map<String, String> getMusicItems() {
		return musicItems;
	}

	public void setMusicItems(Map<String, String> musicItems) {
		this.musicItems = musicItems;
	}
}
