package com.vault.test.model.dao.impl;

import com.vault.test.model.dao.JobDao;
import com.vault.test.model.dao.JpaEntityDaoImpl;
import com.vault.test.model.dao.RegionDao;
import com.vault.test.model.entity.Job;
import com.vault.test.model.entity.Region;

import javax.inject.Named;

@Named
public class JobDaoImpl extends JpaEntityDaoImpl <Long, Job> implements JobDao {

}
