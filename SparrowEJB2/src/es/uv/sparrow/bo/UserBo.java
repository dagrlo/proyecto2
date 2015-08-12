package es.uv.sparrow.bo;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.annotation.XmlElement;

import uv.es.bd.sparrow.dao.UserDao;
import uv.es.bd.sparrow.entity.User;
import es.uv.bd.sparrow.util.FinalString;

/**
 * Session Bean implementation class UserBo
 */
@Stateless
@LocalBean
public class UserBo implements UserBoRemote {
	@PersistenceContext(name = "sparrowPersistence")
	private EntityManager em;
	private UserDao udao;

	/**
	 * Default constructor.
	 */
	public UserBo() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		udao = new UserDao(em);
	}

	@PreDestroy
	public void finaliza() {
		em.close();
	}

	
	@Override
	public List<User> listaUsuarios() {
		List<User> usuarios = udao.getAllUsers();
		return usuarios;
	}

	@Override
	public User buscaUsuario(String username) {
		username = FinalString.arregla(username);
		return udao.findUserByUsername(username);
	}

	@Override
	public User buscaUsuarioId(String id) {

		return udao.findById(Integer.parseInt(id));
	}

	@Override
	public boolean validaUsuario(String nombre, String pass) {
		boolean result = false;
		try {
			User usuario = udao.findUserByUsername(nombre);
			if ((usuario.getUsername().equals(nombre))
					&& (usuario.getPassword().equals(pass))) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}

		return result;
	}

	@Override
	public void addUser(User nuevo) {
		System.out.println("=>"+nuevo.getEMail());
		udao.persist(nuevo);
	}

	@Override
	public String recuperaPassword(String nombre, String email) {
		User usuario = null;
		try {
			usuario = udao.findUserByUsername(nombre);
		} catch (Exception e) {

		}

		String result = "El nombre de usuario o el email son incorrectos";
		if (usuario != null) {
			if ((usuario.getUsername().equals(nombre))
					&& (usuario.getEMail().equals(email))) {
				result = usuario.getPasswordString();
			}
		}
		
		return result;
	}

	@Override
	public void editUser(User editado) {
		udao.update(editado);

	}

	@Override
	public List<User> buscaApellidos(String apellidos) {
		List<User> lista = udao.findByApellidos(apellidos);

		return lista;
	}
}
