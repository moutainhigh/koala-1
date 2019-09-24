package org.igetwell.system.controller;

import org.igetwell.common.uitls.GsonUtils;
import org.igetwell.common.uitls.Pagination;
import org.igetwell.common.uitls.ResponseEntity;
import org.igetwell.system.dto.SystemUserPageDto;
import org.igetwell.system.entity.SystemUser;
import org.igetwell.system.service.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemUserController {

    @Autowired
    private ISystemUserService iSystemUserService;

    /**
     * 登录(根据租户ID和用户名查询)
     * @param tenant 租户ID
     * @param username 用户名
     * @return
     */
    @PostMapping("/systemUser/loadByUsername/{tenant}/{username}")
    public SystemUser loadByUsername(@PathVariable("tenant") String tenant, @PathVariable("username") String username){
        SystemUser systemUser = iSystemUserService.loadByUsername(tenant, username);
        return systemUser;
    }

    @PostMapping("/systemUser/getList")
    public ResponseEntity getList(@RequestBody SystemUserPageDto dto){
        Pagination<SystemUser> pagination = new Pagination<SystemUser>();
        iSystemUserService.getList(pagination, dto);
        return ResponseEntity.ok(pagination);
    }

    @PostMapping("/systemUser/test")
    public ResponseEntity test(){
        return ResponseEntity.ok("abcd");
    }
}
