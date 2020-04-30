package com.mastercard.crossborder.api.rest.request.deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mastercard.crossborder.api.rest.request.GovernmentIdData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Custom deserialize for <code>GovernmentIdData</code>. This special deserializer is needed because
 * of backward compatiblity of cross border payment service where a client had a unintentional flexiblity
 * to send the government-id in various possible format. Below are the formates which are supported and
 * this deserializer is able to parse:
 *
 * <pre>
 * {@code
 * "government_ids":{
 *      "government_id_uri":
 *          "ppn:123456789;expiration-date=2019-05-27;issue-date=2011-07-12;country=USA"
 *      }
 * }

 * </pre>
 * <pre>
 * {@code
 *  "government_ids":[
 *      {"government_id_uri":"ppn:123456789;expiration-date=2019-05-27;issue-date=2011-07-12;country=USA"},
 *      {"government_id_uri":"ppn:123456789;expiration-date=2019-05-27;issue-date=2011-07-13;country=USA"}
 *    ]
 * }
 *
 * </pre>
 *
 * <pre>
 *
 * {@code
 *  "government_ids":{
 *      "government_id_uri":["ppn:123456789;expiration-date=2019-05-27;issue-date=2011-07-12;country=USA",
 *      "ppn:123456789;expiration-date=2019-05-27;issue-date=2011-07-13;country=USA"]
 *  }
 *
 * }
 *
 * </pre>
 *
 *
 *
 */
public class GovernmentIdDeserializer extends StdDeserializer<GovernmentIdData> {


    public static final String GOVERNMENT_ID_URI_NODE_KEY = "government_id_uri";

    private static Logger logger = LogManager.getLogger(GovernmentIdDeserializer.class);


    public GovernmentIdDeserializer(Class<?> vc) {
        super(vc);
    }

    public GovernmentIdDeserializer(JavaType valueType) {
        super(valueType);
    }

    public GovernmentIdDeserializer() {
        this((Class) null);
    }

    @Override
    public GovernmentIdData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        GovernmentIdData governmentIdData = new GovernmentIdData();
        List<String> idList = new ArrayList<>();
        if (jsonNode != null) {
            if (jsonNode.isArray()) {
                extractFromArray(idList, jsonNode);
            } else if (jsonNode.isObject()) {
                extractFromObject(idList, jsonNode);
            }
            logger.info("GovernmentIdDeserializer converted request object with size: {}", idList.size());
            governmentIdData.setGovernmentIdURIs(idList.isEmpty() ? null : idList);
        }
        return governmentIdData;
    }

    private void extractFromObject(List<String> idList, JsonNode jsonNode) {
        final JsonNode government_id_uri = jsonNode.get(GOVERNMENT_ID_URI_NODE_KEY);
        if (government_id_uri != null) {
            if (government_id_uri.isArray()) {
                final Iterator<JsonNode> elements = government_id_uri.elements();
                while (elements.hasNext()) {
                    extractTextualValue(idList, elements.next());
                }
            } else {
                extractTextualValue(idList, government_id_uri);
            }
        }
    }

    private void extractTextualValue(List<String> idList, JsonNode government_id_uri) {
        if (government_id_uri.isTextual()) {
            idList.add(government_id_uri.textValue());
        }
    }

    private void extractFromArray(List<String> idList, JsonNode jsonNode) {
        final Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            extractTextualValue(idList, elements.next().get(GOVERNMENT_ID_URI_NODE_KEY));
        }
    }
}
