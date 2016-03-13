
package org.usfirst.frc.team6070.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot {
	Victor BL = new Victor (3);
	Victor BR = new Victor (2);
	Victor FL = new Victor (1);
	Victor FR = new Victor (0);
	Timer myTimer = new Timer();
	RobotDrive drive = new RobotDrive(BL, FL, BR, FR);
	Talon arm = new Talon (4);
	//DigitalInput down = new DigitalInput(5);
	//DigitalInput up = new DigitalInput(6);
	Joystick joy = new Joystick (0);
	Joystick armjoy = new Joystick(1);
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
    	
    	double speed = 0.4;
    	if (myTimer.get() < 0.8)
    	{
    		drive.mecanumDrive_Cartesian(0,speed, 0, 0);
    	}
    	else if (myTimer.get() < 1.5){
    		drive.mecanumDrive_Cartesian(0,-speed, 0, 0);
    	}
    	else if (myTimer.get() < 6.8)
    	{
    		drive.mecanumDrive_Cartesian(-speed, 0, 0,0);
    	}
    	else
    	{
    		drive.mecanumDrive_Cartesian(0, 0, 0, 0);
    	}
//    	if (myTimer.get() < 3)
//    	{
//    		drive.mecanumDrive_Cartesian(0,speed, 0, 0);
//    	}
//    	else if (myTimer.get() < 6)
//    	{
//    		drive.mecanumDrive_Cartesian(0, -speed, 0, 0);
//    	}

    	


    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	if (!horiz)
    	{
    		drive.mecanumDrive_Cartesian(-joy.getX(), joy.getY(), joy.getTwist(), 0);
    	}
    	else
   		{
   			drive.mecanumDrive_Cartesian(-joy.getY(), joy.getX(), joy.getTwist(), 0);
   		}
    	if (joy.getThrottle() < 0.9)
    	{
    		horiz = true;
    	}
    	else 
    	{
    		horiz = false;
    	}
    	if (armjoy.getY() > 0.1 )// down.get() == true)
    	{
    		arm.set(0.3);
    	}
    	else if (armjoy.getY() < -0.1)// && up.get() == true)
    	{
    		arm.set(-0.5);
    	}
    	else	
    	{
    		arm.set(0);
    	}
    	Timer.delay(0.01);
    }
}
