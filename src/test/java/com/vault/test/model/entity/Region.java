package com.vault.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Region {

    private static final int SIZE_REGION_NAME = 25;

    @Id
    private Long regionId;

    @Column(name = "REGION_NAME")
    @Size(max = SIZE_REGION_NAME)
    private String regionName;

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
