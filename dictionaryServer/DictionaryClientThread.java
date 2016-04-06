package dictionaryServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class DictionaryClientThread extends Thread{
	private int CLIENT_REQUEST_TIMEOUT = 15*60*1000; //15min
	private Socket mSocket;
	private BufferedReader mSocketReader;
	private PrintWriter mSocketWriter;
	
	public DictionaryClientThread(Socket aSocket) throws IOException{
		mSocket = aSocket;
		mSocket.setSoTimeout(CLIENT_REQUEST_TIMEOUT);
		mSocketReader = new BufferedReader(
				new InputStreamReader(mSocket.getInputStream()));
		mSocketWriter = new PrintWriter(
				new OutputStreamWriter(mSocket.getOutputStream()));
	}
	
	public void run(){
		System.out.println(new Date().toString() + " : " + 
				"Accepted client : " + mSocket.getInetAddress() + ":" + 
				mSocket.getPort());
		try{
			mSocketWriter.println("Dictionary server ready.");
			mSocketWriter.flush();
			while(!isInterrupted()){
				String word = mSocketReader.readLine();
				if(word == null)
					break;
				String translation = getTranslation(word);
				mSocketWriter.println(translation);
				mSocketWriter.flush();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		System.out.println(new Date().toString() + " : " + 
		"Connection lost : " + mSocket.getInetAddress() + 
		":" + mSocket.getPort());
	}
	
	private String getTranslation(String aWord){
		if(aWord.equalsIgnoreCase("netword")){
			return "мрежа";
		} else if (aWord.equalsIgnoreCase("firewall")){
			return "za]itna stena";
		} else {
			return "! непозната дума !";
		}
	}
}
