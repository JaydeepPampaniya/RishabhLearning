package com.PhoneX.Backend.Service;

import com.PhoneX.Backend.DTO.RolePermissionDTO;
import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Permission;
import com.PhoneX.Backend.entity.Role;
import com.PhoneX.Backend.entity.RolePermission;
import com.PhoneX.Backend.globalException.NotFoundException;
import com.PhoneX.Backend.repository.PermissionRepository;
import com.PhoneX.Backend.repository.RolePermissionRepository;
import com.PhoneX.Backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public String addRolePermission(RolePermissionDTO dto) {
        Optional<Role> role = roleRepository.findById(dto.getRoleId());
        Optional<Permission> permission = permissionRepository.findById(dto.getPermissionId());

        if (role.isPresent() && permission.isPresent()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRole(role.get());
            rolePermission.setPermission(permission.get());
            rolePermissionRepository.save(rolePermission);

            return "Permission added for user.";
        } else {
            throw new NotFoundException("Role or Permission not found");
        }
    }

    public List<RolePermissionDTO> getAllRolePermissions() {
        List<RolePermissionDTO> list = new ArrayList<>();
        for (RolePermission permission : rolePermissionRepository.findAll()) {
            RolePermissionDTO dto = new RolePermissionDTO(permission.getRole().getId(), permission.getPermission().getId());
            list.add(dto);
        }
        return list;
    }


    public String deleteRolePermission(Long id) {
        rolePermissionRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Role-Permission mapping not found"));
        rolePermissionRepository.deleteById(id);
        return "permission removed.";
    }

    @Transactional
    public Role addPermissionsToRole(Integer roleId, List<Long> permissionIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException(MessageConstants.ROLE_NOT_FOUND));

        Set<Permission> permissionsToAdd = new HashSet<>(permissionRepository.findAllById(permissionIds));
        if (permissionsToAdd.isEmpty()) {
            throw new NotFoundException(MessageConstants.PERMISSION_NOT_FOUND);
        }

        role.getPermissions().addAll(permissionsToAdd);
        return roleRepository.save(role);
    }


    @Transactional
    public Role removePermissionsFromRole(Integer roleId, List<Long> permissionIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException(MessageConstants.ROLE_NOT_FOUND));

        Set<Permission> permissionsToRemove = new HashSet<>(permissionRepository.findAllById(permissionIds));
        if (permissionsToRemove.isEmpty()) {
            throw new NotFoundException(MessageConstants.PERMISSION_NOT_FOUND);
        }

        role.getPermissions().removeAll(permissionsToRemove);
        return roleRepository.save(role);
    }
}
