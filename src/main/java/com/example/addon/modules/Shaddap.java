package com.example.addon.modules;


import com.example.addon.AddonTemplate;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;

public class Shaddap extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    public final Setting<String> be4 = sgGeneral.add(new StringSetting.Builder()
        .name("before")

        .defaultValue("clanker")
        .description("What to replace")

        .build()
    );
    public final Setting<String> aftr = sgGeneral.add(new StringSetting.Builder()
        .name("after")

        .defaultValue("crigger")
        .description("What to replace with")

        .build()
    );
    public Shaddap() {
        super(AddonTemplate.CATEGORY, "shaddap", "Replaces shit");
    }

}
