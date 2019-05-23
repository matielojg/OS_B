package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import DAO.EntidadeBase;

@Entity
@Table(name = "tb_user")
@XmlRootElement
public class UserModel implements EntidadeBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private long id;

	@Column
	private String nome;

	@Column
	private String login;

	@Column
	private String senha;

	@Column
	private String email;

	@Column
	private String foto;

	@Column
	private String cpf;

	@Column
	private String contact;

	@JsonIgnoreProperties("user") // quando é RESTFul precisa desta annotation
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<ActionModel> action;// correto professor

	@JsonIgnoreProperties("userResponsible") // quando é RESTFul precisa desta annotation
	@OneToMany(mappedBy = "userResponsible", fetch = FetchType.LAZY)
	private List<FormModel> formResponsible;// correto

	@JsonIgnoreProperties("userRequester") // quando é RESTFul precisa desta annotation
	@OneToMany(mappedBy = "userRequester", fetch = FetchType.LAZY)
	private List<FormModel> formRequester;// correto

	@JsonIgnoreProperties("users")
	@ManyToOne
	@JoinColumn(name = "function_id", nullable = false) // an
	private FunctionModel function;

	@JsonIgnoreProperties("users")
	@ManyToOne
	@JoinColumn(name = "sector_id", nullable = true)
	private SectorModel sector;

	public SectorModel getSector() {
		return sector;
	}

	public void setSector(SectorModel sector) {
		this.sector = sector;
	}

	public FunctionModel getFunction() {
		return function;
	}

	public void setFunction(FunctionModel function) {
		this.function = function;
	}

	public void setFormResponsible(List<FormModel> formResponsible) {
		this.formResponsible = formResponsible;
	}

	public void setFormRequester(List<FormModel> formRequester) {
		this.formRequester = formRequester;
	}

	public List<ActionModel> getAction() {
		return action;
	}

	public List<FormModel> getFormResponsible() {
		return formResponsible;
	}

	public void setFormsResponsible(List<FormModel> formResponsible) {
		this.formResponsible = formResponsible;
	}

	public List<FormModel> getFormRequester() {
		return formRequester;
	}

	public void setFormsRequester(List<FormModel> formRequester) {
		this.formRequester = formRequester;
	}

	public void setAction(List<ActionModel> action) {

		this.action = action;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}
