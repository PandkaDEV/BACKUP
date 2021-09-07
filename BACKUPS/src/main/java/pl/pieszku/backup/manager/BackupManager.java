package pl.pieszku.backup.manager;

import pl.pieszku.backup.Main;
import pl.pieszku.backup.basic.Backup;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BackupManager {

    public static List<Backup> backupList = new ArrayList<>();

    public static  void deleteBackup(Backup backupTarget){
        try {
            Statement statement = Main.getMySQL().connection.createStatement();
            statement.executeQuery("DELETE FROM `BACKUPS` WHERE `ID`='" + backupTarget.getId() + "'");
            backupList.remove(backupTarget);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static Backup getBackupId(int id){
        for(Backup backup : getBackupList()){
            if(backup.getId() == id){
                return backup;
            }
        }
        return null;
    }

    public static List<Backup> getBackupList() {
        return backupList;
    }
}
