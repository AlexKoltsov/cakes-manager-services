package com.koltsov.cakes.manager.cakesservice.data;

import com.koltsov.cakes.manager.data.IdAble;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "cakes")
@Entity
public class Cake implements IdAble<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cake")
    @SequenceGenerator(name = "seq_cake", allocationSize = 1)
    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cake cake = (Cake) o;
        return id != null && Objects.equals(id, cake.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
