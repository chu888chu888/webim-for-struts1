package webim.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import webim.WebimConfig;

public class BootAction extends Action {

	public String theme = WebimConfig.THEME;
	public String local = WebimConfig.LOCAL;
	public String emot = WebimConfig.EMOT;
	public int opacity = WebimConfig.OPACITY;
	public boolean enable_room = WebimConfig.ENABLE_ROOM;
	public boolean enable_noti = WebimConfig.ENABALE_NOTI;
	public boolean enable_chatlink = WebimConfig.ENABLE_CHATLINK;
	public boolean enable_shortcut = WebimConfig.ENABLE_SHORTCUT;
	public boolean enable_menu = WebimConfig.ENABLE_MENU;
	public boolean show_unavailable = WebimConfig.SHOW_UNAVAILABLE;
	public boolean visitor = WebimConfig.VISITOR;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("success");
	}

}
