/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;

public class PixyCamSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  public PixyCamSubsystem() {
	
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
/**
  public PixySPI(SPI argPixy, HashMap<Integer, ArrayList<PixyPacket>> argPixyPacket, PixyException argPixyException){
		SPI pixy = argPixy;
		packets = argPixyPacket;
		pExc = argPixyException;

		// Set some SPI parameters.
		pixy.setMSBFirst();
		pixy.setChipSelectActiveLow();
		pixy.setClockRate(1000);
		pixy.setSampleDataOnFalling();
		pixy.setClockActiveLow();
 */