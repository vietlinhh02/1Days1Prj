import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// IOInterface.java
interface IOInterface {
    void printTitle();

    void printData();
}

// GioiTinh.java
enum GioiTinh {
    Male, Female, None
}

// HocVien.java
class HocVien implements IOInterface {
    private String maHV;
    private String tenHV;
    private GioiTinh gioiTinh;
    private int soBuoiHoc;

    public HocVien(String maHV, String tenHV, GioiTinh gioiTinh, int soBuoiHoc) {
        setMaHV(maHV); // Sử dụng setter để kiểm tra lỗi
        this.tenHV = tenHV;
        this.gioiTinh = gioiTinh;
        this.soBuoiHoc = soBuoiHoc;
    }

    public String getMaHV() {
        return maHV;
    }

    public String getTenHV() {
        return tenHV;
    }

    public GioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public int getSoBuoiHoc() {
        return soBuoiHoc;
    }

    public void setMaHV(String maHV) {
        if (maHV == null || maHV.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã học viên không được rỗng.");
        }
        this.maHV = maHV;
    }

    public void setTenHV(String tenHV) {
        this.tenHV = tenHV;
    }

    public void setGioiTinh(GioiTinh gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setSoBuoiHoc(int soBuoiHoc) {
        this.soBuoiHoc = soBuoiHoc;
    }

    @Override
    public void printTitle() {
        System.out.println("--------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-10s | %-10s |\n", "Mã HV", "Tên HV", "Giới Tính", "Số Buổi Học");
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void printData() {
        System.out.printf("| %-10s | %-20s | %-10s | %-10d |\n", maHV, tenHV, gioiTinh, soBuoiHoc);
    }
}

// HocVienManagement.java
public class HocVienManagement {
    private List<HocVien> danhSachHocVien;
    private Scanner scanner;

    public HocVienManagement() {
        danhSachHocVien = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void fakeData() {
        danhSachHocVien.add(new HocVien("HV003", "Nguyễn Văn C", GioiTinh.Male, 8));
        danhSachHocVien.add(new HocVien("HV001", "Nguyễn Thị A", GioiTinh.Female, 10));
        danhSachHocVien.add(new HocVien("HV002", "Trần Văn B", GioiTinh.Male, 12));
        danhSachHocVien.add(new HocVien("HV005", "Lê Thị D", GioiTinh.Female, 5));
        danhSachHocVien.add(new HocVien("HV004", "Phạm Văn E", GioiTinh.None, 7));
        danhSachHocVien.add(new HocVien("HV004", "Phạm Văn E", GioiTinh.None, 7));
        danhSachHocVien.add(new HocVien("HV004", "Phạm Văn E", GioiTinh.None, 7));
    }

    public void outputData() {
        if (danhSachHocVien.isEmpty()) {
            System.out.println("Danh sách học viên trống.");
            return;
        }
        danhSachHocVien.get(0).printTitle();
        for (HocVien hv : danhSachHocVien) {
            hv.printData();
        }
        System.out.println("--------------------------------------------------");
    }

    public void sortData() {
        danhSachHocVien.sort(Comparator.comparing(HocVien::getMaHV));
    }

    public void findData(String ma) {
        boolean found = false;
        List<HocVien> toRemove = new ArrayList<>();

        for (HocVien hv : danhSachHocVien) {
            if (hv.getMaHV().equals(ma)) {
                if (!found) {
                    found = true;
                } else {
                    toRemove.add(hv);
                }
            }
        }

        danhSachHocVien.removeAll(toRemove);

        if (!found) {
            System.out.println("Không tìm thấy học viên có mã " + ma);
        }
    }

    public void themHocVien() {
        System.out.println("Nhập thông tin học viên mới:");
        String maHV;
        while (true) {
            try {
                System.out.print("Mã HV: ");
                maHV = scanner.nextLine();
                // Kiểm tra trùng lặp mã HV
                for (HocVien hv : danhSachHocVien) {
                    if (hv.getMaHV().equals(maHV)) {
                        throw new IllegalArgumentException("Mã học viên đã tồn tại. Vui lòng nhập mã khác.");
                    }
                }
                // Kiểm tra mã HV rỗng
                if (maHV.trim().isEmpty()) {
                    throw new IllegalArgumentException("Mã học viên không được để trống.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Tên HV: ");
        String tenHV = scanner.nextLine();

        System.out.print("Giới tính (0: Male, 1: Female, 2: None): ");
        int gioiTinhInt = scanner.nextInt();
        GioiTinh gioiTinh = GioiTinh.values()[gioiTinhInt];

        System.out.print("Số buổi học: ");
        int soBuoiHoc = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        try {
            HocVien hv = new HocVien(maHV, tenHV, gioiTinh, soBuoiHoc);
            danhSachHocVien.add(hv);
            System.out.println("Thêm học viên thành công.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void suaHocVien() {
        System.out.print("Nhập mã học viên cần sửa: ");
        String maHV = scanner.nextLine();
        HocVien hocVienToUpdate = null;

        for (HocVien hv : danhSachHocVien) {
            if (hv.getMaHV().equals(maHV)) {
                hocVienToUpdate = hv;
                break;
            }
        }
        if (hocVienToUpdate == null) {
            System.out.println("Không tìm thấy học viên với mã " + maHV);
            return;
        }

        System.out.println("Nhập thông tin mới cho học viên (bỏ trống để giữ nguyên):");

        System.out.print("Tên HV (" + hocVienToUpdate.getTenHV() + "): ");
        String tenHV = scanner.nextLine();
        if (!tenHV.isEmpty()) {
            hocVienToUpdate.setTenHV(tenHV);
        }

        System.out.print("Giới tính (0: Male, 1: Female, 2: None) (" + hocVienToUpdate.getGioiTinh() + "): ");
        String gioiTinhInput = scanner.nextLine();
        if (!gioiTinhInput.isEmpty()) {
            int gioiTinhInt = Integer.parseInt(gioiTinhInput);
            hocVienToUpdate.setGioiTinh(GioiTinh.values()[gioiTinhInt]);
        }

        System.out.print("Số buổi học (" + hocVienToUpdate.getSoBuoiHoc() + "): ");
        String soBuoiHocInput = scanner.nextLine();
        if (!soBuoiHocInput.isEmpty()) {
            int soBuoiHoc = Integer.parseInt(soBuoiHocInput);
            hocVienToUpdate.setSoBuoiHoc(soBuoiHoc);
        }

        System.out.println("Cập nhật thông tin học viên thành công.");
    }

    public void xoaHocVien() {
        System.out.print("Nhập mã học viên cần xóa: ");
        String maHV = scanner.nextLine();

        boolean removed = danhSachHocVien.removeIf(hv -> hv.getMaHV().equals(maHV));

        if (removed) {
            System.out.println("Xóa học viên thành công.");
        } else {
            System.out.println("Không tìm thấy học viên với mã " + maHV);
        }
    }

    public void run() {
        fakeData();
        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm học viên");
            System.out.println("2. Sửa học viên");
            System.out.println("3. Xóa học viên");
            System.out.println("4. Tìm kiếm và xoá trùng lặp theo mã");
            System.out.println("5. Hiển thị danh sách học viên (đã sắp xếp theo mã)");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    themHocVien();
                    break;
                case 2:
                    suaHocVien();
                    break;
                case 3:
                    xoaHocVien();
                    break;
                case 4:
                    System.out.print("Nhập mã học viên cần tìm: ");
                    String maCanTim = scanner.nextLine();
                    findData(maCanTim);
                    outputData();
                    break;
                case 5:
                    sortData();
                    outputData();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);
        scanner.close();
    }

    public static void main(String[] args) {
        HocVienManagement manager = new HocVienManagement();
        manager.run();
    }
}