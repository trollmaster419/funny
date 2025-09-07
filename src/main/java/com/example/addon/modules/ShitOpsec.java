package com.example.addon.modules;

import com.example.addon.AddonTemplate;
import meteordevelopment.meteorclient.systems.modules.Module;
import net.minecraft.network.packet.c2s.play.RequestCommandCompletionsC2SPacket;

public class ShitOpsec extends Module {


    public ShitOpsec() {
        super(AddonTemplate.CATEGORY, "shit-opsec", "Server recon");
    }

    @Override
    public void onActivate() {
        if (mc.getNetworkHandler() != null) {
            mc.getNetworkHandler().sendPacket(new RequestCommandCompletionsC2SPacket(1337,"/ver "));
            mc.getNetworkHandler().sendPacket(new RequestCommandCompletionsC2SPacket(1338, "/skript:"));
            toggle();
        }
    }


    @Override
    public void onDeactivate() {
    }
}
