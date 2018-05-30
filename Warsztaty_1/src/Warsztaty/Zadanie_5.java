package Warsztaty;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Zadanie_5 {


    public static void main(String[] args) {
        Path path1 = Paths.get("popular_words.txt");
        Path path2 = Paths.get("filtered_popular_words.txt");
        File file = new File("popular_words.txt");
        Connection connect = (Connection) Jsoup.connect("http://www.onet.pl/");
        StringBuilder linksText = new StringBuilder();
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> filter = new ArrayList<String>() {{
            add("ponieważ");
            add("oraz");

        }};
        ArrayList<String> filteredWords = new ArrayList<>();
// wczytywanie linków i tworzenie z nich Stringa w StringBuilderze
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            for (Element element : links) {
                System.out.println(element.text());
                linksText.append(element.text() + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(linksText.toString());


//tworzymy tablicę a następnie zapisujemy słowa do pliku
        String[] splitLinks = linksText.toString().split(" ");
        System.out.println(Arrays.toString(splitLinks));
        for (String str : splitLinks) {
            words.add(str);
        }
        try {
            Files.write(path1, words);
        } catch (IOException e) {
            e.printStackTrace();
        }
// wczytujemy z pliku tekst, filtrujemy i zapisujemy w tymczasowej ArrayLiscie

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String str2 = scanner.nextLine();
                if (str2.length() > 3 && !filter.contains(str2)) {
                    filteredWords.add(str2);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(filteredWords);
        try {
            Files.write(path2, filteredWords);

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(filteredWords.toString());


    }
}

