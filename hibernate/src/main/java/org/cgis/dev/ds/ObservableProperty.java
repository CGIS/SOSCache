package org.cgis.dev.ds;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Zil on 2016/9/19.
 */
@Entity
@Table(name = "ObservableProperty")
public class ObservableProperty {

    @Id @GeneratedValue
    @Column(name = "PROPERTY_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "PROPERTY_NAME", nullable = false, length = 150)
    private String name;

    @ManyToMany(mappedBy="observableProperties")
    private Set<Offering> offering;

    public ObservableProperty() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Offering> getOffering() {
        return offering;
    }

    public void setOffering(Set<Offering> offering) {
        this.offering = offering;
    }

    public String toJSON() {
        return "\"" + getName() + "\"";
    }
}
