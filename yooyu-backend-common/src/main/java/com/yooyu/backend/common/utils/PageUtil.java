package com.yooyu.backend.common.utils;

import com.github.pagehelper.Page;
import com.yooyu.backend.common.pojo.PageParam;

/**
 * Page工具类
 * 
 * @author shenweiwei
 * @date 2017年12月11日
 */

public class PageUtil {

    /**
     * 转换输入page参数为通用的page对象
     *
     * @author s8xriw
     * @date 2017年12月11日
     * @param inputPage
     * @return
     */
    public static Page<?> getPage(PageParam inputPage) {
        int pageNum = inputPage.getPageNum() == null ? 1 : inputPage.getPageNum();
        int pageSize = inputPage.getPageSize() == null ? 10 : inputPage.getPageSize();
        Page<?> page = new Page<>(pageNum, pageSize);
        page.setOrderBy(inputPage.getOrderBy());
        return page;
    }
}
