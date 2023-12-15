package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * This class contains tests for the LightsOutFrame class.
 * 
 * @author Prof. Martin and Khang Hoang Nguyen
 * @version Nov 17 2023
 */
public class LightsOutFrameTest {

	@Test
	public void testToggleLight() {
		LightsOutFrame frame = new LightsOutFrame();
		boolean initialState = frame.lightIsOn(1, 2);
		boolean initialStateNorth = frame.lightIsOn(0, 2);
		boolean initialStateEast = frame.lightIsOn(1, 3);
		boolean initialStateSouth = frame.lightIsOn(2, 2);
		boolean initialStateWest = frame.lightIsOn(1, 1);

		frame.toggleLight(1, 2);
		assertEquals(!initialState, frame.lightIsOn(1, 2));
		assertEquals(!initialStateNorth, frame.lightIsOn(0, 2));
		assertEquals(!initialStateEast, frame.lightIsOn(1, 3));
		assertEquals(!initialStateSouth, frame.lightIsOn(2, 2));
		assertEquals(!initialStateWest, frame.lightIsOn(1, 1));
	}

	@Test
	public void testToggleLightException() {
		LightsOutFrame frame = new LightsOutFrame();
		assertThrows(IndexOutOfBoundsException.class, () -> {
			frame.toggleLight(5, 3);
		});
	}

	@Test
	public void testRandomize() {
		// collect initial on/off info for each button
		LightsOutFrame frame = new LightsOutFrame();
		Boolean[] buttonStates1 = new Boolean[25];
		int index = -1;
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				buttonStates1[++index] = frame.lightIsOn(i, j);

		frame.randomize();
		// collect new on/off info for each button
		Boolean[] buttonStates2 = new Boolean[25];
		index = -1;
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				buttonStates2[++index] = frame.lightIsOn(i, j);

		assertFalse(Arrays.deepEquals(buttonStates1, buttonStates2));
	}

	@Test
	public void testActionPerformedMethod() {
		LightsOutFrame frame = new LightsOutFrame();
		boolean initialState = frame.lightIsOn(1, 2);
		frame.actionPerformed(new ActionEvent(new LightsOutButton(1, 2), 0, ""));
		assertEquals(!initialState, frame.lightIsOn(1, 2));
	}

	@Test
	public void testLeftEdgeToggleLight() {
		LightsOutFrame frame = new LightsOutFrame();
		boolean initialState = frame.lightIsOn(0, 2);
		boolean initialStateNorth = frame.lightIsOn(0, 1);
		boolean initialStateEast = frame.lightIsOn(1, 2);
		boolean initialStateSouth = frame.lightIsOn(0, 3);

		frame.toggleLight(0, 2);
		assertEquals(!initialState, frame.lightIsOn(0, 2));
		assertEquals(!initialStateNorth, frame.lightIsOn(0, 1));
		assertEquals(!initialStateEast, frame.lightIsOn(1, 2));
		assertEquals(!initialStateSouth, frame.lightIsOn(0, 3));
	}

	@Test
	public void testRightEdgeToggleLight() {
		LightsOutFrame frame = new LightsOutFrame();
		boolean initialState = frame.lightIsOn(4, 4);
		boolean initialStateWest = frame.lightIsOn(4, 3);
		boolean initialStateNorth = frame.lightIsOn(3, 4);

		frame.toggleLight(4, 4);
		assertEquals(!initialState, frame.lightIsOn(4, 4));
		assertEquals(!initialStateWest, frame.lightIsOn(4, 3));
		assertEquals(!initialStateNorth, frame.lightIsOn(3, 4));
	}

	@Test
	public void testTopLeftCornerToggleLight() {
		LightsOutFrame frame = new LightsOutFrame();
		boolean initialState = frame.lightIsOn(0, 0);
		boolean initialStateEast = frame.lightIsOn(0, 1);
		boolean initialStateSouth = frame.lightIsOn(1, 0);

		frame.toggleLight(0, 0);
		assertEquals(!initialState, frame.lightIsOn(0, 0));
		assertEquals(!initialStateEast, frame.lightIsOn(0, 1));
		assertEquals(!initialStateSouth, frame.lightIsOn(1, 0));
	}

	@Test
	public void testTopRightCornerToggleLight() {
		LightsOutFrame frame = new LightsOutFrame();
		boolean initialState = frame.lightIsOn(0, 4);
		boolean initialStateWest = frame.lightIsOn(0, 3);
		boolean initialStateSouth = frame.lightIsOn(1, 4);

		frame.toggleLight(0, 4);
		assertEquals(!initialState, frame.lightIsOn(0, 4));
		assertEquals(!initialStateWest, frame.lightIsOn(0, 3));
		assertEquals(!initialStateSouth, frame.lightIsOn(1, 4));
	}

	@Test
	public void testBottomLeftCornerToggleLight() {
		LightsOutFrame frame = new LightsOutFrame();
		boolean initialState = frame.lightIsOn(4, 0);
		boolean initialStateEast = frame.lightIsOn(4, 1);
		boolean initialStateNorth = frame.lightIsOn(3, 0);

		frame.toggleLight(4, 0);
		assertEquals(!initialState, frame.lightIsOn(4, 0));
		assertEquals(!initialStateEast, frame.lightIsOn(4, 1));
		assertEquals(!initialStateNorth, frame.lightIsOn(3, 0));
	}

	@Test
	public void testBottomRightCornerToggleLight() {
		LightsOutFrame frame = new LightsOutFrame();
		boolean initialState = frame.lightIsOn(4, 4);
		boolean initialStateWest = frame.lightIsOn(4, 3);
		boolean initialStateNorth = frame.lightIsOn(3, 4);

		frame.toggleLight(4, 4);
		assertEquals(!initialState, frame.lightIsOn(4, 4));
		assertEquals(!initialStateWest, frame.lightIsOn(4, 3));
		assertEquals(!initialStateNorth, frame.lightIsOn(3, 4));

	}

}