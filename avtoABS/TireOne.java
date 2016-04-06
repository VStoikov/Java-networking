package avtoABS;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
import java.util.*;

public class TireOne {

	public static void main(String[] args) throws IOException {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 5555);
		} catch (UnknownHostException e) {
			System.out.println("Ne moje da se vurje s hosta");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintStream out = new PrintStream(socket.getOutputStream());
		Scanner in = new Scanner(socket.getInputStream());
		Scanner cin = new Scanner(System.in);
		
		
		while(true){
			out.println(1111);
			System.out.println(in.nextLine());
			System.out.println("Vuvedi status na gumata(0-100)");
			int input = cin.nextInt();
			out.println(input);
	}
	}

}
