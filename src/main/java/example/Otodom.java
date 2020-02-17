package example;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Otodom {
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        long start = System.currentTimeMillis();

        // ready from oracle site solution to read page directly from url
        URL otodom = new URL("https://www.otodom.pl/sprzedaz/mieszkanie/warszawa/");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(otodom.openStream()));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder(); //create stringbuilder
        while ((inputLine = in.readLine()) != null)
            stringBuilder.append(inputLine);                //add each line to stringbuilder
            stringBuilder.append(System.lineSeparator());   //add new line separator
        in.close();
        String content = stringBuilder.toString();          //variable content

        Set<String> setOfLinks = new TreeSet<>();

        for (int i = 0; i <content.length() ; i++) {
            i = content.indexOf("https://www.otodom.pl/oferta/" , i);
            if(i<0) break;
            String link = content.substring(i).split(".html")[0];
            setOfLinks.add(link);
        }

        for (int i = 0; i < setOfLinks.size(); i++) {

            int finalI = i;
            executorService.submit(() -> {
                try {
                    readWebSite(setOfLinks.toArray()[finalI].toString(), finalI +"html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
        executorService.shutdown();

        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }

    public static void readWebSite(String link, String fileName) throws IOException {
        URL otodom = new URL(link);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(otodom.openStream()));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder(); //create stringbuilder
        while ((inputLine = in.readLine()) != null)
            stringBuilder.append(inputLine);                //add each line to stringbuilder
        stringBuilder.append(System.lineSeparator());   //add new line separator
        in.close();


        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
        bw.write(stringBuilder.toString());
        bw.close();

    }
}
