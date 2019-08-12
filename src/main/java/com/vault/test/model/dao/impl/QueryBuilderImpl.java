package com.vault.test.model.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.vault.test.model.dao.JpaBasicDaoImpl;
import com.vault.test.model.dao.QueryBuilder;
import com.vault.test.model.exception.DaoException;
import com.vault.test.model.utils.DateUtility;
import com.vault.test.model.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class QueryBuilderImpl extends JpaBasicDaoImpl implements QueryBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryBuilderImpl.class);

    @Inject
    private Messages messages;

    @Override
    public List<?> createQuery(String query, Map<String, Object> parameters, int startPosition, int resultLimit) throws DaoException {
        return executeQuery(getQuery(query), parameters, resultLimit, startPosition);
    }

    private List<?> executeQuery(Query query, Map<String, Object> parameters, int resultLimit, int startPosition) {
        Date fechaInicial = new Date();
        LOGGER.info(messages.get("info.queryBuilder.ejecucionQueryInicio", query.toString(), DateUtility.getString(fechaInicial, DateUtility.PATRON_FECHA_HORA_CON_BARRAS)));
        if (parameters != null) {
            Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
            for (Map.Entry<String, Object> entry : rawParameters) {
                if (entry.getValue() instanceof Date) {
                    query.setParameter(entry.getKey(), (Date) entry.getValue(), TemporalType.TIMESTAMP);
                } else if (containParameter(query, entry.getKey())) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
        }

        if (startPosition > 0) {
            query.setFirstResult(startPosition);
        }

        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }

        List<?> resultado = query.getResultList();

        return resultado;
    }

    private Query getQuery(String consultaJpql) {
        return this.getEntityManager().createQuery(consultaJpql);
    }

    private boolean containParameter(Query query, String key) {
        Set<Parameter<?>> parametros = query.getParameters();
        for (Parameter<?> parametro : parametros) {
            if (parametro.getName().equalsIgnoreCase(key)) {
                return true;
            }
        }

        return false;
    }
}

