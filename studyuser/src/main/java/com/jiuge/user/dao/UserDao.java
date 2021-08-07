package com.jiuge.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuge.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
