package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map.Entry;
import java.util.UUID;

public class ServerLogic extends Thread{
	int port = 12121;
	ServerSocketHashMap sMap = null;
	
	ServerSocket sSocket = null;
	
	@Override
	public void run() {
		try {
			sSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sMap = new ServerSocketHashMap();
		SocketConnector c = new SocketConnector(sSocket,sMap);
		c.run();
		
		while(!isInterrupted()) {
			for(Entry<UUID, ClientSocket> s : sMap.socketmap.entrySet()) {
				ClientSocket t = s.getValue();
				if(t.flagged()) {
					//Put here what you want to do with the message recieved in ClientSocket.
					//The entire server works on the assumption the SERVER works FASTER than the clients!
					//It MAY be possible to split up every flagged client into a seperate Thread.
				}
			}
		}
	}
	
}
