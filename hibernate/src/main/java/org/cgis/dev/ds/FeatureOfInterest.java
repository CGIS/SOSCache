package org.cgis.dev.ds;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zil on 2016/9/20.
 */

@Entity
@Table(name="FeatureOfInterest")
public class FeatureOfInterest {

    @Id @GeneratedValue
    @Column(name = "FEATURE_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "FEATURE_NAME", nullable = false, length = 150)
    private String name;

    @Column(name = "FEATURE_LAT", nullable = false, length = 150)
    private String latitude;

    @Column(name = "FEATURE_LON", nullable = false, length = 150)
    private String longitude;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "featureOfInterest")
    private Set<Observation> observations = new HashSet<Observation>();

    public FeatureOfInterest() {
    }

    public FeatureOfInterest(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Set<Observation> getObservations() {
        return observations;
    }

    public void setObservations(Set<Observation> observations) {
        this.observations = observations;
    }
}
