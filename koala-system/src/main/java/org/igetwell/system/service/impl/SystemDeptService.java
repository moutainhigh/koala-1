package org.igetwell.system.service.impl;

import org.igetwell.common.uitls.ResponseEntity;
import org.igetwell.system.entity.SystemDept;
import org.igetwell.system.mapper.SystemDeptMapper;
import org.igetwell.system.service.ISystemDeptService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemDeptService implements ISystemDeptService {

    @Resource
    private SystemDeptMapper systemDeptMapper;

    @Override
    public List<Long> getDeptAncestors(Long deptId) {
        return systemDeptMapper.getDeptAncestors(deptId);
    }

    @Override
    public List<Long> getAncestors(Long deptId) {
        return systemDeptMapper.getAncestors(deptId);
    }

    @Override
    public ResponseEntity deleteById(Long id) {
        int i = systemDeptMapper.deleteById(id);
        if (i > 0){
            return ResponseEntity.ok();
        }
        return ResponseEntity.error();
    }

    @Override
    public ResponseEntity insert(SystemDept systemDept) {
        int i = systemDeptMapper.insert(systemDept);
        if (i > 0){
            return ResponseEntity.ok();
        }
        return ResponseEntity.error();
    }

    @Override
    public ResponseEntity update(SystemDept systemDept) {
        int i = systemDeptMapper.update(systemDept);
        if (i > 0){
            return ResponseEntity.ok();
        }
        return ResponseEntity.error();
    }
}
