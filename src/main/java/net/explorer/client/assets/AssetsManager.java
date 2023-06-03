package net.explorer.client.assets;

import net.explorer.client.main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class AssetsManager {
    private static AssetsManager instance;
    private Main main;
    private Path assetsDir;
    private final Map<String, Path> filePathCache;

    public AssetsManager() {
        instance = this;
        this.filePathCache = new HashMap<>();
    }

    public void setMain(Main main) {
        if (this.main == null) this.main = main;
        else throw new RuntimeException("Reassignment of main");
        this.mainOnlyInit();
        System.out.println("Assigned main!");
    }

    private void mainOnlyInit() {
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

    public Path addToCache(String name, Path path) {
        this.filePathCache.put(name, path);
        return path;
    }

    public boolean isAvailableInCache(String name) {
        return this.filePathCache.containsKey(name);
    }

    public Path getPathInCache(String name) {
        return this.filePathCache.get(name);
    }

    public Path getPathInCacheOrAddToCache(String name, Path fallback) {
        if (this.isAvailableInCache(name)) return this.filePathCache.get(name);
        else return this.addToCache(name, fallback);
    }
}
