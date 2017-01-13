package base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import base.metier.PanneDAO;
import base.metier.PanneDAOImpl;

public class MonitoringServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Pas trop de secret pour cette fonction, on récupère le nombre de panne et on l'envoie à la jsp
	// Le code commenté correspondait à l'implémentation avant l'AJAX
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
//		String typeSearch = request.getParameter("type");
//		String repPanne = request.getParameter("panne");
		PanneDAO p = new PanneDAOImpl();
	/*	List<Panne> listPanne = null;
		if(typeSearch!=null){
			if(typeSearch.equals("all")){
				listPanne = p.findByAll(false);
			}
			if(typeSearch.equals("allorder")){
				listPanne = p.findByAll(true);
			}
			if(typeSearch.equals("month")){
				listPanne = p.findByLastMonth(false);
			}
			if(typeSearch.equals("monthorder")){
				listPanne = p.findByLastMonth(true);
			}
			if(typeSearch.equals("day")){
				listPanne = p.findByLastDay(false);
			}
			if(typeSearch.equals("dayorder")){
				listPanne = p.findByLastDay(true);
			}
			if(typeSearch.equals("hour")){
				listPanne = p.findByLastHour(false);
			}
			if(typeSearch.equals("hourorder")){
				listPanne = p.findByLastHour(true);
			}
			if(typeSearch.equals("minute")){
				listPanne = p.findByLastMinute(false);
			}
			if(typeSearch.equals("minuteorder")){
				listPanne = p.findByLastMinute(true);
			}
		} */
		int PanneMinute = p.findNumberByLastMinute();
		int PanneHeure = p.findNumberByLastHour();
		int PanneJour = p.findNumberByLastDay();
		int PanneMois = p.findNumberByLastMonth();
		request.setAttribute("PanneMinute", PanneMinute);
		request.setAttribute("PanneHeure", PanneHeure);
		request.setAttribute("PanneJour", PanneJour);
		request.setAttribute("PanneMois", PanneMois);
		String pageName = "/monitoring.jsp";
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
