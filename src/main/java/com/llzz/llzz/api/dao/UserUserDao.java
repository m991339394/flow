package com.llzz.llzz.api.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.llzz.llzz.api.model.po.UserPO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jgl
 * @since 2019-12-03
 */
@Mapper
public interface UserUserDao extends BaseMapper<UserPO> {

}
