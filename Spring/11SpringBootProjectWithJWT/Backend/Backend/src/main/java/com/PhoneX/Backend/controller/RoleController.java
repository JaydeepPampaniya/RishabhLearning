package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.Service.RoleService;
import com.PhoneX.Backend.constants.UserPermissions;
import com.PhoneX.Backend.entity.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_ROLES+ "')")
    public ResponseEntity<String> addRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.addRole(role));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_ROLES + "')")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_ROLES + "')")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_ROLES + "')")
    public ResponseEntity<String> updateRole(@PathVariable Integer id, @RequestBody Role updatedRole) {
        return ResponseEntity.ok(roleService.updateRole(id, updatedRole));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_ROLES + "')")
    public ResponseEntity<String> deleteRole(@PathVariable Integer id) {
        return ResponseEntity.ok(roleService.deleteRole(id));
    }
}