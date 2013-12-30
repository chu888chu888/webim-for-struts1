/*
 * WebimAction.java
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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Webim Action父类, 处理AJAX/JSON请求。
 * 
 * @author Ery Lee <ery.lee at gmail.com>
 * @since 1.0
 */
public abstract class WebimAction extends Action {

	public static final JSONResult SUCCESS = new JSONResult("ok");
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONResult result = this.ajaxExecute(mapping, form, request, response);
		if(result != null) {
			response.addHeader("Content-Type", "application/json");
			response.getOutputStream().write(result.toJSON());
			response.getOutputStream().flush();
		}
		return null;
	}
	
	protected abstract JSONResult ajaxExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
}