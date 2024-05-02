package rikkei.academy.business.until;


import java.util.Scanner;

public class InputMethods {

    private static final String ERROR_ALERT = "===>> Định dạng không hợp lệ, hoặc ngoài phạm vi! Vui lòng thử lại....";
    private static final String EMPTY_ALERT = "===>> Trường nhập vào không thể để trống! Vui lòng thử lại....";
    /*========================================Input Method Start========================================*/

    /**
     * getInput()  Nhận chuỗi từ người dùng, trả về lỗi nếu rỗng và yêu cầu nhập lại.
     */
    public static String getString() {
        while (true) {
            String result = getInput();
            if (result.equals("")) {
                System.err.println(EMPTY_ALERT);
                continue;
            }
            return result;
        }
    }

    /**
     * getInput()  trả về true nếu nhập "true"(không phân biệt hoa thường) còn lại trả về false
     */
    public static boolean getBoolean() {
        String result = getString();
        return result.equalsIgnoreCase("true");
    }

    /**
     * getByte()  trả về giá trị tương ứng từ người dùng nhập vào, nếu không phải số thì thông báo lỗi và yêu cầu nhập lại.
     */
    public static byte getByte() {
        while (true) {
            try {
                return Byte.parseByte(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getInteger()  trả về giá trị tương ứng từ người dùng nhập vào, nếu không phải số thì thông báo lỗi và yêu cầu nhập lại.
     */
    public static int getInteger() {
        while (true) {
            try {
                return Integer.parseInt(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /*========================================Input Method End========================================*/

    /**
     * getInput()  Nhấn bất kỳ phim nào để tiếp tục.
     */
    private static String getInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}
