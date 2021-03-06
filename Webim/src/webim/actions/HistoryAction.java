/*
 * HistoryAction.java
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

import webim.WebimHistory;
import webim.service.WebimService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Ajax����/Webim/history.do������
 * 
 * @author Ery Lee <ery.lee at gmail.com>
 * @since 1.0
 */
public class HistoryAction extends WebimAction {

	@Override
	protected JSONResult ajaxExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long uid = WebimService.instance().currentUid();
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		List<WebimHistory> histories = WebimService.instance().getHistories(
				uid, id, type);
		return new JSONResult(histories);
	}

}
