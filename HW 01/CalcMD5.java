import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;

public class CalcMD5 {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"))) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //String emptyStringMD5 = DatatypeConverter.printHexBinary(md.digest(new String().getBytes("UTF-8")));
            while (in.ready()) {
                System.out.println(DatatypeConverter.printHexBinary(md.digest(Files.
                        readAllBytes(Paths.get(in.readLine())))));
            }
        } catch (NoSuchAlgorithmException err) {
            System.out.println("Error: MD5 is not supported");
        } catch (FileNotFoundException err) {
            System.out.println("Error: " + args[0] + " not found");
        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println("You should insert file name");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}