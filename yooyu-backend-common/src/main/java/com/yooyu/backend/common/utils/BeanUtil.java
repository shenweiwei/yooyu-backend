package com.yooyu.backend.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.yooyu.backend.common.exception.AppException;

/**
 * 简单封装BeanUtils, 实现深度转换Bean<->Bean的Mapper.实现:
 * @author shenweiwei
 * create on 2017-11-23
 */
public class BeanUtil {
    private static Logger logger = LogManager.getLogger(BeanUtil.class);

    public static <T> T map(Object source, Class<T> destinationClass) {
        if(null == source){return null;}

        try {
            T t = destinationClass.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            logger.error(e);
            throw new AppException("对象转换异常");
        }
    }

    public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        if(null == sourceList || sourceList.isEmpty()){return null;}

        List<T> destinationList = new ArrayList<>();
        for (Object source : sourceList) {
            destinationList.add(BeanUtil.map(source,destinationClass ));
        }
        return destinationList;
    }
}