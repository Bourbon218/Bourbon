/**
 * Java Example Programs
 * @author Naoyuki Tamura
 */

import java.util.*;

public class Ex0211a {
    public static void main(String args[]) {
	Buffer buff = new Buffer();
	Producer p = new Producer("Producer", buff);// 生産者
	Consumer c = new Consumer("Consumer", buff);// 消費者
	p.start();
	c.start();
	try {
	    p.join();
	} catch (InterruptedException e) {}
	buff.close();
    }
}

//
// "Message 1", ..., "Message 10"をバッファにputする
//
class Producer extends Thread {
    private Buffer buff;

    public Producer(String name, Buffer b) {
	super(name);
	buff = b;
    }

    public void run() {
	for (int i = 0; i < 10; ++i) {
	    try {
		Thread.sleep((int)(500 * Math.random()));
	    } catch (InterruptedException e) {};
	    String elem = "Message " + (i+1);
	    System.out.println(getName() + ": put " + elem);
	    buff.put(elem);
	    // バッファが無限長なので，生産者ばかりが動いてしまう可能性があり，
	    // 消費者がstarvation状態になる
	    // これを防ぐため，yieldを実行する
	    Thread.yield();
	}
    }
}

//
// バッファから要素をgetして表示する
//
class Consumer extends Thread {
    private Buffer buff;

    public Consumer(String name, Buffer b) {
	super(name);
	buff = b;
    }

    public void run() {
	while (true) {
	    try {
		Thread.sleep((int)(500 * Math.random()));
	    } catch (InterruptedException e) {};
	    Object elem = buff.get();
	    if (elem == null)
		break;
	    if (elem instanceof String) {
		System.out.println(getName() + ": get " + (String)elem);
	    }
	}
    }
}

//
// 共有バッファ(無限長)
// このプログラムは，複数の消費者がいるとうまく動かない．
// どこを直せば良いか?
// 
class Buffer {
    private Vector vec = new Vector();
    private boolean closed = false;

    public synchronized void close() {
	System.out.println("Buffer closed");
	closed = true;
    }

    public synchronized void put(Object elem) {
	if (vec.isEmpty())
	    notify();// 空でなくなるので消費者を再開
	vec.addElement(elem);
    }

    public synchronized Object get() {
	if (vec.isEmpty()) {
	    if (closed)
		return null;
	    System.out.println(Thread.currentThread().getName() + ": get wait");
	    try {
		wait();// バッファが空なら消費者は待つ
	    } catch (InterruptedException e) {}
	}
	Object elem = vec.firstElement();
	if (elem != null)// null は消さない
	    vec.removeElementAt(0);
	return elem;
    }
}
