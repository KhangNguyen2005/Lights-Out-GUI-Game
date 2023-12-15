package assign09;

import java.awt.Graphics;
import java.io.Serializable;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Custom JButton representing a light in the Lights Out puzzle. This button can
 * be toggled between on and off states.
 * 
 * @author Khang Hoang Nguyen
 * @version Nov 17 2023
 */
public class LightsOutButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int rows;
	private int columns;
	private boolean isOn;

	public LightsOutButton(int rows, int columns) {
		super();
		this.setIcon(new ImageIcon("lightbulbOff.png"));
		this.rows = rows;
		this.columns = columns;
		this.isOn = false;

	}

	/**
	 * Toggles the state of the button between on and off. Updates the icon
	 * accordingly.
	 */
	void toggle() {
		if (this.isOn == true) {
			this.isOn = false;
			// this.setText("off");
			this.setIcon(new ImageIcon("lightbulbOff.png"));

		} else {
			this.isOn = true;
			// this.setText("on");
			this.setIcon(new ImageIcon("on.png"));
		}
	}

	/**
	 * Gets the row index of the button.
	 * 
	 * @return The row index.
	 */
	int getRow() {
		return this.rows;
	}

	/**
	 * Gets the column index of the button.
	 * 
	 * @return The column index.
	 */
	int getColumn() {
		return this.columns;
	}

	/**
	 * Checks if the button is in the on state.
	 * 
	 * @return True if the button is on, false if button is off.
	 */
	boolean isOn() {
		return this.isOn;
	}

}
