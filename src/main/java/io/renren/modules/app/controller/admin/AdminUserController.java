package io.renren.modules.app.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.renren.common.LayerMsg;
import io.renren.common.utils.BaseController;
import io.renren.common.utils.StringUtils;
import io.renren.modules.app.model.form.UserForm;
import io.renren.modules.app.model.po.UserPO;
import io.renren.modules.app.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jgl
 * @since 2019-12-20
 */
@RestController
@RequestMapping("/api/admin/user")
public class AdminUserController extends BaseController {
    @Autowired
    AdminUserService userService;

    /**
     *
     * @Title: getHCardOrder
     * @Description: 查询所有用户
     * @param: @param request
     * @param: @param hCardOrderPO
     * @param: @return
     * @return: Result<?>
     * @date: 2019年10月4日:下午4:08:02
     * @throws
     */
    @RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public LayerMsg list(@RequestBody UserForm userForm){
        try {

            int pageNo=userForm.getPageNo();
            int pageSize=userForm.getPageSize();
            PageHelper.startPage(pageNo, pageSize);
            QueryWrapper<UserPO> queryWrapper=new QueryWrapper();
            if(StringUtils.isNotEmpty(userForm.getNickName())){
                queryWrapper.like("nick_name" ,userForm.getNickName());
            }

            if(StringUtils.isNotEmpty(userForm.getMobile())){
                queryWrapper.like("mobile" ,userForm.getMobile());
            }

            if(StringUtils.isNotEmpty(userForm.getStartTime())){
                queryWrapper.ge("createTime" ,userForm.getStartTime());
            }

            if(StringUtils.isNotEmpty(userForm.getEndTime())){
                queryWrapper.le("createTime" ,userForm.getEndTime());
            }


            List<UserPO> list=userService.list(queryWrapper);
            PageInfo pageInfo = new PageInfo(list, pageSize);
            return LayerMsg.success(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return LayerMsg.fail();
        }
    }

    /**
     *
     * @Title: getHCardOrder
     * @Description: 查询所有用户
     * @param: @param request
     * @param: @param hCardOrderPO
     * @param: @return
     * @return: Result<?>
     * @date: 2019年10月4日:下午4:08:02
     * @throws
     */
    @RequestMapping(value = "/loginLog.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public LayerMsg loginLog(@RequestBody UserForm userForm){
        try {

            int pageNo=userForm.getPageNo();
            int pageSize=userForm.getPageSize();
            PageHelper.startPage(pageNo, pageSize);
            QueryWrapper<UserPO> queryWrapper=new QueryWrapper();
            if(StringUtils.isNotEmpty(userForm.getNickName())){
                queryWrapper.like("nick_name" ,userForm.getNickName());
            }

            if(StringUtils.isNotEmpty(userForm.getMobile())){
                queryWrapper.like("mobile" ,userForm.getMobile());
            }

            if(StringUtils.isNotEmpty(userForm.getStartTime())){
                queryWrapper.ge("endLoginTime" ,userForm.getStartTime());
            }

            if(StringUtils.isNotEmpty(userForm.getEndTime())){
                queryWrapper.le("endLoginTime" ,userForm.getEndTime());
            }


            List<UserPO> list=userService.list(queryWrapper);
            PageInfo pageInfo = new PageInfo(list, pageSize);
            return LayerMsg.success(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return LayerMsg.fail();
        }
    }
}
