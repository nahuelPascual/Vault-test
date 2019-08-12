package com.vault.test.model.dao.impl;

import com.vault.test.model.dao.JpaEntityDaoImpl;
import com.vault.test.model.dao.LocationDao;
import com.vault.test.model.entity.Location;
import com.vault.test.model.exception.DaoException;
import com.vault.test.model.exception.ValidationException;
import com.vault.test.model.utils.Messages;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LocationDaoImpl extends JpaEntityDaoImpl<Long, Location> implements LocationDao {

    @Inject
    private Messages messages;

    @Override
    public Location getLocation(Long id) throws DaoException, ValidationException {
        Location l = findById(id);
        if(l == null) {
            throw new ValidationException(messages.get("error.validation.locationInexistente"));
        }
        return l;
    }
}
