package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp(name = "GOOSE DECODE TELEOP")
public class GooseGOTHAM extends LinearOpMode {

    //This was made by Adhithya Yuvaraj XD

    //lets instanitate the 7 motors!
    DcMotor motorright, motorleft, frontright, frontleft, backright, backleft, intake;

    //servo kick motor
    CRServo kickBall;

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
        intake = hardwareMap.get(DcMotor.class, "intake");

        kickBall = hardwareMap.get(CRServo.class, "kickBall");

        //EX

        //UNO REVERSE THOSE MOTORS
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorright.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);

        //INIT PART DONE!
        waitForStart();

        motorleft.setPower(0);
        motorright.setPower(0);


        //THIS RUNS ON START!
        while(opModeIsActive()){
            //Circle, Cross, Triangle, Square Stuff
            if(gamepad2.circleWasPressed()) { //MAX SPEED DONT CLICK HIGH SPEED GO BRRRRRR
                motorleft.setPower(0.3);
                motorright.setPower(0.3);
                telemetry.addData("Motor Speed Left",motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());
            }else if (gamepad2.triangleWasPressed()){ //decrease speed
                motorleft.setPower(0.45);
                motorright.setPower(0.45);
                telemetry.addData("Motor Speed Left",motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());
            }else if (gamepad2.crossWasPressed()){ //increase speed
                motorleft.setPower(0);
                motorright.setPower(0);
                telemetry.addData("Motor Speed Left", motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());
            }else if(gamepad2.squareWasPressed()){ //Zero Speed
                motorleft.setPower(0.6);
                motorright.setPower(0.6);
                telemetry.addData("Motor Speed Left",motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());

            }

            //decreasing and increasing
            if(gamepad2.leftBumperWasPressed()){
                motorleft.setPower(motorleft.getPower() - 0.05);
                motorright.setPower(motorright.getPower() - 0.05);
            }
            if(gamepad2.rightBumperWasPressed()){
                motorleft.setPower(motorleft.getPower() + 0.05);
                motorright.setPower(motorright.getPower() + 0.05);
            }

            //SHOOT!


            //intake dpad
            if(gamepad2.dpad_up){
                intake.setPower(1);
            }else if(gamepad2.dpad_down){
                intake.setPower(0);
            }else if(gamepad2.dpad_right){
                kickBall.setPower(0.5);
            }else if(!gamepad2.dpad_right){
                kickBall.setPower(0);
            }else if(gamepad2.dpad_left){
                kickBall.setPower(-0.5);
            }

            //WHEEEEEEEELLLSSSSSSS GO WEEEEEEE
            //i dont get this stuff :(
            //dima did math in robotics rip
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;

//            frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            frontleft.setPower((y+x+r)*speed);
            frontright.setPower((y-x-r)*speed);
            backleft.setPower((y-x+r)*speed);
            backright.setPower((y+x-r)*speed);

            telemetry.addData("Motor Speed",frontleft.getPower());
            telemetry.addData("Motor Speed: ",frontright.getPower());


            telemetry.addData("Motor Speed Left",motorleft.getPower());
            telemetry.addData("Motor Speed Right: ",motorright.getPower());
            //update update update
            telemetry.update();
        }
    }
}
