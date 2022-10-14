// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

public class DriveDistance extends CommandBase {
  RomiDrivetrain drivetrain;
  double m_distanceToDrive;
  double m_speed;
  /** Creates a new DriveDistance. */
  public DriveDistance(RomiDrivetrain m_romiDrivetrain, double distance, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = m_romiDrivetrain;
    m_distanceToDrive = distance;
    m_speed = speed;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.resetEncoders();
    drivetrain.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.arcadeDrive(m_speed, 0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(drivetrain.getAverageDistanceInch()) >= m_distanceToDrive){
      return true;
    }
    return false;
  }
}
