package frc.robot.Intake;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    // Define intake motor
    private final SparkMax m_intakeMotor = new SparkMax(22, MotorType.kBrushless);

    // Set the speed for intake
    public void setIntakeSpeed(double speed) {
        m_intakeMotor.set(speed);
    }
}
