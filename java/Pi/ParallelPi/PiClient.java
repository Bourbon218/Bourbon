package ParallelPi;

import java.math.*;
import java.util.*;

class PiClient{

    public static void main(String args[]) {

	int precision = Integer.parseInt(args[0]);
	int multiplexity = Integer.parseInt(args[1]);

	System.out.println("Precision: "+precision);
	System.out.println("Multiplexity: "+multiplexity);

	BigInteger a1 = new BigInteger("1");
	BigInteger a2 = new BigInteger("2");
	BigInteger a3 = new BigInteger("3");
	BigInteger a4 = new BigInteger("4");
	BigInteger a5 = new BigInteger("5");
	BigInteger a16 = new BigInteger("16");
	BigInteger a25 = new BigInteger("25");
	BigInteger a239 = new BigInteger("239");
	BigInteger a57121 = new BigInteger("57121");
	BigInteger m1 = new BigInteger("-1");
	BigInteger a10 = new BigInteger("10");

	System.out.println("Start1 on: "+new Date());
	BigInteger n=a10.pow(precision);

	Date startdate = new Date();

	//式前半 16 * ARCTAN (1/5) = 16 / 5 (1 - 1 / (3 * 25) + 1 / (5 * 25^2) -.....)
	//1項と2項の部分 (1 - 1 / (3 * 25))のみ
	Arctan at1 = new Arctan();
	BigInteger nn1 = at1.arctan(a5, precision+10, multiplexity);


	//式後半 4 * ARCTAN (1/239)= 4/239 (1 - 1 /(3*57121) + 1 /(5*57121^2) - ....)
	//1項と2項の部分 (1 - 1 / (3 * 57121))のみ
	Arctan at2 = new Arctan();
	BigInteger nn2 = at2.arctan(a239, precision+10, multiplexity);

	//式前半
	//          BigInteger pai1=a16.multiply(n).multiply(nn1).divide(a5);
	BigInteger pai1=a16.multiply(nn1);
	//式後半
	//   	  BigInteger pai2=a4.multiply(n).multiply(nn2).divide(a239);
	BigInteger pai2=a4.multiply(nn2);

	//全体
	BigInteger pai=pai1.subtract(pai2);

	Date enddate = new Date();

	String paiString = pai.toString();
	System.out.print("pai = " );
	for(int i=0; i<precision+1; i++) {
	    System.out.print(paiString.charAt(i));
	    if(i%5==0) {
		System.out.print(" ");
	    }
	    if(i%50==0) {
		System.out.println();
	    }
	}
	System.out.println();

	System.out.println("Start on: "+startdate);
	System.out.println("End on: "+enddate);
	System.out.println("Elapsed Time: "+(enddate.getTime()-startdate.getTime())+"ms");
	
    }
}
