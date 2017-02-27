package hu.bme.mit.train.controller;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.TrainController;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private Table table = HashBasedTable.create();

	@Override
	public void followSpeed() {


		referenceSpeed += step;


		if (referenceSpeed < 0)
		referenceSpeed = 0;

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void printReferenceSpeed(){

		System.out.println("C " + this.referenceSpeed);
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	public void tachograph(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		table.put(sdf.format(cal.getTime()), step, referenceSpeed);
	}

	public int getTableSize(){
		return this.table.size();
	}
}
