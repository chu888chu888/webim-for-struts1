/*
 * WebimService.java
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package webim.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import webim.WebimClient;
import webim.WebimConfig;
import webim.WebimEndpoint;
import webim.WebimGroup;
import webim.WebimHistory;
import webim.WebimMenu;
import webim.WebimMessage;
import webim.WebimNotification;
import webim.dao.WebimDao;
import webim.dao.WebimHistoryDao;
import webim.dao.WebimSettingDao;

/**
 * WebimService��װWebim Actions��Webim Dao֮���������á� 
 * 
 * @author Ery Lee <ery.lee at gamil.com>
 * @since 1.0
 */
public class WebimService {

	static WebimService instance = null;

	private WebimDao webimDao;
	private WebimSettingDao settingDao;
	private WebimHistoryDao historyDao;

	public WebimService() {
		webimDao = new WebimDao();
		settingDao = new WebimSettingDao();
		historyDao = new WebimHistoryDao();
	}

	/**
	 * ����
	 * 
	 * @return WebimService����
	 */
	public static WebimService instance() {
		if (instance == null) {
			instance = new WebimService();
		}
		return instance;
	}

	/**
	 * ���ص�ǰWebim�û���Uid��һ��Ӧ��HTTP Session�ж�ȡ�� 
	 * 
	 * @return ��ǰWebim�û���UID
	 */
	public long currentUid() {
		//TODO: Ӧ���滻�ô��룬��ȡ��ǰ��½�û���ID��
		return 1;
	}


	/**
	 * ���ص�ǰ��Webim�˵�(�û�)
	 * 
	 * @return ��ǰWebim�˵�(�û�)
	 */
	public WebimEndpoint currentEndpoint() {
		//TODO: Ӧ�滻�ô��룬���ؼ���ϵͳ�ĵ�ǰ�û���
		WebimEndpoint ep = new WebimEndpoint("1", "user1");
		ep.setPic_url("/Webim/static/images/chat.png"); // �û�ͷ��
		ep.setShow("available");
		ep.setUrl(""); // �û��ռ�
		ep.setStatus(""); // �û�״̬
		return ep;
	}

	/**
	 * ���ص�ǰ��Webim�ͻ��ˡ�
	 * 
	 * @param ticket ͨ������
	 * @return ��ǰ�û���Webim�ͻ���
	 */
	public WebimClient currentClient(String ticket) {
		WebimClient c = new WebimClient(currentEndpoint(), WebimConfig.DOMAIN,
				WebimConfig.APIKEY, WebimConfig.HOST, WebimConfig.PORT);
		c.setTicket(ticket);
		return c;
	}

	/**
	 * ���ݵ�ǰ�û�id�����ظ��û����ѣ���WebimDao�ж�ȡ��
	 * 
	 * @param uid ��ǰ�û�id
	 * @return ���û����ѡ�
	 */
	public List<WebimEndpoint> getBuddies(long uid) {
		return webimDao.getBuddiesByUid(uid, 1000);
	}
	
	/**
	 * ���ݺ���id�б��ȡ�����б�
	 * 
	 * @param ids ����id�б�
	 * @return �����б�
	 */
	public List<WebimEndpoint> getBuddiesByIds(long[] ids) {
		return webimDao.getBuddiesByIds(ids);
	}

	/**
	 * ���ݵ�ǰ�û�id�������û�����Ⱥ�飬��WebimDao�ж�ȡ
	 * 
	 * @param uid ��ǰ�û�id
	 * @return �û�����Ⱥ��
	 */
	public List<WebimGroup> getGroups(long uid) {
		return webimDao.getGroups(uid, 100);
	}

	/**
	 * ���ݵ�ǰ�û�uid����ȡ��ʱ�����顣
	 * 
	 * @param uid �û�uid
	 * @return ��ʱ������
	 */
	public List<WebimGroup> getTmpGroups(long uid) {
		List<WebimGroup> groups = new ArrayList<WebimGroup>();
		String json  = settingDao.get(uid);
		JSONObject obj = new JSONObject(json);
		if(obj.has("temporary_rooms")) {
			JSONArray array = obj.getJSONArray("temporary_rooms");
			for(int i = 0; i < array.length(); i++) {
				JSONObject o = array.getJSONObject(i);
				WebimGroup g = new WebimGroup(o.getString("id"), o.getString("nick"));
				g.setTemporary(true);
				//FIXME Later
				g.setPic_url("/Webim/static/images/chat.png");
				groups.add(g);
			}
		}
		return groups;
	}

	/**
	 * ����Ⱥ��id������һ��Ⱥ�����ϸ��Ϣ��
	 * 
	 * @param id Ⱥ��id
	 * @return Ⱥ����ϸ
	 */
	public WebimGroup getGroup(String id) {
		return webimDao.getGroup(id);
	}

	/**
	 * �����û�uid����ȡ���û�������Ϣ
	 * 
	 * @param uid �û�uid
	 * @return ������Ϣ
	 */
	public List<WebimHistory> getOfflineMessages(long uid) {
		return historyDao.getOfflineMessages(uid);
	}

	/**
	 * ����û���������Ϣ 
	 * 
	 * @param uid �û�uid
	 */
	public void offlineMessageToHistory(long uid) {
		historyDao.offlineMessageToHistory(uid);
	}

	/**
	 * д�������¼
	 * 
	 * @param uid �û�uid
	 * @param offline �Ƿ�����
	 * @param msg ��ʱ��Ϣ
	 */
	public void insertHistory(long uid, String offline, WebimMessage msg) {
		WebimHistory h = new WebimHistory();
		h.setFrom(String.valueOf(uid));
		h.setSend(offline == "true" ? 0 : 1);
		h.setNick(msg.getNick());
		h.setType(msg.getType());
		h.setTo(msg.getTo());
		h.setBody(msg.getBody());
		h.setStyle(msg.getStyle());
		h.setTimestamp(msg.getTimestamp());
		historyDao.insert(h);
	}


	/**
	 * ��ȡ�û�����
	 * 
	 * @param uid �û�uid
	 * @return �û�����
	 */
	public String getSetting(long uid) {
		return settingDao.get(uid);
	}

	/**
	 * �����û�����
	 * 
	 * @param uid �û�uid
	 * @param data ��������,json��ʽ
	 */
	public void updateSetting(long uid, String data) {
		settingDao.set(uid, data);
	}

	/**
	 * ��ȡ��ǰ�û�����������¼
	 * 
	 * @param uid �û�uid
	 * @param with ����id(������Ҫ��ת��Ϊlong) 
	 * @param type �����¼����(chat|grpchat)
	 * @return �����¼
	 */
	public List<WebimHistory> getHistories(long uid, String with, String type) {
		return historyDao.getHistories(uid, with, type);
	}

	/**
	 * �������������¼
	 * 
	 * @param uid �û�uid
	 * @param with ����id
	 */
	public void clearHistories(long uid, String with) {
		historyDao.clearHistories(uid, with);
	}

	/**
	 * ��ȡ��ǰ�û�֪ͨ
	 * 
	 * @param uid �û�uid
	 * @return ֪ͨ�б�
	 */
	public List<WebimNotification> getNotifications(long uid) {
		return webimDao.getNotifications(uid);
	}

	/**
	 * ��ȡ��ǰ�û��˵�
	 * 
	 * @param uid �û�uid
	 * @return �û��˵�
	 */
	public List<WebimMenu> getMenuList(long uid) {
		return webimDao.getMenuList(uid);
	}

}