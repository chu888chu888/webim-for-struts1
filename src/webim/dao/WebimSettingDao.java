/*
 * WebimSettingDao.java
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
package webim.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * webim_settings���ݿ��<br>
 * 
 * <pre>
 * CREATE TABLE webim_settings(
 *	    `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
 *	    `uid` mediumint(8) unsigned NOT NULL,
 *	    `web` blob,
 *	    `air` blob,
 *	    `created_at` DATETIME DEFAULT NULL,
 *	    `updated_at` DATETIME DEFAULT NULL,
 *	    PRIMARY KEY (`id`)
 *	)ENGINE=MyISAM;
 *	</pre>
 * 
 * @author Ery Lee <ery.lee at gmail.com>
 * @since 1.0
 */
public class WebimSettingDao {

	public static Map<String, String> settings = new HashMap<String, String>();
	
	/**
	 * �����û��������ݡ�<br>
	 * 
	 * <ol>
	 * <li> ���ݿ�SQL�ű�: "update webim_settings set data =@0  where uid = @1", uid, data</li>
	 * <li> Ӧ���ȶ�ȡ���ü���Ƿ���ڣ��粻���ڲ��룬���ڸ��¡�</li>
	 * </ol>
	 * @param uid �û�uid
	 * @param data �������ݣ�JSON��ʽ
	 */
	public void set(long uid, String data) {
		// TODO Auto-generated method stub
		WebimSettingDao.settings.put(String.valueOf(uid), data);
	}

	/**
	 * ��ȡ�û��������ݡ�<br>
	 * 
	 * <ol>
	 * <li>���ݿ��ѯSQL�ű���"select data from webim_settings where uid = ?", uid </li>
	 * <li> ���dataΪ�գ�����: "{}"</li>
	 * </ol>
	 * 
	 * @param uid �û�uid
	 * @return �������ݣ�JSON��ʽ
	 */
	public String get(long uid) {
		/**
		 * TODO: 
		 */
		String json = WebimSettingDao.settings.get(String.valueOf(uid));
		if(json == null) {
			json = "{}";
		}
		return json;
	}

}

