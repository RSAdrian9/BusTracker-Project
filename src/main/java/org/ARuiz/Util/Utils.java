package org.ARuiz.Util;

public class Utils {

    public static boolean validateUsername(String username){
        return username.matches("^[a-zA-Z]{3,25}$");
    }

    public static boolean validatePassword(String password) {
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{8,50}$");
    }

    public static boolean validateEmail(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean validateName(String name){
        return name.matches("^(?=.*[a-zA-ZñÑáéíóúÁÉÍÓÚ])[a-zA-ZñÑáéíóúÁÉÍÓÚ ]{3,25}$");
    }

}
