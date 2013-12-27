/*
 * OnlineAction.java
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
package webim.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import webim.WebimClient;
import webim.WebimConfig;
import webim.WebimEndpoint;
import webim.WebimGroup;
import webim.service.WebimService;

/**
 * Ajax«Î«Û/Webim/online.do¥¶¿Ì°£
 * 
 * @author Ery Lee <ery.lee at gmail.com>
 * @since 1.0
 */
public class OnlineAction extends Action {


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		long uid = WebimService.instance().currentUid();
		List<WebimEndpoint> buddies = WebimService.instance().getBuddies(uid);
		List<WebimGroup> groups = WebimService.instance().getGroups(uid);
		groups.addAll(WebimService.instance().getTmpGroups(uid));
		// Forward Online to IM Server
		WebimClient client = WebimService.instance().currentClient("");
		List<String> buddyIds = buddyIds(buddies);
		List<String> groupIds = groupIds(groups);
		try {
			JSONObject json = client.online(buddyIds, groupIds);
			System.out.println(json.toString());
			Map<String, String> conn = new HashMap<String, String>();
			conn.put("ticket", json.getString("ticket"));
			conn.put("domain", client.getDomain());
			conn.put("jsonpd", json.getString("jsonpd"));
			conn.put("server", json.getString("jsonpd"));
			conn.put("websocket", json.getString("websocket"));

			// Online Buddies
			Map<String, WebimEndpoint> buddyMap = new HashMap<String, WebimEndpoint>();
			for (WebimEndpoint e : buddies) {
				buddyMap.put(e.getId(), e);
			}

			JSONArray a = json.getJSONArray("buddies");
			for (int i = 0; i < a.length(); i++) {
				JSONObject o = a.getJSONObject(i);
				String n = o.getString("name");
				buddyMap.get(n).setShow("available");
			}

			Collection<WebimEndpoint> rtBuddies;
			if (WebimConfig.SHOW_UNAVAILABLE) {
				rtBuddies = buddyMap.values();
			} else {
				rtBuddies = new ArrayList<WebimEndpoint>();
				for (WebimEndpoint e : buddyMap.values()) {
					if (e.getShow() == "available")
						rtBuddies.add(e);
				}
			}

			// Groups with count
			Map<String, WebimGroup> groupMap = new HashMap<String, WebimGroup>();
			for (WebimGroup g : groups) {
				groupMap.put(g.getId(), g);
			}
			List<WebimGroup> groups1 = new ArrayList<WebimGroup>();
			a = json.getJSONArray("groups");
			for (int i = 0; i < a.length(); i++) {
				JSONObject o = (JSONObject) a.getJSONObject(i);
				String gid = o.getString("name");
				WebimGroup group = groupMap.get(gid);
				group.setCount(o.getInt("total"));
				groups1.add(group);
			}

			// {"success":true,
			// "connection":{
			// "ticket":"fcc493f7a7b17cfadbf4|admin",
			// "domain":"webim20.cn",
			// "server":"http:\/\/webim20.cn:8000\/packets"},
			// "buddies":[
			// {"uid":"5","id":"demo","nick":"demo","group":"stranger","url":"home.php?mod=space&uid=5","pic_url":"picurl","status":"","presence":"online","show":"available"}],
			// "rooms":[],
			// "server_time":1370751451399.4,
			// "user":{"uid":"1","id":"admin","nick":"admin","pic_url":"pickurl","show":"available","url":"home.php?mod=space&uid=1","status":""},
			// "new_messages":[]}

			data.put("success", true);
			data.put("connection", conn);
			data.put("buddies", rtBuddies.toArray());
			data.put("groups", groups1.toArray());
			data.put("rooms", groups1.toArray());
			data.put("server_time", System.currentTimeMillis() / 1000.0);
			data.put("user", client.getEndpoint());
		} catch (Exception e) {
			data.put("success", false);
			data.put("error_msg", "IM Server is not found");
		}
		
		response.addHeader("Content-Type", "application/json");
		response.getOutputStream().write(new JSONResult(data).toJSON());
		response.getOutputStream().flush();
		
		return null;
	}

	private List<String> buddyIds(List<WebimEndpoint> buddies) {
		List<String> ids = new ArrayList<String>();
		for (WebimEndpoint b : buddies) {
			ids.add(b.getId());
		}
		return ids;
	}

	private List<String> groupIds(List<WebimGroup> groups) {
		List<String> ids = new ArrayList<String>();
		for (WebimGroup g : groups) {
			ids.add(g.getId());
		}
		return ids;
	}

}
