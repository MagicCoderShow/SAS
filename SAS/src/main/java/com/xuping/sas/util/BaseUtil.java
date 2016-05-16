package com.xuping.sas.util;

public class BaseUtil{
	public String message = null;
	public Integer statusCode = 200;  //{"statusCode":"300", "message":"操作失败" statusCode":"200", "message":"操作成功",  statusCode":"301, "message":"会话超时"}
	public String dialogid = null;// 只有callbackType="forward"时需要forward值
	public String divid=null; //服务器转回navTabId可以把那个navTab标记为tabidoadFlag=1,  下次切换到那个navTab时会重新载入内容
	public String tabid=null;
	public String forward=null; //callbackType如果是closeCurrent就会关闭当前tab
	public String forwardConfirm = "";
	public boolean closeCurrent;
	/**
	 * 
	 * @param 消息
	 * @param 状态
	 * @param dialogID
	 * @param divid
	 * @param tabid
	 * @param forward
	 * @param forwardConfirm
	 * @param closeCurrent
	 */
	public BaseUtil(String message, Integer statusCode, String dialogid,
			String divid, String tabid, String forward, String forwardConfirm,
			boolean closeCurrent) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.dialogid = dialogid;
		this.divid = divid;
		this.tabid = tabid;
		this.forward = forward;
		this.forwardConfirm = forwardConfirm;
		this.closeCurrent = closeCurrent;
	}
	public BaseUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

}
