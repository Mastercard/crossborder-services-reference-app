package com.mastercard.crossborder.api.rest.request.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mastercard.crossborder.api.rest.request.GovernmentIdData;

import java.io.IOException;

/**
 * Created by e065730 on 29-07-2019.
 */
public class GovernmentIdSerializer extends JsonSerializer<GovernmentIdData> {

    public static final String GOVERNMENT_ID_URI_NODE_KEY = "government_id_uri";

    public GovernmentIdSerializer() {
        //default constructor
    }

    @Override
    public void serialize(GovernmentIdData value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (null != value) {
            jgen.writeStartArray();
            for (String val : value.getGovernmentIdURIs()) {
                jgen.writeStartObject();
                jgen.writeFieldName(GOVERNMENT_ID_URI_NODE_KEY);
                jgen.writeObject(val);
                jgen.writeEndObject();
            }
            jgen.writeEndArray();
        }
    }
}
