package ru.nicholas.event_processor.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.proxy.HibernateProxy;
import ru.nicholas.event_processor.util.IncidentType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Сущность, представляющая инцидент.
 */
@Entity
@Table(name = "incident")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Incident {
    /**
     * Идентификатор события.
     */
    @Id
    @UuidGenerator
    private UUID id;

    /**
     * Тип события.
     */
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private IncidentType type;

    /**
     * Дата-время события.
     */
    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    /**
     * Соответствующие события.
     */
    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Event> events;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Incident incident = (Incident) o;
        return getId() != null && Objects.equals(getId(), incident.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "type = " + type + ", " +
                "time = " + time + ")";
    }
}
