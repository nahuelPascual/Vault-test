package com.vault.test.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@SequenceGenerator(name = "jobSequence", sequenceName = "SEQ_JOBS", allocationSize = 1)
@Table(name = "JOBS")
public class Job implements Serializable {

    private static final int SIZE_JOB_ID = 2;
    private static final int SIZE_JOB_TITLE = 35;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobSequence")
    @Size(max = SIZE_JOB_ID)
    private Long jobId;

    @Column(name = "JOB_TITLE")
    @Size(max = SIZE_JOB_TITLE)
    private String jobTitle;

    @Column(name = "MIN_SALARY")
    private Long minSalary;

    @Column(name = "MAX_SALARY")
    private Long maxSalary;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }
}
