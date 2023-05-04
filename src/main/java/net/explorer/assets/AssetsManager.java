package net.explorer.assets;

import net.explorer.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;

public class AssetsManager {
    private static AssetsManager instance;
    private Main main;
    private Path assetsDir;

    public AssetsManager() {
        instance = this;
    }

    public void setMain(Main main) {
        if (this.main == null) this.main = main;
        else throw new RuntimeException("Reassignment of main");
        this.init();
        System.out.println("Assigned main!");
    }

    private void init() {
        this.assetsDir = this.main.assetsDir;
    }

    public Image getImageFromFilePathString(String path) {
        try {
            return ImageIO.read(Path.of(this.assetsDir.toString(), path).toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AssetsManager getInstance() {
        return instance;
    }
}
