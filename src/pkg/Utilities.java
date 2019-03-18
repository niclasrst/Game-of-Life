package pkg;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Utilities {
	public static int[][] nextStep(int[][] btStateArray) {
		int[][] ret = new int[Main.fieldSize][Main.fieldSize];
		
		for (int i = 0; i < Main.fieldSize; i++) {
			for (int j = 0; j < Main.fieldSize; j++) {

				int count = 0;
				try {
					if (i > 0 && j > 0 && btStateArray[i-1][j-1] == 1) { count++; } 						// top left
					if (i > 0 && btStateArray[i-1][j] == 1) { count++; } 									// Top
					if (i > 0 && j < Main.fieldSize - 1 && btStateArray[i-1][j+1] == 1) { count++; } 			// top right
					if (j > 0 && btStateArray[i][j-1] == 1) { count++; } 									// left
					if (j < Main.fieldSize - 1 && btStateArray[i][j+1] == 1) { count++; } 						// right
					if (i < Main.fieldSize - 1 && j > 0 && btStateArray[i+1][j-1] == 1) { count++; } 			// bottom left
					if (i < Main.fieldSize - 1 && btStateArray[i+1][j] == 1) { count++; } 						// bottom
					if (i < Main.fieldSize - 1 && j < Main.fieldSize - 1 && btStateArray[i+1][j+1] == 1) { count++; } // bottom right
					
					if (btStateArray[i][j] == 1) {
						if (count < 2 || count > 3) { ret[i][j] = 0; }
						else { ret[i][j] = 1; }
					} else {
						if (count == 3) { ret[i][j] = 1; }
						else { ret[i][j] = 0; }
					}
				} catch (Exception e) {
					ret[i][j] = 0;
				}
			}
		}
		return ret;
	}
	
	public static void visualizeStep(int step) {
		Main.pnlField.removeAll();
		Main.btList.clear();

		Main.btStateArray = nextStep(Main.btStateArray);
		Main.btStateList.add(step, Main.btStateArray);

		Main.lbStep.setText("Step: " + Main.currentStep);
		Main.lbPopulation.setText("Population: " + Utilities.population(Main.btStateArray) + "               ");

		for (int i = 0; i < Main.fieldSize; i++) {
			for (int j = 0; j < Main.fieldSize; j++) {
				Main.btField = new JButton();
				Main.btField.setVisible(false);

				Main.pnlField.add(Main.btField);
				Main.btList.add(Main.btField);

				Main.btField.setBorder(BorderFactory.createLineBorder(Main.colorBorder, 1));

				Main.btField.setOpaque(true);
				Main.btField.setBorderPainted(true);

				if (Main.btStateList.get(step)[i][j] == 1) {
					Main.btField.setBackground(Main.colorActive);
					Main.btField.setForeground(Main.colorActive);
				} else {
					Main.btField.setBackground(Main.colorDead);
					Main.btField.setForeground(Main.colorDead);
				}
				Main.btField.addActionListener(Main.listener);
			}
		}

		for (int i = 0; i < Main.btList.size(); i++) {
			Main.btList.get(i).setVisible(true);
		}

		Main.currentStep++;
	}
		
	public static void visualizePreviousStep() {
		Main.pnlField.removeAll();
		Main.btList.clear();
		
		if (Main.currentStep > 1) {
			Main.btStateArray = Main.btStateList.get(Main.currentStep - 2);		
			Main.lbStep.setText("Step: " + (Main.currentStep - 2));
		}
		
		for (int i = 0; i < Main.fieldSize; i++) {
			for (int j = 0; j < Main.fieldSize; j++) {
				Main.btField = new JButton();
				Main.pnlField.add(Main.btField);
				Main.btList.add(Main.btField);
				
				Main.btField.setBorder(BorderFactory.createLineBorder(Main.colorBorder, 1));
				
				Main.btField.setOpaque(true);
				Main.btField.setBorderPainted(true);

				if (Main.btStateArray[i][j] == 1) {
					Main.btField.setBackground(Main.colorActive);
					Main.btField.setForeground(Main.colorActive);
				} else {
					Main.btField.setBackground(Main.colorDead);
					Main.btField.setForeground(Main.colorDead);
				}
				
				Main.btField.addActionListener(Main.listener);
			}
		}
		
		if (Main.currentStep > 1) { Main.currentStep -= 1; }
	}
	
	public static void drawGameBoard(int fieldsize) {
		
		Main.pnlField.removeAll();
		Main.pnlField.setLayout(new GridLayout(Main.fieldSize, Main.fieldSize, -1, -1));
		Main.btStateArray = new int[fieldsize][fieldsize];
		Main.btStateList.clear();
		Main.btList.clear();
		Main.currentStep = 0;
		Main.lbStep.setText("Step: " + Main.currentStep);
		
		for (int i = 0; i < Main.fieldSize; i++) {
			for (int j = 0; j < Main.fieldSize; j++) {
				
				Main.pnlField.repaint();
				
				Main.btField = new JButton();
				Main.pnlField.add(Main.btField);
				Main.btList.add(Main.btField);
				
				Main.btField.setBorder(BorderFactory.createLineBorder(Main.colorBorder, 1));
				
				Main.btField.setOpaque(true);
				Main.btField.setBorderPainted(true);

				Main.btField.setBackground(Main.colorDead);
				Main.btField.setForeground(Main.colorDead);
				
				Main.btField.addActionListener(Main.listener);
			}
		}
		
		Main.lbPopulation.setText("Population: " + Utilities.population(Main.btStateArray) + "               ");
	}
	
	static int population(int btState[][]) {
		int count = 0;
        
        for (int i = 0; i < Main.fieldSize; i++) {
        	for (int j = 0; j < Main.fieldSize; j++) {
                if (Main.btStateArray[i][j] == 1) { count++; }
            }
        }
        
        return count;
    }
}
