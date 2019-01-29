package caax.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class test1 {


    public static void main (String args[]) throws IOException {


        Properties config=new Properties();
        Properties OR=new Properties();

        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\properties\\Config.properties");
        config.load(fis);
        fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\properties\\OR.properties");
        OR.load(fis);

        if(config.getProperty("browser").equals("chroma")){
            System.out.println("i'm in");
        }

    }
}
