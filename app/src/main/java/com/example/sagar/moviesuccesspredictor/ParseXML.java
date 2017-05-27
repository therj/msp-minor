package com.example.sagar.moviesuccesspredictor;

/**
 * Created by Sagar on 5/15/2017.
 */

public class ParseXML {
   private String str="";
char c;
    public  String parse(String received_xml){
         int x=0;   //removing unncessary xml data
        for(int i=0;i<received_xml.length();i++) {
            c = received_xml.charAt(i);
            if (c == '>') {
                x++;

            }
            if (x == 2) {
                i++;
                for (; i < received_xml.length(); i++) {
                    if (received_xml.charAt(i) == '<') {
                        break;
                    }
                    str = str + received_xml.charAt(i);

                }
                break;
            }
        }


    return str;
}

}
