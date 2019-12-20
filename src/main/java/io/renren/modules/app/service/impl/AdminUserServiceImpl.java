package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.app.dao.AdminUserDao;
import io.renren.modules.app.model.po.UserPO;
import io.renren.modules.app.service.AdminUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jgl
 * @since 2019-12-20
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserDao, UserPO> implements AdminUserService {

}
