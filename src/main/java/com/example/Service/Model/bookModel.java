package com.example.Service.Model;

public class bookModel {
    private String id;
    private String Name;
    private String Author;
    private String Category;
    private int Copies;
 
    public bookModel(){
        
    }
    public bookModel(String Name, String Author, String Category, int Copies)
    {
        this.Name = Name;
        this.Author = Author;
        this.Category = Category;
        this.Copies = Copies;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getCopies() {
        return Copies;
    }

    public void setCopies(int copies) {
        Copies = copies;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

}
