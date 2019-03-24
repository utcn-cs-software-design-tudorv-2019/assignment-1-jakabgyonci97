package bll;

public class Validator {
    protected static ValidatorResponse validateUserName(String userName,int lengthLimit){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(userName == null || userName.isEmpty()){
            vr.setValidity(false);
            vr.setMessage("No actual userName introduced");
        }
        else{
            if(!userName.matches("[A-Za-z0-9_]+")){
                vr.setValidity(false);
                vr.setMessage("UserName contains invalid characters!Valid pattern: [A-Za-z0-9_]");
            }
            if(userName.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("UserName is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected static ValidatorResponse validatePassword(String password,int lengthLimit){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(password == null || password.isEmpty()){
            vr.setValidity(false);
            vr.setMessage("No actual password introduced");
        }
        else{
            if(!password.matches("[A-Za-z0-9_+!?~&$@]+")){
                vr.setValidity(false);
                vr.setMessage("Password contains invalid characters!Valid pattern: [A-Za-z0-9_+!?~&$@]");
            }
            if(password.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("Password is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected static ValidatorResponse validateLastName(String lastName,int lengthLimit){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(lastName == null || lastName.isEmpty()){
            vr.setValidity(false);
            vr.setMessage("No actual Last Name introduced");
        }
        else{
            String[] splitresult = lastName.split("-");
            for(String slipItem: splitresult)
                if(!slipItem.matches("[A-Za-z]")){
                    vr.setValidity(false);
                    vr.setMessage("Last name contains invalid characters!Valid pattern: [A-Za-z]");
                }
            if(lastName.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("Last name is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected static ValidatorResponse validateFirstName(String firstName,int lengthLimit){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(firstName == null || firstName.isEmpty()){
            vr.setValidity(false);
            vr.setMessage("No actual First Name introduced");
        }
        else{
            String[] splitresult = firstName.split(" ");
            for(String slipItem: splitresult)
                if(!slipItem.matches("[A-Za-z]")){
                    vr.setValidity(false);
                    vr.setMessage("First name contains invalid characters!Valid pattern: [A-Za-z]");
                }
            if(firstName.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("First name is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected static ValidatorResponse validateEmailAddress(String emailAddress,int lengthLimit){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(emailAddress == null || emailAddress.isEmpty()){
            vr.setValidity(false);
            vr.setMessage("No actual email address introduced");
        }
        else{
            if(!emailAddress.matches("[A-Za-z0-9_@.<>&$]")){
                vr.setValidity(false);
                vr.setMessage("Email address contains invalid characters!valid pattern: [A-Za-z0-9_@.<>&$]");
            }
            if(emailAddress.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("Email address is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected static ValidatorResponse validateDoubleNumber(String doubleNumber,double lowLimit,double highLimit){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(doubleNumber == null || doubleNumber.isEmpty()){
            vr.setValidity(false);
            vr.setMessage("No actual value introduced");
        }
        else{
            try{
                double doubleValue = Double.parseDouble(doubleNumber);
                if(!(lowLimit <= doubleValue && doubleValue <= highLimit)){
                    vr.setValidity(false);
                    vr.setMessage("Double value introduced not in range!Range: [" + lowLimit+","+highLimit+"]");
                }
            }catch(NumberFormatException e){
                vr.setValidity(false);
                vr.setMessage("Value introduced was not double!");
            }
        }
        return vr;
    }

    protected static ValidatorResponse validateIntegerNumber(String intNumber,int lowLimit,int highLimit){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(intNumber == null || intNumber.isEmpty()){
            vr.setValidity(false);
            vr.setMessage("No actual value introduced");
        }
        else{
            try{
                int intValue = Integer.parseInt(intNumber);
                if(!(lowLimit <= intValue && intValue <= highLimit)){
                    vr.setValidity(false);
                    vr.setMessage("Integer value introduced not in range!Range: [" + lowLimit+","+highLimit+"]");
                }
            }catch(NumberFormatException e){
                vr.setValidity(false);
                vr.setMessage("Value introduced was not integer!");
            }
        }
        return vr;
    }

}
