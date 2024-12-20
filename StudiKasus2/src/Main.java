import model.MyBatisUtil;
import model.StudentMapper;
import view.WelcomeView;
import controller.MainController;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            try {
                SqlSession session = MyBatisUtil.getSqlSession();
                StudentMapper mapper = session.getMapper(StudentMapper.class);
                WelcomeView welcomeView = new WelcomeView();
                MainController controller = new MainController(welcomeView, mapper, session);
                welcomeView.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error starting application: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}