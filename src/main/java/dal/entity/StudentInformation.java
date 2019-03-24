package dal.entity;

public class StudentInformation {
    public enum scholarShip{
        NONE{
            public int getValueInt(){return 0;}
            public String getValueString(){return "NONE";}
        },
        MERIT{
            public int getValueInt(){return 1;}
            public String getValueString(){return "MERIT";}
        },
        ACADEMIC{
            public int getValueInt(){return 2;}
            public String getValueString(){return "ACADEMIC";}
        };
        public abstract int getValueInt();
        public abstract String getValueString();
    };
    private int id;
    private int idStudent;
    private String group;
    private scholarShip scholarShipState;
    private double gradeAvrg;

    public StudentInformation(){
        super();
    }

    public StudentInformation(int id, int idStudent, String group, int scholarShipState, double gradeAvrg) {
        this.id = id;
        this.idStudent = idStudent;
        this.group = group;
        switch(scholarShipState){
            case 0: this.scholarShipState = scholarShip.NONE;break;
            case 1: this.scholarShipState = scholarShip.MERIT;break;
            case 2: this.scholarShipState = scholarShip.ACADEMIC;break;
        }
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
