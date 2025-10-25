package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "GOOSE DECODE TELEOP")
public class GOOSE_DECODE_TELEOP extends LinearOpMode {

    //This was made by Adhithya Yuvaraj XD

    //lets instanitate the 7 motors!
    DcMotor motorright, motorleft, frontright, frontleft, backright, backleft, intake1, intake2;

    //servo kick motor
    Servo lowerStop, upperStop;

    double speed = 1; //0.3 is baby mode

    @Override
    public void runOpMode() throws InterruptedException {

        //set the motors to the postions on CHUB + UHUB
        motorleft = hardwareMap.get(DcMotor.class, "motorleft");
        motorright = hardwareMap.get(DcMotor.class, "motorright");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        intake1 = hardwareMap.get(DcMotor.class, "intake1");
        intake2 = hardwareMap.get(DcMotor.class, "intake2");
        lowerStop = hardwareMap.get(Servo.class, "kickBall");

        //DIMA UNCOMMENT THIS WHEN FINISHED SET UP AND CONFIG!
        //upperStop = hardwareMap.get(Servo.class, "upperStop");

        //UNO REVERSE THOSE MOTORS
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorright.setDirection(DcMotor.Direction.REVERSE);
        intake1.setDirection(DcMotor.Direction.REVERSE);

        //INIT PART DONE!
        waitForStart();

        motorleft.setPower(0);
        motorright.setPower(0);


        while(opModeIsActive()){

            if(gamepad1.circleWasPressed()) {
                motorleft.setPower(0.3);
                motorright.setPower(0.3);
                telemetry.addData("Motor Speed Left",motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());
            }else if (gamepad1.triangleWasPressed()){ //decrease speed
                motorleft.setPower(0.45);
                motorright.setPower(0.45);
                telemetry.addData("Motor Speed Left",motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());
            }else if (gamepad1.crossWasPressed()){ //increase speed
                motorleft.setPower(0);
                motorright.setPower(0);
                telemetry.addData("Motor Speed Left", motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());
            }else if(gamepad1.squareWasPressed()){ //Zero Speed
                motorleft.setPower(0.6);
                motorright.setPower(0.6);
                telemetry.addData("Motor Speed Left",motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());

            }

            //decreasing and increasing
            if(gamepad1.dpadDownWasPressed()){
                if(motorleft.getPower() != 0) {
                    motorleft.setPower(motorleft.getPower() - 0.05);
                    motorright.setPower(motorright.getPower() - 0.05);
                }
            }
            if(gamepad1.dpadUpWasPressed()){
                motorleft.setPower(motorleft.getPower() + 0.05);
                motorright.setPower(motorright.getPower() + 0.05);
            }


            //INTAKE BUTTONS
            if(gamepad2.circleWasPressed()){
                if(intake1.getPower() == 0){
                    intake1.setPower(1);
                }else{
                    intake1.setPower(0);
                }
            } else if(gamepad2.triangleWasPressed()){
                if(intake2.getPower() == 0){
                    intake2.setPower(1);
                }else{
                    intake2.setPower(0);
                }
            }

            //UPPER STOP BUTTONS DIMA CODE THISSSSS!!!!!!
            if(gamepad2.crossWasPressed()){
                //DIMA THIS IS THE PART YOU NEED TO CODE!
                upperStop.setPosition(0); //SET TO OPEN POSITION
                sleep(1000); //EDIT THIS IF WE NEED MORE/LESS TIME!
                upperStop.setPosition(1); //SET TO CLOSE POSITION
            }if(gamepad2.squareWasPressed()){
                upperStop.setPosition(1); //CLOSE UPPER STOPPER
            }

            //LOWER STOP BUTTONS
            if(gamepad2.dpadLeftWasPressed()){
                lowerStop.setPosition(0.48);
            }else if(gamepad2.dpadRightWasPressed()){
                lowerStop.setPosition(0);
            }else if(gamepad2.dpadUpWasPressed()) {
                lowerStop.setPosition(0.24);
            }else if(gamepad2.dpadDownWasPressed()){
                lowerStop.setPosition(.8);
            }

            //WHEELS
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;
            //NO RUNNING WITH ENCODERS!!!!
            frontleft.setPower((y+x+r)*speed);
            frontright.setPower((y-x-r)*speed);
            backleft.setPower((y-x+r)*speed);
            backright.setPower((y+x-r)*speed);
            telemetry.addData("Motor Speed",frontleft.getPower());
            telemetry.addData("Motor Speed: ",frontright.getPower());

            //updating stuff
            telemetry.addData("Motor Speed Left",motorleft.getPower());
            telemetry.addData("Motor Speed Right: ",motorright.getPower());
            telemetry.update();
        }
    }
}
