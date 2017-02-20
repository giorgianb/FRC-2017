package org.usfirst.frc.team5149.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;



public class Robot extends SampleRobot {
	RobotDrive wheels = new RobotDrive(MotorPorts.LEFT, MotorPorts.RIGHT);
	Talon ballHopper = new Talon(MotorPorts.BALL_HOPPER);
	Talon ropeClimber = new Talon(MotorPorts.ROPE_CLIMBER);
	Joystick driveStick = new Joystick(JoystickPorts.DRIVER);
	Joystick manipStick = new Joystick(JoystickPorts.MANIPULATOR);
	boolean directionFlipped = false;
	boolean test = true;
	
	public Robot() {
		wheels.setExpiration(0.1);
	}

	@Override
	public void robotInit() {
	}


	@Override
	public void operatorControl() {
		wheels.setSafetyEnabled(true);
		ballHopper.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			double leftPower = -driveStick.getRawAxis(DriverJoystick.LEFT_POWER_AXIS);
			double rightPower = -driveStick.getRawAxis(DriverJoystick.RIGHT_POWER_AXIS);
			System.out.printf("leftPower = %f\t\trightPower = %f\n", leftPower, rightPower);
			if (!test) {
				if (driveStick.getRawButton(DriverJoystick.DIRECTION_FLIP_BUTTON))
					directionFlipped = !directionFlipped;
				
				if (directionFlipped)
					wheels.tankDrive(rightPower, leftPower);
				else
					wheels.tankDrive(leftPower,  rightPower);
			}
			System.out.printf("ballPower = %f\nropePower=%f\n", -manipStick.getRawAxis(ManipulatorJoystick.BALL_HOPPER_POWER_AXIS),
					-manipStick.getRawAxis(ManipulatorJoystick.ROPE_CLIMBER_POWER_AXIS));
					
			if (!test) {
				ballHopper.set(-manipStick.getRawAxis(ManipulatorJoystick.BALL_HOPPER_POWER_AXIS));
				ropeClimber.set(-manipStick.getRawAxis(ManipulatorJoystick.ROPE_CLIMBER_POWER_AXIS));
			}
			
			if (!test)
				Timer.delay(0.005); // wait for a motor update time
			else
				Timer.delay(.5);
		}
	}
}
