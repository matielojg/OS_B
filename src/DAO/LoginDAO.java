package DAO;

import javax.persistence.Query;

import model.UserModel;

@SuppressWarnings("rawtypes")
public class LoginDAO extends DAO {

	public UserModel findByLoginAndSenha(String login, String senha) {
		Object retorno = null;
		try {
			manager.clear(); // limpeza de cache de queries
			Query query = manager.createQuery("Select c from UserModel c WHERE c.login = :login and UPPER(c.senha) = UPPER(MD5(:senha))");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			query.setMaxResults(1);
			retorno = query.getSingleResult();
			if (retorno != null) {
				return (UserModel) retorno; 
				}
			else 
				return null;
			
			

		} catch (Exception ex) {
			return null;
		}
	}
	
	
	

}
