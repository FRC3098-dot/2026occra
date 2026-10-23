package frc.robot.Intake;

import edu.wpi.first.wpilibj2.command.Command;

public class IntakeAuton extends Command {
    // Declare variables
    private final Intake m_intake;
    private final double m_speed;

    public IntakeAuton(Intake intake, double speed) {
        m_intake = intake;
        m_speed = speed;
        addRequirements(m_intake);
    }

    // Loops until command finished
    @Override
    public void execute() {
        m_intake.setIntakeSpeed(m_speed);
    }

    // Run at command finished
    @Override
    public void end(boolean interrupted) {
        m_intake.setIntakeSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}