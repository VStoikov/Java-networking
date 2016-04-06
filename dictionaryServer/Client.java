package dictionaryServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private static int SERVER_RESPONSE_TIMEOUT = 60*1000;
	public static void main(String args[]) throws IOException{
		Socket socket = new Socket("localhost", 3333);
		socket.setSoTimeout(SERVER_RESPONSE_TIMEOUT);
		BufferedReader socketReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		PrintWriter socketWriter =
				new PrintWriter(socket.getOutputStream());
		BufferedReader consoleReader = new BufferedReader(
				new InputStreamReader(System.in));
		String welcomeMessage = socketReader.readLine();
		System.out.println(welcomeMessage);
		try{
			while(true){
				String word = consoleReader.readLine();
				socketWriter.println(word);
				socketWriter.flush();
				String translation = socketReader.readLine();
				System.out.println(translation);
			}
		} finally{
			socket.close();
		}
		
		
	}
	

}
