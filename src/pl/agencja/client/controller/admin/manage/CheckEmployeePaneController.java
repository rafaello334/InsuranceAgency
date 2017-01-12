package pl.agencja.client.controller.admin.manage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.EmployeesPaneController;
import pl.agencja.client.model.customer.Employee;
import pl.agencja.client.model.customer.EmployeeCollection;

public class CheckEmployeePaneController implements Initializable
{
	public static final String FIRST_NAME_COLUMN = "Imiê";
	public static final String LAST_NAME_COLUMN = "Nazwisko";
	public static final String AGE_COLUMN = "Wiek";
	public static final String EMAIL_ADDRESS_COLUMN = "Adres e-mail";
	public static final String JOIN_DATE_COLUMN = "Data do³¹czenia";
	public static final String BIRTH_DATE_COLUMN = "Data urodzenia";
	public static final String ADMINISTRATOR_COLUMN = "Prawa administratorskie";
	@FXML
	private Button backCheckEmployeeButton;

	@FXML
	private Pane contentPane;

	@FXML
	private TableView<Employee> employeeTableView;

	EmployeesPaneController employeesPaneController;

	public void setContentPane(Pane contentPane)
	{
		this.contentPane = contentPane;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		configureCollection();
		configureButtons();
		configureTableView();
	}

	private void configureButtons()
	{
		backCheckEmployeeButton.setOnAction(new EventHandler<ActionEvent>()
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

	}

	@SuppressWarnings("unchecked")
	private void configureTableView()
	{
		TableColumn<Employee, String> firstNameColumn = new TableColumn<>(FIRST_NAME_COLUMN);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn<Employee, String> lastNameColumn = new TableColumn<>(LAST_NAME_COLUMN);
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		TableColumn<Employee, Integer> ageColumn = new TableColumn<>(AGE_COLUMN);
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Employee, String> emailAddressColumn = new TableColumn<>(EMAIL_ADDRESS_COLUMN);
		emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("emailAdress"));

		TableColumn<Employee, LocalDate> joinDateColumn = new TableColumn<>(JOIN_DATE_COLUMN);
		joinDateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));

		TableColumn<Employee, LocalDate> birthDateColumn = new TableColumn<>(BIRTH_DATE_COLUMN);
		birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

		TableColumn<Employee, String> administratorColumn = new TableColumn<>(ADMINISTRATOR_COLUMN);
		administratorColumn.setCellValueFactory(new PropertyValueFactory<>("administrator"));

		employeeTableView.getColumns().setAll(firstNameColumn, lastNameColumn, ageColumn, emailAddressColumn,
				joinDateColumn, birthDateColumn, administratorColumn);

		employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	private void configureCollection()
	{
		employeeTableView.setItems(EmployeeCollection.getEmployeeList());
	}
}
