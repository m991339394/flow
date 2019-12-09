package com.llzz.llzz.api.service;

import com.llzz.llzz.api.model.po.TaskPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jgl
 * @since 2019-12-05
 */
public interface UserTaskService extends IService<TaskPO> {
    public boolean yn(String task);

    public boolean taskDN(String task,int state);

    public boolean taskTime(String task);
}
