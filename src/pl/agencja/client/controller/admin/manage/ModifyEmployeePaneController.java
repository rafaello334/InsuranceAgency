package pl.agencja.client.controller.admin.manage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.EmployeesPaneController;
import pl.agencja.client.model.customer.Employee;
import pl.agencja.client.model.customer.EmployeeCollection;

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
	private Label errorAgeLabel;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private Button backAddEmployeeButton;

	@FXML
	private Label userNameCheckLabel;

	@FXML
	private TextField ageTextField;

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
	Employee employee;
	boolean modifyEmployee;
	boolean ageCorrect;
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
				Iterator<Employee> iterator = EmployeeCollection.getEmployeeList().iterator();

				if (iterator.hasNext())
				{
					while (iterator.hasNext())
					{
						Employee employee = iterator.next();

						if (checkUserNameTextField.getText().equals(employee.getUserName()))
						{
							setData(employee);

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
							alert.setContentText("WprowadŸ dane do formularza!");
							alert.showAndWait();
						} else
						{
							setEmptyData();
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Brak pracownika!");
							alert.setHeaderText(null);
							alert.setContentText("Pracownik: " + checkUserNameTextField.getText() + " nie istnieje!");
							alert.showAndWait();
						}
					}
				} else
				{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Baza danych jest pusta!");
					alert.setHeaderText(null);
					alert.setContentText("Baza danych jest pusta!");
					alert.showAndWait();
				}

			}
		});

		acceptButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				ageCorrect = false;
				modifyEmployee = false;
				firstNameCorrect = false;
				lastNameCorrect = false;
				emailAddressCorrect = false;

				if (isNumeric(ageTextField.getText()))
				{
					ageCorrect = true;
					errorAgeLabel.setText("");
				} else if (ageTextField.getText().equals(""))
				{
					errorAgeLabel.setText("Podaj wiek!");
				} else
				{
					errorAgeLabel.setText("Nieprawid³owa wartoœæ");
				}

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

				if (ageCorrect && firstNameCorrect && lastNameCorrect && emailAddressCorrect)
					modifyEmployee = true;

				if (modifyEmployee && (!firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty()
						&& !ageTextField.getText().isEmpty() && !emailAdressTextField.getText().isEmpty()
						&& birthDatePicker.getValue() != null))
				{
					modifyEmployee(checkUserNameTextField.getText());

					setEmptyData();

				} else
				{
					System.out.println(modifyEmployee);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("B³¹d dodawania");
					alert.setHeaderText(null);
					alert.setContentText("B³¹d edycji. \nPopraw dane w formularzu!");
					alert.showAndWait();

				}
			}

		});
	}

	private void modifyEmployee(String userName)
	{
		ArrayList<Employee> arrayList = new ArrayList<>();

		for (Employee employee : EmployeeCollection.getEmployeeList())
		{
			arrayList.add(employee);
		}

		Iterator<Employee> iterator = arrayList.iterator();

		if (iterator.hasNext())
		{
			while (iterator.hasNext())
			{
				Employee employee = iterator.next();

				if (userName.equals(employee.getUserName()))
				{
					EmployeeCollection.getEmployeeList().remove(employee);
					EmployeeCollection.getEmployeeList()
							.add(new Employee(firstNameTextField.getText(), lastNameTextField.getText(),
									Integer.parseInt(ageTextField.getText()), emailAdressTextField.getText(),
									birthDatePicker.getValue(), this.userName, this.password,
									isAdminCheckBox.isSelected()));

				} else
				{
					setEmptyData();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Nie mo¿na zmodyfikowaæ!");
					alert.setHeaderText(null);
					alert.setContentText("Pracownik: " + userName + " nie istnieje w bazie danych");
					alert.showAndWait();
				}
			}
		} else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Baza danych jest pusta!");
			alert.setHeaderText(null);
			alert.setContentText("Baza danych jest pusta!");
			alert.showAndWait();
		}

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
		ageTextField.setText(String.valueOf(employee.getAge()));
		emailAdressTextField.setText(employee.getEmailAdress());
		birthDatePicker.setValue(employee.getBirthDate());
	}

	private void setEmptyData()
	{
		this.userName = "";
		this.password = "";
		isAdminCheckBox.setSelected(false);
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		ageTextField.setText("");
		emailAdressTextField.setText("");
		birthDatePicker.setValue(null);
	}
}
