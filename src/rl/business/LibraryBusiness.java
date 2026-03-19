package rl.business;

import rl.entity.Book;

import java.util.*;

public class LibraryBusiness {
    public List<Book> books = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    private static LibraryBusiness instance;

    private LibraryBusiness() {
    }

    //Khởi tạo biến instance một lần duy nhất
    public static LibraryBusiness getInstance() {
        if (instance == null) {
            instance = new LibraryBusiness();
        }
        return instance;
    }
    //in ra tất cả sách
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("Danh sách rỗng.");
            return;
        }

        System.out.println("+------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-20s | %-20s | %-15s | %-30s | %-20s |\n",
                "Id", "Tên sách", "Tác giả", "Năm XB", "Mô tả", "Trạng thái");
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------+");
        books.forEach(Book::displayData);

    }
    // Thêm sách
    public void add() {

        while (true) {
            Book book = new Book();
            book.inputData(input);
            boolean isExisted = books.stream()
                    .anyMatch(t -> t.getBookId().equals(book.getBookId()));
            if (isExisted) {
                System.out.println("Id sách bị trùng vui lòng nhập lại.");
            } else {
                books.add(book);
                System.out.println("Thêm thành công!");
                break;
            }
        }
    }
    // Update sách
    public void update() {
        System.out.print("Nhập id sách cần sửa : ");
        String id = input.nextLine();
        boolean isUpdate = false;

        Book book = books.stream().filter(t -> t.getBookId().equals(id)).findFirst().orElse(null);

        if (book == null) {
            System.out.println("Mã sách không tồn tại");
        } else {
            // update tên sách
            while (true) {
                System.out.print("Nhập tên sách mới (Enter để bỏ qua): ");
                String newName = input.nextLine().trim();
                if (newName.isEmpty()) break;
                if (newName.length() >= 5) {
                    book.setBookName(newName);
                    isUpdate = true;
                    break;
                }
                System.out.println("Tên sách không được dưới 5 kí tự.");
            }
            // update tên tác giả
            System.out.print("Nhập tên tác giả mới (Enter để bỏ qua): ");
            String newAuthor = input.nextLine();
            if (!newAuthor.trim().isEmpty()) {
                book.setAuthor(newAuthor);
                isUpdate = true;
            }
            //update năm mới
            while (true) {
                System.out.print("Nhập năm mới (Enter để bỏ qua): ");
                String temp = input.nextLine().trim();
                if (temp.isEmpty()) break;
                try {
                    int newYear = Integer.parseInt(temp);
                    if (newYear >= 1901 && newYear <= 2026) {
                        book.setYear(newYear);
                        isUpdate = true;
                        break;
                    }
                    System.out.println("Năm xuất bản phải từ 1901 đến 2026.");
                } catch (Exception e) {
                    System.out.println("Sai định dạng năm!");
                }
            }

            System.out.print("Nhập mô tả mới về sách (Enter để bỏ qua): ");
            String description = input.nextLine();
            if (!description.trim().isEmpty()) {
                book.setDescription(description);
                isUpdate = true;
            }

            System.out.print("Nhập trạng thái mới (true/false) (Enter để bỏ qua): ");
            String isAvailable = input.nextLine().trim().toLowerCase();
            if (isAvailable.equalsIgnoreCase("true") || isAvailable.equalsIgnoreCase("false")) {
                book.setAvailable(Boolean.parseBoolean(isAvailable));
                isUpdate = true;
            }
        }
        if (isUpdate) {
            System.out.println("Cập nhật thành công");
        } else {
            System.out.println("Không có thay đổi nào!");
        }

    }
    // Xóa sách theo id, từ chối nếu đang được mượn
    public void delete() {
        System.out.print("Nhập id sách cần xóa: ");
        String id = input.nextLine();

        Book book = books.stream()
                .filter(t -> t.getBookId().equals(id))
                .findFirst()
                .orElse(null);

        if (book == null) {
            System.out.println("Mã sách không tồn tại!");
        } else if (!book.isAvailable()) {
            System.out.println("Sách đang được mượn, không thể xóa!");
        } else {
            books.remove(book);
            System.out.println("Xóa thành công!");
        }
    }

    // tìm kiếm không phân biệt hoa thường
    public void search() {
        System.out.print("Nhập tên sách hoặc tác giả cần tìm: ");
        String key = input.nextLine().trim().toLowerCase();
        if (key.isEmpty()) {
            System.out.println("Vui lòng nhập từ khóa tìm kiếm!");
            return;
        }
        // lọc : chỉ lấy những sách có Tên HOẶC Tác giả chứa từ khóa
        List<Book> result = books.stream()
                .filter(b -> b.getBookName().toLowerCase().contains(key) ||
                        b.getAuthor().toLowerCase().contains(key))
                .toList();

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        } else {
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("| %-4s | %-20s | %-20s | %-15s | %-30s | %-20s |\n",
                    "Id", "Tên sách", "Tác giả", "Năm XB", "Mô tả", "Trạng thái");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------+");

            // Hiển thị kết quả
            result.forEach(Book::displayData);

            System.out.println("Tổng số lượng : " + result.size());
        }
    }

    //Sắp xếp
    public void sort() {
        books = books.stream()
                .sorted((a, b) -> b.getYear() - a.getYear())
                .collect(java.util.stream.Collectors.toList());

        System.out.println("Sắp xếp thành công!");
        displayBooks();
    }

    // Thống kê sách có sẵn và đang mượn
    public void count() {
        long available = books.stream().filter(Book::isAvailable).count();
        long borrowed = books.size() - available;

        System.out.println("Có sẵn: " + available);
        System.out.println("Đang mượn: " + borrowed);
    }
}
