package MaterialSwing;

import MaterialEfectos.ElevationEffect;
import MaterialEfectos.MaterialShadow;
import MaterialEfectos.RippleEffect;
import MaterialEfectos.Roboto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.plaf.basic.BasicPanelUI;

/**
 * A Material Design button.
 *
 * @see
 * <a href="https://www.google.com/design/spec/components/buttons.html">Buttons
 * (Google design guidelines)</a>
 */
public class MaterialPanel extends JPanel {

    private ElevationEffect elevation;

    /**
     * Creates a new button.
     */
    public MaterialPanel() {
        elevation = ElevationEffect.applyTo(this, 1);
        setOpaque(false);

        setFont(Roboto.MEDIUM.deriveFont(14f));

        setUI(new BasicPanelUI() {
            @Override
            public boolean contains(JComponent c, int x, int y) {
                return x > MaterialShadow.OFFSET_LEFT && y > MaterialShadow.OFFSET_TOP
                        && x < getWidth() - MaterialShadow.OFFSET_RIGHT && y < getHeight() - MaterialShadow.OFFSET_BOTTOM;
            }
        });
    }





    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        elevation.paint(g);

        final int offset_lr = MaterialShadow.OFFSET_LEFT + MaterialShadow.OFFSET_RIGHT;
        final int offset_td = MaterialShadow.OFFSET_TOP + MaterialShadow.OFFSET_BOTTOM;

        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - offset_lr, getHeight() - offset_td, 5, 5));
               
    }

    @Override
    protected void paintBorder(Graphics g) {
        //intentionally left blank
    }

    
}
