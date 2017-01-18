package pl.agencja.client.controller.admin.manage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.EmployeesPaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.customer.Employee;

public class AddEmployeePaneController implements Initializable
{
	@FXML
	private Button backAddEmployeeButton;

	@FXML
	private Label errorBirthDateLabel;

	@FXML
	private TextField emailAdressTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private DatePicker birthDatePicker;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private Label errorEmailAddressLabel;

	@FXML
	private Label errorFirstNameLabel;

	@FXML
	private Label errorLastNameLabel;

	@FXML
	private Button acceptButton;

	@FXML
	private Label nameLabel;

	@FXML
	private Label errorRepeatPasswordLabel;

	@FXML
	private Label errorUserNameLabel;

	@FXML
	private Label errorPasswordLabel;

	@FXML
	private PasswordField repeatPasswordField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField userNameTextField;

	@FXML
	private CheckBox isAdminCheckBox;

	@FXML
	private Pane contentPane;

	EmployeesPaneController employeesPaneController;

	Employee employee;
	boolean addClient;
	boolean emailAddressCorrect;
	boolean firstNameCorrect;
	boolean lastNameCorrect;
	boolean userNameCorrect;
	boolean passwordCorrect;
	boolean repeatPasswordCorrect;

	public void setContentPane(Pane contentPane)
	{
		this.contentPane = contentPane;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		configureButtons();
	}

	private void configureButtons()
	{
		backAddEmployeeButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/pl/agencja/client/view/menu/EmployeesPane.fxml"));
					loader.setController(new EmployeesPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					employeesPaneController = (EmployeesPaneController) loader.getController();
					employeesPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		acceptButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				addClient = false;
				emailAddressCorrect = false;
				firstNameCorrect = false;
				lastNameCorrect = false;
				userNameCorrect = false;
				passwordCorrect = false;
				repeatPasswordCorrect = false;

				if (firstNameTextField.getText().equals(""))
				{

					errorFirstNameLabel.setText("Podaj imiê!");
				} else if (isNumeric(firstNameTextField.getText()))
				{
					errorFirstNameLabel.setText("Nieprawid³owa wartoœæ!");
				} else
				{
					firstNameCorrect = true;
					errorFirstNameLabel.setText("");
				}

				if (lastNameTextField.getText().equals(""))
				{
					errorLastNameLabel.setText("Podaj nazwisko!");
				} else if (isNumeric(lastNameTextField.getText()))
				{
					errorLastNameLabel.setText("Nieprawid³owa wartoœæ!");
				} else
				{
					lastNameCorrect = true;
					errorLastNameLabel.setText("");
				}

				if (emailAdressTextField.getText().equals(""))
				{
					errorEmailAddressLabel.setText("Podaj adres!");
				} else
				{
					emailAddressCorrect = true;
					errorEmailAddressLabel.setText("");
				}
				if (birthDatePicker.getValue() == null)
				{
					errorBirthDateLabel.setText("Wybierz date!");
				} else
				{
					errorBirthDateLabel.setText("");
				}

				if (userNameTextField.getText().equals(""))
				{
					errorUserNameLabel.setText("Podaj login!");
				} else
				{
					userNameCorrect = true;
					errorUserNameLabel.setText("");
				}
				if (passwordField.getText().equals(""))
				{
					errorPasswordLabel.setText("Podaj has³o!");
				} else
				{
					passwordCorrect = true;
					errorPasswordLabel.setText("");
				}
				if (repeatPasswordField.getText().equals(""))
				{
					errorRepeatPasswordLabel.setText("Podaj has³o!");
				} else if (!passwordField.getText().equals(repeatPasswordField.getText()))
				{
					errorRepeatPasswordLabel.setText("B³êdne has³o");
				} else
				{
					repeatPasswordCorrect = true;
					errorRepeatPasswordLabel.setText("");
				}

				if (firstNameCorrect && lastNameCorrect && emailAddressCorrect && userNameCorrect && passwordCorrect
						&& repeatPasswordCorrect)
					addClient = true;

				if (addClient
						&& (!firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty()
								&& !emailAdressTextField.getText().isEmpty() && birthDatePicker.getValue() != null)
						&& !userNameTextField.getText().isEmpty() && !passwordField.getText().isEmpty())
				{

					employee = new Employee();
					employee.setFirstName(firstNameTextField.getText());
					employee.setLastName(lastNameTextField.getText());
					employee.setEmailAddress(emailAdressTextField.getText());
					employee.setBirthDate(birthDatePicker.getValue());
					employee.setAdmin(isAdminCheckBox.isSelected());
					employee.setUserName(userNameTextField.getText());
					employee.setPassword(passwordField.getText());
					employee.setJoinDate(LocalDate.now());

					if (employee.isAdmin())
					{
						employee.setAdministrator("Posiada");
					} else
					{
						employee.setAdministrator("Nie posiada");
					}

					HibernateUtil.entityManager.getTransaction().begin();
					HibernateUtil.entityManager.persist(employee);
					HibernateUtil.entityManager.getTransaction().commit();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Dodawanie pracownika");
					alert.setHeaderText(null);
					alert.setContentText("Dodanie pracownika zakoñczy³o siê sukcesem.\n");
					alert.showAndWait();

					firstNameTextField.setText("");
					lastNameTextField.setText("");
					emailAdressTextField.setText("");
					birthDatePicker.setValue(null);
					userNameTextField.setText("");
					passwordField.setText("");
					repeatPasswordField.setText("");
					isAdminCheckBox.setSelected(false);

				} else
				{

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("B³¹d dodawania");
					alert.setHeaderText(null);
					alert.setContentText("Nie mo¿na dodaæ klienta. \nPopraw dane w formularzu!");
					alert.showAndWait();

				}
			}
		});

	}

	public static boolean isNumeric(String str)
	{
		try
		{
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe)
		{
			return false;
		}
		return true;
	}
}
