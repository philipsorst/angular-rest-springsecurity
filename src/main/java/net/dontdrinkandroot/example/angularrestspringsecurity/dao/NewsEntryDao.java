package net.dontdrinkandroot.example.angularrestspringsecurity.dao;

import java.util.List;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;


public interface NewsEntryDao {

	List<NewsEntry> findAll();


	NewsEntry find(Long id);


	NewsEntry save(NewsEntry newsEntry);


	void delete(Long id);

}
