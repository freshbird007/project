package com.leaves.smalltiger.common.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用基础mapper
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
