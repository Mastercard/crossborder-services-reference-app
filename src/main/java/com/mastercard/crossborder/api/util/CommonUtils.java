package com.mastercard.crossborder.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.crossborder.api.exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

@Component
public class CommonUtils {

	private CommonUtils() {
		//to hide public constructor
	}

	private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	private TransformerFactory tf = TransformerFactory.newInstance();
	private ObjectMapper mapper = new ObjectMapper();

	public String convertToString(HttpHeaders headers, Object data) throws ServiceException {
		if(data != null){
			if ( null != headers.getContentType() && MediaType.APPLICATION_JSON.equals(headers.getContentType().toString())) {
				return convertJsonToString(data);
			}
			if ( null != headers.getContentType() && MediaType.APPLICATION_XML.equals(headers.getContentType().toString())) {
				return convertDocumentToString(data);
			}
		}
		return null;
	}

	private String convertDocumentToString(Object obj) throws ServiceException {

		if(null == obj)
			return "";
		Document document = createXMLDocument(obj);
		Transformer transformer;
		if(null != document ) {
			try {
				transformer = tf.newTransformer();
				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(document), new StreamResult(writer));
				return writer.getBuffer().toString();
			} catch (TransformerException e) {
				throw new ServiceException(e.getMessage());
			}
		}
		return "";
	}

	public String convertJsonToString(Object jsonObject) throws ServiceException {

		try {
			return mapper.writeValueAsString(jsonObject);
		} catch (JsonProcessingException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	private Document createXMLDocument(Object request) throws ServiceException {
		try {
			Document doc = dbf.newDocumentBuilder().newDocument();
			JAXBContext context = JAXBContext.newInstance(request.getClass());
			context.createMarshaller().marshal(request, doc);
			return doc;
		}catch (ParserConfigurationException | JAXBException e){
			throw new ServiceException(e.getMessage());
		}
	}
}
