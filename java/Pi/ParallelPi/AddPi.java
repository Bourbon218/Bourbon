package ParallelPi;

import java.math.*;
public class AddPi{
    public AddPi(){}
    BigInteger result = new BigInteger("0");
    public synchronized void addPi(BigInteger response){ result = result.add(response); }
    public BigInteger getResult(){ return result; }
}