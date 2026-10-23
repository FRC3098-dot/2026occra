package frc.robot.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class DriveAuton extends Command {

    // Define variables
    private final Drivetrain m_drivetrain;
    private final double m_speed;
    private final double m_duration;
    private double m_startTime;

    public DriveAuton(Drivetrain drivetrain, double speed, double duration) {
        addRequirements(drivetrain);
        m_drivetrain = drivetrain;
        m_speed = speed;
        m_duration = duration;
    }

    // Runs at command start
    @Override
    public void initialize() {
        m_startTime = Timer.getFPGATimestamp();
    }

    // Loops until Finished
    @Override
    public void execute() {
        m_drivetrain.drive(m_speed, 0);
    }

    @Override
    public boolean isFinished() {
        // If current time - start time which is the time running >= duration then return true
        return Timer.getFPGATimestamp() - m_startTime >= m_duration;
    }

    // Runs at command finished
    @Override
    public void end(boolean interrupted) {
        m_drivetrain.drive(0, 0);
    }
}
