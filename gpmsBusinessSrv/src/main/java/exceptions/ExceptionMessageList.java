/**
 * 
 */
package exceptions;

/**
 * @author narenda.kumar
 * 
 */
public interface ExceptionMessageList {

	String ERROR_NO_USER_FOUND_MSG = "The User Id doesnot exists in the system.";
	String ERROR_NO_ASSET_FOUND_MSG = "The Asset Id doesnot exists in the system.";
	String ERROR_ASSET_IS_NOT_AVAILABLE_MSG = "The asset is not available for assignment.";
	String ERROR_WITH_DATABASE_MSG = "There is an error with the database.";
	String ERROR_ASSET_IS_ALREADY_ASSIGNED_MSG = "The asset is already assigned state";
	String ERROR_ASSET_IS_NOT_ASSIGNED_MSG = "The asset is not in a assigned state, to be returned";
	String ERROR_ASSET_IS_NOT_INPROCESS_MSG = "The asset is not in a process state for further action";

}
