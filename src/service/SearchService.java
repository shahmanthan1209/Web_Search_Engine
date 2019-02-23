package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.*;


@Path("/search")
public class SearchService {

	@GET
	@Path("/s")
	public void search() {
		System.out.println("Inside web service");

	}

	
}
