package com.anicetti.mediatek.persistant.documents;



public abstract class Document implements mediatek2021.Document {
    public enum TypeDoc{
        LIVRE,
        CD,
        DVD
    }

    private int primaryKey;
    private String name;
    private String author;
    private TypeDoc typeDoc;

    public Document(String name, String author, TypeDoc typeDoc) {
        this.name = name;
        this.author = author;
        this.typeDoc = typeDoc;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public TypeDoc getTypeDoc() {
        return typeDoc;
    }

    public abstract void insert();
}
