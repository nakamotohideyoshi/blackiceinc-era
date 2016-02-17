package com.blackiceinc.era.persistence.erau.model.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;
import java.sql.Date;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Date sqlDate = null;

        JsonToken t = jsonParser.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String str = jsonParser.getText().trim();
            java.util.Date jodaDate = ISODateTimeFormat.dateTimeParser().parseDateTime(str).toLocalDate().toDate();
            sqlDate = new Date(jodaDate.getTime());
        }

        return sqlDate;
    }
}
