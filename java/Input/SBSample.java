import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SBSample{

	public static void main(String[] args) {

		int m;

		System.out.print("人数を入力 :");
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		System.out.println("----------------------------------");

		String[] name = new String[m];

		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);

		System.out.println("名前を入力して下さい");
		for(int i=0; i<m; i++){
			System.out.print("名前["+ (i+1) +"] :");
			try {
				name[i] = br.readLine();
			} catch (IOException e) { e.printStackTrace(); } 
		}

		for(int i=0; i<m; i++){
			System.out.println("----------------------------------");
			System.out.println("学生[" + (i+1) + "]");
			System.out.println("名前 :" + name[i]);
		}
		System.out.println("----------------------------------");
	}
}
