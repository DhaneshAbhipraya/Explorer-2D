package net.explorer;

import net.explorer.client.main.Main;
import net.explorer.version.MMPQVersion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Constants {
    public static final boolean GRAVITY_ENABLED = false;
    public static final long tps = 20;
    public static MMPQVersion version;
    private Main main;

    private static String getVersionFromPropertiesFile() {
        try {
            Path filePath = Path.of("version.properties");
            Properties properties = new Properties();
            properties.load(Files.newBufferedReader(filePath));
            return properties.getProperty("version");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void register(Main main) {
        this.main = main;
        version = MMPQVersion.parse(getVersionFromPropertiesFile());
    }
}
