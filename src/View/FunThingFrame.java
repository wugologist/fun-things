package View;

import FunThingGeneratorModel.Generator;
import FunThingGeneratorModel.IFunThing;
import FunThingGeneratorModel.NoMatchException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by DJ on 10/1/2016.
 */
public class FunThingFrame extends JFrame{
    JButton vetoButton;
    JButton acceptButton;
    FunThingPanel ftPanel;
    JLabel loadingLabel;
    JPanel bottomPanel = new JPanel(new GridLayout(0, 1));

    ImageIcon loadingIcon = new ImageIcon("ajax-loader.gif");

    public FunThingFrame(IFunThing thing) {
        vetoButton = new JButton("VETO!");
        acceptButton = new JButton("YES!");
        acceptButton.addActionListener(e->{
            JOptionPane.showMessageDialog(this, "CONGRATULATIONS!!! \n" +
                    "You have selected a fun thing!!! \n" +
                    "Go do the thing! \n" +
                    "omg you're going to have so much fun");
        });
        vetoButton.addActionListener(e->{try {
            IFunThing f = Generator.generate(Preferences.getPeople(),Preferences.getMinutes(),Preferences.getCost(),
                    Preferences.isOutside(),Preferences.getCategories());
            this.remove(ftPanel);
            this.add(new FunThingPanel(f));
            this.revalidate();
            this.repaint();
        } catch (NoMatchException e1) {
            JOptionPane.showMessageDialog(this, e1.getMessage(), "No fun things found.",JOptionPane.ERROR_MESSAGE);
        }});
        ftPanel = new FunThingPanel(thing);
        this.add(ftPanel, BorderLayout.CENTER);
        this.setSize(View.WIDTH, View.HEIGHT);
        this.loadingLabel = new JLabel("when pacman stops chomping, it's loading", loadingIcon, JLabel.CENTER);
        bottomPanel.add(new ButtonDuo(acceptButton, vetoButton));
        bottomPanel.add(loadingLabel);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private class ButtonDuo extends JPanel {
        public ButtonDuo(JButton leftButton, JButton rightButton) {
            this.setLayout(new FlowLayout());
            this.add(leftButton);
            this.add(rightButton);
        }
    }
}
