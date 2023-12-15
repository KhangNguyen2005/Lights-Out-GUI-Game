package assign09;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.random.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Represents the main frame of the Lights Out puzzle game.
 * 
 * @author Khang Hoang Nguyen
 * @version Nov 17 2023
 */
public class LightsOutFrame extends JFrame implements ActionListener {

	private LightsOutButton[][] lightGrid;
	private JButton randomizeButton;
	private JButton manualButton;
	private boolean puzzleMode;

	public LightsOutFrame() {
		this.puzzleMode = false;

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel lightPanel = new JPanel();
		lightPanel.setLayout(new GridLayout(5, 5));
		lightPanel.setPreferredSize(new Dimension(600, 600));
		lightGrid = new LightsOutButton[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				LightsOutButton button = new LightsOutButton(i, j);

				this.lightGrid[i][j] = button;
				button.addActionListener(this);

				lightPanel.add(button);

			}
		}

		JPanel modePanel = new JPanel();
		modePanel.setPreferredSize(new Dimension(600, 600));
		randomizeButton = new JButton();
		randomizeButton.setText("Randomize");
		modePanel.add(randomizeButton);

		manualButton = new JButton("Enter manual setup");
		modePanel.add(manualButton);

		JPanel framePanel = new JPanel();
		framePanel.add(lightPanel);
		framePanel.add(modePanel);
		framePanel.setPreferredSize(new Dimension(700, 700));

		randomizeButton.addActionListener(this);

		manualButton.addActionListener(this);
		randomize();

		checkGame();

