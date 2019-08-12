package com.vault.test.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@SequenceGenerator(name = "locationSequence", sequenceName = "SEQ_LOCATIONS", allocationSize = 1)
@Table(name = "LOCATIONS")
public class Location implements Serializable {

    private static final int SIZE_STREET_ADDRESS = 40;
    private static final int SIZE_POSTAL_CODE = 12;
    private static final int SIZE_CITY = 30;
    private static final int SIZE_STATE_PROVINCE = 25;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locationSequence")
    private Long locationId;

    @Column(name = "STREET_ADDRESS")
    @Size(max = SIZE_STREET_ADDRESS)
    private String streeAddress;

    @Column(name = "POSTAL_CODE")
    @Size(max = SIZE_POSTAL_CODE)
    private String postalCode;

    @Column(name = "CITY")
    @Size(max = SIZE_CITY)
    private String city;

    @Column(name = "STATE_PROVINCE")
    @Size(max = SIZE_STATE_PROVINCE)
    private String stateProvince;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getStreeAddress() {
        return streeAddress;
    }

    public void setStreeAddress(String streeAddress) {
        this.streeAddress = streeAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
