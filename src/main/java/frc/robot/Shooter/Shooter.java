package frc.robot.Shooter;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    // Create and assign motor controller and encoder objects to variables
    private final SparkMax shooter_front = new SparkMax(10, MotorType.kBrushless);
    private final SparkMax shooter_back = new SparkMax(19, MotorType.kBrushless);
    private final RelativeEncoder frontEncoder = shooter_front.getEncoder();
    private final RelativeEncoder backEncoder = shooter_back.getEncoder();
    private final double kp = 0.001; // Proportional gain for PID control
    private final PIDController frontPID = new PIDController(kp, 0.0, 0.0);
    private final PIDController backPID = new PIDController(kp, 0.0, 0.0);

    // Loop forever
    @Override
    public void periodic() {
        SmartDashboard.putNumber( "kp", kp);
        SmartDashboard.putNumber("RPM Shooter Front", frontEncoder.getVelocity());
        SmartDashboard.putNumber("RPM Shooter Back", backEncoder.getVelocity());
    }

    // Set shooter speed for both motors
    public void setShooterReference(double front_speed, double rear_speed) {
        frontPID.setP(SmartDashboard.getNumber("kp", kp));
        backPID.setP(SmartDashboard.getNumber("kp", kp));
        shooter_front.set(frontPID.calculate(frontEncoder.getVelocity(), front_speed));
        shooter_back.set(backPID.calculate(backEncoder.getVelocity(), rear_speed));
    }
}
