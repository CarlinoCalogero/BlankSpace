package it.univaq.disim.oop.blankspace.controllers;

public class WrapperInterVista<Dato1,Dato2,Dato3,Dato4> {
	private Dato1 dato1;
	private Dato2 dato2;
	private Dato3 dato3;
	private Dato4 dato4;
	public WrapperInterVista(Dato1 d1, Dato2 d2,Dato3 d3,Dato4 d4) {
		this.dato1 = d1;
		this.dato2 = d2;
		this.dato3 = d3;
		this.dato4=d4;
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
	public Dato3 getDato3() {
		return dato3;
	}

	public void setDato3(Dato3 dato3) {
		this.dato3 = dato3;
	}

	public Dato4 getDato4() {
		return dato4;
	}

	public void setDato4(Dato4 dato4) {
		this.dato4 = dato4;
	}

}
