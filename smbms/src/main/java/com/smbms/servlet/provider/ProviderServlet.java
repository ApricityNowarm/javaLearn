package com.smbms.servlet.provider;

import com.mysql.cj.util.StringUtils;
import com.smbms.bean.Provider;
import com.smbms.service.provider.ProviderService;
import com.smbms.service.provider.ProviderServiceImpl;
import com.smbms.utils.FinalParam;
import com.smbms.utils.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method){
            case "query" : queryPro(req,resp); break;
            case "delprovider" : delPro(req,resp); break;
            case "modify" : toModifyPro(req,resp); break;
            case "view" : toViewPro(req,resp); break;
            case "modifypro" : modifyPro(req,resp); break;
            default : resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    private void modifyPro(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toViewPro(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toModifyPro(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void delPro(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void queryPro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryProCode = req.getParameter("queryProCode");
        String queryProName = req.getParameter("queryProName");
        int pageIndex = 1;
        if(!StringUtils.isNullOrEmpty(req.getParameter("pageIndex"))){
            pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        }
        ProviderService providerService = new ProviderServiceImpl();

        int proCount = 0;
        proCount = providerService.getProviderCount(queryProCode,queryProName);

        PageSupport pageSupport = new PageSupport(proCount, FinalParam.PAGE_SIZE);
        if(pageIndex < 1){
            pageSupport.setPageCurrentNo(1);
        }else if(pageIndex > pageSupport.getPageCount()){
            pageSupport.setPageCurrentNo(pageSupport.getPageCount());
        }else {
            pageSupport.setPageCurrentNo(pageIndex);
        }

        List<Provider> providerList = providerService.getProviderList(queryProCode,queryProName,
                pageSupport.getStartIndex(),pageSupport.getPageSize());

        req.setAttribute("queryProCode",queryProCode);
        req.setAttribute("queryProName",queryProName);
        req.setAttribute("providerList",providerList);
        req.setAttribute("totalPageCount",pageSupport.getPageCount());
        req.setAttribute("totalCount",pageSupport.getCount());
        req.setAttribute("currentPageNo",pageSupport.getPageCurrentNo());

        req.getRequestDispatcher("providerlist.jsp").forward(req,resp);
    }
}

