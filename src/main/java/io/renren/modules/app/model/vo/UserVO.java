package io.renren.modules.app.model.vo;

import io.renren.modules.app.model.po.UserPO;
import lombok.Data;

/**   
 * @ClassName:  UserDTO   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: jgl
 * @date:   2019年10月5日 上午11:29:42   
 */
@Data
public class UserVO extends UserPO {
	
	private Double balance;
	private Long integralBalance;
	private String openId;
	private String sessionKey;
	

}
