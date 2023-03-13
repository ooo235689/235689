package com.pengqianhuai.hospitaldata.util;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleShow {

    @JsonProperty("LAY_CHECKED")
    private boolean LAY_CHECKED;
    private String roledesc;

    private int roleid;

    private String rolename;

}
