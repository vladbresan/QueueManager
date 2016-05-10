import java.util.ArrayList;

public class Queue extends Thread {
	ArrayList<Task> queue = new ArrayList<Task>();
	int queueNr;
	Thread t;
	public static int waiting;
	
	public boolean stopThread = false;

	public Queue(int queueNr) {
		this.queueNr = queueNr;
	}

	@Override
	public void run() {
		while (stopThread == false) {
			if (queue.size() > 0) {
				if (queue.get(0).getProcessTime() <= 0) {
					queue.remove(0);
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public int getProcessTime() {
		int time = 0;
		
		for (int i = 0; i < queue.size(); i++) {
			time = time + queue.get(i).getProcessTime();
		}
		
		return time;
	}
	
	public void addTask(Task tsk){
		queue.add(tsk);
	}
	
	public int getSize(){
		return queue.size();
	}
	
	public Task getTask(int i){
		return queue.get(i);
	}
	
	public void removeTask(int i){
		queue.remove(i);
	}
}
