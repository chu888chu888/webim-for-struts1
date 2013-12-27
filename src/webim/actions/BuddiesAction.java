/*
 * BuddiesAction.java
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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import webim.WebimEndpoint;
import webim.service.WebimService;

/**
 * Ajax«Î«Û/Webim/buddies.do¥¶¿Ì°£
 * 
 * @author Ery Lee <ery.lee at gmail.com>
 * @since 1.0
 */
public class BuddiesAction extends WebimAction {

	@Override
	protected JSONResult ajaxExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		String ids1[] = ids.split(",");
		long id2[] = new long[ids1.length];
		for (int i = 0; i < ids1.length; i++) {
			id2[i] = Long.parseLong(ids1[i]);
		}
		List<WebimEndpoint> buddies = WebimService.instance().getBuddiesByIds(
				id2);
		return new JSONResult(buddies);
	}

}
