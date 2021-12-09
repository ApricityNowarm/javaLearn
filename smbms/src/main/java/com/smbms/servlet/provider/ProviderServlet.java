package com.smbms.servlet.provider;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import com.smbms.bean.Provider;
import com.smbms.bean.User;
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
                queryPro(req, resp);
                break;
            case "delprovider":
                delPro(req, resp);
                break;
            case "modify":
                toModifyPro(req, resp);
                break;
            case "view":
                toViewPro(req, resp);
                break;
            case "modifypro":
                modifyPro(req, resp);
                break;
            case "add":
                addPro(req, resp);
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
        Provider provider = providerService.getProviderByCode(proCode);
        HashMap<String, String> map = new HashMap<>();

        if (StringUtils.isNullOrEmpty(proCode)) {
            map.put("proCode", "null");
        } else {
            if (provider != null) {
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

    private void addPro(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
        boolean flag = providerService.addProvider(provider);
        if (flag) {
            resp.sendRedirect(req.getContextPath() + "/jsp/provider.do?method=query");
        } else {
            req.getRequestDispatcher("providermodify.jsp").forward(req, resp);
        }

    }

    private void modifyPro(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
            resp.sendRedirect(req.getContextPath() + "/jsp/provider.do?method=query");
        } else {
            req.getRequestDispatcher("providermodify.jsp").forward(req, resp);
        }
    }

    private void toViewPro(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (StringUtils.isNullOrEmpty(req.getParameter("proid"))) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        } else {
            int proId = Integer.parseInt(req.getParameter("proid"));
            Provider provider = null;
            ProviderService providerService = new ProviderServiceImpl();
            provider = providerService.getProviderByCode(proId);
            if (provider != null) {
                req.setAttribute("provider", provider);
                req.getRequestDispatcher("providerview.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
            }
        }
    }

    private void toModifyPro(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (StringUtils.isNullOrEmpty(req.getParameter("proid"))) {
            resp.sendRedirect(req.getContextPath() + "/jsp/providerlist.jsp");
        } else {
            int proId = -1;
            proId = Integer.parseInt(req.getParameter("proid"));
            ProviderService providerService = new ProviderServiceImpl();
            Provider provider = null;
            provider = providerService.getProviderByCode(proId);
            if (provider != null) {
                req.setAttribute("provider", provider);
                req.getRequestDispatcher("providermodify.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
            }
        }
    }

    private void delPro(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void queryPro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        if (pageIndex < 1) {
            pageSupport.setPageCurrentNo(1);
        } else if (pageIndex > pageSupport.getPageCount()) {
            pageSupport.setPageCurrentNo(pageSupport.getPageCount());
        } else {
            pageSupport.setPageCurrentNo(pageIndex);
        }

        List<Provider> providerList = providerService.getProviderList(queryProCode, queryProName,
                pageSupport.getStartIndex(), pageSupport.getPageSize());

        req.setAttribute("queryProCode", queryProCode);
        req.setAttribute("queryProName", queryProName);
        req.setAttribute("providerList", providerList);
        req.setAttribute("totalPageCount", pageSupport.getPageCount());
        req.setAttribute("totalCount", pageSupport.getCount());
        req.setAttribute("currentPageNo", pageSupport.getPageCurrentNo());

        req.getRequestDispatcher("providerlist.jsp").forward(req, resp);
    }
}

