package com.win.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.win.dao.impl.CileDao;
import com.win.pojo.CileInfo;
import com.win.pojo.MageInfo;
public class CustomerServlet extends BaseServlet {
     private CileDao cileDao=new CileDao();
     
     public void toCilePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	 List<CileInfo> list=cileDao.query();
    	 request.setAttribute("list",list);
    	 request.getRequestDispatcher("/page/cile/cile_list.jsp").forward(request,response);
     }
     
     public void toValuePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	 List<CileInfo> list=cileDao.query2();
    	 request.setAttribute("value",list);
    	 request.getRequestDispatcher("/page/cile/cile_value.jsp").forward(request,response);
     }
     
	 public void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ɾ���ÿͻ�
		String id = req.getParameter("id");
		int result = cileDao.del(id);
		if (result > 0) {
			responseObject("1", resp);
		} else {
			responseObject("0", resp);
		}
	}
	//��ѯ���Ŀͻ��б�
    public void CileList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
			//��ѯ�ͻ��б�
			CileInfo cileInfo =new CileInfo();
			cileInfo.setId(req.getParameter("cileId"));
			cileInfo.setCileName(req.getParameter("cileName"));
			List<CileInfo> list=cileDao.query3(cileInfo);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/page/cile/cile_list.jsp").forward(req, resp);
		}
  //��ѯ���Ŀͻ�������Ϣ�б�
    public void CileList2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
			//��ѯ�ͻ��б�
			CileInfo cileInfo =new CileInfo();
			cileInfo.setId(req.getParameter("cileId"));
			cileInfo.setCileName(req.getParameter("cileName"));
			List<CileInfo> list=cileDao.query4(cileInfo);
			req.setAttribute("value", list);
			req.getRequestDispatcher("/page/cile/cile_value.jsp").forward(req, resp);
		}
	 //��ת�������û���Ϣҳ��
	 public void toUpPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�ͻ�id
		String id = req.getParameter("id");
		//����id��ѯԱ����Ϣ
		CileInfo cf = new CileInfo();
		cf.setId(id);
		CileInfo cileOne =cileDao.queryOne(id);
		req.setAttribute("cileOne", cileOne);
		//����ת������ת������Ա����Ϣҳ��
		req.getRequestDispatcher("/page/cile/cile_update.jsp").forward(req, resp);
	}
	
	//���¿ͻ���Ϣ
	public void upCileInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("cileOne");
		//�����������
		CileInfo cf = new CileInfo();		
		cf.setCileInfo(req);
		//----------------------------����--------------------------------------
		//����Ա����Ϣ
		int result = cileDao.update(cf);		
		if (result < 1) {//����ʧ��
			responseObject("0", resp);
		} else {
			responseObject("1", resp);
		}
	} 
	 //��ת�������û�������Ϣҳ��
	 public void toUpPage2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�ͻ�id
		String id = req.getParameter("id");
		//����======================================================
		System.out.println(id);
		//����id��ѯԱ����Ϣ
		CileInfo cf = new CileInfo();
		cf.setId(id);
		CileInfo cileOne =cileDao.queryOne(id);
		req.setAttribute("cileTwo", cileOne);
		//����ת������ת������Ա����Ϣҳ��
		req.getRequestDispatcher("/page/cile/cile_value_update.jsp").forward(req, resp);
	}
	//���¿ͻ����ѽ��
	public void upCileValueInfo(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("cileTwo");
		//�����������
		CileInfo cf = new CileInfo();		
		cf.setCileInfo(req);

		//����Ա����Ϣ
		int result = cileDao.update2(cf);		
		if (result < 1) {//����ʧ��
			responseObject("0", resp);
		} else {
			responseObject("1", resp);
		}
	}
	//��ת����ӿͻ�ҳ��
	public void addCilePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/page/cile/cile_add.jsp").forward(req, resp);
	}
	//��ӿͻ���Ϣ
	public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//�����������
		CileInfo sf = new CileInfo();		
		sf.setCileInfo(req);
		String a=req.getParameter("value");
		System.out.println(a);
		System.out.println(sf.getValue());
		//���Ա����Ϣ
		Integer result = cileDao.add(sf);
		if (result < 1) {//����ʧ��
			responseObject("0", resp);
		} else {//����ɹ�
			responseObject("1", resp);
		}
	}
}
