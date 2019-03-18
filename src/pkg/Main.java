package pkg;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	static Listener listener = new Listener();
	static JFrame frame;
	Container contentPane;
	
	static JPanel pnlStats;
	static JLabel lbPopulation;
	static JLabel lbStep;
		
	static JPanel pnlField;
	static JButton btField;
	
	static JPanel pnlControl;
		
	static JPanel pnlControlFieldSize;
	static JLabel lbFieldSize;
	static JTextField tfFieldSize;
		
	static JPanel pnlControlRun;
	static JButton btBack;
	static JButton btRun;
	static JButton btForwards;
	
	static JPanel pnlControlSpeed;
	static JLabel lbSpeed;
	static JTextField tfSpeed;
			
	static int fieldSize = 20;
	static double speed = 1;
	static Instant last = Instant.now();
	static int population = 0;
	
	static int currentStep = 0;
	
	static List<JButton> btList;
	
	static int[][] btStateArray = new int[fieldSize][fieldSize];
	static List<int[][]> btStateList = new ArrayList<int[][]>();
	
	static Color colorActive = new Color(0, 204, 153);
	static Color colorDead = new Color(104,104,104);
	static Color colorBorder = new Color(200, 200, 200);
	
	static Color colorBckgr = new Color(104,104,104);
	static Color colorForgr = new Color(242, 242, 242);
	
	static Color colorBtBckgr = new Color(0, 204, 153);
	static Color colorRedBtBckgr = new Color(252, 118, 103);
	
	static Color colorTfBckgr = new Color(128, 128, 128);
	static Color colorTfForgr = new Color(128, 193, 255);
	
	public static void main(String[] args) {
		Main x = new Main();
		x.Gui();
	}
	
	public void Gui() {
		btStateList.add(currentStep, btStateArray);
		currentStep += 1;
		
		frame = new JFrame("Game of Life");
		contentPane = getContentPane();
				
		pnlStats = new JPanel();
		pnlStats.setBackground(colorBckgr);
			
		lbPopulation = new JLabel("Population: " + population + "               ");
		lbPopulation.setForeground(colorForgr);
		lbPopulation.setFont(new Font("Century Gothic", Font.BOLD, 20));
		
		lbStep = new JLabel("Step: 0");
		lbStep.setForeground(colorForgr);
		lbStep.setFont(new Font("Century Gothic", Font.BOLD, 20));
		
		pnlField = new JPanel();
		pnlField.setBackground(colorBckgr);
		
		btList = new ArrayList<JButton>(fieldSize * fieldSize);
		
		for (int i = 0; i < fieldSize; i++) {
			for (int j = 0; j < fieldSize; j++) {
				btField = new JButton();
				pnlField.add(btField);
				btList.add(btField);
				
				btField.setBorder(BorderFactory.createLineBorder(colorBorder, 1));
				
				btField.setOpaque(true);
				btField.setBorderPainted(true);
				
				if (btStateArray[i][j] == 1) {
					btField.setBackground(colorActive);
					btField.setForeground(colorActive);
				} else {
					btField.setBackground(colorDead);
					btField.setForeground(colorDead);
				}

				btField.addActionListener(listener);
			}
		}
					
		pnlControl = new JPanel();
		pnlControl.setBackground(colorBckgr);
		
		pnlControlFieldSize = new JPanel();
		pnlControlFieldSize.setBackground(colorBckgr);

		lbFieldSize = new JLabel("Field Size:");
		lbFieldSize.setForeground(colorForgr);
		lbFieldSize.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				
		tfFieldSize = new JTextField(6);
		tfFieldSize.setBackground(colorTfBckgr);
		tfFieldSize.setForeground(colorTfForgr);
		tfFieldSize.setBorder(BorderFactory.createLineBorder(colorBckgr, 1));
		tfFieldSize.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfFieldSize.setEditable(true);
		tfFieldSize.addActionListener(Listener.action);
		tfFieldSize.setText("" + fieldSize);
			
		pnlControlRun = new JPanel();
		pnlControlRun.setBackground(colorBckgr);
				
		btBack = new JButton("<"); 
		btBack.setBackground(colorBtBckgr);
		btBack.setForeground(colorForgr);
		btBack.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btBack.addActionListener(listener);
		
		btRun = new JButton("RUN");
		btRun.setBackground(colorBtBckgr);
		btRun.setForeground(colorForgr);
		btRun.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btRun.addActionListener(listener);
				
		btForwards = new JButton(">");
		btForwards.setBackground(colorBtBckgr);
		btForwards.setForeground(colorForgr);
		btForwards.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btForwards.addActionListener(listener);
		
		pnlControlSpeed = new JPanel();
		pnlControlSpeed.setBackground(colorBckgr);
			
		lbSpeed = new JLabel("Speed:");
		lbSpeed.setForeground(colorForgr);
		lbSpeed.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				
		tfSpeed = new JTextField(6);
		tfSpeed.setBackground(colorTfBckgr);
		tfSpeed.setForeground(colorTfForgr);
		tfSpeed.setBorder(BorderFactory.createLineBorder(colorBckgr, 1));
		tfSpeed.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfSpeed.setEditable(true);
		tfSpeed.setText("" + speed);
		
		contentPane.add(pnlStats, BorderLayout.NORTH);	
		pnlStats.add(lbPopulation, BorderLayout.WEST);
		pnlStats.add(lbStep, BorderLayout.EAST);
		
		contentPane.add(pnlField, BorderLayout.CENTER);
		pnlField.setLayout(new GridLayout(fieldSize, fieldSize, -1, -1));
				
		contentPane.add(pnlControl, BorderLayout.SOUTH);
		pnlControl.add(pnlControlFieldSize, BorderLayout.WEST);
		pnlControlFieldSize.add(lbFieldSize, BorderLayout.NORTH);
		pnlControlFieldSize.add(tfFieldSize, BorderLayout.SOUTH);
		pnlControl.add(pnlControlRun, BorderLayout.CENTER);
		pnlControlRun.add(btBack, BorderLayout.WEST);
		pnlControlRun.add(btRun, BorderLayout.CENTER);
		pnlControlRun.add(btForwards, BorderLayout.EAST);
		pnlControl.add(pnlControlSpeed, BorderLayout.WEST);
		pnlControlSpeed.add(lbSpeed, BorderLayout.NORTH);
		pnlControlSpeed.add(tfSpeed, BorderLayout.SOUTH);
				
		frame.getContentPane().add(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 1000);
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		btForwards.setOpaque(true);
		btBack.setOpaque(true);
		btRun.setOpaque(true);
		btForwards.setBorderPainted(false);
		btBack.setBorderPainted(false);
		btRun.setBorderPainted(false);
	}
}