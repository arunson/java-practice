package fileprocessing;

import java.io.*;
import java.util.Scanner;

public class Main {
	public static Student register() {
		Student kid = new Student();
		Scanner scnr = new Scanner(System.in);

		System.out.println("Enter information about the student");
		System.out.print("Student ID: ");
		kid.StudentIdentificationNumber = scnr.nextInt();
		System.out.print("First Name: ");
		kid.FirstName = scnr.next();
		System.out.print("Last Name: ");
		kid.LastName = scnr.next();
		System.out.print("Number of credits so far: ");
		kid.CreditsSoFar = scnr.nextInt();
		System.out.print("Grade point average: ");
		kid.GPA = scnr.nextDouble();

		return kid;
	}

	public static void save(Student pupil) throws Exception {
		String strFilename = "";
		Scanner scnr = new Scanner(System.in);

		System.out.print("Enter the file name: ");
		strFilename = scnr.next();

		// Make sure the user entered a valid file name
		if (!strFilename.equals("")) {
			// Incidate that you are planning to use a file
			File fleStudent = new File(strFilename);
			// Create that file and prepare to write some values to it
			PrintWriter wrtStudent = new PrintWriter(fleStudent);

			wrtStudent.println(pupil.StudentIdentificationNumber);
			wrtStudent.println(pupil.FirstName);
			wrtStudent.println(pupil.LastName);
			wrtStudent.println(pupil.CreditsSoFar);
			wrtStudent.println(pupil.GPA);

			// After using the PrintWriter object, de-allocated its memory
			wrtStudent.close();
			// For convenience, let the user know that the file has been created
			System.out.println("The file has been created.");
		}
	}

	public static Student open() throws Exception {
		String strFilename = "";
		String strStudentIdentificationNumber;
		String strCreditsSoFar;
		String strGPA;

		Student majoring = new Student();
		Scanner scnr = new Scanner(System.in);

		System.out.print("Enter the file name: ");
		strFilename = scnr.next();

		if (!strFilename.equals("")) {
			// Indicate that you are planning to opena file
			File fleStudent = new File(strFilename);
			// Find out if the file exists already
			if (fleStudent.exists()) {
				// Prepare a Scanner that will "scan" the document
				Scanner opnStudentInfo = new Scanner(fleStudent);

				// Read each line in the file
				while (opnStudentInfo.hasNext()) {
					// Read each line and display its value
					strStudentIdentificationNumber = opnStudentInfo.nextLine();
					majoring.StudentIdentificationNumber = Integer
							.parseInt(strStudentIdentificationNumber);
					majoring.FirstName = opnStudentInfo.nextLine();
					majoring.LastName = opnStudentInfo.nextLine();
					strCreditsSoFar = opnStudentInfo.nextLine();
					majoring.CreditsSoFar = Integer.parseInt(strCreditsSoFar);
					strGPA = opnStudentInfo.nextLine();
					majoring.GPA = Double.parseDouble(strGPA);
				}

				// De-allocate the memory that was used by the scanner
				opnStudentInfo.close();
			}
		}

		return majoring;
	}

	public static void show(Student std) throws Exception {
		System.out.println("Student Record");
		System.out.println("Student ID: " + std.StudentIdentificationNumber);
		System.out.println("First Name: " + std.FirstName);
		System.out.println("Last Name: " + std.LastName);
		System.out.println("Number of credits so far: " + std.CreditsSoFar);
		System.out.println("Grade point average: " + std.GPA);
	}

	public static void main(String[] args) throws Exception {
		String mainAnswer = "Q", answer = "N";
		Scanner scnr = new Scanner(System.in);

		System.out.println("State University");
		System.out.println("What do you want to do?");
		System.out.println("R - Register a student");
		System.out.println("V - View a student information");
		System.out.println("Q - Exit or Quit");
		System.out.print("Your Answer? ");
		mainAnswer = scnr.next();

		if ((mainAnswer.equals("r")) || (mainAnswer.equals("R"))) {
			Student std = register();

			System.out.print("Do you want to save this information (y/n)? ");
			answer = scnr.next();
			if ((answer.equals("y")) || (answer.equals("Y"))) {
				show(std);
				save(std);
			}
		} else if ((mainAnswer.equals("v")) || (mainAnswer.equals("V"))) {
			Student std = open();
			show(std);
		} else
			System.out.println("Good Bye!!!");
	}
}

class Student {
	public int StudentIdentificationNumber;
	public String FirstName;
	public String LastName;
	public int CreditsSoFar;
	public double GPA;
}
