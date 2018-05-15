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
public class NaoCadastradoException extends Exception {
    public static final long serialVersionUID = 1149241039409861914L;
    
    public NaoCadastradoException(String msg) {
        super(msg);
    }
    
    public NaoCadastradoException(String msg, Throwable causa){
        super(msg, causa);
    }
}
