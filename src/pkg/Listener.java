package pkg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Listener implements ActionListener {
	
	public static boolean running = false;
	
	@Override
	public void actionPerformed(ActionEvent evt) {		
		if (evt.getSource() == Main.btForwards) { Utilities.visualizeStep(Main.currentStep); Main.lbPopulation.setText("Population: " + Utilities.population(Main.btStateArray)); }
		if (evt.getSource() == Main.btBack) { Utilities.visualizePreviousStep(); Main.lbPopulation.setText("Population: " + Utilities.population(Main.btStateArray)); }
		
		if (evt.getSource() == Main.btRun && !running) {
			new Run();
			running = true;
			Main.btRun.setText("STOP");
			Main.btRun.setBackground(Main.colorRedBtBckgr);
		} else if (evt.getSource() == Main.btRun && running) {
			running = false;
			Main.btRun.setText("RUN");
			Main.btRun.setBackground(Main.colorBtBckgr);
		}
		
		for (int i = 0; i < Main.btList.size(); i++) {
			if (evt.getSource() == Main.btList.get(i)) {
				if (Main.btStateArray[i / Main.fieldSize][i % Main.fieldSize] == 0) {
					Main.btList.get(i).setBackground(Main.colorActive);
					Main.btList.get(i).setForeground(Main.colorActive);
					
					Main.btStateArray[i / Main.fieldSize][i % Main.fieldSize] = 1;
				} else {
					Main.btList.get(i).setBackground(Main.colorDead);
					Main.btList.get(i).setForeground(Main.colorDead);
					
					Main.btStateArray[i / Main.fieldSize][i % Main.fieldSize] = 0;
				}
			}
			
			Main.lbPopulation.setText("Population: " + Utilities.population(Main.btStateArray) + "               ");
		}
	}
	
	static Action action = new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent evt) {
	        System.out.println("[+] Action occured");
	        
	        if (!(Integer.parseInt(Main.tfFieldSize.getText()) == Main.fieldSize)) {
	        
		        if (running) {
		        	running = false;
			        Main.btRun.setText("Start");
					Main.btRun.setBackground(Main.colorBtBckgr);
		        }
		        
		        if (!(Main.tfFieldSize.getText().equals("")) && Integer.parseInt(Main.tfFieldSize.getText()) >= 1) {
		        	Main.fieldSize = Integer.parseInt(Main.tfFieldSize.getText());
		        }
		        
		        Utilities.drawGameBoard(Main.fieldSize);
	        }
	    }
	};
}
