package com.scheduler.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleCompositeId implements Serializable {

    private static final long serialVersionUID = 4931156580468910545L;

    @Column(name = "shift_date", nullable = false)
    @Getter
    @Setter
    private LocalDate shiftDate;


    @Column(name = "shift_time", nullable = false)
    @Getter
    @Setter
    private LocalTime shiftTime;

    @Override
    public int hashCode() {
        return Objects.hash(shiftTime, shiftDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ScheduleCompositeId entity = (ScheduleCompositeId) o;
        return Objects.equals(this.shiftTime, entity.shiftTime) &&
                Objects.equals(this.shiftDate, entity.shiftDate);
    }
}