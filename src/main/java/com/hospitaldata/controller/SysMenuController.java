package com.hospitaldata.controller;


import com.hospitaldata.entity.SysMenu;
import com.hospitaldata.entity.SysUser;
import com.hospitaldata.mapper.SysMenuMapper;
import com.hospitaldata.service.ISysMenuService;
import com.hospitaldata.util.MenusUtil;
import com.hospitaldata.util.SysMenus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping
public class SysMenuController {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Resource
    private ISysMenuService sysMenuService;

    MenusUtil menusUtil = new MenusUtil();

    /**
     * 目录
     *
     * @return
     */
    @RequestMapping("/toTreeLoad")
    @ResponseBody
    public List toTreeLoad(HttpSession session) {

        SysUser sysUser= (SysUser) session.getAttribute("sysUser");

        List<SysMenu> list = sysMenuService.selectPid(1,sysUser.getUserid());

        List list1 = menusUtil.transform(list);

        System.err.println("list1====="+list1.size());

        for (int a = 0; a < list1.size(); a++) {
            SysMenus s = (SysMenus) list1.get(a);
//
            List lists = sysMenuService.selectPid(s.getId(),sysUser.getUserid());

            List lsit2 = menusUtil.transform(lists);

            ((SysMenus) list1.get(a)).setChildren(lsit2);

        }
        return list1;
    }

    @RequestMapping("/toMenuManager")
    public String toMenuManager() {

        return "view/menu/menuManager";
    }

    @RequestMapping("/toMenuRight")
    public String menuRight() {

        return "view/menu/menuRight";
    }

    @RequestMapping("/toMenuLeft")
    public String toMenuLeft() {
        return "view/menu/menuLeft";
    }

    @RequestMapping("toLoadAllRole")
    public String roleManager() {
        return "view/role/roleManager";
    }

    /**
     * 进入用户管理页面
     *
     * @return
     */
    @RequestMapping("toLoadAllUser")
    public String toLoadAllUser() {
        return "view/user/userManager";
    }

    /**
     * 进入图标管理
     * @return
     */
    @RequestMapping("icon")
    public  String icon(){
        return "view/center/icon";
    }

    /**
     * 进入数据源监控
     * @return
     */
    @RequestMapping("/druid")
    public String druid(){
        return "view/center/dataOrigin";
    }

    /**
     * 进入科室中心2
     * @return
     */
    @RequestMapping("/toDepartments")
    public String toDepartments(){

        return "view/center/departments";
    }

    /**
     * 进入医生列表
     * @return
     */
    @RequestMapping("/toDoctor")
    public String toDoctor(){
        return "view/center/doctor";
    }

    /**
     * 进入药品场地
     * @return
     */
    @RequestMapping("/toArea")
    public String toArea(){
        return "view/center/area";
    }

    /**
     * 进入项目大类
     * @return
     */
    @RequestMapping("/toProjectTypeManage")
    public String toProjectTypeManage(){
        return "view/center/projectTypeManage";
    }

    /**
     * 进入挂号类型
     */
    @RequestMapping("/toRegisteredType")
    public String toRegisteredType(){
        return "view/center/registeredType";
    }

    /**
     * 进入仓库
     */
    @RequestMapping("/toWarehuose")
    public String toWarehuose(){
        return "view/center/warehuose";
    }

    /**
     * 进入经办人
     */
    @RequestMapping("/toSkull")
    public String toSkull(){
        return "view/center/skull";
    }

    /**
     * 进入计量单位
     */
    @RequestMapping("/toUnit")
    public String toUnit(){
        return "view/center/unit";
    }

    /**
     * 进入供货商
     */
    @RequestMapping("/toSupply")
    public String toSupply(){
        return "view/center/supply";
    }

    /**
     * 进入药品分类
     */
    @RequestMapping("/toType")
    public String toType(){
        return "view/center/type";
    }

    /**
     * 进入药品字典
     */
    @RequestMapping("/toDrugdictionary")
    public String toDrugdictionary(){
        return "view/center/drugdictionary";
    }


    /**
     * 进入排班
     * @return
     */
    @RequestMapping("/toPaiban")
    public String toPaiban(){
        return "view/center/paiban";
    }

    /**
     * 进入门诊月度统计
     * @return
     */
    @RequestMapping("/toReportFinance")
    public String toReportFinance(){
        return "view/finance/reportManage";
    }

    /**
     * 进入住院月度统计
     * @return
     */
    @RequestMapping("/toZhuYaunManage")
    public String toZhuYaunManage(){
        return "view/finance/zhuYuanManage";
    }
    /**
     * 进入门诊年度统计
     * @return
     */
    @RequestMapping("/toBingYear")
    public String toBingYear(){
        return "view/finance/reportBing";
    }
    /**
     * 进入住院年度统计
     * @return
     */
    @RequestMapping("/toBing2")
    public String toBing2(){
        return "view/finance/zhuYuanBing";
    }
    /**
     * 进入医院统计对比
     * @return
     */
    @RequestMapping("/toDoctorDuibi")
    public String toDoctorDuibi(){
        return "view/finance/doctorDuibi";
    }
    /**
     * 进入门诊当天统计
     * @return
     */
    @RequestMapping("/toCurrent")
    public String toCurrent(){
        return "view/finance/current";
    }
}
