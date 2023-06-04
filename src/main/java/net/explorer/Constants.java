package net.explorer;

import net.explorer.client.main.Explorer;
import net.explorer.version.MMPQVersion;

public class Constants {
    public static final boolean GRAVITY_ENABLED = false;
    public static final long tps = 20;
    public static MMPQVersion version;
    private Explorer explorer;

//    private static String getVersionFromPropertiesFile() {
//        try {
//            Path filePath = Path.of("version.properties");
//            Properties properties = new Properties();
//            properties.load(Files.newBufferedReader(filePath));
//            return properties.getProperty("version");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    public void register(Explorer explorer) {
        this.explorer = explorer;
        version = MMPQVersion.parse("0.1.0-beta");
    }
}
