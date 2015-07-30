package uv.es.sparrow.tokenauth;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

public final class TokenAuth {

	private String usr="usuario03";
	private String clave="pass";
	private static TokenAuth auth=null;
	private final Map<String,String> listaTokens=new HashMap();
	
	private TokenAuth(){
		
	}
	
	public static TokenAuth getInstance(){
		if (auth==null){
			auth=new TokenAuth();
		}
		return auth;
	}
	
	public String login(String usr,String clave) throws LoginException{
		//if ((usr.equals(this.usr))&&(clave.equals(this.clave)))
		{
			String token="aaaaa";
			listaTokens.put(token, usr);
			return token;
		}
		//throw new LoginException("Datos incorrectos!!!");
	}
	
	public boolean tokenValido(String token){
		if (listaTokens.containsKey(token)){
			if (listaTokens.get(token).equals(usr)){
				return true;
			}
		}
		return false;
	}
}
