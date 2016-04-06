package primeren;

import java.io.*;
import java.util.Scanner;

public class StudentScholarshipInquiry {
	
	String name;
	double GPA;
	double income;
	String faculty;
	Scanner in;
	public StudentScholarshipInquiry(File file) throws FileNotFoundException{
		try{
		in = new Scanner(file);
		name = in.nextLine();
		GPA = Double.parseDouble(in.nextLine());
		income = Double.parseDouble(in.nextLine());
		faculty = in.next();
		} catch(IOException e){
			System.out.println("Read error");
		} finally{
			in.close();
		}
	}
	
	@Override
	public String toString(){
		return "StudentScholarshipInquiry [name="+ name + ", GPA=" + GPA + ", income=" + income + ", faculty=" + faculty + "]";
	}
}
