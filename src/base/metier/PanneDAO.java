package base.metier;
import java.util.List;

public interface PanneDAO {

	public boolean insert(String machine, String typeMachine, String typePanne);
	
	public List<Panne> findByAll(boolean order, String type, boolean asc);

	public List<Panne> findByLastMinute(boolean order, String type, boolean asc);

	public List<Panne> findByLastHour(boolean order, String type, boolean asc);

	public List<Panne> findByLastDay(boolean order, String type, boolean asc);

	public List<Panne> findByLastMonth(boolean order, String type, boolean asc);

	public int findNumberByAll();

	public int findNumberByLastMinute();

	public int findNumberByLastHour();

	public int findNumberByLastDay();

	public int findNumberByLastMonth();
	
	public void resolv(int id);
	
	public int findNumberByDate(String date);
	
	public int findNumberById(int id);
}
