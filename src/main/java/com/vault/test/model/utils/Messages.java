package com.vault.test.model.utils;

import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@Named
public class Messages {

    private static final Logger LOGGER = LoggerFactory.getLogger(Messages.class);

    @Inject
    private MessageSource messageSource;

    public String get(String key) {
        try {
            return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            return key;
        }
    }

    public String get(String key, String... args) {
        try {
            return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

            return key ;
        }
    }

}

