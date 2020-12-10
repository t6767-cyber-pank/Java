package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;

import java.awt.*;
import java.sql.*;

public class Controller {
    public int lang=0;
    public int tema=0;
    public int glav=0;

    public int langrb=0;
    public int temarb=0;
    public int glavrb=0;

    public int dob=0;
    public int ud=0;
    public int izm=0;
    public Connection con=null;
    public Statement stmt=null;
    public PreparedStatement mystmt;
    public ResultSet res=null;
    //public String url="jdbc:sqlite:/media/t67/74EE4ABBEE4A7580/Users/t67/IdeaProjects/untitled1/t67";
    //public String url="jdbc:sqlite:C:\\Users\\t67\\IdeaProjects\\untitled1\\t67";
    public String url="jdbc:sqlite:t67";
    @FXML
    private Button button;
    @FXML
    private Button buttond1;
    @FXML
    private ComboBox cb;
    @FXML
    private ComboBox cb1;
    @FXML
    private ComboBox cb2;
    @FXML
    private HTMLEditor edi;
    @FXML
    private TextArea edi1;
    @FXML
    private Pane panel1;
    @FXML
    private TextField textd;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;

    @FXML
    public void onClickB1(){
        try
        {
            rb3.setSelected(false);
            rb2.setSelected(false);
            rb1.setSelected(false);
            buttond1.setText("Выберите действие");
            button.getStyleClass().add("bluebutton");

            langrb=0;
            temarb=0;
            glavrb=0;
            lang=0;
            tema=0;
            glav=0;
            dob=0;
            ud=0;
            izm=0;
            panel1.setVisible(false);
            edi.setVisible(true);
            cb.getItems().clear();
            cb1.getItems().clear();
            cb2.getItems().clear();
            edi.setHtmlText("");
            Driver d=(Driver) Class.forName("org.sqlite.JDBC").newInstance();
            con=DriverManager.getConnection(url);
            String sql="select * from lang ORDER by lang";
            stmt=con.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next())
            {
                cb.getItems().add(res.getString("lang"));
            }
        } catch(SQLException e)
        {System.out.println("ошибка CB1_1");} catch (Exception e) { System.out.println("ошибка CB1_2");}
        finally
        {
            try
            {
                if(res!=null) res.close();
                if(stmt!=null) stmt.close();
                if(con!=null) con.close();
                button.setText("Выбирай язык");
            } catch (Exception e) {System.out.println("ошибка CB1_3");}
        }
    }

    @FXML
    public void CB(){
        try
        {
            Driver d=(Driver) Class.forName("org.sqlite.JDBC").newInstance();
            con=DriverManager.getConnection(url);
            mystmt=con.prepareStatement("select * from lang where lang=?");
            mystmt.setString(1, cb.getValue().toString());
            res=mystmt.executeQuery();
            res.next();
            lang=res.getInt("id_lang");
        } catch(SQLException e)
        {System.out.println("ошибка  lang");} catch (Exception e) { System.out.println("ошибка lang");}
        finally
        {
            try
            {
                if(res!=null) res.close();
                if(mystmt!=null) mystmt.close();
                if(con!=null) con.close();
            } catch (Exception e) {System.out.println("ошибка  lang");}
        }
        try
        {
            cb1.getItems().clear();
            cb2.getItems().clear();
            edi.setHtmlText("");
            Driver d=(Driver) Class.forName("org.sqlite.JDBC").newInstance();
            con=DriverManager.getConnection(url);
            mystmt=con.prepareStatement("select *, l.id_lang as language from tema t, lang l where l.id_lang=t.id_lang and lang=? ORDER by t.tema");
            mystmt.setString(1, cb.getValue().toString());
            res=mystmt.executeQuery();
            while(res.next())
            {
                cb1.getItems().add(res.getString("tema"));
            }
            System.out.println("tlang="+lang);
        } catch(SQLException e)
        {System.out.println("ошибка");} catch (Exception e) { System.out.println("ошибка");}
        finally
        {
            try
            {
                if(res!=null) res.close();
                if(mystmt!=null) mystmt.close();
                if(con!=null) con.close();
            } catch (Exception e) {System.out.println("ошибка");}
        }
    }

    @FXML
    public void CB1() {
        try
        {
            cb2.getItems().clear();
            edi.setHtmlText("");
            Driver d=(Driver) Class.forName("org.sqlite.JDBC").newInstance();
            con=DriverManager.getConnection(url);
            mystmt=con.prepareStatement("select *, t.id_tema as tematika from tema t, lang l where l.id_lang=t.id_lang and l.lang=? and t.tema=?");
            mystmt.setString(1, cb.getValue().toString());
            mystmt.setString(2, cb1.getValue().toString());
            res=mystmt.executeQuery();
            res.next();
            tema=res.getInt("tematika");
        } catch(SQLException e)
        {System.out.println("ошибка");} catch (Exception e) { System.out.println("ошибка");}
        finally
        {
            try
            {
                if(res!=null) res.close();
                if(mystmt!=null) mystmt.close();
                if(con!=null) con.close();
            } catch (Exception e) {System.out.println("ошибка");}
        }
        try
        {
            cb2.getItems().clear();
            edi.setHtmlText("");
            Driver d=(Driver) Class.forName("org.sqlite.JDBC").newInstance();
            con=DriverManager.getConnection(url);
            mystmt=con.prepareStatement("select *, t.id_tema as tematika from glava g,tema t, lang l where g.id_tema=t.id_tema and g.id_lang=l.id_lang and l.id_lang=t.id_lang and lang=? and tema=? ORDER by g.glava");
            mystmt.setString(1, cb.getValue().toString());
            mystmt.setString(2, cb1.getValue().toString());
            res=mystmt.executeQuery();
            while(res.next())
            {
                tema=res.getInt("tematika");
                cb2.getItems().add(res.getString("glava"));
            }
            System.out.println("tema"+tema);
        } catch(SQLException e)
        {System.out.println("ошибка");} catch (Exception e) { System.out.println("ошибка");}
        finally
        {
            try
            {
                if(res!=null) res.close();
                if(mystmt!=null) mystmt.close();
                if(con!=null) con.close();
            } catch (Exception e) {System.out.println("ошибка");}
        }
    }

    @FXML
    public void CB2() {
        try
        {
            edi.setHtmlText("");
            Driver d=(Driver) Class.forName("org.sqlite.JDBC").newInstance();
            con=DriverManager.getConnection(url);
            mystmt=con.prepareStatement("select *, g.id_glava as glavar from glava g,tema t, lang l where g.id_tema=t.id_tema and g.id_lang=l.id_lang and l.id_lang=t.id_lang and l.lang=? and t.tema=? and g.glava=? ORDER by g.glava");
            mystmt.setString(1, cb.getValue().toString());
            mystmt.setString(2, cb1.getValue().toString());
            mystmt.setString(3, cb2.getValue().toString());
            res=mystmt.executeQuery();
            res.next();
            glav=res.getInt("glavar");
            edi.setHtmlText(res.getString("vivod"));
            edi1.setText(res.getString("vivod"));
            System.out.println(glav);
        } catch(SQLException e)
        {System.out.println("ошибка");} catch (Exception e) { System.out.println("ошибка");}
        finally
        {
            try
            {
                if(res!=null) res.close();
                if(mystmt!=null) mystmt.close();
                if(con!=null) con.close();
            } catch (Exception e) {System.out.println("ошибка");}
        }
    }

    @FXML
    public void onClickBd(){
        //panel1.setLayoutX(10);
        //panel1.setLayoutY(10);
        dob=1;
        ud=0;
        izm=0;
        panel1.setVisible(true);
        edi.setVisible(false);
        edi1.setVisible(false);
    }

    @FXML
    public void onClickBu(){
        ud=1;
        dob=0;
        izm=0;
        panel1.setVisible(true);
        edi.setVisible(false);
        edi1.setVisible(false);
    }

    @FXML
    public void onClickBi(){
        ud=0;
        dob=0;
        izm=1;
        panel1.setVisible(true);
        edi.setVisible(false);
        edi1.setVisible(false);
    }

    @FXML
    public void onRB1(){
        if (dob>0) { buttond1.setText("Добавить язык"); }
        if (ud>0) { buttond1.setText("Удалить язык"); }
        if (izm>0) { buttond1.setText("Изменить язык"); }
        rb2.setSelected(false);
        rb3.setSelected(false);
        edi1.setVisible(false);
        langrb=1;
        temarb=0;
        glavrb=0;
        try { textd.setText(cb.getValue().toString()); } catch (Exception e){ textd.setText(""); }
    }

    @FXML
    public void onRB2(){
        if (dob>0) { buttond1.setText("Добавить тему"); }
        if (ud>0) { buttond1.setText("Удалить тему"); }
        if (izm>0) { buttond1.setText("Изменить тему"); }
        rb1.setSelected(false);
        rb3.setSelected(false);
        edi1.setVisible(false);
        langrb=0;
        temarb=1;
        glavrb=0;
        try { textd.setText(cb1.getValue().toString()); } catch (Exception e){ textd.setText(""); }
    }

    @FXML
    public void onBclose(){
        buttond1.setText("Выберите действие");
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb3.setSelected(false);
        edi1.setVisible(false);
        edi.setVisible(true);
        panel1.setVisible(false);
        langrb=0;
        temarb=0;
        glavrb=0;
        dob=0;
        ud=0;
        izm=0;
        onClickB1();
    }

    @FXML
    public void onBdiv(){
        edi1.setText(edi1.getText().toString()+"<div align=\"center\"><style type=\"text/css\">#hack {background: orange;} #back {color: #000;}</style><h1 id=\"hack\"></h1><pre></pre></div>");
    }

    @FXML
    public void onBpre(){
        edi1.setText(edi1.getText().toString()+"<pre id=\"back\"></pre>");
    }

    @FXML
    public void onBh1(){
        edi1.setText(edi1.getText().toString()+"<h1 id=\"hack\"></h1>");
    }

    @FXML
    public void onBh2(){
        edi1.setText(edi1.getText().toString()+"<h2></h2>");
    }

    @FXML
    public void onRB3(){
        if (dob>0) { buttond1.setText("Добавить статью"); }
        if (ud>0) { buttond1.setText("Удалить статью"); }
        if (izm>0) { buttond1.setText("Изменить статью"); }
        rb2.setSelected(false);
        rb1.setSelected(false);
        edi1.setVisible(true);
        langrb=0;
        temarb=0;
        glavrb=1;
        try { textd.setText(cb2.getValue().toString()); } catch (Exception e){ textd.setText(""); }
    }

    @FXML
    public void onClickBd1(){
        try
        {
            Driver d=(Driver) Class.forName("org.sqlite.JDBC").newInstance();
            con=DriverManager.getConnection(url);
            String sql="";
            System.out.println("glavrb"+glavrb);
            System.out.println("lang"+lang);
            System.out.println("tema"+tema);
            if (dob>0) {
                if (langrb > 0) {
                    mystmt=con.prepareStatement("insert into lang (lang) values(?)");
                    mystmt.setString(1, textd.getText().toString());
                    mystmt.executeUpdate();
                }
                if (temarb > 0 && lang > 0) {
                    mystmt=con.prepareStatement("insert into tema(tema, id_lang) values(?,?)");
                    mystmt.setString(1, textd.getText().toString());
                    mystmt.setInt(2, lang);
                    mystmt.executeUpdate();
                }
                if (glavrb > 0 && lang > 0 && tema > 0) {
                    mystmt=con.prepareStatement("insert into glava(glava, id_lang, id_tema, vivod) values(?,?,?,?)");
                    mystmt.setString(1, textd.getText().toString());
                    mystmt.setInt(2, lang);
                    mystmt.setInt(3, tema);
                    mystmt.setString(4, edi1.getText().toString());
                    mystmt.executeUpdate();
                }

            }
            if (izm>0) {
                if (langrb > 0 && lang > 0) {
                    mystmt=con.prepareStatement("UPDATE lang set lang=? WHERE id_lang=?");
                    mystmt.setString(1, textd.getText().toString());
                    mystmt.setInt(2, lang);
                    mystmt.executeUpdate();
                }
                if (temarb > 0 && lang > 0 && tema > 0) {
                    mystmt=con.prepareStatement("UPDATE tema set tema=? where id_lang=? and id_tema=?");
                    mystmt.setString(1, textd.getText().toString());
                    mystmt.setInt(2, lang);
                    mystmt.setInt(3, tema);
                    mystmt.executeUpdate();
                }
                if (glavrb > 0 && lang > 0 && tema > 0 && glav > 0) {
                    mystmt=con.prepareStatement("UPDATE glava set glava=?, vivod=? WHERE id_lang=? and id_tema=? and id_glava=?");
                    mystmt.setString(1, textd.getText().toString());
                    mystmt.setInt(3, lang);
                    mystmt.setInt(4, tema);
                    mystmt.setInt(5, glav);
                    mystmt.setString(2, edi1.getText().toString());
                    mystmt.executeUpdate();
                }

            }
            if (ud>0) {
                if (langrb > 0) {
                    mystmt=con.prepareStatement("DELETE FROM glava WHERE id_lang=?");
                    mystmt.setInt(1, lang);
                    mystmt.executeUpdate();
                    mystmt=con.prepareStatement("DELETE FROM tema WHERE id_lang=?");
                    mystmt.setInt(1, lang);
                    mystmt.executeUpdate();
                    mystmt=con.prepareStatement("DELETE FROM lang WHERE id_lang=?");
                    mystmt.setInt(1, lang);
                    mystmt.executeUpdate();
                }
                if (temarb > 0 && lang > 0) {
                    mystmt=con.prepareStatement("DELETE FROM glava WHERE id_tema=?");
                    mystmt.setInt(1, tema);
                    mystmt.executeUpdate();
                    mystmt=con.prepareStatement("DELETE FROM tema WHERE id_tema=?");
                    mystmt.setInt(1, tema);
                    mystmt.executeUpdate();
                }
                if (glavrb > 0 && lang > 0 && tema > 0) {
                    mystmt=con.prepareStatement("DELETE FROM glava WHERE id_glava=?");
                    mystmt.setInt(1, glav);
                    mystmt.executeUpdate();
                }
            }
        } catch(SQLException e)
        {System.out.println("ошибка");} catch (Exception e) { System.out.println("ошибка");}
        finally
        {
            onClickB1();
            try
            {
                if(res!=null) res.close();
                if(mystmt!=null) mystmt.close();
                if(con!=null) con.close();
            } catch (Exception e) {System.out.println("ошибка");}
        }
    }


}
