package frc.robot.Intake;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeTeleop extends Command {
    private final Intake m_intake;

    private final XboxController m_stick;

    public IntakeTeleop(Intake intake, XboxController controller) {
        m_intake = intake;
        m_stick = controller;
        addRequirements(m_intake);
        SmartDashboard.putNumber("Intake Speed", 1.0);
    }

    @Override
    public void execute() {
        double intakeSpeed = SmartDashboard.getNumber("Intake Speed", 0.0);
        if (m_stick.getLeftTriggerAxis() > 0.3) {
            m_intake.setIntakeSpeed(intakeSpeed);
        } else if (m_stick.getLeftBumperButton() == true) {
            m_intake.setIntakeSpeed(-intakeSpeed/2);
        } else {
            m_intake.setIntakeSpeed(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.setIntakeSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}