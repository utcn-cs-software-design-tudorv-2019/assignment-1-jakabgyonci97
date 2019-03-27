package bll;

import bll.model.StudentProfile;
import dal.dao.*;
import dal.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class AdminBLL {
    private static final int ID_LOWEST_LIMIT = 1000;
    private static final int ID_HIGHEST_LIMIT = 600000;
    private static final int FIRSTNAME_LIMIT = 20;
    private static final int LASTNAME_LIMIT = 20;
    private static final int GROUP_LIMIT = 6;
    private static final double GRADE_LOWEST_LIMIT = 1.0;
    private static final double GRADE_HIGHEST_LIMIT = 10.0;
    private static final String USER_ERROR = "Cannot create new user!";
    private static final String STUDENT_INFO_ERROR = "This student already has student information section!";
    private static final String PERSONAL_INFO_ERROR = "This student already has personal information section";
    private static final String DATABASE_ERROR = "Database error!Sorry Boss!";

    /**create/view/update/delete student profiles
     *observation: creating student:   1.creating a full new student: user, student
     *                                 2.creating student information for existing student*/
    public String createStudent(String idStudentText,String firstNameText,String lastNameText,String groupText,String averageText,String scholarShipText){
        ValidatorResponse v1 = Validator.validateIntegerNumber(idStudentText,ID_LOWEST_LIMIT,ID_HIGHEST_LIMIT,Validator.CheckType.CHECK_ALL);
        if(!v1.isValid()) return v1.getMessage();
        Student student = (new StudentDAO()).findByID(Integer.parseInt(idStudentText));
        if(student == null){
            System.out.println("CREATING NEW STUDENT");
            ValidatorResponse v2 = Validator.validateFirstName(firstNameText,FIRSTNAME_LIMIT,Validator.CheckType.CHECK_ALL);
            if(!v2.isValid()) return v2.getMessage();
            ValidatorResponse v3 = Validator.validateLastName(lastNameText,LASTNAME_LIMIT,Validator.CheckType.CHECK_ALL);
            if(!v3.isValid()) return v3.getMessage();

            User user = new User(0,firstNameText+lastNameText,idStudentText,User.UserType.STUDENT.getValue());
            boolean userInsertResult = (new UserDAO()).insert(user);
            if(!userInsertResult) return USER_ERROR;

            user = (new UserDAO()).userLogin(firstNameText+lastNameText,idStudentText);
            if(user == null) return DATABASE_ERROR;

            student = new Student(Integer.parseInt(idStudentText),user.getId());
            boolean studentInsertResult = (new StudentDAO()).insert(student);
            if(!studentInsertResult) return DATABASE_ERROR;

            PersonalInformation pi = (new PiDAO()).findByIdStudent(student.getId());
            if(pi != null) return PERSONAL_INFO_ERROR;

            pi = new PersonalInformation(0,student.getId(),firstNameText,lastNameText,"","");
            boolean insertPiResult = (new PiDAO()).insert(pi);
            if(!insertPiResult) return DATABASE_ERROR;

            return null;
        }
        System.out.println("CREATING NEW STUDENT INFO");
        ValidatorResponse v4 = Validator.validatePersonalNumericalCode(groupText,GROUP_LIMIT,Validator.CheckType.CHECK_ALL);
        if(!v4.isValid()) return v4.getMessage();
        ValidatorResponse v5 = Validator.validateDoubleNumber(averageText,GRADE_LOWEST_LIMIT,GRADE_HIGHEST_LIMIT,Validator.CheckType.CHECK_ALL);
        if(!v5.isValid()) return v5.getMessage();
        ValidatorResponse v6 = Validator.validateScholarShipState(scholarShipText,Validator.CheckType.CHECK_ALL);
        if(!v6.isValid()) return v6.getMessage();

        StudentInformation si = (new SiDAO()).findByIdStudent(student.getId());
        if(si != null) return STUDENT_INFO_ERROR;

        si = new StudentInformation(0,student.getId(),groupText,StudentInformation.scholarShip.valueOfObject(scholarShipText), Double.parseDouble(averageText));
        boolean insertSiResult = (new SiDAO()).insert(si);

        if(!insertSiResult) return DATABASE_ERROR;
        return null;
    }
    public ObservableList<StudentProfile> viewStudents(){
        ObservableList<StudentProfile> studentProfiles = FXCollections.observableArrayList();
        List<Student> students = (new StudentDAO()).findAll();
        StudentInformation si;
        PersonalInformation pi;
        for(Student student: students){
            si = (new SiDAO()).findByIdStudent(student.getId());
            pi = (new PiDAO()).findByIdStudent(student.getId());
            StudentProfile sp = new StudentProfile();
            sp.setIdStudent(student.getId());
            if(pi != null){
                sp.setFirstName(pi.getFirstName());
                sp.setLastName(pi.getLastName());
                if(si != null){
                    sp.setGroup(si.getGroup());
                    sp.setAverage(si.getGradeAvrg());
                    sp.setScholarShipState(si.getScholarShipState());
                }
            }
            studentProfiles.add(sp);
        }
        return studentProfiles;
    }
    /**observation: neither admin can update student id -> primary key of table*/
    public String updateStudent(StudentProfile student,String groupText, String averageText, String scholarShipText){
        ValidatorResponse v3 = Validator.validatePersonalNumericalCode(groupText,GROUP_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        if(!v3.isValid()) return v3.getMessage();
        ValidatorResponse v4 = Validator.validateDoubleNumber(averageText,GRADE_LOWEST_LIMIT,GRADE_HIGHEST_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        if(!v4.isValid()) return v4.getMessage();
        ValidatorResponse v5 = Validator.validateScholarShipState(scholarShipText,Validator.CheckType.NO_NULL_CHECK);
        if(!v5.isValid()) return v5.getMessage();

        StudentInformation si = (new SiDAO()).findByIdStudent(student.getIdStudent());
        if(si == null) return "You cannot update something that does not exist ... supped!";

        if(groupText != null && !groupText.isEmpty()) si.setGroup(groupText);
        if(scholarShipText != null && !scholarShipText.isEmpty()) si.setScholarShipState(StudentInformation.scholarShip.valueOfObject(scholarShipText));
        if(averageText != null && !averageText.isEmpty()) si.setGradeAvrg(Double.parseDouble(averageText));

        boolean siUpdateResult = (new SiDAO()).update(si);

        if(!siUpdateResult) return DATABASE_ERROR;
        return null;
    }
    /**observation: delete operation refers only to student information, NOT whole student profile*/
    public String deleteStudent(StudentProfile student){
        StudentInformation si = (new SiDAO()).findByIdStudent(student.getIdStudent());
        if(si == null) return "You cannot delete something that does not exist...supped!";
        boolean deleteResult = (new SiDAO()).delete(si);
        if(!deleteResult) return DATABASE_ERROR;
        return null;
    }


    /**generate reports based on student's activity for a time period*/
    public StudentProfile findStudent(String idStudent){
        ValidatorResponse vr = Validator.validateIntegerNumber(idStudent,ID_LOWEST_LIMIT,ID_HIGHEST_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        if(!vr.isValid()) return null;
        Student student = (new StudentDAO()).findByID(Integer.parseInt(idStudent));
        if(student == null) return null;

        StudentInformation si = (new SiDAO()).findByIdStudent(student.getId());
        PersonalInformation pi = (new PiDAO()).findByIdStudent(student.getId());
        if(si == null) return null;
        if(pi == null) return null;

        StudentProfile sp = new StudentProfile(student.getId(),pi.getFirstName(),pi.getLastName(),si.getGroup(),si.getScholarShipState(),si.getGradeAvrg());
        return sp;
    }
    public ObservableList<String> filterActivities(StudentProfile student, LocalDate startDate, LocalDate endDate){
        if(student == null) return null;
        if(startDate.isAfter(endDate)) return null;
        List<StudentActivity> studentActivities = (new StudentActivityDAO()).findByIdStudent(student.getIdStudent());
        ObservableList<String> filteredActivities = FXCollections.observableArrayList();
        for(StudentActivity itr: studentActivities){
            LocalDate activityDate = itr.getActivityDate().toLocalDate();
            if(activityDate.isAfter(startDate) && activityDate.isBefore(endDate))
                filteredActivities.add(itr.toString());
        }
        return filteredActivities;
    }
    public ObservableList<String> viewActivities(StudentProfile student){
        if(student == null) {
            List<StudentActivity> studentActivities = (new StudentActivityDAO()).findAll();
            ObservableList<String> filteredActivities = FXCollections.observableArrayList();
            for(StudentActivity itr: studentActivities){
                filteredActivities.add(itr.toString());
            }
            return filteredActivities;
        }
        List<StudentActivity> studentActivities = (new StudentActivityDAO()).findByIdStudent(student.getIdStudent());
        ObservableList<String> filteredActivities = FXCollections.observableArrayList();
        for(StudentActivity itr: studentActivities){
            filteredActivities.add(itr.toString());
        }
        return filteredActivities;
    }






}
