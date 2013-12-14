/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Bono
 */
public class Helper {
    private static Helper helper = null;
    public static int LANGUAGE_UK = 0, LANGUAGE_DUTCH = 1;
    private static String[][] translations = {
        //Words
        {"my", "mijn"},
        {"courses", "cursussen"},
        {"log in", "inloggen"},
        {"contact", "contact"},
        {"teacher", "leraar"},
        {"register", "registreren"},
        {"profile", "profiel"},
        {"log out", "uitloggen"},
        {"search", "zoeken"},
        {"for", "naar"},
        {"more", "meer"},
        {"info", "info"},
        {"back", "terug"},
        {"", ""},
    };
    
    public static Helper getInstance(){
        if(helper == null){
            helper = new Helper();
        }
        
        return helper;
    }
    
    
    public static int isInt(String string){
        int outcome = -1;
        
        try{
            outcome = Integer.parseInt(string);
        }
        catch(NumberFormatException e){
            return outcome;
        }
        
        return outcome;
    }
    
    public static boolean isTeacher(Object user){        
        if(user instanceof Teacher){
            return true;
        }
        return false;
    }
    
    public static boolean isStudent(Object user){
        if(user instanceof Student){
            return true;
        }
        return false;
    }
        
    public static String translateWord(int language, String word){
        for(String[] trans_words : translations){
            if(trans_words[0].equals(word)){
                word = trans_words[language];
            }
        }
        
        return word;
    }
}
