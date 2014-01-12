/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.servlet.http.HttpServletRequest;
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
        {"Go to course", "Ga naar cursus"},
        {"My Grades", "Mijn Cijfers"},
        {"Student Panel", "Student Paneel"},
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
        {"More info", "Meer info"},
        {"Back", "Terug"},
        {"Cancel", "Annuleren"},
        {"Search for courses", "Zoeken naar cursussen"},
        {"Edit user information", "Gebruikersinformatie aanpassen"},
        {"You have successfully edited your information", "U heeft succesvol uw informatie aangepast"},
        {"Gender", "Geslacht"},
        {"Male", "Man"},
        {"Female", "Vrouw"},
        {"Firstname", "Voornaam"},
        {"Surname", "Achternaam"},
        {"City", "Stad"},
        {"Country", "Land"},
        {"Zipcode", "Postcode"},
        {"Address", "Adres"},
        {"Language", "Taal"},
        {"Select language", "Selecteer taal"},
        {"E-mail", "E-mail"},
        {"Password", "Wachtwoord"},
        {"Change your password", "Verander uw wachtwoord"},
        {"New password", "Nieuw wachtwoord"},
        {"Confirm password", "Bevestig wachtwoord"},
        {"Save", "Opslaan"},
        {"Required", "Verplicht"},
        {"Optional", "Optioneel"},
        {"Name", "Naam"},
        {"Subject", "Onderwerp"},
        {"Message", "Bericht"},
        {"Contact information", "Contact informatie"},
        {"Phone", "Telefoon"},
        {"Send", "Versturen"},
        {"Log in", "Inloggen"},
        {"You have been successfully registered", "U bent succesvol geregistreerd"},
        {"Register", "Registreren"},
        {"Welcome to: ", "Welkom bij: "},
        {"Livestreams", "Livestreams"},
        {"Video chat", "Video chat"},};

    private static final int[] allowed_languages = {0, 1};

    public static Helper getInstance() {
        if (helper == null) {
            helper = new Helper();
        }

        return helper;
    }

    public static int isInt(String string) {
        int outcome = -1;

        try {
            outcome = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return outcome;
        }

        return outcome;
    }

    public static boolean isTeacher(Object user) {
        if (user instanceof Teacher) {
            return true;
        }
        return false;
    }

    public static boolean isAdmin(Object user) {
        if (user instanceof Admin) {
            return true;
        }
        return false;
    }

    public static boolean isStudent(Object user) {
        if (user instanceof Student) {
            return true;
        }
        return false;
    }

    public static boolean allowedLanguage(int language) {
        for (int allowed_language : allowed_languages) {
            if (language == allowed_language) {
                return true;
            }
        }
        return false;
    }

    public static void setLanguage(int language, HttpServletRequest request) {
        boolean existing_language = allowedLanguage(language);

        HttpSession session = request.getSession();
        session.setAttribute("language", existing_language ? language : 0);
    }

    public static int getLanguage(HttpSession session) {
        int language = 0;

        if (session.getAttribute("language") != null) {
            Object session_language = session.getAttribute("language");

            if (session_language instanceof Integer) {
                language = (Integer) session_language;
            } else if (session_language instanceof String) {
                language = Helper.isInt((String) session_language);
            }
        }
        System.out.println(language);
        return language;
    }

    public static String translateWord(int language, String word) {
        for (String[] trans_words : translations) {
            if (trans_words[0].equals(word)) {
                word = trans_words[language];
            }
        }

        return word;
    }
}
