package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Goose Decode v1.0")
public class GooseGOTHAM extends LinearOpMode {

    //This was made by Adhithya Yuvaraj XD

    //lets instanitate the 7 motors!
    DcMotor motorleft, motorright, frontright, frontleft, backright, backleft, intake;


    //servo kick motor
    Servo kickBall;

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

        kickBall = hardwareMap.get(Servo.class, "kickBall");



        //UNO REVERSE THOSE MOTORS
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorright.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);

        //INIT PART DONE!
        waitForStart();


        //THIS RUNS ON START!
        while(opModeIsActive()){

            //Circle, Cross, Triangle, Square Stuff
            if(gamepad1.circleWasPressed()) { //MAX SPEED DONT CLICK HIGH SPEED GO BRRRRRR
                motorleft.setPower(1);
                motorright.setPower(1);
                telemetry.addData("Motor Speed Left: ",motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());
            }else if (gamepad1.crossWasPressed()){ //decrease speed
                motorleft.setPower(motorleft.getPower()-0.05);
                motorright.setPower(motorright.getPower()-0.05);
                telemetry.addData("Motor Speed Left",motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());
            }else if (gamepad1.triangleWasPressed()){ //increase speed
                motorleft.setPower(motorleft.getPower()+0.05);
                motorright.setPower(motorright.getPower()+0.05);
                telemetry.addData("Motor Speed Left",motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());
            }else if(gamepad1.squareWasPressed()){ //Zero Speed
                motorleft.setPower(0);
                motorright.setPower(0);
                telemetry.addData("Motor Speed Left",motorleft.getPower());
                telemetry.addData("Motor Speed Right: ",motorright.getPower());
            }

            //intaking
            if(gamepad1.leftBumperWasPressed()){
                intake.setPower(1);
            }else if(gamepad1.rightBumperWasPressed()){
                intake.setPower(0);
            }

            //kick ball dpad
            if(gamepad1.dpad_up){
                //RESET
                kickBall.setPosition(1);
            }else if(gamepad1.dpad_left){
                //SHOOT
                kickBall.setPosition(0);
            }
            else if(gamepad1.dpad_right){
                //GET READY
                kickBall.setPosition(0.5);
            }

            //WHEEEEEEEELLLSSSSSSS GO WEEEEEEE
            //i dont get this stuff :(
            //dima did math in robotics rip
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;

            frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontleft.setPower(y+x+r);
            frontright.setPower(y-x-r);
            backleft.setPower(y-x+r);
            backright.setPower(y+x-r);

            //update update update
            telemetry.update();
        }
    }
}
