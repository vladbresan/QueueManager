import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GUI {

	private JFrame frmQueueSimulation;
	Manager manag;
	TaskGenerator taskGen;
	Time t;
	Timer t1;
	int simProgress=0;
	private JTextField nrOfClientsField;
	private JTextField nrOfQueuesField;
	private JTextField simulationField;
	private JTextField minProcessTimeField;
	private JTextField maxProcessTimeField;
	private JTextField minArrivalTimeField;
	private JTextField maxArrivalTimeField;
	private JProgressBar progressBar;
	private JButton btnAddQueue;
	private JButton btnNewButton;
	private JTextField textArea;
	private JButton btnCloseQueue;
	private JTextField textField;

	public GUI() {
		initialize();
	}

	
	private void initialize() {
		frmQueueSimulation = new JFrame();
		frmQueueSimulation.setTitle("Queue simulation");
		frmQueueSimulation.setBounds(100, 100, 900, 291);
		frmQueueSimulation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQueueSimulation.getContentPane().setLayout(null);
		
		JLabel lblNumberOfClients = new JLabel("Number of clients");
		lblNumberOfClients.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfClients.setBounds(486, 30, 103, 24);
		frmQueueSimulation.getContentPane().add(lblNumberOfClients);
		
		JLabel lblNumberOfQueues = new JLabel("Number of queues");
		lblNumberOfQueues.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfQueues.setBounds(486, 65, 103, 24);
		frmQueueSimulation.getContentPane().add(lblNumberOfQueues);
		
		JLabel lblSimulationTime = new JLabel("Simulation time");
		lblSimulationTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimulationTime.setBounds(486, 100, 103, 24);
		frmQueueSimulation.getContentPane().add(lblSimulationTime);
		
		nrOfClientsField = new JTextField();
		nrOfClientsField.setBounds(589, 32, 86, 20);
		frmQueueSimulation.getContentPane().add(nrOfClientsField);
		nrOfClientsField.setColumns(10);
		
		nrOfQueuesField = new JTextField();
		nrOfQueuesField.setColumns(10);
		nrOfQueuesField.setBounds(589, 67, 86, 20);
		frmQueueSimulation.getContentPane().add(nrOfQueuesField);
		
		simulationField = new JTextField();
		simulationField.setColumns(10);
		simulationField.setBounds(589, 102, 86, 20);
		frmQueueSimulation.getContentPane().add(simulationField);
		
		JLabel lblMinProcessTime = new JLabel("Min process time");
		lblMinProcessTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinProcessTime.setBounds(685, 11, 103, 24);
		frmQueueSimulation.getContentPane().add(lblMinProcessTime);
		
		JLabel lblMaxProcessTime = new JLabel("Max process time");
		lblMaxProcessTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaxProcessTime.setBounds(685, 46, 103, 24);
		frmQueueSimulation.getContentPane().add(lblMaxProcessTime);
		
		JLabel lblMinArrivalTime = new JLabel("Min arrival time");
		lblMinArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinArrivalTime.setBounds(685, 81, 103, 24);
		frmQueueSimulation.getContentPane().add(lblMinArrivalTime);
		
		JLabel lblMaxArrivalTime = new JLabel("Max arrival time");
		lblMaxArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaxArrivalTime.setBounds(685, 116, 103, 24);
		frmQueueSimulation.getContentPane().add(lblMaxArrivalTime);
		
		minProcessTimeField = new JTextField();
		minProcessTimeField.setColumns(10);
		minProcessTimeField.setBounds(788, 13, 86, 20);
		frmQueueSimulation.getContentPane().add(minProcessTimeField);
		
		maxProcessTimeField = new JTextField();
		maxProcessTimeField.setColumns(10);
		maxProcessTimeField.setBounds(788, 48, 86, 20);
		frmQueueSimulation.getContentPane().add(maxProcessTimeField);
		
		minArrivalTimeField = new JTextField();
		minArrivalTimeField.setColumns(10);
		minArrivalTimeField.setBounds(788, 83, 86, 20);
		frmQueueSimulation.getContentPane().add(minArrivalTimeField);
		
		maxArrivalTimeField = new JTextField();
		maxArrivalTimeField.setColumns(10);
		maxArrivalTimeField.setBounds(788, 118, 86, 20);
		frmQueueSimulation.getContentPane().add(maxArrivalTimeField);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int minArrival = Integer.parseInt(minArrivalTimeField.getText());
				int maxArrival = Integer.parseInt(maxArrivalTimeField.getText());
				int minProcess = Integer.parseInt(minProcessTimeField.getText());
				int maxProcess = Integer.parseInt(maxProcessTimeField.getText());
				int simulationTime = Integer.parseInt(simulationField.getText());
				int nrOfQueues = Integer.valueOf(nrOfQueuesField.getText());
				int nrOfTasks = Integer.valueOf(nrOfClientsField.getText());
				simProgress = simulationTime;
				manag = new Manager(nrOfQueues);
				taskGen = new TaskGenerator(minArrival, maxArrival, minProcess, maxProcess, manag, nrOfTasks);
				t= new Time(taskGen,manag,simulationTime);
				update();
			
				
			}
		});
		btnSubmit.setBounds(557, 150, 118, 41);
		frmQueueSimulation.getContentPane().add(btnSubmit);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 202, 864, 24);
		frmQueueSimulation.getContentPane().add(progressBar);
		
		btnAddQueue = new JButton("Add queue");
		btnAddQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manag.addQueue();
				manag.redistribute();
			}
		});
		btnAddQueue.setBounds(328, 82, 103, 23);
		frmQueueSimulation.getContentPane().add(btnAddQueue);
		
		btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.start();
				t1.start();
			}
		});
		btnNewButton.setBounds(710, 151, 118, 41);
		frmQueueSimulation.getContentPane().add(btnNewButton);
		
		textArea = new JTextField();
		textArea.setBounds(10, 30, 421, 40);
		frmQueueSimulation.getContentPane().add(textArea);
		textArea.setColumns(10);
		
		btnCloseQueue = new JButton("Close queue:");
		btnCloseQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manag.closeQueue(Integer.parseInt(textField.getText()));
			}
		});
		btnCloseQueue.setBounds(10, 82, 103, 23);
		frmQueueSimulation.getContentPane().add(btnCloseQueue);
		
		textField = new JTextField();
		textField.setBounds(119, 83, 52, 20);
		frmQueueSimulation.getContentPane().add(textField);
		textField.setColumns(10);
	}

	public void update(){
		t1=new javax.swing.Timer(1000,new ActionListener() {
			int counter=0;
			@Override
			public void actionPerformed(ActionEvent e) {
				counter++;
				textArea.setText(manag.toString());
				progressBar.setValue(100/simProgress*counter);
				if(t.timerStopped()==true){
					t1.stop();
					System.out.println("Peak process time was: " + Manager.peakVal);
					System.out.println("Peak hour was: " + Manager.peakHour);
					int avg1= Manager.queueEmptyTime/Integer.parseInt(nrOfQueuesField.getText());
					System.out.println("Average empty time was: " + avg1);
				}
			}
		});
	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmQueueSimulation.setVisible(true);
					
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
