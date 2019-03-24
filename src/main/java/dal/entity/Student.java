package dal.entity;

public class Student {
    private int id;
    private int idUser;

    public Student(){
        super();
    }

    public Student(int id, int idUser) {
        this.id = id;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", idUser=" + idUser +
                '}';
    }
}
