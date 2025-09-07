package com.example.addon;
import com.example.addon.modules.*;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;

public class AddonTemplate extends MeteorAddon {
    public static final Category CATEGORY = new Category("shit");
    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public void onInitialize() {

        // Modules
        Modules.get().add(new ViaCrash());
        Modules.get().add(new SillyThing());
        Modules.get().add(new Shaddap());
        Modules.get().add(new GigCrash());
        Modules.get().add(new ShitOpsec());
    }


    @Override
    public String getPackage() {
        return "com.troll.funny";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("trollmaster419", "funny");
    }
}
