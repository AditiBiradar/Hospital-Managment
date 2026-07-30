package com.healthcare.auditsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String action;
    private String resourceType;
    private String resourceId;
    private String outcome;
    private String ipAddress;
    private String details;
    @Column(nullable = false)
    private LocalDateTime timestamp;
}
