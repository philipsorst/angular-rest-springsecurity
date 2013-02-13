package net.dontdrinkandroot.example.angularrestspringsecurity.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.NewsEntryDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Path("/news")
public class NewsEntryResource {

	@Autowired
	private NewsEntryDao newsEntryDao;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public NewsEntry getEntry(@PathParam("id") Long id) {

		NewsEntry newsEntry = this.newsEntryDao.find(id);
		if (newsEntry == null) {
			throw new WebApplicationException(404);
		}
		return newsEntry;
	}

}