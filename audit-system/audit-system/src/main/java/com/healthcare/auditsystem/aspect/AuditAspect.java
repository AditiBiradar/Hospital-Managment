package com.healthcare.auditsystem.aspect;

import com.healthcare.auditsystem.annotation.Auditable;
import com.healthcare.auditsystem.entity.AuditLog;
import com.healthcare.auditsystem.repository.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {
    private final AuditLogRepository auditLogRepository;

    public AuditAspect(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Around("@annotation(auditable)")
    public Object logAction(ProceedingJoinPoint joinPoint, Auditable auditable) throws Throwable {
        String outcome = "SUCCESS";
        String details = "";
        Object result;
        Object[] args = joinPoint.getArgs();
        String resourceId = args.length > 0 ? String.valueOf(args[0]) : "N/A";
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Exception ex) {
            outcome = "FAILURE";
            details = ex.getMessage();
            throw ex;
        } finally {
            String username = "demo-user";
            String ipAddress = "unknown";
            try {
                ServletRequestAttributes attrs =
                        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attrs != null) {
                    HttpServletRequest request = attrs.getRequest();
                    ipAddress = request.getRemoteAddr();
                    String headerUser = request.getHeader("X-User-Name");
                    if (headerUser != null) username = headerUser;
                }
            } catch (Exception ignored) {}
            AuditLog log = AuditLog.builder()
                    .username(username)
                    .action(auditable.action())
                    .resourceType(auditable.resourceType())
                    .resourceId(resourceId)
                    .outcome(outcome)
                    .ipAddress(ipAddress)
                    .details(details)
                    .timestamp(LocalDateTime.now())
                    .build();
            auditLogRepository.save(log);
        }
    }
}
