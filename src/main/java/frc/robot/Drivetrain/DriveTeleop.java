package frc.robot.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTeleop extends Command {
    // Set up the Controller and Dahsboard
    private final XboxController m_stick;
    public DriveTeleop(XboxController stick) {
        m_stick = stick;
        addRequirements(Robot.m_drivetrain);
        SmartDashboard.putNumber("Drive Speed", 1.0);
        SmartDashboard.putNumber("Turn Speed", 0.75);
    }
    @Override
    public void execute() {
        // Drive with arcade drive.
        // That means that the Left joystick Y axis drives forward
        // and backward, and the Right joystick X turns left and right.
        double driveSpeed = SmartDashboard.getNumber("Drive Speed", 1.0);
        double turnSpeed = SmartDashboard.getNumber("Turn Speed", 0.75);
        Robot.m_drivetrain.drive(-m_stick.getLeftY() * driveSpeed, -m_stick.getRightX() * turnSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
