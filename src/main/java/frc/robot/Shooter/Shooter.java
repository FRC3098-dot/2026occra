package frc.robot.Shooter;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    // Create and assign motor controller and encoder objects to variables
    private final SparkMax shooter_front = new SparkMax(10, MotorType.kBrushless);
    private final SparkMax shooter_back = new SparkMax(19, MotorType.kBrushless);
    private final RelativeEncoder frontEncoder = shooter_front.getEncoder();
    private final RelativeEncoder backEncoder = shooter_back.getEncoder();

    // Loop forever
    @Override
    public void periodic() {
        SmartDashboard.putNumber("RPM Shooter Front", frontEncoder.getVelocity());
        SmartDashboard.putNumber("RPM Shooter Back", backEncoder.getVelocity());
    }

    // Set shooter speed for both motors
    public void setShooterSpeed(double front_speed, double rear_speed) {
        shooter_front.set(front_speed);
        shooter_back.set(rear_speed);
    }
}
