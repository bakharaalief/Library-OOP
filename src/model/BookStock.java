package model;

import daomodel.BookDaoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookStock {
    private ArrayList<Book> listBuku;
    private BookDaoModel bookDaoModel;
    private Book bookData;
    private boolean ditemukan;
    private Scanner input = new Scanner(System.in);

    public BookStock(){
        bookDaoModel = new BookDaoModel();
        ditemukan = false;
    }

    //mengecek buku yang ada di server
    public void bookCheck(String titleInput){
        //merefresh data di database
        listBuku = bookDaoModel.getAll();

        //looping untuk mencari user
        for(int i = 0; i < listBuku.size(); i++){
            Book data = listBuku.get(i);
            String title = data.getTitle().toLowerCase();

            //ketika title ditemukan
            if( title.equals(titleInput.toLowerCase())){
                ditemukan = true;
                bookData = data;
                break;
            }
            else{
                ditemukan = false;
            }
        }
    }

    //import buku baru
    public void importNewBook(String titleInput, String synopsisInput, String genreInput, String writeInput, int publisedInput, double priceInput, int amountInput){
        //mengecek apakah stock buku sudah tersedia
        bookCheck(titleInput);

        //jika ada
        if(ditemukan){
            System.out.println("maaf buku tidak bisa diimport karena sudah tersedia");
        }

        //jika belum
        else{
            String title = titleInput;
            String sinopsis = synopsisInput;
            String genre = genreInput;
            String writer = writeInput;
            int publised = publisedInput;
            double price = priceInput;
            int amount = amountInput;

            //membuat data buku baru
            Book data = new Book(title, sinopsis, genre, writer, publised, price, amount);

            try{
                //membuat di dalam server
                bookDaoModel.create(data);
            }

            catch (Exception e){
                System.out.println("maaf membuat buku baru gagal");
            }
        }
    }

    //update buku
    public void updateInfoBook(String titleInput){
        //melakukan check
        bookCheck(titleInput);

        //jika ditemukan
        if(ditemukan){
            int id = bookData.getId();
            System.out.print("Judul baru : ");
            String newTitle = input.nextLine();

            //agar tidak nempel
            input = new Scanner(System.in);

            System.out.print("Sinopsis baru : ");
            String newSynopsis = input.nextLine();

            //agar tidak nempel
            input = new Scanner(System.in);

            System.out.print("Genre baru : ");
            String newGenre = input.nextLine();

            //agar tidak nempel
            input = new Scanner(System.in);

            System.out.print("Penulis baru : ");
            String newWriter = input.nextLine();

            System.out.print("Tanggal penerbit baru : ");
            int newPublished = input.nextInt();
            System.out.print("Harga baru : ");
            double newPrice = input.nextDouble();

            //data baru untuk diset
            Book dataBaru = new Book(id, newTitle, newSynopsis, newGenre, newWriter, newPublished, newPrice);

            try{
                bookDaoModel.update(dataBaru);
            }
            catch (Exception e){
                System.out.println("Maaf memperbarui buku gagal");
            }
        }

        else{
            System.out.println("maaf memperbarui data gagal karena data tidak ditemukan");
        }
    }

    //update stock buku
    public void updateTotalStockBook(String titleInput){
        //mencek buku ada atau tidak
        bookCheck(titleInput);

        if(ditemukan){
            int id = bookData.getId();
            System.out.print("Stok total baru : ");
            int newAmount = input.nextInt();

            Book dataBaru = new Book(id, newAmount);

            try{
                bookDaoModel.updateStock(dataBaru);
            }
            catch (Exception e){
                System.out.println("Maaf memperbarui buku gagal");
            }
        }

        else{
            System.out.println("Maaf perubahan data gagal karena data tidak ditemukan");
        }
    }

    //menambah stock book karena pengembalian
    public void plusStockBook(String titleInput, int amountInput){
        bookCheck(titleInput);

        if(ditemukan){
            int id = bookData.getId();
            int newAmount = bookData.getAmount() + amountInput;

            Book dataBaru = new Book(id, newAmount);

            try{
                bookDaoModel.updateStock(dataBaru);
            }
            catch (Exception e){
                System.out.println("Maaf memperbarui buku gagal");
            }
        }

        else{
            System.out.println("Maaf perubahan data gagal karena data tidak ditemukan");
        }
    }

    //minus stock book karena dipinjam
    public void minusStockBook(String titleInput, int amountInput){
        bookCheck(titleInput);

        if(ditemukan){
            int id = bookData.getId();
            int newAmount = bookData.getAmount() - amountInput;

            Book dataBaru = new Book(id, newAmount);

            try{
                bookDaoModel.updateStock(dataBaru);
            }
            catch (Exception e){
                System.out.println("Maaf memperbarui buku gagal");
            }
        }

        else{
            System.out.println("Maaf perubahan data gagal karena data tidak ditemukan");
        }
    }

    //delete buku
    public void deleteBook (String titleInput){
        //mencek dulu
        bookCheck(titleInput);

        //jika data ada
        if(ditemukan) {
            try {
                bookDaoModel.delete(bookData);
                ditemukan = false;
            }

            catch (Exception e) {
                System.out.println("Maaf buku tidak berhasil dihapus");
            }
        }

        //jika nggak
        else{
            System.out.println("Maaf buku tidak berhasil dihapus karena data tidak ditemukan");
        }
    }

    //cari buku dengan title
    public void searchBookTitle(String titleInput){
        //refresh database
        listBuku = bookDaoModel.getAll();

        for (int i = 0; i < listBuku.size(); i++){
            String title_data = listBuku.get(i).getTitle().toLowerCase();

            if(title_data.equals(titleInput.toLowerCase())){
                Book data = listBuku.get(i);

                System.out.println("Judul       : " + data.getTitle());
                System.out.println("Sinopsis    : " + data.getSynopsis());
                System.out.println("Genre       : " + data.getGenre());
                System.out.println("Writer      : " + data.getWriter());
                System.out.println("Published   : " + data.getPublished());
                System.out.println("Price       : " + data.getPrice());
                System.out.println("Amount      : " + data.getAmount());
            }
        }
    }

    //cari buku dengan genre
    public void searchBookGenre(){
        //refresh database
        listBuku = bookDaoModel.getAll();

        System.out.println("Daftar genre : ");
        for (int i = 0; i < listBuku.size(); i++){
            int number = i + 1;

            System.out.println(number + " " + listBuku.get(i).getGenre() );
        }

        System.out.print("Masukkan nomor genre : ");
        int number = input.nextInt();
        int index = number - 1;

        //genre yang ditemukan
        String genre = listBuku.get(index).getGenre().toLowerCase();

        System.out.println("Judul--penulis--terbit--harga--stock");
        System.out.println("------------------------------------");
        for (int i = 0; i < listBuku.size(); i++){

            //jika genre sama
            if(listBuku.get(i).getGenre().toLowerCase().equals(genre)){
                Book data = listBuku.get(i);

                System.out.println(data.getTitle() + "--" + data.getWriter() + "--" + data.getPublished() + "--" + data.getPrice() + "--" + data.getAmount());
            }
        }
    }


    //list buku
    public void listBook(){
        //refresh database
        listBuku = bookDaoModel.getAll();

        System.out.println("No -- Judul Buku -- Genre -- Writer -- Terbit -- Harga -- stock");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < listBuku.size(); i++){
            int number = i + 1;
            Book dataBuku = listBuku.get(i);

            System.out.println(number + " -- " + dataBuku.getTitle() + " -- " + dataBuku.getGenre() + " -- " + dataBuku.getWriter() + " -- " + dataBuku.getPublished() + " -- " + dataBuku.getPrice() + " -- " + dataBuku.getAmount());
        }
    }

    //get ditemukan
    public boolean getBookDitemukan(){
        return ditemukan;
    }

    //get bookdata
    public Book getBookData(){
        return bookData;
    }
}
