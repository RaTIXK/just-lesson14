package com.project14;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    /*    Person p = new Person("Rati", 34);

        try {
            p.setAge("12a");

        }catch (BadNumberStringException e){
            e.printStackTrace();
            //System.exit(-1);
            *//* return;*//*
        } finally {
            System.out.println("finaly :)");
        }
        System.out.println("Outer :(");*/
        printUrls("https://www.tsu.ge/", 3);
    }
    private static void printUrls(String rootUrl, int depth) throws IOException {
        System.out.println("Depth: " + depth);
        if(depth < 0) {
            return;
        }
        Document doc = null;
        try {
            doc = Jsoup.connect(rootUrl).get();
        }catch ( IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        /*  System.out.println((doc.title());*/
        Elements links = doc.select("a[href]");
        Elements pdfs = doc.select("a[href]");
        for (Element pdf: pdfs){
            String soumeUrl = pdf.absUrl("href");
            if(soumeUrl.endsWith(".pdf")){
                System.out.println("PDF: " + soumeUrl);
            }
        }
        for (Element link : links) {
            String someUrl = link.absUrl("href");
            if(someUrl.startsWith("https://www.tsu.ge/")) {
                System.out.println(someUrl);
                printUrls(someUrl, depth -1);
            }
        }
    }
}