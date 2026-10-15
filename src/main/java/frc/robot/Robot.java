// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonSRX m_left1Motor = new WPI_TalonSRX(32);
  private final WPI_TalonSRX m_right1Motor = new WPI_TalonSRX(34);
  private final WPI_TalonSRX m_left2Motor = new WPI_TalonSRX(33);
  private final WPI_TalonSRX m_right2Motor = new WPI_TalonSRX(35);
  private final DifferentialDrive m_robotDrive =
      new DifferentialDrive(m_left1Motor, m_right1Motor);
  private final XboxController m_stick = new XboxController(0);

  /** Called once at the beginning of the robot program. */
  public Robot() {
    m_left2Motor.follow(m_left1Motor);
    m_right2Motor.follow(m_right1Motor);
    SendableRegistry.addChild(m_robotDrive, m_left1Motor);
    SendableRegistry.addChild(m_robotDrive, m_right1Motor);

    m_left1Motor.setNeutralMode(NeutralMode.Brake);
    m_right1Motor.setNeutralMode(NeutralMode.Brake);
    m_left2Motor.setNeutralMode(NeutralMode.Brake);
    m_right2Motor.setNeutralMode(NeutralMode.Brake);

    m_left1Motor.setInverted(false);
    m_right1Motor.setInverted(true);
    m_left2Motor.setInverted(false);
    m_right2Motor.setInverted(true);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_right1Motor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(-m_stick.getLeftY(), -m_stick.getRightX());
  }
}
