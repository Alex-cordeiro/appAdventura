package com.unicuritiba.adventura.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.unicuritiba.adventura.helper.ConfiguracaoFirebase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Usuario implements Serializable{
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String caminhoFoto;

    public Usuario(){

    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference usuariosRef = firebaseRef.child("usuarios").child( getId() );
        usuariosRef.setValue( this );
    }

    public void atualizarQtdPostagem(){

        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference usuariosRef = firebaseRef
                .child("usuarios")
                .child( getId() );

        HashMap<String, Object> dados = new HashMap<>();

        usuariosRef.updateChildren( dados );

    }

    public void atualizar(){

        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();

        Map objeto = new HashMap();
        objeto.put("/usuarios/" + getId() + "/nome", getNome() );
        objeto.put("/usuarios/" + getId() + "/caminhoFoto", getCaminhoFoto() );

        firebaseRef.updateChildren( objeto );

    }

    public Map<String, Object> converterParaMap(){

        HashMap<String, Object> usuarioMap = new HashMap<>();
        usuarioMap.put("email", getEmail() );
        usuarioMap.put("nome", getNome() );
        usuarioMap.put("id", getId() );
        usuarioMap.put("caminhoFoto", getCaminhoFoto() );

        return usuarioMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
