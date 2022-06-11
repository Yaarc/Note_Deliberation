package com.helpers;

import java.util.ArrayList;
import java.util.List;

import com.boudaa.tools.ExcelHandler;
import com.boudaa.tools.ExcelHandlerException;
import java.util.Date;


public class VerificationHelper {
	public static void verify(String url) throws InvalidNoteException, InvalidFormat, ExcelHandlerException {
		List<ArrayList<Object>> list= ExcelHandler.readFromExcel(url, 0);

		for (ArrayList<Object> objects : list)
			System.out.println(objects);

		System.out.println(list.get(1));
		getNiveau(list,"GI2");

		System.out.println( getProf(list));
		System.out.print(VerificationYear(list));

		VerificationGrades(list);
		verificationFormat(url);
		VerificationFormule(list);
		getCurrentYear();
		VerificationYear(list);
		VerificationCalcul(list);

		checkModule(list,"JEE");
	}

	//Return grade excel
	public static void getNiveau(List<ArrayList<Object>> l,String NiveauDB) {
		List<Object> list=l.get(1);
		String niveau = (String) list.get(1);

		if(niveau.equals(NiveauDB))
			System.out.println("niveau correcte");
		else
			System.out.println("erreur niveau");
	}


	//Check Module
	public static void checkModule(List<ArrayList<Object>> l,String ModuleDB) {

		//
	}


	//Return All professors list
	public static List<Object> getProf(List<ArrayList<Object>> l){
		List<Object> list=l.get(1);
		List<Object> listProf= new ArrayList<Object>();

		for (int i = 2; i < list.size(); i++) {
			listProf.add(list.get(i));
		}

		return listProf;
	}



	//return current date
	public static String getCurrentYear(){
		int year = new Date().getYear()+1900;

		String str = (year-1)+"/"+year;
		System.out.println(str);

		return str;
	}


	//Verify year compatibility
	public static String VerificationYear(List<ArrayList<Object>> l) {
		List<Object> list=l.get(0);
		String year = (String) list.get(1);

		if(year.equals(getCurrentYear())) {
			System.out.println("Correct Year");
		}

		return year;
	}



	//Verify grade
	public static void VerificationGrades(List<ArrayList<Object>> l) throws InvalidNoteException {
		List<ArrayList<Object>> grades = l.subList(3, l.size());
		int cmp=0;

		for (ArrayList<Object> iter : grades) {
			double grade1 = (double) iter.get(4);
			double grade2 = (double) iter.get(8);

			if (grade1 < 0.0 || grade2 < 0.0 || grade1 > 20.0 || grade2 > 20.0)
				throw new InvalidNoteException("invalid note de " + iter.get(2) + " " + iter.get(1) + "!!!");
			else
				cmp++;

		}

		if(cmp!=0) {
			System.out.println("Correct grades");
		}
	}



	//VERIFICEATION FORMAT
	public static void verificationFormat(String url)throws InvalidFormat{
		 url=url.toLowerCase();

		 if(url.endsWith(".xlsx")|| url.endsWith(".xls"))
			 System.out.println("format valid");
		 else
			 throw new InvalidFormat("invalid format!!!") ;

	}



	//VERIFICATION calcul formula
	public static void VerificationFormule(List<ArrayList<Object>> l) throws InvalidNoteException{
		VerificationGrades(l);
		List<ArrayList<Object>> grades = l.subList(3, l.size());

		for (ArrayList<Object> iter : grades) {
			double N1 = (double) iter.get(6);
			double N2 = (double) iter.get(7);
			double moyenne = (double) iter.get(8);

			if ((N1 + N2) / 2 == moyenne) {
				System.out.print("Correct Moyenne");
				System.out.print("\n");
			} else
				System.out.print("Erreur Moyenne\n");
		}
	}



	//VERIFICATION DE VALIDATION
	public static void VerificationCalcul(List<ArrayList<Object>> l){
		List<ArrayList<Object>> grades = l.subList(3, l.size());

		for (ArrayList<Object> iter : grades) {

			double moyenne = (double) iter.get(10);
			String val = (String) iter.get(11);

			if (moyenne >= 12) {
				if (val.equals("Validé"))
					System.out.println("Correct Validation");
				else
					System.out.println("Error Column");
			}
			else if (moyenne < 12) {
				if (val.equals("Ajourné"))
					System.out.println("Correct Validation");
				else
					System.out.println("Error Column");

			}
		}
	}
}
