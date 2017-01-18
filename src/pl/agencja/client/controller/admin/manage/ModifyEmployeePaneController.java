package pl.agencja.client.controller.admin.manage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.EmployeesPaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.customer.Employee;

public class ModifyEmployeePaneController implements Initializable
{
	@FXML
	private Button backModifyEmployeeButton;

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
	private Button backAddEmployeeButton;

	@FXML
	private Label userNameCheckLabel;

	@FXML
	private TextField checkUserNameTextField;

	@FXML
	private Label errorEmailAddressLabel;

	@FXML
	private Label errorFirstNameLabel;

	@FXML
	private Button checkUserNameButton;

	@FXML
	private Label errorLastNameLabel;

	@FXML
	private Button acceptButton;

	@FXML
	private CheckBox isAdminCheckBox;

	@FXML
	private Label nameLabel;

	@FXML
	private Pane contentPane;

	EmployeesPaneController employeesPaneController;
	Employee existingEmployee;
	boolean modifyEmployee;
	boolean firstNameCorrect;
	boolean lastNameCorrect;
	boolean emailAddressCorrect;

	private String userName;
	private String password;

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
		backModifyEmployeeButton.setOnAction(new EventHandler<ActionEvent>()
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

		checkUserNameButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				int klucz;
				existingEmployee = null;
				if (!checkUserNameTextField.getText().isEmpty())
				{
					if (isNumeric(checkUserNameTextField.getText()))
					{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("B³êdne nazwisko!");
						alert.setHeaderText(null);
						alert.setContentText("B³êdne nazwisko.\nSpróbuj ponownie");
						alert.showAndWait();

					} else
					{
						userName = checkUserNameTextField.getText();
						HibernateUtil.entityManager.getTransaction().begin();
						TypedQuery<Employee> query = HibernateUtil.entityManager
								.createQuery("SELECT e from Employee e WHERE e.userName = :userName", Employee.class);
						query.setParameter("userName", userName);

						try
						{
							existingEmployee = query.getSingleResult();

							klucz = existingEmployee.getIdEmployee();
							existingEmployee = HibernateUtil.entityManager.find(Employee.class, klucz);

						} catch (NoResultException nre)
						{
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Brak pracownika!");
							alert.setHeaderText(null);
							alert.setContentText(
									"Pracownik o podanej nazwie u¿ytkownika nie istnieje.\nSpróbuj ponownie");
							alert.showAndWait();
						} finally
						{
							HibernateUtil.entityManager.getTransaction().commit();
							
						}
					}
				}
				if (existingEmployee != null)
				{
					setData(existingEmployee);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Wybrano pracownika!");
					alert.setHeaderText(null);
					alert.setContentText(
							"Pracownik: " + checkUserNameTextField.getText() + " zosta³ wybrany do edycji!");
					alert.showAndWait();
				} else if (checkUserNameTextField.getText().equals(""))
				{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("B³êdne dane!");
					alert.setHeaderText(null);
					alert.setContentText("Podaj nazwê u¿ytkownika klienta!");
					alert.showAndWait();

				} 

			}

		});

		acceptButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				modifyEmployee = false;
				firstNameCorrect = false;
				lastNameCorrect = false;
				emailAddressCorrect = false;

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

				if (firstNameCorrect && lastNameCorrect && emailAddressCorrect)
					modifyEmployee = true;

				if (modifyEmployee && (!firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty()
						&& !emailAdressTextField.getText().isEmpty() && birthDatePicker.getValue() != null
						&& existingEmployee != null))
				{
					HibernateUtil.entityManager.getTransaction().begin();
					existingEmployee.setFirstName(firstNameTextField.getText());
					existingEmployee.setLastName(lastNameTextField.getText());
					existingEmployee.setEmailAddress(emailAdressTextField.getText());
					existingEmployee.setBirthDate(birthDatePicker.getValue());
					existingEmployee.setAdmin(isAdminCheckBox.isSelected());
					existingEmployee.setJoinDate(LocalDate.now());
					HibernateUtil.entityManager.getTransaction().commit();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Wykonano");
					alert.setHeaderText(null);
					alert.setContentText("Edycja klienta przebieg³a pomyœlnie");
					alert.showAndWait();
					setEmptyData();

				} else
				{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("B³¹d dodawania");
					alert.setHeaderText(null);
					alert.setContentText("B³¹d edycji. \nPopraw dane w formularzu!");
					alert.showAndWait();

				}
			}

		});
	}

	private static boolean isNumeric(String str)
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

	private void setData(Employee employee)
	{
		this.userName = employee.getUserName();
		this.password = employee.getPassword();
		isAdminCheckBox.setSelected(employee.isAdmin());
		firstNameTextField.setText(employee.getFirstName());
		lastNameTextField.setText(employee.getLastName());
		emailAdressTextField.setText(employee.getEmailAddress());
		birthDatePicker.setValue(employee.getBirthDate());
	}

	private void setEmptyData()
	{
		this.userName = "";
		this.password = "";
		isAdminCheckBox.setSelected(false);
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		emailAdressTextField.setText("");
		birthDatePicker.setValue(null);
	}
}
