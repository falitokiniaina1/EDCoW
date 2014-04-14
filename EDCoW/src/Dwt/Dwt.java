package Dwt;

import math.transform.jwave.handlers.DiscreteWaveletTransform;
import math.transform.jwave.handlers.wavelets.Haar02;
import math.transform.jwave.handlers.wavelets.WaveletInterface;

public class Dwt {
	
	int level;
	double[] signal;
	double[] arrHilb;	
	double[] probaVector;
	double shannonEntropy;
	double swemax, hMeasure;
	
	WaveletInterface wI;
	DiscreteWaveletTransform dwt;		
	
	Dwt(double[] signal1,int level1){    					
		signal = signal1;
		level = level1;
		wI = new Haar02();		
		dwt = new DiscreteWaveletTransform(wI,level);				
		arrHilb = dwt.forward( signal );		
	}

	public int nbCoeffAtLevel(int level1){		
		return (int)((arrHilb.length)/Math.pow(2, level1));
	}
	
	/** Get coefficients of a level*/
	public double[] getCoefficients(int atLev) {
		// TODO Auto-generated method stub	           			
			int rightPart = 0;
			for (int i=1; i<=atLev; i++){
				rightPart = rightPart + nbCoeffAtLevel(i);
			}
			int totalSize = arrHilb.length;
			int beginIndice = totalSize - rightPart;
			int nbCoeff = nbCoeffAtLevel(atLev);
			double[] coeff = new double[nbCoeff];
			
			for(int i=0; i<nbCoeff; i++){
				coeff[i] = arrHilb[beginIndice+i];
			}
			return coeff;
	}					
    	
	/** compute proba vector*/
	public void probaVector() {
		// TODO Auto-generated method stub
		double sSquare = 0;
		probaVector = new double[level];
		
		for(int i=0; i<level; i++){
			double[] coeff = getCoefficients(i+1);						
			Vector vec = new Vector(coeff);
			sSquare = sSquare + Math.pow(vec.getNorm(),2);												
		}
		for(int i=0; i<level; i++){
			double[] coeff = getCoefficients(i+1);						
			Vector vec = new Vector(coeff);
			probaVector[i] = Math.pow(vec.getNorm(),2) / sSquare;											
		}				
		for(double x:probaVector){
			System.out.println("p = "+x);
		}		
	}	
    
	public void shannonEntropy() {
		shannonEntropy=0;
		for(double x:probaVector){
			shannonEntropy = shannonEntropy - (x * (Math.log(x)/Math.log(2)));
		}
		System.out.println("shannon Entropy :"+shannonEntropy);		
	}
    
	public void hMeasure(){
		swemax = Math.log(level)/Math.log(2);
		hMeasure = shannonEntropy / swemax;
		//System.out.println("h(s) is " + hMeasure);
	
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double[] getSignal() {
		return signal;
	}

	public void setSignal(double[] signal) {
		this.signal = signal;
	}

	public double[] getArrHilb() {
		return arrHilb;
	}

	public void setArrHilb(double[] arrHilb) {
		this.arrHilb = arrHilb;
	}

	public double gethMeasure() {
		return hMeasure;
	}			
}
