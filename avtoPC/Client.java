package avtoPC;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	static final int PORT = 5555;
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", OilAndTempServer.PORT);
		PrintStream out = new PrintStream(socket.getOutputStream());
		Scanner in = new Scanner(socket.getInputStream());
		Scanner cin = new Scanner(System.in);
		
		System.out.println(in.nextLine());
		String input = "temp";
		out.println(input);
		Random rand = new Random();
		while(true){		
		int randomNum = rand.nextInt((100 - 3) + 1) + 3;
		out.println(randomNum);		
		}
	}

}
