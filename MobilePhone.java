import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

public class MobilePhone {

    @DomainConstraint(type = "String", mutable = false, optional = false, length = 100)
    private String manName;

    @DomainConstraint(type = "String", mutable = false, optional = false, length = 50)
    private String model;

    @DomainConstraint(type = "char", mutable = false, optional = false, length = 1)
    private char color;

    @DomainConstraint(type = "int", mutable = false, optional = false, length = 4, min = 1900)
    private int year;

    @DomainConstraint(type = "boolean", mutable = true, optional = false)
    private boolean guranteed;

    public MobilePhone(@AttrRef("manName") String manName){
        this.manName = manName;
    }
{


    if (!validateManName(manName)){
        throw new NotPossibleException("MobilePhone.init: invalid manName: " + manName);
    }

    if (!validateModel(model)){
        throw new NotPossibleException("MobilePhone.init: invalid model: " + model);
    }

    if (!validateColor(color)){
        throw new NotPossibleException("MobilePhone.init: invalid color: " + color);
    }

    if (!validateYear(year)){
        throw new NotPossibleException("MobilePhone.init: invalid year: " + year);
    }

    this.manName = manName;
    this.model = model;
    this.color = color;
    this.year = year;
}

    @DOpt(type = OptType.Observer)
    @AttrRef("manName")
    public String getManName() {
        return manName;
    }

    @DOpt(type = OptType.Observer)
    @AttrRef("model")
    public String getModel() {
        return model;
    }

    @DOpt(type = OptType.Observer)
    @AttrRef("color")
    public char getColor() {
        return color;
    }

    @DOpt(type = OptType.Observer)
    @AttrRef("year")
    public int getYear() {
        return year;
    }

    @DOpt(type = OptType.Observer)
    @AttrRef("guranteed")
    public boolean getGuranteed() {
        return guranteed;
    }

    @DOpt(type = OptType.Mutator)
    @AttrRef("guranteed")
    public boolean setGuranteed(boolean guranteed) {
        this.guranteed = guranteed;
        return true;
    }

    @Override
    public String toString() {
        return "MobilePhone(" + manName + "," + model + "," + color + "," + year + ")";
    }

    @Override
    public boolean equals(Object o){
        if ( o == null || !(o instanceof MobilePhone) ){
            return false;
    }else{
        MobilePhone other = (MobilePhone) o;
        return manName.equals(other.getManName()) && 
        model.equals(other.getModel()) && 
        color == other.getColor() && 
        year == other.getYear();
    }
}

    public boolean repOK() {
        return validateManName(manName) && validateModel(model) && validateColor(color) && validateYear(year);
    }

    private boolean validateManName(String manName) {
        return manName != null && manName.length() <= 100;
    }

    private boolean validateModel(String model) {
        return model != null && model.length() <= 50;
    }

    private boolean validateColor(char color){
        char[] validcolors = {'R', 'O', 'Y', 'G', 'B','P'};
        for (char vc : validcolors){
            if (color == vc){
                return true;
            }
        }
        return false;
    }

    private boolean validateYear(int year){
        return year >= 1970;
    }
}

// public class GreetingConversationApp
