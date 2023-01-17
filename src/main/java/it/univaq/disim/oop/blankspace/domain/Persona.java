package it.univaq.disim.oop.blankspace.domain;

import java.time.LocalDate;

public class Persona implements Comparable<Persona>{
	protected String nome;
	protected String cognome;
	protected LocalDate dataNascita;
	protected String email;
	protected String telefono;
	protected String password;
	
	public Persona(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	public Persona(String nome, String cognome, LocalDate dataNascita, String email, String telefono, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
		this.telefono = telefono;
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public int compareTo(Persona o) {
		if(this.cognome.compareTo(o.cognome)==0)
			return this.nome.compareTo(o.nome);
		return this.cognome.compareTo(o.cognome);
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Persona))
			return false;
		Persona p = (Persona)obj;
		return this.cognome.equalsIgnoreCase(p.cognome) && this.nome.equalsIgnoreCase(p.nome) && this.dataNascita.equals(p.dataNascita) && this.email.equalsIgnoreCase(p.email) && this.telefono.equals(p.telefono)&& this.password.equals(p.password);
	}
	@Override
	public String toString() {
		return this.nome+","+this.cognome+","+this.email+","+dataNascita.toString()+","+telefono;
	}
}
