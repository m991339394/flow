package io.renren.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jgl
 * @since 2019-12-20
 */
@Mapper
public interface AdminUserDao extends BaseMapper<UserPO> {

}
