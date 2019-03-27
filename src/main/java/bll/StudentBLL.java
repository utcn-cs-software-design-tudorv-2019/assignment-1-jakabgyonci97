package bll;

import bll.model.EnrolledCourse;
import dal.dao.*;
import dal.entity.*;
import javafx.collections.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class StudentBLL {
    private static final int FIRSTNAME_LIMIT = 20;
    private static final int LASTNAME_LIMIT = 20;
    private static final int ICN_LIMIT = 8;
    private static final int PNC_LIMIT = 13;
    private static final int GROUP_LIMIT = 6;
    private static final double GRADE_LOWEST_LIMIT = 1.0;
    private static final double GRADE_HIGHEST_LIMIT = 10.0;
    private static final int ADDRESS_LIMIT = 20;
    private static final int PHONE_NUM_LIMIT = 10;
    private static final int ENROLLMENT_KEY_LIMIT = 10;
    private static final String ERROR_TEXT = "Database Error!Sorry!";
    private static final String ENROLLMENT_ERROR = "Enrollment key was invalid!";
    private static final String NULL_ERROR = "You have nothing to delete!";

    /**create/view/update student's personal information*/
    public String createPersonalInfo(Student student,String firstName,String lastName,String icn,String pnc){
        ValidatorResponse v1 = Validator.validateFirstName(firstName,FIRSTNAME_LIMIT, Validator.CheckType.CHECK_ALL);
        ValidatorResponse v2 = Validator.validateLastName(lastName,LASTNAME_LIMIT, Validator.CheckType.CHECK_ALL);
        ValidatorResponse v3 = Validator.validateIdentityCardNumber(icn,ICN_LIMIT, Validator.CheckType.CHECK_ALL);
        ValidatorResponse v4 = Validator.validatePersonalNumericalCode(pnc,PNC_LIMIT, Validator.CheckType.CHECK_ALL);
        if(!v1.isValid()) return v1.getMessage();
        if(!v2.isValid()) return v2.getMessage();
        if(!v3.isValid()) return v3.getMessage();
        if(!v4.isValid()) return v4.getMessage();

        PersonalInformation pi = new PersonalInformation(0,student.getId(),firstName,lastName,icn,pnc);
        boolean insertResult = (new PiDAO()).insert(pi);
        if(!insertResult) return ERROR_TEXT;
        logStudentActivity(student.getId(),"CREATE","Create personal information section");
        return null;
    }
    public PersonalInformation viewPersonalInfo(Student student){
        PiDAO pidao = new PiDAO();
        PersonalInformation pi = pidao.findByIdStudent(student.getId());
        return pi;
    }
    public String updatePersonalInfo(PersonalInformation piOld,String firstName,String lastName,String icn,String pnc){
        if(piOld == null) return NULL_ERROR;
        ValidatorResponse v1 = Validator.validateFirstName(firstName,FIRSTNAME_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v2 = Validator.validateLastName(lastName,LASTNAME_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v3 = Validator.validateIdentityCardNumber(icn,ICN_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v4 = Validator.validatePersonalNumericalCode(pnc,PNC_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        if(!v1.isValid()) return v1.getMessage();
        if(!v2.isValid()) return v2.getMessage();
        if(!v3.isValid()) return v3.getMessage();
        if(!v4.isValid()) return v4.getMessage();

        PersonalInformation piNew = piOld.clone();
        if(firstName != null && !firstName.isEmpty()) piNew.setFirstName(firstName);
        if(lastName != null && !lastName.isEmpty()) piNew.setLastName(lastName);
        if(icn != null && !icn.isEmpty()) piNew.setIcn(icn);
        if(pnc != null && !pnc.isEmpty()) piNew.setPnc(pnc);
        System.out.println("New: "+piNew);
        System.out.println("Old: "+piOld);

        boolean updateResult = (new PiDAO()).update(piNew);
        if(!updateResult) return ERROR_TEXT;
        logStudentActivity(piOld.getIdStudent(),"UPDATE","Update personal information section");
        return null;
    }

    /**create/view/update/delete student's student information*/
    public String createStudentInfo(Student student,String group,String scholarShip,String grades){
        ValidatorResponse v1 = Validator.validatePersonalNumericalCode(group,GROUP_LIMIT,Validator.CheckType.CHECK_ALL);
        ValidatorResponse v2 = Validator.validateScholarShipState(scholarShip,Validator.CheckType.CHECK_ALL);
        ValidatorResponse v3 = Validator.validateDoubleNumber(grades,GRADE_LOWEST_LIMIT,GRADE_HIGHEST_LIMIT,Validator.CheckType.CHECK_ALL);
        if(!v1.isValid()) return v1.getMessage();
        if(!v2.isValid()) return v2.getMessage();
        if(!v3.isValid()) return v3.getMessage();

        int sch = StudentInformation.scholarShip.valueOfObject(scholarShip);
        StudentInformation si = new StudentInformation(0,student.getId(),group,sch,Double.parseDouble(grades));
        boolean insertResult = (new SiDAO()).insert(si);
        if(!insertResult) return ERROR_TEXT;
        logStudentActivity(student.getId(),"CREATE","Create student information section");
        return null;
    }
    public StudentInformation viewStudentInfo(Student student){
        SiDAO siDAO = new SiDAO();
        StudentInformation si = siDAO.findByIdStudent(student.getId());
        return si;
    }
    public String updateStudentInfo(StudentInformation siOld,String group,String scholarShip,String grades){
        if(siOld == null) return NULL_ERROR;
        ValidatorResponse v1 = Validator.validatePersonalNumericalCode(group,GROUP_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v2 = Validator.validateScholarShipState(scholarShip,Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v3 = Validator.validateDoubleNumber(grades,GRADE_LOWEST_LIMIT,GRADE_HIGHEST_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        if(!v1.isValid()) return v1.getMessage();
        if(!v2.isValid()) return v2.getMessage();
        if(!v3.isValid()) return v3.getMessage();

        StudentInformation siNew = siOld.clone();
        if(group != null && !group.isEmpty()) siNew.setGroup(group);
        if(scholarShip != null && !scholarShip.isEmpty()){
            int sch = StudentInformation.scholarShip.valueOfObject(scholarShip);
            siNew.setScholarShipState(sch);
        }
        if(grades != null && !grades.isEmpty()) siNew.setGradeAvrg(Double.parseDouble(grades));

        boolean updateResult = (new SiDAO()).update(siNew);
        if(!updateResult) return ERROR_TEXT;
        logStudentActivity(siOld.getIdStudent(),"UPDATE","Update student information section");
        return null;
    }
    public String deleteStudentInfo(StudentInformation si){
        if(si == null) return NULL_ERROR;
        boolean deleteResult = (new SiDAO()).delete(si);
        if(!deleteResult) return ERROR_TEXT;
        logStudentActivity(si.getIdStudent(),"DELETE","Delete student information section");
        return null;
    }

    /**create/view/update/delete student's student information*/
    public String createContactInfo(Student student,String address,String phoneNUmber,String emailAddress){
        ValidatorResponse v1 = Validator.validateAddress(address,ADDRESS_LIMIT,Validator.CheckType.CHECK_ALL);
        ValidatorResponse v2 = Validator.validatePersonalNumericalCode(phoneNUmber,PHONE_NUM_LIMIT,Validator.CheckType.CHECK_ALL);
        ValidatorResponse v3 = Validator.validateEmailAddress(emailAddress,ADDRESS_LIMIT,Validator.CheckType.CHECK_ALL);
        if(!v1.isValid()) return v1.getMessage();
        if(!v2.isValid()) return v2.getMessage();
        if(!v3.isValid()) return v3.getMessage();

        ContactInformation ci = new ContactInformation(0,student.getId(),address,phoneNUmber,emailAddress);
        boolean insertResult = (new CiDAO()).insert(ci);
        if(!insertResult) return ERROR_TEXT;
        logStudentActivity(student.getId(),"CREATE","Create contact information section");
        return null;
    }
    public ContactInformation viewContactInfo(Student student){
        CiDAO ciDAO = new CiDAO();
        ContactInformation ci =  ciDAO.findByIdStudent(student.getId());
        return ci;
    }
    public String updateContactInfo(ContactInformation ciOld,String address,String phoneNUmber,String emailAddress){
        if(ciOld == null) return NULL_ERROR;
        ValidatorResponse v1 = Validator.validateAddress(address,ADDRESS_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v2 = Validator.validatePersonalNumericalCode(phoneNUmber,PHONE_NUM_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v3 = Validator.validateEmailAddress(emailAddress,ADDRESS_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        if(!v1.isValid()) return v1.getMessage();
        if(!v2.isValid()) return v2.getMessage();
        if(!v3.isValid()) return v3.getMessage();

        ContactInformation ciNew = ciOld.clone();
        if(address != null && !address.isEmpty()) ciNew.setAddress(address);
        if(phoneNUmber != null && !phoneNUmber.isEmpty()) ciNew.setPhoneNumber(phoneNUmber);
        if(emailAddress != null && !emailAddress.isEmpty()) ciNew.setPhoneNumber(phoneNUmber);

        boolean updateResult = (new CiDAO()).update(ciNew);
        if(!updateResult) return ERROR_TEXT;
        logStudentActivity(ciOld.getIdStudent(),"UPDATE","Update contact information section");
        return null;
    }
    public String deleteContactInfo(ContactInformation ci){
        if(ci == null) return NULL_ERROR;
        boolean deleteResult = (new CiDAO()).delete(ci);
        if(!deleteResult) return ERROR_TEXT;
        logStudentActivity(ci.getIdStudent(),"DELETE","Delete contact information section");
        return null;
    }

    /**process student enrollment - search for course, enroll student for course*/
    public Course searchForCourse(String courseSession){
        if(courseSession.isEmpty()) return null;
        String[] splitText = courseSession.split("-");
        if(splitText.length != 2) return null;
        String courseName = splitText[0];
        String session = splitText[1];
        Course course = (new CourseDAO()).findByNameAndSession(courseName,session);
        return course;
    }
    public String processEnrollment(Student student,Course course,String enrollmentKey){
        ValidatorResponse vR = Validator.validatePassword(enrollmentKey,ENROLLMENT_KEY_LIMIT,Validator.CheckType.CHECK_ALL);
        if(!vR.isValid()) return vR.getMessage();
        if(enrollmentKey.compareTo(course.getEnrollmentKey()) != 0) return ENROLLMENT_ERROR;

        Enrollment e = new Enrollment(0,course.getId(),student.getId(),0.0);
        boolean enrollmentResult = (new EnrollmentDAO()).insert(e);
        if(!enrollmentResult) return ERROR_TEXT;
        logStudentActivity(student.getId(),"ENROLLMENT","Student enrollment to "+course.getName());
        return null;
    }
    public ObservableList<String> findEnrolledCourses(Student student){
        List<Enrollment> enrollments = (new EnrollmentDAO()).findByidStudent(student.getId());
        ObservableList<String> enrolledCourses = FXCollections.observableArrayList();

        for(Enrollment itr: enrollments){
            System.out.println(itr);
            Course course = (new CourseDAO()).findByID(itr.getIdCourse());
            EnrolledCourse e = new EnrolledCourse(course.getName(),course.getSession(),course.getExamDate(),itr.getFinalGrade());
            System.out.println(e);
            enrolledCourses.add(e.toString());
        }
        return enrolledCourses;
    }

    private void logStudentActivity(int idStudent,String activityType,String description){
        StudentActivity sa = new StudentActivity(0,idStudent, Date.valueOf(LocalDate.now()),activityType,description);
        (new StudentActivityDAO()).insert(sa);
    }
}
