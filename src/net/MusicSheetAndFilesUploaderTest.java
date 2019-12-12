package net;

import java.util.ArrayList;
import java.util.List;


/**
 * 歌曲上传类
 *
 */
public class MusicSheetAndFilesUploaderTest {
	public static void main(String[] args) throws Exception {
		
		String url = "http://service.uspacex.com/music.server/upload";

		List<String> filePaths = new ArrayList<String>();

		//filePaths.add("javatext/");
		filePaths.add("javatext/李荣浩 - 爸爸妈妈.mp3");
		
		MusicSheet ms = new MusicSheet();
		// ms.setUuid("cddc055bfa33439a889cb611c1cb6db2");
		ms.setCreatorId("18170023039");

		ms.setPicture("javatext/lironghao.png");
		
		ms.setCreator("xukaixn");
		ms.setName("xkxn13");

		MusicSheetAndFilesUploader.createMusicSheetAndUploadFiles(url, ms, filePaths);
	}

}
