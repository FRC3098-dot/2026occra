// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Drivetrain.Drivetrain;
import frc.robot.Intake.Intake;
import frc.robot.Shooter.Shooter;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {

  private final XboxController driver_stick = new XboxController(0);
  private final XboxController operator_stick = new XboxController(1);
  public static Drivetrain m_drivetrain;
  public static Intake m_intake;
  public static Shooter m_shooter;

  /** Called once at the beginning of the robot program. */
  public Robot() {
    m_intake = new Intake();
    m_drivetrain = new Drivetrain();
    m_shooter = new Shooter();
    m_drivetrain.setDefaultCommand(new frc.robot.Drivetrain.DriveTeleop(driver_stick));
    m_intake.setDefaultCommand(new frc.robot.Intake.IntakeTeleop(m_intake, operator_stick));
    m_shooter.setDefaultCommand(new frc.robot.Shooter.ShooterTeleop(m_shooter, operator_stick));
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
