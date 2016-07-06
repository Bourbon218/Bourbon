package ParallelPi;

import java.math.*;

public class Arctan {

    public Arctan(){}

    public BigInteger arctan(BigInteger denomi, int precision, int multiplexity) {

	PiThread[] pit = new PiThread[multiplexity];

	AddPi ap = new AddPi();

	for(int i=0; i<multiplexity; i++){
	    pit[i] = new PiThread(denomi, precision, multiplexity, i, ap);
	    pit[i].start();
	}

	for(int i=0; i<multiplexity; i++){
	    try{ pit[i].join(); }
	    catch(InterruptedException e){}
	}

	BigInteger result = ap.getResult();

        return result;

    }

}