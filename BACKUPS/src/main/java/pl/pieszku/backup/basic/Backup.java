package pl.pieszku.backup.basic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.pieszku.backup.basic.impl.BackupInterface;
import pl.pieszku.backup.manager.BackupManager;
import pl.pieszku.backup.util.ItemSerializer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Backup implements BackupInterface {

    private final int id;
    private final UUID uuid;
    private final String player;
    private final ItemStack[] armorContent;
    private final ItemStack[] inventoryContent;
    private final int ping;
    private final String killer;
    private final String date;

    public Backup(Player player, String killer){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        this.id = BackupManager.getBackupList().size() + 1;
        this.uuid = player.getUniqueId();
        this.player = player.getName();
        this.armorContent = player.getInventory().getArmorContents();
        this.inventoryContent = player.getInventory().getContents();
        this.ping = 0;
        this.killer =  killer;
        this.date = formattedDate;
        BackupManager.backupList.add(this);
    }
    public Backup(ResultSet resultSet)throws SQLException{
        this.id = resultSet.getInt("ID");
        this.uuid = UUID.fromString(resultSet.getString("UUID"));
        this.player = resultSet.getString("PLAYER");
        this.armorContent = ItemSerializer.itemsFromBase64(resultSet.getString("ARMOR_CONTENT"));
        this.inventoryContent = ItemSerializer.itemsFromBase64(resultSet.getString("INVENTORY_CONTENT"));
        this.ping = resultSet.getInt("PING");
        this.killer = resultSet.getString("KILLER");
        this.date = resultSet.getString("DATE");
        BackupManager.backupList.add(this);
    }


    @Override
    public String toSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO `BACKUPS` VALUES (");
        stringBuilder.append("'" + this.getId() + "',");
        stringBuilder.append("'" + this.getUuid() + "',");
        stringBuilder.append("'" + this.getPlayer() + "',");
        stringBuilder.append("'" + ItemSerializer.itemsToBase64(this.getArmorContent()) + "',");
        stringBuilder.append("'" + ItemSerializer.itemsToBase64(this.getInventoryContent()) + "',");
        stringBuilder.append("'" + this.getPing() + "',");
        stringBuilder.append("'" + this.getKiller() + "',");
        stringBuilder.append("'" + this.getDate() + "'");
        stringBuilder.append(") ON DUPLICATE KEY UPDATE ");
        stringBuilder.append("ID='" + this.getId() + "',");
        stringBuilder.append("UUID='" + this.getUuid() + "',");
        stringBuilder.append("PLAYER='" + this.getPlayer() + "',");
        stringBuilder.append("ARMOR_CONTENT='" + ItemSerializer.itemsToBase64(this.getArmorContent()) + "',");
        stringBuilder.append("INVENTORY_CONTENT='" + ItemSerializer.itemsToBase64(this.getInventoryContent()) + "',");
        stringBuilder.append("PING='" + this.getPing() + "',");
        stringBuilder.append("KILLER='" + this.getKiller() + "',");
        stringBuilder.append("DATE='" + this.getDate() + "'");

        return stringBuilder.toString();
    }

    @Override
    public void setStatus(boolean status) {

    }

    @Override
    public boolean hasUpdate() {
        return false;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPlayer() {
        return player;
    }

    public ItemStack[] getArmorContent() {
        return armorContent;
    }

    public ItemStack[] getInventoryContent() {
        return inventoryContent;
    }

    public int getPing() {
        return ping;
    }

    public String getKiller() {
        return killer;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
