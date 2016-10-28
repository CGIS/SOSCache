package org.cgis.dev.ds;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zil on 2016/9/15.
 */

@Entity
@Table(name="Offering")
public class Offering {

    @Id @GeneratedValue
    @Column(name = "OFFERING_ID", unique = true)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SOS_ID", nullable = false)
    private SOS sos;

    @Column(name = "OFFERING_NAME", unique = true, nullable = false, length = 150)
    private String name;

    @Column(name = "PROCEDURE_NAME", unique = true, nullable = false, length = 150)
    private String procedure;

    @Column(name = "OFFERING_BEGIN_TIMESTAMP")
    private Date beginPosition;

    @Column(name = "OFFERING_END_TIMESTAMP")
    private Date endPosition;

    @Column(name = "OFFERING_AREA_LOWER")
    private String lowerCorner;

    @Column(name = "OFFERING_AREA_UPPER")
    private String upperCorner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "offering")
    private Set<Observation> observations = new HashSet<Observation>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "EMPLOYEE_MEETING",
                joinColumns = {@JoinColumn(name = "OFFERING_ID")},
                inverseJoinColumns={@JoinColumn(name="PROPERTY_ID")})
    private Set<ObservableProperty> observableProperties = new HashSet<ObservableProperty>();

    public Offering() {
    }

    public Offering(String name, String procedure) {
        this.name = name;
        this.procedure = procedure;
    }

    public Offering(String procedure) {
        this.procedure = procedure;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + procedure.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Offering) {
            Offering offering = (Offering) obj;
            return ( offering.procedure.equals(this.procedure)
                    && offering.beginPosition.equals(this.beginPosition) && offering.endPosition.equals(this.endPosition)
                    && (offering.lowerCorner.equals(this.lowerCorner) && offering.upperCorner.equals(this.upperCorner)));
        }
        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SOS getSos() {
        return sos;
    }

    public void setSos(SOS sos) {
        this.sos = sos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public Set<Observation> getObservations() {
        return observations;
    }

    public void setObservations(Set<Observation> observations) {
        this.observations = observations;
    }

    public Set<ObservableProperty> getObservableProperties() {
        return observableProperties;
    }

    public void setObservableProperties(Set<ObservableProperty> observableProperties) {
        this.observableProperties = observableProperties;
    }

    public String toJSON() {
        return "{ name: \"" + getName() + "\", procedure: \"" + getProcedure() + "\", properties: " + propertiesSetToJsonArray() + ", area: [ \"" + getLowerCorner() + "\", \"" + getUpperCorner() + "\" ] , time: { begin: \"" + getBeginPosition() + "\", end: \"" + getEndPosition() + "\" } }";
    }

    private String propertiesSetToJsonArray(){
        String[] jsonArray = new String[observableProperties.size()];
        ObservableProperty[] array = observableProperties.toArray(new ObservableProperty[observableProperties.size()]);
        for( int i = 0; i < array.length ; i++ )
            jsonArray[i] = array[i].toJSON();
        return Arrays.toString(jsonArray);
    }

    public Date getBeginPosition() {
        return beginPosition;
    }

    public void setBeginPosition(Date beginPosition) {
        this.beginPosition = beginPosition;
    }

    public Date getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Date endPosition) {
        this.endPosition = endPosition;
    }

    public String getLowerCorner() {
        return lowerCorner;
    }

    public void setLowerCorner(String lowerCorner) {
        this.lowerCorner = lowerCorner;
    }

    public String getUpperCorner() {
        return upperCorner;
    }

    public void setUpperCorner(String upperCorner) {
        this.upperCorner = upperCorner;
    }
}
