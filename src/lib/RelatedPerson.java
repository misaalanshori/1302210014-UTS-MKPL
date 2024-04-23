package lib;

public class RelatedPerson {
    final private String name;
    final private String idNumber;
    public RelatedPerson(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }
    public String getName() {
        return name;
    }
    public String getIdNumber() {
        return idNumber;
    }
    
}

