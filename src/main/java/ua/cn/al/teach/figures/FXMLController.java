package ua.cn.al.teach.figures;

import ua.cn.al.teach.util.ColorUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import ua.cn.al.teach.figures.engine.FXEngine;
import ua.cn.al.teach.figures.engine.Graphics;
import ua.cn.al.teach.figures.shapes.Composite;
import ua.cn.al.teach.figures.shapes.Point;
import ua.cn.al.teach.figures.shapes.PolyLine;
import ua.cn.al.teach.figures.shapes.Polygon;
import ua.cn.al.teach.figures.shapes.Shape;
import ua.cn.al.teach.util.ShapeType;
import ua.cn.al.teach.figures.shapes.Sheet;
import ua.cn.al.teach.util.TypeChecker;
import ua.cn.al.teach.util.ShapeFactory;
import ua.cn.al.teach.util.StateStack;

public class FXMLController implements Initializable, Serializable {
    @FXML
    private Canvas MyCanva;
    @FXML
    private Button button;
    @FXML
    private ColorPicker colorpick1;
    @FXML
    private ColorPicker colorpick2;
    @FXML
    private Pagination pageNav1;
    @FXML
    private Pagination pageNav2;
    @FXML
    private Label ZIndexLabel;
    @FXML
    private Label FirsLabBottom;
    @FXML
    private Label SecondLabBottom;
    @FXML
    private Label FValueLable;
    @FXML
    private Label SValueLable;
    @FXML
    private Slider FirstSlider;
    @FXML
    private Slider SecondSlider;
    @FXML
    private ContextMenu menu;
    private BorderPane rootpane;
    private final FXEngine ge = new FXEngine();
    private final List<Point> TempPoinst_List = new ArrayList<>();
    private Composite TempCircle; // for select, rotate, maybe resize
    private Composite TempGroup=new Composite(); 
    private Polygon TempGon;
    private Sheet ShapesList;
    private Shape prev_shapes;
    private Color DrawColor;
    private File my_f=null;
    private Color FillColor = Color.WHITE;
    private int Lsize = 1;
    private int mode; 
    private JAXBContext context;
    private Schema ShemForSerial;
    static {
        try {
            Class.forName("ua.cn.al.teach.figures.shapes.Line");
            Class.forName("ua.cn.al.teach.figures.shapes.PolyLine");
            Class.forName("ua.cn.al.teach.figures.shapes.Triangle");
            Class.forName("ua.cn.al.teach.figures.shapes.Rectangle");
            Class.forName("ua.cn.al.teach.figures.shapes.Bow");
            Class.forName("ua.cn.al.teach.figures.shapes.Circle");
            Class.forName("ua.cn.al.teach.figures.shapes.Bezier");
            Class.forName("ua.cn.al.teach.figures.shapes.CubicBezier");
        } catch (ClassNotFoundException any) {
            any.printStackTrace();
        }
    }

    private void Reset_color_and_line_Width() {
        colorpick1.setValue(Color.BLACK);
        DrawColor = colorpick1.getValue();
        colorpick2.setValue(Color.WHITE);
        FillColor = colorpick2.getValue();
        pageNav1.setCurrentPageIndex(0);
        Lsize = 1;
    }
    
