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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.EmployeesPaneController;
import pl.agencja.client.model.customer.Employee;
import pl.agencja.client.model.customer.EmployeeCollection;

public class DeleteEmployeePaneController implements Initializable
{
	@FXML
	private Button backDeleteEmployeeButton;

	@FXML
	private Label errorFirstNameLabel;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private Label errorLastNameLabel;

	@FXML
	private Button acceptButton;

	@FXML
	private Label nameLabel;

	@FXML
	private Pane contentPane;

	EmployeesPaneController employeesPaneController;

	boolean deleteClient;
	boolean firstNameCorrect;
	boolean lastNameCorrect;

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
		backDeleteEmployeeButton.setOnAction(new EventHandler<ActionEvent>()
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
				firstNameCorrect = false;
				lastNameCorrect = false;

				if (firstNameTextField.getText().equals(""))
				{

					errorFirstNameLabel.setText("Podaj imiê!");
				} else
				{
					firstNameCorrect = true;
					errorFirstNameLabel.setText("");
				}

				if (lastNameTextField.getText().equals(""))
				{
					errorLastNameLabel.setText("Podaj nazwisko!");
				} else
				{
					lastNameCorrect = true;
					errorLastNameLabel.setText("");
				}

				if (firstNameCorrect && lastNameCorrect)
					deleteClient = true;

				if (deleteClient && (!firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty()))
				{
					Alert a = new Alert(Alert.AlertType.CONFIRMATION);
					a.setTitle("Ostrze¿enie");
					a.setHeaderText("Czy na pewno chcesz usun¹æ tego pracownika?");
					a.showAndWait().ifPresent(response -> {
						if (response == ButtonType.OK)
						{
							deleteEmployee(firstNameTextField.getText(), lastNameTextField.getText());
						} else
						{
							event.consume();
						}
					});

					firstNameTextField.setText("");
					lastNameTextField.setText("");
				} else
				{

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("B³¹d dodawania");
					alert.setHeaderText(null);
					alert.setContentText("Nie mo¿na usun¹æ pracownika. \nPopraw dane w formularzu!");
					alert.showAndWait();

				}
			}

			private void deleteEmployee(String firstName, String lastName)
			{
				ArrayList<Employee> arrayList = new ArrayList<>();
				
				for(Employee employee : EmployeeCollection.getEmployeeList())
				{
					arrayList.add(employee);
				}
				
				Iterator<Employee> iterator = arrayList.iterator();

				if (iterator.hasNext())
				{
					while (iterator.hasNext())
					{
						Employee employee = iterator.next();

						if (firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName()))
						{
							EmployeeCollection.getEmployeeList().remove(employee);

							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Usuniêto!");
							alert.setHeaderText(null);
							alert.setContentText("Pracownik: " + firstName + " " + lastName + " zosta³ usuniêty!");
							alert.showAndWait();
						} else
						{
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Usuniêto!");
							alert.setHeaderText(null);
							alert.setContentText(
									"Pracownik: " + firstName + " " + lastName + " nie istnieje w bazie danych");
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
	}

}
