package rikkei.academy.business.designImpl;

import rikkei.academy.business.design.IGenericService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import rikkei.academy.business.model.Catalog;

public class CatalogService implements IGenericService<Catalog, Integer> {
    private List<Catalog> catalogs = new ArrayList();

    public CatalogService() {
    }

    public List<Catalog> findAll() {
        return this.catalogs;
    }

    public void save(Catalog catalog) {
        int index = this.catalogs.indexOf(catalog);
        if (index != -1) {
            this.catalogs.set(index, catalog);
        } else {
            this.catalogs.add(catalog);
        }

    }

    public Catalog findById(Integer id) {
        Iterator var2 = this.catalogs.iterator();

        Catalog catalog;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            catalog = (Catalog)var2.next();
        } while(catalog.getCatalogId() != id);

        return catalog;
    }

    public void deleteById(Integer id) {
        Catalog catalog = this.findById(id);
        if (catalog != null) {
            if (catalog.getProducts().isEmpty()) {
                this.catalogs.remove(catalog);
            } else {
                System.out.println("Không thể xóa danh mục có id là " + id + " vì nó có sản phẩm liên quan.");
            }
        } else {
            System.out.println("Không tìm thấy danh mục " + id + " để xóa.");
        }

    }

    public Catalog getCatalogByName(String name) {
        Iterator var2 = this.catalogs.iterator();

        Catalog catalog;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            catalog = (Catalog)var2.next();
        } while(!catalog.getCatalogName().equals(name));

        return catalog;
    }

    public void addCatalogs(int numCatalogs) {
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < numCatalogs; ++i) {
            Catalog catalog = new Catalog();
            System.out.print("Mời bạn nhập mã danh mục (Chỉ được nhập số): ");
            catalog.setCatalogId(scanner.nextInt());
            scanner.nextLine();

            String catalogName;
            do {
                System.out.print("Mời bạn thêm tên danh mục (Không được để trống): ");
                catalogName = scanner.nextLine();
            } while(catalogName.isEmpty());

            catalog.setCatalogName(catalogName);

            String descriptions;
            do {
                System.out.print("Mời bạn mô tả danh mục (không được để trống): ");
                descriptions = scanner.nextLine();
            } while(descriptions.isEmpty());

            catalog.setDescriptions(descriptions);
            this.save(catalog);
            System.out.println("Đã thêm danh mục thành công");
        }

    }

    public void displayAllCatalogs() {
        List<Catalog> allCatalogs = this.findAll();
        if (allCatalogs.isEmpty()) {
            System.out.println("Danh sách danh mục rỗng.");
        } else {
            Iterator var2 = allCatalogs.iterator();

            while(var2.hasNext()) {
                Catalog catalog = (Catalog)var2.next();
                System.out.println(catalog);
            }
        }

    }

    public void editCatalogName(int id, String newName) {
        Catalog catalog = this.findById(id);
        if (catalog != null) {
            catalog.setCatalogName(newName);
            this.save(catalog);
            System.out.println("Đã sửa thành công");
            this.displayAllCatalogs();
        }

    }

    public void deleteCatalog(int id) {
        this.deleteById(id);
    }
}
