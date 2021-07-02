package com.otomasyon.util;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

import java.sql.*;
public class LoginController {

	
	public LoginController() {
		con = ConnectionUtil.conDB();
	}
	
	Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField tfPass;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblError;
    
    @FXML
    private Button btnForget;

    @FXML
    private Button btnAdd;

    
    @FXML
    void btnAdd_Click(ActionEvent event) {
    	
    	if(event.getSource() == btnAdd) {
    		
    			try {
					Node node = (Node) event.getSource();
					Stage stage = (Stage) node.getScene().getWindow();
					stage.close();
					
					Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Kayit.fxml")));
					stage.setScene(scene);
					stage.show();
				} catch (Exception e) {
					System.err.println(e.getMessage());
			}
    	}    	
    }

    @FXML
    void btnForget_Click(ActionEvent event) {
    	showDialog("Unutmaman Gerekiyordu..", "HATA", "ÞÝFREMÝ UNUTTUM");
    }

    @FXML
    void btnLogin_Action(ActionEvent event) {

    	if(event.getSource() == btnLogin) {
    		if(login().equals("basarili")) {
    		
    			try {
					Node node = (Node) event.getSource();
					Stage stage = (Stage) node.getScene().getWindow();
					stage.close();
					
					Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Sample.fxml")));
					stage.setScene(scene);
					stage.show();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}    				
    		}
    	}
    }
    

    @FXML
    void initialize() {
       
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
    
     
    private String login() {
    	String user_name = tfUsername.getText().toString();
    	String password = tfPass.getText().toString();
    	
    	
    	String sorgu = "SELECT * FROM members WHERE user_name = ? and password = ?";
    	try {
			preparedStatement = con.prepareStatement(sorgu);			
			preparedStatement.setString(1, user_name);
			preparedStatement.setString(2, password.valueOf(MD5Generator(password)));
			
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				lblError.setTextFill(Color.TOMATO);
				lblError.setText("Geçerli Bir Giriþ Yap");
				return "error";
			}
			else {
				lblError.setTextFill(Color.GREEN);
				lblError.setText("Giriþ Baþarýlý");
				return "basarili";
			}			
		} catch (SQLException e) {
			
			System.err.println(e.getMessage());
			return "exception";
		}
    }
    
    private void showDialog(String info, String header, String title) {
    	
    	Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText(info);
		alert.setHeaderText(header);
		alert.showAndWait();	
    }
}
