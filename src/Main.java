import daomodel.UserDaoModel;
import model.*;

import java.sql.Date;
import java.util.Scanner;


public class Main {
    /*buat objek*/
    static UserManage userManage = new UserManage();
    static BookStock bookStock = new BookStock();
    static RentBook rentBook = new RentBook();
    static IncomeManage incomeManage = new IncomeManage();
    static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {

        showMenu();

    }

    //menu
    static void showMenu(){
        int pilihan = 0;

        do{
            System.out.println("Selamat Datang di Pencatatan Perpustakaan");

            //menu
            System.out.println("-------------------");
            System.out.println("1. Daftar Member");
            System.out.println("2. Cari Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Pengembalian Buku");
            System.out.println("5. Isi saldo");
            System.out.println("6. Pengaturan User");
            System.out.println("7. Pengaturan Stock Buku");
            System.out.println("8. Pengaturan Pendapatan");
            System.out.println("0. Keluar");

            System.out.println("-------------------");
            System.out.print("Silahkan Pilih Menu : ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    daftarMember();
                    break;
                case 2 :
                    cariBuku();
                    break;
                case 3:
                    pinjemBuku();
                    break;
                case 4:
                    pengembalianBuku();
                    break;
                case 5:
                    isiSaldo();
                    break;
                case 6 :
                    pengaturanUser();
                    break;
                case 7 :
                    pengaturanStock();
                    break;
                case 8 :
                    pengaturanPendapatan();
                    break;
                default:
                    System.out.println("Pilihan salah!");
            }

        }while (pilihan != 0);
    }

    //daftar member
    static void daftarMember(){
        System.out.println("-------------------");
        System.out.println("Daftar Member");
        System.out.println("-------------------");

        try{
            //biar nggak nempel
            input = new Scanner(System.in);

            //nama depan
            System.out.print("Nama Depan    : ");
            String firstname = input.nextLine().trim();

            //biar nggak nempel
            input = new Scanner(System.in);

            //nama belakang
            System.out.print("Nama Belakang : ");
            String lastname = input.nextLine().trim();

            //umur
            System.out.print("Umur          : ");
            int age = input.nextInt();

            //method untuk daftar ke userManage
            userManage.createUser(firstname, lastname, age);

        }catch (Exception e){
            System.out.println("masukkan data dengan benar");
        }
    }

    //cari buku
    static void cariBuku() {
        int pilihan = 0;

        System.out.println("-------------------");
        System.out.println("Cari Buku");

        try{

            do {
                System.out.println("-------------------");
                System.out.println("1. Cari Judul");
                System.out.println("2. Cari Genre");
                System.out.println("0. Selesai Menu");
                System.out.print("Silahkan Pilih Menu : ");
                pilihan = input.nextInt();

                switch (pilihan) {
                    case 0:
                        break;

                    //update user
                    case 1:
                        System.out.println("-------------------");
                        System.out.println("Cari Judul");
                        System.out.println("-------------------");

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama depan
                        System.out.print("Masukkan Judul    : ");
                        String title = input.nextLine().trim();

                        //masukkan ke server
                        bookStock.searchBookTitle(title);

                        break;

                    //delete user
                    case 2:
                        System.out.println("-------------------");
                        System.out.println("Cari Genre");
                        System.out.println("-------------------");

                        //masukkan ke server
                        bookStock.searchBookGenre();
                        break;

                    default:
                        System.out.println("masukkan pilihan dengan benar");
                }
            }while (pilihan != 0);

        }

        catch (Exception e){
            System.out.println("masukkan data dengan benar");
        }

    }

