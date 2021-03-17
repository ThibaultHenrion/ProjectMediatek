package com.anicetti.mediatek.persistant.documents;

public class Cd extends Document{
    public enum GenreCd{
        ROCK,
        ORCHESTRAL,
        PODCAST,
        POP
    }

    private int id;
    private GenreCd genreCd;


    @Override
    public Object[] data() {
        return new Object[0];
    }

    public Cd(String name, String author, TypeDoc typeDoc, GenreCd genreCd) {
        super(name, author, typeDoc);
        this.genreCd = genreCd;
    }

    @Override
    public String insert() {
        super.insert();

    }
}
