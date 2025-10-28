package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
@Autonomous(name = "Far Zone WITH RAMP")
public class GOOSE_DECODE_AUTON_2 extends LinearOpMode {
    //This was made by Adhithya Yuvaraj XD
    //Ideally it should shoot an artifact, and then move a bit.
    DcMotor motorright, motorleft, frontright, frontleft, backright, backleft, intake1, intake2;
    DcMotor[] wheels;
    //servo kick motor
    Servo lowerStop, upperStop;
    double thruRampTime, thruShooterTime;

    VoltageSensor voltageSensor;

    @Override
    public void runOpMode() throws InterruptedException {

        motorleft = hardwareMap.get(DcMotor.class, "motorleft");
        motorright = hardwareMap.get(DcMotor.class, "motorright");

        frontright = hardwareMap.get(DcMotor.class, "frontright");
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");

        wheels[0] = frontright;
        wheels[1] = frontleft;
        wheels[2] = backright;
        wheels[3] = backleft;

        intake1 = hardwareMap.get(DcMotor.class, "intake1");
        intake2 = hardwareMap.get(DcMotor.class, "intake2");

        lowerStop = hardwareMap.get(Servo.class, "kickBall");
        upperStop = hardwareMap.get(Servo.class, "upperStop");

        upperStop.scaleRange(0,1); // 0=open, 1=close ////////////////
        thruRampTime = 1000;
        thruShooterTime = 3000;

        //if this doesnt work DIMA DELETE THIS!
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Expansion Hub 2");

        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorright.setDirection(DcMotorSimple.Direction.REVERSE);
        intake1.setDirection(DcMotorSimple.Direction.REVERSE);

        lowerStop.setPosition(0);
        motorleft.setPower(0);
        motorright.setPower(0);
        waitForStart();

        if(opModeIsActive()){
            //set motor speed
            double shooterPwr = (voltageSensor.getVoltage()/12.5) * .47;
            //if voltageSensor doesnt work set to .47
            motorleft.setPower(shooterPwr);
            motorright.setPower(shooterPwr);
            lowerStop.setPosition(0.24);
            sleep(5000);

            //shoot first artifact
            intake2.setPower(1);
            sleep(5000);

            intake2.setPower(0);
            sleep(500);

            // shoot 2nd and 3rd artifacts
            for (int i = 0; i < 2; i++) {
                upperStop.setPosition(0); //OPEN POS
                sleep((long) thruRampTime);
                upperStop.setPosition(1); //CLOSE POS
                sleep((long) thruShooterTime);
            }

            //all done shooting
            motorleft.setPower(0);
            motorright.setPower(0);
            intake2.setPower(0);
            intake1.setPower(0);

            //start moving
            sleep(10);
            for (DcMotor wheel : wheels) { wheel.setPower(0.5); }
            intake2.setPower(0);
            intake1.setPower(0);

            //done moving
            sleep(300);
            for (DcMotor wheel : wheels) { wheel.setPower(0); }
            intake2.setPower(0);
            intake1.setPower(0);

        }
    }
}
