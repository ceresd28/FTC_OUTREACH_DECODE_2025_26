package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Basic auto")
public class AutoDecode extends LinearOpMode {
    //This was made by Adhithya Yuvaraj XD
    //Ideally it should shoot an artifact, and then move a bit.
    DcMotor motorleft, motorright, frontright, frontleft, backright, backleft, intake;


    //servo kick motor
    Servo kickBall;

    @Override
    public void runOpMode() throws InterruptedException {

        motorleft = hardwareMap.get(DcMotor.class, "motorleft");
        motorright = hardwareMap.get(DcMotor.class, "motorright");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        intake = hardwareMap.get(DcMotor.class, "intake");

        kickBall = hardwareMap.get(Servo.class, "kickBall");

        //UNO REVERSE THOSE MOTORS
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorright.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        if(opModeIsActive()){
            kickBall.setPosition(0.5);
            motorleft.setPower(0.47);
            motorright.setPower(0.47);
            sleep(10000);
            kickBall.setPosition(0);
            sleep(3000);
            motorleft.setPower(0);
            motorright.setPower(0);
            sleep(3000);
            frontleft.setPower(0.5);
            frontright.setPower(0.5);
            backleft.setPower(0.5);
            backright.setPower(0.5);
            sleep(500);
            frontleft.setPower(0);
            frontright.setPower(0);
            backleft.setPower(0);
            backright.setPower(0);
        }
    }
}
