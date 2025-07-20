/**
 * Utlities.java
 * This class contains utility methods that can be used throughout the application.
 * Samantha Beauchamp
 * CST-339
 * 04/03/2025
 */
package cloud.main.utilities;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import com.gcu.model.CategoryModel;
/**
 * This class contains utility methods that can be used throughout the application.
 */
public class Utilities {
	
	/**
	 * This method returns the current date and time in a specific format.
	 * 2025-04-03T15:50:58.387358 format
	 * https://www.w3schools.com/java/java_date.asp for more formatting options
	 * @return The current date and time as a string.
	 */
	public static String getCurrentTime() {
		
		LocalDateTime curDateTime = LocalDateTime.now();									// Get the current date and time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");	// Format the date and time
		return curDateTime.format(formatter);												// Return the formatted date and time
	}
	/**
	 * This method formats a given date string from "yyyy-MM-dd HH:mm:ss" to "MM/dd/yyyy HH:mm:ss".
	 * @param date The date string to be formatted.
	 * @return The formatted date string.
	 */
	public static String formatDate(String date) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
		 DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		 
		 return dateTime.format(outputFormatter);
	}
	/**
	 * This method formats a given double value to a string representing money in USD.
	 * @param money The double value to be formatted.
	 * @return The formatted money string.
	 */
	public static String formatMoney(Double money) {
		String moneyString = String.format("%.2f", money);
		return "$" + moneyString;
	}
	
	/**
	 * This method refreshes the image directory by updating the last modified time of each file in the directory.
	 * This is useful for ensuring that the images are reloaded in the application.
	 * @param folderPath The path to the image directory.
	 */
	public static void refreshImageDirectory(String folderPath) {
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		
		if(files != null) {
			for (File file : files) {
				file.setLastModified(System.currentTimeMillis());
			}
			System.out.println("Image directory refreshed.");
		} else {
			System.out.println("No files found in the directory.");
		}
	}
	public DateTimeFormatter getFormatter() {
		
		return null;
	}
	/**
	 * Changes text color to red.
	 * @param str The string to be colored.
	 * @return red colored string.
	 */
	public static String errColor(String message) {
		return  CColor.RED + message + CColor.RESET;
	}
	/**
	 * Changes text color to green.
	 * @param str The string to be colored.
	 * @return green colored string.
	 */
	public static String successColor(String message) {
		return CColor.GREEN + message + CColor.RESET;
	}
	/**
	 * Changes text color to yellow.
	 * @param str The string to be colored.
	 * @return yellow colored string.
	 */
	public static String warningColor(String message) {
		return CColor.YELLOW + message + CColor.RESET;
	}
	public static String warningMessage(String className, String methodName, String Message) {
		return CColor.YELLOW + className + " -> " + methodName + " -> " + Message + CColor.RESET;
	}
	public static String successMessage(String className, String methodName, String Message) {
		return CColor.GREEN + className + " -> " + methodName + " -> " + Message + CColor.RESET;
	}
	public static String errorMessage(String className, String methodName, String Message) {
		return CColor.RED + className + " -> " + methodName + " -> " + Message + CColor.RESET;
	}
	
	public static String[] reverseArray(String[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            // Swap elements at start and end indices
            String temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            // Move indices towards the middle
            start++;
            end--;
        }
        return arr;
    }
	/*public static CategoryModel[] reverseArray(CategoryModel[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            // Swap elements at start and end indices
            CategoryModel temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            // Move indices towards the middle
            start++;
            end--;
        }
        return arr;
    }*/
	
	
}
