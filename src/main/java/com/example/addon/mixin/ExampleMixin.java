package com.example.addon.mixin;

import com.example.addon.modules.Shaddap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.CommandSuggestionsS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static meteordevelopment.meteorclient.utils.player.ChatUtils.info;


@Mixin(ClientPlayNetworkHandler.class)
public abstract class ExampleMixin {
    @Inject(method = "onChatMessage", at = @At("HEAD"), cancellable = true)
    private void onPacket(ChatMessageS2CPacket packet, CallbackInfo ci) {
        if (new Shaddap().isActive()) {
            String content = packet.body().content();
            String replaced = content.replace(new Shaddap().be4.get(), new Shaddap().aftr.get());
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of(replaced));
            ci.cancel();
        }
    }
    @Inject(method="onCommandSuggestions", at = @At("HEAD"))
    private void onSuggest(CommandSuggestionsS2CPacket packet, CallbackInfo ci) {
        List<String> aclist = List.of("grimac", "vulcan", "ncp", "nocheatplus", "matrix", "spartan", "anticheatx");
        List<String> funlist = List.of("axshulkers", "eshulkerbox", "deluxeauctions", "chestshop");
        List<String> dumplist = List.of("placeholderapi", "litebans", "holographicdisplays","commandpanels", "plugman", "plugmanx");
        try {
            if (packet.id() == -1337) {
                for (CommandSuggestionsS2CPacket.Suggestion i : packet.suggestions()) {
                    if (aclist.contains(i.text().toLowerCase())) {
                        info("Anticheat: §c" + i.text());
                    } else if (funlist.contains(i.text().toLowerCase())){
                        info("Potentially funny plugin: §e" + i.text());
                    } else if (dumplist.contains(i.text().toLowerCase())){
                        info("Dumping plugin: §6" + i.text());
                    } else {
                        info("Plugin: §a" + i.text());
                    }



                }
            } else if (packet.id() == -1338) {
                for (CommandSuggestionsS2CPacket.Suggestion i : packet.suggestions()) {
                    info("Skript command: §a" + i.text());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
