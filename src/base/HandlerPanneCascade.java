/**
 * 
 */
package base;

import java.util.Random;

import base.metier.PanneDAO;
import base.metier.PanneDAOImpl;

/**
 * @author cdescamp
 *
 */
public class HandlerPanneCascade extends Thread {
	// Vars de 'instance des parametres du thread
	private int nbPannes;
	private int delaiSecondes;
	
	// Stockage des valeurs utiles pour ne les creer qu'une fois
	private PanneDAO p = new PanneDAOImpl();
	private Random rd= new Random(System.currentTimeMillis());
	private String[] tabTypeMachine = new String[3];
	private String[] tabTypePanne = new String[3];
	
	
	/** Constructeur
	 * @param nbPannes explicit
	 * @param delaiSecondes explicit
	 */
	public HandlerPanneCascade(int nbPannes, int delaiSecondes){
		this.nbPannes=nbPannes;
		this.delaiSecondes=delaiSecondes;
		tabTypeMachine[0] = "routeur";
		tabTypeMachine[1] = "pare-feu";
		tabTypeMachine[2] = "serveur";
		tabTypePanne[0] = "réseau";
		tabTypePanne[1] = "crash disque";
		tabTypePanne[2] = "problème mémoire";
	}
	
	/* Routine de création de pannes
	 */
	public void run(){
		int i;
		for(i=0;i<nbPannes;i++){
			genererPanne();
			try {
				Thread.sleep(1000*delaiSecondes);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** Genere une panne aléatoire
	 */
	private void genererPanne() {
		String nom = Long.toHexString(rd.nextLong());
		String typeMachine = tabTypeMachine[rd.nextInt(3)];
		String typePanne = (typeMachine != "serveur")?tabTypePanne[0]:tabTypePanne[rd.nextInt(3)];
		p.insert(nom, typeMachine, typePanne);
	}
		
		
}
