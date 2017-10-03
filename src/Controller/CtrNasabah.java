/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Nasabah;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author 200NS
 */
public class CtrNasabah {

    private List<Nasabah> pelanggan;

    public CtrNasabah() {
        this.pelanggan = new ArrayList<>();
    }

    public CtrNasabah(List<Nasabah> cust) {
        this.pelanggan = cust;
    }

    public List<Nasabah> getData() throws FileNotFoundException, IOException {

        File file = new File("H:/DataNasabah.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        List<Nasabah> people = new ArrayList<>();

        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] arrayNasabah = line.split(", ");

            double gaji = Double.parseDouble(arrayNasabah[7]);
            boolean bo = Boolean.valueOf(arrayNasabah[4]);
            int umur = Integer.parseInt(arrayNasabah[3]);

            Nasabah p = new Nasabah(arrayNasabah[0], arrayNasabah[1], arrayNasabah[2], bo, umur, arrayNasabah[5], arrayNasabah[6], gaji, arrayNasabah[8]);
            people.add(p);
        }
        return people;
    }

    public void tampilkanData(String fill, DefaultTableModel tbl) throws IOException {
        String a = fill;

        JTable table = new JTable(tbl);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<Nasabah> people = getData();

        Object[] row = new Object[4];
        for (Nasabah nas : people) {
            row[0] = nas.getId();
            row[1] = nas.getName();
            row[2] = nas.getPekerjaan();
            row[3] = nas.getTelp();
            model.addRow(row);
        }

    }

    public void Cari(String cari, DefaultTableModel tbl, String type) throws IOException {

        JTable table = new JTable(tbl);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<Nasabah> people = getData();
        if (type.equalsIgnoreCase("Nama")) {
            for (Nasabah nas : people) {
                if (cari.equalsIgnoreCase(nas.getName())) {
                    Object[] row = new Object[4];
                    row[0] = nas.getId();
                    row[1] = nas.getName();
                    row[2] = nas.getPekerjaan();
                    row[3] = nas.getTelp();
                    model.addRow(row);
                }
            }
        } else if (type.equalsIgnoreCase("ID")) {
            for (Nasabah nas : people) {
                if (cari.equalsIgnoreCase(nas.getId())) {
                    Object[] row = new Object[4];
                    row[0] = nas.getId();
                    row[1] = nas.getName();
                    row[2] = nas.getPekerjaan();
                    row[3] = nas.getTelp();
                    model.addRow(row);
                }
            }
        }
    }

    /**
     * @return the pelanggan
     */
    public List<Nasabah> getPelanggan() {
        return pelanggan;
    }

    /**
     * @param pelanggan the pelanggan to set
     */
    public void setPelanggan(List<Nasabah> pelanggan) {
        this.pelanggan = pelanggan;
    }

}
