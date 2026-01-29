package book.store.service.impl;

import book.store.entity.Role;
import book.store.payload.response.RoleRsp;
import book.store.repository.RoleRepository;
import book.store.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Set<RoleRsp> getAllRole() {
        List<Role> roleList = roleRepository.findAll();
        Set<RoleRsp> roleRspList = new HashSet<>();

        for (Role role : roleList) {
            RoleRsp roleRsp = new RoleRsp();
            roleRsp.setId(role.getId());
            roleRsp.setName(role.getName());
            roleRspList.add(roleRsp);
        }

        return roleRspList;
    }
}
