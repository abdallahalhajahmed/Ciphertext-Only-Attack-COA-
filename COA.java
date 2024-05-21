package CryptographyMidterm;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



public class COA {
    public static void main(String[] args) {
   
        Rotor96Crypto myRotor = new Rotor96Crypto();
        
        String cipherText2419103a = "vvPjV@.epe+4r7lO+gUp/ot}2?|5yx>3u0&o9><(/VXQDVf/MQ)k~fLlW?K\\Y3 E/yu_fqFPXh~Nk+TCuN- w<k:O)|wYFD8u!o\"^`T+6-p{?-q@s'\\mt+.bo|g5veZ&(/O/ TP]&-F&j:\\Dm*$q<^;^ dR%<RWKTa4<8I^59k@b=Wm!_#P[e4N769&Y':c3'T<l.]\\WrIm;Dm9?X@,F\\byT^|z5veZCE&{I_OiL&k(|jcvpB7M[2LObz?&%=`DeB}2fnh<Rl}nI?1#m4/=1V.JO=>I^96uQQ/pd@SZi&Oq_<Mq':Lt*v^jd;g4Wl#y9[rYSY=]/S1^\\^-3m7P+bW:A'Qg9RDdPw][0:-X/0No=Oi#n,/*V=7";
        
        List<String> passwordsCollection = loadKeys("C:\\Users\\abeda\\Desktop\\Semester Two Material\\Cryptography & Secure Development\\Cryptography Midterm Assignment\\passwords.txt"); // replace with the provided passwords collection file
        HashSet<String> englishVocabulary = loadEnglishVocabulary("C:\\Users\\abeda\\Desktop\\Semester Two Material\\Cryptography & Secure Development\\Cryptography Midterm Assignment\\English words.txt"); // Replace with an English vocabulary list of your choice

        Iterator<String> iterator = passwordsCollection.iterator();
        
        // Loop through the common passwords in the given passwords collection
        while(iterator.hasNext()) {
        	String password = iterator.next();
        	String decryptedText = myRotor.encdec(Rotor96Crypto.DEC, password, cipherText2419103a);
        	if (isCorrectEnglish(decryptedText, englishVocabulary)) {
                System.out.println("Key: " + password);
                System.out.println("Decoded Message: " + decryptedText); 
                System.out.println(); 
        	}
        }
    }  

    // A method to check if the vocabulary is in English
    // The method returns the ratio of correct English vocabulary
    private static boolean isCorrectEnglish(String text, HashSet<String> englishVocabulary) {
        // A Scanner is usd to split the text into words
        Scanner scanner = new Scanner(text);
        List<String> vocabularyList = new ArrayList<>();
        
        while (scanner.hasNext()) {
            vocabularyList.add(scanner.next().toLowerCase());
        }
        
        double correctEnglishVocabulary = 0;
        for (String word : vocabularyList) {
            if (englishVocabulary.contains(word.toLowerCase())) {
                ++correctEnglishVocabulary;
            }
        }
        double percentage = correctEnglishVocabulary / vocabularyList.size();
        return percentage >= 0.8;
    }
    
    // A method to load a list of all the words found in the English Language
    private static HashSet<String> loadEnglishVocabulary(String filePath) {
        HashSet<String> dictionary = new HashSet<String>();
        try {
        	BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.toLowerCase().trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    // A method to load the keys found in the common passwords file
    private static List<String> loadKeys(String filePath) {
        List<String> passwords = new LinkedList<String>();
        try {
        	BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                passwords.add(line.trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passwords;
    }
}
