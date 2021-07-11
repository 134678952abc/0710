package com.gff.service;

import com.gff.uilt.TestUtil;
import com.gff.zy.Customer;
import com.gff.zy.CustomerData;

import java.util.List;
import java.util.Scanner;

public class CustomerService {
    private List<Customer> customerList;
    private Customer currentCustomer;
    private Customer cunkuanCustomer;
    private Customer qukuanCustomer;

    // 1. 查, 登录 判断账号密码是否正确
    public void  checkPwd(String cardid, String  cardPwd){
        CustomerData customerData = CustomerData.getInstance();
        customerList  = customerData.getCustomerList();
        // 1. 拿到  carid中的账户名 和 cardPwd  对  list中的数据做对比
        System.out.println("cardid = " + cardid);
        System.out.println("cardPwd = " + cardPwd);

        for (Customer customer : customerList) {
            if (customer.getAccount().equals(cardid) && customer.getPassword().equals(cardPwd)){
                // 拿出来这个人
                currentCustomer=  customer;
                // 账户正确
                System.out.println("欢迎"+customer.getCname()+"顾客登录!请注意您的安全"   );
                TestUtil.oneLeveOption();  // 界面
                Scanner scanner = new Scanner(System.in);
                String option = scanner.nextLine();
                oneOption(option);
                // 如何 保证 按了 1 3 5... 让他再此回到 此界面呢 ?
            }
        }
    }

    private void oneOption(String option) {
        switch (option) {
            case "1":
                System.out.println("余额查询");
                // 查询余额
                doSelectMoney();
                // 返回到选择界面
                goOneHome();
                break;
            case "2":
                System.out.println("取款");
                doGetMoney();
                goOneHome();
                break;
            case "3":
                System.out.println("转账");
                goOneHome();
                break;
            case "4":
                doSaveMoney();
                System.out.println("存款");
                goOneHome();
                break;
            case "5":
                System.out.println("退卡");
                goOneHome();
                break;

            default:
                System.out.println("你输入的数字有误");
                goOneHome();
                break;
        }
    }

    // 返回一级菜单
    private void goOneHome() {
        TestUtil.oneLeveOption();
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        System.out.println("option1 = " + option);
        oneOption(option);  //递归算法
    }


    // 查询余额
    private void doSelectMoney() {
        double money = currentCustomer.getMoney();
        System.out.println("余额是 " + money);
    }

    // 取款
    private void doGetMoney() {
        Scanner scanner = new Scanner(System.in);
        double money = scanner.nextDouble();
        qukuanCustomer.setMoney(qukuanCustomer.getMoney() - money);
        System.out.println("你当前的余额为：" + qukuanCustomer.getMoney());
    }

    //  存款
    private void doSaveMoney() {
        System.out.println("请输入要存款的金额");
        // 输入存款金额
        Scanner sc = new Scanner(System.in);
        try {
            double money = sc.nextDouble();
            cunkuanCustomer.setMoney(cunkuanCustomer.getMoney() + money);
            System.out.println("你当前的余额为：" + cunkuanCustomer.getMoney());
        } catch (Exception e) {
            System.out.println("输入的金额不合法，请重新输入");
            doSaveMoney();
        }
    }


}

