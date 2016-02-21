
package org.usfirst.frc.team6070.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot {
	Victor FL = new Victor (1);
	Victor FR = new Victor (0);
	Victor BL = new Victor (3);
	Victor BR = new Victor (2);
	Timer myTimer;
	RobotDrive drive = new RobotDrive(FR, FL, BR, BL);
	Joystick joy = new Joystick (0);
	boolean horiz = false;

    public void robotInit() {
    	drive.setInvertedMotor(MotorType.kFrontLeft, true); 
    	drive.setInvertedMotor(MotorType.kRearLeft, true);
    }
    
	
    public void autonomousInit() {
    	myTimer.reset();
    	myTimer.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	double speed = 0.2;
    	double seconds = 1;
    	double distance = 3; 
    	if (myTimer.get() < 2.0)
    	{
    		FR.set(speed);
    		FL.set(speed);
    		BL.set(speed);
    		BR.set(speed);
    	}
    	else if (myTimer.get() < 4.0){
    		FR.set(-speed);
	    	FL.set(-speed);
	    	BL.set(-speed);
	    	BR.set(-speed);
    	}
    	

//    	Timer.delay(seconds);
//    	FR.set(speed);
//    	FL.set(-speed);
//    	BL.set(-speed);
//    	BR.set(speed);
//    	Timer.delay(0.4);
//    	FR.set(speed);
//    	FL.set(speed);
//    	BL.set(speed);
//    	BR.set(speed);
//    	Timer.delay(distance);
//    	FR.set(speed);
//    	FL.set(-speed);
//    	BL.set(-speed);
//    	BR.set(speed);
//    	Timer.delay(0.4);
//    	FR.set(0);
//    	FL.set(0);
//    	BL.set(0);
//    	BR.set(0);
//    	Timer.delay(9.2);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	if (!horiz)
    	{
    		drive.mecanumDrive_Cartesian(joy.getX(), joy.getY(), joy.getTwist(), 0);
    	}
    	else
   		{
   			drive.mecanumDrive_Cartesian(2*joy.getY()/3, 2*joy.getX()/3, 2*joy.getZ()/3, 0);
   		}
    	if (joy.getThrottle() > 0.9)
    	{
    		horiz = true;
    		System.out.println("Horizontal mode activated");
    	}
    	else 
    	{
    		horiz = false;
    	}
    	Timer.delay(0.01);
    }
    
    /**
     * This function is called periodically during test mode
     */

}
