/*
 * WebimHistoryDao.java
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

import java.util.ArrayList;
import java.util.List;

import webim.WebimHistory;

/**
 * webim_histories���ݿ���洢�����¼��<br>
 *
 * <pre>
 * CREATE TABLE webim_histories (
 *	    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *	    `send` tinyint(1) DEFAULT NULL,
 *	    `type` varchar(20) DEFAULT NULL,
 *	    `to` varchar(50) NOT NULL,
 *	    `from` varchar(50) NOT NULL,
 *	    `nick` varchar(20) DEFAULT NULL COMMENT 'from nick',
 *	    `body` text,
 *	    `style` varchar(150) DEFAULT NULL,
 *	    `timestamp` double DEFAULT NULL,
 *	    `todel` tinyint(1) NOT NULL DEFAULT '0',
 *	    `fromdel` tinyint(1) NOT NULL DEFAULT '0',
 *	    `created_at` date DEFAULT NULL,
 *	    `updated_at` date DEFAULT NULL,
 *	    PRIMARY KEY (`id`),
 *	    KEY `todel` (`todel`),
 *	    KEY `fromdel` (`fromdel`),
 *	    KEY `timestamp` (`timestamp`),
 *	    KEY `to` (`to`),
 *	    KEY `from` (`from`),
 *	    KEY `send` (`send`)
 *	) ENGINE=MyISAM;
 * </pre>
 * 
 * @author Ery Lee <ery.lee at gmail.com>
 * @since 1.0
 */
public class WebimHistoryDao {

	/**
	 * ����һ�������¼���ο������WebimHistory�ֶΡ�
	 * 
	 * @param history �����¼
	 */
	public void insert(WebimHistory history) {
		// TODO Auto-generated method stub
	}

	/**
	 * ��ȡ��with�û������¼����ѯ�߼�:<br>
	 * <pre>
	 *     if (type == "chat")
     *       {
     *           
     *           "SELECT * FROM webim_Histories WHERE `type` = 'chat' 
     *           AND ((`to`=%s AND `from`=%s AND `fromdel` != 1) 
     *           OR (`send` = 1 AND `from`=%s AND `to`=%s AND `todel` != 1))  
     *           ORDER BY timestamp DESC LIMIT %d", $with, $uid, $with, $uid, $limit );
     *           
     *       }
     *       else
     *       {
     *           
     *           "SELECT * FROM  webim_histories 
     *               WHERE `to`=%s AND `type`='grpchat' AND send = 1 
     *               ORDER BY timestamp DESC LIMIT %d", , $with, $limit);
     *           
     *       }
	 * </pre>
	 * @param uid ��ǰ�û�id
	 * @param with �Է�id���ɸ�����Ҫת��Ϊlong
	 * @param type ��¼���ͣ�chat | grpchat
	 * @return �����¼
	 */
	public List<WebimHistory> getHistories(long uid, String with, String type) {
		// TODO Auto-generated method stub
		return new ArrayList<WebimHistory>();
	}


	/**
	 * �����with�û������¼��SQL�ű�:<br>
	 * 
	 *  "UPDATE webim_histories SET fromdel = 1 Where from = @0 and to = @1 and type = 'chat'"<br>
     *  "UPDATE webim_histories SET todel = 1 Where to = @0 and from = @1 and type = 'chat'"<br>
     *  "DELETE FROM webim_histories WHERE fromdel = 1 AND todel = 1"
     *  
	 * @param uid �û�uid
	 * @param with �Է�id,�ɸ�����Ҫת��Ϊlong
	 */
	public void clearHistories(long uid, String with) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ��ȡ�û���������Ϣ��SQL�ű�:<br>
	 * 
	 * "SELECT * FROM  webim_histories WHERE `to` = ? and send != 1 ORDER BY timestamp DESC LIMIT %d", limit;
	 * 
	 * @param uid �û�uid
	 * @return ����������Ϣ
	 */
	public List<WebimHistory> getOfflineMessages(long uid) {
		// TODO Auto-generated method stub
		return new ArrayList<WebimHistory>();
	}


	/**
	 * ������Ϣת��Ϊ��ʷ��Ϣ��SQL�ű�:<br>
	 * 
	 * "UPDATE webim_histories SET send = 1 where to = ? and send = 0");
	 * 
	 * @param uid �û�uid
	 */
	public void offlineMessageToHistory(long uid) {
		// TODO Auto-generated method stub
		
	}

}
