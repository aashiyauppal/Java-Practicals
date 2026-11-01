import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginApplet extends Applet implements ActionListener
{
    Label l1, l2, msg;
    TextField t1, t2;
    Button b1;

    public void init()
    {
        setLayout(new FlowLayout());

        l1 = new Label("Name:");
        t1 = new TextField(20);

        l2 = new Label("Password:");
        t2 = new TextField(20);
        t2.setEchoChar('*');

        b1 = new Button("Submit");
        msg = new Label("");

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b1);
        add(msg);

        b1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        String name = t1.getText();
        String password = t2.getText();

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/LoginDB",
                "root",
                "your_password"
            );

            String query = "INSERT INTO UserDetails (Name, Password) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, password);

            ps.executeUpdate();

            msg.setText("Data inserted successfully!");

            ps.close();
            con.close();
        }
        catch(Exception ex)
        {
            msg.setText("Error: " + ex.getMessage());
        }
    }
}