package core;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketConnector extends Thread{

	ServerSocket sSocket = null;
	ServerSocketHashMap sMap = null;
	
	@Override
	public void run() {
		System.out.print("SocketConnector.run()");//Test.
		while(!this.isInterrupted()) {
			try {
				ClientSocket s = new ClientSocket();
				s.client = s.waitForConnect(null);
				//This is to prevent spawning of infinite ClientSockets, as putting this into
				//ClientSocket.run() would do
				sMap.put(s.id, s);
			}
			catch(IOException e) {
				//Do nothing
			}
			catch(Exception e) {
				e.printStackTrace();
				System.exit(-1);
				//Unexpected exception error
			}
		}
	}
	
	public SocketConnector(ServerSocket sSocket, ServerSocketHashMap sMap) {
		this.sSocket = sSocket;
		this.sMap = sMap;
		this.setDaemon(true);
		//Setting this DURING RUNTIME is not possible.
		//It results in a rare exception that gets printed
		//but does not crash the program.
		//Threat: WARN.
	}
	
}
