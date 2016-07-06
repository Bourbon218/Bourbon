package ParallelPi;

import java.math.*;

public class PiThread extends Thread {

	BigInteger denomi;
	int precision;
	int multiplexity;
	AddPi addpi;
	int m;

	public PiThread(BigInteger denomi, int precision, int multiplexity, int m, AddPi addpi){
		this.denomi = denomi;
		this.precision = precision;
		this.multiplexity = multiplexity;
		this.addpi = addpi;
		this.m = m;
	}

	public void run(){
		Pi pi = new Pi();
		pi.arctan(denomi, precision, multiplexity, m);
		addpi.addPi(pi.getArctan());
	}

}