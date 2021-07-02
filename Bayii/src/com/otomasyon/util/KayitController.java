package com.otomasyon.util;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class KayitController {
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfid;

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField tfPass;

    @FXML
    private TextField tfYetki;

    @FXML
    private TableView<Kayitlar> tvKayitlar;

    @FXML
    private TableColumn<Kayitlar, Integer> colid;

    @FXML
    private TableColumn<Kayitlar, String> colusername;

    @FXML
    private TableColumn<Kayitlar, String> colpass;

    @FXML
    private TableColumn<Kayitlar, Integer> colyetki;

    
    @FXML
    private Button btnEkle;

    @FXML
    private Button btnGuncelle;

    @FXML
    private Button btnSil;

    @FXML
    void btnEkle_Click(ActionEvent event) {

    	if(event.getSource() == btnEkle){
            insertRecord();    	
    }
    	
    }

    @FXML
    void btnGuncelle_Click(ActionEvent event) {

    	if(event.getSource() == btnGuncelle){
            updateRecord(); 	
    	}
    	
    }

    @FXML
    void btnSil_Click(ActionEvent event) {

    	if(event.getSource() == btnSil){
            deleteRecord(); 	
    	}
    	
    }
    
    @FXML
    void tvKayitlar_Pressed(MouseEvent event) {

    	Kayitlar sec = tvKayitlar.getSelectionModel().getSelectedItem();
    	
    	tfid.setText("" + sec.getmemberID());
    	tfUsername.setText(sec.getUser_name());
    	tfPass.setText(sec.getPassword());
    	tfYetki.setText("" + sec.getAuthority());
    	
    	
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
    
    public ObservableList<Kayitlar>getKayitList(){
    	ObservableList<Kayitlar>kayitList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String sorgu = "SELECT * FROM members";
    	Statement st;
    	ResultSet rs;
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(sorgu);
			Kayitlar kayit_islemleri;
			while(rs.next()) {
				kayit_islemleri = new Kayitlar(rs.getInt("memberID"), rs.getString("user_name"), rs.getString("password"), rs.getInt("authority"));
				kayitList.add(kayit_islemleri);				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return kayitList;
    }
    
    public void kayitGoster() {
    	ObservableList<Kayitlar>list = getKayitList();
    	
    	colid.setCellValueFactory(new PropertyValueFactory<Kayitlar, Integer>("memberID"));
    	colusername.setCellValueFactory(new PropertyValueFactory<Kayitlar, String>("user_name"));
    	colpass.setCellValueFactory(new PropertyValueFactory<Kayitlar, String>("password"));
    	colyetki.setCellValueFactory(new PropertyValueFactory<Kayitlar, Integer>("authority"));
    	
    	tvKayitlar.setItems(list);
    }
    
    
private void insertRecord() {
    	
	String sorgu = "INSERT INTO members VALUES(" + tfid.getText() + ",'" + tfUsername.getText()
	+ "','" + tfPass.getText().valueOf(MD5Generator(tfPass.getText())) + "'," + tfYetki.getText() + ")";
    	
    	executeQuery(sorgu);
    	kayitGoster();
    }
    
    private void updateRecord(){
    	String sorgu = "UPDATE  members SET user_name  = '" + tfUsername.getText() + "', password = '" + tfPass.getText().valueOf(MD5Generator(tfPass.getText())) + "', authority = "
                 + tfYetki.getText() +  " WHERE memberID = " + tfid.getText() + "";
       
        executeQuery(sorgu);
        kayitGoster();
    }
    
    private void deleteRecord(){
        String sorgu = "DELETE FROM members WHERE memberID =" + tfid.getText() + "";
        executeQuery(sorgu);
        kayitGoster();
    }
    

	public static String MD5Generator(String pass) {
		try {
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] encrypted = md5.digest(pass.getBytes());
			BigInteger no = new BigInteger(1, encrypted);
			String hashPass = no.toString(16);
			while(hashPass.length() < 32) {
				hashPass = "0" + hashPass;
			}
			return hashPass;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
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
    
    @FXML
    void initialize() {
       kayitGoster();
    }
}
