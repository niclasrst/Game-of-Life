package pkg;

import java.time.Instant;

public class Run implements Runnable {
	Thread thread;
	
	Run() {
		thread = new Thread(this, "Run");
	    System.out.println("[+] Thread created: " + thread.getName());
	    thread.start();
	}
	
	@Override
	public void run() {
		try {
			do {
				if (!(Main.tfSpeed.getText().equals("")) && Double.parseDouble(Main.tfSpeed.getText()) < 500) {
					Main.speed = Double.parseDouble(Main.tfSpeed.getText());
				}
				
				if (Math.floor((Instant.now().toEpochMilli() - Main.last.toEpochMilli())) > 1000/Main.speed) {
					Utilities.visualizeStep(Main.currentStep);
					Main.last = Instant.now();
				}
			} while (Listener.running);
		} catch (Exception e) {
			System.out.println("[-] Error [run]: " + e.getMessage());
		}
	}
	
}
