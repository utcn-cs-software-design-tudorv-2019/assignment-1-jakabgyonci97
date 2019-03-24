package dal.entity;

public class StudentInformation {
    public enum scholarShip{NONE,MERIT,ACADEMIC};
    private int id;
    private int idStudent;
    private String group;
    private scholarShip scholarShipState;
    private double gradeAvrg;

    public StudentInformation(){
        super();
    }

    public StudentInformation(int id, int idStudent, String group, scholarShip scholarShipState, double gradeAvrg) {
        this.id = id;
        this.idStudent = idStudent;
        this.group = group;
        this.scholarShipState = scholarShipState;
        this.gradeAvrg = gradeAvrg;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public scholarShip getScholarShipState() {
        return scholarShipState;
    }

    public void setScholarShipState(scholarShip scholarShipState) {
        this.scholarShipState = scholarShipState;
    }

    public double getGradeAvrg() {
        return gradeAvrg;
    }

    public void setGradeAvrg(double gradeAvrg) {
        this.gradeAvrg = gradeAvrg;
    }

    @Override
    public String toString() {
        return "StudentInformation{" +
                "id=" + id +
                ", idStudent=" + idStudent +
                ", group='" + group + '\'' +
                ", scholarShipState=" + scholarShipState +
                ", gradeAvrg=" + gradeAvrg +
                '}';
    }
}
