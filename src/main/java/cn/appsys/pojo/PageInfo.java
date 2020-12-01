package cn.appsys.pojo;

public class PageInfo {


    private  int currentPageNo;//当前页面

    private  int everPageNum;//每页记录数

    private  int totalCount; //记录总数

    private int totalPageCount;  //总页数

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getEverPageNum() {
        return everPageNum;
    }

    public void setEverPageNum(int everPageNum) {
        this.everPageNum = everPageNum;
    }



    public int getTotalPageCount() {
        System.out.println((totalCount+everPageNum-1)/everPageNum);
        return (totalCount+everPageNum-1)/everPageNum;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    //   分页查询时，获取当前页的第一条数据的  索引
    public int getSelectEverPageFirst(){
        return (this.currentPageNo-1)*this.everPageNum;//当前页面数-1，乘以每页记录数
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "currentPageNo=" + currentPageNo +
                ", everPageNum=" + everPageNum +
                ", totalCount=" + totalCount +
                ", totalPageCount=" + totalPageCount +
                '}';
    }
}
