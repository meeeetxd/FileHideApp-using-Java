package model;

public class Data {
    private int id;
    private String fileName;
    private String path;
    private String email;

    public Data(int id, String fileName, String path){
        this.id = id;
        this.fileName = fileName;
        this.path = path;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getFileName(){
        return fileName;
    }
    public void setFileName(){
        this.fileName = fileName;
    }
    public String getPath(){
        return path;
    }
    public void setPath(){
        this.path = path;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(){
        this.email = email;
    }

    public Data(int id,String fileName,String path, String email){
        this.id = id;
        this.fileName = fileName;
        this.path = path;
        this.email = email;
    }
}
