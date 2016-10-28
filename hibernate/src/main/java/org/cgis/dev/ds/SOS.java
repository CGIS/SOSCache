package org.cgis.dev.ds;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zil on 2016/9/15.
 */

@Entity
@Table(name="SOS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "SOS_URL"),
        @UniqueConstraint(columnNames = "SOS_NAME")})
public class SOS {

    @Id @GeneratedValue
    @Column(name = "SOS_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "SOS_URL", unique = true, nullable = false, length = 150)
    private String url;

    @Column(name = "SOS_TYPE")
    private String type;

    @Column(name = "SOS_NAME", unique = true, nullable = false, length = 100)
    private String name;

    // YY:MM:HH:mm:ss
    @Column(name = "SOS_UPDATE_FREQUENCY")
    private String frequency;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sos")
    private Set<Offering> offerings = new HashSet<Offering>();

    public SOS() {
    }

    public SOS(String name, String url, String type) {
        this.name = name;
        this.url = url;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Offering> getOfferings() {
        return offerings;
    }

    public void setOfferings(Set<Offering> offerings) {
        this.offerings = offerings;
    }

    public String toJSON(){

        return "{ name: \"" + getName() + "\", url: \""+ getUrl() +"\", type:\""+ getType() +"\", offering: " + offeringSetToJsonArray() + "}";
    }

    private String offeringSetToJsonArray(){
        String[] jsonArray = new String[offerings.size()];
        Offering[] offeringsArray = offerings.toArray(new Offering[offerings.size()]);
        for( int i = 0; i < offeringsArray.length ; i++ )
            jsonArray[i] = offeringsArray[i].toJSON();
        return Arrays.toString(jsonArray);
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
