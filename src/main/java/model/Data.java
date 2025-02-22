package model;

public class Data {
    private int id ;
    private String filename;
    private String path;
    private String email;
    public Data(int id ,String filename,String path,String email){
        this.id=id;
        this.filename=filename;
        this.path=path;
        this.email=email;

    }

    public Data(int id, String filename, String path) {
        this.id = id;
        this.filename = filename;
        this.path = path;
    }

    public void setId(int id){
        this.id=id;
    }
    public int getId() {
        return id;
    }
    public void setFilename(String filename){
        this.filename=filename;
    }
    public String getFileName(){
        return  filename;
    }
    public void setPath(String path){
        this.path=path;
    }
    public String getPath() {
        return path;
    }
    public void setEmail(){
        this.email=email;

    }
    public String getEmail(){
        return email;
    }
}
