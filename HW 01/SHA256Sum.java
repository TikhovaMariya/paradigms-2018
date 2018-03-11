import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

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
                Scanner sc = new Scanner(System.in, "UTF-8");
                sc.useDelimiter("\\Z");
                String s = "";
                if (sc.hasNext()) {
                    s = sc.next();
                }
                printHash("-", s.getBytes(StandardCharsets.UTF_8));
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
