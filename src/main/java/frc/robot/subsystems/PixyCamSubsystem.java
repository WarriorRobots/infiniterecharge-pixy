/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class PixyCamSubsystem extends SubsystemBase {

  // Creates the Pixy SPI bus in order to read off of
  SPI pixy = new SPI(Port.kOnboardCS0);
  
  public PixyCamSubsystem() {
		pixy.setMSBFirst();
		pixy.setChipSelectActiveLow();
		pixy.setClockRate(1000);
		pixy.setSampleDataOnFalling();
    pixy.setClockActiveLow();
    
  }

  // The sync byte to get the pixy to talk
  byte PIXY_SYNC_BYTE = 0x5a;

  private int getWord() {
		int word = 0x00;
		int ret = -1;
		ByteBuffer writeBuf = ByteBuffer.allocateDirect(2);
		writeBuf.order(ByteOrder.BIG_ENDIAN);
		ByteBuffer readBuf = ByteBuffer.allocateDirect(2);
		readBuf.order(ByteOrder.BIG_ENDIAN);
		String readString = null;
		String writeString = null;

		writeBuf.put(PIXY_SYNC_BYTE);

		// Flip the writeBuf so it's ready to be read.
		writeBuf.flip();

		// Send the sync / data bit / 0 to get the Pixy to return data appropriately.
		ret = pixy.transaction(writeBuf, readBuf, 2);

		// Set the position back to 0 in the buffer so we read it from the beginning next time.
		readBuf.rewind();

		// Store the contents of the buffer in a int that will be returned to the caller.
		word = (int) (readBuf.getShort() & 0xffff);

		// Clear the buffers, not needed, but nice to know they are cleaned out.
		writeBuf.clear();
		readBuf.clear();
		return(word);
  }

  ArrayList<Integer> words;
  int i;
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Stores words from the pixy cam to read off the smart dashboardS
    for(i=0; i<=8; i++);
      words.add(getWord());
    for(i=0; i<=16; i++);
      String I = "" + i;
      SmartDashboard.putNumber(I, words.get(i));
  }
}
/**
 private int getWord() {
		int word = 0x00;
		int ret = -1;
		ByteBuffer writeBuf = ByteBuffer.allocateDirect(2);
		writeBuf.order(ByteOrder.BIG_ENDIAN);
		ByteBuffer readBuf = ByteBuffer.allocateDirect(2);
		readBuf.order(ByteOrder.BIG_ENDIAN);
		String readString = null;
		String writeString = null;

		writeBuf.put(PIXY_SYNC_BYTE);

		// Flip the writeBuf so it's ready to be read.
		writeBuf.flip();

		// Send the sync / data bit / 0 to get the Pixy to return data appropriately.
		ret = pixy.transaction(writeBuf, readBuf, 2);

		// Set the position back to 0 in the buffer so we read it from the beginning next time.
		readBuf.rewind();

		// Store the contents of the buffer in a int that will be returned to the caller.
		word = (int) (readBuf.getShort() & 0xffff);

		// Clear the buffers, not needed, but nice to know they are cleaned out.
		writeBuf.clear();
		readBuf.clear();
		return(word);
	}
 
    */