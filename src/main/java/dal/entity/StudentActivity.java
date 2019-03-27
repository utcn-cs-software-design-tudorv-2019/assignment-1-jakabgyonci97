package dal.entity;

import java.sql.Date;

public class StudentActivity {
    private int id;
    private int idStudent;
    private Date activityDate;
    private String activityType;
    private String description;

    public StudentActivity(){
        super();
    }

    public StudentActivity(int id, int idStudent, Date activityDate, String activityType, String description) {
        this.id = id;
        this.idStudent = idStudent;
        this.activityDate = activityDate;
        if(isActivityValid(activityType))
            this.activityType = activityType;
        this.description = description;
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

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static boolean isActivityValid(String activity){
        switch(activity){
            case "CREATE":
            case "UPDATE":
            case "DELETE":
            case "ENROLLMENT": return true;
        }
        return false;
    }

    public String toString(){
        return idStudent + "\t" + activityDate + "\t" + activityType + "\t" + description;
    }
}
