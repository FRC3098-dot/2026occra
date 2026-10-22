package frc.robot.Shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class ShooterTeleop extends Command {
    private final Shooter m_shooter;

    private final XboxController m_stick;

    public ShooterTeleop(Shooter shooter, XboxController controller) {
        m_shooter = shooter;
        m_stick = controller;
        addRequirements(m_shooter);
        SmartDashboard.putNumber("Shooter Front Speed High", 0.7);
        SmartDashboard.putNumber("Shooter Back Speed High", -0.5);
        SmartDashboard.putNumber("Shooter Front Speed Low", 0.35);
        SmartDashboard.putNumber("Shooter Back Speed Low", -0.25);
    }

    @Override
    public void execute() {
        
 
        if (m_stick.getRightTriggerAxis() > 0.3) {
            double HighfrontSpeed = SmartDashboard.getNumber("Shooter Front Speed High", 0.0);
            double HighbackSpeed = SmartDashboard.getNumber("Shooter Back Speed High", 0.0);
            m_shooter.setShooterSpeed(HighfrontSpeed, HighbackSpeed);
        } else if (m_stick.getRightBumperButton() == true) {
            double LowfrontSpeed = SmartDashboard.getNumber("Shooter Front Speed Low", 0.0);
            double LowbackSpeed = SmartDashboard.getNumber("Shooter Back Speed Low", 0.0);
            m_shooter.setShooterSpeed(LowfrontSpeed, LowbackSpeed);
        } else {
            m_shooter.setShooterSpeed(0, 0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setShooterSpeed(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}