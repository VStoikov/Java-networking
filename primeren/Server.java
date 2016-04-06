package primeren;

import java.io.*;
import java.net.*;
import java.util.*;


public class Server {
	static final int port = 5555;
	
	static Queue<StudentScholarshipInquiry> quUspeh = new LinkedList<>();
	static Queue<StudentScholarshipInquiry> quSpecial = new LinkedList<>();
	static ServerSocket sock = null;
	
	public static void main(String args[]){
		try{
		sock = new ServerSocket(port);
		} catch(IOException e){
			System.out.println("Ne moje da startira server-a");
			return;
		}
		Secretary s1 = new Secretary();
		Secretary s2 = new Secretary();
		s1.start();
		s2.start();
		
		while(true){
			System.out.println("Chakane za klient:");
			try{
				Socket s = sock.accept();
				ClientThread ct = new ClientThread(s);
				ct.start();
			} catch(IOException e){
				System.out.println("Connection failed");
			}
		}
	}
}

class ClientThread extends Thread{
	
	private static int counter = 0;
	Socket sock;
	PrintStream out;
	Scanner in;
	private final String MENU = "1. Uspeh; 2. Specialna";
	
	public ClientThread(Socket sock) throws IOException{
		this.sock = sock;
		in = new Scanner(sock.getInputStream());
		out = new PrintStream(sock.getOutputStream());
	}
	
	@Override
	public void run(){
		out.println(MENU);
		String input = in.nextLine();
		
		switch(input){
		case "1":{
			File f = getFile();
			try{
				StudentScholarshipInquiry ssi = new StudentScholarshipInquiry(f);
			} catch(FileNotFoundException e){
				System.out.println("Ne moje da nameri faila");
			}
			
			System.out.println("Client send 1");
		}
		
		case "2":{
			File f = getFile();
			try{
				StudentScholarshipInquiry ssi = new StudentScholarshipInquiry(f);
				System.out.println(ssi);
				Server.quSpecial.add(ssi);
			} catch(FileNotFoundException e){
				System.out.println("Ne moje da nameri faila");
			}
			System.out.println("Client send 2");
		}
		
		default:
			System.out.println("Unrecognized command");
			break;
		}
	}
	
	private File getFile(){
		File file = new File("server/inquiry_"+ counter++ + ".txt");
		FileWriter fw;
		try{
			fw = new FileWriter(file);
			String temp;
			while(!(temp = in.nextLine()).equals("-1")){
				fw.write(temp);
				fw.write("\n");
			}
			fw.flush();
			fw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		return file;
	}	
}

class Secretary extends Thread{
	
	static List<StudentScholarshipInquiry> approved = new ArrayList<>();
	static List<StudentScholarshipInquiry> declined = new ArrayList<>();
	
	@Override
	public void run(){
		while(true){
			while(Server.quSpecial.isEmpty() && Server.quUspeh.isEmpty()){
				try{
					Thread.sleep(10000);
				} catch (InterruptedException e){
					
				}
			}
			System.out.println("passed");
			
			StudentScholarshipInquiry ssi;
			if(!Server.quSpecial.isEmpty()){
				synchronized (Server.quSpecial){
					ssi = Server.quSpecial.poll();
				}
				if(ssi.GPA > 4){
					approved.add(ssi);
					System.out.println(ssi + " approved");
				} else{
					declined.add(ssi);
					System.out.println(ssi + " declined");
				}
			} else if(!Server.quUspeh.isEmpty()){
				synchronized(Server.quUspeh){
					ssi = Server.quUspeh.poll();
				}
				if(ssi.GPA > 5.5){
					approved.add(ssi);
					System.out.println(ssi + " approved");
				} else {
					declined.add(ssi);
					System.out.println(ssi + " declined");
				}
			}
		}
	}
}
