import java.util.Random;

public class Oracle{

	private static Random random = new Random();

	public String getResult(){
		
		int n = random.nextInt(10);

		if(n < 2){
			return "very good";

		}else if(n < 5){
			return "good";

		}else if(n < 7){
			return "bad";

		}else {
			return "very bad";
		}
	}

}
