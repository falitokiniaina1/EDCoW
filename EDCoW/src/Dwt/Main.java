package Dwt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {	
	
	Main(double[] nwt, double[] nt) {
		
	}
	
	Main(double[] nwt, double[] nt, int delta, int lev) throws FileNotFoundException, UnsupportedEncodingException {				
		SignalConstruction signWavelet = new SignalConstruction();
		signWavelet.firstSignalConstruction(nwt, nt);
		signWavelet.secondSignalConstruction(signWavelet.getSw(), delta, lev);
		
		System.out.println("\n\n nwt [number of tweets which contain word w as INPUT] : ");
		for(double x : nwt){
			System.out.print(x+";");
		}		
		System.out.println("\n\n nt [number of all tweets in the same period of time as INPUT] : ");
		for(double x : nt){
			System.out.print(x+";");
		}

		System.out.println("\n\n signal construction (for world w) in the first step:");
		PrintWriter writer = new PrintWriter("fileOut.txt", "UTF-8");
		writer.println("first");
		for(double x : signWavelet.getSw()){
			//System.out.println(x);						
			writer.println(x);				
		}
		
		System.out.println("\n signal construction (for world w) in the second step:");		
		writer.println("second");
		for( double x : signWavelet.getSw2()){
			//System.out.println(x);
			writer.println(x);	
		}
		writer.close();		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

// ------ VERY SIMPLE FORWARD DWT-------------------------------
//		double[] sign={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
//		int lev = 2;		
		
//---------Forwart Discrete Wavelet Transform---------
//		Dwt dwt = new Dwt(sign,lev);		
		
//----------retrieve hilbert array-----------------------
//		System.out.println("\n arrHilb");		
//		double[] arrHilb = dwt.getArrHilb();			
//		for(double x : arrHilb){
//			System.out.println(x);
//		}
					
//----------retrieve coefficients per level-------------------
//		System.out.println("\n coefficients at level "+atLev);
//		double[] coeff = dwt.getCoefficients(atLev);		
//		for(double x:coeff){
//			System.out.println("coeff : "+x);			
//		}
		
//		dwt.probaVector();
//		dwt.shannonEntropy();
//		dwt.hMeasure();						

		double[] nwt = null;
		double[] nt = null;
		/** Ask the user about the file names and parameters */
		System.out.println("CONSTRUCTION OF SIGNALS");
		Scanner sc = new Scanner(System.in);
		System.out.println("Filename containing nwt signals? [type \"nwt.txt\"] : ");
		String nwtFileName = sc.next();
		System.out.println("Filename containing nt signals? [type \"nt.txt\"] : ");
		String ntFileName = sc.next();
		System.out.println("Delta value? [e.g.: 6]: ");
		int delta = sc.nextInt();
		System.out.println("Level value? [e.g.: 2] : ");
		int lev = sc.nextInt();
		sc.close();
		 
		try{
			FileReader fileReadear = new FileReader(nwtFileName);
			BufferedReader buff = new BufferedReader(fileReadear);

			String firstLine = buff.readLine();
			/** separate the data with delimiter ; */			
			StringTokenizer st = new StringTokenizer(firstLine, ";");		
			int nb = 0;
			nwt = new double[st.countTokens()];
			while(st.hasMoreTokens()){				
				nwt[nb] = Double.parseDouble(st.nextToken());
				nb++;
			}
			buff.close();
			fileReadear.close();
			
			fileReadear = new FileReader(ntFileName);
			buff = new BufferedReader(fileReadear);

			firstLine = buff.readLine();
			/** separate the data with delimiter ; */			
			st = new StringTokenizer(firstLine, ";");		
			nb = 0;
			nt = new double[st.countTokens()];
			while(st.hasMoreTokens()){				
				nt[nb] = Double.parseDouble(st.nextToken());
				nb++;
			}
			
			buff.close();
			fileReadear.close();
		}//Fin try
		 
		catch (IOException e){
			System.out.println("Error : "+e);
		}
		
		try {
			Main main = new Main(nwt,nt,delta,lev);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}													
	}

}
