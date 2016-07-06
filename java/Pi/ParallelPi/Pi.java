package ParallelPi;

import java.math.*;

class Pi {
    BigInteger nn1;

    public Pi() {
    }

    public BigInteger getArctan() {
	return nn1;
    }


    public void arctan(BigInteger denomi, int precision, int multiplexity, int offset) {
	System.out.println("Start Pi.arctan(1/"+denomi+"):"+offset+"/"+multiplexity);

	BigInteger a1 = new BigInteger("1");
	BigInteger a2 = new BigInteger("2");
	BigInteger a3 = new BigInteger("3");
	BigInteger m1 = new BigInteger("-1");
	BigInteger a10 = new BigInteger("10");

	BigInteger n=a10.pow(precision);

	BigInteger denomi2 = denomi.multiply(denomi); // denomi**2

	BigInteger denomi2m = denomi2;        // denomi2m = denomi**(2*multiplexity)
	for(int i=1; i<multiplexity; i++) {
	    denomi2m=denomi2m.multiply(denomi2);
	}

	BigInteger denomi0 = denomi;          // denomi0 = denomi**(2*offset+1)
	for(int i=0; i<offset; i++) {
	    denomi0=denomi0.multiply(denomi2);
	}

	//	BigInteger n=a10.pow(precision);
	BigInteger c0=BigInteger.valueOf(offset).multiply(a2).add(a1);
	int s;
	if(offset%2==0) {
	    s=1;
	} else {
	    s=-1;
	}

	BigInteger n1=n.divide(c0.multiply(denomi0));
	if(s==-1) {
	    n1 = n1.negate();
	}
	//	BigInteger nn1=n1;
	nn1=n1;
	//System.out.println("num0="+num0);
	//System.out.println("denomi0="+denomi0);
	//System.out.println("c0="+c0);

	//BigInteger i=new BigInteger("1");
	long i = 1;
	//System.out.println("i = 0 n1="+n1); //繰り返し回数
	
	BigInteger mul=BigInteger.valueOf(multiplexity);
	BigInteger mul2=a2.multiply(mul);

	//	BigInteger denomi1=denomi0;
	BigInteger q=n.divide(denomi0);

	while ((n1.abs()).compareTo(a1) > 0) {
	    c0=c0.add(mul2);

	    //	    denomi1=denomi1.multiply(denomi2m);
	    q=q.divide(denomi2m);
	    
	    if(multiplexity%2!=0) {
		s=s*(-1);
	    }
	    if(s==1) {
		//n1=n.divide(c0.multiply(denomi1));
		n1=q.divide(c0);
	    } else {
		//		n1=n.divide(c0.multiply(denomi1));
		n1=q.divide(c0);
		n1=n1.negate();
	    }

	    nn1=nn1.add(n1);
	    //System.out.println("i = " + i +" n1="+n1+" c0="+c0+" num1="+num1+" denomi1="+denomi1); //繰り返し回数
	    //i=i.add(a1);
	    i++;
	}

	System.out.println("Finished Pi.arctan(1/"+denomi+"):"+offset+"/"+multiplexity+" on i=" + i); //繰り返し回数
	//return nn1;
    }
}
