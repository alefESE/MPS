/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.exceptions;

/**
 *
 * @author aluno
 */
public class SenhaException extends Exception{
    public static long serialVersionUID = 1149241039409861914L;
    
    public SenhaException(String msg){
        super(msg);
    }
    
    public SenhaException(String msg, Throwable causa) {
        super(msg, causa);
    }
}
