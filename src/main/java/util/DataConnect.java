package util;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.ArrayBlockingQueue;

public class DataConnect {
    private final static String DBURL = "amRiYzpteXNxbDovL2JjZC5ob21lLnBsLzAxMTU5MjU4X3NrbGVwP2F1dG9SZWNvbm5lY3Q9dHJ1ZSZ1c2VVbmljb2RlPXRydWUmdXNlSkRCQ0NvbXBsaWFudFRpbWV6b25lU2hpZnQ9dHJ1ZSZ1c2VMZWdhY3lEYXRldGltZUNvZGU9ZmFsc2Umc2VydmVyVGltZXpvbmU9VVRD";
    private final static String DBUSER = "MDExNTkyNThfc2tsZXA=";
    private final static String DBPASS = "YmF6YTEyMzsxMjM=";
    private final static String DBDRIVER = "Y29tLm15c3FsLmNqLmpkYmMuRHJpdmVy";

    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
    public static Connection getConnection() {
        try {
            queue.put(1);
        } catch (InterruptedException e) {
            System.out.println("ArrayBlockingQueue Error while putting; DataConnect.getConnection() --> " + e.getMessage());
        }
        try {
            Class.forName(new String(Base64.getDecoder().decode(DBDRIVER)));
            return DriverManager.getConnection(new String(Base64.getDecoder().decode(DBURL)), new String(Base64.getDecoder().decode(DBUSER)), new String(Base64.getDecoder().decode(DBPASS)));
        } catch (Exception ex) {
            System.out.println("Opening DB connection Error; DataConnect.getConnection() --> " + ex.getMessage());
            try {
                queue.take();
            } catch (InterruptedException e) {
                System.out.println("ArrayBlockingQueue Error while taking; DataConnect.getConnection() --> " + e.getMessage());
            }
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
            System.out.println("Connection close Error; DataConnect.close() --> " + ex.getMessage());
        }
        try {
            queue.take();
        } catch (InterruptedException e) {
            System.out.println("ArrayBlockingQueue Error; DataConnect.close() --> " + e.getMessage());
        }
    }
}