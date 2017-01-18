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
import pl.agencja.client.controller.admin.manage.AddAcPaneController;
import pl.agencja.client.database.HibernateUtil;
import pl.agencja.client.model.policy.CarAcPolicy;

public class CheckAcPolicyPaneController implements Initializable
{
	public static final String CAR_MARK_COLUMN = "Marka";
	public static final String CAR_VALUE_COLUMN = "Wartoœæ samochodu";
	public static final String PRODUCTION_YEAR_COLUMN = "Rok produkcji";
	public static final String START_POLICY_DATE = "Data rozpoczêcia polisy";
	public static final String FINISH_POLICY_DATE = "Data rozpoczêcia polisy";

	@FXML
	private Button backCheckPolicyButton;

	@FXML
	private TableView<CarAcPolicy> acPolicyTableView;

	@FXML
	private Pane contentPane;

	ArrayList<CarAcPolicy> acList;
	AddAcPaneController addAcPaneController;

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
							getClass().getResource("/pl/agencja/client/view/admin/policy/manage/AddAcPane.fxml"));
					loader.setController(new AddAcPaneController());
					contentPane.getChildren().clear();
					contentPane.getChildren().add(loader.load());

					addAcPaneController = (AddAcPaneController) loader.getController();
					addAcPaneController.setContentPane(contentPane);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}

	private void configureTableView()
	{
		TableColumn<CarAcPolicy, String> markColumn = new TableColumn<>(CAR_MARK_COLUMN);
		markColumn.setCellValueFactory(new PropertyValueFactory<>("carMark"));

		TableColumn<CarAcPolicy, Integer> valueColumn = new TableColumn<>(CAR_VALUE_COLUMN);
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("carValue"));

		TableColumn<CarAcPolicy, Integer> productionYearColumn = new TableColumn<>(PRODUCTION_YEAR_COLUMN);
		productionYearColumn.setCellValueFactory(new PropertyValueFactory<>("productionYear"));

		TableColumn<CarAcPolicy, LocalDate> startPolicyColumn = new TableColumn<>(START_POLICY_DATE);
		startPolicyColumn.setCellValueFactory(new PropertyValueFactory<>("startPolicyDate"));

		TableColumn<CarAcPolicy, LocalDate> finishPolicyColumn = new TableColumn<>(FINISH_POLICY_DATE);
		finishPolicyColumn.setCellValueFactory(new PropertyValueFactory<>("finishPolicyDate"));

		acPolicyTableView.getColumns().add(markColumn);
		acPolicyTableView.getColumns().add(valueColumn);
		acPolicyTableView.getColumns().add(productionYearColumn);
		acPolicyTableView.getColumns().add(startPolicyColumn);
		acPolicyTableView.getColumns().add(finishPolicyColumn);

	}

	@SuppressWarnings("unchecked")
	private void configureCollection()
	{
		HibernateUtil.entityManager.getTransaction().begin();
		acList = (ArrayList<CarAcPolicy>) HibernateUtil.entityManager.createQuery("from CarAcPolicy").getResultList();
		HibernateUtil.entityManager.getTransaction().commit();
		acPolicyTableView.setItems(FXCollections.observableArrayList(acList));
	}
}
