package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.DTO.RolePermissionDTO;
import com.PhoneX.Backend.Service.RolePermissionService;
import com.PhoneX.Backend.constants.UserPermissions;
import com.PhoneX.Backend.entity.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role-permissions")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }


    @PostMapping("/addRolePermission")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_ROLES_PERMISSIONS + "')")
    public ResponseEntity<String> addRolePermission(@RequestBody RolePermissionDTO dto) {
        return ResponseEntity.ok(rolePermissionService.addRolePermission(dto));
    }


    @GetMapping("/getAllRolePermissions")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_ALL_ROLES_PERMISSIONS + "')")
    public ResponseEntity<List<RolePermissionDTO>> getAllRolePermissions() {
        return ResponseEntity.ok(rolePermissionService.getAllRolePermissions());
    }


    @DeleteMapping("/{id}/delete-role-permission")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_ROLES_PERMISSIONS + "')")
    public ResponseEntity<String> deleteRolePermission(@PathVariable Long id) {
        return ResponseEntity.ok(rolePermissionService.deleteRolePermission(id));
    }


    @PostMapping("/{roleId}/addAllRolePermissions")
    public ResponseEntity<Role> addPermissionsToRole(
            @PathVariable Integer roleId,
            @RequestBody List<Long> permissionIds) {

        Role updatedRole = rolePermissionService.addPermissionsToRole(roleId, permissionIds);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{roleId}/deleteAllRolePermission")
    public ResponseEntity<Role> removePermissionsFromRole(
            @PathVariable Integer roleId,
            @RequestBody List<Long> permissionIds) {

        Role updatedRole = rolePermissionService.removePermissionsFromRole(roleId, permissionIds);
        return ResponseEntity.ok(updatedRole);
    }
}
