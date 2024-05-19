package com.javacorner.admin.service.impl;

import com.javacorner.admin.dao.RoleDao;
import com.javacorner.admin.entity.Role;
import com.javacorner.admin.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleServiceInpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceInpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role createRole(String roleName) {

        return roleDao.save(new Role(roleName));
    }
}
