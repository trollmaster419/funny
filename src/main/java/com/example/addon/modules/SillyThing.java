package com.example.addon.modules;



import java.util.Random;
import com.example.addon.AddonTemplate;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;


public class SillyThing extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final Setting<Integer> packets = sgGeneral.add(new IntSetting.Builder()
        .name("ppe")       // name in GUI
        .description("How many times to loop") // tooltip
        .defaultValue(1)                // default value
        .min(0)                         // minimum slider value
        .max((int) Double.POSITIVE_INFINITY)                       // maximum slider value
        .sliderMax(100)                 // slider drag limit
        .build()
    );
    public SillyThing() {
        super(AddonTemplate.CATEGORY, "silly-thing", "Forces you to go beyond uhh... something? I dont know...");
    }

    @Override
    public void onActivate() {
        if (mc.getNetworkHandler() == null) return;
        assert mc.player != null;

        double magicx = mc.player.getX();
        double magicy = mc.player.getY();
        double magicz = mc.player.getZ();
        for (int i = 0; i < packets.get(); i++) {
            mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(new Random().nextInt(-2000000000, 2000000000), new Random().nextInt(-2000000000, 2000000000), new Random().nextInt(-2000000000, 2000000000), true, true));
        }
        mc.player.setPos(magicx,magicy,magicz);
        mc.player.addVelocity(0,5,0);
        toggle();
    }


}
