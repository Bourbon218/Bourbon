/*インターフェース*/
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote{	
	public void Hanako() throws RemoteException;
	public void Say() throws RemoteException;
}
