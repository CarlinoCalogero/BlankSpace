package it.univaq.disim.oop.blankspace.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class GestoreLuogoDiRitrovo extends Persona {

	private String nomeLuogoDiRitrovo;
	private String indirizzoLuogoDiRitrovo;
	private Set<Ordine> ordini = new HashSet<>();

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

	public Set<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(Set<Ordine> ordini) {
		this.ordini = ordini;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof GestoreLuogoDiRitrovo))
			return false;
		GestoreLuogoDiRitrovo glr = (GestoreLuogoDiRitrovo) obj;
		return this.cognome.equalsIgnoreCase(glr.cognome) && this.nome.equalsIgnoreCase(glr.nome)
				&& this.dataNascita.equals(glr.dataNascita) && this.email.equalsIgnoreCase(glr.email)
				&& this.telefono.equals(glr.telefono) && this.password.equals(glr.password)
				&& this.nomeLuogoDiRitrovo.equalsIgnoreCase(glr.nomeLuogoDiRitrovo)
				&& this.indirizzoLuogoDiRitrovo.equalsIgnoreCase(glr.indirizzoLuogoDiRitrovo);
	}

	@Override
	public String toString() {
		return super.toString() + "," + this.nomeLuogoDiRitrovo + "," + this.indirizzoLuogoDiRitrovo;
	}

}
