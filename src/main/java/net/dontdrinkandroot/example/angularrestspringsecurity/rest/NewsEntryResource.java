package net.dontdrinkandroot.example.angularrestspringsecurity.rest;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.NewsEntryDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Path("/news")
public class NewsEntryResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NewsEntryDao newsEntryDao;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<NewsEntry> list() {

		this.logger.info("list()");

		List<NewsEntry> allEntries = this.newsEntryDao.findAll();
		return allEntries;
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public NewsEntry read(@PathParam("id") Long id) {

		this.logger.info("getEntry(id)");

		NewsEntry newsEntry = this.newsEntryDao.find(id);
		if (newsEntry == null) {
			throw new WebApplicationException(404);
		}
		return newsEntry;
	}


	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public NewsEntry update(NewsEntry newsEntry) {

		this.logger.info("update(): " + newsEntry);

		return this.newsEntryDao.save(newsEntry);
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public NewsEntry create(NewsEntry newsEntry) {

		this.logger.info("create(): " + newsEntry);

		return this.newsEntryDao.save(newsEntry);
	}


	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {

		this.logger.info("deleteEntry(id)");

		this.newsEntryDao.delete(id);
	}

}