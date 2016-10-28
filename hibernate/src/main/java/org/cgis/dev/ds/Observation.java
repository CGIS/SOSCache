package org.cgis.dev.ds;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Zil on 2016/9/15.
 */
@Entity
@Table(name="Observation")
public class Observation {

    @Id @GeneratedValue
    @Column(name = "OBSERVATION_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "OBSERVATION_RESULT", nullable = false, length = 150)
    private String result;

    @Column(name = "OBSERVATION_TYPE", nullable = false, length = 150)
    private String type;

    @Column(name = "OBSERVATION_TIME", nullable = false)
    private Date timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OFFERING_ID", nullable = false)
    private Offering offering;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FEATURE_ID", nullable = false)
    private FeatureOfInterest featureOfInterest;

    public Observation() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Offering getOffering() {
        return offering;
    }

    public void setOffering(Offering offering) {
        this.offering = offering;
    }

    public FeatureOfInterest getFeatureOfInterest() {
        return featureOfInterest;
    }

    public void setFeatureOfInterest(FeatureOfInterest featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
    }
}
