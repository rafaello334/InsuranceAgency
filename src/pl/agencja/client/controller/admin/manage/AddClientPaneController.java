package pl.agencja.client.controller.admin.manage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.ClientsPaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.customer.Customer;

public class AddClientPaneController implements Initializable
{
	@FXML
	private Button backAddClientButton;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField adressTextField;

	@FXML
	private SplitMenuButton countrySplitMenuButton;

	@FXML
	private Button acceptButton;

	@FXML
	private TextField phoneNumberTextField;

	@FXML
	private Label errorPhoneNumberLabel;

	@FXML
	private Label errorCountryLabel;

	@FXML
	private Label errorFirstNameLabel;

	@FXML
	private Label errorLastNameLabel;

	@FXML
	private Label errorAdressLabel;

	@FXML
	private Label nameLabel;

	@FXML
	private Label errorBirthDateLabel;

	@FXML
	private DatePicker birthDatePicker;

	@FXML
	private Pane contentPane;

	ClientsPaneController clientsPaneController;
	Customer customer;
	boolean addClient;
	boolean phoneCorrect;
	boolean countryCorrect;
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

		// Ustawienie kraju w formularzu

		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales)
		{
			Locale country = new Locale("", countryCode);
			countrySplitMenuButton.getItems().add(new MenuItem(country.getDisplayName()));
		}

		for (MenuItem item : countrySplitMenuButton.getItems())
		{
			item.setOnAction((event) -> {

				countrySplitMenuButton.setText(item.getText());
			});
		}

		acceptButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				phoneCorrect = false;
				addClient = false;
				firstNameCorrect = false;
				lastNameCorrect = false;

				if (isNumeric(phoneNumberTextField.getText()))
				{
					phoneCorrect = true;
					errorPhoneNumberLabel.setText("");
				} else if (phoneNumberTextField.getText().equals(""))
				{
					errorPhoneNumberLabel.setText("Podaj numer telefonu!");
				} else
				{
					errorPhoneNumberLabel.setText("Nieprawid³owa wartoœæ");
				}

				if (!countrySplitMenuButton.getText().equals("Kraj"))
				{
					countryCorrect = true;
					errorCountryLabel.setText("");
				} else
				{
					errorCountryLabel.setText("Wybierz kraj!");
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

				if (adressTextField.getText().equals(""))
				{
					errorAdressLabel.setText("Podaj adres!");
				} else
				{
					errorAdressLabel.setText("");
				}
				if (birthDatePicker.getValue() == null)
				{
					errorBirthDateLabel.setText("Wybierz date!");
				} else
				{
					errorBirthDateLabel.setText("");
				}

				if (phoneCorrect && countryCorrect && firstNameCorrect && lastNameCorrect)
					addClient = true;

				if (addClient && (!firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty()
						&& !countrySplitMenuButton.getText().isEmpty() && !adressTextField.getText().isEmpty()
						&& !phoneNumberTextField.getText().isEmpty() && !birthDatePicker.getValue().equals(null)))
				{
					
					customer = new Customer();
					customer.setFirstName(firstNameTextField.getText());
					customer.setLastName(lastNameTextField.getText());
					customer.setCountry(countrySplitMenuButton.getText());
					customer.setAddress(adressTextField.getText());
					customer.setPhoneNumber(Long.parseLong(phoneNumberTextField.getText()));
					customer.setBirthDate(birthDatePicker.getValue());
					customer.setJoinDate(LocalDate.now());
					
					HibernateUtil.entityManager.getTransaction().begin();
					HibernateUtil.entityManager.persist(customer);
					HibernateUtil.entityManager.getTransaction().commit();
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Dodawanie u¿ytkownika");
					alert.setHeaderText(null);
					alert.setContentText("Dodanie u¿ytkownika zakoñczy³o siê sukcesem.\n");
					alert.showAndWait();

					firstNameTextField.setText("");
					lastNameTextField.setText("");
					countrySplitMenuButton.setText("Kraj");
					birthDatePicker.setValue(null);
					adressTextField.setText("");
					phoneNumberTextField.setText("");

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

		backAddClientButton.setOnAction(new EventHandler<ActionEvent>()
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
