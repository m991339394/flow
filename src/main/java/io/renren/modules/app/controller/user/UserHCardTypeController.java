package io.renren.modules.app.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.Constants.HCardTypeConstants;
import io.renren.common.Result;
import io.renren.modules.app.model.po.HCardTypePO;
import io.renren.modules.app.model.po.UserCommodityhitsPO;
//import io.renren.modules.app.model.po.UserPO;
import io.renren.modules.app.service.UserCommodityhitsService;
import io.renren.modules.app.service.UserHCardTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 心意卡类型 前端控制器
 * </p>
 *
 * @author cth
 * @since 2019-09-27
 */
@RestController
@RequestMapping("api/user/hCardType")
public class UserHCardTypeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserHCardTypeService userHCardTypeService;

    @Autowired
    UserCommodityhitsService userCommodityhitsService;

    /**
     * @throws
     * @Title: list
     * @Description: 查询卡类型
     * @param: @return
     * @return: Result<?>
     */
    @RequestMapping(value = "/list.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> list() {
        Map<String, Object> relMap = new HashMap<String, Object>();
        try {
            QueryWrapper<HCardTypePO> qWrapper = new QueryWrapper<HCardTypePO>();
            qWrapper.eq("upper_shelf", HCardTypeConstants.UPPER_SHELF);
            List<HCardTypePO> list = userHCardTypeService.list(qWrapper);
            relMap.put("data", list);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Result.fail(500, e.getMessage());
        }
        return Result.success(relMap);
    }

    /**
     * @return io.renren.common.Result<?>
     * @Author jgl
     * @Description 根据卡类型id查询
     * @Date 19:24 2019/12/3
     * @Param [id]
     **/
    @RequestMapping(value = "/getById.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> getById(@RequestParam(value = "id") Long id) {
        Map<String, Object> relMap = new HashMap<String, Object>();
        long typeid  = id;
        try {
            QueryWrapper<UserCommodityhitsPO> queryWrapper = new QueryWrapper();
            queryWrapper.eq("typeid", typeid);
//            UserCommodityhitsPO byId = userCommodityhitsService.getById(queryWrapper);
        if (userCommodityhitsService.getOne(queryWrapper) == null) {
            UserCommodityhitsPO userCommodityhitsPO = new UserCommodityhitsPO();
            userCommodityhitsPO.setTypeid(id);
            userCommodityhitsPO.setHits((long)1);
            HCardTypePO hCardTypePO = userHCardTypeService.getById(id);
            userCommodityhitsPO.setName(hCardTypePO.getName());
            userCommodityhitsService.save(userCommodityhitsPO);
        }else {
			UserCommodityhitsPO userCommodityhitsPO = userCommodityhitsService.getOne(queryWrapper);
			userCommodityhitsPO.setHits(userCommodityhitsPO.getHits()+1);
            HCardTypePO hCardTypePO = userHCardTypeService.getById(id);
            userCommodityhitsPO.setName(hCardTypePO.getName());
			userCommodityhitsService.updateById(userCommodityhitsPO);
		}
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, e.getMessage());
        }

        try {
            HCardTypePO hCardTypePO = userHCardTypeService.getById(id);
            relMap.put("data", hCardTypePO);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, e.getMessage());
        }
        return Result.success(relMap);
    }
}

