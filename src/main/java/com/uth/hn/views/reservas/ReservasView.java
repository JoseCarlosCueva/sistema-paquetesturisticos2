package com.uth.hn.views.reservas;

import com.uth.hn.controller.ReservasInteractor;
import com.uth.hn.controller.ReservasInteractorImpl;
import com.uth.hn.data.PaquetesTuristicos;
import com.uth.hn.data.Reservas;
import com.uth.hn.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;

@PageTitle("Reservas")
@Route(value = "reservas/:reservasID?/:action?(edit)", layout = MainLayout.class)
@Uses(Icon.class)
public class ReservasView extends Div implements BeforeEnterObserver, ReservasViewModel {

    private final String RESERVAS_ID = "reservasID";
    private final String RESERVAS_EDIT_ROUTE_TEMPLATE = "reservas/%s/edit";

    private final Grid<Reservas> grid = new Grid<>(Reservas.class, false);

    private TextField idreserva;
    private ComboBox<PaquetesTuristicos> paquete;
    private ComboBox<Reservas> cliente; 
    private DatePicker fecha;
    private NumberField precio;
    private ComboBox<String> estado;

    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");
    private final Button delete = new Button("Eliminar", new Icon(VaadinIcon.TRASH));
    
    private ConfirmDialog deleteDialog;

    private Reservas reservas;
    private ReservasInteractor controladorReservas;
    private List<Reservas> elementos;
    private List<PaquetesTuristicos> paquetesDisponibles;

