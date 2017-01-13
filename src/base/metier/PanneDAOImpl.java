package base.metier;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.DBManager;

public class PanneDAOImpl implements PanneDAO {

		/* Methode d'insertion dans la base des pannes
		 * args : explicit
		 * return : true si l'insert a réussi
		 */
		public boolean insert(String nomMachine, String typeMachine, String typePanne){
			boolean res=false;	// Buffer pour indiquer a l'appelant l'echec
			java.sql.Connection connect = DBManager.getInstance().getConnection();
			try {
				java.sql.Statement stat = connect.createStatement();
				// On s'assure que la machine n'a pas deja une panne
				java.sql.ResultSet rs = stat.executeQuery("select count(*) from table_panne where Traite=false AND nom_machine=\""+nomMachine+"\";");
				rs.next();
				if(rs.getInt("count(*)")==0){
					rs = stat.executeQuery("select max(id) from table_panne;");
					rs.next();
					int nouvelId = rs.getInt("max(id)")+1; 	// On genere un nouvel id dont on est sur de l'unicité
					stat.executeUpdate("insert into table_panne (id, nom_machine, type_machine, type_panne, date_panne, Traite) values ("+nouvelId+", \""+nomMachine+"\", \""+typeMachine+"\", \""+typePanne+"\", NOW(), false)");
					res = (findNumberById(nouvelId)>0); // On regarde si l'insert a reussi
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(connect!=null){	// On referme la connection
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return res;
			
		}
		
	@Override
	public List<Panne> findByAll(boolean order, String type, boolean asc) {
		List<Panne> res = new ArrayList<Panne>();
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		String sasc;
		if(asc){
			sasc=" ASC";
		}
		else{
			sasc=" DESC";
		}
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs;
			if (order) {
				rs = stat.executeQuery("select * from table_panne order by "+type+sasc);
			} else {
				rs = stat.executeQuery("select * from table_panne;");
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom_machine");
				String type_machine = rs.getString("type_machine");
				String type_panne = rs.getString("type_panne");
				Timestamp timestamp = rs.getTimestamp("date_panne");
				Date date = new java.util.Date(timestamp.getTime());
				boolean traite = rs.getBoolean("Traite");
				res.add(new Panne(id, nom, type_machine, type_panne, date, traite));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public List<Panne> findByLastMinute(boolean order, String type, boolean asc) {
		List<Panne> res = new ArrayList<Panne>();
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		String sasc;
		if(asc){
			sasc=" ASC";
		}
		else{
			sasc=" DESC";
		}
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs;
			if (order) {
				rs = stat.executeQuery(
						"select * from table_panne where DATE_ADD(date_panne, INTERVAL 1 MINUTE) >= NOW() order by "+type+sasc);
			} else {
				rs = stat.executeQuery(
						"select * from table_panne where DATE_ADD(date_panne, INTERVAL 1 MINUTE) >= NOW();");
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom_machine");
				String type_machine = rs.getString("type_machine");
				String type_panne = rs.getString("type_panne");
				Timestamp timestamp = rs.getTimestamp("date_panne");
				Date date = new java.util.Date(timestamp.getTime());
				boolean traite = rs.getBoolean("Traite");
				res.add(new Panne(id, nom, type_machine, type_panne, date, traite));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public List<Panne> findByLastHour(boolean order, String type, boolean asc) {
		List<Panne> res = new ArrayList<Panne>();
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		String sasc;
		if(asc){
			sasc=" ASC";
		}
		else{
			sasc=" DESC";
		}
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs;
			if (order) {
				rs = stat.executeQuery(
						"select * from table_panne where DATE_ADD(date_panne, INTERVAL 1 HOUR) >= NOW() order by "+type+sasc);
			} else {
				rs = stat.executeQuery(
						"select * from table_panne where DATE_ADD(date_panne, INTERVAL 1 HOUR) >= NOW();");
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom_machine");
				String type_machine = rs.getString("type_machine");
				String type_panne = rs.getString("type_panne");
				Timestamp timestamp = rs.getTimestamp("date_panne");
				Date date = new java.util.Date(timestamp.getTime());
				boolean traite = rs.getBoolean("Traite");
				res.add(new Panne(id, nom, type_machine, type_panne, date, traite));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public List<Panne> findByLastDay(boolean order, String type, boolean asc) {
		List<Panne> res = new ArrayList<Panne>();
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		String sasc;
		if(asc){
			sasc=" ASC";
		}
		else{
			sasc=" DESC";
		}
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs;
			if (order) {
				rs = stat.executeQuery(
						"select * from table_panne where DATE_ADD(date_panne, INTERVAL 1 DAY) >= NOW() order by "+type+sasc);
			} else {
				rs = stat
						.executeQuery("select * from table_panne where DATE_ADD(date_panne, INTERVAL 1 DAY) >= NOW();");
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom_machine");
				String type_machine = rs.getString("type_machine");
				String type_panne = rs.getString("type_panne");
				Timestamp timestamp = rs.getTimestamp("date_panne");
				Date date = new java.util.Date(timestamp.getTime());
				boolean traite = rs.getBoolean("Traite");
				res.add(new Panne(id, nom, type_machine, type_panne, date, traite));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public List<Panne> findByLastMonth(boolean order, String type, boolean asc) {
		List<Panne> res = new ArrayList<Panne>();
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		String sasc;
		if(asc){
			sasc=" ASC";
		}
		else{
			sasc=" DESC";
		}
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs;
			if (order) {
				rs = stat.executeQuery(
						"select * from table_panne where DATE_ADD(date_panne, INTERVAL 1 MONTH) >= NOW() order by "+type+sasc);
			} else {
				rs = stat.executeQuery(
						"select * from table_panne where DATE_ADD(date_panne, INTERVAL 1 MONTH) >= NOW();");
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom_machine");
				String type_machine = rs.getString("type_machine");
				String type_panne = rs.getString("type_panne");
				Timestamp timestamp = rs.getTimestamp("date_panne");
				Date date = new java.util.Date(timestamp.getTime());
				boolean traite = rs.getBoolean("Traite");
				res.add(new Panne(id, nom, type_machine, type_panne, date, traite));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int findNumberByAll() {
		int res = 0;
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs = stat.executeQuery("select count(*) from table_panne;");
			rs.next();
			res = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int findNumberByLastMinute() {
		int res = 0;
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs = stat.executeQuery(
					"select count(*) from table_panne where DATE_ADD(date_panne, INTERVAL 1 MINUTE) >= NOW();");
			rs.next();
			res = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int findNumberByLastHour() {
		int res = 0;
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs = stat.executeQuery(
					"select count(*) from table_panne where DATE_ADD(date_panne, INTERVAL 1 HOUR) >= NOW();");
			rs.next();
			res = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int findNumberByLastDay() {
		int res = 0;
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs = stat.executeQuery(
					"select count(*) from table_panne where DATE_ADD(date_panne, INTERVAL 1 DAY) >= NOW();");
			rs.next();
			res = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int findNumberByLastMonth() {
		int res = 0;
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs = stat.executeQuery(
					"select count(*) from table_panne where DATE_ADD(date_panne, INTERVAL 1 MONTH) >= NOW();");
			rs.next();
			res = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public void resolv(int id) {
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		try {
			java.sql.Statement stat = connect.createStatement();
			stat.executeUpdate("update table_panne set Traite = true where id = "+id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public int findNumberByDate(String date) {
		int res = 0;
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs = stat.executeQuery(
					"select count(*) from table_panne where DATE_ADD(date_panne, INTERVAL 1 HOUR) >= '"+date+"' && date_panne <= '"+date+"';");
			rs.next();
			res = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	

	/* Methode pour savoir combien d'elements de la table sont d'un id donné afin que la methode insert puisse verifier le succes
	 * args: id recherché
	 * return: le nombre de pannes ayant cet id,  normalement soit 1 soit 0 vu que id est PK
	 */
	@Override
	public int findNumberById(int id) {
		int res = 0;
		java.sql.Connection connect = DBManager.getInstance().getConnection();
		try {
			java.sql.Statement stat = connect.createStatement();
			java.sql.ResultSet rs = stat.executeQuery(
					"select * from table_panne where id="+id+";");
			while(rs.next()) res++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(connect!=null){
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}
