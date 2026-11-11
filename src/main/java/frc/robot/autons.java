package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Drivetrain.DriveAuton;
import frc.robot.Drivetrain.Drivetrain;
import frc.robot.Intake.Intake;
import frc.robot.Intake.IntakeAuton;
import frc.robot.Shooter.Shooter;
import frc.robot.Shooter.ShooterAutonRPM;

public class autons {
    Drivetrain m_drivetrain;
    Intake m_intake;
    Shooter m_shooter;
    public autons (Drivetrain drive, Intake intake, Shooter shooter) {
        m_drivetrain = drive;
        m_intake = intake;
        m_shooter = shooter;
    }

    // Drive Only Auto
    Command driveOnly() {
        return new DriveAuton(m_drivetrain, -0.5, -1.5); 
    } 

    // HighShot Auto
    Command highShot() {
        return new SequentialCommandGroup(
            new DriveAuton(m_drivetrain, -0.8, 1.0),
            new ParallelDeadlineGroup(            // Runs the two commands simultaneously
                new WaitCommand(1.5)
                    .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)), // Runs after the WaitCommand
                new ShooterAutonRPM(m_shooter, 2014, 1105)),
            new ParallelDeadlineGroup(            // Runs the two commands simultaneously
                new WaitCommand(1.5)
                    .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)), // Runs after the WaitCommand
                new ShooterAutonRPM(m_shooter, 2014, 1105)),
            new ParallelDeadlineGroup(
                new DriveAuton(m_drivetrain, 0.80, 1.5),
                new ShooterAutonRPM(m_shooter, -500, -500),
                new IntakeAuton(m_intake, 1.0)),
            new DriveAuton(m_drivetrain, -0.80, 1.5),
            new ParallelDeadlineGroup(
                new WaitCommand(1.5)
                    .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)),
                new ShooterAutonRPM(m_shooter, 2014, 1105)));

    }
    // LowShot Auto
    Command lowShot() {
      return new SequentialCommandGroup(
        new DriveAuton(m_drivetrain, -0.8, 1.0),
        new ParallelDeadlineGroup(            // Runs the two commands simultaneously
            new WaitCommand(1.5)
                .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)), // Runs after the WaitCommand
            new ShooterAutonRPM(m_shooter, 2014, 1067)),
        new ParallelDeadlineGroup(            // Runs the two commands simultaneously
            new WaitCommand(1.5)
                .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)), // Runs after the WaitCommand
            new ShooterAutonRPM(m_shooter, 2014, 1067)),
        new ParallelDeadlineGroup(
            new DriveAuton(m_drivetrain, 0.5, 3.5),
            new ShooterAutonRPM(m_shooter, -500, -500),
            new IntakeAuton(m_intake, 1.0)),
        new DriveAuton(m_drivetrain, -0.5, 3.5),
        new ParallelDeadlineGroup(
            new WaitCommand(1.5)
                .andThen(new IntakeAuton(m_intake, 1.0).withTimeout(1.5)),
            new ShooterAutonRPM(m_shooter, 2014, 1067)));
    }
}
