package es.uv.sparrow.bo;

import java.util.List;

import javax.ejb.Remote;
import javax.xml.bind.annotation.XmlRootElement;

import uv.es.bd.sparrow.entity.User;

@Remote

public interface UserBoRemote {
	List<User> listaUsuarios();
	public User buscaUsuario(String username);
	public boolean validaUsuario(String nombre,String pass);
	public void addUser(User nuevo);
	public String recuperaPassword(String nombre,String email);
	public void editUser(User editado);
	public List<User> buscaApellidos(String apellidos);
	public User buscaUsuarioId(String id);
}
