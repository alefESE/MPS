/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.exceptions;

/**
 *
 * @author alef_
 */
public class LoginNumerosException extends LoginException{
    
    public LoginNumerosException(String msg) {
        super(msg);
    }
    
    public LoginNumerosException(String msg, Throwable causa){
        super(msg, causa);
    }
}
