package com.llzz.llzz.api.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llzz.llzz.api.model.po.TaskPO;
import com.llzz.llzz.api.service.UserTaskService;
import com.llzz.llzz.api.service.UserUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.llzz.llzz.api.utility.BaseController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jgl
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/task")
public class UserTaskController extends BaseController {

    @Autowired
    UserTaskService userTaskService;

    /**
     * 任务列表
     *
     * @param task
     * @return
     */
    @RequestMapping(value = "/getTask.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> getTask(@RequestParam(value = "task") String task) {
//        TaskPO taskPO = userTaskService.getById(task);
//        return Result.success(taskPO);

        Map<String, Object> map = new HashMap<String, Object>();
        List<TaskPO> taskPOS = userTaskService.list();
        for (TaskPO taskPO : taskPOS) {
            userTaskService.taskTime(taskPO.getTask());
        }
        map.put("date", taskPOS);
        return Result.success(map);

    }


    /**
     * 新增任务
     * @param taskPO
     * @return
     */
    @RequestMapping(value = "/addTask.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<?> getTask(@RequestBody TaskPO taskPO) {
        if (userTaskService.yn(taskPO.getTask()) == true) {
            System.out.println(userTaskService.yn(taskPO.getTask()));
            return Result.fail("已有任务，请勿重复添加！！");
        } else {
            if (taskPO.getTaskprice() == 0) {
                taskPO.setTaskprice(taskPO.getTaskmoney() / taskPO.getTasknumber());
            } else if (taskPO.getTasknumber() == 0) {
                taskPO.setTasknumber((int)(taskPO.getTaskmoney() / taskPO.getTaskprice()));
                System.out.println(taskPO.getTasknumber());
            }else if (taskPO.getTaskmoney() == 0){
                taskPO.setTaskmoney(taskPO.getTaskprice() * taskPO.getTasknumber());
            } else if (taskPO.getTasknumber() !=0 && taskPO.getTaskprice() != 0){
                if (taskPO.getTasknumber() != (int) (taskPO.getTaskmoney()/taskPO.getTaskprice())){
                    return Result.fail("添加任务失败，金额数量不正确");
                }
                
            }
            System.out.println(taskPO.getTasknumber());
            boolean flag = userTaskService.save(taskPO);
            return flag ? Result.success() : Result.fail("新增失败");
        }

    }

    /**
     * 修改任务
     * @param taskPO
     * @return
     */
    @RequestMapping(value = "/updateTask.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<?> updateTask(@RequestBody TaskPO taskPO) {
        if (userTaskService.yn(taskPO.getTask()) == false) {
            System.out.println(userTaskService.yn(taskPO.getTask()));
            return Result.fail("没有此任务，请添加！！");
        } else {
            boolean flag = userTaskService.updateById(taskPO);
            return flag ? Result.success() : Result.fail("修改失败");

        }

    }

    /**
     * 删除任务
     *
     * @param task1
     * @return
     */
    @RequestMapping(value = "/deleteTask.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> deleteTask(@RequestParam(value = "task") String task) {
        if (userTaskService.yn(task) == false) {
            return Result.fail("没有此任务，不需要删除");
        } else {
            boolean flag = userTaskService.removeById(task);
            return flag ? Result.success() : Result.fail("删除失败");

        }

    }

    /**
     * 更改任务状态
     *
     * @param task
     * @param state
     * @return
     */
    @RequestMapping(value = "/updateTaskState.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> updateTaskState(@RequestParam(value = "task") String task,
                                     @RequestParam(value = "state") int state) {
        if (userTaskService.yn(task) == false) {
            return Result.fail("没有此任务");
        } else {
            boolean flag = userTaskService.taskDN(task, state);
            return flag ? Result.success() : Result.fail("更改失败或状态错误");
        }
    }

//    /**
//     * 根据设定时间下架
//     *
//     * @param task
//     * @return
//     */
//    @RequestMapping(value = "/cheng", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public Result<?> cheng(@RequestParam(value = "task") String task) {
//        if (userTaskService.yn(task) == false) {
//            return Result.fail("没有此任务");
//        } else {
//            boolean flag = userTaskService.taskTime(task);
//            return flag ? Result.success("任务已下架") : Result.fail("任务时间未到");
//        }
//    }
}
