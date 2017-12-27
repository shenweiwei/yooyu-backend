package com.yooyu.backend.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 后台分页切面
 * 
 * @author shenweiwei
 * @date 2017年12月11日
 */
@Component
@Aspect
public class PageAspect {
    /**
     * 拦截repository的mapper类，以及方法参数以Page结尾
     * 
     * @author shenweiwei
     * @date 2017年12月11日
     * @param jp
     * @param inputPage
     * @return
     * @throws Throwable
     */
    @Before("execution(* com.yooyu.backend..repository.*.*(..)) && args(..,page)")
    public void handlePageHelper(Page<?> page) throws Throwable {
        if (page != null) {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            if (page.getOrderBy() != null) {
                PageHelper.orderBy(page.getOrderBy());
            }
        }
    }
}
