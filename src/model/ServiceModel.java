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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import DAO.EntidadeBase;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_service")
public class ServiceModel implements EntidadeBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_service")
	private long id;
	@Column
	private String description;

	@JsonIgnoreProperties("service")
	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
	private List<FormModel> forms;

	@JsonIgnoreProperties("services")
	@ManyToOne
	@JoinColumn(name = "idsector", nullable = true)
	private SectorModel sector;

	public SectorModel getSector() {
		return sector;
	}

	public void setSector(SectorModel sector) {
		this.sector = sector;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
