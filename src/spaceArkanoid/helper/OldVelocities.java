package spaceArkanoid.helper;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Helper class to keep track of old velocities of an object
 * @author David Fain
 *
 */
public class OldVelocities {
	private HashMap<Long, int[]> deltas = new HashMap<Long, int[]>();
	
	public OldVelocities() {
		
	}
	
	public void addVelocity(int dx, int dy, long time) {
		deltas.put(time, new int[]{dx, dy});
	}
	
	public Set<Entry<Long, int[]>> getVelocities() {
		return deltas.entrySet();
	}
	
	public void removeVelocity(long time) {
		deltas.remove(time);
	}
	
	
	
}
