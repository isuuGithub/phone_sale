package com.suge.bean;

import java.util.List;

/**
 * @author suu
 * @create 2020-12-11 22:33
 */
public class Page<T> {

    public static  final  Integer PAGE_SIZE = 4;
    /**
     * 当前页面显示项目的数量
     */
    private Integer pageSize =PAGE_SIZE;


    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 总页码
     */
    private Integer pageTotal;

    /**
     * 总记录数
     */
    private Integer itemTotalCount;


    /**
     * 当前页数据
     */
    private List<T> items;

    /**
     * 分页条功能请求地址
     * 在不同的servlet中给该属性赋不同的值，可以复用于多个页面
     */
    private String url;


    public Page() {
    }

    public Page(Integer pageSize, Integer pageNo, Integer pageTotal, Integer itemTotalCount, List<T> items, String url) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.itemTotalCount = itemTotalCount;
        this.items = items;
        this.url = url;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 该方法内含有数据边界的有效检查
     * 需要用到pageTotal变量，所以要在设置了pageTotal之后才可以调用
     * @param pageNo
     */
    public void setPageNo(Integer pageNo) {
        /* 数据边界的有效检查 */
        if (pageNo < 1) {
            pageNo = 1;
        }
        // 设置当前页码 数据边界的有效检查
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getItemTotalCount() {
        return itemTotalCount;
    }

    public void setItemTotalCount(Integer itemTotalCount) {
        this.itemTotalCount = itemTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", itemTotalCount=" + itemTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
