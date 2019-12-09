package com.llzz.llzz.api.dao;

import com.llzz.llzz.api.model.po.TaskPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jgl
 * @since 2019-12-05
 */
@Mapper
public interface UserTaskDao extends BaseMapper<TaskPO> {

}
