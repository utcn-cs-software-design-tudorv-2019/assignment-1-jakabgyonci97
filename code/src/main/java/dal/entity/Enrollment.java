package dal.entity;

public class Enrollment {
    private int id;
    private int idCourse;
    private int idStudent;
    private double finalGrade;

    public Enrollment(){
        super();
    }

    public Enrollment(int id, int idCourse, int idStudent, double finalGrade) {
        this.id = id;
        this.idCourse = idCourse;
        this.idStudent = idStudent;
        this.finalGrade = finalGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", idCourse=" + idCourse +
                ", idStudent=" + idStudent +
                ", finalGrade=" + finalGrade +
                '}';
    }
}
