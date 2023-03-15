package com.hospitaldata.util;

import com.hospitaldata.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

public class MenusUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 对象转换
     * @param list
     * @return
     */
    public List transform(List list) {

        List MenuList = new ArrayList();

        for (int a = 0; a < list.size(); a++) {

            SysMenu sysMenu=(SysMenu)list.get(a);

            SysMenus sysMenus=new SysMenus();

            sysMenus.setId(sysMenu.getId());
            sysMenus.setParentId(sysMenu.getPid());
            sysMenus.setTitle(sysMenu.getTitle());
            sysMenus.setHref(sysMenu.getHref());
            sysMenus.setSpread(sysMenu.getSpread()==0?false:true);
            sysMenus.setTarget(sysMenu.getTarget());
            sysMenus.setIcon(sysMenu.getIcon());
            sysMenus.setCheckArr("0");

            MenuList.add(sysMenus);
        }
        return MenuList;
    }

}
