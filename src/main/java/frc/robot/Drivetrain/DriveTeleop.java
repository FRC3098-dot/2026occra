package frc.robot.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.XboxController;

public class DriveTeleop extends Command {
    private final XboxController m_stick;
    public DriveTeleop(XboxController stick) {
        m_stick = stick;
        addRequirements(Robot.m_drivetrain);
    }
    @Override
    public void execute() {
        // Drive with arcade drive.
        // That means that the Y axis drives forward
        // and backward, and the X turns left and right.
        Robot.m_drivetrain.drive(-m_stick.getLeftY(), -m_stick.getRightX());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
