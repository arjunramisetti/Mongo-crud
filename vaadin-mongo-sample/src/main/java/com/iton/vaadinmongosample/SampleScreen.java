package com.iton.vaadinmongosample;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.shared.ui.Dependency.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.vaadin.klaudeta.PaginatedGrid;
import org.vaadin.klaudeta.PlutoniumPagination;

@HtmlImport("frontend://styles/NewFile.html")

public class SampleScreen extends VerticalLayout{
	
//	private VerticalLayout mainLayout;
	private Div mainLayout;
//	private FlexLayout mainLayout;
	private TextField nameField;
	private TextField salaryField;
	private Button submitButton;
	private JavaMongoDemo javaMongoDemo;
	private User user;
    private ArrayList<User> userCollection=null;
//	private Grid grid;
    private PaginatedGrid<User> grid;
	private Button updateButton;
	private String editNameValue;
	private String deleteNameValue;
	private String editSalaryValue;
	private Button cancelButton;
	private VerticalLayout formField;
	private Button button2;
	private HorizontalLayout searchLayout;
	private TextField searchField;
	private Label employeeLabel;
	private Button clearButton;
	private TextField gotoField;
	private VerticalLayout fieldsLayout;
	private long editEmpId;
	private Button addAddressButton;
	private TextField addressLine1;
	private TextField addressLine2;
	private TextField addressLine3;
	private Button submitAddressDetailsButton;
	private Address address;
	private Button viewButton;
	private Grid<Address> addressGrid;
	private Button editAddressButton;
	private Button deleteAddressButton;
	private ArrayList<Address> addressList;
	private Dialog addressListDialog;
	private VerticalLayout addressDetailsLayout;
	private VerticalLayout addressDetailsLayout2;
	private TextField addressLine4;
	private TextField addressLine5;
	private TextField addressLine6;
	private Button submitEditAddressDetailsButton;
	private TextField addressLine7;
	private TextField addressLine8;
	public SampleScreen() {
      buildMainLayout();
//		expand(formField);
	add(mainLayout);
//	setAlignItems(Alignment.STRETCH);
//		setCompositionRoot(mainLayout);
	}
	
