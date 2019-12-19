package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.Result;
import io.renren.modules.app.model.po.UserUserRelationPO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jgl
 * @since 2019-12-06
 */
public interface UserUserRelationService extends IService<UserUserRelationPO> {

    Result<?> insertRelation(HttpServletRequest request , UserUserRelationPO relationPO);
}
