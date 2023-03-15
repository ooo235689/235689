package com.hospitaldata.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer parentId;

    private String title;

    private String href;

    /**
     * 0不展开1展开
     */
    private Boolean spread;

    private String target;

    private String icon;

    private String checkArr;

    private List<SysMenus> children = new ArrayList<>();

    public List<SysMenus> getChildren(List list) {
        return children;
    }

    public void setChildren(List<SysMenus> children) {
        this.children = children;
    }
}
