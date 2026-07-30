package com.healthcare.auditsystem.repository;

import com.healthcare.auditsystem.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUsernameContainingIgnoreCase(String username);
    List<AuditLog> findByResourceIdAndResourceType(String resourceId, String resourceType);
}
