// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Drivetrain.DriveAuton;
import frc.robot.Drivetrain.Drivetrain;
import frc.robot.Intake.Intake;
import frc.robot.Intake.IntakeAuton;
import frc.robot.Shooter.Shooter;
import frc.robot.Shooter.ShooterAuton;

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
    m_chooser.setDefaultOption("Shoot High", highShot());
    m_chooser.addOption("Shoot Low", lowShot());
    m_chooser.addOption("Drive Only", new DriveAuton(m_drivetrain, -0.3, 1.0));
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  // HighShot function
  private Command highShot() {
    return new SequentialCommandGroup(
        new DriveAuton(m_drivetrain, -0.5, 1.0),
        new ParallelDeadlineGroup(            // Runs the two commands simultaneously
            new WaitCommand(1.5)
                .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)), // Runs after the WaitCommand
            new ShooterAuton(m_shooter, 0.45, -0.3)),
        new ParallelDeadlineGroup(            // Runs the two commands simultaneously
            new WaitCommand(1.5)
                .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)), // Runs after the WaitCommand
            new ShooterAuton(m_shooter, 0.45, -0.3)),
        new ParallelDeadlineGroup(
            new DriveAuton(m_drivetrain, 0.5, 1.0 * 2),
            new IntakeAuton(m_intake, 1.0)),
        new DriveAuton(m_drivetrain, -0.5, 1.0 * 2),
        new ParallelDeadlineGroup(
            new WaitCommand(1.5)
                .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)),
            new ShooterAuton(m_shooter, 0.45, -0.3)));

  }

    // LowShot function
    private Command lowShot() {
      return new SequentialCommandGroup(
        new DriveAuton(m_drivetrain, -0.5, 1.0),
        new ParallelDeadlineGroup(            // Runs the two commands simultaneously
            new WaitCommand(1.5)
                .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)), // Runs after the WaitCommand
            new ShooterAuton(m_shooter, 0.4, -03)),
        new ParallelDeadlineGroup(            // Runs the two commands simultaneously
            new WaitCommand(1.5)
                .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)), // Runs after the WaitCommand
            new ShooterAuton(m_shooter, 0.4, -0.3)),
        new ParallelDeadlineGroup(
            new DriveAuton(m_drivetrain, 0.5, 1.0 * 2),
            new IntakeAuton(m_intake, 1.0)),
        new DriveAuton(m_drivetrain, -0.5, 1.0 * 2.5),
        new ParallelDeadlineGroup(
            new WaitCommand(1.5)
                .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)),
            new ShooterAuton(m_shooter, 0.4, -0.3)));
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
    CommandScheduler.getInstance().run();
  }
}
