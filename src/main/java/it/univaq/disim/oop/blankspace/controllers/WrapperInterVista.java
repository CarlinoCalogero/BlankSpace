package it.univaq.disim.oop.blankspace.controllers;

public class WrapperInterVista<Dato1,Dato2> {
	private Dato1 dato1;
	private Dato2 dato2;
	
	public WrapperInterVista(Dato1 d1, Dato2 d2) {
		this.dato1 = d1;
		this.dato2 = d2;
	}

	public Dato1 getDato1() {
		return dato1;
	}

	public void setDato1(Dato1 dato1) {
		this.dato1 = dato1;
	}

	public Dato2 getDato2() {
		return dato2;
	}

	public void setDato2(Dato2 dato2) {
		this.dato2 = dato2;
	}


}
