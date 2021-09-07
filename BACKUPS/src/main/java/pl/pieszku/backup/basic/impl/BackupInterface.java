package pl.pieszku.backup.basic.impl;

public interface BackupInterface {


    String toSQL();
    void setStatus(boolean status);
    boolean hasUpdate();
}
