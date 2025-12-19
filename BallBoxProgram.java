import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Κλάση Ball
class Ball {
    private String color;
    private String material;
    private double weight;

    // Constructor
    public Ball(String color, String material, double weight) {
        this.color = color;
        this.material = material;
        this.weight = weight;
    }

    // Getters
    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    public double getWeight() {
        return weight;
    }

    // Setters
    public void setColor(String color) {
        this.color = color;
    }

    // Μέθοδος εκτύπωσης χαρακτηριστικών
    public void printAll() {
        System.out.println("Χρώμα: " + color + ", Υλικό: " + material + ", Βάρος: " + weight + "kg");
    }
}

// Κλάση Box
class Box {
    private String material;
    private List<Ball> content;

    // Constructor
    public Box(String material) {
        this.material = material;
        this.content = new ArrayList<>();
    }

    // Προσθήκη μπάλας στο κουτί
    public void addBall(Ball ball) {
        content.add(ball);
    }

    // Παροχή πρόσβασης στη λίστα content
    public List<Ball> getContent() {
        return content;
    }

    // Αφαίρεση των πρώτων 3 αντικειμένων
    public void removeFirst3() {
        for (int i = 0; i < 3 && !content.isEmpty(); i++) {
            content.remove(0);
        }
    }

    // Αφαίρεση αντικειμένων με βάρος μεγαλύτερο από το δοθέν
    public boolean removeAllMoreThanByWeight(double weight) {
        boolean removed = false;
        Iterator<Ball> iterator = content.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getWeight() > weight) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

    // Αφαίρεση αντικειμένων με συγκεκριμένο χρώμα
    public void removeAllByColor(String color) {
        content.removeIf(ball -> ball.getColor().equals(color));
    }

    // Υπολογισμός συνολικού βάρους
    public double getTotalWeight() {
        double totalWeight = 0;
        for (Ball ball : content) {
            totalWeight += ball.getWeight();
        }
        return totalWeight;
    }

    // Εκτύπωση χαρακτηριστικών κουτιού και περιεχομένων
    public void printAll() {
        System.out.println("Κουτί υλικό: " + material + ", Βάρος: " + getTotalWeight() + "kg");
        System.out.println("Περιεχόμενα:");
        for (Ball ball : content) {
            ball.printAll();
        }
    }

    // Επιστροφή μεγέθους περιεχομένων
    public int getContentSize() {
        return content.size();
    }
}

public class BallBoxProgram {
    public static void main(String[] args) {
        // Κατασκευή κουτιών
        Box box1 = new Box("χάρτινο");
        Box box2 = new Box("ξύλινο");

        // Δημιουργία λίστας με τις μπάλες
        List<Ball> myBalls = new ArrayList<>();
        for (int i = 0; i < 3; i++) myBalls.add(new Ball("κόκκινο", "μεταλλικό", 2.5));
        for (int i = 0; i < 2; i++) myBalls.add(new Ball("κόκκινο", "μεταλλικό", 1.5));
        for (int i = 0; i < 5; i++) myBalls.add(new Ball("μαύρο", "πλαστικό", 0.5));
        for (int i = 0; i < 5; i++) myBalls.add(new Ball("άσπρο", "λαστιχένιο", 1.1));

        // Προσθήκη κόκκινων στο ξύλινο κουτί και άλλων στο χάρτινο κουτί
        for (Ball ball : myBalls) {
            if (ball.getColor().equals("κόκκινο")) {
                box2.addBall(ball);
            } else {
                box1.addBall(ball);
            }
        }

        // Εκτύπωση αρχικών βαρών κουτιών
        System.out.println("Βάρος ξύλινου κουτιού: " + box2.getTotalWeight() + "kg");
        System.out.println("Βάρος χάρτινου κουτιού: " + box1.getTotalWeight() + "kg");

        // Αφαίρεση των πρώτων 3 αντικειμένων από το ξύλινο κουτί
        box2.removeFirst3();
        System.out.println("Βάρος ξύλινου κουτιού μετά την αφαίρεση: " + box2.getTotalWeight() + "kg");

        // Αλλαγή χρώματος όλων των άσπρων μπαλών στο χάρτινο κουτί σε κίτρινο
        for (Ball ball : box1.getContent()) {
            if (ball.getColor().equals("άσπρο")) {
                ball.setColor("κίτρινο");
            }
        }

        // Αφαίρεση όλων των μπαλών με βάρος > 1.0 από το χάρτινο κουτί
        box1.removeAllMoreThanByWeight(1.0);

        // Εκτύπωση κουτιών
        System.out.println("\nΧαρακτηριστικά χάρτινου κουτιού:");
        box1.printAll();
        System.out.println("\nΧαρακτηριστικά ξύλινου κουτιού:");
        box2.printAll();
    }
}
