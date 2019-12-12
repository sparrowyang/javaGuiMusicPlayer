package net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;



/**
 * 鍔熻兘锛氶�氳繃妯℃嫙POST鏂瑰紡鎻愪氦鏁版嵁
 */
public class MusicSheetAndFilesUploader {

	/**
	 * 鍔熻兘锛氭ā鎷熻〃鍗曟暟鎹笂浼�
	 * 
	 * 璇存槑锛歝ontentType涓虹┖鍒欓粯璁ら噰鐢╝pplication/octet-stream锛�
	 * contentType闈炵┖鍒欓噰鐢╢ilename鍖归厤榛樿鐨勫浘鐗囩被鍨�
	 */
	@SuppressWarnings("rawtypes")
	private static String formUpload(String urlStr, Map<String, String> textMap, Map<String, String> fileMap,
			String contentType) {

		String res = "";
		HttpURLConnection conn = null;
		// boundary鏄痳equest澶村拰涓婁紶鏂囦欢鍐呭鐨勫垎闅旂
		String BOUNDARY = "---------------------------13708983877";

		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 鍘熸潵娌℃湁鎸囧畾瀛楃闆嗕负UTF-8
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());

			// TEXT
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}

				out.write(strBuf.toString().getBytes());

			}
			// File
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();

					/**
					 * 娌℃湁浼犲叆鏂囦欢绫诲瀷锛屽悓鏃舵牴鎹枃浠惰幏鍙栦笉鍒扮被鍨嬶紝榛樿閲囩敤application/octet-stream
					 */
					contentType = new MimetypesFileTypeMap().getContentType(file);

					/**
					 * contentType闈炵┖閲囩敤filename鍖归厤榛樿鐨勫浘鐗囩被鍨�
					 */
					if (!"".equals(contentType)) {
						if (filename.endsWith(".png")) {
							contentType = "image/png";
						} else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")
								|| filename.endsWith(".jpe")) {
							contentType = "image/jpeg";
						} else if (filename.endsWith(".gif")) {
							contentType = "image/gif";
						} else if (filename.endsWith(".ico")) {
							contentType = "image/image/x-icon";
						}
					}
					if (contentType == null || "".equals(contentType)) {
						contentType = "application/octet-stream";
					}
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// 璇诲彇杩斿洖鏁版嵁
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("Send POST Request Error: " + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

	/**
	 * 
	 */
	public static void createMusicSheetAndUploadFiles(String url, MusicSheet musicSheet, List<String> musicFilePaths) {

		Map<String, String> textMap = new HashMap<String, String>();
		textMap.put("musicSheetUuid", musicSheet.getUuid());
		textMap.put("musicSheetName", musicSheet.getName());
		textMap.put("musicSheetCreatorId", musicSheet.getCreatorId());
		textMap.put("musicSheetCreator", musicSheet.getCreator());
		textMap.put("musicSheetDateCreated", musicSheet.getDateCreated());
		textMap.put("musicSheetPicture", musicSheet.getPicture());

		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("musicSheetPicture", musicSheet.getPicture());

		Map<String, String> musicFileMap = new HashMap<String, String>();

		FileInputStream fis = null;
		String fileMd5;

		for (String filePath : musicFilePaths) {
			try {
				fis = new FileInputStream(filePath);
				fileMd5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
				fileMap.put(fileMd5, filePath);
				musicFileMap.put(fileMd5, filePath);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(fis);
			}
		}

		musicSheet.setMusicItems(musicFileMap);
		String contentType = null;
		String ret = formUpload(url, textMap, fileMap, contentType);

		System.out.println(ret);
	}
}