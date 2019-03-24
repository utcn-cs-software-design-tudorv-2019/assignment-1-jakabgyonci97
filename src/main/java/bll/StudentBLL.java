package bll;

import dal.dao.*;
import dal.entity.*;
public class StudentBLL {

    public PersonalInformation viewPersonalInfo(Student student){
        PiDAO pidao = new PiDAO();
        PersonalInformation pi = pidao.findByIdStudent(student.getId());
        return pi;
    }

    public StudentInformation viewStudentInfo(Student student){
        SiDAO siDAO = new SiDAO();
        StudentInformation si = siDAO.findByIdStudent(student.getId());
        return si;
    }

    public ContactInformation viewContactInfo(Student student){
        CiDAO ciDAO = new CiDAO();
        ContactInformation ci =  ciDAO.findByIdStudent(student.getId());
        return ci;
    }

    public String createPersonalInfo(String firstName,String lastName,String icn,String pnc){

        return null;
    }

    public String createStudentInfo(String idStudent,String group,String scholarShip,String grades){
        return null;
    }

    public String createContactInfo(String address,String phoneNUmber,String emailAddress){
        return null;
    }

    public String deleteStudentInfo(StudentInformation si){
        SiDAO siDAO = new SiDAO();
        siDAO.delete(si);
        return null;
    }

    public String deleteContactInfo(ContactInformation ci){
        CiDAO ciDAO = new CiDAO();
        ciDAO.delete(ci);
        return null;
    }

    public String updatePersonalInfo(PersonalInformation pi,String firstName,String lastName,String icn,String pnc){
        return null;
    }

    public String updateStudentInfo(StudentInformation si,String idStudent,String group,String scholarShip,String grades){
        return null;
    }

    public String updateContactInfo(ContactInformation ci,String address,String phoneNUmber,String emailAddress){
        return null;
    }
}
