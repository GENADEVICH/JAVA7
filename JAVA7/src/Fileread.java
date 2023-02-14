import java.io.*;


public class Fileread {
    File file = new File("file.bin");
    InputStream is = new FileInputStream(file);
    long length = file.length();

    if (length > Integer.MAX_VALUE) {
        throw new IOException("Файл" + file.getName() + "слишком длинный");
    }
    byte[] bytes = new byte[(int) length];
    int offset = 0;
    int numRead = 0;
    while (offset < bytes.length &&
            (numRead=is.read(bytes offset; bytes.length-offset)) >= 0){
        offset += numRead;
    }
    if (offset < bytes.length) {
        throw new IOException("Не удалось прочитать файл" + file.getName() + "целиком.");
    }

    public Fileread() throws FileNotFoundException {
    }
    is.close();
}
