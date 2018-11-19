package projektas1.demo;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SortingService {

    String fullList;
    Map<String, Integer> freaquencyMap;


    public void addFilesTextToOneString(List<MultipartFile> list) throws IOException {
        String result = new String();
        for(int i = 0; i < list.size(); i++){
            result += new String(list.get(i).getBytes());
        }
        fullList = result;
    }

    public void createFreaquencyMap(String filesText){
        Map<String,Integer> map = new HashMap<>();
        for (char ch = 'A'; ch <= 'Z'; ++ch){
            map.put(String.valueOf(ch), 0);
        }
        for(int i = 0; i < 10; i++){
            map.put(String.valueOf(i), 0);
        }
        String[] string = filesText.split(" ");

        for(String i : string){
            if(i.equals("")) continue;
            if(i.substring(0,1).matches("^[A-Za-z]*$")){
                String firstLetter = i.substring(0,1).toUpperCase();

                map.put(firstLetter, map.get(firstLetter) + 1);
            }
        }
        freaquencyMap = map;
    }

    public void createFiles() throws FileNotFoundException, UnsupportedEncodingException {
        createFreaquencyMap(fullList);
        String alphabet = "ABCDEFGHIJKLMOPQRSTUVXYZ";
        PrintWriter writer = new PrintWriter("./src/main/resources/static/GeneratedFiles/FileOne", "UTF-8");
        for(int i = 0; i < 5; i++){
            writer.println(alphabet.substring(i, i+1) + " " + "have" + " " + String.valueOf(freaquencyMap.get(alphabet.substring(i, i+1))) + " " + "occuerences");
        }
        PrintWriter writer2 = new PrintWriter("./src/main/resources/static/GeneratedFiles/FileTwo", "UTF-8");
        for(int i = 5; i < 11; i++){
            writer2.println(alphabet.substring(i, i+1) + " " + "have" + " " + String.valueOf(freaquencyMap.get(alphabet.substring(i, i+1))) + " " + "occuerences");
        }
        PrintWriter writer3 = new PrintWriter("./src/main/resources/static/GeneratedFiles/FileThree", "UTF-8");
        for(int i = 11; i < 24; i++){
            writer3.println(alphabet.substring(i, i+1) + " " + "have" + " " + String.valueOf(freaquencyMap.get(alphabet.substring(i, i+1))) + " " + "occuerences");
        }
        writer.close();
        writer2.close();
        writer3.close();
    }

    public void initDownload(List<MultipartFile> list) throws IOException {
        addFilesTextToOneString(list);
        createFreaquencyMap(fullList);
        createFiles();
    }


}
