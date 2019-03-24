package dal.entity;

import java.sql.Date;

public class Course {
    private int id;
    private String name;
    private String session;
    private Date examDate;
    private String enrollmentKey;

    public Course(){
        super();
    }

    public Course(int id, String name, String session, Date examDate, String enrollmentKey) {
        this.id = id;
        this.name = name;
        this.session = session;
        this.examDate = examDate;
        this.enrollmentKey = enrollmentKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getEnrollmentKey() {
        return enrollmentKey;
    }

    public void setEnrollmentKey(String enrollmentKey) {
        this.enrollmentKey = enrollmentKey;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", session='" + session + '\'' +
                ", examDate=" + examDate +
                ", enrollmentKey='" + enrollmentKey + '\'' +
                '}';
    }
}
