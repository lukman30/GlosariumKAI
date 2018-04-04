package luk.soft.glosarium.adapterabe;

public class SuggestGetSet {

    String roll_no,name;
    public SuggestGetSet(String roll_no, String name){
        this.setRoll_no(roll_no);
        this.setName(name);
    }
    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String id) {
        this.roll_no = roll_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}