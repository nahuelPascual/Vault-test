package com.vault.test.model.dao;

import com.vault.test.model.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface QueryBuilder {

    List<?> createQuery(String query, Map<String, Object> parameters, int startPosition, int resultLimit) throws DaoException;

}

