package net.explorer.version;

import java.util.Objects;

public class MMPQVersion {
    public static final MMPQVersion UNKNOWN = new MMPQVersion(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, Qualifier.RELEASE);
    private final long major;
    private final long minor;
    private final long patch;
    private final Qualifier qualifier;

    public MMPQVersion(long major, long minor, long patch, Qualifier qualifier) {
        if (patch < 1 && minor < 1 && major < 1) throw new IllegalStateException("Version must not be less than 1");
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.qualifier = qualifier;
    }

    public MMPQVersion() {
        this.major = 0;
        this.minor = 0;
        this.patch = 1;
        this.qualifier = Qualifier.ALPHA;
    }

    public static MMPQVersion parse(String versionString) {
        if (versionString.isEmpty() || versionString.equals(UNKNOWN.toString())) return UNKNOWN;
        String[] parts = versionString.split("\\.");
        int major = Integer.parseInt(parts[0]);
        int minor = Integer.parseInt(parts[1]);
        int patch = Integer.parseInt(parts[2].split("-")[0]);
        String subLevel = "";
        if (parts.length == 4)
            subLevel = "." + parts[3];
        else if (parts.length == 3)
            subLevel = "";
        String constructedString = "";
        if (parts[2].split("-").length == 2)
            constructedString = parts[2].split("-")[1] + subLevel;
        else if (parts[2].split("-").length == 1)
            constructedString = "";
        Qualifier qualifier = Qualifier.parse(constructedString);
        return new MMPQVersion(major, minor, patch, qualifier);
    }

    @Override
    public String toString() {
        return this == UNKNOWN ? "Unknown Version" : "%d.%d.%d%s".formatted(major, minor, patch, qualifier.equals(Qualifier.RELEASE) ? "" : ("-" + qualifier));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MMPQVersion version = (MMPQVersion) o;
        return major == version.major && minor == version.minor && patch == version.patch && qualifier == version.qualifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch, qualifier);
    }

    public long getMajor() {
        return major;
    }

    public long getMinor() {
        return minor;
    }

    public long getPatch() {
        return patch;
    }

    public Qualifier getQualifier() {
        return qualifier;
    }

    public enum Qualifier {
        ALPHA(0), BETA(1), RC(2), RELEASE(3);

        private final int level;
        private int subLevel;

        Qualifier(int level) {
            this.level = level;
        }

        public static Qualifier parse(String qualString) {
            String[] splitString = qualString.split("\\.");
            Qualifier qualifier1 = (switch (splitString[0]) {
                case "alpha" -> Qualifier.ALPHA;
                case "beta" -> Qualifier.BETA;
                case "rc" -> Qualifier.RC;
                default -> Qualifier.RELEASE;
            });
            if (splitString.length == 2)
                qualifier1.withSubLevel(Integer.parseInt(splitString[1]));
            return qualifier1;
        }

        Qualifier withSubLevel(int subLevel) {
            this.subLevel = subLevel;
            return this;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(switch (this.level) {
                case 0 -> "alpha";
                case 1 -> "beta";
                case 2 -> "rc";
                case 3 -> "";
                default -> throw new IllegalStateException("Level is not in range [0, 3]: " + this.level);
            });
            if (this.subLevel > 0 && this.level != 3) {
                builder.append(".");
                builder.append(this.subLevel);
            }
            return builder.toString();
        }
    }
}
