package pl.pieszku.backup.util;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.*;

public class ItemSerializer {

    public static String itemsToBase64(final ItemStack[] items) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeInt(items.length);
            for (final ItemStack item : items) {
                bukkitObjectOutputStream.writeObject(item);
            }
            bukkitObjectOutputStream.close();
            return Base64Coder.encodeLines(byteArrayOutputStream.toByteArray());
        }
        catch (Exception ex) {
            return "";
        }
    }

    public static ItemStack[] itemsFromBase64(final String data) {
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            final BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
            final ItemStack[] items = new ItemStack[bukkitObjectInputStream.readInt()];
            for (int i = 0; i < items.length; ++i) {
                items[i] = (ItemStack)bukkitObjectInputStream.readObject();
            }
            bukkitObjectInputStream.close();
            return items;
        }
        catch (ClassNotFoundException | IOException ex5) {
            final Exception ex4 = null;
            final Exception ex3 = ex4;
            return new ItemStack[0];
        }
    }
}

