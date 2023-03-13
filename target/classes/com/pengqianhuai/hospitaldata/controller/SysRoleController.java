package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.SysMenu;
import com.pengqianhuai.hospitaldata.entity.SysRole;
import com.pengqianhuai.hospitaldata.entity.SysRoleMenu;
import com.pengqianhuai.hospitaldata.mapper.SysMenuMapper;
import com.pengqianhuai.hospitaldata.mapper.SysRoleMapper;
import com.pengqianhuai.hospitaldata.mapper.SysRoleMenuMapper;
import com.pengqianhuai.hospitaldata.util.MenusUtil;
import com.pengqianhuai.hospitaldata.util.SysMenus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    MenusUtil menusUtil = new MenusUtil();

    @RequestMapping("/loadAllRole")
    @ResponseBody
    public Map loadAllRole(String rolename, String roledesc, String available, int page, int limit) {

        Map map = new HashMap<>();

        QueryWrapper queryWrapper = new QueryWrapper();

        if (roledesc != null) {
            queryWrapper.like("rolename", rolename);
        }
        if (roledesc != null) {
            queryWrapper.like("roledesc", roledesc);
        }
        if (available != null) {
            queryWrapper.like("available", available);
        }

        Page<SysRole> pages = new Page(page, limit);
        IPage<SysRole> iPage = sysRoleMapper.selectPage(pages, queryWrapper);
        List<SysRole> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);

        return map;
    }

    /**
     * 权限信息选中和不选中
     *
     * @return
     */
    @RequestMapping("/initRoleMenuTreeJson")
    @ResponseBody
    public Map initRoleMenuTreeJson(Integer roleid) {

        Map map = new HashMap();

        List<SysMenu> list = sysMenuMapper.selectList(null);

        List MenuList = new ArrayList();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid", roleid);

        List toMenuRight = sysRoleMenuMapper.selectList(queryWrapper);

        Map maps = new HashMap();
        for (int a = 0; a < toMenuRight.size(); a++) {
            SysRoleMenu sysRoleMenu = (SysRoleMenu) toMenuRight.get(a);
            maps.put(sysRoleMenu.getMid(), sysRoleMenu.getMid());
        }
        for (int a = 0; a < list.size(); a++) {

            SysMenu sysMenu = (SysMenu) list.get(a);

            SysMenus sysMenus = new SysMenus();

            sysMenus.setId(sysMenu.getId());
            sysMenus.setParentId(sysMenu.getPid());
            sysMenus.setTitle(sysMenu.getTitle());
            sysMenus.setHref(sysMenu.getHref());
            sysMenus.setSpread(sysMenu.getSpread() == 0 ? false : true);
            sysMenus.setTarget(sysMenu.getTarget());
            sysMenus.setIcon(sysMenu.getIcon());

            if (maps.containsValue(sysMenu.getId())) {
                sysMenus.setCheckArr("1");
            } else {
                sysMenus.setCheckArr("0");
            }
            MenuList.add(sysMenus);
        }
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", null);
        map.put("data", MenuList);

        return map;
    }

    /**
     * 修改角色信息
     *
     * @param sysRole
     * @return
     */
    @RequestMapping("/updateRole")
    @ResponseBody
    public String updateRole(SysRole sysRole) {

        int row = sysRoleMapper.updateById(sysRole);

        if (row > 0) {
            return "修改成功";
        }

        return "修改失败";
    }

    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    @RequestMapping("/insertRole")
    @ResponseBody
    public String insertRole(SysRole sysRole) {

        int row = sysRoleMapper.insert(sysRole);

        if (row > 0) {
            return "添加成功";
        }

        return "添加成功";
    }

    /**
     * 修改分配菜单
     * @param roleid
     * @param ids
     * @return
     */
    @RequestMapping("/saveRoleMenu")
    @ResponseBody
    public String saveRoleMenu(Integer roleid, String ids) {

        List list = Arrays.asList(ids.split(","));

        System.err.println(list);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid", roleid);
        sysRoleMenuMapper.delete(queryWrapper);

        if (list.size()!=0) {
            for (int a = 0; a < list.size(); a++) {

                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRid(roleid);
                sysRoleMenu.setMid(Integer.valueOf((String) list.get(a)));
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }

        return "修改成功";
    }

    /**
     * 删除角色
     * @param roleid
     * @return
     */
    @RequestMapping("/deleteRole")
    @ResponseBody
    public String deleteRole(Integer roleid){

        int row=sysRoleMapper.deleteById(roleid);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("rid",roleid);

        //删除角色权限
        int rows=sysRoleMenuMapper.delete(queryWrapper);
        if(row>0){
            return "删除成功";
        }

        return "删除失败";
    }
}
