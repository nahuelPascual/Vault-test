package com.vault.test.service;

import com.vault.test.model.exception.DaoException;
import com.vault.test.model.exception.ValidationException;
import com.vault.test.rest.dto.DepartmentDto;

public interface DepartmentService {

    void create(DepartmentDto departmentDto) throws DaoException, ValidationException;

    }
