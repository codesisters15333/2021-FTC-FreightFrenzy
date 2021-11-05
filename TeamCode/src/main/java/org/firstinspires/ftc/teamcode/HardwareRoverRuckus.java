package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ashley.peake on 8/30/2018.
 */

public class HardwareRoverRuckus<crServo> {

    //Wheels
    public DcMotor leftFront;
    public DcMotor leftBack;
    public DcMotor rightFront;
    public DcMotor rightBack;

    //int driveTime;

    //big wheels

    public DcMotor bigWheelA;
    //public DcMotor bigWheelB;

    //small wheels
    public DcMotor smallWheels;

    //claw

    public CRServo clawA;
    public CRServo clawB;

    //----------------------------------METHODS------------------------------------------------------

    //Constructor
/*   public HardwareRoverRuckus() {

   }

 */
    //---------------------------------INIT--ROBOT---------------------------------------------------
   /*  This method allows us to initialize the motors and sensors only once.
       It is used in every other program after "Init" is pressed.
    */

    public void initializeRobot(HardwareMap hwMap) {

        HardwareMap HWMap = hwMap;

        //initialize motors

        leftFront = HWMap.dcMotor.get("leftFront");
        leftBack = HWMap.dcMotor.get("leftBack");
        rightFront = HWMap.dcMotor.get("rightFront");
        rightBack = HWMap.dcMotor.get("rightBack");

        //leftFront.setDirection(DcMotor.Direction.REVERSE);

        //big wheels on top
        bigWheelA = HWMap.dcMotor.get("bigWheelA");
        //bigWheelB = HWMap.dcMotor.get("bigWheelB");

        //small wheels
        smallWheels = HWMap.dcMotor.get("smallWheels");

        //top servo
       //  clawA = HWMap.CRServo.get("clawA");

        //bottom servo
        //clawB = HWMap.CRServo.get("clawB");

    }

//--------------------------DRIVE--FORWARD-------------------------------------------------------

   /*  This method allows the robot to drive forward based on encoder values.
       A distance is given that is converted to an encoder position in the code.
       leftDirection and rightDirection set the direction of the motors to allow
       the robot to either mover straight ot turn.
    */

    public void driveStraight(double power, double totalInches, int Direction) throws InterruptedException { //For driving

        // declare variables for this method


        leftFront.setPower(power * Direction);
        leftBack.setPower(power * Direction);
        rightFront.setPower(power * -Direction);
        rightBack.setPower(power * -Direction);

        // long totalSeconds = 0;
        // Thread.sleep(totalSeconds);

        // stops all motion

        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
    }

    //right = positive direction
    public void DriveTurn(double power, long totalSeconds, int Direction) throws InterruptedException {
        leftFront.setPower(power * -Direction);
        leftBack.setPower(power * -Direction);
        rightFront.setPower(power * -Direction);
        rightBack.setPower(power * -Direction);

        Thread.sleep(totalSeconds);


        // stops all motion

        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
    }

    public void DriveSideways(double power, long totalSeconds, int Direction) throws InterruptedException {
        leftFront.setPower(power * -Direction);
        leftBack.setPower(power * Direction);
        rightFront.setPower(power * -Direction);
        rightBack.setPower(power * Direction);

        Thread.sleep(totalSeconds);
        // stops all motion

        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
    }


//------------------------------DRIVE--SIDEWAYS--------------------------------------------------
   /*  This method makes the robot move in a sideways direction.
       It is separate from the other drive method because the motors on each side must turn
       in opposite directions. (See Mecanum Drive diagram)
       Because the weight on the robot is not evenly balanced, we found that each motor
       needed to run at a slightly different power (and thus had a slightly different encoder count).

   public void mechanum(float x, float y, float r){
       double leftFrontPower = Range.clip(y + x + r, -1.0, 1.0);
       double leftBackPower = Range.clip(y - x + r, -1.0, 1.0);
       double rightFrontPower = Range.clip(y - x - r, -1.0, 1.0);
       double rightBackPower = Range.clip(y + x - r, -1.0, 1.0);

       leftFront.setPower(leftFrontPower);
       leftBack.setPower(leftBackPower);
       rightFront.setPower(rightFrontPower);
       rightBack.setPower(rightBackPower);
   }

   public void driveMecanum(double power, double totalInches, int leftFrontDirection, int leftBackDirection, int rightFrontDirection, int rightBackDirection) { //For driving

       // declare variables for this method

       int NewLeftFrontTarget;
       int NewLeftBackTarget;
       int NewRightFrontTarget;
       int NewRightBackTarget;

       //set encoder values to zero
       leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

       // counts = inches * 1120 (counts per revolution)/ 4*pi (circumference of wheels)
       // 1120/4*pi ~ 89 - we found that there was less error when given a precise number

       NewLeftFrontTarget = (int) (totalInches * 89 * .9);
       NewLeftBackTarget = (int) (totalInches * 89);
       NewRightFrontTarget = (int) (totalInches * 89 * .68);
       NewRightBackTarget = (int) (totalInches * 89 * .85);

       //get current position of the encoders at the start of the method

       leftFront.setTargetPosition(leftFrontDirection * NewLeftFrontTarget);
       leftBack.setTargetPosition(leftBackDirection * NewLeftBackTarget);
       rightFront.setTargetPosition(rightFrontDirection * NewRightFrontTarget);
       rightBack.setTargetPosition(rightBackDirection * NewRightBackTarget);

       leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

       //reset the timeout time  and start motion

       //powers scaled scaled based on trials & by the same as encoder values, so that everything
       //stops at the same time

       leftFront.setPower(power * leftFrontDirection * .9);
       leftBack.setPower(power * leftBackDirection);
       rightFront.setPower(power * rightFrontDirection * .68);
       rightBack.setPower(power * rightBackDirection * .85);

       while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy()) {

       }

       // stops all motion

       leftFront.setPower(0.0);
       leftBack.setPower(0.0);
       rightFront.setPower(0.0);
       rightBack.setPower(0.0);
*/


    public void bigWheelA(double power) {
        bigWheelA.setPower(power);
        //bigWheelA.setPower(-power);
    }

   /*public void bigWheelB(double power) {
      bigWheelB.setPower(power);
      bigWheelB.setPower(-power);
    */

    public void smallWheels(double power) {
        smallWheels.setPower(-power);
        //smallWheelsB.setPower(-power);
    }}

//
/*public void handA (double positionL, double positionR) {
    clawA.setPosition(positionL);
     clawB.setPosition((positionR)); */


