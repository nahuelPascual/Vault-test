package com.vault.test.model.dao.impl;

import com.vault.test.model.dao.DepartmentDao;
import com.vault.test.model.dao.JpaEntityDaoImpl;
import com.vault.test.model.dao.RegionDao;
import com.vault.test.model.entity.Department;
import com.vault.test.model.entity.Region;
import com.vault.test.model.exception.DaoException;

import javax.inject.Named;

@Named
public class DepartmentDaoImpl extends JpaEntityDaoImpl <Long, Department> implements DepartmentDao {

}
