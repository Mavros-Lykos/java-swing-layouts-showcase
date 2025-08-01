package LayoutsShowcase;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class LayoutShowcase extends JFrame {

    public LayoutShowcase() {
        setTitle("All Layouts Showcase");
        setSize(800, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel to hold all the layout examples
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add one panel for each layout type
        mainPanel.add(createBorderLayoutPanel());
        mainPanel.add(createFlowLayoutPanel());
        mainPanel.add(createGridLayoutPanel());
        mainPanel.add(createBoxLayoutPanel());
        mainPanel.add(createCardLayoutPanel());
        mainPanel.add(createGridBagLayoutPanel());

        // Wrap the main panel in a JScrollPane to allow scrolling
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);

        setVisible(true);
    }

    // Helper method to create a labeled panel with a border
    private JPanel createLabeledPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    // BorderLayout panel example
    private JPanel createBorderLayoutPanel() {
        JPanel panel = createLabeledPanel("BorderLayout");
        JPanel layoutPanel = new JPanel(new BorderLayout());

        layoutPanel.add(createColoredButton("North", new Color(204, 229, 255)), BorderLayout.NORTH);     // Soft Sky Blue
        layoutPanel.add(createColoredButton("South", new Color(255, 204, 204)), BorderLayout.SOUTH);     // Soft Rose
        layoutPanel.add(createColoredButton("East", new Color(204, 255, 204)), BorderLayout.EAST);       // Mint Green
        layoutPanel.add(createColoredButton("West", new Color(229, 204, 255)), BorderLayout.WEST);       // Lavender
        layoutPanel.add(createColoredButton("Center", new Color(255, 255, 204)), BorderLayout.CENTER);   // Light Butter Yellow

        panel.add(layoutPanel, BorderLayout.CENTER);
        panel.add(new JLabel("BorderLayout divides space into 5 regions. Resize the window to see how they stretch."), BorderLayout.SOUTH);
        return panel;
    }

    // FlowLayout panel example
    private JPanel createFlowLayoutPanel() {
        JPanel panel = createLabeledPanel("FlowLayout");
        JPanel layoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        layoutPanel.add(createSizedButton("Short", 80, 30));
        layoutPanel.add(createSizedButton("Longer Button", 150, 30));
        layoutPanel.add(createSizedButton("Medium", 100, 30));
        layoutPanel.add(createSizedButton("Wrap Me If Needed", 160, 30));
        layoutPanel.add(createSizedButton("Tiny", 60, 30));

        panel.add(layoutPanel, BorderLayout.CENTER);
        panel.add(new JLabel("FlowLayout aligns components left to right, wrapping when needed."), BorderLayout.SOUTH);
        return panel;
    }

    // GridLayout panel example
    private JPanel createGridLayoutPanel() {
        JPanel panel = createLabeledPanel("GridLayout (2x3)");
        JPanel layoutPanel = new JPanel(new GridLayout(2, 3, 5, 5));

        for (int i = 1; i <= 6; i++) {
            layoutPanel.add(createColoredButton("Grid " + i, new Color(200, 255 - i * 20, 200)));
        }

        panel.add(layoutPanel, BorderLayout.CENTER);
        panel.add(new JLabel("GridLayout enforces equal-sized cells, even with different content."), BorderLayout.SOUTH);
        return panel;
    }

    // BoxLayout panel example (Y_AXIS)
    private JPanel createBoxLayoutPanel() {
        JPanel panel = createLabeledPanel("BoxLayout (Y_AXIS)");
        JPanel layoutPanel = new JPanel();
        layoutPanel.setLayout(new BoxLayout(layoutPanel, BoxLayout.Y_AXIS));

        layoutPanel.add(createSizedButton("Box 1 (max 200px)", 200, 30));
        layoutPanel.add(Box.createVerticalStrut(10));
        layoutPanel.add(createSizedButton("Box 2 (fixed 150px)", 150, 30));
        layoutPanel.add(Box.createVerticalStrut(10));
        layoutPanel.add(createSizedButton("Box 3 (wider)", 300, 30));
        layoutPanel.add(Box.createVerticalStrut(10));
        layoutPanel.add(createSizedButton("Box 4", 100, 30));

        panel.add(layoutPanel, BorderLayout.CENTER);
        panel.add(new JLabel("BoxLayout stacks components vertically. Components keep their preferred sizes."), BorderLayout.SOUTH);
        return panel;
    }

    // CardLayout panel example with interactive switching
    private JPanel createCardLayoutPanel() {
        JPanel panel = createLabeledPanel("CardLayout (Interactive)");

        JPanel layoutPanel = new JPanel(new CardLayout());
        layoutPanel.setPreferredSize(new Dimension(400, 100));

        JPanel card1 = createCard("Card 1", Color.PINK);
        JPanel card2 = createCard("Card 2", Color.CYAN);
        JPanel card3 = createCard("Card 3", Color.LIGHT_GRAY);

        layoutPanel.add(card1, "Card1");
        layoutPanel.add(card2, "Card2");
        layoutPanel.add(card3, "Card3");

        // Controls
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prevBtn = new JButton("Previous");
        JButton nextBtn = new JButton("Next");
        JButton card1Btn = new JButton("Card 1");
        JButton card2Btn = new JButton("Card 2");
        JButton card3Btn = new JButton("Card 3");

        buttonPanel.add(prevBtn);
        buttonPanel.add(nextBtn);
        buttonPanel.add(card1Btn);
        buttonPanel.add(card2Btn);
        buttonPanel.add(card3Btn);

        CardLayout cl = (CardLayout) layoutPanel.getLayout();

        prevBtn.addActionListener(e -> cl.previous(layoutPanel));
        nextBtn.addActionListener(e -> cl.next(layoutPanel));
        card1Btn.addActionListener(e -> cl.show(layoutPanel, "Card1"));
        card2Btn.addActionListener(e -> cl.show(layoutPanel, "Card2"));
        card3Btn.addActionListener(e -> cl.show(layoutPanel, "Card3"));

        panel.add(layoutPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    // GridBagLayout panel example
    private JPanel createGridBagLayoutPanel() {
        JPanel panel = createLabeledPanel("GridBagLayout");
        JPanel layoutPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // Pastel colors
        Color pastelBlue = new Color(204, 229, 255); // soft sky blue
        Color pastelGreen = new Color(204, 255, 229); // light mint
        Color pastelPeach = new Color(255, 229, 204); // light peach
        Color pastelLavender = new Color(229, 204, 255); // light lavender

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        layoutPanel.add(createColoredButton("GB1", pastelBlue), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        layoutPanel.add(createColoredButton("GB2", pastelGreen), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        layoutPanel.add(createColoredButton("GB3 (span 2 cols)", pastelPeach), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        layoutPanel.add(createColoredButton("GB4", pastelLavender), gbc);

        panel.add(layoutPanel, BorderLayout.CENTER);
        panel.add(new JLabel("GridBagLayout provides fine-grained control over position, size, and stretching."), BorderLayout.SOUTH);
        return panel;
    }

    // Helper: Colored button
    private JButton createColoredButton(String label, Color color) {
        JButton button = new JButton(label);
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    // Helper: Sized button
    private JButton createSizedButton(String label, int width, int height) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    // Helper: Labeled card panel
    private JPanel createCard(String label, Color bgColor) {
        JPanel card = new JPanel();
        card.setBackground(bgColor);
        card.add(new JLabel(label));
        return card;
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();

        SwingUtilities.invokeLater(LayoutShowcase::new);
    }
}
