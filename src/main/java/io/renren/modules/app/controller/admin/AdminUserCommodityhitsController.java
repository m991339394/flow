package io.renren.modules.app.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import io.renren.common.Result;
import io.renren.common.utils.BaseController;
import io.renren.common.utils.StringUtils;
import io.renren.modules.app.dao.UserCommodityhitsDao;
import io.renren.modules.app.model.form.GoodsBrowseForm;
import io.renren.modules.app.model.po.UserCommodityhitsPO;
import io.renren.modules.app.service.UserCommodityhitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    UserCommodityhitsDao userCommodityhitsDao;

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

    @RequestMapping(value = "/getLogs.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<?> getLogs(@RequestBody GoodsBrowseForm goodsBrowseForm) {


        int pageNo = goodsBrowseForm.getPageNo();
        int pageSize = goodsBrowseForm.getPageSize();
        PageHelper.startPage(pageNo, pageSize);

        QueryWrapper<UserCommodityhitsPO> queryWrapper = new QueryWrapper();

        if (StringUtils.isNotEmpty(goodsBrowseForm.getStartTime())) {
            queryWrapper.ge("time", goodsBrowseForm.getStartTime());
        }

        if (StringUtils.isNotEmpty(goodsBrowseForm.getEndTime())) {
            queryWrapper.le("time", goodsBrowseForm.getEndTime());
        }
        if (StringUtils.isNotEmpty(goodsBrowseForm.getName())) {
            queryWrapper.like("name", goodsBrowseForm.getName());
        }
        List<UserCommodityhitsPO> userCommodityhitsPOS = userCommodityhitsService.list(queryWrapper);
        List<UserCommodityhitsPO> userCommodityhitsPOS2 = userCommodityhitsPOS.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(UserCommodityhitsPO::getOpenid))), ArrayList::new));
        userCommodityhitsPOS.size();
        Map<String, Object> map = new HashMap<>();
        map.put("DAUs", userCommodityhitsPOS2.size());
//        map.put("userCommodityhitsPOS",userCommodityhitsPOS);
        map.put("hits", userCommodityhitsPOS.size());
        List<UserCommodityhitsPO> query = userCommodityhitsService.query(pageNo, pageSize);
        map.put("totil", query.size());
//        list.add(map);
//        Page<UserCommodityhitsPO> page = new Page<>(pageNo, pageSize,false);
//        IPage<Map<String, Object>> iPage = userCommodityhitsDao.selectMapsPage(page, queryWrapper);
        map.put("page", query);

        List<Object> list = new ArrayList<>();
//        list.add(iPage);
//        map.put("page",list);
//        System.out.println(list.toString());
//        if (pageNo == 0 || pageSize == 0){
//            return Result.success(userCommodityhitsService.list(queryWrapper));
//        }
        list.add(userCommodityhitsPOS);

//        PageInfo pageInfo = new PageInfo(userCommodityhitsPOS, pageSize);

        return Result.success(map);
//        return LayerMsg.success(pageInfo.getTotal(), list);

    }

}
