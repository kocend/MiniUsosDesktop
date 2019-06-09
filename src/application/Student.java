package application;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imie;
	private String nazwisko;
	private Integer numerUSOS;
	private Double ocenaKoncowa;
	private Integer punktyKolokwiumI;
	private Integer punktyKolokwiumII;
	private Integer sumaPunktow;
	
	public Student(String imie, String nazwisko, Integer numerUsos) {
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.numerUSOS=numerUsos;
		punktyKolokwiumI=0;
		punktyKolokwiumII=0;
		przeliczPunkty();
	}
	
	private void przeliczPunkty() {
		sumaPunktow=punktyKolokwiumI+punktyKolokwiumII;
		
		if(sumaPunktow<50)
			ocenaKoncowa=2.0;
		else if(sumaPunktow<=60)
			ocenaKoncowa=3.0;
		else if(sumaPunktow<=70)
			ocenaKoncowa=3.5;
		else if(sumaPunktow<=80)
			ocenaKoncowa=4.0;
		else if(sumaPunktow<=90)
			ocenaKoncowa=4.5;
		else if(sumaPunktow<=100)
			ocenaKoncowa=5.0;
	}
	
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public Integer getNumerUSOS() {
		return numerUSOS;
	}
	public void setNumerUSOS(Integer numerUSOS) {
		this.numerUSOS = numerUSOS;
	}
	public Double getOcenaKoncowa() {
		return ocenaKoncowa;
	}
	public Integer getPunktyKolokwiumI() {
		return punktyKolokwiumI;
	}
	public void setPunktyKolokwiumI(Integer punktyKolokwiumI) {
		if(punktyKolokwiumI>=0&&punktyKolokwiumI<=50) {
			this.punktyKolokwiumI = punktyKolokwiumI;
			przeliczPunkty();
		}
	}
	public Integer getPunktyKolokwiumII() {
		return punktyKolokwiumII;
	}
	public void setPunktyKolokwiumII(Integer punktyKolokwiumII) {
		if(punktyKolokwiumII>=0&&punktyKolokwiumII<=50) {
			this.punktyKolokwiumII = punktyKolokwiumII;
			przeliczPunkty();
		}
	}
	public Integer getSumaPunktow() {
		return sumaPunktow;
	}

	@Override
	public String toString() {
		return imie + " " + nazwisko + " " + numerUSOS;
	}
}