package base;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.metier.Panne;
import base.metier.PanneDAO;
import base.metier.PanneDAOImpl;

public class AjaxServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// On récupère le json que l'ajax nous envoie, et en fonction de ça on effectue des actions
	// sur la base de données et on renvoie la réponse correspondante
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PanneDAO p = new PanneDAOImpl();
	    String rq = request.getParameter("date");
	    String repPanne = request.getParameter("upd");
	    String typeSearch = request.getParameter("type");
	    String orderT = request.getParameter("order");
	    String asc = request.getParameter("asc");
	    boolean ascT = true;
	    if(asc!=null && asc.equals("asc")){
	    	ascT=true;
	    }
	    else{
	    	ascT=false;
	    }
	    boolean order = true;
	    if (orderT !=null && orderT.equals("not")){
	    	order = false;
	    }
		if(repPanne!=null){
			p.resolv(Integer.parseInt(repPanne));
		}
	    if(rq!=null){
			String ans ="";
		    for(int i=0;i<24;i++){
		    	ans=ans+p.findNumberByDate(rq+i+":00:00")+"/";
		    }
		    response.getWriter().write(ans);  
	    }
	    if(typeSearch!=null){
			List<Panne> listPanne = null;
			if(typeSearch.equals("all")){
				listPanne = p.findByAll(order, orderT,ascT);
			}
			if(typeSearch.equals("month")){
				listPanne = p.findByLastMonth(order, orderT,ascT);
			}
			if(typeSearch.equals("day")){
				listPanne = p.findByLastDay(order, orderT,ascT);
			}
			if(typeSearch.equals("hour")){
				listPanne = p.findByLastHour(order, orderT,ascT);
			}
			if(typeSearch.equals("minute")){
				listPanne = p.findByLastMinute(order, orderT,ascT);
			}
			String res=" ";
			if(listPanne!=null && !listPanne.isEmpty()){
				res="<table><tr><th class=\"order\" id=\"nom_machine\">Nom de la machine</th><td>&nbsp;</td>" +
						"<th class=\"order\" id=\"type_machine\">Type de la machine</th><td>&nbsp;</td><th class=\"order\" id=\"type_panne\">Type de la panne</th><td>&nbsp;</td>" +
						"<th class=\"order\" id=\"date_panne\">Date de la panne</th><td>&nbsp;</td><th class=\"order\" id=\"Traite\">Panne résolue ?</th></tr>";
				for(Panne panne:listPanne){
					res=res+"<tr><td>"+panne.getNom()+"</td><td>&nbsp;</td><td>"+panne.getType_machine()+"</td><td>&nbsp;</td><td>"+panne.getType_panne()+"</td><td>&nbsp;</td><td>"+panne.getDate()+"</td><td>&nbsp;</td>";
					if(panne.isResolu()){
						res=res+"<td>Oui</td></tr>";
					}
					else{
						res=res+"<td><input class=\"cbox\" type=\"checkbox\" id=\""+panne.getId()+"\" value=\"checkbox1\"></td></tr>";
					}
				}
			}
			int PanneMinute = p.findNumberByLastMinute();
			int PanneHeure = p.findNumberByLastHour();
			int PanneJour = p.findNumberByLastDay();
			int PanneMois = p.findNumberByLastMonth();
			res=res+"|"+PanneMinute+"|"+PanneHeure+"|"+PanneJour+"|"+PanneMois;
	        response.setContentType("text/html; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(res);  
	    }

	}
}
