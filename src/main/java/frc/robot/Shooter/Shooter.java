package frc.robot.Shooter;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    // Create and assign motor controller and encoder objects to variables
    private final SparkMax shooter_front = new SparkMax(10, MotorType.kBrushless);
    private final SparkMax shooter_back = new SparkMax(19, MotorType.kBrushless);
    private final RelativeEncoder frontEncoder = shooter_front.getEncoder();
    private final RelativeEncoder backEncoder = shooter_back.getEncoder();
    private final SparkClosedLoopController frontController = shooter_front.getClosedLoopController();
    private final SparkClosedLoopController backController = shooter_back.getClosedLoopController();

    public Shooter() {
        SparkMaxConfig config = new SparkMaxConfig();
        //Front Wheel Configuration
        // Set PID gains
        config.closedLoop
            .p(0.00025)
            .i(0.0000025)
            .d(0)
            .iZone(200)
            .outputRange(-1, 1);
        // Set PID gains
        config.closedLoop.feedForward
            .kS(0)
            .kV(0.002)
            .kA(0)
            .kG(0) // kG is a linear gravity feedforward, for an elevator
            .kCos(0) // kCos is a cosine gravity feedforward, for an arm
            .kCosRatio(0); // kCosRatio relates the encoder position to absolute position
        shooter_front.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

        // Rear wheel configuration
        config.closedLoop
            .p(0.00005)
            .i(0.0000005)
            .d(0)
            .iZone(200)
            .outputRange(-1, 1);
        // Set PID gains
        config.closedLoop.feedForward
            .kS(0)
            .kV(0.002)
            .kA(0)
            .kG(0) // kG is a linear gravity feedforward, for an elevator
            .kCos(0) // kCos is a cosine gravity feedforward, for an arm
            .kCosRatio(0); // kCosRatio relates the encoder position to absolute position
        config.inverted(true);
        shooter_back.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    }

    // Loop forever
    @Override
    public void periodic() {
        SmartDashboard.putNumber("RPM Shooter Front", frontEncoder.getVelocity());
        SmartDashboard.putNumber("RPM Shooter Back", backEncoder.getVelocity());
    }

    // Set shooter speed for both motors
    public void setSetPoint(double front_speed, double rear_speed) {
        frontController.setSetpoint(front_speed, ControlType.kVelocity);
        backController.setSetpoint(rear_speed, ControlType.kVelocity);
    }
    public void setShooterPower(double front_power, double rear_power) {
        shooter_front.set(front_power);
        shooter_back.set(rear_power);
    }
}
