package org.whs542.ftc2017.subsys;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.whs542.lib.Alliance;
import org.whs542.lib.OpModeTimer;
import org.whs542.lib.Toggler;

/**
 * Beacon Pusher Subsystem Class
 */

public class BeaconPusher {
    //private DcMotor beaconPusher;
    private Servo beaconPusher;
    //private TouchSensor touchSensor;
    public Color color;
    public Alliance side;

    private final double ENC_TICKS_PER_REV = 560;
    public boolean isBeaconPushed;

    private Toggler beaconToggler = new Toggler(2);

    public BeaconPusher(HardwareMap map, Alliance side)
    {
        color = new Color(map);
        this.side = side;

        //beaconPusher = map.dcMotor.get("beacon");
        beaconPusher = map.servo.get("beacon");
        //beaconPusher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //beaconPusher.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //beaconPusher.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //touchSensor = map.touchSensor.get("touch");

        isBeaconPushed = false;
    }


    public void extendPusher(boolean lBumper)
    {
        beaconToggler.changeState(lBumper);
        switch(beaconToggler.currentState())
        {
            case 0:
                //beaconPusher.setTargetPosition(0);
                //beaconPusher.setPower(0.3);
                beaconPusher.setPosition(0);
                break;
            case 1:
                //beaconPusher.setTargetPosition((int) ENC_TICKS_PER_REV * 9 / 8);
                //beaconPusher.setPower(0.3);
                beaconPusher.setPosition(1);
                break;
        }
       // if(touchSensor.isPressed())
        //{
            //beaconPusher.setPower(0.0);
            //beaconPusher.setTargetPosition(beaconPusher.getCurrentPosition() - 50);
            //beaconPusher.setPower(0.3);
        //}
    }

    public void extendPusherNoToggle(boolean extend)
    {
        if(extend /*& !touchSensor.isPressed()*/)
        {
            beaconToggler.setState(1);
            //beaconPusher.setTargetPosition((int)ENC_TICKS_PER_REV * 9 / 8);
            //beaconPusher.setPower(0.3);
            beaconPusher.setPosition(1);
        }
        /*else if(touchSensor.isPressed())
        {
            beaconToggler.setState(1);
            beaconPusher.setPower(0.0);
            beaconPusher.setTargetPosition(beaconPusher.getCurrentPosition() - 50);
            beaconPusher.setPower(0.3);
        }*/
        else
        {
            beaconToggler.setState(0);
            //beaconPusher.setTargetPosition(0);
            //beaconPusher.setPower(0.3);
            beaconPusher.setPosition(0);
        }
    }

    public boolean isBeaconPushed()
    {
        String status;
        boolean isPressed = false;

        if((color.state.equals("blue") && side == Alliance.BLUE) || (color.state.equals("red") && side == Alliance.RED)){
            status = "Match";
            extendPusherNoToggle(true);
            isPressed = true;
        }
        else if(color.state.equals("purple")){
            status = "Unknown";
        }
        else {
            status = "Not match";
        }
        return isPressed;
    }

    public String getBeaconPusherStatus()
    {
        String state = "";
        switch (beaconToggler.currentState())
        {
            case 0:
                state = "Not extended";
                break;
            case 1:
                state = "Extended";
                break;
        }
        return state;
    }



}