package com.vault.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Country {

    private static final int SIZE_COUNTRY_ID = 2;
    private static final int SIZE_COUNTRY_NAME = 40;

    @Id
    @Size(max = SIZE_COUNTRY_ID)
    private String countryId;

    @Column(name = "COUNTRY_NAME")
    @Size(max = SIZE_COUNTRY_NAME)
    private String countryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REGION_ID")
    private Region region;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
