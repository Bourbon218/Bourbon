/*クライアントクラス*/
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.io.*;

public class RMIClient {

	private RMIClient(){}//コンストラクタ

	public static void main(String[] args) {

		String host = (args.length < 1) ? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			RemoteInterface stub = (RemoteInterface) registry.lookup("RMIServer");
			stub.Hanako();
			stub.Say();
		} catch (Exception e) {

			System.err.println("Client exception: " + e.toString());

			e.printStackTrace();

		}
	}
}


