package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    @Autonomous(name = "Autonomous")


public class Auto extends LinearOpMode {
        DcMotor leftFront = null;
        DcMotor leftBack = null;
        DcMotor rightFront = null;
        DcMotor rightBack = null;

        //big wheel A
        DcMotor bigWheelA = null;

        //big wheel B
        DcMotor bigWheelB = null;

        //small wheels
        DcMotor smallWheels = null;

        @Override
        //this is the main method
        public void runOpMode() throws InterruptedException {
            leftFront = hardwareMap.dcMotor.get("leftFront");
            leftBack = hardwareMap.dcMotor.get("leftBack");

            leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            rightFront = hardwareMap.dcMotor.get("rightFront");
            rightBack = hardwareMap.dcMotor.get("rightBack");

            rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            smallWheels = hardwareMap.dcMotor.get("smallWheels");
            bigWheelA = hardwareMap.dcMotor.get("bigWheelA");

            smallWheels.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bigWheelA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



            //Add actions or rearrange the orders to make the robot complete the task
            waitForStart();

            /**The following code are for parking over the midfield tape
             assuming that the robot started facing the opponent.
             To start facing the wall, revert everything:
             driveBack first, and then turn*/
            // for blue, started near red square on the field
            //turnLeftTime(DRIVE_POWER, 500);
           // DriveForwardTime(DRIVE_POWER, 500);

            // for blue, started near the blue triangle
            //turnRightTime(DRIVE_POWER, 1000);
            //DriveForwardTime(DRIVE_POWER,1000);

            //for red, started near blue square on the field
            //turnRightTime(DRIVE_POWER, 1000);
            //DriveForwardTime(DRIVE_POWER, 1000);

            // for red, started near the red triangle
          //turnLeftTime(DRIVE_POWER, 500);
        //    DriveForwardTime(DRIVE_POWER,700);

            DriveForwardTime(DRIVE_POWER, 400);
            DriveBackTime(DRIVE_POWER,200);

            /**if the robot moves in the opposite direction than expected,
             * check the direction of wheels*/


        }


        double DRIVE_POWER = 0.5;

        public void DriveForward(double power) {
            leftBack.setPower(-power);
            leftFront.setPower(-power);
            rightBack.setPower(power);
            rightFront.setPower(power);
        }

        public void DriveForwardTime(double power, long time) throws InterruptedException {
            DriveForward(power);
            Thread.sleep(time);
        }


        public void DriveBack(double power) {
            leftBack.setPower(power);
            leftFront.setPower(power);
            rightBack.setPower(-power);
            rightFront.setPower(-power);
        }

        public void DriveBackTime(double power, long time) throws InterruptedException {
            DriveBack(power);
            Thread.sleep(time);
        }


        public void turnLeft(double power) {
            leftBack.setPower(-power);
            leftFront.setPower(power);
            rightBack.setPower(power);
            rightFront.setPower(-power);
        }

        public void turnLeftTime(double power, long time) throws InterruptedException {
            turnLeft(power);
            Thread.sleep(time);
        }


        //TurnRight
        public void turnRight(double power) {
            leftBack.setPower(power);
            leftFront.setPower(-power);
            rightBack.setPower(-power);
            rightFront.setPower(power);
        }

        public void turnRightTime(double power, long time) throws InterruptedException {
            turnRight(power);
            Thread.sleep(time);
        }





    }
