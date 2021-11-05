package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by ashley.peake on 8/30/2018.
 * Modified by Julia Zhou
 * Double modified by Claire Black 2021
 */



@TeleOp (name= "TeleOpTeam", group= "Linear Opmode")
public class TeleOpTeam extends LinearOpMode {

    Hardware Rover = new Hardware();

    // ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        System.out.println("Starting up");
        telemetry.addData("init pressed", "about to initialize");
        telemetry.update();

        System.out.println("Initialize Robot");
        Rover.initializeRobot(hardwareMap);
        System.out.println("Robot Initialized");

        telemetry.addData("Status", "Ready!");

        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            double fwdBack = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            if (gamepad1.start) {

                Rover.leftFront.setPower((fwdBack + 1.5 * strafe - turn) * .25);
                Rover.leftBack.setPower((fwdBack - 1.5 * strafe - turn) * .25);
                Rover.rightFront.setPower((-fwdBack + 1.5 * strafe - turn) * .25);
                Rover.rightBack.setPower((-fwdBack - 1.5 * strafe - turn) * .25);

            } else { // drive robot normally at full speed

                Rover.leftFront.setPower((fwdBack + strafe - turn));
                Rover.leftBack.setPower((fwdBack - strafe - turn));
                Rover.rightFront.setPower((-fwdBack + strafe - turn));
                Rover.rightBack.setPower((-fwdBack - strafe - turn));
            }


            /**Julia - try this, likely needs modification.
             * I have declared and initialized scissorLiftB in HardwareRoverRucks
             * The two lifts will move in the same direction together
             */


            if (gamepad2.dpad_up) {
                Rover.bigWheelA(1.0);
                //Rover.bigWheelB(1.0);
            } else if (gamepad2.dpad_down) {
                Rover.bigWheelA(-1.0);
                // Rover.bigWheelB(-1.0);
            } else {
                Rover.bigWheelA(0);
                // Rover.bigWheelB(0);


            }


            if (gamepad2.right_bumper) {
                Rover.smallWheels(1);
            } else if (gamepad2.left_bumper) {
                Rover.smallWheels(-1);
            } else {
                Rover.smallWheels(0);
            }


            if (gamepad2.a) {
                Rover.clawA.setPower(0.9);
                Rover.clawB.setPower(0.7);
            } else if (gamepad2.b) {
                Rover.clawA.setPower(0.5);
                Rover.clawB.setPower(0.5);
            } else if (gamepad2.y) {
                Rover.clawA.setPower(0);
                Rover.clawB.setPower(0);
            } else if (gamepad2.x){
                    Rover.clawA.setPower(0);
                }

            }
        }
    }