    //pinjem buku
    static void pinjemBuku(){
        int pilihan = 0;

        System.out.println("-------------------");
        System.out.println("Pinjam Buku");
        System.out.println("-------------------");
        System.out.println("Mastikan nama anda terdaftar di user");

        try{
            //biar nggak nempel
            input = new Scanner(System.in);

            //nama depan
            System.out.print("Nama Depan    : ");
            String firstname = input.nextLine().trim();

            //biar nggak nempel
            input = new Scanner(System.in);

            //nama belakang
            System.out.print("Nama Belakang : ");
            String lastname = input.nextLine().trim();

            //biar nggak nempel
            input = new Scanner(System.in);

            //judul buku
            System.out.print("Judul Buku    : ");
            String title = input.nextLine().trim();

            //jumlah buku
            System.out.print("Jumlah Buku   : ");
            int amount = input.nextInt();

            //jumlah hari
            System.out.print("Jumlah Hari   : ");
            int rent_day = input.nextInt();

            //masukkan ke server
            rentBook.rentBook(firstname, lastname, title, amount, rent_day);

            do{
                System.out.println("-------------------");
                System.out.println("Jumlah buku dipinjam : " + rentBook.getAmountBookSementara());
                System.out.println("1. Tambah Buku");
                System.out.println("0. Selesai Peminjaman ( buku minimal 2 )");
                System.out.print("Silahkan Pilih Menu : ");
                pilihan = input.nextInt();
                System.out.println("-------------------");

                switch (pilihan) {
                    case 0:
                        rentBook.rentBookSelesai();
                        break;
                    case 1:
                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //judul buku
                        System.out.print("Judul Buku    : ");
                        title = input.nextLine().trim();

                        //jumlah buku
                        System.out.print("Jumlah Buku   : ");
                        amount = input.nextInt();

                        //jumlah hari
                        System.out.print("Jumlah Hari   : ");
                        rent_day = input.nextInt();

                        //masukkan ke server
                        rentBook.rentBook(firstname, lastname, title, amount, rent_day);
                    default:
                        System.out.println("masukkan pilihan dengan benar");
                }
            }while (pilihan != 0);
        }

        catch (Exception e){
            System.out.println("masukkan data dengan benar");
        }
    }

    //pengembalian buku
    static void pengembalianBuku(){
        int pilihan = 0;

        System.out.println("-------------------");
        System.out.println("Pengembalian Buku");
        System.out.println("-------------------");

        try{
            //biar nggak nempel
            input = new Scanner(System.in);

            //nama depan
            System.out.print("Nama Depan        : ");
            String firstname = input.nextLine().trim();

            //biar nggak nempel
            input = new Scanner(System.in);

            //nama belakang
            System.out.print("Nama Belakang     : ");
            String lastname = input.nextLine().trim();

            //biar nggak nempel
            input = new Scanner(System.in);

            //judul buku
            System.out.print("Judul Buku        : ");
            String title = input.nextLine().trim();

            //biar nggak nempel
            input = new Scanner(System.in);

            System.out.println("Masukkan ex: ( 2020-20-20 ) ");
            System.out.print("Tanggal pinjam    : ");
            String stringDate = input.nextLine().trim();

            //masukkan ke server
            rentBook.returnBook(firstname, lastname, title, stringDate);

            do{
                System.out.println("-------------------");
                System.out.println("1. Pengembalian kembali");
                System.out.println("0. Selesai Pengembalian ");
                System.out.print("Silahkan Pilih Menu : ");
                pilihan = input.nextInt();
                System.out.println("-------------------");

                switch (pilihan) {
                    case 0:
                        break;
                    case 1:
                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama depan
                        System.out.print("Nama Depan        : ");
                        firstname = input.nextLine().trim();

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama belakang
                        System.out.print("Nama Belakang     : ");
                        lastname = input.nextLine().trim();

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //judul buku
                        System.out.print("Judul Buku        : ");
                        title = input.nextLine().trim();

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        System.out.println("Masukkan ex: ( 2020-20-20 ) ");
                        System.out.print("Tanggal pinjam    : ");
                        stringDate = input.nextLine();

                        try{
                            //masukkan ke server
                            rentBook.returnBook(firstname, lastname, title, stringDate);
                        }

                        catch (Exception e){
                            System.out.println("masukkan data dengan benar");
                        }
                        break;

                    default:
                        System.out.println("masukkan pilihan dengan benar");
                }
            }while (pilihan != 0);

        }catch (Exception e){
            System.out.println("masukkan data dengan benar");
        }
    }

    //isi saldo
    static void isiSaldo() {
        System.out.println("-------------------");
        System.out.println("Isi Saldo");
        System.out.println("-------------------");

        try {
            //biar nggak nempel
            input = new Scanner(System.in);

            //nama depan
            System.out.print("Nama Depan        : ");
            String firstname = input.nextLine().trim();

            //biar nggak nempel
            input = new Scanner(System.in);

            //nama belakang
            System.out.print("Nama Belakang     : ");
            String lastname = input.nextLine().trim();

            //jumlah Saldo
            System.out.print("Jumlah Saldo      :");
            double saldo = input.nextDouble();

            try{
                userManage.addBalance(firstname, lastname, saldo);
            }

            catch (Exception e){
                System.out.println("masukkan data dengan benar");
            }

        } catch (Exception e) {
            System.out.println("masukkan data dengan benar");
        }
    }

