package io.renren.modules.app.controller.user;


import io.renren.common.Result;
import io.renren.common.utils.AesCbcUtil;
import io.renren.common.utils.BaseController;
import io.renren.modules.app.model.po.UserPO;
import io.renren.modules.app.model.vo.UserVO;
import io.renren.modules.app.service.UserUserService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jgl
 * @since 2019-10-05
 */
@RestController
@RequestMapping("/api/user/user")
public class UserUserController extends BaseController {
	@Autowired
	UserUserService userService;

	 /**
     * 
     * @Title: findByOpenId   
     * @Description: 通过openid查找用户   
     * @param: @param openid
     * @param: @return
     * @param: @throws Exception      
     * @return: Result<?>      
     * @date: 2019年10月5日:下午4:27:55
     * @throws
     */
    @RequestMapping(value = "/findByOpenId.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> findByOpenId(@RequestParam(value = "openid") String openid) throws Exception {
    	UserVO user = userService.findByOpenId(openid);
    	return Result.success(user);
    }

	/**
	 * @Author jgl
	 * @Description
	 * @Date 19:22 2019/12/11
	 * @Param [session_key, encryptedData, vi, userId]
	 * @return io.renren.common.Result<?>
	 **/
    @RequestMapping(value = "/updateUserInfo.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Result<?> updateUserInfo(@RequestParam(value = "session_key") String session_key
									,@RequestParam(value = "encryptedData") String encryptedData
									,@RequestParam(value = "vi") String vi
									,@RequestParam(value = "userId") Long userId) throws Exception {

		Map map = new HashMap();
		String decrypt = AesCbcUtil.decrypt(encryptedData, session_key, vi, "UTF-8");
		if (StringUtils.isBlank(decrypt)) {
			map.put("msg", "获取用户信息为空");
		}
		JSONObject jsonObject = new org.json.JSONObject(decrypt);
		String nickName = jsonObject.get("nickName").toString();
		String avatarUrl=jsonObject.get("avatarUrl").toString();
//		String phoneNumber=jsonObject.get("phoneNumber").toString();

		UserPO userPO=new UserPO();
		userPO.setId(userId);
		userPO.setNickName(nickName);
		userPO.setFaceIcon(avatarUrl);
//		userPO.setMobile(phoneNumber);


		boolean flag=userService.updateById(userPO);
    	return flag?Result.success():Result.fail();
    }
}
