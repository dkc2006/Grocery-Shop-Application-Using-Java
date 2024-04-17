import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GroceryShopApp {
    private JFrame frame;
    private Map<String, Double> groceryItems;
    private Map<String, Integer> cart;
    private JList<String> itemListbox;
    private JList<String> cartListbox;
    private JLabel totalLabel;

    public GroceryShopApp() {
        frame = new JFrame("Grocery Shop App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(2, 3));

        groceryItems = new HashMap<>();
        groceryItems.put("Apple", 2.00);
        groceryItems.put("Banana", 1.50);
        groceryItems.put("Milk", 3.00);
        groceryItems.put("Bread", 2.50);
        groceryItems.put("Eggs", 2.00);

        cart = new HashMap<>();

        itemListbox = new JList<>(groceryItems.keySet().toArray(new String[0]));
        JScrollPane itemListScrollPane = new JScrollPane(itemListbox);
        frame.add(itemListScrollPane);

        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = itemListbox.getSelectedValue();
                if (selected != null) {
                    cart.put(selected, cart.getOrDefault(selected, 0) + 1);
                    updateCart();
                }
            }
        });
        frame.add(addButton);

        JLabel cartLabel = new JLabel("Your Cart:");
        frame.add(cartLabel);

        cartListbox = new JList<>();
        JScrollPane cartListScrollPane = new JScrollPane(cartListbox);
        frame.add(cartListScrollPane);

        totalLabel = new JLabel("Total: $0.00");
        frame.add(totalLabel);

        frame.setVisible(true);
    }

    private void updateCart() {
        DefaultListModel<String> cartListModel = new DefaultListModel<>();
        double total = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            cartListModel.addElement(item + ": " + quantity);
            total += groceryItems.get(item) * quantity;
        }
        cartListbox.setModel(cartListModel);
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GroceryShopApp();
            }
        });
    }
}
