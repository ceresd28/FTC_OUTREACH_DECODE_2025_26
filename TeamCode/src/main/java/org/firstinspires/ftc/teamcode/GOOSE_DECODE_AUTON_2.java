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

    //servo kick motor
    Servo lowerStop, upperStop;

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
        upperStop = hardwareMap.get(Servo.class, "upperStop");


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
            //set motor speed
            motorleft.setPower((voltageSensor.getVoltage()/12.5) * .47);
            motorright.setPower((voltageSensor.getVoltage()/12.5) * .47);
            lowerStop.setPosition(0.24);
            sleep(5000);

            //shoot first artifact
            intake2.setPower(1);
            sleep(5000);

            //DIMA UNCOMMENT THE FOLLOWING IF NECESSARY
            /*intake2.setPower(0)
            sleep(500);
             */

            //shoot artifact 2
            upperStop.setPosition(0); //OPEN POS DIMA EDIT
            sleep(1000); //time it takes to fully OPEN and one to leave DIMA EDIT
            upperStop.setPosition(1); //CLOSE POS DIMA EDIT
            sleep(3000); //time it takes to fully leave robot! DIMA EDIT

            //shoot artifact 3
            upperStop.setPosition(0); //OPEN POS DIMA EDIT
            sleep(1000); //time it takes to fully OPEN and one to leave DIMA EDIT
            upperStop.setPosition(1); //CLOSE POS DIMA EDIT
            sleep(3000); //time it takes to fully leave robot! DIMA EDIT

            //all done shooting
            motorleft.setPower(0);
            motorright.setPower(0);
            intake2.setPower(0);
            intake1.setPower(0);

            //start moving
            sleep(10);
            frontleft.setPower(0.5);
            frontright.setPower(0.5);
            backleft.setPower(0.5);
            backright.setPower(0.5);
            intake2.setPower(0);
            intake1.setPower(0);

            //done moving
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
