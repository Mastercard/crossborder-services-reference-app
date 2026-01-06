package com.mastercard.crossborder.api.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.mastercard.crossborder.api.rest.response.Detail;
import com.mastercard.crossborder.api.rest.response.Details;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DetailsDeserializer extends JsonDeserializer<Details> {
	@Override
	public Details deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = p.getCodec().readTree(p);

		Details details = new Details();

		if (node.isArray()) {
			// Deserialize as a list of Detail objects
			List<Detail> detailList = new ArrayList<>();
			for (JsonNode detailNode : node) {
				Detail detail = p.getCodec().treeToValue(detailNode, Detail.class);
				detailList.add(detail);
			}
			details.setDetails(detailList);
		} else if (node.isObject()) {
			// Deserialize as a single Detail object
			Detail detail = p.getCodec().treeToValue(node, Detail.class);
			details.setDetails(Collections.singletonList(detail));
		}

		return details;
	}
}
