import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Vector;
import java.lang.StringBuilder;

public class CalcSHA256 {
    public static void main(String[] args) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            if (args.length == 0) {
                Scanner sc = new Scanner(System.in, "UTF-8");
                sc.useDelimiter("\\Z");
                String s = "";
                if (sc.hasNext()) {
                    s = sc.next();
                }
                System.out.println(DatatypeConverter.printHexBinary(md.digest(s.getBytes(StandardCharsets.UTF_8))));
            } else {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"))) {
                    while (in.ready()) {
                        System.out.println(DatatypeConverter.printHexBinary(md.digest(Files.
                                readAllBytes(Paths.get(in.readLine())))));
                    }
                } catch (FileNotFoundException err) {
                    System.out.println("Error: " + args[0] + " not found");
                } catch (IOException err) {
                    System.out.println(err.getMessage());
                }
            }

        } catch (NoSuchAlgorithmException err) {
            System.out.println("Error: SHA-256 is not supported");
        }
    }
}



