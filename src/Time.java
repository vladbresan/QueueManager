import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Time {
	javax.swing.Timer t;
	TaskGenerator taskGen;
	Manager manag;
	int delay, currentTime;
	boolean timerStop = false;
	final int simulation;

	public Time(TaskGenerator taskGen, Manager manag, final int simulation) {
		this.taskGen = taskGen;
		this.manag = manag;
		this.simulation = simulation;

		t = new javax.swing.Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentTime < simulation) {
					timePasses();
					currentTime++;
				} else
					empty();
			}
		});
	}

	private void timePasses() {
		taskGen.timePasses();
		manag.timePasses(false);
	}

	public void start(){
		t.start();
	}
	
	public void stop(){
		t.stop();
	}
	
	public void empty(){
		boolean stop = true;
		manag.timePasses(true);
		for(int i=0;i<manag.getNrOfQueues();i++){
			if(manag.getQueue(i).getSize()>0)
				stop = false;
		}
		if(stop == true){
			delay++;
			if(delay==1){
				t.stop();
				manag.stopThreads();
				timerStop = true;
			}
		}
	}
	public boolean timerStopped(){
		return timerStop;
	}
}
