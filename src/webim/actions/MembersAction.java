/*
 * MembersAction.java
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import webim.WebimClient;
import webim.service.WebimService;

/**
 * Ajax«Î«Û/Webim/members.do¥¶¿Ì°£
 * 
 * @author Ery Lee <ery.lee at gmail.com>
 * @since 1.0
 */
public class MembersAction extends WebimAction {

	private List<Map<String, String>> members;
	

	@Override
	protected JSONResult ajaxExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String gid = request.getParameter("id");
		String ticket = request.getParameter("ticket");
    	WebimClient c = WebimService.instance().currentClient(ticket);
        JSONObject obj = c.members(gid);
        members = new ArrayList<Map<String,String>>();
        JSONArray array = obj.getJSONArray(gid);
        for(int i = 0; i < array.length(); i++)
        {
            Map<String,String> m = new HashMap<String,String>();
            obj = array.getJSONObject(i);
            m.put("id", obj.getString("id"));
            m.put("nick", obj.getString("nick"));
            members.add(m);
        }
    	return new JSONResult(members);
    }

}