package com.PhoneX.Backend.Service;


import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Role;
import com.PhoneX.Backend.globalException.BadRequestException;
import com.PhoneX.Backend.globalException.NotFoundException;
import com.PhoneX.Backend.repository.PermissionRepository;
import com.PhoneX.Backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Transactional
    public String addRole(Role role) {
        if(roleRepository.existsByName(role.getName())){
            throw new BadRequestException(MessageConstants.ROLE_ALREADY_EXISTS);
        }
        roleRepository.save(role);
        return "Role created successfully";
    }


    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.ROLE_NOT_FOUND));
    }


    @Transactional
    public String updateRole(Integer id, Role updatedRole) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.ROLE_NOT_FOUND));

        existingRole.setName(updatedRole.getName());
        roleRepository.save(existingRole);

        return "Role updated successfully.";
    }

    @Transactional
    public String deleteRole(Integer id) {
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException(MessageConstants.ROLE_NOT_FOUND);
        }
        roleRepository.deleteById(id);
        return "Role deleted successfully";
    }
}
