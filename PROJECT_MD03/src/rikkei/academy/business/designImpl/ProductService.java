package rikkei.academy.business.designImpl;

import rikkei.academy.business.design.IGenericService;
import rikkei.academy.business.model.Catalog;
import rikkei.academy.business.model.Product;

import java.util.*;

public class ProductService implements IGenericService<Product, String> {
    private List<Product> products = new ArrayList();
    private CatalogService catalogService;

    public ProductService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public List<Product> findAll() {
        return this.products;
    }

    public void save(Product product) {
        int index = this.products.indexOf(product);
        if (index != -1) {
            this.products.set(index, product);
        } else {
            this.products.add(product);
        }

    }

    public Product findById(String id) {
        Iterator var2 = this.products.iterator();

        Product product;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            product = (Product)var2.next();
        } while(!product.getProductId().equals(id));

        return product;
    }

    public void deleteById(String id) {
        Product product = this.findById(id);
        if (product != null) {
            this.products.remove(product);
        }

    }

    public void addProducts(int numProducts) {
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < numProducts; ++i) {
            Product product = new Product();

            String productId;
            do {
                System.out.print("Mời bạn nhập mã sách (bắt đầu bằng chữ P và thêm 4 ký tự số): ");
                productId = scanner.nextLine();
            } while(!productId.matches("P\\d{4}"));

            product.setProductId(productId);

            String productName;
            do {
                System.out.print("Mời bạn nhập tên sách (Không được để trống): ");
                productName = scanner.nextLine();
            } while(productName.isEmpty());

            product.setProductName(productName);

            double productPrice;
            do {
                System.out.print("Mời bạn nhập giá (phải lớn hơn 0): ");
                productPrice = scanner.nextDouble();
            } while(productPrice <= 0.0);

            product.setProductPrice(productPrice);

            int stock;
            do {
                System.out.print("Mời bạn nhập số lượng sách (ít nhất là 10): ");
                stock = scanner.nextInt();
                scanner.nextLine();
            } while(stock < 10);

            product.setStock(stock);
            System.out.print("Mời bạn nhập mô tả sách: ");
            product.setDescription(scanner.nextLine());
            Catalog catalog = null;

            while(catalog == null) {
                System.out.print("Mời bạn nhập tên danh mục mà bạn muốn bỏ sách vào (không được để null):");
                String catalogName = scanner.next();
                catalog = this.catalogService.getCatalogByName(catalogName);
                if (catalog == null) {
                    System.out.println("Không tìm thấy danh mục. Tạo mới danh mục.");
                    Catalog newCatalog = new Catalog();
                    newCatalog.setCatalogName(catalogName);
                    this.catalogService.save(newCatalog);
                    catalog = newCatalog;
                }
            }

            product.setCatalog(catalog);
            System.out.print("Mời bạn nhập trạng thái sách (true/false): ");
            product.setStatus(scanner.nextBoolean());
            this.save(product);
            System.out.println("Thêm sách thành công!");
        }

    }

    public void displayAllProducts() {
        List<Product> allProducts = this.findAll();
        if (allProducts.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong danh sách.");
        } else {
            Iterator var2 = allProducts.iterator();

            while(var2.hasNext()) {
                Product product = (Product)var2.next();
                System.out.println(product);
            }
        }

    }

    public void sortProductsByPrice() {
        Collections.sort(this.products, Comparator.comparingDouble(Product::getProductPrice).reversed());
        System.out.println("Đã sắp xếp xong.");
    }

    public void deleteProduct(String id) {
        Product product = this.findById(id);
        if (product != null) {
            this.deleteById(id);
            System.out.println("Sản phẩm có ID là " + id + " đã bị xóa.");
        } else {
            System.out.println("không tìm thấy sản phẩm có ID là " + id + ".");
        }

    }

    public void searchProductByName(String name) {
        Iterator var2 = this.findAll().iterator();

        while(var2.hasNext()) {
            Product product = (Product)var2.next();
            if (product.getProductName().equals(name)) {
                System.out.println(product);
            }
        }

    }

    public void editProduct(String id, String newName, double newPrice, String newDescription, int newStock, Catalog newCatalog, boolean newStatus) {
        Product product = this.findById(id);
        if (product != null) {
            product.setProductName(newName);
            product.setProductPrice(newPrice);
            product.setDescription(newDescription);
            product.setStock(newStock);
            product.setCatalog(newCatalog);
            product.setStatus(newStatus);
            this.save(product);
        }

    }

    public Product getProductById(String productId) {
        Iterator var2 = this.products.iterator();

        Product product;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            product = (Product)var2.next();
        } while(!product.getProductId().equals(productId));

        return product;
    }

    public Product getProductByName(String productName) {
        Iterator var2 = this.products.iterator();

        Product product;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            product = (Product)var2.next();
        } while(!product.getProductName().equals(productName));

        return product;
    }
}
