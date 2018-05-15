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
import infra.Persistencia;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class GerenteUsuario {
    
    static List<Usuario> usuarios;
    
    public GerenteUsuario() throws IOException, ClassNotFoundException {
        usuarios = (ArrayList<Usuario>) Persistencia.carrega("usuarios");
        if(usuarios == null) //o arquivo estava vazio
            usuarios = new ArrayList<Usuario>();
    }
    
    public static void verificaLogin(String login) throws LoginException
    {
        if(login.isEmpty())
            throw new LoginException("O login nao pode ser vazio");
        if(login.length() > 15)
            throw new LoginException("O login nao pode conter mais que 15 caracteres");
       // if(login.contains("\\d+"))
        if(login.matches(".*\\d.*"))
            throw new LoginException("O login nao pode conter numeros");
    }
    
    public static void verificaSenha(String senha) throws SenhaException
    {
        if(senha.isEmpty())
            throw new SenhaException("A senha nao pode ser vazia");
        if(senha.length() > 18 || senha.length() < 6)
            throw new SenhaException("A senha deve ter entre 6 e 18 caracteres");
        if(senha.matches("[0-9]+") || senha.matches("[a-zA-Z]+"))
            throw new SenhaException("A senha deve conter letras e numeros");
        if(!senha.matches(".*\\d.*\\d.*"))
            throw new SenhaException("A senha deve conter ao menos dois numeros");
    }
    public List getUsuarios() {
        return usuarios;
    }
    public int get(String nome) throws NaoCadastradoException{
        Usuario usuario = null;
        for(int i = 0; i < usuarios.size(); i++){
            usuario = usuarios.get(i);
            if(usuario.getLogin().equals(nome))
                return i;
        }
        throw new NaoCadastradoException("Usuario nao cadastrado!");
    }
    
    public Usuario deleta(int idx) {
        return usuarios.remove(idx);
    }
    
    public boolean existe(String nome) {
        for(Usuario usuario: usuarios)
            if(usuario.getLogin().equals(nome))
                return true;
        return false;
    }
    
    public void persiste(Usuario usuario) {
        usuarios.add(usuario);
    }
    
    public static void commit() throws IOException {
        Persistencia.persiste(usuarios, "usuarios");
    }
}
