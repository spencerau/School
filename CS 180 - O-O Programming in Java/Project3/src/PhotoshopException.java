/**
 * Created by Spencer on 7/14/2017.
 */
public class PhotoshopException extends Exception {

    private int errorCode;
    private String error;

    public PhotoshopException(int errorCode) {
        this.errorCode = errorCode;
        error = "ERROR: ";
        switch (errorCode) {
            case 10:
                error += "No Command Specified";
                break;
            case 11:
                error += "Unknown Command";
                break;
            case 12:
                error += "Invalid Argument Count";
                break;
            case 20:
                error += "Invalid Flip";
                break;
            case 21:
                error += "Invalid Filter";
                break;
            case 22:
                error += "Could Not Parse Integer Parameters";
                break;
            case 30:
                error += "Invalid Selection Area";
                break;
            case 31:
                error += "Cannot Rotate Selection";
                break;
            case 32:
                error += "No Region Selected";
                break;
            case 33:
                error += "Cannot Generate Sub-Region From A Sub-Region";
                break;
            default:
                error += "Unknown Error";
                break;
        }
    }

    @Override
    public String getMessage() {
        return this.error;
    }

    @Override
    public void printStackTrace() {
        System.out.println(error);
    }
}
