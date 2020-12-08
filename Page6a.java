/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayproject;

import java.awt.event.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;

/**
 *
 * @author melro
 */
public class Page6a {
    String trainid,tname,availclass,stationname,ac1fare,ac2fare,nonac;
    

    public String getTrainid() {
        return trainid;
    }

    public void setTrainid(String trainid) {
        this.trainid = trainid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getAvailclass() {
        return availclass;
    }

    public void setAvailclass(String availclass) {
        this.availclass = availclass;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getAc1fare() {
        return ac1fare;
    }

    public void setAc1fare(String ac1fare) {
        this.ac1fare = ac1fare;
    }

    public String getAc2fare() {
        return ac2fare;
    }

    public void setAc2fare(String ac2fare) {
        this.ac2fare = ac2fare;
    }

    public String getNonac() {
        return nonac;
    }

    public void setNonac(String nonac) {
        this.nonac = nonac;
    }

    public Page6a(String trainid, String tname, String availclass, String stationname, String ac1fare, String ac2fare, String nonac) {
        this.trainid = trainid;
        this.tname = tname;
        this.availclass = availclass;
        this.stationname = stationname;
        this.ac1fare = ac1fare;
        this.ac2fare = ac2fare;
        this.nonac = nonac;
        //this.action = new Button("BOOK");
    }
    
}