	private Div buildMainLayout() {
//		mainLayout = new VerticalLayout();
		mainLayout = new Div();
//		mainLayout = new FlexLayout();
		mainLayout.setWidth("100%");
		
//		mainLayout.setMargin(false);
		mainLayout.addClassName("sample");
//		mainLayout.setPadding(false);
		fieldsLayout = new VerticalLayout();
		fieldsLayout.setWidth("100%");
		fieldsLayout.addClassName("fieldsLayout");
		 employeeLabel = new Label("Employee Form");
		 employeeLabel.addClassName("employeeLabel");
		formField = new VerticalLayout();
		formField.addClassName("formfield");
		formField.setWidth("300px");
		formField.setHeight("100%");
		formField.setPadding(false);
		formField.setHeight("-1px");
	
//		formField.setMargin(false);
		nameField = new TextField();//("Name");
		nameField.setPlaceholder("Name");
		nameField.setMaxLength(19);
		nameField.setWidth("100%");
		nameField.setHeight("30px");
		nameField.setPattern("[a-zA-Z ]{0,20}");
        nameField.addClassName("nameField");
//		nameField.setErrorMessage("please enter alphabets and white spaces only");
		nameField.setPreventInvalidInput(true);
		
//		nameField.StyleName(ValoTheme.TEXTFIELD_SMALL);
		salaryField = new TextField();//("Salary");
		salaryField.setPlaceholder("Salary");
//		salaryField.StyleName(ValoTheme.TEXTFIELD_SMALL);
		salaryField.setMaxLength(6);
		salaryField.setPattern("[0-9]{1,7}");
		salaryField.setPreventInvalidInput(true);
		salaryField.setWidth("100%");
		salaryField.setHeight("30px");
		
		
		
		
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setWidth("100%");
		submitButton  = new Button("Add");
		submitButton.addClassNames("greenButton");
		submitButton.getStyle().set("color", "#fff").set("background", "green").set("opacity", "0.7").set("height", "30px");
		
		updateButton  = new Button("Update");
		updateButton.addClassNames("greenButton");
		updateButton.getStyle().set("color", "#fff").set("background", "green").set("opacity", "0.7").set("height", "30px");
		updateButton.setVisible(false);
		
		clearButton = new Button("Clear");
		clearButton.getStyle().set("color", "#fff").set("background", "red").set("opacity", "0.9").set("height", "30px");
		clearButton.addClickListener(e->{
			nameField.clear();
			salaryField.clear();
		});
		cancelButton  = new Button("Cancel");
		cancelButton.addClassNames("deleteButton");
		cancelButton.getStyle().set("color", "#fff").set("background", "red").set("opacity", "0.9").set("height", "30px");
		cancelButton.setVisible(false);
		hl.add(submitButton,clearButton,updateButton,cancelButton);
		hl.addClassName("hl");
		hl.setJustifyContentMode(JustifyContentMode.CENTER);
	

//		hl.setAlignSelf(Alignment.CENTER, submitButton);
//		hl.setAlignSelf(Alignment.CENTER, clearButton);
		formField.add(nameField,salaryField,hl);
		formField.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		
		fieldsLayout.add(employeeLabel);
		fieldsLayout.add(formField);
		 
//		  mainLayout.setComponentAlignment(employeeLabel);
		fieldsLayout.setAlignItems(Alignment.CENTER);
		mainLayout.add(fieldsLayout);
//		mainLayout.setAlignItems(Alignment.CENTER);//setComponentAlignment(formField, Alignment.MIDDLE_CENTER);
		 javaMongoDemo = new JavaMongoDemo();
		 user= new User();
		 submitButton.addClickListener(e->{
			 
			 String name = nameField.getValue();
			 String salary = salaryField.getValue();
			 if(name==null||name.trim().equalsIgnoreCase("")||salary==null||salary.trim().equalsIgnoreCase("")) {
				 Notification.show("please fill name,salary fields");
			 }else {
				 
				 Pattern p = Pattern.compile("[0-9]{1,7}");//. represents single character  
					Matcher m = p.matcher(salary);  
					boolean b = m.matches(); 	 
					System.out.println("boolean pattern "+b);
					
					 Pattern p2 = Pattern.compile("[a-zA-Z ]{3,20}");//. represents single character  
						Matcher m2 = p2.matcher(name);  
						boolean b2 = m2.matches(); 	 
						System.out.println("boolean pattern2 "+b2); 
			if(b&&b2) {	 
			user.setName(name);
			user.setSalary(salary);
			
			
			
			javaMongoDemo.addFields(user);
			
			userCollection  =  javaMongoDemo.getUserCollection();
			
			grid.setItems(userCollection);
			nameField.clear();
			salaryField.clear();
//			grid.setHeightByRows(userCollection.size());
			if(userCollection.size()<7) {
//				grid.setHeightByRows(userCollection.size());
//				grid.setHeight(""+userCollection.size());
			}else {
//				grid.setHeight("6");
			}
			Notification.show("Details  successfully added", 1000, Position.BOTTOM_END);
//			Notification.show("Details  successfully added");
			
			 
			}else {
//				Notification.show("please enter digits in salary");
				Notification n = new Notification("please enter name must be in letters and salary must be in digits");
				n.setDuration(1000);
				n.setPosition(Position.BOTTOM_END);
//				Notification.show("please enter name must be in letters and salary must be in digits");
				n.open();
			}
			 }
		 });
		 userCollection  =  javaMongoDemo.getUserCollection();
//		 grid =new Grid<>(User.class);
		 grid = new PaginatedGrid<>();
		 grid.setWidth("100%");
		 grid.setHeight("100%");
		 grid.addClassName("samplegrid");
		 grid.setSelectionMode(SelectionMode.NONE);
		 grid.addColumn(User::getDate).setHeader("Date");
//		 grid.addColumn(User::getId).setHeader("Id");
		 grid.addColumn(User::getName).setHeader("Name");
		 grid.addColumn(User::getSalary).setHeader("Salary");
//		 grid.removeColumnByKey("empId");
//		 grid.remove
		 grid.addComponentColumn(e -> {
			  Icon editIcon = VaadinIcon.EDIT.create();
		      Button button = new Button(editIcon);
		      button.getStyle().set("color", "green").set("background", "none");
//		      button.StyleNames(ValoTheme.BUTTON_SMALL,ValoTheme.BUTTON_ICON_ONLY,"editButton");
//		      button.setIcon(FontAwesome.EDIT);
		      button.addClickListener(click ->{
		      updateButton.setVisible(true);
		      cancelButton.setVisible(true);
		      submitButton.setVisible(false);
		      clearButton.setVisible(false);
//		            Notification.show("Clicked: " + ((User) e).getName());
		            editNameValue = ((User) e).getName();
				      nameField.setValue(editNameValue);
				      
				      editSalaryValue = ((User) e).getSalary();
				      salaryField.setValue(editSalaryValue);
				 editEmpId = ((User) e).getEmpId();
				      });
		 
		    
		      return button;
		});
		 
		 
		 grid.addComponentColumn(e1 -> {
		      
		       Icon icon = VaadinIcon.TRASH.create();//VAADIN_H.create();
		       button2 = new Button(icon);
//		      button2.setIcon(FontAwesome.TRASH);
		      button2.addClassName("deleteButton");
		      button2.getStyle().set("color", "red").set("background", "none");
		      button2.addClickListener(click ->{
		    	  Dialog dialog = new Dialog();
		    	  VerticalLayout vl = new VerticalLayout();
//		      UI.getCurrent().add(dialog);
		      dialog.add(vl);
		      dialog.open();
//		      dialog.center();
//		      dialog.setModal(true);
//		      window.setHeight("180px");
		      HorizontalLayout deleteLayout = new HorizontalLayout();
		      Button confirmDelete = new Button("Confirm");
		      confirmDelete.getStyle().set("color", "#fff").set("background", "red").set("opacity", "0.9").set("height", "30px");
//		      confirmDelete.StyleNames(ValoTheme.BUTTON_SMALL);
		      Button cancelDelete = new Button("Cancel");
		      cancelDelete.getStyle().set("color", "#fff").set("background", "green").set("opacity", "0.7").set("height", "30px");
//		      cancelDelete.StyleName(ValoTheme.BUTTON_SMALL);
		      deleteLayout.add(confirmDelete,cancelDelete);
		      vl.add(new Label("Are you sure to Delete"));
		      vl.setAlignItems(Alignment.CENTER);
		      vl.setWidth("250px");
//		      vl.setHeight("150px");
		      vl.add(deleteLayout);
		      System.out.println("empid "+((User) e1).getEmpId());
		      confirmDelete.addClickListener(e2->{
		    	  deleteNameValue = ((User) e1).getName();
		    	  long id = ((User)e1).getEmpId();
				   javaMongoDemo.deleteDocument(id);
				   userCollection  =  javaMongoDemo.getUserCollection();
				   if(userCollection.size()!=0) {
					grid.setItems(userCollection);
//					grid.setHeightByRows(userCollection.size());
					if(userCollection.size()<7) {
//						grid.setHeightByRows(userCollection.size());
					}else {
//						grid.setHeightByRows(6);
					}
					}
					Notification.show("Details successfully deleted",1000,Position.BOTTOM_END);
//					window.close();
					dialog.close();
					
		      });
		          cancelDelete.addClickListener(e3->{
		        	 dialog.close(); 
		          });
				      });
		  
		    
		      return button2;
		});
		 
		 grid.addComponentColumn(e2->{
			 addAddressButton = new Button("");
			 Icon addIcon = new Icon(VaadinIcon.PLUS_CIRCLE);
			 addAddressButton.setIcon(addIcon);
			 addAddressButton.getStyle().set("color", "sky-blue").set("background", "none");
			 addAddressButton.addClickListener(e->{
				 
				 long empid = e2.getEmpId();
				 Dialog addressDialog = new Dialog();
				 
				 System.out.println("empid of current user is "+empid);
				 
				  addressDetailsLayout = new VerticalLayout();
				 addressLine1 = new TextField("Street/LandMark");
				 addressLine2 = new TextField("City/Town");
				 addressLine3 = new TextField("State");
				 HorizontalLayout butLayout = new HorizontalLayout();
				 submitAddressDetailsButton = new Button("Submit");
				 Button cancelAddressButton = new Button("Cancel");
				 butLayout.add(submitAddressDetailsButton,cancelAddressButton);
				 addressDetailsLayout.add(addressLine1,addressLine2,addressLine3,butLayout);
				 submitAddressDetailsButton.addClickListener(c->{
					 if(addressLine1.getValue().trim().equalsIgnoreCase("")||addressLine1.getValue()==null||addressLine2.getValue().trim().equalsIgnoreCase("")||addressLine2.getValue()==null||addressLine3.getValue().trim().equalsIgnoreCase("")||addressLine3.getValue()==null) {
						 Notification.show("Please enter all fields", 1000, Position.BOTTOM_END);
					 }else {
						 javaMongoDemo = new JavaMongoDemo();
						 address = new Address();
//						 address.setAddressId(addressId);
						 address.setEmpId(empid);
						 address.setStreet(addressLine1.getValue());
						 address.setCity(addressLine2.getValue()); 
						 address.setState(addressLine3.getValue());
						 javaMongoDemo.addAddressDetails(address);
						 addressDialog.close();
						 Notification.show("Details Successfully added", 1000, Position.BOTTOM_END);
						 
					 }
				 });
				 
				 cancelAddressButton.addClickListener(cancel->{
					 addressDialog.close();
				 });
				 
				 
				 
				 
				 addressDialog.add(addressDetailsLayout);
				 
				 addressDialog.open();
				 
			 });
			 
		
			 
			 
			 return addAddressButton;
		 });
		 
		 grid.addComponentColumn(e3->{
			 viewButton = new Button();
			 Icon viewIcon = new Icon(VaadinIcon.EYE);
			 viewButton.setIcon(viewIcon);
			 viewButton.getStyle().set("color", "chocolate").set("background", "none");
			 viewButton.addClickListener(e->{
				 System.out.println("view clicked");
				 long empId = e3.getEmpId();
				 javaMongoDemo = new JavaMongoDemo();
				 addressList = javaMongoDemo.getAddressDetails( empId);
			
				if(addressList!=null&&addressList.size()>0) {
				 addressGrid = new Grid<>();
				 addressGrid.setWidth("100%");
				 addressGrid.setItems(addressList);
				 addressGrid.addColumn(Address::getStreet).setHeader("Street");
				 addressGrid.addColumn(Address::getCity).setHeader("City");
				 addressGrid.addColumn(Address::getState).setHeader("State");
				 addressGrid.addComponentColumn(edit->{
					 editAddressButton = new Button(new Icon(VaadinIcon.EDIT));
					 editAddressButton.addClickListener(n->{
						 long currentEmpId = edit.getEmpId();
//						 editAddressLayout();
						 addressDetailsLayout2.setVisible(true);
						 addressLine4.setValue(edit.getStreet());
						 addressLine5.setValue(edit.getCity());
						 addressLine6.setValue(edit.getState());
						 addressLine7.setValue(edit.getAddressId());
						 addressLine8.setValue(edit.getEmpId()+"");

					 });
					 return editAddressButton;
				 });
				 addressGrid.addComponentColumn(delete->{
					 deleteAddressButton = new Button(new Icon(VaadinIcon.TRASH));
					 deleteAddressButton.addClickListener(d->{
						 String currentEmpId = delete.getAddressId();
						 javaMongoDemo = new JavaMongoDemo();
						 javaMongoDemo.deleteAddress(currentEmpId);
						 System.out.println(currentEmpId);
						
						 addressList = javaMongoDemo.getAddressDetails( empId);
						 if(addressList.size()>0) {
							 addressGrid.setItems(addressList);
							 Notification.show("Selected Address deleted Succesfully", 1000, Position.BOTTOM_END);
						 }
						 else {
							 addressListDialog.close();
							 Notification.show("No Addresses found", 1000, Position.BOTTOM_END);
						 }
						
					 });
					 return deleteAddressButton;
				 });
				 addressGrid.getColumns().get(3).setHeader("Edit");
				 addressGrid.getColumns().get(4).setHeader("Delete");
				  addressListDialog = new Dialog();
				 addressListDialog.setWidth("600px");
				 VerticalLayout addressListVerticalLayout = new VerticalLayout();
				 addressListVerticalLayout.setWidth("100%");
				 Button closeGridButton = new Button("close");
				 closeGridButton.addClickListener(e4->{
					addressListDialog.close();
				});
				addressListVerticalLayout.add(closeGridButton);
				editAddressLayout();
				addressListVerticalLayout.add(addressDetailsLayout2);
				addressDetailsLayout2.setVisible(false);
				addressListVerticalLayout.add(addressGrid);
				addressListDialog.add(addressListVerticalLayout);
				addressListDialog.open();
				}
				else {
					System.out.println("addressList is null");
				}
			 });
			 return viewButton;
		 }); 
		 
//		System.out.println( grid.getColumns() );
		 updateButton.addClickListener(e->{
			 String name = nameField.getValue();
			String salary = salaryField.getValue();
			 if(name==null||name.trim().equalsIgnoreCase("")||salary==null||salary.trim().equalsIgnoreCase("")) {
				 Notification.show("please fill name,salary fields");
			 }else {
				 Pattern p = Pattern.compile("[0-9]{1,7}");//. represents single character  
					Matcher m = p.matcher(salary);  
					boolean b = m.matches(); 	 
					System.out.println("boolean pattern "+b); 
				 
					 Pattern p2 = Pattern.compile("[a-zA-Z ]{3,20}");//. represents single character  
						Matcher m2 = p2.matcher(name);  
						boolean b2 = m2.matches(); 	 
						System.out.println("boolean pattern2 "+b2); 
			if(b&&b2) {	 
			user.setName(name);
			user.setSalary(salary);
			user.setEmpId(editEmpId);
			javaMongoDemo.updateDocument(user,editNameValue,editSalaryValue);
			userCollection  =  javaMongoDemo.getUserCollection();
			grid.setItems(userCollection);
			updateButton.setVisible(false);
			cancelButton.setVisible(false);
			submitButton.setVisible(true);
			clearButton.setVisible(true);
			nameField.clear();
			salaryField.clear();
			if(userCollection.size()<7) {
//				grid.setHeight(userCollection.size()+"");
			}else {
//				grid.setHeight(6+"");
			}
			Notification.show("Details successfully updated",1000,Position.BOTTOM_END);
			
			 
			}else {
				Notification n = new Notification("please enter name must be in letters and salary must be in digits");
				n.setDuration(1000);
				n.setPosition(Position.BOTTOM_END);
//				Notification.show("please enter name must be in letters and salary must be in digits");
				n.open();
			}
			 
			 
			 
			 }
		 });
		 
		 cancelButton.addClickListener(e->{
			 updateButton.setVisible(false);
			 cancelButton.setVisible(false);
			 submitButton.setVisible(true);
			 cancelButton.setVisible(true);
			 nameField.clear();
			 salaryField.clear();
		 });
		 System.out.println("userCollection size  "+userCollection+"   "+userCollection.size());
		 if(userCollection.size()!=0) {
		grid.setItems(userCollection);
		if(userCollection.size()<7) {
//			grid.setHeight(userCollection.size()+"");
		}else {
//			grid.setHeight(6+"");
		}
		 }
//	grid.getColumnByKey("name").setFlexGrow(2);//setExpandRatio(1);
//	grid.getColumnByKey("salary").setFlexGrow(2);
//	grid.getColumnByKey("date").setFlexGrow(6);
//	grid.getColumnByKey("id").setFlexGrow(6);
//	Column c = (Column) grid.getColumns().get(4);
//	c.setFlexGrow(1);
    SearchLayout();
//    mainLayout.setMargin(false);
    grid.setPageSize(5);
//    grid.setPaginatorSize(5);
    grid.setHeightByRows(true);
    grid.getColumns().get(0).setFlexGrow(1);//ByKey("date").setFlexGrow(2);
//    grid.getColumns().get(1).
    grid.getColumns().get(1).setFlexGrow(1);
    grid.getColumns().get(2).setFlexGrow(1);
    grid.getColumns().get(3).setFlexGrow(0);//.setHeader("Edit");
    grid.getColumns().get(4).setFlexGrow(0);//.setHeader("Delete");
    grid.getColumns().get(5).setFlexGrow(0);//.setHeader("Add Address");
    grid.getColumns().get(6).setFlexGrow(0);//.setHeader("View");
//    grid.setHeight("3");
//    mainLayout.add(searchLayout);
//		mainLayout.add(grid);
		VerticalLayout v = new VerticalLayout();
		v.setWidth("100%");
		v.add(searchLayout);
		v.add(grid);
		v.setSpacing(false);
		v.getStyle().set("padding-top", "0px");
		v.getStyle().set("padding-bottom", "0px").set("padding-right", "30px");
		
	mainLayout.add(v);
		
		
/*		PaginatedGrid<User> grid2 = new PaginatedGrid<>();

		grid2.addColumn(User::getDate).setHeader("Date").setSortable(true);
		grid2.addColumn(User::getId).setHeader("ID");
		
		
		grid2.addColumn(User::getName).setHeader("Name").setSortable(true);
		grid2.addColumn(User::getSalary).setHeader("Salary").setSortable(true);
		

		grid2.setItems(userCollection);
		
		// Sets the max number of items to be rendered on the grid for each page
		grid2.setPageSize(3);
		
		// Sets how many pages should be visible on the pagination before and/or after the current selected page
		grid2.setPaginatorSize(5);
		
		mainLayout.add(grid2);
		*/
//		mainLayout.setJustifyContentMode(JustifyContentMode.AROUND);
//		mainLayout.setFlexGrow(0, formField);
//		mainLayout.setFlexGrow(0, searchLayout);
//		mainLayout.setFlexGrow(1, grid );
//        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.);
//		mainLayout.setFlexGrow(1, searchLayout);
//		mainLayout.setFlexGrow(1,grid);
		
//		formField.setBoxSizing(BoxSizing.CONTENT_BOX);
//		formField.setAlignItems(Alignment.BASELINE);
		
//		mainLayout.setSizeUndefined();

		return mainLayout;
	}
public HorizontalLayout SearchLayout() {
	searchLayout = new HorizontalLayout();
	searchLayout.setWidth("100%");
	searchLayout.setMargin(false);
	searchLayout.setPadding(false);
	searchLayout.addClassName("searchLayout");
    
	 Icon icon = VaadinIcon.SEARCH.create();
		searchField = new TextField();
		searchField.setSuffixComponent(icon);
	searchField.setPlaceholder("Name");
	searchField.setValueChangeMode(ValueChangeMode.EAGER);
	searchField.getStyle().set("height", "30px");//.set("background", "#fff").set("position", "absolute").set("z-index", "2");
//	searchField.StyleName(ValoTheme.TEXTFIELD_SMALL);
	searchLayout.add(searchField);

	searchField.addValueChangeListener(
//			e->{
//		List list= javaMongoDemo.searchQuery(e.getValue());
//		grid.setItems(list);
//		if(e.getValue().equals("")) {
//			userCollection  =  javaMongoDemo.getUserCollection();
//			grid.setItems(userCollection);
//			if(userCollection.size()<7) {
//				grid.setHeightByRows(userCollection.size());
//			}else {
//				grid.setHeightByRows(6);
//			}
//		}
		
//	}
	this::onNameFilterTextChange);
//	 Icon icon = VaadinIcon.SEARCH.create();
	
	Label spaceLabel = new Label(" ");
	searchLayout.add(spaceLabel);
	Label recordsLabel = new Label("No. of Records per Page:");
	searchLayout.add(recordsLabel);
	recordsLabel.getStyle().set("font-size", "14px");
	searchLayout.setVerticalComponentAlignment(Alignment.CENTER, recordsLabel);
		gotoField = new TextField();
		gotoField.setWidth("45px");
		gotoField.setValue(5+"");
		gotoField.setPattern("[0-9]{1,2}");
		gotoField.setPreventInvalidInput(true);
		gotoField.getStyle().set("height", "30px");
		gotoField.setValueChangeMode(ValueChangeMode.EAGER);
//		gotoField.setPlaceholder("No. of Rows");
		searchLayout.add(gotoField);
		gotoField.addValueChangeListener(e->{
			
			if(e.getValue().equalsIgnoreCase("")) {}else {
				int no = Integer.parseInt(e.getValue());
				if(no!=0)
			grid.setPageSize(no);
			gotoField.setValue(no+"");}
		});
	searchLayout.setFlexGrow(1, spaceLabel);
		
//		searchLayout.setAlignSelf(Alignment., gotoField);
//		searchLayout.setJustifyContentMode(JustifyContentMode.);
		searchLayout.setVerticalComponentAlignment(Alignment.START, searchField);
	searchLayout.setVerticalComponentAlignment(Alignment.END, gotoField);
	return searchLayout;
}
	
private void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
    ListDataProvider<User> dataProvider = (ListDataProvider<User>) grid.getDataProvider();
    dataProvider.setFilter(User::getName, s -> caseInsensitiveContains(s, event.getValue()));
}

