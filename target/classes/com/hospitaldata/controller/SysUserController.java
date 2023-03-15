package com.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospitaldata.entity.SysRoleUser;
import com.hospitaldata.mapper.SysRoleMapper;
import com.hospitaldata.mapper.SysRoleUserMapper;
import com.hospitaldata.entity.SysMenu;
import com.hospitaldata.entity.SysRole;
import com.hospitaldata.entity.SysUser;
import com.hospitaldata.mapper.SysMenuMapper;
import com.hospitaldata.mapper.SysUserMapper;
import com.hospitaldata.service.ISysMenuService;
import com.hospitaldata.util.MenusUtil;
import com.hospitaldata.util.SysRoleShow;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/sel")
public class SysUserController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Resource
    private ISysMenuService sysMenuService;

    @Autowired
    private RedisTemplate redisTemplate;

    MenusUtil menusUtil = new MenusUtil();

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;


    /**
     * 进入登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String Login() {
        return "index";
    }

    /**
     *进入登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "view/login";
    }

    /**
     * 用户登录
     */
    @RequestMapping("/loginJudge")
    public String login(HttpSession session, SysUser sysUser, Model model) {

        try {
            //获取当前的用户
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(sysUser.getLoginname(), sysUser.getPwd());

            subject.login(usernamePasswordToken);

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("loginname", sysUser.getLoginname());
            queryWrapper.eq("pwd", sysUser.getPwd());

            SysUser sysUser1 = sysUserMapper.selectOne(queryWrapper);

            session.setAttribute("sysUser", sysUser1);

            model.addAttribute("loginname", sysUser1.getLoginname());
            model.addAttribute("id", sysUser1.getUserid());

            return "view/index";

        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在");
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
        }

        return "view/login";
    }

