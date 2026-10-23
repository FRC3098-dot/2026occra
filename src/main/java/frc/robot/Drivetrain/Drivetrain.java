package frc.robot.Drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    // Create all of the motor controller objects and assign them to variables
    private final WPI_TalonSRX m_left1Motor = new WPI_TalonSRX(32);
    private final WPI_TalonSRX m_right1Motor = new WPI_TalonSRX(34);
    private final WPI_TalonSRX m_left2Motor = new WPI_TalonSRX(33);
    private final WPI_TalonSRX m_right2Motor = new WPI_TalonSRX(35);
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_left1Motor, m_right1Motor);

    public Drivetrain() {
        // Make the back motors of each side follow the front of the same side
        m_left2Motor.follow(m_left1Motor);
        m_right2Motor.follow(m_right1Motor);
        SendableRegistry.addChild(m_robotDrive, m_left1Motor);
        SendableRegistry.addChild(m_robotDrive, m_right1Motor);

        // Set all motors to Brake mode
        m_left1Motor.setNeutralMode(NeutralMode.Brake);
        m_right1Motor.setNeutralMode(NeutralMode.Brake);
        m_left2Motor.setNeutralMode(NeutralMode.Brake);
        m_right2Motor.setNeutralMode(NeutralMode.Brake);

        // Invert right motors
        m_left1Motor.setInverted(false);
        m_right1Motor.setInverted(true);
        m_left2Motor.setInverted(false);
        m_right2Motor.setInverted(true);
        // We need to invert one side of the drivetrain so that positive voltages
        // result in both sides moving forward. Depending on how your robot's
        // gearbox is constructed, you might have to invert the left side instead.
    }

    public void drive(double drive, double turn) {
        m_robotDrive.arcadeDrive(drive, turn);
    }
}
