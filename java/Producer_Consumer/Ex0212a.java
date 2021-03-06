/**
 * Java Example Programs
 * @author Naoyuki Tamura
 **/

import java.util.*;

public class Ex0212a {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Buffer buff = new Buffer();
		Producer p1 = new Producer("Producer 1",buff); //生産者1
		Producer p2 = new Producer("Producer 1",buff); //生産者2
		Consumer c1 = new Consumer("Consumer 1",buff); //消費者1
		Consumer c2 = new Consumer("Consumer 2",buff); //消費者2

		p1.start();
		p2.start();
		c1.start();
		c2.start();

		try {
			p1.join();
			p2.join();
		}catch(Exception e){}
		buff.close();

	}
}

// "Message 1" , ..., "Message 10"をバッファにputする
//
//
class Producer extends Thread {

	private Buffer buff;

	public Producer (String name, Buffer b){
		super(name);
		buff = b;
	}

	public void run(){
		for(int i=0; i<10; ++i){
			try{
				Thread.sleep((int)(500*Math.random()));
			}catch(InterruptedException e){};
			String elem = "Message " + (i+1);
			System.out.println(getName() + ": put " + elem);
			buff.put(elem);
			Thread.yield();
		}
	}
}

//
//バッファから要素をgetして表示する
//
class Consumer extends Thread {

	private Buffer buff;

	public Consumer(String name, Buffer b) {
		super(name);
		buff = b;
	}

	public void run(){
		while(true){
			try{
				Thread.sleep((int)(500*Math.random()));
			}catch(InterruptedException e){};
			Object elem = buff.get();
			if(elem == null) break;
			if(elem instanceof String) System.out.println(getName() + ": get " + (String)elem);
			Thread.yield();
		}
	}
}

//
//共有バッファ(無限長)
//
class Buffer {

	private Vector vec = new Vector();
	private boolean closed = false;

	public synchronized void close() {
		notifyAll();           //ここを変更
		System.out.println("Buffer close");
		closed = true;
	}

	public synchronized void put(Object elem) {
		if(vec.isEmpty()) 
			notifyAll();       //ここを変更
		vec.addElement(elem);
	}

	public synchronized Object get() {
		while(vec.isEmpty()) {
			if(closed) return null;
			System.out.println(Thread.currentThread().getName() + ": get wait");
			try{
				wait();
			}catch(InterruptedException e){}
		}
		Object elem = vec.firstElement();
		if(elem != null)
			vec.removeElementAt(0);
		return elem;
	}
}