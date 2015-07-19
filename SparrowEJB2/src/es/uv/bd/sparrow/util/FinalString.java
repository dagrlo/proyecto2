package es.uv.bd.sparrow.util;

public class FinalString {
	public static String arregla(String s){
		String n;
		n=s.trim().toUpperCase();
		n=n.replaceAll("( )+", " ");
	    return n;
	}
}
