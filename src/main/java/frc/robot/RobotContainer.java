// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.SimpleAuton;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.igniterobotics.robotbase.preferences.DoublePreference;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private DoublePreference autonInitialTurnAngle = new DoublePreference("Auton Angle One", 90);
  private DoublePreference autonSecondTurnAngle = new DoublePreference("Auton Angle Two", 135);

  private final RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_romiDrivetrain);

  private final XboxController driveController = new XboxController(0);

  private final ArcadeDrive arcadeDriveCommand = new ArcadeDrive(m_romiDrivetrain, () -> driveController.getLeftY(), () -> driveController.getLeftX());

  private final DriveDistance driveDistanceCommand = new DriveDistance(m_romiDrivetrain, Math.PI, 1.0);

  private final SimpleAuton simpleAutonCommand = new SimpleAuton(m_romiDrivetrain, autonInitialTurnAngle, autonSecondTurnAngle);

  private JoystickButton btn_driveA = new JoystickButton(driveController, XboxController.Button.kA.value);

  private JoystickButton btn_autonX = new JoystickButton(driveController, XboxController.Button.kX.value);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    configureButtonBindings();
    setDefaultCommands();
    // do something
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    btn_driveA.whenHeld(driveDistanceCommand);
    btn_autonX.whenPressed(simpleAutonCommand);
  }


  private void setDefaultCommands() {
    m_romiDrivetrain.setDefaultCommand(arcadeDriveCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return simpleAutonCommand;
  }
}
