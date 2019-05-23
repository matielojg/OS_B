package util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("api")
public class WSConfigV2 extends ResourceConfig {

	public WSConfigV2() {
		property("jersey.config.server.provider.classnames", "org.glassfish.jersey.media.multipart.MultiPartFeature");

		// Pacotes do projeto
		packages("controller");
		packages("util");

		property(ServerProperties.TRACING, "ALL");
		register(WSJsonProviderConfig.class);
		register(JacksonFeature.class);
		register(MultiPartFeature.class);

	}

}
