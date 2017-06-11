package kr.bookstorage.push;

/**
 * Created by ksb on 2017. 6. 11..
 */
public class PushObject {
    private String name;
    private String id;

    public void setName(String name) {
        this.name = name;

    }

    public void setId(String id) {

        this.id = id;
    }
    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }
}
