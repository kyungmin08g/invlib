package io.github.invlib.lib;

import io.github.invlib.property.InvProperty;
import io.github.invlib.plugin.InvLibPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class InvLib implements InventoryHolder {

    private static final Plugin plugin = InvLibPlugin.getPlugin(InvLibPlugin.class);

    public static Inventory inv;
    private static int number;

    // 새로운 인벤토리를 만들 때 사용되는 메소드
    public static InvLib frame(int lines, Component title, InvProperty init) {
        inv = Bukkit.createInventory(null, lines * 9, title);
        number = lines;
        init.initInventory();

        return null;
    }

    // 인벤토리에 아이템을 원하는 좌표에 추가하고 그 아이템을 클릭했을 떄 호출되는 메소드
    public static void slot(int x, int y, ItemStack item, Consumer<InventoryClickEvent> onClickEvent) {
        inv.setItem((number * 9 - 8) + x - 1 - (y * 9), item);

        Bukkit.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onClick(InventoryClickEvent event) {
                if (event.getInventory() == inv && !(event.getClickedInventory() == event.getView().getBottomInventory()) && event.getSlot() == (number * 9 - 8) + x - 1 - (y * 9)) {
                    event.setCancelled(true);
                    if (onClickEvent == null) return;

                    onClickEvent.accept(event);
                }
            }
        }, plugin);
    }

    // 아이템을 채우는 용도로 사용되는 메소드
    public static void list(ItemStack item, Consumer<InventoryClickEvent> onClickEvent) {
        for (int i = 0; i <= number * 9 - 1; i++) {
            inv.setItem(i, item);
        }

        Bukkit.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onClick(InventoryClickEvent event) {
                if (event.getInventory() == inv && !(event.getClickedInventory() == event.getView().getBottomInventory())) {
                    event.setCancelled(true);
                    if (onClickEvent == null) return;

                    onClickEvent.accept(event);
                }
            }
        }, plugin);
    }

    // 인벤토리가 열렸을 때 호출될 메소드
    public static void onOpen(Consumer<InventoryOpenEvent> onOpenEvent) {
        Bukkit.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onOpen(InventoryOpenEvent event) {
                if (event.getInventory() == inv) onOpenEvent.accept(event);
            }
        }, plugin);
    }

    // 인벤토리가 닫힐 떄 호출될 메소드
    public static void onClose(Consumer<InventoryCloseEvent> onCloseEvent) {
        Bukkit.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onOpen(InventoryCloseEvent event) {
                if (event.getInventory() == inv) onCloseEvent.accept(event);
            }
        }, plugin);
    }

    // 인벤토리 보관함을 클릭했을 떄 호출될 메소드
    public static void onClickBottom(Consumer<InventoryClickEvent> onClickEvent) {
        Bukkit.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onClickBottom(InventoryClickEvent event) {
                if (event.getInventory() == inv && event.getClickedInventory() == event.getView().getBottomInventory()) {
                    event.setCancelled(true);
                    onClickEvent.accept(event);
                }
            }
        }, plugin);
    }

    // 플레이어에게 만든 인벤토리를 보여주는 메소드
    public static void openFrame(Player player, InvLib invLib) {
        player.openInventory(inv);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv;
    }
}
