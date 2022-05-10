package main;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;

/**
 * configuration file accessor
 *
 * @author zaitian
 * @author Zhang Zeyu
 * @author Ni Ruijie
 *
 * @version 4.1
 * Adjust attributes for auto dark theme function.
 * @date 2022/5/7
 *
 * @version 4.0
 * Boost performance of generating default config.
 * Add a new attribute.
 * @date 2022/5/6
 *
 * @version 3.4
 * Added limit time config, and allowed managers to enable/disable timers.
 * @date 2022/4/27
 *
 * @version 3.3
 * Add animation speed config.
 * @date 2022/4/24
 *
 * @version 3.2
 * Automatically call loadConfig().
 * @date 2022/4/20
 *
 * @version 3.1
 * Add 3 leading time configs.
 * @date 2022/4/20
 *
 * @version 3.0
 * redesign code structure and function call
 * @date 4/19
 *
 * @version 2.1
 * create dir and file if not exits
 * @date 4/13
 *
 * @version 2.0
 * @date 4/8
 */
public abstract class Config {

    final static String dirName = "conf";
    final static String fileName = dirName + "/config.yaml";
    final static Path dirPath = Path.of(dirName);
    final static Path filePath = Path.of(fileName);

    static Yaml yaml = new Yaml();
    static LinkedHashMap config = null;
    /**
     * retrieve configuration
     * @param name tag name
     * @return tag value
     */
    public static String readConfig(String name) {
        if (config == null) {
            loadConfig();
        }
        return config.get(name).toString();
    }
    /**
     * read file to config variable
     */
    public static void loadConfig() {
        InputStream cfg = null;
        while(cfg == null) {
            try {
                cfg = new FileInputStream(fileName);
            } catch (FileNotFoundException e1) {
                try {
                    if (!Files.exists(dirPath)){
                        Files.createDirectory(dirPath);
                    }
                    establishConfig();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        config = yaml.load(cfg);
        try {
            cfg.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * reset configuration to default
     */
    public static void establishConfig() throws IOException {
        String strDefaultConfig = """
                # USER CONFIGURATION FILE
                            
                # --------------< GENERAL SETTINGS >--------------
                                    
                # Supported language: English.
                language: English
                                    
                # USB drive that holds ID document, recommended: E://, F://, G:// (Windows); /volumes/DRIVE NAME/ (Mac OS)
                idCardDrive: F://
                                    
                # --------------< AIRPORT SETTINGS >--------------
                                    
                # You can set the airport name here.
                airportName: Beijing International Airport
                                    
                # Enable/disable the check-in leading time function.
                enableCheckInLeadingTime: false
                # Check-in starts ... (hours) before departure.
                startCheckInLeadingTime: 24
                # Check-in stops ... (minutes) before departure.
                stopCheckInLeadingTime: 30
                                    
                # Enable/disable global timer.
                overallTimer: enable
                # Set the limit time (seconds) for the global Timer
                limitTime: 120
                                    
                # Enable/disable backstage timer.
                backStageTimer: enable
                                 
                # -------------< APPEARANCE SETTINGS >------------
                                    
                # Select a theme from the theme library.
                theme: Anchor
                
                # Select the dark theme from the theme library.
                darkTheme: Onyx
                # Enable/disable auto dark theme
                enableAutoDarkTheme: true
                                    
                # Animation speed (1-5, -1 to disable), default: 3.
                animationSpeed: 3
                """;
        FileWriter fileWriter = new FileWriter(String.valueOf(filePath), false);
        fileWriter.write(strDefaultConfig);
        fileWriter.close();
    }
//    /**
//     * modify configuration file
//     * @param name tag name
//     * @param value tag value
//     */
//    public static void addConfig(String name, String value){
//        try {
//            List<String> oldConf = Files.readAllLines(filePath);
//            String newConf = "";
//            for (String s : oldConf) {
//                if (!s.startsWith(name)) {                 //if duplicate
//                    newConf = newConf.concat(s + "\n");    //discard old
//                }
//            }
//            newConf = newConf.concat(name + ": " + value + "\n");
//            Files.writeString(filePath, newConf);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void addComment(String comment){
//        try {
//            List<String> oldConf = Files.readAllLines(filePath);
//            String newConf = "";
//            for (String s : oldConf) {
//                newConf = newConf.concat(s + "\n");
//            }
//            newConf = newConf.concat("\n# " + comment + "\n");
//            Files.writeString(filePath, newConf);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
