package com.vault.test.model.dao.impl;

import com.vault.test.model.dao.JpaEntityDaoImpl;
import com.vault.test.model.dao.RegionDao;
import com.vault.test.model.entity.Region;

import javax.inject.Named;

@Named
public class RegionDaoImpl extends JpaEntityDaoImpl <Long, Region> implements RegionDao {

}
