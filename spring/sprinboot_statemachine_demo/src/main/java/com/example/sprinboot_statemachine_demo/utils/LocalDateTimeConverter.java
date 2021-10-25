package com.example.sprinboot_statemachine_demo.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        return convertLocalDateTimeToDate(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date dbData) {
        return convertDateToLocalDateTime(dbData);
    }

    private static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(Instant.from(localDateTime.atZone(ZoneId.systemDefault())));
    }

    private static LocalDateTime convertDateToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return convertMillsToLocalDateTime(date.getTime());
    }

    private static LocalDateTime convertMillsToLocalDateTime(Long mills) {
        if (mills == null) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(mills), ZoneId.systemDefault());
    }
}
