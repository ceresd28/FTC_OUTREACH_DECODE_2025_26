package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Far Zone V1.0")
public class AutoDecode extends LinearOpMode {
    //This was made by Adhithya Yuvaraj XD
    //Ideally it should shoot an artifact, and then move a bit.
    DcMotor motorright, motorleft, frontright, frontleft, backright, backleft, intake1, intake2;

    //servo kick motor
    CRServo kickBall;

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

        kickBall = hardwareMap.get(CRServo.class, "kickBall");

        //EX

        //UNO REVERSE THOSE MOTORS
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorright.setDirection(DcMotor.Direction.REVERSE);
        intake1.setDirection(DcMotor.Direction.REVERSE);

        //INIT PART DONE!
        waitForStart();

        motorleft.setPower(0);
        motorright.setPower(0);

        waitForStart();

        if(opModeIsActive()){
            /*
            //accelerate motors to shoot
            intake2.setPosition(0.5);
            motorleft.setPower(0.47);
            motorright.setPower(0.47);

            if(time.seconds() >= 10 && time.seconds() <= 10.2){
                kickBall.setPosition(0);
            }

            if (time.seconds() > 13 && time.seconds() < 13.2){
                motorleft.setPower(0);
                motorright.setPower(0);
                frontleft.setTargetPosition(300);
                frontright.setTargetPosition(300);
                backleft.setTargetPosition(300);
                backright.setTargetPosition(300);
            }

            if(time.seconds() > 13.5){
                frontleft.setPower(0);
                frontright.setPower(0);
                backleft.setPower(0);
                backright.setPower(0);
            }

            */

            motorleft.setPower(0.47);
            motorright.setPower(0.47);
            sleep(1000);
            intake2.setPower(0.5);
            sleep(5000);
            //stop wasting battery
            motorleft.setPower(0);
            motorright.setPower(0);
            sleep(3000);

            //MOVE AWAY FROM THE START
            frontleft.setPower(0.5);
            frontright.setPower(0.5);
            backleft.setPower(0.5);
            backright.setPower(0.5);
            sleep(200);

            //should've moved halfway from the field lol
            frontleft.setPower(0);
            frontright.setPower(0);
            backleft.setPower(0);
            backright.setPower(0);



        }
    }
}
