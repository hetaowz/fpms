package org.beginsoft.common;


public class RequestURL {
	public static int ConnectTimeout = 30;
	public static int ReadTimeout = 60;
	//public static final String BASEURL="http://mngc402.oicp.net:36256/fpms/";
	public static final String BASEURL="http://172.17.14.7:8080/fpms/";
	//登录Controller
	public static final String LOGIN="loginController.do?loginForApp&interfaceName=datagridForApp";
	//公告Controller
	public static final String NOTICE="sfWfNoticeControllerForApp.do?datagridForApp&interfaceName=datagridForApp";
	//任务Controller
	public static final String TASK="sfPpWorktableControllerForApp.do?datagridForApp&interfaceName=datagridForApp";
	public static final String TASKUPDATE="sfPpWorktableControllerForApp.do?updateTaskForApp&interfaceName=datagridForApp";
}
