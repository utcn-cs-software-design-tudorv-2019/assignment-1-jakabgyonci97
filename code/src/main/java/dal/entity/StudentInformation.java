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
        public static int valueOfObject(Object obj){
            if(obj instanceof String){
                String scholarShipName = (String) obj;
                if(scholarShipName.compareTo("NONE") == 0) return 0;
                if(scholarShipName.compareTo("MERIT") == 0) return 1;
                if(scholarShipName.compareTo("ACADEMIC") == 0) return 2;
            }
            if(obj instanceof Integer){
                return ((Integer)obj).intValue();
            }
            return -1;
        }
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

    public String getScholarShipState() {
        return scholarShipState.getValueString();
    }

    public int getScholarShip(){
        return scholarShipState.getValueInt();
    }

    public void setScholarShipState(int scholarShipState) {
        switch(scholarShipState){
            case 0: this.scholarShipState = scholarShip.NONE;break;
            case 1: this.scholarShipState = scholarShip.MERIT;break;
            case 2: this.scholarShipState = scholarShip.ACADEMIC;break;
        }
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

    public StudentInformation clone(){
        StudentInformation si = new StudentInformation();
        si.setId(this.id);
        si.setIdStudent(this.idStudent);
        si.setGroup(this.group);
        si.setScholarShipState(this.scholarShipState.getValueInt());
        si.setGradeAvrg(this.gradeAvrg);
        return si;
    }
}
