// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.igniterobotics.robotbase.preferences.DoublePreference;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.RomiDrivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SimpleAuton extends SequentialCommandGroup {
  /** Creates a new SimpleAuton. */
  public SimpleAuton(RomiDrivetrain drivetrain, DoublePreference initialAngle, DoublePreference secondAngle) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new TurnDegrees(drivetrain, initialAngle, -0.75),
      new DriveDistance(drivetrain, 12, 0.8),
      new TurnDegrees(drivetrain, secondAngle, 0.75),
      new DriveDistance(drivetrain, 22, .75)
    );
  }
}
