package base.metier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Panne {
	private int id;
	private String nom;
	private String type_machine;
	private String type_panne;
	private Date date;
	private boolean resolu;

	public Panne(int id, String nom, String type_machine, String type_panne, Date date, boolean resolu) {
		this.id = id;
		this.nom = nom;
		this.type_machine = type_machine;
		this.type_panne = type_panne;
		this.date = date;
		this.resolu = resolu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType_machine() {
		return type_machine;
	}

	public void setType_machine(String type_machine) {
		this.type_machine = type_machine;
	}

	public String getType_panne() {
		return type_panne;
	}

	public void setType_panne(String type_panne) {
		this.type_panne = type_panne;
	}

	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");

		return df.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isResolu() {
		return resolu;
	}

	public void setResolu(boolean resolu) {
		this.resolu = resolu;
	}

}
