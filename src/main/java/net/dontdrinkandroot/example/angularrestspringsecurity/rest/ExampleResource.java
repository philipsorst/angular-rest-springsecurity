package net.dontdrinkandroot.example.angularrestspringsecurity.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/example")
public class ExampleResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public String getHTML() {

		return "Hello World";
	}

}