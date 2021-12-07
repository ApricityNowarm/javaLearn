package com.smbms.utils;

public class PageSupport {
    private int pageCount;
    private int pageCurrentNo = 1;
    private int count;
    private int pageSize;

    public PageSupport(){
    }

    public PageSupport(int count,int pageSize){
        if(count > 0 && pageSize >0) {
            this.count = count;
            this.pageSize = pageSize;
            this.calculate();
        }
    }

    public int getPageCount() {
        return pageCount;
    }



    public void setPageCurrentNo(int pageCurrentNo){
        if(pageCurrentNo > 0) {
            this.pageCurrentNo = pageCurrentNo;
        }
    }
    public int getPageCurrentNo() {
        return pageCurrentNo;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if(count > 0) {
            this.count = count;
            this.calculate();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if(pageSize > 0) {
            this.pageSize = pageSize;
            this.calculate();
        }
    }

    private void calculate(){
        this.pageCount = count / pageSize;
        if(count % pageSize > 0){
            pageCount += 1;
        }
    }
}
