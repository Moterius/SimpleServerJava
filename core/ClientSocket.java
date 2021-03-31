package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class ClientSocket extends Thread {
	
	Socket client = null;
	
	String message = null;
	boolean msg = false;
	
	
	UUID id = null;
	
	public ClientSocket() {
		id = UUID.randomUUID();
		this.setDaemon(true);
	}
	
	private boolean flag() {
		msg = true;
		try {wait();} catch (InterruptedException e) {e.printStackTrace();}
		return true;
	}
	
	public boolean flagged() {
		return msg;}
	
	public String get() {
		notify();
		return message;
	}
	
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			try {
				message = readMessage(client);
				flag();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Failed to recieve string.");
			}
		}
	}
	
	void sendnull() throws IOException {
		writeMessage(client, "");
	}
	
	java.net.Socket waitForConnect(java.net.ServerSocket serverSocket) throws IOException {
		java.net.Socket socket = serverSocket.accept(); // blocks until client
		return socket;
	}
	
	String readMessage(java.net.Socket socket) throws IOException {//Assumes 200 symbols max
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		char[] buffer = new char[200];
		int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blocks until message
		String nachricht = new String(buffer, 0, anzahlZeichen);
		return nachricht;
	}
	
	void writeMessage(Object message) throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
		printWriter.print(message);
		printWriter.flush();
	}
	
	void writeMessage(java.net.Socket socket, Object message) throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		printWriter.print(message);
		printWriter.flush();
	}
}
