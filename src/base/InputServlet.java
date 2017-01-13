package base;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import base.metier.Panne;
import base.metier.PanneDAO;
import base.metier.PanneDAOImpl;

public class InputServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String inputMode = request.getParameter("action");
		if(inputMode!=null){ // On exclut le cas ou l'on accede a la page pour la premiere fois
			PanneDAO p = new PanneDAOImpl();
			if(inputMode.equals("saisie")){		// Si le formulaire nous envoie une saisie
												// On forward les arguments a la DAO 
												// On ne verifie pas les arguments car en utilisation normale, le formulaire filtre les interdictions
				String nomMachine = request.getParameter("nom");
				String typeMachine = request.getParameter("typeMachine");
				String typePanne = request.getParameter("typePanne");
				if(typePanne.equals("reseau")){
					typePanne="réseau";
				}
				if(		nomMachine!=null && typeMachine!=null && typePanne!=null &&
						(typeMachine.equals("routeur") || typeMachine.equals("serveur") || typeMachine.equals("pare-feu")) && 
						(typePanne.equals("réseau") || typePanne.equals("crash disque") || typePanne.equals("problème-mémoire"))
					) p.insert(nomMachine, typeMachine, typePanne);
				
				
			}else if(inputMode.equals("aleatoire")){	// Si le formulaire nous demande des insertions aléatoires
														// on recupere le nombre demandé et on genere les pannes dans la bdd
				int nbPannes = Integer.parseInt(request.getParameter("qteRandom"));
				int delaiPannes;
				try{
					delaiPannes = Integer.parseInt(request.getParameter("delaiRandom"));
				}catch(java.lang.NumberFormatException e){
					delaiPannes=0;
				}
				
				HandlerPanneCascade hpc = new HandlerPanneCascade(nbPannes, delaiPannes);
				hpc.start();
				try {
					Thread.sleep(3*nbPannes+1); // Ce serais dommage de pas avoir l'animation d'attente alors autant simuler le delai
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		
		
		String pageName = "/input.jsp";
		javax.servlet.RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doProcess(request, response);
	}

}