package it.univaq.disim.oop.blankspace.domain;

import java.time.LocalDate;

public class GestoreLuogoDiRitrovo extends Persona {
	private String nomeLuogoDiRitrovo;
	private String indirizzoLuogoDiRitrovo;

	public GestoreLuogoDiRitrovo(String nome, String cognome, LocalDate dataNascita, String email, String telefono,
			String password, String nomeLuogoDiRitrovo, String indirizzoLuogoDiRitrovo) {
		super(nome, cognome, dataNascita, email, telefono, password);
		this.nomeLuogoDiRitrovo = nomeLuogoDiRitrovo;
		this.indirizzoLuogoDiRitrovo = indirizzoLuogoDiRitrovo;
	}

	public String getNomeLuogoDiRitrovo() {
		return nomeLuogoDiRitrovo;
	}

	public void setNomeLuogoDiRitrovo(String nomeLuogoDiRitrovo) {
		this.nomeLuogoDiRitrovo = nomeLuogoDiRitrovo;
	}

	public String getIndirizzoLuogoDiRitrovo() {
		return indirizzoLuogoDiRitrovo;
	}

	public void setIndirizzoLuogoDiRitrovo(String indirizzoLuogoDiRitrovo) {
		this.indirizzoLuogoDiRitrovo = indirizzoLuogoDiRitrovo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof GestoreLuogoDiRitrovo))
			return false;
		GestoreLuogoDiRitrovo glr = (GestoreLuogoDiRitrovo) obj;
		return super.equals(glr) && this.nomeLuogoDiRitrovo.equalsIgnoreCase(glr.nomeLuogoDiRitrovo)
				&& this.indirizzoLuogoDiRitrovo.equalsIgnoreCase(glr.indirizzoLuogoDiRitrovo);
	}
	@Override
	public String toString() {
		return super.toString()+","+this.nomeLuogoDiRitrovo+","+this.indirizzoLuogoDiRitrovo;
	}
}
