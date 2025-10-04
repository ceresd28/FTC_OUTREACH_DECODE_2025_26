package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Goose_Gotham_Program")
public class GooseGOTHAM extends LinearOpMode {

    //lets instanitate the 6 motors!
    DcMotor motorleft, motorright, frontright, frontleft, backright, backleft;

    @Override
    public void runOpMode() throws InterruptedException {

        //set the motors to the postions on CHUB + UHUB
        motorleft = hardwareMap.get(DcMotor.class, "motorleft");
        motorright = hardwareMap.get(DcMotor.class, "motorright");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");

        //UNO REVERSE THOSE MOTORS
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorright.setDirection(DcMotor.Direction.REVERSE);

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

            //Dpad hardcoded
            if(gamepad1.dpad_up){
                motorleft.setPower(0.4);
                motorright.setPower(0.4); //far zone!
            }else if(gamepad1.dpad_down){
                motorleft.setPower(0.2);
                motorright.setPower(0.2); //near zone! (very edge)
            }
            else if(gamepad1.dpad_right){
                motorleft.setPower(0); //quick turn off
                motorright.setPower(0); //quick turn off
            }

            //WHEEEEEEEELLLSSSSSSS GO WEEEEEEE
            //i dont get this stuff :(
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