    //pengaturan tentang user
    static void pengaturanUser(){
        int pilihan = 0;

        System.out.println("-------------------");
        System.out.println("Pengaturan User");


        try{
            do{
                System.out.println("-------------------");
                System.out.println("1. Update User");
                System.out.println("2. Delete User");
                System.out.println("3. Cari User");
                System.out.println("0. Selesai Menu");
                System.out.print("Silahkan Pilih Menu : ");
                pilihan = input.nextInt();

                switch (pilihan) {
                    case 0:
                        break;

                    //update user
                    case 1:
                        System.out.println("-------------------");
                        System.out.println("Update User");
                        System.out.println("-------------------");

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama depan
                        System.out.print("Nama Depan lama       : ");
                        String firstname = input.nextLine().trim();

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama belakang
                        System.out.print("Nama Belakang lama    : ");
                        String lastname = input.nextLine().trim();

                        //masukkan ke server
                        userManage.updateUser(firstname, lastname);

                        break;

                    //delete user
                    case 2 :
                        System.out.println("-------------------");
                        System.out.println("Delete User");
                        System.out.println("-------------------");

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama depan
                        System.out.print("Nama Depan            : ");
                        firstname = input.nextLine().trim();

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama belakang
                        System.out.print("Nama Belakang         : ");
                        lastname = input.nextLine().trim();

                        //masukkan ke server
                        userManage.deleteUser(firstname, lastname);

                        break;

                    case 3 :
                        System.out.println("-------------------");
                        System.out.println("Cari User");
                        System.out.println("-------------------");

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama depan
                        System.out.print("Nama Depan        : ");
                        firstname = input.nextLine().trim();

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama belakang
                        System.out.print("Nama Belakang     : ");
                        lastname = input.nextLine().trim();

                        //masukkan ke server
                        userManage.userInfo(firstname, lastname);
                        break;

                    default:
                        System.out.println("masukkan pilihan dengan benar");
                }
            }while (pilihan != 0);

        }

        catch (Exception e){
            System.out.println("masukkan data dengan benar");
        }

    }

    //pengaturan tentang Stock
    static void pengaturanStock(){
        int pilihan = 0;

        System.out.println("-------------------");
        System.out.println("Pengaturan Stock Buku");

        try{
            do{
                System.out.println("-------------------");
                System.out.println("1. List Buku");
                System.out.println("2. Update Info Buku");
                System.out.println("3. Update Total Buku");
                System.out.println("0. Selesai Menu");
                System.out.print("Silahkan Pilih Menu : ");
                pilihan = input.nextInt();

                switch (pilihan) {
                    case 0:
                        break;

                    //list buku
                    case 1:
                        System.out.println("-------------------");
                        System.out.println("List Buku");
                        System.out.println("-------------------");

                        bookStock.listBook();
                        break;

                    //update info buku
                    case 2 :
                        System.out.println("-------------------");
                        System.out.println("Update Info Buku");
                        System.out.println("-------------------");

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama depan
                        System.out.print("Judul Buku Lama       : ");
                        String title = input.nextLine().trim();

                        //masukkan ke server
                        bookStock.updateInfoBook(title);

                        break;

                    case 3:
                        System.out.println("-------------------");
                        System.out.println("Update Total Buku");
                        System.out.println("-------------------");

                        //biar nggak nempel
                        input = new Scanner(System.in);

                        //nama depan
                        System.out.print("Judul Buku        : ");
                        title = input.nextLine().trim();

                        //masukkan ke server
                        bookStock.updateTotalStockBook(title);
                        break;

                    default:
                        System.out.println("masukkan pilihan dengan benar");
                }
            }while (pilihan != 0);
        }

        catch (Exception e){
            System.out.println("masukkan data dengan benar");
        }
    }

    //pengaturan pendapatan
    static void pengaturanPendapatan(){
        int pilihan = 0;

        System.out.println("-------------------");
        System.out.println("Pengaturan Pendapatan");

        try{
            do{
                System.out.println("-------------------");
                System.out.println("1. List Pendapatan");
                System.out.println("2. Total Seluruh Pendapatan");
                System.out.println("0. Selesai Menu");
                System.out.print("Silahkan Pilih Menu : ");
                pilihan = input.nextInt();

                switch (pilihan) {
                    case 0:
                        break;

                    //list pendaatan
                    case 1:
                        System.out.println("-------------------");
                        System.out.println("List Pendapatan");
                        System.out.println("-------------------");

                        incomeManage.listPendapatan();
                        break;

                    //total pendapatan
                    case 2:
                        System.out.println("-------------------");
                        System.out.println("Total Pendapatan");
                        System.out.println("-------------------");

                        incomeManage.totalPendapatan();
                        break;

                    default:
                        System.out.println("masukkan pilihan dengan benar");
                }
            }while (pilihan != 0);
        }

        catch (Exception e){
            System.out.println("masukkan data dengan benar");
        }
    }
}
