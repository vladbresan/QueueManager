
public class TaskGenerator {
	private int minProcessTime,maxProcessTime;
	private int minArrivalTime,maxArrivalTime;
	private int arrival = 0;
	private int random;
	Manager manag;
	
	public TaskGenerator(int minArrivalTime,int maxArrivalTime,int minProcessTime,int maxProcessTime,Manager manag,int init){
		this.minArrivalTime = minArrivalTime;
		this.maxArrivalTime = maxArrivalTime;
		this.minProcessTime = minProcessTime;
		this.maxProcessTime = maxProcessTime;
		random = (minArrivalTime + (int)(Math.random()*(maxArrivalTime-minArrivalTime)));
		this.manag = manag;
		if(init>0){
			for(int i=0;i<init;i++){
				addTask();
			}
		}
	}

	public void addTask(){
		manag.addTask(new Task(minProcessTime,maxProcessTime));
	}
	
	public void timePasses(){
		arrival++;
		if(arrival == random){
			addTask();
			arrival = 0;
			random = minArrivalTime +(int)(Math.random()*(maxArrivalTime-minArrivalTime));
		}
	}
}
