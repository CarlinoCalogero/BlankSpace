package it.univaq.disim.oop.blankspace.business;

public abstract class BusinessFactory {
	private static BusinessFactory ramImpl = new RAMBusinessFactory();
	
	public static BusinessFactory getImplementation() {
		return ramImpl;
	}
	
	public abstract ServizioUtente getServizioUtente();
}
