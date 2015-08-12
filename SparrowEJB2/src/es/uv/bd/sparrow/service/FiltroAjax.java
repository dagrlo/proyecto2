package es.uv.bd.sparrow.service;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

public class FiltroAjax implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		headers.add("Filtro", "Jersey :-)");
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST");			
		headers.add("Access-Control-Allow-Headers", "X-Requested-With,Authorization, Content-Type, X-Codingpedia");
	}

}
