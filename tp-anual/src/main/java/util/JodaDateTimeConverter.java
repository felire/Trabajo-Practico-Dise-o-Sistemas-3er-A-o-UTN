package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.mapping.MappedField;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.diagnostics.logging.Logger;

import javafx.util.converter.LocalDateStringConverter;

public class JodaDateTimeConverter extends TypeConverter implements SimpleValueConverter {
    //private static final Logger LOG = (Logger) MorphiaLoggerFactory.get(JodaDateTimeConverter.class);


    /**
     * Creates the Converter.
     */
    public JodaDateTimeConverter() {
        this(LocalDate.class);
    }

    protected JodaDateTimeConverter(final Class clazz) {
        super(clazz);
    }

    @Override
    public Object encode(Object value, MappedField optionalExtraInfo) {
        if (value == null) {
            return null;
        }
        LocalDate localDate = (LocalDate) value;

        DBObject dbo = new BasicDBObject();

        dbo.put("year", localDate.getYear());
        dbo.put("month", localDate.getMonth().getValue());
        dbo.put("day", localDate.getDayOfMonth());

        return dbo;
    }
    
    @Override
    public Object decode(final Class<?> targetClass, final Object fromDBObject, final MappedField optionalExtraInfo) {
    	DBObject dbo = (DBObject) fromDBObject;
        if (dbo == null) {
            return null;
        }

        LocalDate localDate = null;

        Integer anio = (Integer) dbo.get("year");
        Integer mes = (Integer) dbo.get("month");
        Integer dia = (Integer) dbo.get("day");

        if (anio != null && mes != null && dia != null) {
            localDate = LocalDate.of(anio, mes, dia);
        }

        return localDate;
    }
}