package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.constants.UserPermissions;
import com.PhoneX.Backend.entity.DeviceType;
import com.PhoneX.Backend.Service.DeviceNameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deviceType")
public class DeviceTypeController {

    private final DeviceNameService deviceNameService;

    public DeviceTypeController(DeviceNameService deviceNameService) {
        this.deviceNameService = deviceNameService;
    }

    @PostMapping("/addDeviceType")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_DEVICE_TYPE + "')")
    public ResponseEntity<String> addDeviceType(@RequestBody DeviceType cat) {
        return ResponseEntity.accepted().body(deviceNameService.addDeviceType(cat.getName()));
    }

    @GetMapping("/getDeviceTypes")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_DEVICE_TYPE + "')")
    public ResponseEntity<Page<DeviceType>> getDeviceType(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(deviceNameService.getDeviceType(pageable));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_DEVICE_TYPE + "')")
    public ResponseEntity<String> getDeviceType(@PathVariable int id) {
        return ResponseEntity.ok().body(deviceNameService.deleteDeviceType(id));
    }
}
