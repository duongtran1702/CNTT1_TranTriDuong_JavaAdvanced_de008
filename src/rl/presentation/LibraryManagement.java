package rl.presentation;

import rl.business.LibraryBusiness;

import java.util.Scanner;

public class LibraryManagement {

    private final Scanner sc = new Scanner(System.in);

    public int menu() {
        while (true) {
            System.out.println("""
                *************** QUẢN LÝ KHO HÀNG ***************
                1. Hiển thị toàn bộ danh sách.
                2. Thêm mới sách.
                3. Cập nhật thông tin theo mã.
                4. Xóa sách theo mã.
                5. Tìm kiếm sách theo tên hoặc tác giả.
                6. Thống kê tình trạng sách.
                7. Sắp xếp theo năm giảm dần.
                8. Thoát.
                """);
            try {
                System.out.print("Lựa chọn của bạn: ");
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập không hợp lệ!");
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagement lm = new LibraryManagement();
        LibraryBusiness lb = LibraryBusiness.getInstance();

        while (true) {
            int choice = lm.menu();

            switch (choice) {
                // in ra
                case 1:
                    lb.displayBooks();
                    break;
                //Thêm
                case 2:
                    String input;
                    do {
                        lb.add();
                        while (true) {
                            System.out.print("Tiếp tục thêm? (y/n): ");
                            input = lm.sc.nextLine().trim().toLowerCase();
                            if (input.equals("y") || input.equals("n")) {
                                break;
                            } else {
                                System.out.println("Chỉ được nhập y hoặc n!");
                            }
                        }

                    } while (input.equals("y"));
                    break;
                // cập nhật
                case 3:
                    lb.update();
                    break;
                // xóa
                case 4:
                    lb.delete();
                    break;
                // xóa
                case 5:
                    lb.search();
                    break;
                // Thống kê
                case 6:
                    lb.count();
                    break;
                // sắp xếp
                case 7:
                    lb.sort();
                    break;
                // Thoát
                case 8:
                    System.out.println("Thoát chương trình!");
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}