     @FXML
    private void showMenu(ContextMenuEvent e) {
        if(mode==0 && (prev_shapes!=null || !TempGroup.isEmpty()))menu.show(rootpane, e.getScreenX(), e.getScreenY());
        e.consume();
    }
    @FXML
    private void Del(ActionEvent e) {
        rootpane.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED,"delete", "delete", KeyCode.DELETE, false, false, false, false));
    }
    @FXML
    private void Group(ActionEvent e) {
        ShapesList.AddGroup(TempGroup);
        TempCircle.deleteAll();
        menu.hide();
        ShapesList.show(ge);
    }
     @FXML
    private void Ungroup(ActionEvent e) {
        if(prev_shapes instanceof Composite)ShapesList.UnGroup((Composite)prev_shapes);
        TempCircle.deleteAll();
        menu.hide();
        ShapesList.show(ge);
    }
    @FXML
    private void Press_vbox_button(ActionEvent e) {
        menu.hide();
        Reset_color_and_line_Width();
        if (mode != 0) {
            ShapesList.delete(prev_shapes);
        }
        mode = ((VBox) rootpane.getRight()).getChildren().indexOf(e.getSource());
        if(mode==0){
        pageNav2.setVisible(true);
        ZIndexLabel.setVisible(true);
        }
        else{
        pageNav2.setVisible(false);
        ZIndexLabel.setVisible(false);
        }
        System.out.println(String.valueOf(mode));
        TempPoinst_List.clear();
        TempCircle.deleteAll();
        prev_shapes = null;
        if(mode==4 || mode==7){
        FirsLabBottom.setText("Width:");
        FirstSlider.setValue(50);
        FirstSlider.setVisible(true);
        SecondLabBottom.setText("Height:");
        SecondSlider.setValue(50);
        SecondSlider.setVisible(true);
        FValueLable.setVisible(true);
        SValueLable.setVisible(true);
        }
        else if(mode==5){
        FirsLabBottom.setText("Radius:");
        FirstSlider.setValue(20);
        FirstSlider.setVisible(true);
        SecondLabBottom.setText("");
        SecondSlider.setVisible(false);
        FValueLable.setVisible(true);
        SValueLable.setVisible(false);
        }
        else{
        FirsLabBottom.setText("");
        FirstSlider.setVisible(false);
        SecondLabBottom.setText("");
        SecondSlider.setVisible(false);
        FValueLable.setVisible(false);
        SValueLable.setVisible(false);
        }
        ShapesList.show(ge);
    }

    @FXML
    private void ColorSelect(ActionEvent e) {
        DrawColor = ((ColorPicker) e.getSource()).getValue();
        if (mode == 0 && prev_shapes != null) {
            prev_shapes.setColor(DrawColor);
            ShapesList.show(ge);
        }
    }

    @FXML
    private void FillSelect(ActionEvent e) {
        FillColor = ((ColorPicker) e.getSource()).getValue();
        if (mode == 0 && prev_shapes != null) {
            prev_shapes.setFill(FillColor);
            ShapesList.show(ge);
        }
    }

    @FXML
    private void LWidthChange(MouseEvent e) {
        Lsize = pageNav1.getCurrentPageIndex() + 1;
        if (mode == 0 && prev_shapes != null) {
            prev_shapes.setlineWidth(Lsize);
            ShapesList.show(ge);
        TempCircle.deleteAll();
        prev_shapes.AddTempCircle(TempCircle);
        }
    }
    @FXML
    private void ZIndexChange(MouseEvent e) {
        if (mode == 0 && prev_shapes != null) {
            ShapesList.ChangeZIndex(prev_shapes, pageNav2.getCurrentPageIndex() + 1);
            ShapesList.show(ge);
        }
    }
    @FXML
    private void FSliderChange(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val){
        FValueLable.setText(String.valueOf(new_val.intValue()));
    }
    @FXML
    private void SSliderChange(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val){
    SValueLable.setText(String.valueOf(new_val.intValue()));
    }
    
    @FXML
    private void NewCanva() {
        ge.Clear();
        ShapesList.deleteAll();
        ShapesList.add(TempCircle);
    }

    @FXML
    private void SaveFile() {
        if(my_f==null){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save to file");
            my_f = fileChooser.showSaveDialog(rootpane.getScene().getWindow());
            if (my_f == null) return;
            }
                    TempCircle.deleteAll();
        try {
           if(context==null)context=JAXBContext.newInstance(Sheet.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setSchema(ShemForSerial);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
           marshaller.marshal(ShapesList, my_f);
        } catch (JAXBException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        my_f=null;
    }

    @FXML
    private void LoadFile() {
            if(my_f==null){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load from file");
            my_f = fileChooser.showOpenDialog(rootpane.getScene().getWindow());
            if (my_f == null) return;
            }
       try {
            if(context==null)
            context=JAXBContext.newInstance(Sheet.class);
            Unmarshaller um = context.createUnmarshaller();
            um.setSchema(ShemForSerial);
            ShapesList=(Sheet)um.unmarshal(my_f);
            TempCircle=(Composite)ShapesList.get(0);
        } catch (JAXBException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(ShapesList!=null)ShapesList.show(ge);
        my_f=null;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            this.ShemForSerial=SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File("./src/main/resources/xsd/schema1.xsd"));
        } catch (SAXException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    Reset_color_and_line_Width();
    }
    
    @FXML
    public void setListener(BorderPane root) {
        if (ShapesList == null) {
            ShapesList = new Sheet();
        }
        if (TempCircle == null) {
            TempCircle = new Composite();
            TempCircle.setZIndex(-2);
        }
        ShapesList.add(TempCircle);
        rootpane = root;
        ge.setGC(MyCanva.getGraphicsContext2D());
        Graphics.getInstance().addEngine(ge, "JavaFX");
        rootpane.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> 
        {
            ((Canvas) rootpane.getCenter()).setWidth(((Canvas) rootpane.getCenter()).getWidth() + (newValue.intValue() - oldValue.intValue()));
            if (ShapesList != null) {
                ShapesList.show(ge);
            }
        });
        rootpane.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
        {
            ((Canvas) rootpane.getCenter()).setHeight(((Canvas) rootpane.getCenter()).getHeight() + (newValue.intValue() - oldValue.intValue()));
            if (ShapesList != null) {
                ShapesList.show(ge);
            }
        });
        MyCanva.setOnMouseMoved((MouseEvent e) -> {
            if (mode != 0) {
                if (prev_shapes != null){
                    if(!StateStack.XMLState.empty() && StateStack.ShapeSize!=ShapesList.getSize())StateStack.XMLState.pop();
                    ShapesList.delete(prev_shapes);
                }
                TempPoinst_List.add(TempPoinst_List.size(), new Point((int) e.getX(), (int) e.getY()));
                prev_shapes = ShapeFactory.getInstance().createShapebyID(String.valueOf(mode), TempPoinst_List, FirstSlider.getValue(), SecondSlider.getValue());
                if (prev_shapes != null) {
                    StateStack.XMLState.push(String.valueOf(StateStack.XMLState.size()+1)+".xml");
                    my_f= new File("./src/main/resources/xml/stack/"+StateStack.XMLState.peek());
                    this.SaveFile();
                    ShapesList.add(prev_shapes);
                    prev_shapes.setColor(DrawColor);
                    prev_shapes.setFill(FillColor);
                    prev_shapes.setlineWidth(Lsize);
                }
                ShapesList.show(ge);
                TempPoinst_List.remove(TempPoinst_List.size() - 1);
            }
        });
        MyCanva.setOnMousePressed((MouseEvent e) -> {
            if (mode == 0) {         
         StateStack.XMLState.push(String.valueOf(StateStack.XMLState.size()+1)+".xml");
         my_f= new File("./src/main/resources/xml/stack/"+StateStack.XMLState.peek());
         this.SaveFile();
                if (e.getButton() == MouseButton.PRIMARY) {
                 TempCircle.deleteAll();
                 menu.hide();
                 TempPoinst_List.add(0, new Point((int) e.getX(), (int) e.getY()));
                 prev_shapes = ShapesList.SelectShape(TempPoinst_List.get(0), TempCircle);
                    if (prev_shapes != null || TempGroup.getSize()==1) {
                    System.out.println("You select shape");
                    colorpick1.setValue(prev_shapes.getColor());
                    colorpick2.setValue(prev_shapes.getFill());
                    pageNav1.setCurrentPageIndex(prev_shapes.getlineWidth() - 1);
                    pageNav2.setCurrentPageIndex(prev_shapes.getZIndex() - 1);
                } else {
                    Reset_color_and_line_Width();
                    System.out.println("You not select shape");
                }
                }
            }
            else{
                if(e.getButton() == MouseButton.PRIMARY) {
            StateStack.ShapeSize=ShapesList.getSize();
                if(TypeChecker.CurType==ShapeType.Triangle && TempPoinst_List.size()==2) prev_shapes=null;
                if (mode != 0 && TypeChecker.CurType!=ShapeType.PolyLine && TypeChecker.CurType!=ShapeType.Polygon &&
                TypeChecker.CurType!=ShapeType.Triangle) prev_shapes = null;
                TypeChecker.CurType.Click(prev_shapes, TempPoinst_List, TempCircle, e);
            }
            if (e.getButton() == MouseButton.SECONDARY) {
                if (prev_shapes != null && TypeChecker.CurType!=ShapeType.PolyLine && TypeChecker.CurType!=ShapeType.Polygon)
                    ShapesList.delete(prev_shapes);
                else if(prev_shapes != null)
                ((PolyLine)prev_shapes).removePoint(TempPoinst_List.size());
                prev_shapes = null;
                TempPoinst_List.clear();
            }
            }
            ShapesList.show(ge);
        });
        MyCanva.setOnMouseDragged((MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY){
            if (mode == 0) {
                TempCircle.deleteAll();
                if( prev_shapes != null){
                    System.out.println("We moved line");
                    prev_shapes.moveTo(new Point((int) event.getX() - TempPoinst_List.get(0).getX(), (int) event.getY() - TempPoinst_List.get(0).getY()));
                    TempPoinst_List.add(0, new Point((int) event.getX(), (int) event.getY()));
                }
                else{
                    Point tmpPoint=TempPoinst_List.get(0);
                    TempPoinst_List.add(1, new Point((int)event.getX(), (int)event.getY()));
                    TempPoinst_List.add(2, new Point(tmpPoint.getX()+(TempPoinst_List.get(1).getX()-tmpPoint.getX()), tmpPoint.getY()));
                    TempPoinst_List.add(3,new Point(tmpPoint.getX(),tmpPoint.getY()+(TempPoinst_List.get(1).getY()-tmpPoint.getY())));
                    TempGon=new Polygon(tmpPoint, TempPoinst_List.get(2), TempPoinst_List.get(1), TempPoinst_List.get(3));
                    TempGon.setColor(Color.BLUE);
                    ShapesList.add(ShapesList.getSize(), TempGon);
                }
            }
            ShapesList.show(ge);
            ShapesList.delete(TempGon);
            }
        });
        MyCanva.setOnMouseReleased((MouseEvent event) -> {
            if (mode == 0){
            if(TempGon!=null){
            TempGroup=new Composite();
            int add_x=TempPoinst_List.get(1).getX()-TempPoinst_List.get(0).getX();
            while(add_x!=0){
            int add_y=TempPoinst_List.get(1).getY()-TempPoinst_List.get(0).getY();
            while(add_y!=0){
            ShapesList.GroupSelected(new Point(TempPoinst_List.get(0).getX()+add_x, TempPoinst_List.get(0).getY()+add_y), TempGroup, TempCircle);
            if(add_y>0)add_y--;
            else add_y++;
            }
            if(add_x>0)add_x--;
            else add_x++;
            }
                            TempGon=null;
                if(TempGroup.getSize()==1){
                prev_shapes=TempGroup.get(0);
                TempGroup.deleteAll();
                }
                }
                TempPoinst_List.clear();
            }
            ShapesList.show(ge);
        });
        rootpane.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode()==KeyCode.DELETE && (prev_shapes!=null)){
                TempCircle.deleteAll();
                ShapesList.delete(prev_shapes);
                prev_shapes=null;
                TempPoinst_List.clear();
            }
            else if(event.getCode()==KeyCode.Z && event.isControlDown() && !StateStack.XMLState.isEmpty()){
                rootpane.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED,"delete", "delete", KeyCode.DELETE, false, false, false, false));
                System.out.println("We Cntrl+Z");
                my_f= new File("./src/main/resources/xml/stack/"+StateStack.XMLState.pop());
                this.LoadFile();
            }
            ShapesList.show(ge);
        });
    }
}
