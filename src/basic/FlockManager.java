package basic;
import java.util.ArrayList;

public class FlockManager {

	
	private ArrayList<Flock> flocks;
	
	public FlockManager() {
		flocks = new ArrayList<>();
	}
	
	public void add(Flock flock) {
		flocks.add(flock);
	}

	public void update() {
		for (Flock flock : flocks) {
			flock.update();
		}
	}
}
