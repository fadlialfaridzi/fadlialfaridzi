import java.util.Scanner;

class Barang {
    protected String kodeBarang;
    protected String namaBarang;
    protected double hargaBarang;

    public Barang(String kodeBarang, String namaBarang, double hargaBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }

    public double hitungTotal(int jumlahBeli) {
        return jumlahBeli * hargaBarang;
    }
}

// Subclass: Transaksi (Inheritance)
class Transaksi extends Barang {
    private String noFaktur;

    public Transaksi(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang) {
        super(kodeBarang, namaBarang, hargaBarang); // Memanggil constructor superclass
        this.noFaktur = noFaktur;
    }

    public void tampilkanTransaksi(int jumlahBeli) {
        double total = hitungTotal(jumlahBeli);
        System.out.println("\n=== Detail Transaksi ===");
        System.out.println("No Faktur   : " + noFaktur);
        System.out.println("Kode Barang : " + kodeBarang);
        System.out.println("Nama Barang : " + namaBarang);
        System.out.println("Harga Barang: Rp " + hargaBarang);
        System.out.println("Jumlah Beli : " + jumlahBeli);
        System.out.println("Total Harga : Rp " + total);
    }
}

// Custom Exception
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

// Main Class
public class MainProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input No Faktur
            System.out.print("Masukkan No Faktur: ");
            String noFaktur = scanner.nextLine().trim();
            if (noFaktur.isEmpty()) {
                throw new InvalidInputException("No Faktur tidak boleh kosong!");
            }

            // Input Kode Barang
            System.out.print("Masukkan Kode Barang: ");
            String kodeBarang = scanner.nextLine().trim();
            if (kodeBarang.isEmpty()) {
                throw new InvalidInputException("Kode Barang tidak boleh kosong!");
            }

            // Input Nama Barang
            System.out.print("Masukkan Nama Barang: ");
            String namaBarang = scanner.nextLine().trim();
            if (namaBarang.isEmpty()) {
                throw new InvalidInputException("Nama Barang tidak boleh kosong!");
            }

            // Input Harga Barang
            System.out.print("Masukkan Harga Barang: ");
            if (!scanner.hasNextDouble()) {
                throw new InvalidInputException("Harga Barang harus berupa angka!");
            }
            double hargaBarang = scanner.nextDouble();
            if (hargaBarang <= 0) {
                throw new InvalidInputException("Harga Barang harus lebih dari 0!");
            }

            // Membersihkan buffer newline setelah nextDouble()
            scanner.nextLine();

            // Input Jumlah Beli
            System.out.print("Masukkan Jumlah Beli: ");
            if (!scanner.hasNextInt()) {
                throw new InvalidInputException("Jumlah Beli harus berupa angka!");
            }
            int jumlahBeli = scanner.nextInt();
            if (jumlahBeli <= 0) {
                throw new InvalidInputException("Jumlah Beli harus lebih dari 0!");
            }

            // Membuat objek transaksi
            Transaksi transaksi = new Transaksi(noFaktur, kodeBarang, namaBarang, hargaBarang);
            transaksi.tampilkanTransaksi(jumlahBeli);

        } catch (InvalidInputException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
