/*
 * JoinAction.java
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import webim.WebimClient;
import webim.WebimGroup;
import webim.service.WebimService;

/**
 * Ajax����/Webim/join.do����
 * 
 * @author Ery Lee <ery.lee at gmail.com>
 * @since 1.0
 */
public class JoinAction extends WebimAction {

	@Override
	protected JSONResult ajaxExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ticket = request.getParameter("ticket");
		String id = request.getParameter("id");
		String nick = request.getParameter("nick");
		if(nick == null) nick = "";
		WebimClient c = WebimService.instance().currentClient(ticket);
		WebimGroup data = WebimService.instance().getGroup(id);
		if(data == null) {
			data = new WebimGroup(id, nick);
			data.setTemporary(true);
			JSONObject o = c.join(id);
			data.setCount(o.getInt("count"));
			//TODO: FIXME Later
			data.setPic_url("/Webim/static/images/chat.png");
		}
		return new JSONResult(data);
	}

}

