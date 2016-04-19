/*オブジェクト*/
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Hello extends UnicastRemoteObject implements RemoteInterface{

	public Hello() throws RemoteException{}

	public void Hanako(){
		System.out.println("花子さん");
	}

	public void Say(){
		System.out.println("こんにちは");
	}
}
