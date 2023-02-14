import javax.swing.text.Style;
import java.util.SimpleTimeZone;
import java.util.zip.*;
import java.io.*;
class ZipDirTest {
    public  static void exec(String zipFile, String zippedDir)
        throws FileNotFoundException, IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
        walkingDir (zos, zippedDir);
        zos.close();
    }
    public static void walkingDir (ZipOutputStream zos, String zippedDir) {
        File zipDir = new File(zippedDir);
        String[] dirList = zipDir.list();

        for (int i = 0; i <dirList.length; i++){
            File f = new File(dirList[i]);
            if(f.isDirectory()){
                String filePath = f.getPath();
                walkingDir(zos, filePath);
                continue;
            }else {
                try {
                    byte[] readBuffer = new byte [2048];
                    int bytesReaded = 0;
                    String fullPath = zippedDir + "\\" +f.getPath();
                    System.out.println("\t архивируется" + fullPath);
                    FileInputStream fis = new FileInputStream(fullPath);
                    ZipEntry ze = new ZipEntry(fullPath);
                    zos.putNextEntry(ze);

                    while ((bytesReaded = fis.read(readBuffer)) != -1) {
                        zos.write(readBuffer, 0, bytesReaded);
                    }
                    fis.close();
                }catch (FileNotFoundException fnfe){
                    System.out.println(fnfe.getMessage());
                }catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
            }
        }
    }

}
class ZipDirTest1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ZipDirTest <имя_архива> <имя_папки>");
            return;
        }
        String zipFile = args[0];
        String zippedDir = args[1];
        try {
            System.out.println("Начало архивации папки:" + zippedDir);

            ZipDirTest.exec(zipFile, zippedDir);
            System.out.println("Архив был записан успешно:" + zipFile);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
