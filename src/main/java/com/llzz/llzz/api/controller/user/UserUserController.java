package com.llzz.llzz.api.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.llzz.llzz.api.model.po.UserPO;
import com.llzz.llzz.api.service.UserUserService;
import com.llzz.llzz.api.utility.BaseController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jgl
 * @since 2019-12-04
 */
@RestController
@RequestMapping("/user")
public class UserUserController extends BaseController {

    @Autowired
    UserUserService userUserService;




    @RequestMapping(value = "/get.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<?> get(@RequestBody UserPO userPO) {
        Date date = new Date();
        UserPO userPO1 = userUserService.getById(userPO.getOpenid());
        String task = userPO1.getTask();
        String task1 = userPO.getTask();

        if(userPO1.getTask().indexOf(userPO.getTask()) == -1){
            userPO1.setTask(userPO1.getTask() + ";" + userPO.getTask());
            userPO1.setTaskpt(date);
            userUserService.updateById(userPO1);
            return Result.success("领取成功");
        }else {

            return Result.fail("已经领取");
        }
    }

    /**
     * 取消任务
     * @param userPO
     * @return
     */
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<?> delete(@RequestBody UserPO userPO) {

        UserPO userPO1 = userUserService.getById(userPO.getOpenid());
        int i = userPO1.getTask().indexOf(userPO.getTask());
        if(i == - 1) {
            return Result.fail("没有此任务");
        }else {
            StringBuilder sb = new StringBuilder(userPO1.getTask());
            sb.delete(i - 1, userPO.getTask().length() + i);
            userPO1.setTask(sb.toString());
            userUserService.updateById(userPO1);
            return Result.success("任务已取消");
        }

    }
}
