package org.igetwell.system.service.impl;

import org.igetwell.common.enums.HttpStatus;
import org.igetwell.common.sequence.sequence.Sequence;
import org.igetwell.common.uitls.Pagination;
import org.igetwell.common.uitls.ResponseEntity;
import org.igetwell.system.bean.SystemOauthClientDetailsBean;
import org.igetwell.system.dto.SystemOauthClientDetailsPageDto;
import org.igetwell.system.entity.SystemOauthClientDetails;
import org.igetwell.system.mapper.SystemOauthClientDetailsMapper;
import org.igetwell.system.service.ISystemOauthClientDetailService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemOauthClientDetailService implements ISystemOauthClientDetailService {

    @Resource
    private Sequence sequence;
    @Resource
    private SystemOauthClientDetailsMapper systemOauthClientDetailsMapper;

    @Override
    public SystemOauthClientDetails get(String clientId) {
        return systemOauthClientDetailsMapper.get(clientId);
    }

    @Override
    public List<SystemOauthClientDetailsBean> getList(Pagination pagination, SystemOauthClientDetailsPageDto dto) {
        return systemOauthClientDetailsMapper.getList(pagination, dto);
    }

    @Override
    public void deleteById(String clientId) {
        systemOauthClientDetailsMapper.deleteById(clientId);
    }

    @Override
    public ResponseEntity insert(SystemOauthClientDetails systemOauthClientDetails) {
        if (!checkParam(systemOauthClientDetails)){
            return ResponseEntity.error(HttpStatus.BAD_REQUEST, "应用ID不可为空!");
        }
        SystemOauthClientDetails oauthClientDetails = get(systemOauthClientDetails.getClientId());
        if (!StringUtils.isEmpty(oauthClientDetails)){
            return ResponseEntity.error(HttpStatus.BAD_REQUEST, "此应用ID已存在!");
        }
        systemOauthClientDetails.setId(sequence.nextValue());
        int i = systemOauthClientDetailsMapper.insert(systemOauthClientDetails);
        if (i > 0){
            return ResponseEntity.ok();
        }
        return ResponseEntity.error();
    }

    @Override
    public ResponseEntity update(SystemOauthClientDetails systemOauthClientDetails) {
        if (!checkParam(systemOauthClientDetails)){
            return ResponseEntity.error(HttpStatus.BAD_REQUEST, "应用ID不可为空!");
        }
        SystemOauthClientDetails oauthClientDetails = get(systemOauthClientDetails.getClientId());
        if (StringUtils.isEmpty(oauthClientDetails)){
            return ResponseEntity.error(HttpStatus.BAD_REQUEST, "此应用ID不存在!");
        }
        int i = systemOauthClientDetailsMapper.update(systemOauthClientDetails);
        if (i > 0){
            return ResponseEntity.ok();
        }
        return ResponseEntity.error();
    }

    private boolean checkParam(SystemOauthClientDetails oauthClientDetails){
        if (StringUtils.isEmpty(oauthClientDetails) || !StringUtils.hasText(oauthClientDetails.getClientId())){
            return false;
        }
        return true;
    }
}
