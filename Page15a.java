/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

/**
 *
 * @author melro
 */
public class Page15a {
    String trainno,arrivaltime,departuretime,ac1price,ac1seat,ac2price,ac2seat,nonacprice,nonacseat;

    public String getTrainno() {
        return trainno;
    }

    public void setTrainno(String trainno) {
        this.trainno = trainno;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getAc1price() {
        return ac1price;
    }

    public void setAc1price(String ac1price) {
        this.ac1price = ac1price;
    }

    public String getAc1seat() {
        return ac1seat;
    }

    public void setAc1seat(String ac1seat) {
        this.ac1seat = ac1seat;
    }

    public String getAc2price() {
        return ac2price;
    }

    public void setAc2price(String ac2price) {
        this.ac2price = ac2price;
    }

    public String getAc2seat() {
        return ac2seat;
    }

    public void setAc2seat(String ac2seat) {
        this.ac2seat = ac2seat;
    }

    public String getNonacprice() {
        return nonacprice;
    }

    public void setNonacprice(String nonacprice) {
        this.nonacprice = nonacprice;
    }

    public String getNonacseat() {
        return nonacseat;
    }

    public void setNonacseat(String nonacseat) {
        this.nonacseat = nonacseat;
    }

    public Page15a(String trainno, String arrivaltime, String departuretime, String ac1price, String ac1seat, String ac2price, String ac2seat, String nonacprice, String nonacseat) {
        this.trainno = trainno;
        this.arrivaltime = arrivaltime;
        this.departuretime = departuretime;
        this.ac1price = ac1price;
        this.ac1seat = ac1seat;
        this.ac2price = ac2price;
        this.ac2seat = ac2seat;
        this.nonacprice = nonacprice;
        this.nonacseat = nonacseat;
    }
    
}
