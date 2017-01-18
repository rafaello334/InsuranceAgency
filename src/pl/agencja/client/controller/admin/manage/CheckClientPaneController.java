package pl.agencja.client.controller.admin.manage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
import pl.agencja.client.controller.ClientsPaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.customer.Customer;

public class CheckClientPaneController implements Initializable
{
	public static final String FIRST_NAME_COLUMN = "Imi�";
	public static final String LAST_NAME_COLUMN = "Nazwisko";
	public static final String COUNTRY_COLUMN = "Kraj";
	public static final String ADRESS_COLUMN = "Adres";
	public static final String PHONE_NUMBER_COLUMN = "Numer Telefonu";
	public static final String JOIN_DATE_COLUMN = "Data do��czenia";
	public static final String BIRTH_DATE_COLUMN = "Data urodzenia";
	public static final String DELETE_COLUMN = "";
	@FXML
	private Button backCheckClientButton;

	@FXML
	private TableView<Customer> clientsTableView;

	@FXML
	private Pane contentPane;

	ClientsPaneController clientsPaneController;
	ArrayList<Customer> customersList;

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

		backCheckClientButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/pl/agencja/client/view/menu/ClientsPane.fxml"));
					loader.setController(new ClientsPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					clientsPaneController = (ClientsPaneController) loader.getController();
					clientsPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}

	private void configureTableView()
	{
		TableColumn<Customer, String> firstNameColumn = new TableColumn<>(FIRST_NAME_COLUMN);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn<Customer, String> lastNameColumn = new TableColumn<>(LAST_NAME_COLUMN);
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		TableColumn<Customer, String> countryColumn = new TableColumn<>(COUNTRY_COLUMN);
		countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

		TableColumn<Customer, String> adressColumn = new TableColumn<>(ADRESS_COLUMN);
		adressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

		TableColumn<Customer, Long> phoneNumberColumn = new TableColumn<>(PHONE_NUMBER_COLUMN);
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

		TableColumn<Customer, LocalDate> joinDateColumn = new TableColumn<>(JOIN_DATE_COLUMN);
		joinDateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));

		TableColumn<Customer, LocalDate> birthDateColumn = new TableColumn<Customer, LocalDate>(BIRTH_DATE_COLUMN);
		birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

		clientsTableView.getColumns().add(firstNameColumn);
		clientsTableView.getColumns().add(lastNameColumn);
		clientsTableView.getColumns().add(countryColumn);
		clientsTableView.getColumns().add(adressColumn);
		clientsTableView.getColumns().add(phoneNumberColumn);
		clientsTableView.getColumns().add(joinDateColumn);
		clientsTableView.getColumns().add(birthDateColumn);

	}

	@SuppressWarnings("unchecked")
	private void configureCollection()
	{
		HibernateUtil.entityManager.getTransaction().begin();
		customersList = (ArrayList<Customer>) HibernateUtil.entityManager.createQuery("from Customer").getResultList();
		HibernateUtil.entityManager.getTransaction().commit();
		clientsTableView.setItems(FXCollections.observableArrayList(customersList));
	}
}
