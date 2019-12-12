# Spar Music Player
**界面设计**：杨宗富17020031091、徐凯新18170023039
**播放功能**：杨宗富
**监听器**：徐凯新
**数据库相关**：徐凯新
**下载上传相关**：杨宗富
## 特色
1. **纯手写UI，拒绝拖拖拽拽**
2. 黑金质感
2. 沉浸式界面
3. 实时播放进度条
4. 支持本地播放
5. 支持网络下载播放，双击歌曲列表下载
6. 支持歌单查看，可以看别人在听啥歌。
7. 支持上传歌单
8. 支持数据库管理网络歌单
## 运行截图
![](_v_images/screenshot2.png)
## 开发日志
### 文件说明
#### UI相关
界面左侧歌单中的一个歌单类：
[AMusicSheetLable.java](src/gui/AMusicSheetLable.java)

UI顶部类：
[Headbar.java](src/gui/Headbar.java)

主界面UI：
[MainView.java](src/gui/MainView.java)

UI底部按钮界面：
[Menu.java](src/gui/Menu.java)

左侧歌单：
[MusicList.java](src/gui/MusicList.java)

中部播放界面：
[PlayingPanel.java](src/gui/PlayingPanel.java)

歌曲列表：
[SongList.java](src/gui/SongList.java)

上传音乐窗口：
[UpSheetWindows.java](src/gui/UpSheetWindows.java)

播放列表：
[PlayedList.java](src/gui/PlayedList.java)

程序启动主函数：
[guiStart.java](src/gui/PlayerStart.java)

#### 网络相关
网络文件下载：
[FileDonwload.java](src/net/FileDonwload.java)

JSON数据解析器：
[JsonGeter.java](src/net/JsonGeter.java) 

音乐歌单模板：
[MusicSheet.java](src/net/MusicSheet.java) <--参考大哥的

音乐上传类：
[MusicSheetAndFilesUploader.java](src/net/MusicSheetAndFilesUploader.java) <--参考大哥的

#### 数据库相关
数据库连接类：
[SheetAdmin.java](src/sqlconnector/SheetAdmin.java)

歌单数据库操作类：
[SqlConnector.java](src/sqlconnector/SqlConnector.java)

## 额外的包

jl1.0.jar

json.jar

## 问题及解决方案
问题太多，没有一一记下
### 播放时占用线程怎么办？

```
//新线程
new Thread(()->{
    //调用播放方法进行播放
     Try {
        player.play();
     } catch (JavaLayerException e1) {
            e1.printStackTrace();
        }
}).start();
```
### 如何暂停播放线程？
使用`waite()`或者`suspend()`，后者优先考虑。

### 如何处理空指针异常？
New 一个数组时，记得初始化，否则会空指针异常

### 无法控件setSize怎么办？
将布局去掉：`setlayout(null);`

### json数据如何解析？

大哥所提供的API的链接中返回的JSON数据格式如下：
```json
{
  "musicSheetList": [
    {
      "creator": "张三",//
      "creatorId": "18776",
      "dateCreated": "20190101",
      "id": 47,
      "musicItems": {
        "d39a9f65618af34bf01a6ffab032ee44": "旧日的足迹.mp3",
        "1fb2f2dc55898b4c722eb967233124b1": "�������.mp3",
        "1e659b0eefb3e1bb796e93cfe0710a9c": "长城.mp3",
        "df142e440c2162c5d5c51077e387dc80": "谁伴我闯荡.mp3",
        "15713ebfd8ffc7a7659f3b232d458cde": "纯音乐 - River Flows In You (钢琴).mp3",
        "cdc8b7a47b61ce5c0eef1b050f6ce41c": "AMANI.mp3",
        "0bcb24befd73757f6a769b43656789d6": "无声的告别.mp3",
        "9c87194b62c39b35de640764d7b53d0f": "命运是你家.mp3"
      },
      "name": "歌单1",
      "picture": "235edc3a68144beb8e8980e59941c470.jpg",
      "uuid": "235edc3a68144beb8e8980e59941c470"
    },
    ......#紧接着几百个如上格式的json对象
}
```

