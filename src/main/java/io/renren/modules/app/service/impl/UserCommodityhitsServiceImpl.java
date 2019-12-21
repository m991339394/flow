package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.app.dao.UserCommodityhitsDao;
import io.renren.modules.app.model.po.UserCommodityhitsPO;
import io.renren.modules.app.service.UserCommodityhitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jgl
 * @since 2019-12-20
 */
@Service
public class UserCommodityhitsServiceImpl extends ServiceImpl<UserCommodityhitsDao, UserCommodityhitsPO> implements UserCommodityhitsService {

    @Autowired
    UserCommodityhitsService userCommodityhitsService;
    @Override
    public List<UserCommodityhitsPO> query(int pageNo, int pageSize) {

        List<UserCommodityhitsPO> userCommodityhitsPOS = userCommodityhitsService.list();
        int firstIndex = (pageNo -1) * pageSize;
        int lastIndex = pageNo * pageSize;
        if(userCommodityhitsService.list().size() < firstIndex){
            return null;
        }else if (userCommodityhitsService.list().size() < lastIndex){
            List<UserCommodityhitsPO> userCommodityhitsPOS1 = userCommodityhitsPOS.subList(firstIndex, userCommodityhitsService.list().size());
            return userCommodityhitsPOS1;
        }else {

            List<UserCommodityhitsPO> userCommodityhitsPOS1 = userCommodityhitsPOS.subList(firstIndex, lastIndex);
            return userCommodityhitsPOS1;
        }





    }
}
