package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import net.dontdrinkandroot.example.angularrestspringsecurity.JsonViews;

import org.codehaus.jackson.map.annotate.JsonView;


@Entity
public class NewsEntry implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private Date date;

	@Column
	private String content;


	public NewsEntry() {

		this.date = new Date();
	}


	@JsonView(JsonViews.Admin.class)
	public Long getId() {

		return this.id;
	}


	@JsonView(JsonViews.User.class)
	public Date getDate() {

		return this.date;
	}


	public void setDate(Date date) {

		this.date = date;
	}


	@JsonView(JsonViews.User.class)
	public String getContent() {

		return this.content;
	}


	public void setContent(String content) {

		this.content = content;
	}


	@Override
	public String toString() {

		return String.format("NewsEntry[%d, %s]", this.id, this.content);
	}

}
