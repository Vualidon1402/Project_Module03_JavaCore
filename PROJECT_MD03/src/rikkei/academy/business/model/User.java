package rikkei.academy.business.model;

import rikkei.academy.business.until.InputMethods;
import rikkei.academy.business.until.validation.UserValidate;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private boolean status = true;
    private String phone;
    private LocalDate createdDate;
    private UserRole role;
//    private List<Product> cart;
//
//    private List<Message> messageList = new ArrayList<>();

    public User() {
    }

    public User(int userId, String userName, String password, String email, boolean status, String phone, LocalDate createdDate, UserRole role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.status = status;
        this.phone = phone;
        this.createdDate = createdDate;
        this.role = role;
//        this.cart = cart;
//        this.messageList = messageList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
//    public List<Product> getExamSessions() {
//        return cart;
//    }
//
//    public void setExamSessions(List<Product> examSessions) {
//        this.cart = examSessions;
//    }
//
//    public List<Message> getMessageList() {
//        return messageList;
//    }
//
//    public void setMessageList(List<Message> messageList) {
//        this.messageList = messageList;
//    }

    // Phương thức để người dùng nhập thông tin với kiểm tra
    public void inputData(boolean isAdd) {

        // Nhập và kiểm tra tên đăng nhập
        while (true) {
            System.out.print("Nhập tên đăng nhập: ");
            String inputUsername = InputMethods.getString();

            if (UserValidate.isUsernameUnique(inputUsername)) {
                this.userName = inputUsername;
                break;
            } else {
                System.err.println("Lỗi: Tên đăng nhập đã tồn tại. Vui lòng chọn tên đăng nhập khác.");
            }
        }

        // Nhập và kiểm tra mật khẩu
        while (true) {
            System.out.print("Nhập mật khẩu: ");
            String inputPassword = InputMethods.getString();

            if (UserValidate.isPasswordValid(inputPassword)) {
                this.password = inputPassword;
                break;
            } else {
                System.err.println("Lỗi: Mật khẩu phải có ít nhất 6 kí tự. Vui lòng nhập lại.");
            }
        }

        // Nhập và kiểm tra vai trò
//        while (true) {
//            System.out.print("Nhập vai trò 1.student hoặc 2.teacher): ");
//            int inputRole = InputMethods.getInteger();
//
//            if (UserValidate.isRoleValid(inputRole)) {
//                if (inputRole==1){
//                    this.role=UserRoles.STUDENT;
//                    break;
//
//                } else if (inputRole==2) {
//                    this.role=UserRoles.TEACHER;
//                    break;
//                }
//                break;
//            } else {
//                System.err.println("Lỗi: Vai trò phải là 1 hoặc 2. Vui lòng nhập lại.");
//            }
//        }
        // Gán ngày tạo với định dạng "dd/MM/yyyy"
        this.createdDate = LocalDate.now();
    }

}
