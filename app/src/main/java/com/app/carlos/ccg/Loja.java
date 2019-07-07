package com.app.carlos.ccg;

import com.google.firebase.database.Exclude;

public class Loja {
     String id_loja;
     String nomeLoja;
     String box;
     String telefoneWhats;
     String telefoneFixo;
     String pontoReferencia;
     String instagram;
     String categoriaLoja1;
     String categoriaLoja2;
     String descricaoLoja;
     String nomeCategoriaLoja, imagemLink;


    public String getNomeCategoriaLoja() {
        return nomeCategoriaLoja;
    }

    public void setNomeCategoriaLoja(String nomeCategoriaLoja) {
        this.nomeCategoriaLoja = nomeCategoriaLoja;
    }

    public String getImagemLink() {
        return imagemLink;
    }

    public void setImagemLink(String imagemLink) {
        this.imagemLink = imagemLink;
    }

    public String getDescricaoLoja() {
        return descricaoLoja;
    }
    public void setDescricaoLoja(String descricaoLoja) {
        this.descricaoLoja = descricaoLoja;
    }

    public String getCategoriaLoja1() {
        return categoriaLoja1;
    }

    public void setCategoriaLoja1(String categoriaLoja1) {
        this.categoriaLoja1 = categoriaLoja1;
    }

    public String getCategoriaLoja2() {
        return categoriaLoja2;
    }

    public void setCategoriaLoja2(String categoriaLoja2) {
        this.categoriaLoja2 = categoriaLoja2;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getTelefoneWhats() {
        return telefoneWhats;
    }

    public void setTelefoneWhats(String telefoneWhats) {
        this.telefoneWhats = telefoneWhats;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
    @Exclude
    public String getId_loja() {
        return id_loja;
    }
    @Exclude
    public void setId_loja(String id_loja) {
        this.id_loja = id_loja;
    }

    public Loja(){

    }
}
