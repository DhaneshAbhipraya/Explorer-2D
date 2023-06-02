package net.explorer.version;

import java.util.Objects;

public class VersionTester {
    public static void main(String[] args) {
        // -- MMPQVersion Testing --
        System.out.println("MMPQVersion testing");
        // Test case 1: Version comparison
        MMPQVersion version1 = new MMPQVersion(1, 0, 0, MMPQVersion.Qualifier.RC);
        MMPQVersion version2 = new MMPQVersion(1, 0, 0, MMPQVersion.Qualifier.RC);
        if (version1.equals(version2)) System.out.println("Test case 1 passed!");
        else System.out.println("Test case 1 failed.");

        // Test case 2: String representation of version with qualifier
        MMPQVersion version3 = new MMPQVersion(2, 1, 0, MMPQVersion.Qualifier.BETA);
        if (Objects.equals(version3.toString(), "2.1.0-beta")) System.out.println("Test case 2 passed!");
        else System.out.println("Test case 2 failed.");

        // Test case 3: String representation of version with qualifier and subLevel
        MMPQVersion version4 = new MMPQVersion(3, 2, 1, MMPQVersion.Qualifier.ALPHA.withSubLevel(2));
        if (Objects.equals(version4.toString(), "3.2.1-alpha.2")) System.out.println("Test case 3 passed!");
        else System.out.println("Test case 3 failed.");

        // Test case 4: Version comparison with subLevel
        MMPQVersion version7 = new MMPQVersion(5, 1, 0, MMPQVersion.Qualifier.ALPHA.withSubLevel(1));
        MMPQVersion version8 = new MMPQVersion(5, 1, 0, MMPQVersion.Qualifier.ALPHA.withSubLevel(2));
        if (version7.equals(version8)) System.out.println("Test case 4 passed!");
        else System.out.println("Test case 4 failed.");

        // Test case 5: Version parsing from string
        MMPQVersion version9 = MMPQVersion.parse("6.2.3-beta.4");
        if (Objects.equals(version9.getQualifier(), MMPQVersion.Qualifier.BETA.withSubLevel(4)) && version9.getMajor() == 6 && version9.getMinor() == 2 && version9.getPatch() == 3)
            System.out.println("Test case 5 passed!");
        else System.out.println("Test case 5 failed.");

        // Test case 6: Version parsing from string without subLevel
        MMPQVersion version10 = MMPQVersion.parse("4.1.7-alpha");
        if (Objects.equals(version10.getQualifier(), MMPQVersion.Qualifier.ALPHA) && version10.getMajor() == 4 && version10.getMinor() == 1 && version10.getPatch() == 7)
            System.out.println("Test case 6 passed!");
        else System.out.println("Test case 6 failed.");

        // Test case 7: Version parsing from string without subLevel and with release qualifier
        MMPQVersion version11 = MMPQVersion.parse("0.5.7");
        if (Objects.equals(version11.getQualifier(), MMPQVersion.Qualifier.RELEASE) && version11.getMajor() == 0 && version11.getMinor() == 5 && version11.getPatch() == 7)
            System.out.println("Test case 7 passed!");
        else System.out.println("Test case 7 failed.");


        System.out.println("Current version: " + System.getProperty("projectVersion"));
    }
}

