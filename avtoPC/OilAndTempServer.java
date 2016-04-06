package avtoPC;

import java.net.*;
import java.util.*;
import java.io.*;

public class OilAndTempServer {
	/*
	 * Ìîäåëèğàéòå êëàñ „OilAndTempServer”, êîéòî å ñúğâúğ, èç÷àêâàù âêëş÷âàíå
	 * íà êëèåíò – äàò÷èê. Êîãàòî áúäå óñòàíîâåíà TCP âğúçêà ñ êëèåíò, çà íåãî
	 * äà ñå ñòàğòèğà îòäåëíà íèøêà. Â íåÿ êëèåíòà ïúğâîíà÷àëíî ïîäàâà ñâîåòî ID
	 * (èäåíòèôèêàöèîíåí íîìåğ íà äàò÷èêà) è çàïî÷âà ïåğèîäè÷íî äà ïîäàâà ñâîèòå
	 * ïîêàçàíèÿ â áåçêğàåí öèêúë. Ñúğâúğúò îò ñâîÿ ñòğàíà âçèìà ïîêàçàíèÿòà è
	 * ãè îáíîâÿâà â ñâîèòå ÷ëåí ïğîìåíëèâè. Êëèåíòèòå åñòåñòâåíî ñà ìàêñèìàëíî
	 * äâà íà áğîé – åäèí çà òåìïåğàòóğà è åäèí çà ìàñëî.
	 */

	static final int PORT = 5555;
	static ServerSocket ss;

	// static Socket sock;
	public static void main(String args[]) throws IOException {
		try {
			ss = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Ne moje da startira servera");
		}

		while (true) {

			Socket socket = ss.accept();

			ClientThread ct = new ClientThread(socket);
			ct.start();

		}
	}
}

class ClientThread extends Thread {
	Socket sock;
	PrintStream out;
	Scanner in;
	private final String STATUS = "Connected";
	public int ID;

	public ClientThread(Socket sock) throws IOException {
		this.sock = sock;
		in = new Scanner(sock.getInputStream());
		out = new PrintStream(sock.getOutputStream());
		// out.println(STATUS);
	}

	public void run() {

		out.println(STATUS);
		String input = in.nextLine();
		Oil(6);
		switch (input) {
		case "temp":
			while (true) {
				int status = in.nextInt();
				if (status > 1 && status < 100) {
					System.out.println("Temp status(): " + status);
					Oil(5);
				} else {
					System.out.println("Temp status: ERROR!!!");
					break;
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		case "oil":
			while (true) {
				int status = in.nextInt();
				if (status > 1 && status < 100) {
					System.out.println("Oil status(bar): " + status);
				} else {
					out.println("Oil status: ERROR!!!");
					break;
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		default:
			System.out.println("Erorro in switch-case");
		}

	}

	public void Oil(int i) {
		if (i > 1 && i < 100) {
			out.println("Oil status(bar): " + i);
		} else {
			out.println("Oil status: ERROR!!!");
		}

	}

	private void Temp(int i) {
		if (i > 3 && i < 85) {
			out.println("Water temp status(): " + i);
		} else {
			out.println("Water status: ERROR!!!");

		}
	}
}
