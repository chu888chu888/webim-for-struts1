CHANGELOG
==============================

v5.2 (2013/12/31)
-------------------

* 版本号与与消息服务器、java库(webim-java)同步
* Webim通用代码正式打包为WEB-INF/lib/webim-5.2.jar
* JSON库代码正式删除，引入WEB-INF/lib/org.json-20120521.jar
* webim.actions包重命名为webim.struts2.actions
* struts.xml重新配置action
* Index.jsp嵌入javascript地址更新为"/Webim/boot.do"
* WebimClient.online接口、OnlineAction更新
* WebimClient.join接口、 JoinAction更新
* WebimClient.members接口、 MemberAction更新
* WebimService新增newTmpGroup接口
* WebimConfig放入webim.config包
* WebimConfig新增ENABLE_UPLOAD配置
* 新增WebClient.push接口
* 新增WebimClient.presences接口
* RunAction重命名为BootAction
* RunJS.jsp更新为Boot.jsp
* 更新static/静态资源文件
* static/webim.strtus2.js更新静态资源位置URL


copy from struts2 (2013/12/18)
-----------------------------


