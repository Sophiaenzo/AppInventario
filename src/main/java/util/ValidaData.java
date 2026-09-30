package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Class que valida se a data e valida
 */
public class ValidaData {
    /**
     * parametro necessario campo no formato data
     */
    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");

    public static boolean isValidFormat(String datestr) {

        if (DATE_PATTERN.matcher(datestr).matches())
            return true;

        return false;
    }

    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat simplesdateformat = new SimpleDateFormat("dd/MM/yyyy");
        simplesdateformat.setLenient(false);
        try {
            simplesdateformat.parse(dateStr);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }


    public static boolean isDataValida(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }





}
