package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Far Zone V1.0")
public class AutoDecode extends LinearOpMode {
    //This was made by Adhithya Yuvaraj XD
    //Ideally it should shoot an artifact, and then move a bit.
    DcMotor motorleft, motorright, frontright, frontleft, backright, backleft, intake;


    //servo kick motor
    Servo kickBall;

    @Override
    public void runOpMode() throws InterruptedException {

        //flywheels
        motorleft = hardwareMap.get(DcMotor.class, "motorleft");
        motorright = hardwareMap.get(DcMotor.class, "motorright");

        //wheels
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");

        //intake
        intake = hardwareMap.get(DcMotor.class, "intake");

        //the hold and kicker
        kickBall = hardwareMap.get(Servo.class, "kickBall");

        //UNO REVERSE THOSE MOTORS
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorright.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        if(opModeIsActive()){
            //accelerate motors to shoot
            kickBall.setPosition(0.5);
            motorleft.setPower(0.47);
            motorright.setPower(0.47);
            sleep(10000);

            //shoot!
            kickBall.setPosition(0);
            sleep(3000);

            //stop wasting battery
            motorleft.setPower(0);
            motorright.setPower(0);
            sleep(3000);

            //MOVE AWAY FROM THE START
            frontleft.setPower(0.5);
            frontright.setPower(0.5);
            backleft.setPower(0.5);
            backright.setPower(0.5);
            sleep(500);

            //should've moved halfway from the field lol
            frontleft.setPower(0);
            frontright.setPower(0);
            backleft.setPower(0);
            backright.setPower(0);
        }
    }
}
