package com.PhoneX.Backend.Service;

import com.PhoneX.Backend.DTO.RolePermissionDTO;
import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Permission;
import com.PhoneX.Backend.globalException.BadRequestException;
import com.PhoneX.Backend.globalException.NotFoundException;
import com.PhoneX.Backend.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final RolePermissionService rolePermissionService;

    @Transactional
    public String createPermission(Permission permission) {
        if (permissionRepository.findByName(permission.getName()).isPresent()) {
            throw new BadRequestException("Permission with name " + permission.getName() + " already exists");
        }
        permissionRepository.saveAndFlush(permission);

        Permission savedPermission = permissionRepository.findByName(permission.getName())
                .orElseThrow(() -> new NotFoundException("Permission not found"));

        RolePermissionDTO dtoForAdmin = new RolePermissionDTO(1, savedPermission.getId());
        RolePermissionDTO dtoForOwner = new RolePermissionDTO(3, savedPermission.getId());
        rolePermissionService.addRolePermission(dtoForAdmin);
        rolePermissionService.addRolePermission(dtoForOwner);
        return "Permission created successfully";
    }


    @Transactional(readOnly = true)
    public Page<Permission> getAllPermissions(Pageable pageable) {
        return permissionRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.PERMISSION_NOT_FOUND));
    }

    @Transactional
    public String updatePermission(Long id, Permission updatedPermission) {
        Permission existingPermission = permissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.PERMISSION_NOT_FOUND));

        existingPermission.setName(updatedPermission.getName());
        permissionRepository.save(existingPermission);

        return "Permission updated successfully.";
    }

    @Transactional
    public String deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new NotFoundException(MessageConstants.PERMISSION_NOT_FOUND);
        }

        permissionRepository.deleteById(id);
        return "Permission deleted successfully";
    }
}
