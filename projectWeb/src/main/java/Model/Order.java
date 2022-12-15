/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author USER
 */
public class Order extends Product{
    private int orderId ;
    private String mid;
    private int number;
    private String date;

    public Order() {
    }
    
    

    public Order(int orderId, String mid, int number, String date) {
        this.orderId = orderId;
        this.mid = mid;
        this.number = number;
        this.date = date;
    }

    public Order(String mid, int number, String date) {
        this.mid = mid;
        this.number = number;
        this.date = date;
    }

    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
