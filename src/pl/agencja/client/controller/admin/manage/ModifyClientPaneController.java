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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.agencja.client.controller.ClientsPaneController;
import pl.agencja.client.model.customer.Customer;
import pl.agencja.client.model.customer.CustomersCollection;

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
	private Label errorAgeLabel;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private Label lastNameCheckLabel;

	@FXML
	private TextField ageTextField;

	@FXML
	private TextField checkLastNameTextField;

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
	Customer customer;

	boolean modifyClient;
	boolean ageCorrect;
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
				Iterator<Customer> iterator = CustomersCollection.getCustomerList().iterator();

				if (iterator.hasNext())
				{
					while (iterator.hasNext())
					{
						Customer customer = iterator.next();

						if (checkLastNameTextField.getText().equals(customer.getLastName()))
						{
							setData(customer);

							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Wybrano pracownika!");
							alert.setHeaderText(null);
							alert.setContentText(
									"Pracownik: " + checkLastNameTextField.getText() + " zosta³ wybrany do edycji!");
							alert.showAndWait();
						} else if (checkLastNameTextField.getText().equals(""))
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
							alert.setContentText("Pracownik: " + checkLastNameTextField.getText() + " nie istnieje!");
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
				phoneCorrect = false;
				modifyClient = false;
				firstNameCorrect = false;
				lastNameCorrect = false;
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

				if (ageCorrect && phoneCorrect && countryCorrect && firstNameCorrect && lastNameCorrect)
					modifyClient = true;

				if (modifyClient && (!firstNameTextField.getText().isEmpty() && !lastNameTextField.getText().isEmpty()
						&& !ageTextField.getText().isEmpty() && !adressTextField.getText().isEmpty()
						&& birthDatePicker.getValue() != null && !phoneNumberTextField.getText().isEmpty()
						&& !countrySplitMenuButton.getText().isEmpty()))
				{
					modifyEmployee(checkLastNameTextField.getText());

					setEmptyData();

				} else
				{
					System.out.println(modifyClient);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("B³¹d dodawania");
					alert.setHeaderText(null);
					alert.setContentText("B³¹d edycji. \nPopraw dane w formularzu!");
					alert.showAndWait();

				}
			}

		});
	}

	private void modifyEmployee(String lastName)
	{
		ArrayList<Customer> arrayList = new ArrayList<>();

		for (Customer employee : CustomersCollection.getCustomerList())
		{
			arrayList.add(employee);
		}

		Iterator<Customer> iterator = arrayList.iterator();

		if (iterator.hasNext())
		{
			while (iterator.hasNext())
			{
				Customer customer = iterator.next();

				if (lastName.equals(customer.getLastName()))
				{
					CustomersCollection.getCustomerList().remove(customer);
					CustomersCollection.getCustomerList()
							.add(new Customer(firstNameTextField.getText(), lastNameTextField.getText(),
									Integer.parseInt(ageTextField.getText()), countrySplitMenuButton.getText(),
									adressTextField.getText(), Long.parseLong(phoneNumberTextField.getText()),
									birthDatePicker.getValue()));

				} else
				{
					setEmptyData();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Nie mo¿na zmodyfikowaæ!");
					alert.setHeaderText(null);
					alert.setContentText("Pracownik: " + lastName + " nie istnieje w bazie danych");
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

	private void setData(Customer customer)
	{
		firstNameTextField.setText(customer.getFirstName());
		lastNameTextField.setText(customer.getLastName());
		ageTextField.setText(String.valueOf(customer.getAge()));
		countrySplitMenuButton.setText(customer.getCountry());
		adressTextField.setText(customer.getAddress());
		phoneNumberTextField.setText(String.valueOf(customer.getPhoneNumber()));
		birthDatePicker.setValue(customer.getBirthDate());
	}

	private void setEmptyData()
	{
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		ageTextField.setText("");
		countrySplitMenuButton.setText("");
		adressTextField.setText("");
		birthDatePicker.setValue(null);
		phoneNumberTextField.setText("");
	}

}
