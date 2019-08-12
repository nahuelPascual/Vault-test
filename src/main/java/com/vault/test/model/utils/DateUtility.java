package com.vault.test.model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public final class DateUtility {

    public static final String PATRON_FECHA_HORA_CON_BARRAS = "dd/MM/yyyy HH:mm:ss";

    public static Date parseDate(String fecha, String pattern) throws ParseException {
        if (StringUtils.isNotBlank(fecha)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(fecha);
        } else {
            return null;
        }
    }

    public static Date parseDate(Date fecha, String pattern) throws ParseException{
        return parseDate(getString(fecha, pattern), pattern);
    }

    public static String getString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return date != null ? sdf.format(date) : null;
    }

}

