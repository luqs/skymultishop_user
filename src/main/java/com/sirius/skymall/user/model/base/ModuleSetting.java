package com.sirius.skymall.user.model.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="module_setting",schema="")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ModuleSetting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1055826203484966661L;
	private Integer id;
	private String title;
	private String engtitle;
	private String link;
	private String icon;
	private Integer showindex;
	private Integer visible;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false,length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="title",length=200)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="link",length=200)
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Column(name="icon",length=200)
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Column(name="engtitle",length=200)
	public String getEngtitle() {
		return engtitle;
	}
	public void setEngtitle(String engtitle) {
		this.engtitle = engtitle;
	}
	@Column(name="showindex")
	public Integer getShowindex() {
		return showindex;
	}
	public void setShowindex(Integer showindex) {
		this.showindex = showindex;
	}
	@Column(name="visible")
	public Integer getVisible() {
		return visible;
	}
	public void setVisible(Integer visible) {
		this.visible = visible;
	}

}
