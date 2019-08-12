package com.vault.test.model.dao;

import com.vault.test.model.exception.DaoException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;

import javax.persistence.criteria.CriteriaQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @param <K> Clave de la Entidad
 * @param <E> Entidad
 */
public abstract class JpaEntityDaoImpl<K, E> extends JpaBasicDaoImpl implements EntityDao<K, E> {

    @Override
    public E saveOrUpdate(E entity) throws DaoException {
        if (super.getEntityManager().contains(entity)) {
            return this.merge(entity);
        } else {
            return this.persist(entity);
        }
    }

    @Override
    public E persist(E entity) throws DaoException {
        try {
            super.getEntityManager().persist(entity);

            return entity;
        } catch (ConstraintViolationException e) {
            throw new DaoException(procesarConstraintViolationException(e), e);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public E merge(E entity) throws DaoException {
        try {
            return super.getEntityManager().merge(entity);
        } catch (ConstraintViolationException e) {
            throw new DaoException(procesarConstraintViolationException(e), e);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void remove(K id) throws DaoException {
        try {
            super.getEntityManager().remove(findById(id));
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public E findById(K id) throws DaoException {
        try {
            if (id != null) {
                return super.getEntityManager().find(getEntityClass(), id);
            } else {
                return null;
            }
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public E findOrDefault(K id, E entityDefault) throws DaoException {
        E entity = findById(id);
        if (entity != null) {
            return entity;
        } else {
            return entityDefault;
        }
    }

    @Override
    public List<E> findAll() throws DaoException {
        try {
            if (super.getEntityManager() != null) {
                CriteriaQuery<E> criteriaQuery = super.getEntityManager().getCriteriaBuilder().createQuery(getEntityClass());

                criteriaQuery = criteriaQuery.select(criteriaQuery.from(getEntityClass()));

                return super.getEntityManager().createQuery(criteriaQuery).getResultList();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void refresh(E entity) throws DaoException {
        try {
            super.getEntityManager().refresh(entity);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void flush() throws DaoException {
        try {
            super.getEntityManager().flush();
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void detach(E entity) throws DaoException {
        try {
            super.getEntityManager().detach(entity);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) parameterizedType.getActualTypeArguments()[1];
    }

    private String procesarConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder("");

        String saltoDeLinea = "";
        Set<ConstraintViolation<?>> constraintsValidation = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintsValidation) {
            message.append(saltoDeLinea).append(constraintViolation.getMessage());

            saltoDeLinea = "\r\n";
        }

        return message.toString();
    }
}

