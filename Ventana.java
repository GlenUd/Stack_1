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

    private JButton btnMostrar;
    private JButton btnEliminar;
    private JLabel lblAlmacenar;

    Pila pila = new Pila();
    public Ventana() {
        actualizarUI();

        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scodigo = txtCodigo.getText().trim();
                String titulo  = txtTitulo.getText().trim();
                String mensaje = txtMensae.getText().trim();
                if (scodigo.isEmpty()) {
                    JOptionPane.showMessageDialog(Principal, "El campo código no puede estar vacío",
                            "Dato requerido", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int codigo;
                try {
                    codigo = Integer.parseInt(scodigo);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Principal, "Debe ingresar un número entero válido",
                            "Error de formato", JOptionPane.ERROR_MESSAGE);
                    txtCodigo.requestFocus();
                    txtCodigo.selectAll();
                    return;
                }
                if (titulo.isEmpty()) {
                    JOptionPane.showMessageDialog(Principal, "El título no puede estar vacío",
                            "Dato requerido", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (mensaje.isEmpty()) {
                    JOptionPane.showMessageDialog(Principal, "El mensaje no puede estar vacío",
                            "Dato requerido", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Publicacion post = new Publicacion(codigo, titulo, mensaje);
                pila.push(post);
                txtListar.setText(pila.toString());
                limpiarCampos();
                actualizarUI();
            }
        });
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Publicacion cima = pila.peek();
                    JOptionPane.showMessageDialog(Principal,
                            "Elemento en la cima:\n" + cima.toString(),
                            "Peek", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Principal,
                            "La pila está vacía. No hay elementos para mostrar.",
                            "Pila vacía", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Publicacion eliminado = pila.pop();
                    JOptionPane.showMessageDialog(Principal,
                            "Se eliminó de la cima:\n" + eliminado.toString(),
                            "Elemento eliminado", JOptionPane.INFORMATION_MESSAGE);

                    txtListar.setText(pila.toString());
                    actualizarUI();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Principal,
                            "La pila está vacía. No hay elementos para eliminar.",
                            "Pila vacía", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    private void limpiarCampos() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtMensae.setText("");
        txtCodigo.requestFocus();
    }
    private void actualizarUI() {
        int n = pila.cantidad();
        if (lblAlmacenar != null) lblAlmacenar.setText("Almacenados: " + n);

        boolean hayDatos = n > 0;
        if (btnMostrar != null)  btnMostrar.setEnabled(hayDatos);
        if (btnEliminar != null) btnEliminar.setEnabled(hayDatos);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ventana");
            frame.setContentPane(new Ventana().Principal);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
