package com.llzz.llzz.api.service.impl;

import com.llzz.llzz.api.model.po.TaskPO;
import com.llzz.llzz.api.dao.UserTaskDao;
import com.llzz.llzz.api.service.UserTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jgl
 * @since 2019-12-05
 */
@Service
public class UserTaskServiceImpl extends ServiceImpl<UserTaskDao, TaskPO> implements UserTaskService {

    @Override
    public boolean yn(String task) {
        List<TaskPO> list = super.list();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getTask().equals(task)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean taskDN(String task,int state) {

        if(state != 0 && state != 1){
            System.out.println(state);
            return false;
        }else {
            TaskPO taskPO = super.getById(task);
            taskPO.setTaskstate(state);
            boolean flag = super.updateById(taskPO);
            System.out.println(flag);
            return flag;
        }
    }

    @Override
    public boolean taskTime(String task) {
        Date date1 = new Date();
        Date date2 = super.getById(task).getTasktime();
        TaskPO taskPO = super.getById(task);
        if (date1.getTime() > date2.getTime()){
            taskPO.setTaskstate(0);
            super.updateById(taskPO);
            return true;
        }else {
            return false;
        }
    }
}
