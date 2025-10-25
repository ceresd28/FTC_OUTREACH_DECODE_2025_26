package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import java.util.List;

@Autonomous(name = "Far Zone (No Ramp)")
public class GOOSE_DECODE_AUTON extends LinearOpMode {
    //This was made by Adhithya Yuvaraj XD
    //Ideally it should shoot an artifact, and then move a bit.
    DcMotor motorright, motorleft, frontright, frontleft, backright, backleft, intake1, intake2;

    //servo kick motor
    Servo lowerStop;

    VoltageSensor voltageSensor;

    @Override
    public void runOpMode() throws InterruptedException {

        motorleft = hardwareMap.get(DcMotor.class, "motorleft");
        motorright = hardwareMap.get(DcMotor.class, "motorright");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        intake1 = hardwareMap.get(DcMotor.class, "intake1");
        intake2 = hardwareMap.get(DcMotor.class, "intake2");
        lowerStop = hardwareMap.get(Servo.class, "kickBall");

        //if this doesnt work DIMA DELETE THIS!
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Expansion Hub 2");


        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorright.setDirection(DcMotor.Direction.REVERSE);
        intake1.setDirection(DcMotor.Direction.REVERSE);

        lowerStop.setPosition(0);
        motorleft.setPower(0);
        motorright.setPower(0);
        waitForStart();

        if(opModeIsActive()){
            //set speed of motors
            motorleft.setPower((voltageSensor.getVoltage()/12.5) * .47);  //if voltageSensor doesnt work set to .47
            motorright.setPower((voltageSensor.getVoltage()/12.5) * .47);  //if voltageSensor doesnt work set to .47
            lowerStop.setPosition(0.24);

            //make first artifact shoot.
            sleep(5000);
            intake2.setPower(1);
            motorleft.setPower((voltageSensor.getVoltage()/12.5) * .47);  //if voltageSensor doesnt work set to .47
            motorright.setPower((voltageSensor.getVoltage()/12.5) * .47);  //if voltageSensor doesnt work set to .47

            //turn off and rest for zero position
            sleep(3000);
            intake2.setPower(0);
            sleep(300);
            lowerStop.setPosition(0);

            //turn on and then far artifact should shoot
            sleep(1000);
            intake2.setPower(1);
            intake1.setPower(1);

            //sleep and turn off
            sleep(6000);
            lowerStop.setPosition(0.48);
            intake2.setPower(0);
            intake1.setPower(0);

            //shoot last artifact
            sleep(1000);
            intake2.setPower(1);
            intake1.setPower(1);

            //turn off all motors
            sleep(4000);
            motorleft.setPower(0);
            motorright.setPower(0);
            intake2.setPower(0);
            intake1.setPower(0);

            //move robot
            sleep(10);
            frontleft.setPower(0.5);
            frontright.setPower(0.5);
            backleft.setPower(0.5);
            backright.setPower(0.5);
            intake2.setPower(0);
            intake1.setPower(0);

            //stop robot
            sleep(300);
            frontleft.setPower(0);
            frontright.setPower(0);
            backleft.setPower(0);
            backright.setPower(0);
            intake2.setPower(0);
            intake1.setPower(0);

        }
    }
}
