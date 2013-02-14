package net.dontdrinkandroot.example.angularrestspringsecurity.dao;

import java.util.Date;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;


public class DataBaseInitializer {

	private NewsEntryDao newsEntryDao;


	protected DataBaseInitializer() {

		/* Default constructor for reflection instantiation */
	}


	public DataBaseInitializer(NewsEntryDao newsEntryDao) {

		this.newsEntryDao = newsEntryDao;
	}


	public void initDataBase() {

		long timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
		for (int i = 0; i < 10; i++) {
			NewsEntry newsEntry = new NewsEntry();
			newsEntry.setContent("This is example content " + i);
			newsEntry.setDate(new Date(timestamp));
			this.newsEntryDao.save(newsEntry);
			timestamp += 1000 * 60 * 60;
		}
	}

}
