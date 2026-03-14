package com.inventory;

public class MainApp {

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        dao.addProduct(new Product("Laptop","Electronics",70000,5));
        dao.addProduct(new Product("Phone","Electronics",30000,10));
        dao.addProduct(new Product("Mouse","Accessories",500,25));
        dao.addProduct(new Product("Keyboard","Accessories",1500,15));
        dao.addProduct(new Product("Monitor","Electronics",12000,7));
        dao.addProduct(new Product("Printer","Office",8000,3));

        dao.sortPriceAscending();
        dao.sortPriceDescending();
        dao.sortByQuantity();

        dao.firstThreeProducts();
        dao.nextThreeProducts();

        dao.countProducts();
        dao.countAvailableProducts();

        dao.countByDescription();
        dao.minMaxPrice();

        dao.groupByDescription();
        dao.priceRange(1000,20000);

        dao.nameStartsWith("P");
    }
}