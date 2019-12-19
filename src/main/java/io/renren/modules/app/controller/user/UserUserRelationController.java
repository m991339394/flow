package io.renren.modules.app.controller.user;


import io.renren.common.Result;
import io.renren.common.utils.BaseController;
import io.renren.modules.app.model.po.UserUserRelationPO;
import io.renren.modules.app.service.UserUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jgl
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/api/user/relation")
public class UserUserRelationController extends BaseController {
    @Autowired
    UserUserRelationService relationService;

    /**
     * @Author jgl
     * @Description 保存任务
     * @Date 18:05 2019/12/12
     * @Param [request, relationPO]
     * @return io.renren.common.Result<?>
     **/
    @RequestMapping(value = "/add.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result<?> getAppQRCode(HttpServletRequest request , UserUserRelationPO relationPO) throws Exception {

       return relationService.insertRelation(request, relationPO);
    }


}
