package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain {
    DifferentialDrive driveTrain;
    ADXRS450_Gyro mainGyro;
    public Drivetrain(){
        mainGyro= new ADXRS450_Gyro();
    }
    public void run(){
        
        if(Robot.operatorController.getAButtonPressed()==true){
            System.out.println(mainGyro.getAngle());
            mainGryo.setAngle(20);        
        }
        if(Robot.operatorController.getBButtonPressed()==true){
            System.out.println("going forward");
            mainGyro.set(1);
        }
    }
        
    
}