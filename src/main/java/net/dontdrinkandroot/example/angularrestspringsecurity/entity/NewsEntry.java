package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
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


	public Long getId() {

		return this.id;
	}


	// TODO: remove
	public void setId(Long id) {

		this.id = id;
	}


	public Date getDate() {

		return this.date;
	}


	public void setDate(Date date) {

		this.date = date;
	}


	public String getContent() {

		return this.content;
	}


	public void setContent(String content) {

		this.content = content;
	}

}
