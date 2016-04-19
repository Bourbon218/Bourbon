import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BRSample {

	public static void main(String[] args) {

		String moji = null;

		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is); 

		System.out.print("文字を入力して下さい :");

		try {
			moji = br.readLine();
		} catch (IOException e) { e.printStackTrace(); } 

		System.out.println("文字 :" + moji);

	}

}
