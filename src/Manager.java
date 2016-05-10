import java.util.ArrayList;

public class Manager {
	ArrayList<Queue> queues = new ArrayList<Queue>();
	private int simulationTime;
	public static int peakVal;
	public static int peakHour;
	public static int queueEmptyTime=0;
	private int[] closedQueues=new int[100];

	public Manager(int nrOfQueues) {
		for (int i = 0; i < nrOfQueues; i++) {
			queues.add(new Queue(i));
			queues.get(i).start();
			closedQueues[i] = 0;
		}
	}

	public void addQueue() {
		queues.add(new Queue(queues.size()));
		queues.get(queues.size() - 1).start();
	}

	public void addTask(Task tsk) {
		int index = 0;
		int min = queues.get(0).getProcessTime();
		for (int i = 0; i < queues.size(); i++) {
			if (queues.get(i).getProcessTime() < min && closedQueues[i]==0) {
				min = queues.get(i).getProcessTime();
				index = i;
			}
		}

		tsk.setArrivalTime(simulationTime);
			queues.get(index).addTask(tsk);
	}

	public void redistribute() {
		Queue min = queues.get(0);
		Queue max = queues.get(0);

		while (queues.get(queues.size() - 1).getProcessTime() < min.getProcessTime()) {
			for (int i = 0; i < queues.size(); i++) {
				if (queues.get(i).getProcessTime() < min.getProcessTime()) {
					min = queues.get(i);
				}
			}

			for (int i = 0; i < queues.size(); i++) {
				if (queues.get(i).getProcessTime() > max.getProcessTime()) {
					max = queues.get(i);
				}
			}

			if (queues.size() > 1) {
				if (queues.get(queues.size() - 1).getProcessTime()
						+ queues.get(max.queueNr).getTask(queues.get(max.queueNr).getSize() - 1).getProcessTime() >= max
								.getProcessTime()) {
					break;
				}
				queues.get(queues.size() - 1)
						.addTask(queues.get(max.queueNr).getTask(queues.get(max.queueNr).getSize() - 1));
				queues.get(max.queueNr).removeTask(queues.get(max.queueNr).getSize() - 1);
			}
		}
	}

	public void timePasses(boolean simulationOver) {
		int max = 0;
		simulationTime++;
		for (int i = 0; i < queues.size(); i++) {
			if (queues.get(i).getSize() > 0) {
				queues.get(i).getTask(0).decrement();
			}
			else{
				if(simulationOver == false){
					queueEmpty();
				}
			}
		}

		for (int i = 0; i < queues.size(); i++) {
			max = max + queues.get(i).getProcessTime();
		}

		if (max > peakVal) {
			peakVal = max;
			peakHour = simulationTime;
		}
	}

	private void queueEmpty() {
		queueEmptyTime++;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < queues.size(); i++) {
			s = s + queues.get(i).getProcessTime() + "\n";
		}
		return s;
	}

	public int getNrOfQueues() {
		return queues.size();
	}

	public Queue getQueue(int i) {
		return queues.get(i);
	}

	public void stopThreads() {
		for (int i = 0; i < queues.size(); i++) {
			queues.get(i).stopThread = true;
		}
	}

	public void closeQueue(int i) {
		closedQueues[i]=1;
	}
	
	public int getPeak(){
		return peakVal;
	}
}