		this.setTitle("Light");
		this.setContentPane(framePanel);
		this.setPreferredSize(new Dimension(750, 750));
		this.pack();

	}

	/**
	 * Randomizes the initial state of the puzzle by toggling random lights.
	 */
	void randomize() {
		// toggle neighbors
		for (int i = 0; i < 10; i++) {
			Random random = new Random();
			int row = random.nextInt(5);
			int col = random.nextInt(5);
			lightGrid[row][col].toggle();

			if (row == 0 && col == 0) {
				this.lightGrid[row + 1][col].toggle();
				this.lightGrid[row][col + 1].toggle();

			} else if (row == 4 && col == 0) {
				this.lightGrid[row - 1][col].toggle();
				this.lightGrid[row][col + 1].toggle();

			} else if (row == 4 && col == 4) {
				this.lightGrid[row - 1][col].toggle();
				this.lightGrid[row][col - 1].toggle();

			} else if (row == 0 && col == 4) {
				this.lightGrid[row + 1][col].toggle();
				this.lightGrid[row][col - 1].toggle();
				// north edge
			} else if ((row == 0 && col == 1) || (row == 0 && col == 2) || (row == 0 && col == 3)) {
				this.lightGrid[row][col - 1].toggle();
				;
				this.lightGrid[row + 1][col].toggle();
				this.lightGrid[row][col + 1].toggle();
				// south edge
			} else if ((row == 4 && col == 1) || (row == 4 && col == 2) || (row == 4 && col == 3)) {
				this.lightGrid[row][col + 1].toggle();
				this.lightGrid[row][col - 1].toggle();
				this.lightGrid[row - 1][col].toggle();
				// left edge
			} else if ((row == 1 && col == 0) || (row == 2 && col == 0) || (row == 3 && col == 0)) {
				this.lightGrid[row + 1][col].toggle();
				this.lightGrid[row - 1][col].toggle();
				this.lightGrid[row][col + 1].toggle();
				// right edge
			} else if ((row == 1 && col == 4) || (row == 2 && col == 4) || row == 3 && col == 4) {
				this.lightGrid[row + 1][col].toggle();
				this.lightGrid[row - 1][col].toggle();
				this.lightGrid[row][col - 1].toggle();

			}

		}

	}

	/**
	 * Checks if a light at the specified position is on.
	 * 
	 * @param row    - The row index of the light.
	 * @param column - The column index of the light.
	 * @return true if the light is on, false otherwise.
	 */
	boolean lightIsOn(int row, int column) {
		if (this.lightGrid[row][column].isOn()) {

			return true;
		} else if ((row > 4 || row < 0) || (column > 4 || column < 0)) {
			throw new IndexOutOfBoundsException("Row or Column is out of bound");
		} else {
			return false;
		}
	}

	/**
	 * Toggles the state of the light at the specified position and its neighbors.
	 * 
	 * @param row    - The row index of the light.
	 * @param column - column The column index of the light.
	 */
	void toggleLight(int row, int column) {

		lightGrid[row][column].toggle();
		if (row == 0 && column == 0) {
			lightGrid[row + 1][column].toggle();
			lightGrid[row][column + 1].toggle();
		} else if (row == 4 && column == 0) {
			lightGrid[row - 1][column].toggle();
			lightGrid[row][column + 1].toggle();
		} else if (row == 4 && column == 4) {
			lightGrid[row - 1][column].toggle();
			lightGrid[row][column - 1].toggle();
		} else if (row == 0 && column == 4) {
			lightGrid[row + 1][column].toggle();
			lightGrid[row][column - 1].toggle();
		} else if (row == 0) {
			lightGrid[row + 1][column].toggle();
			lightGrid[row][column - 1].toggle();
			lightGrid[row][column + 1].toggle();
		} else if (row == 4) {
			lightGrid[row - 1][column].toggle();
			lightGrid[row][column - 1].toggle();
			lightGrid[row][column + 1].toggle();
		} else if (column == 0) {
			lightGrid[row][column + 1].toggle();
			lightGrid[row - 1][column].toggle();
			lightGrid[row + 1][column].toggle();
		} else if (column == 4) {
			lightGrid[row][column - 1].toggle();
			lightGrid[row - 1][column].toggle();
			lightGrid[row + 1][column].toggle();
		} else {
			lightGrid[row][column - 1].toggle();
			lightGrid[row][column + 1].toggle();
			lightGrid[row - 1][column].toggle();
			lightGrid[row + 1][column].toggle();
		}

		if ((row > 4 || row < 0) || (column > 4 || column < 0)) {
			throw new IndexOutOfBoundsException("Row or Column is out of bound");

		}
	}

	/**
	 * Checks the current state of the game and displays a message if the game is
	 * won.
	 */
	void checkGame() {
		int lightOnCount = 0;
		for (int i = 0; i < lightGrid.length; i++) {
			for (int j = 0; j < lightGrid[i].length; j++) {
				if (lightGrid[i][j].isOn()) {
					lightOnCount++;
				}
			}
		}
		if (lightOnCount == 0) {
			if (puzzleMode == false) {
				JOptionPane gameCompleted = new JOptionPane();
				gameCompleted.showMessageDialog(this, "You Won The Game");
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		}
	}

	/**
	 * Handles the actionPerformed event for buttons in the frame.
	 * 
	 * @param e - The ActionEvent triggered by a button click.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == randomizeButton) {
			randomize();
		}
		if (e.getSource() == manualButton) {
			if (puzzleMode == true) {
				manualButton.setText("Enter Manual Setup");
				puzzleMode = false;
			} else {
				manualButton.setText("Exit Manual Setup");
				puzzleMode = true;
			}
		}
		if (e.getSource() instanceof LightsOutButton) {

			if (puzzleMode == false) {
				LightsOutButton buttonClicked = (LightsOutButton) e.getSource();
				int row = buttonClicked.getRow();
				int col = buttonClicked.getColumn();
				toggleLight(row, col);
				checkGame();

			} else {
				LightsOutButton buttonClicked = (LightsOutButton) e.getSource();
				buttonClicked.toggle();
				checkGame();
			}
		}

	}

	private static final long serialVersionUID = 1L;

}
