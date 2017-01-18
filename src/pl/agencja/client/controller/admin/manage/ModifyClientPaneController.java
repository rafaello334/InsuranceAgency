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

public class ModifyClientPaneController implements Initializable
{
	@FXML
	private Button backModifyClientButton;

	@FXML
	private Label errorBirthDateLabel;

	@FXML
	private TextField phoneNumberTextField;

	@FXML
	private Label errorPhoneNumberLabel;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private DatePicker birthDatePicker;

	@FXML
	private SplitMenuButton countrySplitMenuButton;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private Label lastNameCheckLabel;

	@FXML
	private TextField checkIdTextField;

	@FXML
	private Label errorCountryLabel;

	@FXML
	private Label errorFirstNameLabel;

	@FXML
	private Button checkLastNameButton;

	@FXML
	private TextField adressTextField;

	@FXML
	private Label errorLastNameLabel;

	@FXML
	private Button acceptButton;

	@FXML
	private Label nameLabel;

	@FXML
	private Label errorAdressLabel;

	@FXML
	private Pane contentPane;

	ClientsPaneController clientsPaneController;
	Customer existingCustomer;

	boolean modifyClient;
	boolean firstNameCorrect;
	boolean lastNameCorrect;
	boolean addressCorrect;
	boolean phoneCorrect;
	boolean countryCorrect;

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

		backModifyClientButton.setOnAction(new EventHandler<ActionEvent>()
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

		checkLastNameButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				int klucz;
				existingCustomer = null;
				if (!checkIdTextField.getText().isEmpty())
				{
					if (!isNumeric(checkIdTextField.getText()))
					{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("B³¹d ID!");
						alert.setHeaderText(null);
						alert.setContentText("ID sk³ada siê tylko z cyfr.\nSpróbuj ponownie");
						alert.showAndWait();

					} else
					{
						klucz = Integer.parseInt(checkIdTextField.getText());
						HibernateUtil.entityManager.getTransaction().begin();
						existingCustomer = HibernateUtil.entityManager.find(Customer.class, klucz);
						HibernateUtil.entityManager.getTransaction().commit();
					}
				}
				if (existingCustomer != null)
				{
					setData(existingCustomer);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Wybrano pracownika!");
					alert.setHeaderText(null);
					alert.setContentText("Klient: " + checkIdTextField.getText() + " zosta³ wybrany do edycji!");
					alert.showAndWait();
				} else if (checkIdTextField.getText().equals(""))
				{
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("B³êdne dane!");
					alert.setHeaderText(null);
					alert.setContentText("Podaj id klienta!");
					alert.showAndWait();

				} else 
				{
					setEmptyData();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Brak pracownika!");
					alert.setHeaderText(null);
					alert.setContentText("Klient: " + checkIdTextField.getText() + " nie istnieje!");
					alert.showAndWait();
				}

			}
		});

		acceptButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{

				phoneCorrect = false;
				modifyClient = false;
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
					modifyClient = true;

				if (modifyClient && (!firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty()
						&& !adressTextField.getText().isEmpty() && birthDatePicker.getValue() != null
						&& !phoneNumberTextField.getText().isEmpty() && !countrySplitMenuButton.getText().isEmpty())
						&& existingCustomer != null)
				{
					HibernateUtil.entityManager.getTransaction().begin();
					existingCustomer.setFirstName(firstNameTextField.getText());
					existingCustomer.setLastName(lastNameTextField.getText());
					existingCustomer.setCountry(countrySplitMenuButton.getText());
					existingCustomer.setAddress(adressTextField.getText());
					existingCustomer.setPhoneNumber(Long.parseLong(phoneNumberTextField.getText()));
					existingCustomer.setBirthDate(birthDatePicker.getValue());
					existingCustomer.setJoinDate(LocalDate.now());
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
					alert.setTitle("B³¹d edycji");
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

	private void setData(Customer customer)
	{

		firstNameTextField.setText(customer.getFirstName());
		lastNameTextField.setText(customer.getLastName());
		countrySplitMenuButton.setText(customer.getCountry());
		adressTextField.setText(customer.getAddress());
		phoneNumberTextField.setText(String.valueOf(customer.getPhoneNumber()));
		birthDatePicker.setValue(customer.getBirthDate());
	}

	private void setEmptyData()
	{
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		countrySplitMenuButton.setText("");
		adressTextField.setText("");
		birthDatePicker.setValue(null);
		phoneNumberTextField.setText("");
	}

}
