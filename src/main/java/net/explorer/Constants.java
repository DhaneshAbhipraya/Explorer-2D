package net.explorer;

import net.explorer.version.MMPQVersion;

public class Constants {
    public static final boolean GRAVITY_ENABLED = false;
    public static final MMPQVersion version;

    static {
        version = MMPQVersion.parse(System.getProperty("projectVersion", ""));
    }
}
