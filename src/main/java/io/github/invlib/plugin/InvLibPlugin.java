package io.github.invlib.plugin;

import io.github.invlib.lib.InvLib;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static io.github.invlib.lib.InvLib.*;

public final class InvLibPlugin extends JavaPlugin implements CommandExecutor {

    ItemStack item1 = new ItemStack(Material.DIAMOND_SHOVEL);
    ItemStack item2 = new ItemStack(Material.DIAMOND_AXE);
    ItemStack item3 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

    @Override
    public void onEnable() {
        getLogger().info("The InvLib plugin has been activated.");

        Bukkit.getServer().getPluginCommand("open").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player && command.getName().equals("open")) {
            InvLib invLib = InvLib.frame(6, Component.text("인벤토리"));
                onOpen(onOpenEvent -> player.sendMessage("onOpenEvent 호출"));

                list(item3, null);

                slot(3, 2, item1, onClickEvent -> {
                    player.sendMessage(item1.getType() + "을 클릭함");
                });

                slot(5, 2, item2, null);

                onClose(onCloseEvent -> player.sendMessage("onCloseEvent 호출"));
                onClickBottom(onClickEvent -> player.sendMessage("onClickBottomEvent 호출"));
            InvLib.openFrame(player, invLib);

            return true;
        }

        return false;
    }

    @Override
    public void onDisable() {
        getLogger().info("InvLib plugin is disabled.");
    }
}
