package application;

import java.io.Serializable;
import java.util.LinkedList;

public class Grupa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numer;
	private DZIEN_TYGODNIA dzienTygodnia;
	private String godzinaRozpoczecia;
	private String godzinaZakonczenia;
	private LinkedList<Student> listaStudentow;

	public Grupa(Integer numer, DZIEN_TYGODNIA dzienTygodnia, String godzinaRozpoczecia, String godzinaZakonczenia) {
		super();
		this.numer = numer;
		this.dzienTygodnia = dzienTygodnia;
		this.godzinaRozpoczecia = godzinaRozpoczecia;
		this.godzinaZakonczenia = godzinaZakonczenia;
		listaStudentow = new LinkedList<>();
	}
	
	public Integer getNumer() {
		return numer;
	}
	
	public void setNumer(Integer numer) {
		this.numer = numer;
	}
	
	public DZIEN_TYGODNIA getDzienTygodnia() {
		return dzienTygodnia;
	}

	public void setDzienTygodnia(DZIEN_TYGODNIA dzienTygodnia) {
		this.dzienTygodnia = dzienTygodnia;
	}

	public String getGodzinaRozpoczecia() {
		return godzinaRozpoczecia;
	}

	public void setGodzinaRozpoczecia(String godzinaRozpoczecia) {
		this.godzinaRozpoczecia = godzinaRozpoczecia;
	}

	public String getGodzinaZakonczenia() {
		return godzinaZakonczenia;
	}

	public void setGodzinaZakonczenia(String godzinaZakonczenia) {
		this.godzinaZakonczenia = godzinaZakonczenia;
	}
	
	public LinkedList<Student> getListaStudentow() {
		return listaStudentow;
	}

	@Override
	public String toString() {
		return "Grupa " + numer + " " + dzienTygodnia.toString()+" "+godzinaRozpoczecia+"-"+godzinaZakonczenia;
	}
}
