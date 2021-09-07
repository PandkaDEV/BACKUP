package pl.pieszku.backup.basic.database;

import pl.pieszku.backup.basic.Backup;
import pl.pieszku.backup.manager.BackupManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class MySQL {


    public static  Connection connection;
    public String host = "127.0.0.1";
    public int port = 3306;
    public String table = "mitrox2";
    public String username = "mitrox";
    public String password = "k849Gd8a0-259sHtwVC/POL";

    public void connection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + table, username, password);
            Logger.getAnonymousLogger().info("Connection database success");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void init(){
        try {

            Statement statement = connection.createStatement();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS `BACKUPS` (");
            stringBuilder.append("ID int(11),");
            stringBuilder.append("UUID varchar(90),");
            stringBuilder.append("PLAYER varchar(32),");
            stringBuilder.append("ARMOR_CONTENT longText not null,");
            stringBuilder.append("INVENTORY_CONTENT longText not null,");
            stringBuilder.append("PING int(11),");
            stringBuilder.append("KILLER varchar(90),");
            stringBuilder.append("DATE varchar(160),");
            stringBuilder.append("primary key(ID));");
            statement.executeUpdate(stringBuilder.toString());

            ResultSet resultSet = statement.executeQuery("SELECT * FROM `BACKUPS`");
            while (resultSet.next()){
                new Backup(resultSet);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void saveBackups(){
        try {
            Statement statement = connection.createStatement();
            for(Backup backup : BackupManager.getBackupList()){
                statement.executeUpdate(backup.toSQL());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
