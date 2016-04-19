import java.util.Scanner;

public class Dijkstra1 {

    public static void main(String[] args){

	Scanner sc = new Scanner(System.in);
	int nTown = sc.nextInt();
	int src = sc.nextInt();
	int dst = sc.nextInt();
	int[] distance = new int[nTown];
	int[] via = new int[nTown];
	int[][] map = new int[nTown][nTown];

	dijkstra(map, src, via);

	if(distance[dst] == Integer.MAX_VALUE){ System.out.println("no route"); }

	else{
	    System.out.println("distance=" + distance);
	    for(int i=dst; i!=src; i=via[i]){ System.out.println(i + " "); }
	    System.out.println(src);
	}
    }
    
    public static void dijkstra(int[][] map,int src,int[] distance,int[] via) {

	int nTown = distance.length;
	boolean[] fixed = new boolean[nTown];

	for (int i=0; i<nTown; i++) {
	    distance[i] = Integer.MAX_VALUE;
	    fixed[i] = false;
	    via[i] = -1;
	}

	distance[src] = 0;

	while (true){

	    int newDistance = distance[marked]+map[marked][j];

	    if (newDistance < distance[j]) {
		distance[j] = newDistance;
		via[j] = marked;
	    }

	}
    }
}