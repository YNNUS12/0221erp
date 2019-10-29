package com.sxt.sys.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.ActiverUser;
import com.sxt.sys.vo.PermissionVo;
import com.sxt.sys.vo.RoleVo;

public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired 
	private PermissionService permissionService;

	
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String loginName=token.getPrincipal().toString();
		
		User user=this.userService.queryUserByLoginName(loginName);
		if(null!=user) {
			ActiverUser activerUser=new ActiverUser();
			activerUser.setUser(user);
			if(user.getType()!=SysConstast.USER_TYPE_SUPER) {
				//根据用户ID查询角色
				RoleVo roleVo=new RoleVo();
				roleVo.setAvailable(SysConstast.AVAILABLE_TRUE);
				List<String> roles=this.roleService.queryRoleByUserIdForListString(roleVo, user.getId());
				
				activerUser.setRoles(roles);
				//根据用户ID查询权限
				PermissionVo permissionVo=new PermissionVo();
				permissionVo.setAvailable(SysConstast.AVAILABLE_TRUE);
				permissionVo.setType(SysConstast.PERMISSION_TYPE_PERMISSION);
				List<String> permissions=this.permissionService.queryPermissionByUserIdForListString(permissionVo, user.getId());
				activerUser.setPermissions(permissions);
			}
			ByteSource credentialsSalt=ByteSource.Util.bytes(user.getSalt());
			AuthenticationInfo info=new SimpleAuthenticationInfo(activerUser, user.getPwd(), credentialsSalt, getName());
			return info;
		}else {
			return null;
		}
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ActiverUser activerUser=(ActiverUser) principals.getPrimaryPrincipal();
		List<String> roles = activerUser.getRoles();
		List<String> permissions = activerUser.getPermissions();
		
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		if(activerUser.getUser().getType()==SysConstast.USER_TYPE_SUPER) {
			info.addStringPermission("*:*");
		}else {
			if(null!=roles&&roles.size()>0) {
				info.addRoles(roles);
			}
			if(null!=permissions&&permissions.size()>0) {
				info.addStringPermissions(permissions);
			}
		}
		return info;
	}

}
