import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;
import java.io.ByteArrayOutputStream;

public class SHA256Sum {

    public static void printHash(String name, byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            System.out.println(DatatypeConverter.printHexBinary(md.digest(bytes)) + " *" + name);
        } catch (NoSuchAlgorithmException err) {
            System.out.println("Error: SHA-256 is not supported");
        }

    }

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[32 * 1024];
                int bytesRead;

                while ((bytesRead = System.in.read(buffer)) > 0) {
                    baos.write(buffer, 0, bytesRead);
                }

                printHash("-", baos.toByteArray());
            }

            for (int i = 0; i < args.length; ++i) {
                printHash(args[i], Files.readAllBytes(Paths.get(args[i])));
            }
        } catch (FileNotFoundException err) {
            System.out.println("Error: file not found");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

    }
}
