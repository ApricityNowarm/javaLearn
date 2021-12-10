package com.smbms.servlet.bill;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import com.smbms.bean.Bill;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "query":
                billListView(req, resp,"billlist.jsp");
                break;
            case "add":
                addBill(req,resp,"/jsp/bill.do?method=query","billadd.jsp");
                break;
            case "delbill":
                delBill(req, resp);
                break;
            case "view":
                toBillView(req, resp,"billview.jsp");
                break;
            case "modify":
                toBillModifyView(req, resp,"billmodify.jsp");
                break;
            case "modifysave":
                modifyBillSave(req, resp,"/jsp/bill.do?method=query","billmodify.jsp");
                break;
            case "getproviderlist":
                getProviderList(req, resp);
                break;
            case "billExist":
                billExist(req,resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    private void billExist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String billCode = req.getParameter("billCode");
        BillService billService = new BillServiceImpl();
        HashMap<String, String> map = new HashMap<>();

        if (StringUtils.isNullOrEmpty(billCode)) {
            map.put("billCode", "null");
        } else {
            if (billService.getBillByCode(billCode) != null) {
                map.put("billCode", "exist");
            } else {
                map.put("billCode", "noExist");
            }
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSON.toJSONString(map));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    private void addBill(HttpServletRequest req, HttpServletResponse resp,String url,String failUrl) throws IOException, ServletException {
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productUnit = req.getParameter("productUnit");
        BigDecimal productCount = new BigDecimal(req.getParameter("productCount"));
        BigDecimal totalPrice = new BigDecimal(req.getParameter("totalPrice"));
        int providerId = Integer.parseInt(req.getParameter("providerId"));
        int isPayment = Integer.parseInt(req.getParameter("isPayment"));
        int createdBy = ((User) req.getSession().getAttribute(FinalParam.USER_SESSION)).getId();
        Date creationDate = new Date();

        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setProviderId(providerId);
        bill.setIsPayment(isPayment);
        bill.setCreatedBy(createdBy);
        bill.setCreationDate(creationDate);

        BillService billService = new BillServiceImpl();
        if(billService.addBill(bill)){
            resp.sendRedirect(req.getContextPath() +url);
        }else {
            req.setAttribute("bill",bill);
            req.getRequestDispatcher(failUrl).forward(req,resp);
        }
    }

    private void getProviderList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        ProviderService providerService = new ProviderServiceImpl();
        List<Provider> providerList = providerService.getAllProviderList();
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSON.toJSONString(providerList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    private void modifyBillSave(HttpServletRequest req, HttpServletResponse resp, String url, String failUrl) throws IOException, ServletException {
        int billId = Integer.parseInt(req.getParameter("id"));
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productUnit = req.getParameter("productUnit");
        BigDecimal productCount = new BigDecimal(req.getParameter("productCount"));
        BigDecimal totalPrice = new BigDecimal(req.getParameter("totalPrice"));
        int providerId = Integer.parseInt(req.getParameter("providerId"));
        int isPayment = Integer.parseInt(req.getParameter("isPayment"));
        int modifyBy = ((User) req.getSession().getAttribute(FinalParam.USER_SESSION)).getId();
        Date modifyDate = new Date();

        Bill bill = new Bill();
        bill.setId(billId);
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setProviderId(providerId);
        bill.setIsPayment(isPayment);
        bill.setModifyBy(modifyBy);
        bill.setModifyDate(modifyDate);

        BillService billService = new BillServiceImpl();
        if(billService.updateBill(bill)){
            resp.sendRedirect(req.getContextPath() + url);
        }else {
            req.setAttribute("bill",bill);
            req.getRequestDispatcher(failUrl).forward(req,resp);
        }

    }

    private void toBillModifyView(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException, ServletException {
        if(StringUtils.isNullOrEmpty(req.getParameter("billid"))){
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }else {
            int billId = Integer.parseInt(req.getParameter("billid"));
            BillService billService = new BillServiceImpl();
            Bill bill = billService.getBillById(billId);
            if(bill != null){
                req.setAttribute("bill",bill);
                req.getRequestDispatcher(url).forward(req,resp);
            }else {
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
            }
        }
    }

    private void toBillView(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException, ServletException {
        if (StringUtils.isNullOrEmpty(req.getParameter("billid"))) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        } else {
            int billId = Integer.parseInt(req.getParameter("billid"));
            if (billId > 0) {
                BillService billService = new BillServiceImpl();
                Bill bill = billService.getBillById(billId);
                req.setAttribute("bill", bill);
                req.getRequestDispatcher(url).forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
            }
        }
    }

    private void delBill(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int billid = Integer.parseInt(req.getParameter("billid"));
        HashMap<String,String> map = new HashMap<>();
        BillService billService = new BillServiceImpl();

        if(billService.getBillById(billid) != null){
            if(billService.delBill(billid)){
                map.put("delResult","true");
            }else {
                map.put("delResult","false");
            }
        }else{
            map.put("delResult","notexist");
        }

        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSON.toJSONString(map));
        outPrintWriter.flush();
        outPrintWriter.close();

    }

    private void billListView(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        String queryProductName = req.getParameter("queryProductName");
        int queryProviderId = 0;
        if (!StringUtils.isNullOrEmpty(req.getParameter("queryProviderId"))) {
            queryProviderId = Integer.parseInt(req.getParameter("queryProviderId"));
        }
        int queryIsPayment = 0;
        if (!StringUtils.isNullOrEmpty(req.getParameter("queryIsPayment"))) {
            queryIsPayment = Integer.parseInt(req.getParameter("queryIsPayment"));
        }
        int pageIndex = 1;
        if (!StringUtils.isNullOrEmpty(req.getParameter("pageIndex"))) {
            pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        }

        BillService billService = new BillServiceImpl();
        ProviderService providerService = new ProviderServiceImpl();

        PageSupport pageSupport =
                new PageSupport(billService.getBillCount(queryProductName, queryProviderId, queryIsPayment), FinalParam.PAGE_SIZE);
        pageSupport.setPageCurrentNo(pageIndex);

        List<Provider> providerList = null;
        List<Bill> billList = null;

        providerList = providerService.getAllProviderList();
        billList = billService.getBillList(queryProductName, queryProviderId,
                queryIsPayment, pageSupport.getStartIndex(), pageSupport.getPageSize());

        req.setAttribute("queryProductName", queryProductName);
        req.setAttribute("queryProviderId", queryProviderId);
        req.setAttribute("providerList", providerList);
        req.setAttribute("billList", billList);
        req.setAttribute("queryIsPayment", queryIsPayment);
        req.setAttribute("totalPageCount", pageSupport.getPageCount());
        req.setAttribute("totalCount", pageSupport.getCount());
        req.setAttribute("currentPageNo", pageSupport.getPageCurrentNo());

        req.getRequestDispatcher(url).forward(req, resp);
    }
}
