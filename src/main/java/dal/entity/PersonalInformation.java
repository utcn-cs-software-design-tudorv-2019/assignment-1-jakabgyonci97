package dal.entity;

public class PersonalInformation {
    private int id;
    private int idStudent;
    private String firstName;
    private String lastName;
    private String icn;
    private String pnc;

    public PersonalInformation(){
        super();
    }

    public PersonalInformation(int id, int idStudent, String firstName, String lastName, String icn, String pnc) {
        this.id = id;
        this.idStudent = idStudent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.icn = icn;
        this.pnc = pnc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIcn() {
        return icn;
    }

    public void setIcn(String icn) {
        this.icn = icn;
    }

    public String getPnc() {
        return pnc;
    }

    public void setPnc(String pnc) {
        this.pnc = pnc;
    }

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "id=" + id +
                ", idStudent=" + idStudent +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", icn='" + icn + '\'' +
                ", pnc='" + pnc + '\'' +
                '}';
    }
}
