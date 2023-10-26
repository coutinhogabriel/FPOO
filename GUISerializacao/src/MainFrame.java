import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame{
    public MainFrame() {
        super("Frame Principal da App");
        setDefaultCloseOperation(2);
        //add abas do Jpanel
        JTabbedPane abas = new JTabbedPane();
        abas.add("Cadastro Usuários", new CadastroUsuarios());
        //add abas de CadastroDeAgendamento
        abas.add("Agendamento", new CadastroAgendas());
        add(abas);
    }
    public void run(){
        pack();
        setVisible(true);
    }
}