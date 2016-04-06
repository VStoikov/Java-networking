package primeren;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static void main(String args[]) throws IOException, InterruptedException{
		
		Socket sock = new Socket("localhost", Server.port);
		PrintStream out = new PrintStream(sock.getOutputStream());
		Scanner in = new Scanner(sock.getInputStream());
		Scanner cin = new Scanner(System.in);
		
		System.out.println(in.nextLine());
		String input = cin.nextLine();
		out.println(input);
		switch(input){
		case "1":
			Scanner fin = new Scanner(new File("doc1.txt"));
			while(fin.hasNextLine()){
				out.println(fin.nextLine());
			}
			out.write(-1);
			break;
		default: 
			break;
		}
		Thread.sleep(10000);
	}
}
