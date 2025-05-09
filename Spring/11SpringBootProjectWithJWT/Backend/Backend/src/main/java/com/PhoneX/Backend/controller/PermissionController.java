package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.Service.PermissionService;
import com.PhoneX.Backend.constants.UserPermissions;
import com.PhoneX.Backend.entity.Permission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_PERMISSIONS + "')")
    public ResponseEntity<String> createPermission(@RequestBody Permission permission) {
        return ResponseEntity.ok(permissionService.createPermission(permission));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_ALL_PERMISSIONS + "')")
    public ResponseEntity<Page<Permission>> getAllPermissions(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(permissionService.getAllPermissions(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_PERMISSIONS + "')")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_PERMISSIONS + "')")
    public ResponseEntity<String> updatePermission(@PathVariable Long id, @RequestBody Permission updatedPermission) {
        return ResponseEntity.ok(permissionService.updatePermission(id, updatedPermission));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_PERMISSIONS + "')")
    public ResponseEntity<String> deletePermission(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.deletePermission(id));
    }
}