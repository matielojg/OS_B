package util;

import java.io.IOException;

import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class WSJsonProviderConfig extends JacksonJaxbJsonProvider implements ContainerResponseFilter {

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		Hibernate5Module hibernateModule = new Hibernate5Module();
		hibernateModule.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
		mapper.registerModule(hibernateModule);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	public WSJsonProviderConfig() {
		super();
		setMapper(mapper);
	}

	@Override
	public void filter(ContainerRequestContext arg0, ContainerResponseContext cres) throws IOException {
		cres.getHeaders().add("Access-Control-Allow-Origin", "*");
		cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
		cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		cres.getHeaders().add("Access-Control-Max-Age", "1209600");
	}

}