package com.healthcare.auditsystem.controller;

import com.healthcare.auditsystem.entity.AuditLog;
import com.healthcare.auditsystem.service.AuditLogService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@CrossOrigin(origins = "http://localhost:3000") // <-- Add this annotation!
public class AuditLogController {
    private final AuditLogService service;

    public AuditLogController(AuditLogService service) {
        this.service = service;
    }

    @GetMapping
    public List<AuditLog> getAll() {
        return service.findAll();
    }

    @GetMapping("/search")
    public List<AuditLog> search(@RequestParam String username) {
        return service.searchByUsername(username);
    }
}