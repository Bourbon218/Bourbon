import java.util.Scanner;// java1.5で入力を扱うクラス

public class Dijkstra {

    public static void main(String[] args) {

	Scanner sc = new Scanner(System.in); // 標準入力から読む
	int nTown = sc.nextInt(); // 都市の数
	int nRoute = sc.nextInt(); // 道路の数
	int[][] map = new int[nTown][nTown]; // 都市の接続関係のマップ

	for (int i=0; i<nTown; i++) // 接続マップを初期化する
	    for (int j=0; j<nTown; j++)
		map[i][j] = (i==j) ? 0 : -1;

	for (int i=0; i<nRoute; i++) { // 道路の状況を読み込む
	    int from = sc.nextInt();
	    int to = sc.nextInt();
	    int len = sc.nextInt();
	    map[from][to] = map[to][from] = len;
	}

	int src = sc.nextInt();// 出発地点
	int dst = sc.nextInt();// 到着地点
	int[] distance = new int[nTown]; // 各都市までの最短距離

	dijkstra(map,src,distance);

	if (distance[dst]==Integer.MAX_VALUE) {// 解なし
	    System.out.println("no route");
	} else {
	    System.out.println("distance="+distance[dst]);
	}
    }

    public static void dijkstra(int[][] map,int src,int[] distance) {

	int nTown = distance.length;
	boolean[] fixed = new boolean[nTown]; // 最短距離が確定したかどうか

	for (int i=0; i<nTown; i++) { // 各都市について初期化する
	    distance[i] = Integer.MAX_VALUE; // 最短距離の初期値は無限遠
	    fixed[i] = false;// 最短距離はまだ確定していない
	}

	distance[src] = 0;// 出発地点までの距離を0とする

	while (true) {
	    // 未確定の中で最も近い都市を求める
	    int marked = minIndex(distance,fixed);

	    if (marked < 0) return; // 全都市が確定した場合

	    if (distance[marked]==Integer.MAX_VALUE) return; // 非連結グラフ

	    fixed[marked] = true; // その都市までの最短距離は確定となる

	    for (int j=0; j<nTown; j++) { // 隣の都市(i)について

		if (map[marked][j]>0 && !fixed[j]) { // 未確定ならば

		    // 旗の都市を経由した距離を求める
		    int newDistance = distance[marked]+map[marked][j];

		    // 今までの距離よりも小さければ、それを覚える
		    if (newDistance < distance[j]) distance[j] = newDistance;

		}
	    }
	}
    }

    // まだ距離が確定していない都市の中で、もっとも近いものを探す
    static int minIndex(int[] distance,boolean[] fixed) {

	int idx=0;

	for (; idx<fixed.length; idx++)// 未確定の都市をどれか一つ探す
	    if (!fixed[idx]) break;

	if (idx == fixed.length) return -1; // 未確定の都市が存在しなければ-1

	for (int i=idx+1; i<fixed.length; i++) // 距離が小さいものを探す
	    if (!fixed[i] && distance[i]<distance[idx]) idx=i;

	return idx;
    }
}