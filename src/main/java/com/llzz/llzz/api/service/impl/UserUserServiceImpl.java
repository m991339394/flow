package com.llzz.llzz.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.llzz.llzz.api.dao.UserUserDao;
import com.llzz.llzz.api.model.po.UserPO;
import com.llzz.llzz.api.service.UserUserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jgl
 * @since 2019-12-03
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserUserServiceImpl extends ServiceImpl<UserUserDao, UserPO> implements UserUserService {

}
