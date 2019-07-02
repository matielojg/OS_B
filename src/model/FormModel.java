package model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tb_form")
public class FormModel implements EntidadeBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_form")
	private long id;

	@Column
	private Timestamp dateopen;

	@Column
	private String description;

	@Column
	private String reason;

	@Column
	private Timestamp dateclose;

	@Column
	private String foto;

	@JsonIgnoreProperties("responsible")
	@ManyToOne
	@JoinColumn(name = "responsible", nullable = true)
	private UserModel userResponsible;

	@JsonIgnoreProperties("requester")
	@ManyToOne
	@JoinColumn(name = "requester", nullable = false)
	private UserModel userRequester;

	@JsonIgnoreProperties("form")
	@OneToMany(mappedBy = "form")
	private List<ActionModel> actions;

	@JsonIgnoreProperties("FormProvider")
	@ManyToOne
	@JoinColumn(name = "sectorProvider", nullable = true) // os nomes de chaves estravinugeiras nunca podem se repetir
	private SectorModel sectorProvider;

	@JsonIgnoreProperties("FormResponsible")
	@ManyToOne
	@JoinColumn(name = "sectorRequester", nullable = false)
	private SectorModel sectorRequester;//

	@JsonIgnoreProperties("forms")
	@ManyToOne
	@JoinColumn(name = "service", nullable = false)
	private ServiceModel service;

	@JsonIgnoreProperties("forms")
	@ManyToOne
	@JoinColumn(name = "idpriority", nullable = false)
	private PriorityOSModel priority;

	@JsonIgnoreProperties("forms")
	@ManyToOne
	@JoinColumn(name = "idstatus", nullable = false)
	private StatusOSModel status;

	public StatusOSModel getStatus() {
		return status;
	}

	public void setStatus(StatusOSModel status) {
		this.status = status;
	}

	public ServiceModel getService() {
		return service;
	}

	public void setService(ServiceModel service) {
		this.service = service;
	}

	public PriorityOSModel getPriority() {
		return priority;
	}

	public void setPriority(PriorityOSModel priority) {
		this.priority = priority;
	}

	public SectorModel getSectorProvider() {
		return sectorProvider;
	}

	public void setSectorProvider(SectorModel sectorProvider) {
		this.sectorProvider = sectorProvider;
	}

	public SectorModel getSectorRequester() {
		return sectorRequester;
	}

	public void setSectorRequester(SectorModel sectorRequester) {
		this.sectorRequester = sectorRequester;
	}

	public UserModel getUserResponsible() {
		return userResponsible;
	}

	public void setUserResponsible(UserModel userResponsible) {
		this.userResponsible = userResponsible;
	}

	public UserModel getUserRequester() {
		return userRequester;
	}

	public void setUserRequester(UserModel userRequester) {
		this.userRequester = userRequester;
	}

	public List<ActionModel> getActions() {
		return actions;
	}

	public void setActions(List<ActionModel> actions) {
		this.actions = actions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDateopen() {
		return dateopen;
	}

	public void setDateopen(Timestamp dateopen) {
		this.dateopen = dateopen;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Timestamp getDateclose() {
		return dateclose;
	}

	public void setDateclose(Timestamp dateclose) {
		this.dateclose = dateclose;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
