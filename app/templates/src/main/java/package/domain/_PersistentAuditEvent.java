package <%=packageName%>.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import <%=packageName%>.domain.util.EntityWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Persist AuditEvent managed by the Spring Boot actuator
 *
 * @see org.springframework.boot.actuate.audit.AuditEvent
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "audit_events")
public class PersistentAuditEvent extends Base implements Resource<UUID> {
    @NotNull
    @Column(name = "principal")
    private String principal;

    @Column(name = "event_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime auditEventDate;

    @Column(name = "event_type")
    private String auditEventType;

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "audit_event_data", joinColumns = @JoinColumn(name = "audit_event_id"))
    private Map<String, String> data = new HashMap<>();

    @Data
    public static class AuditEventWrapper implements EntityWrapper<PersistentAuditEvent> {
        @Valid
        private PersistentAuditEvent auditEvent;

        public AuditEventWrapper() {
        }

        public AuditEventWrapper(PersistentAuditEvent auditEvent) {
            this.auditEvent = auditEvent;
        }

        @JsonIgnore
        @Override
        public PersistentAuditEvent getEntity() {
            return auditEvent;
        }
    }
}
