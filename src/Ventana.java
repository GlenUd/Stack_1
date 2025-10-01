import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel Principal;
    private JTextField txtCodigo;
    private JTextArea txtMensae;
    private JTextField txtTitulo;
    private JButton btnInsertar;
    private JTextArea txtListar;

    Pila pila=new Pila();

    public Ventana() {
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo=Integer.parseInt(txtCodigo.getText());
                String titulo=txtTitulo.getText();
                String mensaje=txtMensae.getText();
                Publicacion post=new Publicacion(codigo,titulo,mensaje);

                pila.push(post);
                txtListar.setText(pila.toString());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
