// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Drivetrain.Drivetrain;
import frc.robot.Intake.Intake;
import frc.robot.Shooter.Shooter;

/**
 * This is the main program with the use of the DifferentialDrive class. Runs
 * the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {

  // Create objects and put them in variables
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
  private final XboxController driver_stick = new XboxController(0);
  private final XboxController operator_stick = new XboxController(1);

  // Define variables as a type
  public static Drivetrain m_drivetrain;
  public static Intake m_intake;
  public static Shooter m_shooter;
  

  /** Called once at the beginning of the robot program. */
  public Robot() {

    // Create new objects and put them in the predefined variables
    m_intake = new Intake();
    m_drivetrain = new Drivetrain();
    m_shooter = new Shooter();
    

    // Set default commands to teleop
    m_drivetrain.setDefaultCommand(new frc.robot.Drivetrain.DriveTeleop(driver_stick));
    m_intake.setDefaultCommand(new frc.robot.Intake.IntakeTeleop(m_intake, operator_stick));
    m_shooter.setDefaultCommand(new frc.robot.Shooter.ShooterTeleopRPM(m_shooter, operator_stick));

    // Add the options for auton to select from
    autons autons = new autons();
    m_chooser.setDefaultOption("Shoot High", autons.highShot());
    m_chooser.addOption("Shoot Low", autons.lowShot());
    m_chooser.addOption("Drive Only", autons.driveOnly());
    SmartDashboard.putData("Auto choices", m_chooser);
  }
    
  // Runs at start of auto
  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().schedule(m_chooser.getSelected());
  }

  // Runs at start of teleop
  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  // Loops constantly
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("MatchTime", Timer.getMatchTime());
    CommandScheduler.getInstance().run();
  }
}
