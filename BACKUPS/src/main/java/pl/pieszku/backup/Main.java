package pl.pieszku.backup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.pieszku.backup.basic.database.MySQL;
import pl.pieszku.backup.command.BackupCommand;
import pl.pieszku.backup.inventory.BackupInventories;
import pl.pieszku.backup.listeners.PlayerDeathListener;
import pl.pieszku.backup.tasks.SaveTasks;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {


    private static MySQL mySQL;
    private static Main main;

    public static Main getInstance(){
        return main;
    }

    public static MySQL getMySQL() {
        return mySQL;
    }
    @Override
    public void onLoad(){
        main = this;
        mySQL = new MySQL();
    }

    @Override
    public void onEnable() {
        getMySQL().connection();
        getMySQL().init();
        new SaveTasks().start();
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new BackupInventories(), this);
        new BackupCommand(this);
        Logger.getAnonymousLogger().info("BACKUP - Plugin na backup uruchomiono Author: Pieszku");

    }

    @Override
    public void onDisable() {
        getMySQL().saveBackups();
    }
}
