package model;

import daomodel.RentIncomeDaoModel;

import java.util.ArrayList;

public class IncomeManage {
    protected ArrayList<Income> listIncome;
    protected BookStock bookStock;
    protected RentIncomeDaoModel rentIncomeDaoModel;
    private UserManage userManage;
    protected String firstname;
    protected String lastname;
    protected double totalIncome;
    protected double income;
    protected int totalBanyakBuku;
    protected double percent;
    protected double hasil;
    protected boolean terpenuhi;

    public IncomeManage(){
        listIncome = new ArrayList<>();
        bookStock = new BookStock();
        userManage = new UserManage();
        totalBanyakBuku = 0;
        percent = 0;
        rentIncomeDaoModel = new RentIncomeDaoModel();
    }

    public void cekSaldoCukup(String firstnameInput, String lastnameInpur){
        //untuk mengecek data user
        userManage.userCheck(firstnameInput, lastnameInpur);

        //untukk mengambil data user
        User data = userManage.getUserData();
        Double userBalance = data.getBalance();

        if(userBalance >= hasil){
            terpenuhi = true;
        }else{
            System.out.println("maaf saldo anda kurang untuk pinjam");
            terpenuhi = false;
        }

        System.out.println("user saldo : " + userBalance);
    }

    public double rentPrice(String titleInput, int amountInput, long rentDay) {
        //mengambil data buku
        bookStock.bookCheck(titleInput);
        Book data = bookStock.getBookData();
        double bookPrice = data.getPrice();
        int amount = amountInput;
        long day = rentDay;

        double rentCost = bookPrice * percent * amount * day ;
        return rentCost;
    }

    public void rentTotal(ArrayList<UserBook> listPinjam) {
        //mengambil data para array listPinjam
        for(int i = 0; i < listPinjam.size(); i++){
            UserBook data = listPinjam.get(i);
            int banyakBuku = data.getAmount();
            firstname = data.getFirstName();
            lastname = data.getLastName();
            String title = data.getTitle();
            int amount = data.getAmount();

            //menghitung perbedaan hari
            long rent_day = data.getRent_end().getTime() - data.getRent_start().getTime();
            int diffDays = (int) (rent_day / (24 * 60 * 60 * 1000)); //mengubah long ke int dalam bentuk hari

            double rentCost = rentPrice(title, amount, diffDays);
            System.out.println(title + "--" + amount + "--" + rentCost);

            totalBanyakBuku += banyakBuku;
            income += rentCost;
        }
    }

    public void totalPendapatan(){
        listIncome = rentIncomeDaoModel.getAll();

        for(int i = 0; i < listIncome.size(); i++){
            Income data = listIncome.get(i);

            totalIncome += data.getFinal_income();
        }

        System.out.println("total Pendapatan : " + totalIncome);
    }

    public boolean isTerpenuhi() {
        return terpenuhi;
    }
}
