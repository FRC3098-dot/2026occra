package frc.robot.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

public class ShooterAuton extends Command {

    // Declare variables
    private final Shooter m_shooter;
    private final double m_frontSpeed;
    private final double m_backSpeed;

    public ShooterAuton(Shooter Shooter, double frontSpeed, double backSpeed) {
        addRequirements(Shooter);
        m_shooter = Shooter;
        m_frontSpeed = frontSpeed;
        m_backSpeed = backSpeed;
    }

    // Loop until command finished
    @Override
    public void execute() {
        m_shooter.setShooterSpeed(m_frontSpeed, m_backSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }



}
