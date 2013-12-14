/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Bono
 */
public class Helper {
    private static Helper helper = null;
    public static int LANGUAGE_UK = 0, LANGUAGE_DUTCH = 1;
    private static String[][] translations = {
        //Words
        {"My Courses", "Mijn Cursussen"},
        {"Courses", "Cursussen"},
        {"Log In", "Inloggen"},
        {"Contact", "Contact"},
        {"Teacher", "Leraar"},
        {"Register", "Registreren"},
        {"Profile", "Profiel"},
        {"Log Out", "Uitloggen"},
        {"Search", "Zoeken"},
        {"For", "Naar"},
        {"More", "Meer"},
        {"Info", "Info"},
        {"Back", "Terug"},
        {"Search for courses", "Zoeken naar cursussen"},
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
        
    public static int getLanguage(HttpSession session){
        int language = 0;
        
        if(session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            language =  user.getLanguage();
        }
        else{ //Haal language op uit geselecteerde language in header - TODO
            
        }
        
        return language;
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
