import daomodel.UserDaoModel;
import model.*;

import java.sql.Date;
import java.util.Scanner;


public class Main {
    /*buat objek*/
    static UserManage userManage = new UserManage();
    static BookStock bookStock = new BookStock();
    static RentBook rentBook = new RentBook();
    static AdultIncome adultIncome = new AdultIncome();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        showMenu();
//        daftarMember();
//        pinjemBuku();
//        pengembalianBuku();

//        /*buku buku baru*/
//        bookStock.importNewBook("angker", "angker banget uhh", "horor", "deres sedu", 2015,20000,20);
//        bookStock.importNewBook("ayam", "ayam itu keren banget", "hewan", "agus otot", 2020,20000,20);
//        bookStock.importNewBook("ayam goreng", "ayammmmm", "Hewan", "jono", 2010, 20000, 20);

//        /*cek buku*/
//        bookStock.bookCheck("ayam");

//        /*delete book*/
//        bookStock.deleteBook("ayam");

        /*update book*/
//        bookStock.updateInfoBook("kecap");

        /*update stock book total*/
//        bookStock.plusStockBook("angker", 6);
//        bookStock.minusStockBook("angker", 6);

//        /*cek semua buku digudang*/
//        bookStock.listBook();

        /*buat user*/;
//        userManage.createUser("bambang","agus",12);
//        userManage.createUser("Bambang", "Dodo",10);
//        userManage.createUser("jono","tampan", 30);

//        /*deleteUser*/
//        userManage.deleteUser("jono", "tampan");
//        userManage.deleteUser("Bambang","Dodo");

        /*update user*/
//        userManage.updateUser("Bambang","Dodo");
//        userManage.updateUser("jono","tampan");

        /*cari buku*/
//        bookStock.searchBookGenre();
//        bookStock.searchBookTitle("wadaw");

        /*minjem buku*/
//        rentBook.rentBook("bambang", "agus","wadaw", 1, 2);
//        rentBook.rentBook("bambang","agus","angker",2,2);
//        rentBook.rentBookSelesai();

//        rentBook.rentBook("bambang","agus","wadaw",3,2);
//        rentBook.rentBookSelesai();

        //mencari total pendapatan
//        incomeManage.totalPendapatan();


        /*balikin buku*/
        //rentBook.bookRentCheck("Agus","Dodo","wadaw","2020-05-23");
        //rentBook.returnBook("Agus","Dodo","wadaw","2020-05-23");

        /*rent price*/
        //adultIncome.rentPrice("wadaw", "wadaw", "wadaw", 10000,10, 2);
    }

    static void showMenu(){
        int pilihan = 0;

        do{
            System.out.println("Selamat Datang di Pencatatan Perpustakaan");

            //menu
            System.out.println("-------------------");
            System.out.println("1. Daftar Member");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Pengembalian Buku");
            System.out.println("4. Isi saldo");
            System.out.println("5. Pengaturan User");
            System.out.println("6. Pengaturan Stock Buku");
            System.out.println("7. Pengaturan Pendapatan");
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
                case 2:
                    pinjemBuku();
                    break;
                case 3:
                    pengembalianBuku();
                    break;
                case 4:
                    System.out.println("wadaw 4");
                    break;
                default:
                    System.out.println("Pilihan salah!");
            }

        }while (pilihan != 0);
    }

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
        }catch (Exception e){
            System.out.println("masukkan data dengan benar");
        }
    }

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

                        //masukkan ke server
                        rentBook.returnBook(firstname, lastname, title, stringDate);
                    default:
                        System.out.println("masukkan pilihan dengan benar");
                }
            }while (pilihan != 0);
        }catch (Exception e){
            System.out.println("masukkan data dengan benar");
        }
    }
}
