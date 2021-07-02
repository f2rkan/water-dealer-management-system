package com.otomasyon.util;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import javafx.scene.input.MouseEvent;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfUser;

    @FXML
    private TextField tdType;

    @FXML
    private TextField tfMethod;

    @FXML
    private TextField tfPrice;

    @FXML
    private DatePicker dp_Satis;

    @FXML
    private TableView<satislar> tvSatis;

    @FXML
    private TableColumn<satislar, Integer> colId;

    @FXML
    private TableColumn<satislar, String> colUser;

    @FXML
    private TableColumn<satislar, Integer> colType;

    @FXML
    private TableColumn<satislar, String> colMethod;

    @FXML
    private TableColumn<satislar, Double> colPrice;

    @FXML
    private TableColumn<satislar, Date> colDate;

    @FXML
    private Button btnEkle;

    @FXML
    private Button btnGuncelle;

    @FXML
    private Button btnSil;
    @FXML
    void tb_Clicked(MouseEvent event) {
    	
    	satislar sec = tvSatis.getSelectionModel().getSelectedItem();
    	
    	tfId.setText("" + sec.getSalesID());
    	tfUser.setText(sec.getUser());
    	tdType.setText("" + sec.getType());
    	tfMethod.setText(sec.getAbout_selling());
    	tfPrice.setText("" + sec.getAbout_price());
    	
    	dp_Satis.setValue(sec.getAbout_date().toLocalDate());
    }
    @FXML
    void btnEkle_Action(ActionEvent event) {

    	if(event.getSource() == btnEkle){
            insertRecord();    	
    }
}

    @FXML
    void btnGuncelle_Action(ActionEvent event) {

    	if(event.getSource() == btnGuncelle){
            updateRecord(); 	
    	}
    }

    @FXML
    void btnSil_Action(ActionEvent event) {

    	if(event.getSource() == btnSil){
            deleteRecord(); 	
    	}
    	
    }
    
    public Connection getConnection() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://localhost/otomasyon", "root", "mysql");
    		return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
    }

    public ObservableList<satislar>getSatisList(){
    	ObservableList<satislar>satisList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String sorgu = "SELECT * FROM sales";
    	Statement st;
    	ResultSet rs;
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(sorgu);
			satislar satis_islemleri;
			while(rs.next()) {
				satis_islemleri = new satislar(rs.getInt("salesID"), rs.getInt("type"), rs.getString("user"), rs.getString("about_selling"), rs.getDouble("about_price"), rs.getDate("about_date"));
				satisList.add(satis_islemleri);				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return satisList;
    }
    
    
    public void satisGoster() {
    	ObservableList<satislar>list = getSatisList();
    	
    	colId.setCellValueFactory(new PropertyValueFactory<satislar, Integer>("salesID"));
    	colUser.setCellValueFactory(new PropertyValueFactory<satislar, String>("user"));
    	colType.setCellValueFactory(new PropertyValueFactory<satislar, Integer>("type"));
    	colMethod.setCellValueFactory(new PropertyValueFactory<satislar, String>("about_selling"));
    	colPrice.setCellValueFactory(new PropertyValueFactory<satislar, Double>("about_price"));
    	colDate.setCellValueFactory(new PropertyValueFactory<satislar, Date>("about_date"));
    	
    	tvSatis.setItems(list);
    }

    private void executeQuery(String sorgu) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(sorgu);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void insertRecord() {
    	
    	String sorgu = "INSERT INTO sales VALUES(" + tfId.getText() + ",'" + tfUser.getText()
    	+ "'," + tdType.getText() + ",'" + tfMethod.getText() + "'," + tfPrice.getText() + ",'" + dp_Satis.getValue() + "')";
    	
    	executeQuery(sorgu);
    	satisGoster();
    }
    
    private void updateRecord(){
        String sorgu = "UPDATE  sales SET user  = '" + tfUser.getText() + "', type = " + tdType.getText() + ", about_selling = '" +
                tfMethod.getText() + "', about_price = " + tfPrice.getText() + ", about_date = '" + dp_Satis.getValue() + "' WHERE salesID = " + tfId.getText() + "";
        executeQuery(sorgu);
        satisGoster();
    }
    
    private void deleteRecord(){
        String sorgu = "DELETE FROM sales WHERE salesID =" + tfId.getText() + "";
        executeQuery(sorgu);
        satisGoster();
    }
    
    @FXML
    void initialize() {
    	
        satisGoster();
    
    }
}
