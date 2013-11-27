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
}
