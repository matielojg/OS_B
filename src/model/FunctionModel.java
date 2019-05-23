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
@Table(name = "tb_function")
public class FunctionModel implements EntidadeBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_function")
	private long id;
	@Column
	private String nameFunction;

	@JsonIgnoreProperties("function")
	@OneToMany(mappedBy = "function", fetch = FetchType.LAZY)
	private List<UserModel> users;

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameFunction() {
		return nameFunction;
	}

	public void setNameFunction(String nameFunction) {
		this.nameFunction = nameFunction;
	}

}
