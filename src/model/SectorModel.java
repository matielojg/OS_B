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
@Table(name = "tb_sector")
public class SectorModel implements EntidadeBase {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sector")
	public long id;
	@Column
	public String nameSector;

	@JsonIgnoreProperties("sector")
	@OneToMany(mappedBy = "sector", fetch = FetchType.LAZY)
	private List<UserModel> users;

	@JsonIgnoreProperties("sectorProvider")
	@OneToMany(mappedBy = "sectorProvider", fetch = FetchType.LAZY)
	private List<FormModel> FormProvider;

	@JsonIgnoreProperties("sectorRequester")
	@OneToMany(mappedBy = "sectorRequester", fetch = FetchType.LAZY)
	private List<FormModel> FormResponsible;

	@JsonIgnoreProperties("sector")
	@OneToMany(mappedBy = "sector", fetch = FetchType.LAZY)
	private List<ServiceModel> services;


	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ServiceModel> getServices() {
		return services;
	}

	public void setServices(List<ServiceModel> services) {
		this.services = services;
	}

	public List<FormModel> getFormProvider() {
		return FormProvider;
	}

	public void setFormProvider(List<FormModel> formProvider) {
		FormProvider = formProvider;
	}

	public List<FormModel> getFormResponsible() {
		return FormResponsible;
	}

	public void setFormResponsible(List<FormModel> formResponsible) {
		FormResponsible = formResponsible;
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	public String getNameSector() {
		return nameSector;
	}

	public void setNameSector(String nameSector) {
		this.nameSector = nameSector;
	}



	
}
