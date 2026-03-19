package rl.entity;

import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private String author;
    private int year;
    private String description;
    private boolean isAvailable;

    public Book(String bookId, String bookName, String author, int year, String description, boolean isAvailable) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public Book() {
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void inputData(Scanner scanner) {
        String regexName = "^B\\d{3}$";
        // Nhập id sách
        while (true) {
            System.out.print("Nhập id sách (Bxxx):");
            String input = scanner.nextLine().trim();
            if (input.matches(regexName)) {
                this.bookId = input;
                break;
            }
            System.out.println("Id sách không hợp lệ.");
        }

        //Nhập tên sách
        while (true) {
            System.out.print("Nhập tên sách: ");
            String input = scanner.nextLine().trim();
            if (input.length() >= 5) {
                this.bookName = input;
                break;
            }
            System.out.println("Tên sách không được dưới 5 kí tự.");
        }

        //Nhập tên tác giả
        while (true) {
            System.out.print("Nhập tên tác giả: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                this.author = input;
                break;
            }
            System.out.println("Tên tác giả không được trống.");
        }

        //Nhập năm xuất bản
        while (true) {
            try {
                System.out.print("Nhập năm xuất bản: ");
                String temp = scanner.nextLine();
                int year = Integer.parseInt(temp);
                if (year >= 1901 && year <= 2026) {
                    this.year = year;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Năm xuất bản không đúng định dạng.");
                continue;
            }

            System.out.println("Năm xuất bản phải sau 1900 và không lớn hơn 2026.");
        }

        // Nhập mô tả về sách
        while (true) {
            System.out.print("Nhập mô tả ngắn về sách: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                this.description = input;
                break;
            }
            System.out.println("Mô tả sách không được trống.");
        }
        //Nhập trạng thái
        while (true) {
            System.out.print("Nhập trạng thái sách (true - có sẵn/false - đang cho mượn): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("true")) {
                this.isAvailable = true;
                break;
            } else if (input.equalsIgnoreCase("false")) {
                this.isAvailable = false;
                break;
            } else {
                System.out.println("Chỉ nhập true/false!");
            }
        }

    }
    // in dữ liệu dạng bảng
    public void displayData() {
        System.out.printf("| %-4s | %-20s | %-20s | %-15d | %-30s | %-20s |\n",
                this.bookId, this.bookName, this.author, this.year, this.description, (this.isAvailable() ? "Có sẵn" : "Đang cho mượn"));
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------+");
    }
}
