import java.util.ArrayList;

public class Person {
    private String f_name;
    private String l_name;
    private ArrayList<String> p_number ;
    private String email ;

    Person(String f_name,String l_name,ArrayList<String> p_number,String email)
    {
        this.f_name=f_name;
        this.l_name=l_name;
        this.p_number=p_number;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getP_number() {
        return p_number;
    }

    public void setP_number(ArrayList<String> p_number) {
        this.p_number = p_number;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }
}