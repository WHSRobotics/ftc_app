package org.whs542.lib.hwtest;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whs542.ftc2017.subsys.BeaconPusher;

/**
 * Created by Lucy on 11/22/2016.
 */

@TeleOp(name = "BeaconTest", group = "TeleOp")
public class BeaconTest extends OpMode
{
    BeaconPusher beaconPusher;

    public void init() {
        beaconPusher = new BeaconPusher(hardwareMap);
    }

    public void loop()
    {
        beaconPusher.extendBeacon(gamepad1.left_bumper);
    }
}