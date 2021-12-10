package com.smbms.servlet.provider;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import com.smbms.bean.Provider;
import com.smbms.bean.User;
import com.smbms.service.bill.BillService;
import com.smbms.service.bill.BillServiceImpl;
import com.smbms.service.provider.ProviderService;
import com.smbms.service.provider.ProviderServiceImpl;
import com.smbms.utils.FinalParam;
import com.smbms.utils.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "query":
                proListView(req, resp,"providerlist.jsp");
                break;
            case "delprovider":
                delPro(req, resp);
                break;
            case "modify":
                toProModifyView(req, resp,"providermodify.jsp");
                break;
            case "view":
                toProView(req, resp,"providerview.jsp");
                break;
            case "modifypro":
                proModifySave(req, resp,"/jsp/provider.do?method=query","providermodify.jsp");
                break;
            case "add":
                addPro(req, resp,"/jsp/provider.do?method=query","provideradd.jsp");
                break;
            case "proExist":
                proExist(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    private void proExist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String proCode = req.getParameter("proCode");
        ProviderService providerService = new ProviderServiceImpl();
        HashMap<String, String> map = new HashMap<>();

        if (StringUtils.isNullOrEmpty(proCode)) {
            map.put("proCode", "null");
        } else {
            if (providerService.getProviderByCode(proCode) != null) {
                map.put("proCode", "exist");
            } else {
                map.put("proCode", "noExist");
            }
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSON.toJSONString(map));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    private void addPro(HttpServletRequest req, HttpServletResponse resp,String url,String failUrl) throws IOException, ServletException {
        int createdBy = ((User) req.getSession().getAttribute(FinalParam.USER_SESSION)).getId();
        Date creationDate = new Date();
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");

        Provider provider = new Provider();
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setProDesc(proDesc);
        provider.setCreatedBy(createdBy);
        provider.setCreationDate(creationDate);

        ProviderService providerService = new ProviderServiceImpl();
        if (providerService.addProvider(provider)) {
            resp.sendRedirect(req.getContextPath() + url);
        } else {
            req.setAttribute("provider",provider);
            req.getRequestDispatcher(failUrl).forward(req, resp);
        }

    }

    private void proModifySave(HttpServletRequest req, HttpServletResponse resp, String url, String failUrl) throws IOException, ServletException {
        int modifyBy = ((User) req.getSession().getAttribute(FinalParam.USER_SESSION)).getId();
        Date modifyDate = new Date();
        int proId = Integer.parseInt(req.getParameter("proId"));
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");

        Provider provider = new Provider();
        provider.setId(proId);
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setProDesc(proDesc);
        provider.setModifyBy(modifyBy);
        provider.setModifyDate(modifyDate);

        ProviderService providerService = new ProviderServiceImpl();
        boolean flag = providerService.updateProvider(provider);
        if (flag) {
            resp.sendRedirect(req.getContextPath() + url);
        } else {
            req.setAttribute("provider",provider);
            req.getRequestDispatcher(failUrl).forward(req, resp);
        }
    }

    private void toProView(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException, ServletException {
        if (StringUtils.isNullOrEmpty(req.getParameter("proid"))) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        } else {
            int proId = Integer.parseInt(req.getParameter("proid"));
            ProviderService providerService = new ProviderServiceImpl();
            Provider provider = providerService.getProviderById(proId);
            if (provider != null) {
                req.setAttribute("provider", provider);
                req.getRequestDispatcher(url).forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
            }
        }
    }

    private void toProModifyView(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException, ServletException {
        if (StringUtils.isNullOrEmpty(req.getParameter("proid"))) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        } else {
            int proId = -1;
            proId = Integer.parseInt(req.getParameter("proid"));
            ProviderService providerService = new ProviderServiceImpl();
            Provider provider = null;
            provider = providerService.getProviderById(proId);
            if (provider != null) {
                req.setAttribute("provider", provider);
                req.getRequestDispatcher(url).forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
            }
        }
    }

    private void delPro(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int proid = Integer.parseInt(req.getParameter("proid"));
        HashMap<String,Object>map = new HashMap<>();

        BillService billService = new BillServiceImpl();
        ProviderService providerService = new ProviderServiceImpl();

        int billCount = billService.getBillCount(null,proid,0);
        System.out.println("订单总数========" + billCount);
        if(billCount > 0){
            map.put("delResult",billCount);
        }else {
            if(providerService.getProviderById(proid) == null){
                map.put("delResult","notexist");
            }else if(providerService.delProvider(proid)){
                map.put("delResult","true");
            }else {
                map.put("delResult","false");
            }
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSON.toJSONString(map));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    private void proListView(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        String queryProCode = req.getParameter("queryProCode");
        String queryProName = req.getParameter("queryProName");
        int pageIndex = 1;
        if (!StringUtils.isNullOrEmpty(req.getParameter("pageIndex"))) {
            pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        }
        ProviderService providerService = new ProviderServiceImpl();

        int proCount = 0;
        proCount = providerService.getProviderCount(queryProCode, queryProName);

        PageSupport pageSupport = new PageSupport(proCount, FinalParam.PAGE_SIZE);

        pageSupport.setPageCurrentNo(pageIndex);

        List<Provider> providerList = providerService.getProviderList(queryProCode, queryProName,
                pageSupport.getStartIndex(), pageSupport.getPageSize());

        req.setAttribute("queryProCode", queryProCode);
        req.setAttribute("queryProName", queryProName);
        req.setAttribute("providerList", providerList);
        req.setAttribute("totalPageCount", pageSupport.getPageCount());
        req.setAttribute("totalCount", pageSupport.getCount());
        req.setAttribute("currentPageNo", pageSupport.getPageCurrentNo());

        req.getRequestDispatcher(url).forward(req, resp);
    }
}

