package com.win.pojo;
import javax.servlet.http.HttpServletRequest;
public class MageInfo {
    //???id
    private String id;
    //???????
    private String name;
    //??????
    private Integer sex;
    //??????
    private String user;
    //???????
    private String pass;
    //?????/???
    private Integer type;
    public MageInfo(){
    }
    public MageInfo(String id) {
        this.id=id;
    }
    public void setMageInfo(HttpServletRequest req) {
        setId(req.getParameter("id"));
        setName(req.getParameter("name"));
        setSex(req.getParameter("sex"));
        setUser(req.getParameter("user"));
        setPass(req.getParameter("pass"));
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        if(id != null && id != "") {
            this.id = id;
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name != null && name != "") {
            this.name = name;
        }
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(String sex) {
        if(sex != null && sex != "") {
            this.sex = Integer.valueOf(sex);
        }
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        if(user != null && user!="") {
            this.user = user;
        }
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        if(pass!=null&&pass!=" ") {
            this.pass = pass;
        }
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
}
