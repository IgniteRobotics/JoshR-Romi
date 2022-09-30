// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

public class ArcadeDrive extends CommandBase {
  RomiDrivetrain m_driveTrain;
  Supplier<Double> m_xAxisSpeedSupplier;
  Supplier<Double> m_zAxisRotateSupplier;

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(RomiDrivetrain drivetrain, Supplier<Double> xAxisValue, Supplier<Double> zAxisRotate) {
    m_xAxisSpeedSupplier = xAxisValue;
    m_zAxisRotateSupplier = zAxisRotate;
    m_driveTrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.arcadeDrive(m_xAxisSpeedSupplier.get(), m_zAxisRotateSupplier.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
