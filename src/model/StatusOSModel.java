package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import DAO.EntidadeBase;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_status")
public class StatusOSModel implements EntidadeBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_status")
	private long id;
	@Column
	private String nameStatus;

	@JsonIgnoreProperties("status")
	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<FormModel> forms;

	public List<FormModel> getForms() {
		return forms;
	}

	public void setForms(List<FormModel> forms) {
		this.forms = forms;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameStatus() {
		return nameStatus;
	}

	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}

	

}
