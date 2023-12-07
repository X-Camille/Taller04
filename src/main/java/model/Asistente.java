package model;

public class Asistente {

	private Entrada entrada;
	private String contacto;
	private String preferenciaMusical;

	public Asistente(String contacto, String preferenciaMusical){
		this.contacto = contacto;
		this.preferenciaMusical = preferenciaMusical;
	}

	public String getContacto(){
		return contacto;
	}

	public String getPreferenciaMusical(){
		return preferenciaMusical;
	}

	public String toString(){
		return getContacto() + "/" + getPreferenciaMusical() + "/";
	}
}