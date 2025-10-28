package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@TeleOp(name = "GOOSE DECODE TELEOP")
public class GOOSE_DECODE_TELEOP extends LinearOpMode {

    //This was made by Adhithya Yuvaraj XD

    //lets instanitate the 7 motors!
    DcMotor motorright, motorleft, frontright, frontleft, backright, backleft, intake1, intake2;

    //servo kick motor
    VoltageSensor voltageSensor;
    Servo lowerStop, upperStop;
    double shooterSpeed = .47;
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
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Expansion Hub 2");
        //lowerStop = hardwareMap.get(Servo.class, "kickBall");
        //upperStop = hardwareMap.get(Servo.class, "upperStop");

        //upperStop.scaleRange(0,1); // 0=open, 1=close ////////////////

        //UNO REVERSE THOSE MOTORS
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorright.setDirection(DcMotorSimple.Direction.REVERSE);
        intake1.setDirection(DcMotorSimple.Direction.REVERSE);

        //INIT PART DONE!
        waitForStart();

        motorleft.setPower(0);
        motorright.setPower(0);


        while(opModeIsActive()){

            if (gamepad1.crossWasPressed()){ // off/on
                shoot(motorleft, motorright, voltageSensor, (motorleft.getPower()==0) ? shooterSpeed : 0);
            } else if(gamepad1.circleWasPressed()) { // decrease
                shooterSpeed = .3;
                shoot(motorleft, motorright, voltageSensor, shooterSpeed);
            } else if (gamepad1.triangleWasPressed()){ // norm
                shooterSpeed = .47;
                shoot(motorleft, motorright, voltageSensor, shooterSpeed);
            } else if(gamepad1.squareWasPressed()){ // increase
                shooterSpeed = .6;
                shoot(motorleft, motorright, voltageSensor, shooterSpeed);
            }

            //decreasing and increasing
            if (motorleft.getPower() > 0) {
                if(gamepad1.dpadDownWasPressed()){
                    shooterSpeed -= 0.05;
                    shoot(motorleft, motorright, voltageSensor, shooterSpeed);
                }
                if(gamepad1.dpadUpWasPressed()){
                    shooterSpeed += 0.05;
                    shoot(motorleft, motorright, voltageSensor, shooterSpeed);
                }
            }


            //INTAKE BUTTONS
            if(gamepad2.circleWasPressed()){ // spin intake1 norm way
                intake1.setDirection(DcMotorSimple.Direction.REVERSE);
                intake1.setPower(1-intake1.getPower());
            }
            if (gamepad2.squareWasPressed()) {
                intake1.setDirection(DcMotorSimple.Direction.FORWARD);
                intake1.setPower(1-intake1.getPower());
            }
            if (gamepad2.triangleWasPressed()) { // spin intake2 norm way
                intake2.setDirection(DcMotorSimple.Direction.FORWARD);
                intake2.setPower(1-intake2.getPower());
            }
            if(gamepad2.crossWasPressed()){
                intake2.setDirection(DcMotorSimple.Direction.REVERSE);
                intake2.setPower(1-intake2.getPower());
            }
            /*
            if(gamepad2.crossWasPressed()){
                upperStop.setPosition(0);
                sleep((long) thruRampTime);
                upperStop.setPosition(1);
            }if(gamepad2.squareWasPressed()){
                upperStop.setPosition(1);
            }
            */
            //LOWER STOP BUTTONS
            /*
            if(gamepad2.dpadLeftWasPressed()){
                lowerStop.setPosition(0.48);
            }else if(gamepad2.dpadRightWasPressed()){
                lowerStop.setPosition(0);
            }else if(gamepad2.dpadUpWasPressed()) {
                lowerStop.setPosition(0.24);
            }else if(gamepad2.dpadDownWasPressed()){
                lowerStop.setPosition(.8);
            }
             */

            //WHEELS
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;
            //NO RUNNING WITH ENCODERS!!!!
            frontleft.setPower((y+x+r)*speed);
            frontright.setPower((y-x-r)*speed);
            backleft.setPower((y-x+r)*speed);
            backright.setPower((y+x-r)*speed);
            telemetry.addData("Wheel Speed",frontleft.getPower());
            telemetry.addData("Wheel Speed: ",frontright.getPower());

            //updating stuff
            telemetry.addData("Shooter Speed Left",motorleft.getPower());
            telemetry.addData("Shooter Speed Right: ",motorright.getPower());
            telemetry.update();
        }
    }
    public static void shoot(DcMotor motorleft, DcMotor motorright,VoltageSensor voltage,double shooterSpeed) {
        double voltageCoefficient = (voltage.getVoltage()/12.5);
        motorleft.setPower(voltageCoefficient*shooterSpeed);
        motorright.setPower(voltageCoefficient*shooterSpeed);
    }
}
