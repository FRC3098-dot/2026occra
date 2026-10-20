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
        SmartDashboard.putNumber("Shooter Front Speed", 0.2);
        SmartDashboard.putNumber("Shooter Back Speed", -0.15);
    }

    @Override
    public void execute() {
        double frontSpeed = SmartDashboard.getNumber("Shooter Front Speed", 0.0);
        double backSpeed = SmartDashboard.getNumber("Shooter Back Speed", 0.0);
        m_shooter.setShooterSpeed(frontSpeed, backSpeed);
        if (m_stick.getRightTriggerAxis() > 0.3) {
            m_shooter.setShooterSpeed(frontSpeed, backSpeed);
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