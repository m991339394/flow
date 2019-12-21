package io.renren.modules.app.service;

import io.renren.modules.app.model.po.UserCommodityhitsPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jgl
 * @since 2019-12-20
 */
public interface UserCommodityhitsService extends IService<UserCommodityhitsPO> {

    List<UserCommodityhitsPO> query(int pageNo , int pageSize);
}
