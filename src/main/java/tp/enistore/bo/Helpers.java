package tp.enistore.bo;

import tp.enistore.service.ServiceResponse;

public class Helpers {

    public static final int CD_SUCCES = 200;
    public static final int CD_ERROR = 701;
    public static final int CD_NOT_FOUND = 702;

    public static void logger(ServiceResponse serviceResponse){
        System.out.println("Code : " + serviceResponse.code + " & Message : " + serviceResponse.message);
    }

    //we can have better with date and time
    //we can have better systemically saving it to a file

    public static ServiceResponse logResponse(ServiceResponse serviceResponse){
        logger(serviceResponse);
        return serviceResponse;
    }

}
