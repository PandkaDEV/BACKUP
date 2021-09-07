package pl.pieszku.backup.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.pieszku.backup.Main;
import pl.pieszku.backup.inventory.BackupInventories;
import pl.pieszku.backup.util.Utils;

import java.util.Objects;

public class BackupCommand implements CommandExecutor {

    public BackupCommand(Main main) {
        Objects.requireNonNull(main.getCommand("backup")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(!player.hasPermission("mgsurvival.backup")){
            return Utils.sendMessage(player, "&4Błąd: &cNie masz dostępu do: &4mgsurvival.backup");
        }
        if(args.length < 1){
            return Utils.sendMessage(player, "&7Poprawne użycie: &f/backup <nick>");
        }
        Player playerTarget = Bukkit.getPlayer(args[0]);
        if(playerTarget == null){
            return Utils.sendMessage(player, "&4Błąd: &cPodany gracz jest offline!");
        }
        player.openInventory(BackupInventories.openBackupPlayer(player, playerTarget));
        return false;
    }
}