    public ReservasView( ) {
        addClassNames("reservas-view");  
        
        controladorReservas = new ReservasInteractorImpl(this);
        this.elementos = new ArrayList<>();
        this.paquetesDisponibles = new ArrayList<>();

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("idreserva").setAutoWidth(true).setHeader("Número de Reserva");
        grid.addColumn("paquete").setAutoWidth(true).setHeader("Paquete");
        grid.addColumn("cliente").setAutoWidth(true).setHeader("Cliente");
        grid.addColumn("fecha").setAutoWidth(true).setHeader("Fecha de Reserva");
        grid.addColumn("precio").setAutoWidth(true).setHeader("Precio Total");
        grid.addColumn("estado").setAutoWidth(true).setHeader("Estado");
        /*LitRenderer<Reservas> importantRenderer = LitRenderer.<Reservas>of(
                "<vaadin-icon icon='vaadin:${item.icon}' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: ${item.color};'></vaadin-icon>")
                .withProperty("icon", important -> important.isImportant() ? "check" : "minus").withProperty("color",
                        important -> important.isImportant()
                                ? "var(--lumo-primary-text-color)"
                                : "var(--lumo-disabled-text-color)");*/

        //grid.addColumn(importantRenderer).setHeader("Important").setAutoWidth(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(RESERVAS_EDIT_ROUTE_TEMPLATE, event.getValue().getIdreserva()));
            } else {
                clearForm();
                UI.getCurrent().navigate(ReservasView.class);
            }
        });

        this.controladorReservas.consultarReservas();
        this.controladorReservas.consultarPaquetesTuristicos();
        
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
            	if (this.reservas == null) {
                    this.reservas = new Reservas();
                    //ESTOY CREANDO UN NUEVO PAQUETETURISTICO
                    
                    this.reservas.setIdreserva(this.idreserva.getValue());

                    ZoneId defaultZoneId = ZoneId.systemDefault();
                    Date date = Date.from(this.fecha.getValue().atStartOfDay(defaultZoneId).toInstant());
                    this.reservas.setFecha(date);
                    this.reservas.setPrecio(this.precio.getValue());
                    this.reservas.setEstado(this.estado.getValue());
					
					 //VALIDO SI HAY UN ELEMENTO SELECCIONADO
                    if(paquete.getValue() != null) {
                    	Integer idPaquete = paquete.getValue().getIdpaquete();
    					this.reservas.setPaquete(idPaquete);
    					this.controladorReservas.crearReservas(reservas);
                    }else {
                    	Notification.show("Debes de seleccionar un paquete para crear una reserva");
                    }
                }else {
                	  //ESTOY ACTUALIZANDO UNO QUE YA EXISTE
                	
                	ZoneId defaultZoneId = ZoneId.systemDefault();
                    Date date = Date.from(this.fecha.getValue().atStartOfDay(defaultZoneId).toInstant());
                    this.reservas.setFecha(date);
                    this.reservas.setPrecio(this.precio.getValue());
                    this.reservas.setEstado(this.estado.getValue());
                	
					 //VALIDO SI HAY UN ELEMENTO SELECCIONADO
                   if(paquete.getValue() != null) {
                   	Integer idPaquete = paquete.getValue().getIdpaquete();
   					this.reservas.setPaquete(idPaquete);
   					this.controladorReservas.actualizarReservas(reservas);
                   }else {
                   	Notification.show("Debes de seleccionar un paquete para actualizar una reserva");
                   }
                    
                }
            	
                clearForm();
                refreshGrid();
                UI.getCurrent().navigate(ReservasView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        
    delete.addClickListener( e -> {
    	this.deleteDialog.open();
        	
        });
    delete.setEnabled(false);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> reservasIdreserva = event.getRouteParameters().get(RESERVAS_ID);
        if (reservasIdreserva.isPresent()) {
           Reservas reservasFromBackend = obtenerReservas(reservasIdreserva.get());
            if (reservasFromBackend != null) {
                populateForm(reservasFromBackend);
            } else {
                Notification.show(
                        String.format("La reserva con ID = %s no existe", reservasIdreserva.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(ReservasView.class);
            }
        }
    }

    private Reservas obtenerReservas(String reservasIdreserva) {
    	Reservas reservasEncontrado = null;
		for (Reservas reservas : elementos) {
			if(reservas.getIdreserva().equals(reservasIdreserva)) {
				reservasEncontrado = reservas;
				break;
			}
		}
		return reservasEncontrado;
	}

	private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        
        idreserva = new TextField("Número de Reserva");
        idreserva.setPrefixComponent(VaadinIcon.CALENDAR.create());
        
        paquete = new ComboBox<>("Paquete");
        paquete.setItemLabelGenerator(PaquetesTuristicos::getNombre);
        
        cliente = new ComboBox<>("Cliente");
        cliente.setItemLabelGenerator(Reservas::getCliente);

        fecha = new DatePicker("Fecha de Reserva");
        
        precio = new NumberField();
        precio.setLabel("Precio Total");
        precio.setValue(0.0);
        Div dollarPrefix2 = new Div();
        dollarPrefix2.setText("$");
        precio.setPrefixComponent(dollarPrefix2);
        
        add(precio);

        estado = new ComboBox<>("Estado");
        estado.setId("cbgenero");
        estado.setAllowCustomValue(true);
        estado.setItems("0", "1");
        estado.setHelperText("Seleccione el estado de la reserva");
        
        deleteDialog = new ConfirmDialog();
        deleteDialog.setHeader("¿Desea eliminar este PaqueteTuristico?");
        deleteDialog.setText(
                "Confirma la eliminación del paquete seleccionado");

        deleteDialog.setCancelable(true);
        deleteDialog.setCancelText("Cancelar");
        deleteDialog.setConfirmText("Eliminar");
        deleteDialog.setConfirmButtonTheme("error primary");
        deleteDialog.addConfirmListener(event -> {
        	this.controladorReservas.eliminarReservas(this.reservas.getIdreserva());
        	refreshGrid();
        });
        
        formLayout.add( idreserva, paquete, cliente, fecha, precio, estado);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        
        buttonLayout.add(save, cancel, delete);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
        this.controladorReservas.consultarReservas();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Reservas value) {
    	 this.reservas = value;
    	 if(value == null) {
         	this.idreserva.setValue("");
             this.paquete.clear();
             this.cliente.clear();
             this.fecha.setValue(null);
             this.precio.setValue(0.0);
             this.estado.setValue("");
             delete.setEnabled(false);
             
         }else {
         this.idreserva.setValue(value.getIdreserva());
         PaquetesTuristicos PaquetesSeleccionado = buscarPaquetes(value.getPaquete());
         this.paquete.setValue(PaquetesSeleccionado);
         //this.cliente.setValue(value.getCliente());
         if (value.getFecha() != null) {
             this.fecha.setValue(value.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
         } else {
             this.fecha.setValue(null); 
         }
         this.precio.setValue(value.getPrecio());
         this.estado.setValue(value.getEstado());
         delete.setEnabled(true);
     }
 
    }

	private PaquetesTuristicos buscarPaquetes(Integer idPaquete) {
		PaquetesTuristicos PaquetesEncontrado = null;
		for (PaquetesTuristicos paquete : paquetesDisponibles) {
			if(paquete.getIdpaquete() == idPaquete) {
				PaquetesEncontrado = paquete;
				break;
			}
		}
		return PaquetesEncontrado;
	}

	@Override
	public void mostrarReservasEnGrid(List<Reservas> items) {
		Collection<Reservas> itemsCollection = items;
		grid.setItems(itemsCollection);
		this.elementos = items;
		
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		Notification.show(mensaje);
		
	}

	@Override
	public void mostrarMensajeExito(String mensaje) {
		Notification.show(mensaje);
		
	}

	@Override
	public void llenarComboboxPaquetesTuristicos(List<PaquetesTuristicos> items) {
		paquete.setItems(items);
		paquetesDisponibles = items;
		
	}
}