private Boolean caseInsensitiveContains(String where, String what) {
    return where.toLowerCase().contains(what.toLowerCase());
}



private void editAddressLayout() {
	  addressDetailsLayout2 = new VerticalLayout();
		 addressLine4 = new TextField("Street/LandMark");
		 addressLine5 = new TextField("City/Town");
		 addressLine6 = new TextField("State");
		 addressLine7 = new TextField("address Id");
		 addressLine8 = new TextField("emp Id");
		 addressLine7.setVisible(false);
		 addressLine8.setVisible(false);
		 HorizontalLayout butLayout = new HorizontalLayout();
		 submitEditAddressDetailsButton = new Button("Submit");
		 Button cancelAddressButton = new Button("Cancel");
		 butLayout.add(submitEditAddressDetailsButton,cancelAddressButton);
		 addressDetailsLayout2.add(addressLine4,addressLine5,addressLine6,butLayout);
		 cancelAddressButton.addClickListener(e->{
			 addressLine4.clear();
			 addressLine5.clear();
			 addressLine6.clear();
			 addressLine7.clear();
			 addressLine8.clear();
		 });
		 submitEditAddressDetailsButton.addClickListener(e2->{
			 if(addressLine4.getValue().trim().equalsIgnoreCase("")||addressLine4.getValue()==null||addressLine5.getValue().trim().equalsIgnoreCase("")||addressLine5.getValue()==null||addressLine6.getValue().trim().equalsIgnoreCase("")||addressLine6.getValue()==null) {
				 Notification.show("Please enter all fields", 1000, Position.BOTTOM_END);
			 }
			 else {
				 javaMongoDemo = new JavaMongoDemo();
				 Address address = new Address();
				 address.setAddressId(addressLine7.getValue());
				 address.setStreet(addressLine4.getValue());
				 address.setCity(addressLine5.getValue());
				 address.setState(addressLine6.getValue());
				 javaMongoDemo.updateAddress(address);
				 addressDetailsLayout2.setVisible(false);
//				 javaMongoDemo.getAddressDetails(Long.parseLong(addressLine8.getValue()));
				 addressGrid.setItems( javaMongoDemo.getAddressDetails(Long.parseLong(addressLine8.getValue())));
				 Notification.show("Address Successfully Updated", 1000, Position.BOTTOM_END);
			 }
		 });
		                           
                                  }
}

