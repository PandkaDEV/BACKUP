package pl.pieszku.backup.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.pieszku.backup.basic.Backup;
import pl.pieszku.backup.manager.BackupManager;
import pl.pieszku.backup.util.Utils;

import java.util.Arrays;

public class BackupInventories implements Listener {


    public static Inventory openBackupPlayer(Player playerOpen, Player playerTarget){
        Inventory inventory = Bukkit.createInventory(playerOpen, 54, Utils.colored("&8* &cMENU BACKUP &8*"));


        ItemStack yellow_glass = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemStack lime_glass = new ItemStack(Material.LIME_STAINED_GLASS_PANE);

        inventory.setItem(0, yellow_glass);
        inventory.setItem(1, lime_glass);
        inventory.setItem(2, yellow_glass);
        inventory.setItem(3, lime_glass);
        inventory.setItem(4, yellow_glass);
        inventory.setItem(5, lime_glass);
        inventory.setItem(6, yellow_glass);
        inventory.setItem(7, lime_glass);
        inventory.setItem(8, yellow_glass);
        inventory.setItem(9, lime_glass);
        inventory.setItem(17, lime_glass);
        inventory.setItem(18, yellow_glass);
        inventory.setItem(26, yellow_glass);
        inventory.setItem(27, lime_glass);
        inventory.setItem(35, lime_glass);
        inventory.setItem(36, yellow_glass);
        inventory.setItem(49, lime_glass);
        inventory.setItem(44, yellow_glass);
        inventory.setItem(45, yellow_glass);
        inventory.setItem(46, lime_glass);
        inventory.setItem(47, yellow_glass);
        inventory.setItem(48, lime_glass);
        inventory.setItem(50, lime_glass);
        inventory.setItem(51, yellow_glass);
        inventory.setItem(52, lime_glass);
        inventory.setItem(53, yellow_glass);

        for(Backup backup : BackupManager.getBackupList()) {
            if(backup.getPlayer().equalsIgnoreCase(playerTarget.getName())) {
                ItemStack itemStack = new ItemStack(Material.OAK_SIGN);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(Utils.colored("&7BackupId:&f" + " " + backup.getId()));
                itemMeta.setLore(Arrays.asList(Utils.colored(
                        "&7Nick: &f" + backup.getPlayer()),
                        Utils.colored(
                                "&7UUID: &f" + backup.getUuid().toString()),
                        Utils.colored(
                                "&7Data: &f" + backup.getDate()),
                        Utils.colored(
                                "&7Zabójca: &c" + backup.getKiller()),
                        "",
                        Utils.colored(""),
                        Utils.colored(
                                "&7Aby oddać backup: &fLPM"),
                        Utils.colored(
                                "&7Aby usunąć backup: &fSHIFT + LPM"),
                        Utils.colored(
                                "&7Aby zobaczyć podgląd: &fPPM")
                ));
                itemStack.setItemMeta(itemMeta);

                inventory.addItem(itemStack);
            }
        }
        return inventory;
    }
    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equalsIgnoreCase(Utils.colored("&8* &cMENU BACKUP &8*"))){
            event.setCancelled(true);

            if(event.getCurrentItem() == null)return;
            if(event.getCurrentItem().getItemMeta() == null)return;
            if(event.getCurrentItem().getItemMeta().getLore() == null)return;

            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.colored(event.getCurrentItem().getItemMeta().getDisplayName()))){
                int id = Integer.parseInt(event.getCurrentItem().getItemMeta().getDisplayName().substring(14));
                Backup backup = BackupManager.getBackupId(id);
                if(backup == null)return;
                Player playerTarget = Bukkit.getPlayer(backup.getPlayer());
                if(playerTarget == null)return;
                if(event.getClick() == ClickType.LEFT){
                    playerTarget.getInventory().setContents(backup.getInventoryContent());
                    playerTarget.getInventory().setArmorContents(backup.getArmorContent());
                    player.closeInventory();
                    Utils.sendMessage(playerTarget, "&7Otrzymałeś swój backup z dnia: &f" + backup.getDate());
                    Utils.sendMessage(player, "&7Oddałeś backup graczowi: &f" + backup.getPlayer() + " &7z dnia: &f" + backup.getDate());
                    return;
                }
                if(event.getClick() == ClickType.RIGHT){
                    openBackupPlayerItems(player, backup);
                    return;
                }
                if(event.getClick() == ClickType.SHIFT_LEFT){
                    BackupManager.deleteBackup(backup);
                    player.closeInventory();
                    Utils.sendMessage(player, "&cPomyślnie usunięto backup gracz: &4" + backup.getPlayer() + " &7z dnia: &c" + backup.getDate());
                }
            }
        }
    }

    private void openBackupPlayerItems(Player player, Backup backup) {
        Inventory inventory = Bukkit.createInventory(player, 54, Utils.colored("&8* &cMENU BACKUP &8*"));

        ItemStack yellow_glass = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemStack lime_glass = new ItemStack(Material.LIME_STAINED_GLASS_PANE);

        inventory.setItem(0, yellow_glass);
        inventory.setItem(1, lime_glass);
        inventory.setItem(2, yellow_glass);
        inventory.setItem(3, lime_glass);
        inventory.setItem(4, yellow_glass);
        inventory.setItem(5, lime_glass);
        inventory.setItem(6, yellow_glass);
        inventory.setItem(7, lime_glass);
        inventory.setItem(8, yellow_glass);
        inventory.setItem(9, lime_glass);
        inventory.setItem(17, lime_glass);
        inventory.setItem(18, yellow_glass);
        inventory.setItem(26, yellow_glass);
        inventory.setItem(27, lime_glass);
        inventory.setItem(35, lime_glass);
        inventory.setItem(36, yellow_glass);
        inventory.setItem(49, lime_glass);
        inventory.setItem(44, yellow_glass);
        inventory.setItem(45, yellow_glass);
        inventory.setItem(46, lime_glass);
        inventory.setItem(47, yellow_glass);
        inventory.setItem(48, lime_glass);
        inventory.setItem(50, lime_glass);
        inventory.setItem(51, yellow_glass);
        inventory.setItem(52, lime_glass);
        inventory.setItem(53, yellow_glass);


        Arrays.stream(backup.getArmorContent()).forEach(itemStack -> {
            if(itemStack == null)return;
           inventory.addItem(itemStack);
        });

        Arrays.stream(backup.getInventoryContent()).forEach(itemStack -> {
            if(itemStack == null)return;
            inventory.addItem(itemStack);
        });




        player.openInventory(inventory);
    }
}
