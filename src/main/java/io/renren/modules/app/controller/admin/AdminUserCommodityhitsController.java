package io.renren.modules.app.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.Result;
import io.renren.common.utils.BaseController;
import io.renren.modules.app.model.po.UserCommodityhitsPO;
import io.renren.modules.app.service.UserCommodityhitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @RequestMapping(value = "/one.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
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


}
