package com.PhoneX.Backend.DTO;

import  lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RolePermissionDTO {
    private Integer roleId;
    private Long permissionId;
}
