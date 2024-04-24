package io.github.invlib.plugin;

import io.github.invlib.lib.InvLib;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class InvLibPlugin extends JavaPlugin implements Listener {

    ItemStack item1 = new ItemStack(Material.DIAMOND_SHOVEL);
    ItemStack item2 = new ItemStack(Material.DIAMOND_AXE);
    ItemStack item3 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

    @Override
    public void onEnable() {
        getLogger().info("The InvLib plugin has been activated.");

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        open(player);
    }

    public void open(Player player) {
        InvLib invLib = InvLib.frame(5, Component.text("테스트"), () -> {
            InvLib.onOpen(onOpenEvent -> player.sendMessage("onOpenEvent가 호출됨"));

            InvLib.list(item3, null);

            InvLib.slot(3, 2, item1, onClickEvent -> {
                player.sendMessage(item1.getType() + "을 클릭함");
            });

            InvLib.slot(5, 2, item2, null);

            InvLib.onClose(onCloseEvent -> player.sendMessage("onCloseEvent가 호출됨"));
            InvLib.onClickBottom(onClickEvent -> player.sendMessage("onClickBottomEvent가 호출됨"));
        });
        InvLib.openFrame(player, invLib);
    }

    @Override
    public void onDisable() {
        getLogger().info("InvLib plugin is disabled.");
    }
}
