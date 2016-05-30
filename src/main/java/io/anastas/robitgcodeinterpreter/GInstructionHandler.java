package io.anastas.robitgcodeinterpreter;

import java.io.IOException;
import java.util.ArrayList;

import com.ergotech.brickpi.BrickPi;
import com.ergotech.brickpi.motion.Motor;
import com.ergotech.brickpi.motion.MotorPort;
import com.ergotech.brickpi.sensors.*;

public class GInstructionHandler {
	
	
	
	public static void executeGInstructions(ArrayList<GInstruction> instructionList) {
		BrickPi brickPi = null;
		try {
			brickPi = new BrickPi();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Motor xAxis = new Motor();
		Motor yAxis = new Motor();
		Motor zAxis = new Motor();
		brickPi.setMotor(xAxis, MotorPort.MA);
		brickPi.setMotor(yAxis, MotorPort.MB);
		brickPi.setMotor(zAxis, MotorPort.MC);
		TouchSensor xTouch = new TouchSensor();
		TouchSensor yTouch = new TouchSensor();
		brickPi.setSensor(xTouch, SensorPort.S1);
		brickPi.setSensor(yTouch, SensorPort.S2);
		/**while (!xTouch.isSet()) {
			xAxis.rotate(-0.01, 5);
		}
		while (!yTouch.isSet()) {
			yAxis.rotate(-0.01, 5);
		}**/
		double xyRotationToMM = 1/127.2;
		// Loop through each element of the list and execute
		double curX, curY, curZ, curE;
		for (int i=0; i<instructionList.size(); i++) {
			GInstruction curInstruction = instructionList.get(i);
			
			// Update position variables
			if (curInstruction.xcoord != 0) curX = curInstruction.xcoord;
			if (curInstruction.ycoord != 0) curY = curInstruction.ycoord;
			if (curInstruction.zcoord != 0) curZ = curInstruction.zcoord;
			if (curInstruction.extrude != 0) curE = curInstruction.extrude;

			
			System.out.println("Executing Instruction type: " + curInstruction.type);
			//System.out.println("Previous type was: " + instructionList.get(i-1).type);
			if (curInstruction.type.equals("G1")) {
				//xAxis.rotate((curInstruction.xcoord-prevInstruction.xcoord)*xyRotationToMM, 5);
			}
		}
	}
}
