// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Drivetrain.Drivetrain;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {

  private final XboxController m_stick = new XboxController(0);
  public static Drivetrain m_drivetrain;

  /** Called once at the beginning of the robot program. */
  public Robot() {
    m_drivetrain = new Drivetrain();
    m_drivetrain.setDefaultCommand(new frc.robot.Drivetrain.DriveTeleop(m_stick));
  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
