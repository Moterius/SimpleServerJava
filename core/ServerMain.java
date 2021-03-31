package core;

public class ServerMain {

	//The only purpose of this class is to start the server.
	//It mainly exists to make organizing projects easier
	//It also helps finding the file where the program starts.
	
	public static void main(String[] args) {
		ServerLogic l = new ServerLogic();
		l.run();
	}

}
