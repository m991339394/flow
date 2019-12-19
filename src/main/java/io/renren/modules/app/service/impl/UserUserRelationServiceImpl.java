package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.Constants.HCardBalanceLogConstants;
import io.renren.common.Result;
import io.renren.common.exception.RRException;
import io.renren.common.utils.IPUtils;
import io.renren.modules.app.dao.UserUserRelationDao;
import io.renren.modules.app.model.po.UserHCardBalanceLogPO;
import io.renren.modules.app.model.po.UserHCardPursePO;
import io.renren.modules.app.model.po.UserUserRelationPO;
import io.renren.modules.app.service.UserUserHCardBalanceLogService;
import io.renren.modules.app.service.UserUserHCardPurseService;
import io.renren.modules.app.service.UserUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jgl
 * @since 2019-12-06
 */
@Service
public class UserUserRelationServiceImpl extends ServiceImpl<UserUserRelationDao, UserUserRelationPO> implements UserUserRelationService {
    @Autowired
    UserUserRelationDao relationDao;
    @Autowired
    UserUserHCardPurseService hCardPurseService;
    @Autowired
    UserUserHCardBalanceLogService balanceLogService;

    @Override
    public Result<?> insertRelation(HttpServletRequest request , UserUserRelationPO relationPO) {

        // 获取本机的ip地址
        String error_msg="增加任务记录";
        String ip = IPUtils.getIpAddr(request);
        Long hCardTypeId = relationPO.getHCardTypeId();
        Long parentUserId = relationPO.getParentUserId();
        Long childUserId = relationPO.getChildUserId();
        if(parentUserId.equals(childUserId)){
            return Result.success();
        }
        QueryWrapper<UserUserRelationPO> queryWrapper=new QueryWrapper();
        queryWrapper.eq("hCard_type_id" ,hCardTypeId);
        queryWrapper.eq("parent_user_id" ,parentUserId);
        queryWrapper.eq("child_user_id" ,childUserId);
        UserUserRelationPO relationPO1=relationDao.selectOne(queryWrapper);
        if(relationPO1==null){
            relationPO.setIp(ip);
            int num=relationDao.insert(relationPO);

            // 2、将对应金额充值到转发者余额
            if(num==1) {
                Float hCardbalance=1f;
                // 查询我的心意卡余额
                UserHCardPursePO userHCardPursePO=hCardPurseService.getByUserId(parentUserId);
                Float balanceFront=userHCardPursePO.getBalance();
                String openid=userHCardPursePO.getOpenid();
                userHCardPursePO.setBalance(hCardbalance);
                // 充值
                num = hCardPurseService.recharge(userHCardPursePO);
                if(num==1) {
                    // 充值成功
                    UserHCardBalanceLogPO hCardBalanceLogPO=new UserHCardBalanceLogPO();
                    hCardBalanceLogPO.setOpenid(openid);
                    hCardBalanceLogPO.setBalanceFront(balanceFront);
                    hCardBalanceLogPO.setBalanceAfter(balanceFront+hCardbalance);
                    hCardBalanceLogPO.setBalance(hCardbalance);
                    hCardBalanceLogPO.setUseStatus(HCardBalanceLogConstants.USE_STATES_RECHARGE);
                    hCardBalanceLogPO.setSourceType(HCardBalanceLogConstants.SOURCE_TYPE_ACTIVE);
                    hCardBalanceLogPO.setRemark("转发任务");
                    hCardBalanceLogPO.setHCardTypeId(hCardTypeId);
                    boolean flag=balanceLogService.insert(hCardBalanceLogPO);
                    if(!flag) {
                        error_msg="增加任务记录，增加日志记录" + "异常,"+" hCardTypeId ="+hCardTypeId;
                        throw new RRException(error_msg,new Throwable());
                    }
                    return Result.success("激活成功");
                }else {
                    // 抛出异常
                    error_msg="增加任务记录，充值失败" + "异常,"+" hCardTypeId ="+hCardTypeId;
                    throw new RRException( error_msg,new Throwable());
                }
            }
        }

        return Result.success();
    }
}
