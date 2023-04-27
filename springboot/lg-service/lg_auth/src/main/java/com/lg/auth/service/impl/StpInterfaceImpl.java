package com.lg.auth.service.impl;
import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * @author liuga
 * @since 2023-04-14 17:13
 * Description:自定义权限验证接口扩展
 * loginId：账号id，即你在调用 StpUtil.login(id) 时写入的标识值。
 * loginType：账号体系标识，此处可以暂时忽略，在 [ 多账户认证 ] 章节下会对这个概念做详细的解释。
 * <a href="https://sa-token.cc/doc.html#/use/jur-auth?id=%e8%ae%be%e8%ae%a1%e6%80%9d%e8%b7%af">...</a>
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        list.add("101");
        list.add("user.add");
        list.add("user.update");
        list.add("user.get");
        // list.add("user.delete");
        list.add("art.*");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }

}
