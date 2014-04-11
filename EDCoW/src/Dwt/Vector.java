package Dwt;

public class Vector {
	
	double[] vec;
	double norm;
	double sum;

	
	Vector(double[] vec1){
		vec = vec1;
		norm();
		sumVector();
	}

	public void sumVector(){
		sum = 0;
		for(double x: vec){
			sum = sum + x;
		}
	}
	
	
	public void norm() {
		// TODO Auto-generated method stub
		norm = 0;
		for(double x : vec){
			norm = norm + Math.pow(x, 2);
		}
		norm = Math.sqrt(norm);
	}


	public double getNorm() {
		return norm;
	}
	public double getSum() {		
		return sum;
	}


}
