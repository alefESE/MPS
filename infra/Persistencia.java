/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra;

import business.model.Usuario;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public abstract class Persistencia {
    public static Object carrega(String nome) throws IOException, ClassNotFoundException {
        File arquivo = new File(nome);
        if(!arquivo.exists()) {
            arquivo.createNewFile();
            return null;
        }
        if(arquivo.length() == 0) //o arquivo esta vazio
            return null;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo));
        Object objeto = in.readObject();
        in.close();
        return objeto;
    }
    public static void persiste(Object objeto, String nome) throws IOException, FileNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nome));
        out.writeObject(objeto);
        out.close();
    }
}
