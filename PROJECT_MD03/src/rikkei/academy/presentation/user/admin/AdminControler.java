package rikkei.academy.presentation.user.admin;

import rikkei.academy.business.designImpl.AdminService;
import rikkei.academy.business.designImpl.CatalogService;
import rikkei.academy.business.designImpl.ProductService;
import rikkei.academy.business.designImpl.UserService;
import rikkei.academy.business.model.Catalog;
import rikkei.academy.business.model.User;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;

import java.util.Scanner;

//import rikkei.academy.presentation.User.MessageControler;

public class AdminControler {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    static AdminService adminService = new AdminService();

    private static CatalogService catalogService = new CatalogService();
    private static ProductService productService = new ProductService(catalogService);

    public static void adminMenu(User userLogin) {
        UserService service = new UserService();
        String name = IOFile.readDataLogin(IOFile.USERLOGIN_PATH).getUserName();

        while (true) {
            System.out.print("\u001B[H\u001B[2J");

            // In menu với màu chữ và khung khác nhau
            System.out.println(ANSI_BLUE + "╔════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "         Chào mừng ADMINISTRATOR         " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Quản lý danh mục                    ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Quản lý sản phẩm                    ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Xem thống kê người mua hàng         ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Sửa thông tin người dùng            ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 5. Tìm kiếm thông tin người dùng       ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 6. Kiểm tra tin nhắn                   ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 0. Đăng xuất                           ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚════════════════════════════════════════╝" + ANSI_RESET);

            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    catalogManagement(new Scanner(System.in));
                    break;
                case 2:
                    productManagement(new Scanner(System.in));
                    break;
                case 3:
                    break;
                case 4:
                    UserService.upDateInfo();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    return;
                default:
                    System.out.println("lựa chọn không hợp lệ");
                    break;
            }
        }
    }

        private static void catalogManagement(Scanner scanner) {
            int choice;
            do {
                System.out.println("********************CATALOG-MANAGEMENT********************");
                System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
                System.out.println("2. Hiển thị thông tin tất cả các danh mục");
                System.out.println("3. Sửa tên danh mục theo mã danh mục");
                System.out.println("4. Xóa danh mục theo mã danh mục (lưu ý ko xóa khi có sản phẩm)");
                System.out.println("5. Quay lại");
                System.out.print("Nhập lựa chọn của bạn: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Số danh mục mà bạn muốn thêm là: ");
                        int numCatalogs = scanner.nextInt();
                        catalogService.addCatalogs(numCatalogs);
                        break;
                    case 2:
                        catalogService.displayAllCatalogs();
                        break;
                    case 3:
                        if (catalogService.findAll().isEmpty()) {
                            System.out.println("Mảng rỗng, cần thêm mới trước khi chỉnh sửa.");
                        } else {
                            System.out.print("Mời bạn nhập mã danh mục muốn sửa: ");
                            int id = scanner.nextInt();
                            System.out.print("Mời bạn nhập tên danh mục mới: ");
                            String newName = scanner.next();
                            catalogService.editCatalogName(id, newName);
                        }
                        break;
                    case 4:
                        System.out.print("Mời bạn nhập mã danh mục mà bạn muốn xóa: ");
                        int deleteId = scanner.nextInt();
                        catalogService.deleteCatalog(deleteId);
                        break;
                    case 5:
                        System.out.println("Quay lại BASIC-MENU");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                }
            } while (choice != 5);
        }

        private static void productManagement(Scanner scanner) {
            int choice;
            do {
                System.out.println("********************PRODUCT-MANAGEMENT********************");
                System.out.println("1. Nhập số sản phẩm và nhập thông tin sản phẩm");
                System.out.println("2. Hiển thị thông tin các sản phẩm");
                System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
                System.out.println("4. Xóa sản phẩm theo mã");
                System.out.println("5. Tìm kiếm sách theo tên sách");
                System.out.println("6. Thay đổi thông tin của sách theo mã sách");
                System.out.println("7. Quay lại");
                System.out.print("Nhập lựa chọn của bạn: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Nhập số sách bạn muốn thêm: ");
                        int numProducts = scanner.nextInt();
                        productService.addProducts(numProducts);
                        break;
                    case 2:
                        productService.displayAllProducts();
                        break;
                    case 3:
                        productService.sortProductsByPrice();
                        break;
                    case 4:
                        System.out.print("Nhập mã sách mà bạn muốn xóa: ");
                        String deleteId = scanner.next();
                        productService.deleteProduct(deleteId);
                        break;
                    case 5:
                        System.out.print("Nhập tên sách mà bạn muốn kiếm: ");
                        String name = scanner.next();
                        productService.searchProductByName(name);
                        break;
                    case 6:
                        System.out.print("Nhập mã sách mà bạn muốn sửa: ");
                        String id = scanner.next();
                        System.out.print("Mời bạn nhập tên sách mới: ");
                        String newName = scanner.next();
                        System.out.print("Mời bạn nhập giá mới: ");
                        double newPrice = scanner.nextDouble();
                        System.out.print("Mời bạn nhập mô tả: ");
                        String newDescription = scanner.next();
                        System.out.print("Mời bạn nhập số lượng: ");
                        int newStock = scanner.nextInt();
                        System.out.print("Mời bạn nhập danh mục: ");
                        String newCatalogName = scanner.next();
                        Catalog newCatalog = catalogService.getCatalogByName(newCatalogName);
                        System.out.print("Mời bạn cập nhật trạng thái: ");
                        boolean newStatus = scanner.nextBoolean();
                        productService.editProduct(id, newName, newPrice, newDescription, newStock, newCatalog, newStatus);
                        break;
                    case 7:
                        System.out.println("Quay lại BASIC-MENU");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                }
            } while (choice != 7);
        }

    }

