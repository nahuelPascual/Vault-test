package com.vault.test.model.dao;

import com.vault.test.model.entity.Location;
import com.vault.test.model.exception.DaoException;
import com.vault.test.model.exception.ValidationException;

public interface LocationDao extends EntityDao<Long, Location> {

    Location getLocation (Long id) throws DaoException, ValidationException;

}
