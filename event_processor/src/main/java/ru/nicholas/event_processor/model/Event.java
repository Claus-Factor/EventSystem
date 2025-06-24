package ru.nicholas.event_processor.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.proxy.HibernateProxy;
import ru.nicholas.event_processor.util.EventType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Сущность, представляющая событие.
 */
@Entity
@Table(name = "event")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
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
    private EventType type;

    /**
     * Дата-время события.
     */
    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    /**
     * Соответствующий событию инцидент.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incident")
    private Incident incident;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Event event = (Event) o;
        return getId() != null && Objects.equals(getId(), event.getId());
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
