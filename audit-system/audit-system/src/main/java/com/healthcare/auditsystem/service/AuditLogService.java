package com.healthcare.auditsystem.service;

import com.healthcare.auditsystem.entity.AuditLog;
import com.healthcare.auditsystem.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditLogService {
    private final AuditLogRepository repository;

    public AuditLogService(AuditLogRepository repository) {
        this.repository = repository;
    }
    public List<AuditLog> findAll() { return repository.findAll(); }
    public List<AuditLog> searchByUsername(String username) {
        return repository.findByUsernameContainingIgnoreCase(username);
    }
}
