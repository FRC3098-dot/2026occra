package frc.robot.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

public class ShooterAutonRPM extends Command {

    // Declare variables
    private final Shooter m_shooter;
    private final double m_frontRPM;
    private final double m_backRPM;

    public ShooterAutonRPM(Shooter Shooter, double frontRPM, double backRPM) {
        addRequirements(Shooter);
        m_shooter = Shooter;
        m_frontRPM = frontRPM;
        m_backRPM = backRPM;
    }

    // Loop until command finished
    @Override
    public void execute() {
        m_shooter.setSetPoint(m_frontRPM, m_backRPM);
    }

    @Override
    public boolean isFinished() {
        return false;
    }



}
