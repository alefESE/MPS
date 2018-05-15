/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.control;

import business.exceptions.LoginException;
import business.exceptions.NaoCadastradoException;
import business.exceptions.SenhaException;
import business.model.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.CadastroView;

/**
 *
 * @author aluno
 */
public class Main {
    
    private static CadastroView view;
    private static GerenteUsuario gerente;
    
    public static void main(String[] args){
        
        try {
            //tenta instanciar um banco de usuarios
             gerente = new GerenteUsuario();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Parece que algo deu errado ao abrir o programa."+
                    "Talvez o arquivo de usuarios esteja corrompido");
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "O arquivo de usuarios pode estar corrompido");
            System.exit(-2);
        }
        //Apenas pra ficar bonitinho
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        view = new CadastroView();
        view.setVisible(true);
    }
    
    //listeners
    public static void login(java.awt.event.ActionEvent evt) {
        Usuario usuario = null;
        try {
            //GerenteUsuario.verificaLogin(view.getLogin()); seria uma dica do login
            //GerenteUsuario.verificaSenha(view.getSenha()); seria uma dica da senha
            usuario = (Usuario) gerente.getUsuarios().get(gerente.get(view.getLogin()));
            
            if(!view.getSenha().equals(usuario.getSenha()))
                view.mostraErro("Senha incorreta");
        else 
            view.mostraMensagem("Logado como:"+usuario.getLogin());
        } catch (NaoCadastradoException ex) {
            view.mostraErro(ex.getMessage());
        } 
    }
    
    public static void cadastra(java.awt.event.ActionEvent evt) {
        if(!gerente.existe(view.getLogin())) {
            try {
                GerenteUsuario.verificaLogin(view.getLogin());
                GerenteUsuario.verificaSenha(view.getSenha());
                
                Usuario usuario = new Usuario(view.getLogin(), view.getSenha());
                gerente.persiste(usuario);
                
                view.mostraMensagem("Usuairo "+usuario.getLogin()+" cadastrado com sucesso!");
            } catch (SenhaException ex) {
                view.mostraErro(ex.getMessage());
            } catch (LoginException ex) {
                view.mostraErro(ex.getMessage());
            }
        }
        else
            view.mostraErro("Usuario ja cadastrado");
    }
    
    public static void deleta(java.awt.event.ActionEvent evt) {
        if(gerente.existe(view.getLogin())){
            try {
                int i = gerente.get(view.getLogin());
                Usuario usuario = (Usuario)gerente.getUsuarios().get(i);
                
                if(!view.getSenha().equals(usuario.getSenha()))
                    view.mostraErro("Senha incorreta");
                else{
                    gerente.deleta(i);
                    
                    view.mostraMensagem("Usuario "+usuario.getLogin()+" deletado com sucesso!");
                }
            } catch (NaoCadastradoException ex) {
                view.mostraErro(ex.getMessage());
            } 
        }
    }
    
    public static void fechando(java.awt.event.WindowEvent evt) {
        try {
            gerente.commit();
        } catch (IOException ex) {
            view.mostraErro("Erro ao salvar usuarios!");
        }
    }
}