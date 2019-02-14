package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
    //has everything not just drivetrain
    ADXRS450_Gyro mainGyro;
    DifferentialDrive driveTrain;
    VictorSP RBMotor, RFMotor, LBMotor, LFMotor;
    SpeedControllerGroup spdContGL, spdContGR; 
    DoubleSolenoid solenoid;

    private boolean closed = true;
    
    public DriveTrain() {

        
        solenoid = new DoubleSolenoid(0,1);

        mainGyro = new ADXRS450_Gyro();
        RBMotor = new VictorSP(0);
        LBMotor = new VictorSP(1);
        LFMotor = new VictorSP(2);
        RFMotor = new VictorSP(3);

        spdContGL = new SpeedControllerGroup(RFMotor, RBMotor);
        spdContGR = new SpeedControllerGroup(LFMotor, LBMotor);

        driveTrain = new DifferentialDrive(spdContGL, spdContGR);
    }

    public void run(double power,double curve) {
        int Lconst = 0;
        int RConst = 0;
        if(Robot.driver.getY(Hand.kLeft) > 0.1) {
            Lconst = 1;
        } else if(Robot.driver.getY(Hand.kLeft) < -0.1) {
            Lconst = -1;
        }

        if(Robot.driver.getY(Hand.kLeft) > 0.1) {
            RConst = 1;
        } else if (Robot.driver.getY(Hand.kLeft) < -0.1) {
            RConst = 1;
        }

        driveTrain.tankDrive(Lconst * power * Math.pow(Math.abs(Robot.driver.getY(Hand.kLeft)), curve), RConst * power * Math.pow(Math.abs(Robot.driver.getY(Hand.kLeft)),curve));

        

    

        if(Robot.driver.getAButtonPressed()) {
            if(closed == true) {
                solenoid.set(Value.kReverse);
                closed = false;
            } else if (closed == false) {
                solenoid.set(Value.kForward);
                closed = true;
            }
        }

        

        SmartDashboard.putNumber("Gyro", mainGyro.getAngle());

    }
}