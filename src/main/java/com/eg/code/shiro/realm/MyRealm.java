package com.eg.code.shiro.realm;

import com.eg.code.entity.User;
import com.eg.code.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权--登录之后给用户授权--执行授权的逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findByUserName(userName);

        //授权信息，AuthorizationInfo的子类
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();

        if("管理员".equals(user.getRoleName())){//根据用户的身份进行控制
            roles.add("管理员");
            info.addStringPermission("进入管理员主页");
            info.addStringPermission("根据id查询资源类型实体");
            info.addStringPermission("添加或修改资源类型信息");
            info.addStringPermission("删除资源类型信息");
            info.addStringPermission("分页查询资源信息列表");
            info.addStringPermission("删除资源");
            info.addStringPermission("修改资源");
            info.addStringPermission("审核资源");
            info.addStringPermission("生成所有资源帖子索引");
            info.addStringPermission("分页查询评论信息");
            info.addStringPermission("修改评论状态");
            info.addStringPermission("删除评论");
            info.addStringPermission("分页查询用户信息");
            info.addStringPermission("修改用户VIP状态");
            info.addStringPermission("修改用户状态");
            info.addStringPermission("重置用户密码");
            info.addStringPermission("用户vip等级修改");
            info.addStringPermission("用户积分充值");
            info.addStringPermission("分页查询友情链接");
            info.addStringPermission("根据Id查询友情链接实体");
            info.addStringPermission("添加或修改友情链接");
            info.addStringPermission("批量删除友情链接");
            info.addStringPermission("修改管理员密码");
            info.addStringPermission("安全退出");
            info.addStringPermission("修改热门资源状态");
            info.addStringPermission("修改免费资源状态");

        }
        info.setRoles(roles);
        return info;
    }

    /**
     * 权限认证--登录时权限认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    //认证信息 -- 执行认证逻辑
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal(); //getPrincipal方法的实现在UserNamePasswordToken中
        User user = userService.findByUserName(userName);//根据用户名找到对应的用户对象
        if(user==null){
            return null; //用户名不存在，shiro底层会抛出UnKnowAccountException
        }else{//用户名存在，把查找到的密码传进去，shiro会自动判断密码是否正确     放置的user.getUserName()就是Principal
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"xx");
            return authenticationInfo;
        }
    }
}
