package year2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;

public class Challenge7 {


    static class File {
        private final int size;
        private final String name;

        public File(int size, String name) {
            this.size = size;
            this.name = name;
        }
    }

    private static final Tree tree = new Tree();

    static class Tree {

        private Directory rootDir = null;

        public Directory getRootDir() {
            return rootDir;
        }

        public void setRootDir(Directory rootDir) {
            this.rootDir = rootDir;
        }
    }
    private static Directory currentDir = null;
    private static final HashSet<Directory> directories = new HashSet<>();

    public static void main(String[] args) throws IOException {

        var reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Challenge7.class.getResourceAsStream("challenge7.txt"))));
        var line = "";
        while ((line = reader.readLine()) != null) {
            calculate(line);
        }
        System.out.println("\n\n\n");

        addAllDirectorySizes(tree.getRootDir());
        System.out.println("\n\n\n");

        System.out.println("Size of rootDir: " + tree.getRootDir().size);
        System.out.println("\n\n\n");

        System.out.println("all summed " + directories(tree.getRootDir()));

        System.out.println("\n\n\n");

        for (var dir : directories) {
            System.out.println(dir);
        }

        var free = 70000000 - tree.getRootDir().size;
        System.out.println("\n\n\n");
        var needed = 30000000 - free;


        System.out.println("free: " + free);
        System.out.println("space needed: " + needed);

        System.out.println("min. Spacedir: " + directories.stream().filter(dir -> dir.size >= needed).min(Comparator.comparingInt(dir -> dir.size)).get());
    }

    private static int directories(Directory startDir) {
        System.out.println("perfect checking out: " + startDir.name);
        //calculate how many directories there are in the tree with a size over 100000
        if (startDir.subdirectories.isEmpty()) {
            System.out.println("directory " + startDir.name + " is dir empty");
            if (startDir.size <= 100000) {
                System.out.println("found out that: " + startDir.name + " got size under 100k");
                return startDir.size;
            } else return 0;
        }
        var count = 0;
        for (var dir : startDir.subdirectories) {
            count += directories(dir);
        }
        System.out.println("sum of all dirs in: " + startDir.name + " is " + count);
        if (startDir.size <= 100000) {
            count += startDir.size;
        }
        return count;


    }

    private static void addAllDirectorySizes(Directory currentDir) {
        System.out.println("currentDir: " + currentDir.name);
        if (!currentDir.subdirectories.isEmpty()) {
            System.out.println("going to run through all directories of " + currentDir.name);
            for (Directory subDir : currentDir.subdirectories) {
                addAllDirectorySizes(subDir);
            }
            System.out.println("current dir:" + currentDir.name + " size: " + currentDir.size);
            currentDir.size += currentDir.subdirectories.stream().mapToInt(Directory::getSize).sum();

            System.out.println("new current dir: " + currentDir.name + " size: " + currentDir.size);
        } else {
            System.out.println("directory: " + currentDir.name + " done!");
        }
    }

    private static void calculate(String line) {
        if (line.equals("$ cd ..")) {
            assert currentDir != null;
            currentDir = currentDir.getHeadDirectory();
        } else if (line.startsWith("$ cd ")) {

            var folderName = line.replace("$ cd ", "");

            if (tree.getRootDir() == null) {
                var dir = new Directory(null, folderName);
                tree.setRootDir(dir);
                currentDir = tree.getRootDir();
                System.out.println("Created and set rootdir as: " + folderName);
                directories.add(dir);
                return;
            }
            System.out.println("Going to set current directory to:" + folderName);
            currentDir = currentDir.getSubDirectory(folderName);

        } else if (!line.equals("$ ls")) {
            if (!line.startsWith("dir")) {
                var fileInfo = line.split(" ");
                var fileName = fileInfo[1];
                var fileSize = Integer.parseInt(fileInfo[0]);
                assert fileSize > 0;
                var file = new File(fileSize, fileName);
                assert currentDir != null;
                currentDir.getFiles().add(file);
                currentDir.size += fileSize;
                System.out.println("Added new file: " + fileName + " with size: " + fileSize);
            } else {
                var dir = new Directory(currentDir, line.replace("dir ", ""));
                System.out.println("Going to add a new subdirectory to current dir: " + currentDir.name + " subdir name: " + dir.name);
                currentDir.getSubdirectories().add(dir);
                directories.add(dir);
            }
        } else {
            System.out.println("Listing all subdirectories and files in current directory: " + currentDir.name);
        }
    }

    static class Directory {
        private final Directory headDirectory;
        private final String name;
        private final ArrayList<Directory> subdirectories = new ArrayList<>();
        private final ArrayList<File> files = new ArrayList<>();
        private int size = 0;

        public Directory(Directory headDirectory, String name) {
            this.headDirectory = headDirectory;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Directory{" +
                    "name='" + name + '\'' +
                    ", size=" + size +
                    '}';
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Directory getHeadDirectory() {
            return headDirectory;
        }

        public String getName() {
            return name;
        }

        public ArrayList<Directory> getSubdirectories() {
            return subdirectories;
        }

        public Directory getSubDirectory(String directoryName) {
            for (var directory : getSubdirectories()) {
                if (directory.name.equalsIgnoreCase(directoryName))
                    return directory;
            }
            return null;
        }

        public ArrayList<File> getFiles() {
            return files;
        }
    }


}
