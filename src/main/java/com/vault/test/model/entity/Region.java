package com.vault.test.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@SequenceGenerator(name = "regionSequence", sequenceName = "SEQ_REGIONS", allocationSize = 1)
@Table(name = "REGIONS")
public class Region implements Serializable {

    private static final int SIZE_REGION_NAME = 25;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regionSequence")
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
