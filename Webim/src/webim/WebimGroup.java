/*
 * WebimGroup.java
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
package webim;

/**
 * WebimȺ�����
 * 
 * @author Ery Lee <ery.lee at gmail.com>
 * @since 1.0 
 */
public class WebimGroup {

	/**
	 * Ⱥ��id
	 */
	private String id;
	
	/**
	 * Ⱥ���ǳ�
	 */
	private String nick;
	
	/**
	 * Ⱥ����ҳ
	 */
	private String url = "";
	
	/**
	 * ���߳�Աͳ��
	 */
	private int count = 0;
	
	/**
	 * ȫ����Աͳ��
	 */
	//private int all_count = 0;
	
	/**
	 * Ⱥ��ͼƬ
	 */
	private String pic_url = "";

	/**
	 * �Ƿ���ʱ������
	 */
	private boolean temporary = false;
	
	/**
	 * ����Ⱥ��ʵ��
	 * 
	 * @param id Ⱥ��id
	 * @param nick Ⱥ���ǳ�
	 */
	public WebimGroup(String id, String nick) {
		this.id = id;
		this.nick = nick;
	}

	/**
	 * Ⱥ��id
	 * 
	 * @return Ⱥ��id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Ⱥ���ǳ�
	 * @return Ⱥ���ǳ�
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * ����Ⱥ���ǳ�
	 * @param nick Ⱥ���ǳ�
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Ⱥ����ҳ
	 * @return Ⱥ����ҳ
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * ����Ⱥ����ҳ
	 * @param url Ⱥ����ҳ
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Ⱥ���Աͳ��
	 * @return Ⱥ���Աͳ��
	 */
	public int getCount() {
		return count;
	}

	/**
	 * ����Ⱥ���Աͳ��
	 * @param count Ⱥ���Աͳ��
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Ⱥ�����߳�Աͳ��
	 * @return ���߳�Աͳ��
	 */
	//public int getAll_count() {
	//	return all_count;
	//}

	/**
	 * ����Ⱥ�����߳�Աͳ��
	 * 
	 * @param all_count ���߳�Աͳ��
	 */
	//public void setAll_count(int all_count) {
	//	this.all_count = all_count;
	//}

	/**
	 * Ⱥ��ͼƬ
	 * 
	 * @return Ⱥ��ͼƬ
	 */
	public String getPic_url() {
		return pic_url;
	}

	/**
	 * ����Ⱥ��ͼƬ
	 * 
	 * @param pic_url Ⱥ��ͼƬ
	 */
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public boolean isTemporary() {
		return temporary;
	}

	public void setTemporary(boolean temporary) {
		this.temporary = temporary;
	}

	public String toString() {
		return String.format("Group(id=%s, nick=%s, count=%d", id, nick, count);
	}

}
