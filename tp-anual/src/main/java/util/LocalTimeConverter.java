package util;

import javax.persistence.*;

import java.time.*;

import java.sql.*;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {
    
    public Time convertToDatabaseColumn(LocalTime locTime) {
    	return (locTime == null ? null : Time.valueOf(locTime));
    }

    public LocalTime convertToEntityAttribute(Time sqlDate) {
    	return (sqlDate == null ? null : sqlDate.toLocalTime());
    }

}
