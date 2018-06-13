/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaterialSwing;

import MaterialEfectos.MaterialShadow;
import MaterialEfectos.Roboto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicPanelUI;

/**
 *
 * @author eldoc
 */
public class MaterialSpinner extends JPanel {

    private Color rippleColor = Color.WHITE;

    //////////////////////////////////////////////
    private JButton btnUp;
    private JButton btnDown;
    private MaterialSwing.MaterialTextField txtText;

    public MaterialSpinner() {

        btnUp = new JButton();
        btnDown = new JButton();
        txtText = new MaterialSwing.MaterialTextField();

        btnUp.setBackground(this.getBackground());
        btnUp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUp.setText("+");
        btnUp.setBorder(null);
        btnUp.setBorderPainted(false);

        btnUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num;
                if (txtText.getText().equals("")) {
                    txtText.setText("0.0");
                }
                num = Double.parseDouble(txtText.getText());
                txtText.setText(String.valueOf(num + 1));
            }
        });

        btnDown.setBackground(this.getBackground());
        btnDown.setText("-");
        btnDown.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDown.setText("-");
        btnDown.setBorder(null);
        btnDown.setBorderPainted(false);
        btnDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num;
                if (txtText.getText().equals("")) {
                    txtText.setText("0.0");
                }
                num = Double.parseDouble(txtText.getText());
                if (num > 0) {
                    txtText.setText(String.valueOf(num - 1));
                } else {
                    getToolkit().beep();
                }

            }
        });


        txtText.setBackground(this.getBackground());
        txtText.setText("0.0");

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(this);
        setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addComponent(btnUp, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDown, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );


        txtText.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) && (caracter != '.')) {
                    getToolkit().beep();
                    e.consume();  // ignorar el evento de teclado
                }
                if (caracter == '.' && txtText.getText().contains(".")) {
                    getToolkit().beep();

                    e.consume();
                }
            }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        setFont(Roboto.MEDIUM.deriveFont(14f));

        setUI(new BasicPanelUI() {
            @Override
            public boolean contains(JComponent c, int x, int y) {
                return x > MaterialShadow.OFFSET_LEFT && y > MaterialShadow.OFFSET_TOP
                        && x < getWidth() - MaterialShadow.OFFSET_RIGHT && y < getHeight() - MaterialShadow.OFFSET_BOTTOM;
            }
        });
    }

    /**
     * @param num
     */
    public void setValue(Double num) {
        txtText.setText(num.toString());
    }

    /**
     * @return
     */
    public double getValue() {
        return Double.parseDouble(txtText.getText());
    }

    @Override
    public void setCursor(Cursor cursor) {
        super.setCursor(cursor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        final int offset_lr = MaterialShadow.OFFSET_LEFT + MaterialShadow.OFFSET_RIGHT;
        final int offset_td = MaterialShadow.OFFSET_TOP + MaterialShadow.OFFSET_BOTTOM;

        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 3, 3));

        g2.setColor(new Color(rippleColor.getRed() / 255f, rippleColor.getBlue() / 255f, rippleColor.getBlue() / 255f, 0.12f));

        Color bg = getBackground();
        g2.setColor(new Color(bg.getRed() / 255f, bg.getGreen() / 255f, bg.getBlue() / 255f, 0.6f));
        //g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - offset_lr, getHeight() - offset_td, 3, 3));

        FontMetrics metrics = g.getFontMetrics(getFont());

        g2.setFont(getFont());
        if (isEnabled()) {
            g2.setColor(getForeground());
        } else {
            Color fg = getForeground();
            g2.setColor(new Color(fg.getRed() / 255f, fg.getGreen() / 255f, fg.getBlue() / 255f, 0.6f));
        }

    }

    @Override
    protected void paintBorder(Graphics g) {
        //intentionally left blank
    }

}
