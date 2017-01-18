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
import pl.agencja.client.controller.admin.manage.AddOcPaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.policy.CarAcPolicy;
import pl.agencja.client.model.policy.CarOcPolicy;

public class CheckOcPolicyPaneController implements Initializable
{
	public static final String CAR_MARK_COLUMN = "Marka";
	public static final String CAR_VALUE_COLUMN = "Wartoœæ samochodu";
	public static final String PRODUCTION_YEAR_COLUMN = "Rok produkcji";
	public static final String START_POLICY_DATE = "Data rozpoczêcia polisy";
	public static final String FINISH_POLICY_DATE = "Data rozpoczêcia polisy";

	@FXML
	private Button backCheckPolicyButton;

	@FXML
	private TableView<CarOcPolicy> ocPolicyTableView;

	@FXML
	private Pane contentPane;

	ArrayList<CarOcPolicy> ocList;
	AddOcPaneController addOcPaneController;

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
							getClass().getResource("/pl/agencja/client/view/admin/policy/manage/AddOcPane.fxml"));
					loader.setController(new AddOcPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					addOcPaneController = (AddOcPaneController) loader.getController();
					addOcPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}

	private void configureTableView()
	{
		TableColumn<CarOcPolicy, String> markColumn = new TableColumn<>(CAR_MARK_COLUMN);
		markColumn.setCellValueFactory(new PropertyValueFactory<>("carMark"));

		TableColumn<CarOcPolicy, Integer> valueColumn = new TableColumn<>(CAR_VALUE_COLUMN);
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("carValue"));

		TableColumn<CarOcPolicy, Integer> productionYearColumn = new TableColumn<>(PRODUCTION_YEAR_COLUMN);
		productionYearColumn.setCellValueFactory(new PropertyValueFactory<>("productionYear"));

		TableColumn<CarOcPolicy, LocalDate> startPolicyColumn = new TableColumn<>(START_POLICY_DATE);
		startPolicyColumn.setCellValueFactory(new PropertyValueFactory<>("startPolicyDate"));

		TableColumn<CarOcPolicy, LocalDate> finishPolicyColumn = new TableColumn<>(FINISH_POLICY_DATE);
		finishPolicyColumn.setCellValueFactory(new PropertyValueFactory<>("finishPolicyDate"));


		ocPolicyTableView.getColumns().add(markColumn);
		ocPolicyTableView.getColumns().add(valueColumn);
		ocPolicyTableView.getColumns().add(productionYearColumn);
		ocPolicyTableView.getColumns().add(startPolicyColumn);
		ocPolicyTableView.getColumns().add(finishPolicyColumn);


	}

	@SuppressWarnings("unchecked")
	private void configureCollection()
	{
		HibernateUtil.entityManager.getTransaction().begin();
		ocList = (ArrayList<CarOcPolicy>) HibernateUtil.entityManager.createQuery("from CarOcPolicy").getResultList();
		HibernateUtil.entityManager.getTransaction().commit();
		ocPolicyTableView.setItems(FXCollections.observableArrayList(ocList));
	}
}
