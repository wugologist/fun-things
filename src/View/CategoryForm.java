package View;

import FunThingGeneratorModel.Generator;
import FunThingGeneratorModel.IFunThing;
import View.PreferenceWidgets.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * Created by DJ on 10/1/2016.
 */
public class CategoryForm extends JFrame {
    JLabel peopleRangeLabel;
    JLabel timeRangeLabel;
    JLabel costLabel;
    JLabel distanceLabel;
    JLabel outsideLabel;
    JLabel categoryLabel;

    CategoryWidget categoryWidget = new CategoryWidget();

    JPanel peopleRangeRow = new JPanel(new FlowLayout());
    JPanel timeRangeRow = new JPanel(new FlowLayout());
    JPanel costRow = new JPanel(new FlowLayout());
    JPanel distanceRow = new JPanel(new FlowLayout());
    JPanel outsideRow = new JPanel(new FlowLayout());
    JPanel categoryRow = new JPanel(new FlowLayout());

    JButton entertainMeButton;

    public CategoryForm() {
        this.peopleRangeLabel = new JLabel("Number of People: ");
        this.timeRangeLabel = new JLabel("Maximum Duration: ");
        this.costLabel = new JLabel("Maximum Cost: ");
        this.distanceLabel = new JLabel("Maximum Distance: ");
        this.outsideLabel = new JLabel("Wanna go outside?");
        this.categoryLabel = new JLabel("Categories: ");

        this.entertainMeButton = new JButton("ENTERTAIN ME");

        this.peopleRangeRow.add(peopleRangeLabel);
        this.timeRangeRow.add(timeRangeLabel);
        this.costRow.add(costLabel);
        this.distanceRow.add(distanceLabel);
        this.outsideRow.add(outsideLabel);
        this.categoryRow.add(categoryLabel);

        JPanel innerPanel = new JPanel();
        JLabel preferences = new JLabel("PREFERENCES: ");
        preferences.setFont(new Font("Serif", Font.BOLD, 50));
        preferences.setAlignmentX(Component.CENTER_ALIGNMENT);
        PeopleRangeWidget peopleRangeWidget = new PeopleRangeWidget();
        TimeRangeWidget timeRangeWidget = new TimeRangeWidget();
        CostRangeWidget costRangeWidget = new CostRangeWidget();
        DistanceWidget distanceWidget = new DistanceWidget();
        OutsideOrNahWidget outsideWidget = new OutsideOrNahWidget();
        peopleRangeRow.add(peopleRangeWidget);
        timeRangeRow.add(timeRangeWidget);
        costRow.add(costRangeWidget);
        distanceRow.add(distanceWidget);
        outsideRow.add(outsideWidget);
        categoryRow.add(categoryWidget);
        innerPanel.add(preferences, BorderLayout.NORTH);
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.add(this.peopleRangeRow);
        innerPanel.add(this.timeRangeRow);
        innerPanel.add(this.costRow);
        innerPanel.add(this.distanceRow);
        innerPanel.add(outsideRow);
        innerPanel.add(this.categoryRow);
        innerPanel.add(this.entertainMeButton);
        this.categoryWidget.addEnablerToChildren(this.entertainMeButton);
        this.entertainMeButton.addActionListener(e -> {
            this.setVisible(false);
            IFunThing thing = new Generator().generate(peopleRangeWidget.getValue(), timeRangeWidget.getValue(),
                    costRangeWidget.getValue(),outsideWidget.getValue(), categoryWidget.getValue());
            //new FunThingFrame(thing).setVisible(true);
            this.dispose();
        });
        this.entertainMeButton.setEnabled(false);
        this.add(innerPanel);
        this.setSize(View.WIDTH, View.HEIGHT);
    }

    private class MockFunThing implements IFunThing{

        @Override
        public String getName() {
            return "Watch cat videos.";
        }

        @Override
        public String getInfoString() {
            return "WATCH ALL THE CAT VIDEOS.";
        }
    }
}