package core;

import java.util.HashMap;
import java.util.UUID;

public class ServerSocketHashMap {
	HashMap<UUID, ClientSocket> socketmap = null;
	
	public void put(UUID id, ClientSocket s) {
		socketmap.put(id, s);
	}
	
	public ClientSocket get(UUID id) {
		return socketmap.get(id);
	}
	
	public void remove(UUID id) {
		socketmap.remove(id);
	}
	
	public HashMap<UUID, ClientSocket> all() {
		return socketmap;
	}
}
