package pl.agencja.client.controller.admin.manage.policy.check;

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
import pl.agencja.client.controller.admin.manage.AddTravelPaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.policy.TravelPolicy;

public class CheckTravelPolicyPaneController implements Initializable
{
	public static final String PRICE_COLUMN = "Kwota ubezpieczenia";
	public static final String DESTINATION_COLUMN = "Cel podró¿y";
	public static final String START_POLICY_DATE = "Data rozpoczêcia polisy";
	public static final String FINISH_POLICY_DATE = "Data rozpoczêcia polisy";

	@FXML
	private Button backCheckPolicyButton;

	@FXML
	private TableView<TravelPolicy> travelPolicyTableView;

	@FXML
	private Pane contentPane;

	ArrayList<TravelPolicy> travelList;
	AddTravelPaneController addTravelPaneController;

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

		backCheckPolicyButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				try
				{
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/pl/agencja/client/view/admin/policy/manage/AddTravelPane.fxml"));
					loader.setController(new AddTravelPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					addTravelPaneController = (AddTravelPaneController) loader.getController();
					addTravelPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}

	private void configureTableView()
	{
		TableColumn<TravelPolicy, String> priceColumn = new TableColumn<>(PRICE_COLUMN);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		TableColumn<TravelPolicy, Integer> destinationColumn = new TableColumn<>(DESTINATION_COLUMN);
		destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));

		TableColumn<TravelPolicy, LocalDate> startPolicyColumn = new TableColumn<>(START_POLICY_DATE);
		startPolicyColumn.setCellValueFactory(new PropertyValueFactory<>("startPolicyDate"));

		TableColumn<TravelPolicy, LocalDate> finishPolicyColumn = new TableColumn<>(FINISH_POLICY_DATE);
		finishPolicyColumn.setCellValueFactory(new PropertyValueFactory<>("finishPolicyDate"));

		travelPolicyTableView.getColumns().add(priceColumn);
		travelPolicyTableView.getColumns().add(destinationColumn);
		travelPolicyTableView.getColumns().add(startPolicyColumn);
		travelPolicyTableView.getColumns().add(finishPolicyColumn);

	}

	@SuppressWarnings("unchecked")
	private void configureCollection()
	{
		HibernateUtil.entityManager.getTransaction().begin();
		travelList = (ArrayList<TravelPolicy>) HibernateUtil.entityManager.createQuery("from TravelPolicy")
				.getResultList();
		HibernateUtil.entityManager.getTransaction().commit();
		travelPolicyTableView.setItems(FXCollections.observableArrayList(travelList));
	}
}
