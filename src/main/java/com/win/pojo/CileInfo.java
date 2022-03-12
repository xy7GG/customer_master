package com.win.pojo;

import javax.servlet.http.HttpServletRequest;

public class CileInfo {
	private String id;
    private String cileName;
    private String stfId;
    private String value;
    private Integer sex;
	private String staffName;
    public CileInfo(String id,String cileName,String stfId,String value) {
    	this.id=id;
    	this.cileName=cileName;
    	this.stfId=stfId;
    	this.value=value;
    }
    public CileInfo() { 	
    }
	public void setCileInfo(HttpServletRequest req) {
		setId(req.getParameter("id"));
		setCileName(req.getParameter("cileName"));
		setStfId(req.getParameter("stfId"));
		setValue(req.getParameter("value"));
		setSex(req.getParameter("sex"));
	}
    public boolean hasValue() {
    	if(id==null) return false;
    	if(stfId==null) return false;
    	if(cileName!=null) return true;
    	if(value!=null) return true;
    	return false;
    }
    public String getId() {
		return id;
	}
	public void setId(String id) {
		if(id!=null) {
		this.id = id;
		}
	}
	public String getCileName() {
		return cileName;
	}
	public void setCileName(String cileName) {
		if(cileName!=null) {
			this.cileName = cileName;	
		}
	}
	public String getStfId() {
		return stfId;
	}
	public void setStfId(String stfId) {
		if(stfId!=null) {
		this.stfId = stfId;
		}
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		if(value!=null) {
		   this.value = value;
		}
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(String sex) {
		if(sex != null && sex != "") 
		{
			this.sex = Integer.valueOf(sex);
		}
	}	 
    public String getStaffName() {
		return staffName;
	}	
	public void setStaffName(String staffName) {
		if(staffName!=null) {
		this.staffName = staffName;
		}
	}
}
