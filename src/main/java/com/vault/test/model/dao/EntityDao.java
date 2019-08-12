package com.vault.test.model.dao;

import com.vault.test.model.exception.DaoException;
import java.util.List;

/**
 * Interfaz con los métodos básicos para manejar la persistencia de una Entidad.
 *
 * @param <K> Clave de la Entidad
 * @param <E> Entidad
 *
 */
public interface EntityDao<K, E> {

    /**
     * Inserta una nueva Entidad, si existe la actualiza
     *
     * @param entity Entidad a Persistir o Actualizar
     * @return Devuelve la Entidad Persistida o Actualizada
     * @throws com.vault.test.model.exception.DaoException
     * Excepción Capa Dao
     */
    E saveOrUpdate(E entity) throws DaoException;

    /**
     * Persiste una entidad
     *
     * @param entity Entidad a Persistir
     * @return Devuelve la entidad Persistida
     * @throws com.vault.test.model.exception.DaoException
     * Excepción Capa Dao
     */
    E persist(E entity) throws DaoException;

    /**
     * Actualiza una entidad
     *
     * @param entity Entidad a Actualizar
     * @return Devuelve la Entidad actualizada
     * @throws com.vault.test.model.exception.DaoException
     * Excepción Capa Dao
     */
    E merge(E entity) throws DaoException;

    /**
     * Elimina una entidad por el valor de su clave Primaria
     *
     * @param id Id de la Entidada A Eliminar
     * @throws com.vault.test.model.exception.DaoException
     * Excepción Capa Dao
     */
    void remove(K id) throws DaoException;

    /**
     * Busca una Entidad por el valor de su Clave Primaria
     *
     * @param id Id de la Entidad buscada
     * @return Devuelve una Entidad por el Valor de su Clave Primaria
     * @throws com.vault.test.model.exception.DaoException
     * Excepción Capa Dao
     */
    E findById(K id) throws DaoException;

    /**
     * Devuelve la Entidad cuyo Id coincida con el especificado. Si la Entidad
     * Buscada no existe devuelve entityDefault
     *
     * @param id Id de la Entidad buscada
     * @param entityDefault Entidad a devolver si no se encuentra la que tiene
     * el ID especificado
     * @return Devuelve la Entidad cuyo Id coincida con el especificado. Si la
     * Entidad Buscada no existe devuelve entityDefault
     * @throws com.vault.test.model.exception.DaoException
     * Excepción Capa Dao
     */
    E findOrDefault(K id, E entityDefault) throws DaoException;

    /**
     * Devuelve una lista de todas las Entidades del tipo de entity
     *
     * @return Devuelve una lista de todas las Entidades del tipo de entity
     * @throws com.vault.test.model.exception.DaoException
     * Excepción Capa Dao
     */
    List<E> findAll() throws DaoException;

    /**
     * Refresca una entidad con las últimas novedades de la BD. Sobreescribe en
     * memoria cualquier atributo que se haya modificado, perdiéndose dichos
     * cambios
     *
     * @param entity Entidad a Refrescar
     * @throws com.vault.test.model.exception.DaoException
     * Excepción Capa Dao
     */
    void refresh(E entity) throws DaoException;

    /**
     * Impacta los cambios pendientes del Persistence Context en la Base
     *
     * @throws com.vault.test.model.exception.DaoException
     * Excepción Capa Dao
     */
    void flush() throws DaoException;

    /**
     * Hace que el contexto deje de manejar la Entidad. Después de hacer detach
     * no se puede hacer persist ni tampoco los cambios en atributos tendrán
     * impacto en la bae, salvo que se haga un merge antes.
     *
     * @param entity Entidad a Refrescar
     * @throws com.vault.test.model.exception.DaoException
     * Excepción Capa Dao
     */
    void detach(E entity) throws DaoException;
}

