@Provider
@PreMatching
public class RESTRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger( RESTRequestFilter.class.getName() );

    @Override
    public void filter( ContainerRequestContext requestCtx ) throws IOException {
        log.info( "Executing REST request filter" );
          requestCtx.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
          requestCtx.getHeaders().add( "Access-Control-Allow-Origin", "http://localhost:8090");
          requestCtx.getHeaders().add( "Access-Control-Allow-Methods", "OPTIONS, GET, POST, DELETE, PUT" );
          requestCtx.getHeaders().add( "Access-Control-Allow-Headers", "Content-Type" );

        // When HttpMethod comes as OPTIONS, just acknowledge that it accepts...
        if ( requestCtx.getRequest().getMethod().equals( "OPTIONS" ) ) {
           log.info( "HTTP Method (OPTIONS) - Detected!" );

            // Just send a OK signal back to the browser (Abort the filter chain with a response.)
           Response response = Response.status( Response.Status.OK )
                   .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                   .header("Access-Control-Allow-Origin", "http://localhost:8090")
                   .header("Access-Control-Allow-Headers", "Content-Type, accept, headers")
                   .build();           
           requestCtx.abortWith( response );

        }

    }

}

@Provider
@PreMatching
public class RESTResponseFilter implements ContainerResponseFilter {

    private final static Logger log = Logger.getLogger( RESTResponseFilter.class.getName() );

    @Override
    public void filter( ContainerRequestContext requestCtx, ContainerResponseContext responseCtx ) throws IOException {
        log.info( "Executing REST response filter" );

        // The following was required to permit testing outside the Application Server Container
//        responseCtx.getHeaders().add( "Access-Control-Allow-Origin", "http://127.0.0.1:53307" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Origin", "http://localhost:8090");
        responseCtx.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Methods", "OPTIONS, GET, POST, DELETE, PUT" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Access-Control-Allow-Methods, Access-Control-Allow-Headers, Authorization, X-Requested-With" );
    }
}
