package brian.example.boot.multiple.file.reader.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@RestController
public class IndexController {

    @Value("files")     // The directory where properties files located
    File inputDir;

    @GetMapping("/")
    public String getIndex(){

        StringBuilder builder = new StringBuilder();
        File[] inputFiles = inputDir.listFiles();

        for(File file: inputFiles){
            try(FileInputStream s = new FileInputStream(file)) {
                Properties prop = new Properties();
                prop.load(s);

                builder.append("File:");builder.append(file.getPath());builder.append("<br />");
                builder.append("a.b.c.1:"); builder.append(prop.getProperty("a.b.c.1")); builder.append("<br />");
                builder.append("a.b.c.2:"); builder.append(prop.getProperty("a.b.c.2")); builder.append("<br />");
                builder.append("a.b.c.3:"); builder.append(prop.getProperty("a.b.c.3")); builder.append("<br />");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }
    
    
    @GetMapping("/test")
    public String getTest(){
        return "Test";
    }
}
