package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import DAO.EntidadeBase;

@Entity
@Table(name = "tb_action")
public class ActionModel implements EntidadeBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_action")
	private long id;
	
	@Column(name = "change_") // palavra reservada mysql
	private String change;
	
	@Column
	private Timestamp dateChange;

	@JsonIgnoreProperties("actions") // quando é RESTFul precisa desta annotation
	@OneToOne
	@JoinColumn(name = "iduser", nullable = false)
	private UserModel user;// correto

	@JsonIgnoreProperties("actions")
	@ManyToOne
	@JoinColumn(name = "form_id", nullable = false)
	private FormModel form;

	
	public FormModel getForm() {
		return form;
	}

	public void setForm(FormModel form) {
		this.form = form;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public Timestamp getDateChange() {
		return dateChange;
	}

	public void setDateChange(Timestamp dateChange) {
		this.dateChange = dateChange;
	}

}
