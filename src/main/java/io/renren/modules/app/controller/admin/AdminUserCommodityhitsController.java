package io.renren.modules.app.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.Result;
import io.renren.common.utils.BaseController;
import io.renren.common.utils.StringUtils;
import io.renren.modules.app.model.po.UserCommodityhitsPO;
import io.renren.modules.app.service.UserCommodityhitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jgl
 * @since 2019-12-20
 */
@RestController
@RequestMapping("/api/admin/commodityhits")
public class AdminUserCommodityhitsController extends BaseController {


    @Autowired
    UserCommodityhitsService userCommodityhitsService;

    /**
     * 查找全部记录
     *
     * @return
     */
    @RequestMapping(value = "/list.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> list() {
        List<UserCommodityhitsPO> list = userCommodityhitsService.list();
        return Result.success(list);
    }

    /**
     * 按名称查找记录
     *
     * @return
     */
    @RequestMapping(value = "/getone.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> one(@RequestParam(value = "name") String name) {

        QueryWrapper<UserCommodityhitsPO> queryWrapper = new QueryWrapper();
        queryWrapper.like("name", name);
        UserCommodityhitsPO userCommodityhitsPO = userCommodityhitsService.getOne(queryWrapper);
        if (userCommodityhitsPO == null) {
            return Result.fail("无商品信息，请重新核对输入商品名称！！!");
        } else {
            return Result.success(userCommodityhitsPO);

        }
    }


    @RequestMapping(value = "/getLog.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> get(@RequestParam(value = "timeStart") String timeStart
            , @RequestParam(value = "timeStop") String timeStop) {
        QueryWrapper<UserCommodityhitsPO> queryWrapper = new QueryWrapper();
        queryWrapper.ge("time", timeStart);
//        queryWrapper.and(i->i.ge("time", timeStart).le("time", timeStop));
//        if (timeStop. == null || timeStop == " ") {
//            timeStop = (new Date()).toString();
//            System.out.println("10" + timeStop);
//        }
        if (StringUtils.isEmpty(timeStop)) {
            queryWrapper.le("time", new Date());
        } else {
            queryWrapper.le("time", timeStop);
        }

        List<UserCommodityhitsPO> userCommodityhitsPOS = userCommodityhitsService.list(queryWrapper);
        return Result.success(userCommodityhitsPOS);


    }

    @RequestMapping(value = "/getLogs.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> getLogs(@RequestParam(value = "timeStart") String timeStart
            , @RequestParam(value = "timeStop") String timeStop
            , @RequestParam(value = "name") String name) {

        QueryWrapper<UserCommodityhitsPO> queryWrapper = new QueryWrapper();
        queryWrapper.ge("time", timeStart);
        if (StringUtils.isEmpty(timeStop)) {
            queryWrapper.le("time", new Date());
        } else {
            queryWrapper.le("time", timeStop);
        }
        if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }
        List<UserCommodityhitsPO> userCommodityhitsPOS = userCommodityhitsService.list(queryWrapper);
        List<UserCommodityhitsPO> userCommodityhitsPOS2 = userCommodityhitsPOS.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(UserCommodityhitsPO :: getOpenid))), ArrayList::new));
        userCommodityhitsPOS.size();
        Map<String, Object> map = new HashMap<>();
        map.put("DAUs",userCommodityhitsPOS2.size());
        map.put("userCommodityhitsPOS",userCommodityhitsPOS);
        map.put("hits", userCommodityhitsPOS.size());
        return Result.success(map);

    }

}
