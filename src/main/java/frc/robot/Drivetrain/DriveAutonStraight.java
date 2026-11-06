package frc.robot.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class DriveAutonStraight extends Command {

    // Define variables
    private final Drivetrain m_drivetrain;
    private final double m_speed;
    private final double m_distance;
    private double startDistance;
    private double startAngle;


    public DriveAutonStraight(Drivetrain drivetrain, double speed, double distance) {
        addRequirements(drivetrain);
        m_drivetrain = drivetrain;
        m_speed = speed;
        m_distance = distance;
    }

    // Runs at command start
    @Override
    public void initialize() {
        startDistance = m_drivetrain.getEncoderDistance();
        startAngle = m_drivetrain.getGryoYaw(); 
    }

    // Loops until Finished
    @Override
    public void execute() {
        double angleError = m_drivetrain.getGryoYaw() - startAngle;
        //double angleError = 0;
        m_drivetrain.drive(m_speed, angleError * -0.0); // Adjust the turn value based on the angle error
    }

    @Override
    public boolean isFinished() {
        m_drivetrain.getEncoderDistance();
        if (m_distance > 0) {
            return Math.abs(m_drivetrain.getEncoderDistance() - startDistance) >= m_distance;
        } else {
            return Math.abs(m_drivetrain.getEncoderDistance() - startDistance) <= m_distance;
        }
    }

    // Runs at command finished
    @Override
    public void end(boolean interrupted) {
        m_drivetrain.drive(0, 0);
    }
}