//    /**
//     * 用户登录
//     */
//    @RequestMapping("/loginJudge")
//    public String loginJudge(HttpSession session, SysUser sysUser, Model model) {
//        Map map = new HashMap<>();
//
//        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
//
//        wrapper.eq("loginname", sysUser.getLoginname());
//
//        SysUser sysUsers = sysUserMapper.selectOne(wrapper);
//
//        if (sysUsers == null) {
//            model.addAttribute("msg", "用户名不存在");
//        } else {
//            wrapper.eq("pwd", sysUser.getPwd());
//            sysUsers = sysUserMapper.selectOne(wrapper);
//
//            if (sysUsers == null) {
//                model.addAttribute("msg", "用户名或密码错误");
//            } else {
//                model.addAttribute("msg", "登录成功");
//                //设置 redisTemplate 对象 key 的序列化方式
//                redisTemplate.setKeySerializer(new StringRedisSerializer());
//
//                redisTemplate.opsForValue().set("sys:users", sysUsers, 12, TimeUnit.HOURS);
//                model.addAttribute("loginname", sysUser.getLoginname());
//                model.addAttribute("id", sysUsers.getUseri1d());
//                session.setAttribute("sysUser", sysUsers);
//                return "view/index";
//            }
//        }
//
//        return "view/login";
//    }

    /**
     * 进入个人资料页面
     *
     * @return
     */
    @RequestMapping("/toUpdateLogin")
    public String toUpdateLogin(Integer userid, Model model) {


        SysUser sysUsers = sysUserMapper.selectById(userid);
        model.addAttribute("usersLogin", sysUsers);

        return "view/user/updateLogin";
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("/exit")
    public String userExit() {
        redisTemplate.delete("sys:users");
        return "view/login";
    }

    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping("/main")
    public String main() {

        return "view/main/main";
    }


    /**
     * 修改当前登录用户的信息
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("/editLogin")
    @ResponseBody
    public String updateSysUser(SysUser sysUser) {

        System.err.println(sysUser.getPwd());

        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();

        wrapper.eq("userId", sysUser.getUserid());

        int row = sysUserMapper.update(sysUser, wrapper);

        if (row > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    /**
     * 个人资料
     * 修改密码
     * @param loginname
     * @param pwd0
     * @param pwd1
     * @return
     */
    @RequestMapping("/editPwd")
    @ResponseBody
    public int editPwd(String loginname, String pwd0, String pwd1) {

        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();

        wrapper.eq("loginname", loginname);

        SysUser sysUser = sysUserMapper.selectOne(wrapper);

        if (sysUser.getPwd().equals(pwd0)) {
            SysUser sysUsers = new SysUser();
            sysUsers.setPwd(pwd1);
            int update = sysUserMapper.update(sysUsers, wrapper);

            return 1;
        }

        return 0;
    }


    @RequestMapping("/toMenuLeft")
    public String toMenuLeft() {
        return "view/menu/menuLeft";
    }

    /**
     * 菜单管理模糊查询
     *
     * @param title
     * @param id
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryMenuAllList")
    @ResponseBody
    public Map queryMenuAllList(String title, Integer id, int page, int limit) {

        Map map = new HashMap();
        System.err.println(title);
        QueryWrapper queryWrapper = new QueryWrapper();

        if (title != null) {
            queryWrapper.like("title", title);
        }
        if (id != null) {
            queryWrapper.eq("id", id);
        }

        Page<SysMenu> pages = new Page(page, limit);
        IPage<SysMenu> iPage = sysMenuMapper.selectPage(pages, queryWrapper);
        List<SysMenu> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);

        return map;
    }

    /**
     * 菜单管理
     * 更新菜单刷新树状结构
     *
     * @param spread
     * @return
     */
    @RequestMapping("/loadMenuMangerLeftTreeJson")
    @ResponseBody
    public Map loadMenuMangerLeftTreeJsons(Integer spread, HttpSession session) {
        Map map = new HashMap();

        SysUser sysUser = (SysUser) session.getAttribute("sysUser");


        List<SysMenu> list = sysMenuService.selectPid(-1, sysUser.getUserid());

        List list1 = menusUtil.transform(list);

        System.err.println();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", null);
        map.put("data", list1);
        return map;
    }

    /**
     * 菜单管理
     * 添加目录
     *
     * @return
     */
    @RequestMapping("/addMenu")
    @ResponseBody
    public String addMenu(SysMenu sysMenu) {

        int row = sysMenuMapper.insert(sysMenu);

        if (row > 0) {
            return "添加成功";
        }
        return "添加失败";
    }

    /**
     * 菜单管理
     * 修改目录
     *
     * @return
     */
    @RequestMapping("/updateMenu")
    @ResponseBody
    public String updateMenu(SysMenu sysMenu) {

        System.err.println(sysMenu);

        int row = sysMenuMapper.updateById(sysMenu);

        if (row > 0) {
            return "修改成功";
        }

        return "修改失败";
    }

    /**
     * 菜单管理
     * 判断删除的目录是否有子节点
     *
     * @param id
     * @return
     */
    @RequestMapping("/checkMenuHasChildren")
    @ResponseBody
    public int checkMenuHasChildren(Integer id) {

        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("pid", id);

        List list = sysMenuMapper.selectList(queryWrapper);

        if (list.size() > 0) {
            return 0;
        }

        return 1;
    }

    /**
     * 删除目录
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public boolean deleteMenu(Integer id) {

        int row = sysMenuMapper.deleteById(id);
        if (row > 0) {
            return true;
        }

        return false;
    }

    /**
     * 用户显示
     *
     * @param realname
     * @param loginname
     * @param address
     * @param phone
     * @param identity
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("selectAllUser")
    @ResponseBody
    public Map selectAllUser(String realname, String loginname, String address, String phone, String identity, Integer page, Integer limit) {

        Map map = new HashMap();

        QueryWrapper queryWrapper = new QueryWrapper();

        if (realname != null) {
            queryWrapper.like("realname", realname);
        }
        if (loginname != null) {
            queryWrapper.like("realname", realname);
        }
        if (address != null) {
            queryWrapper.like("address", address);
        }
        if (phone != null) {
            queryWrapper.like("phone", phone);
        }
        if (identity != null) {
            queryWrapper.like("identity", identity);
        }

        Page<SysUser> pages = new Page(page, limit);
        IPage<SysUser> iPage = sysUserMapper.selectPage(pages, queryWrapper);
        List<SysUser> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);

        return map;
    }

    /**
     * 用户管理
     * 修改用户
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("updateUser")
    @ResponseBody
    public String updateUser(SysUser sysUser) {

        int row = sysUserMapper.updateById(sysUser);

        if (row > 0) {
            return "修改成功";
        }

        return "修改失败";
    }

    /**
     * 重置密码
     *
     * @param userid
     * @return
     */
    @RequestMapping("/resetUserPwd")
    @ResponseBody
    public String resetUserPwd(Integer userid) {

        SysUser sysUser = new SysUser();
        sysUser.setUserid(userid);
        sysUser.setPwd("123456");

        int row = sysUserMapper.updateById(sysUser);

        if (row > 0) {
            return "重置成功";
        }

        return "重置失败";
    }

    /**
     * 用户管理
     * 添加用户信息
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(SysUser sysUser) {
        sysUser.setPwd("123456");
        int row = sysUserMapper.insert(sysUser);

        if (row > 0) {
            return "添加成功";
        }

        return "添加失败";
    }

    /**
     * 用户管理
     * 删除用户
     *
     * @param userid
     * @return
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(Integer userid) {

        int row = sysUserMapper.deleteById(userid);

        if (row > 0) {
            return "删除成功";
        }

        return "删除失败";
    }

    /**
     * 显示分配角色
     *
     * @param userid
     * @return
     */
    @RequestMapping("/initUserRole")
    @ResponseBody
    public Map initUserRole(Integer userid) {

        Map map = new HashMap();

        //所有用户
        List ListsysRole = sysRoleMapper.selectList(null);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", userid);

        //用户绑定的角色
        List listSysRoleUser = sysRoleUserMapper.selectList(queryWrapper);

        //用于判断是否选中的
        Map map1 = new HashMap();
        for (int a = 0; a < listSysRoleUser.size(); a++) {
            SysRoleUser sysRoleUser = (SysRoleUser) listSysRoleUser.get(a);
            map1.put(sysRoleUser.getRid(), sysRoleUser.getRid());
        }
        List sysRoleUser = new ArrayList();
        //配置传到页面的数据
        for (int a = 0; a < ListsysRole.size(); a++) {

            SysRole sysRole = (SysRole) ListsysRole.get(a);

            SysRoleShow sysRoleShow = new SysRoleShow();

            //选中赋值
            if (map1.containsValue(sysRole.getRoleid())) {
                sysRoleShow.setLAY_CHECKED(true);
            } else {
                sysRoleShow.setLAY_CHECKED(false);
            }
            sysRoleShow.setRoledesc(sysRole.getRoledesc());
            sysRoleShow.setRoleid(sysRole.getRoleid());
            sysRoleShow.setRolename(sysRole.getRolename());

            sysRoleUser.add(sysRoleShow);

        }
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", sysRoleUser.size());
        map.put("data", sysRoleUser);
        return map;
    }

    @RequestMapping("/saveUserRole")
    @ResponseBody
    public String saveUserRole(Integer userid, String ids) {
        List list = new ArrayList();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", userid);
        //删除原先用户绑定的角色
        int row = sysRoleUserMapper.delete(queryWrapper);
        if (ids == null) {
            return "分配成功";
        }
        list = Arrays.asList(ids.split(","));
        System.err.println(list);

        for (int a = 0; a < list.size(); a++) {
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRid(Integer.valueOf((String) list.get(a)));
            sysRoleUser.setUid(userid);
            sysRoleUserMapper.insert(sysRoleUser);
        }

        return "分配成功";

    }
}
