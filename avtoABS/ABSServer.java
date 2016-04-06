package avtoABS;

import java.net.*;
import java.io.*;
import java.util.*;

public class ABSServer {
	static final int PORT = 5555;

	public static void main(String args[]) throws IOException {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Ne moje da startira servera");
		}

		while (true) {
			Socket sock = ss.accept();
			ClientThread ct = new ClientThread(sock);
			ct.start();
		}
	}

}

class ClientThread extends Thread {
	Socket socket;
	PrintStream out;
	Scanner in;
	private final String INFO = "Waiting for status";

	public ClientThread(Socket socket) throws IOException {
		this.socket = socket;
		in = new Scanner(socket.getInputStream());
		out = new PrintStream(socket.getOutputStream());
	}

	public void run() {
		while(true){
			out.println(INFO);
		int input = in.nextInt();
		switch (input) {
		case 1111:			
			try {
				System.out.println("Status for ABS is: " + slip1());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	}

	private boolean slip1() throws InterruptedException {
		boolean result = false;
		while(true){
			int breaks = in.nextInt();
		if (breaks > 50){
			result = ABS(result);
			
		}
		return result;
		}
	}
	
	private static boolean ABS(boolean result) throws InterruptedException{
		System.out.println("ABS is ON!");
		Thread.sleep(500);
		
		return result = false;
	}
}
