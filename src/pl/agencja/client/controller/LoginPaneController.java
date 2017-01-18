package pl.agencja.client.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.customer.Employee;
import pl.agencja.client.model.users.Admin;

public class LoginPaneController implements Initializable
{
	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button loginButton;

	@FXML
	private ImageView mainLogoView;

	@FXML
	private TextField loginTextField;

	ArrayList<Employee> employeeList;
	Stage prevStage;
	AdminMenuPaneController adminMenuPaneController;
	EmployeeMenuPaneController employeeMenuPaneController;

	public void setPrevStage(Stage stage)
	{
		this.prevStage = stage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		System.out.println("Login Pane: " + this);

		configureButtons();

	}

	private void configureButtons()
	{
		loginButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				changeSceneToMainMenu(prevStage);
			}
		});

		passwordTextField.setOnKeyPressed(new EventHandler<KeyEvent>()
		{

			@Override
			public void handle(KeyEvent keyEvent)
			{
				if (keyEvent.getCode() == KeyCode.ENTER)
				{
					changeSceneToMainMenu(prevStage);
				}

			}
		});

		loginTextField.setOnKeyPressed(new EventHandler<KeyEvent>()
		{

			@Override
			public void handle(KeyEvent keyEvent)
			{
				if (keyEvent.getCode() == KeyCode.ENTER)
				{
					changeSceneToMainMenu(prevStage);
				}
			}
		});
	}

	private void changeSceneToMainMenu(Stage prevStage)
	{

		if ((loginTextField.getText().equals(Admin.getLogin())
				&& passwordTextField.getText().equals(Admin.getPassword())))
		{
			loginAdmin();
		} else if (checkEmployeeLoginAndPassword())
		{

		}

		else
		{
			try
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("B³êdne dane logowania");
				alert.setHeaderText(null);
				alert.setContentText("Nieprawid³owy login lub has³o! \nSpróbuj ponownie.");

				alert.showAndWait();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private boolean checkEmployeeLoginAndPassword()
	{
		HibernateUtil.entityManager.getTransaction().begin();
		employeeList = (ArrayList<Employee>) HibernateUtil.entityManager.createQuery("from Employee").getResultList();
		HibernateUtil.entityManager.getTransaction().commit();

		for (Employee employee : employeeList)
		{
			if ((employee.getUserName().equals(loginTextField.getText())
					&& employee.getPassword().equals(passwordTextField.getText()) && employee.isAdmin()))
			{
				loginAdmin();
				return true;
			} else if ((employee.getUserName().equals(loginTextField.getText())
					&& employee.getPassword().equals(passwordTextField.getText())))
			{
				loginEmployee();
				return true;
			}

		}
		return false;
	}

	private void loginAdmin()
	{
		try
		{
			System.out.println("Zalogowano");

			Stage primaryStage = new Stage();
			primaryStage.setTitle("Menu G³ówne");
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/pl/agencja/client/view/menu/AdminMenuPane.fxml"));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			prevStage.close();
			primaryStage.show();

			primaryStage.setOnCloseRequest((WindowEvent we) -> {
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.setTitle("Ostrze¿enie");
				a.setHeaderText("Czy na pewno chcesz zakoñczyæ program?");
				a.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK)
					{
						Platform.exit();
					} else
					{
						we.consume();
					}
				});
			});

			adminMenuPaneController = (AdminMenuPaneController) loader.getController();
			adminMenuPaneController.setPrevStage(primaryStage);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void loginEmployee()
	{
		try
		{
			System.out.println("Zalogowano");

			Stage primaryStage = new Stage();
			primaryStage.setTitle("Menu G³ówne");
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/pl/agencja/client/view/menu/EmployeeMenuPane.fxml"));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			prevStage.close();
			primaryStage.show();

			primaryStage.setOnCloseRequest((WindowEvent we) -> {
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.setTitle("Ostrze¿enie");
				a.setHeaderText("Czy na pewno chcesz zakoñczyæ program?");
				a.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK)
					{
						Platform.exit();
					} else
					{
						we.consume();
					}
				});
			});

			employeeMenuPaneController = (EmployeeMenuPaneController) loader.getController();
			employeeMenuPaneController.setPrevStage(primaryStage);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
