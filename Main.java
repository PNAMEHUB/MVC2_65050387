import controller.MagicPetController;
import model.MagicPetDatabase;
import model.Owl;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {
        MagicPetDatabase database = new MagicPetDatabase();
        MainFrame mainFrame = new MainFrame();
        MagicPetController controller = new MagicPetController(database, mainFrame);

        mainFrame.setVisible(true);
    }
}