<%@ page language="java" contentType="text/javascript"
    pageEncoding="UTF8"%>
    
    var _IMC = {
	            production_name: 'struts1',
	            version: '5.1',
	            path: '/Webim/',
	            is_login: true,
	            user: '',
	            setting: '{}',
	            menu: '',
	            enable_chatlink: false,
	            enable_shortcut: false,
	            enable_menu: false,
	            enable_room: true,
	            enable_noti: true,
	            theme: 'base',
	            local: 'zh-CN',
	            opacity: 80,
	            show_unavailable: true,
	            min: window.location.href.indexOf("webim_debug") != -1 ? "" : ".min"
            };
            
            _IMC.script = window.webim ? '' : ('<link href="' + _IMC.path + 'static/webim'+ _IMC.min + '.css?' + _IMC.version + '" media="all" type="text/css" rel="stylesheet"/><link href="' + _IMC.path + 'static/themes/' + _IMC.theme + '/jquery.ui.theme.css?' + _IMC.version + '" media="all" type="text/css" rel="stylesheet"/><script src="' + _IMC.path + 'static/webim' + _IMC.min + '.js?' + _IMC.version + '" type="text/javascript"></script><script src="' + _IMC.path + 'static/i18n/webim-' + _IMC.local + '.js?' + _IMC.version + '" type="text/javascript"></script>');
            
            _IMC.script += '<script src="' + _IMC.path + 'static/webim.'+ _IMC.production_name + '.js?' + _IMC.version + '" type="text/javascript"></script>';
           
            document.write( _IMC.script );
            
