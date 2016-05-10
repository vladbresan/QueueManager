
public class Task {
	public int arrivalTime=0;
	public int initialProcessTime;
	private int processTime=0;
	
	public Task(int minProcessTime,int maxProcessTime){
		processTime = minProcessTime + (int) (Math.random()*(maxProcessTime - minProcessTime));
		initialProcessTime = processTime;
	}

	public void decrement(){
		processTime--;
	}
	
	void remove(){
		this.remove();
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getInitialProcessTime() {
		return initialProcessTime;
	}

	public void setInitialProcessTime(int initialProcessTime) {
		this.initialProcessTime = initialProcessTime;
	}

	public int getProcessTime() {
		return processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

	

}
